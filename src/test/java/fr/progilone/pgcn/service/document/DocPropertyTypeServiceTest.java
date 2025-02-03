package fr.progilone.pgcn.service.document;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import fr.progilone.pgcn.domain.document.DocPropertyType;
import fr.progilone.pgcn.exception.PgcnValidationException;
import fr.progilone.pgcn.exception.message.PgcnErrorCode;
import fr.progilone.pgcn.repository.document.DocPropertyTypeRepository;
import fr.progilone.pgcn.service.exchange.MappingService;
import fr.progilone.pgcn.util.TestUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.ReturnsArgumentAt;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DocPropertyTypeServiceTest {

	@Mock
	private DocPropertyTypeRepository docPropertyTypeRepository;

	@Mock
	private DocPropertyService docPropertyService;

	@Mock
	private MappingService mappingService;

	private DocPropertyTypeService service;

	@BeforeEach
	public void setUp() {
		service = new DocPropertyTypeService(docPropertyTypeRepository, docPropertyService, mappingService);
	}

	@Test
	public void testFindAll() {
		final List<DocPropertyType> properties = new ArrayList<>();
		when(docPropertyTypeRepository.findAll()).thenReturn(properties);
		final List<DocPropertyType> actual = service.findAll();
		assertSame(properties, actual);
	}

	@Test
	public void testFindAllBySuperType() {
		final List<DocPropertyType> properties = new ArrayList<>();
		when(docPropertyTypeRepository.findAllBySuperType(DocPropertyType.DocPropertySuperType.CUSTOM))
			.thenReturn(properties);
		final List<DocPropertyType> actual = service.findAllBySuperType(DocPropertyType.DocPropertySuperType.CUSTOM);
		assertSame(properties, actual);
	}

	@Test
	public void testFindAllByIdentifierIn() {
		final List<DocPropertyType> properties = new ArrayList<>();
		final List<String> identifiers = Arrays.asList("1", "2", "3");
		when(docPropertyTypeRepository.findAllById(identifiers)).thenReturn(properties);
		final List<DocPropertyType> actual = service.findAllByIdentifierIn(identifiers);
		assertSame(properties, actual);
	}

	@Test
	public void testFindOne() {
		final String identifier = "ID-001";
		final DocPropertyType property = new DocPropertyType();
		when(docPropertyTypeRepository.findById(identifier)).thenReturn(Optional.of(property));
		final DocPropertyType actual = service.findOne(identifier);
		assertSame(property, actual);
	}

	@Test
	public void testSave() {
		when(docPropertyTypeRepository.save(any(DocPropertyType.class))).then(new ReturnsArgumentAt(0));
		when(docPropertyTypeRepository.findCurrentRankForPropertyType(DocPropertyType.DocPropertySuperType.CUSTOM))
			.thenReturn(45);

		try {
			// pas de libellé
			final DocPropertyType property = new DocPropertyType();
			property.setSuperType(DocPropertyType.DocPropertySuperType.CUSTOM);
			property.setRank(14);

			final DocPropertyType actual = service.save(property);
			fail("testSave: PgcnValidationException expected");

		}
		catch (final PgcnValidationException e) {
			TestUtil.checkPgcnException(e, PgcnErrorCode.DOC_PROP_TYPE_LABEL_MANDATORY);
		}

		try {
			// le rang est défini
			final DocPropertyType property = new DocPropertyType();
			property.setSuperType(DocPropertyType.DocPropertySuperType.CUSTOM);
			property.setRank(14);
			property.setLabel("libellé de mon champ");

			final DocPropertyType actual = service.save(property);
			assertNotNull(actual);
			assertEquals(14L, actual.getRank().longValue());

		}
		catch (final PgcnValidationException e) {
			fail("testSave: PgcnValidationException not expected");
		}

		try {
			// le rang n'est pas défini
			final DocPropertyType property = new DocPropertyType();
			property.setSuperType(DocPropertyType.DocPropertySuperType.CUSTOM);
			property.setLabel("libellé de mon champ");

			final DocPropertyType actual = service.save(property);

			assertNotNull(actual);
			assertEquals(46L, actual.getRank().longValue());

		}
		catch (final PgcnValidationException e) {
			fail("testSave: PgcnValidationException not expected");
		}
	}

	@Test
	public void testDelete() {
		final String identifier = "ID-DEL";
		final DocPropertyType property = new DocPropertyType();
		property.setIdentifier(identifier);

		when(docPropertyService.countByType(property)).thenReturn(10, 0);
		when(mappingService.countByPropertyType(property)).thenReturn(20, 0);

		// ko
		try {
			service.delete(property);
			fail("testDelete: expecting validation exception");
		}
		catch (final PgcnValidationException e) {
			verify(docPropertyTypeRepository, never()).delete(property);
			TestUtil.checkPgcnException(e, PgcnErrorCode.DOC_PROP_TYPE_DEL_USED_MAPPING,
					PgcnErrorCode.DOC_PROP_TYPE_DEL_USED_PROP);
		}

		// ok
		try {
			service.delete(property);
			verify(docPropertyTypeRepository).delete(property);
		}
		catch (final PgcnValidationException e) {
			fail("testDelete: expecting no validation exception");
		}
	}

}
