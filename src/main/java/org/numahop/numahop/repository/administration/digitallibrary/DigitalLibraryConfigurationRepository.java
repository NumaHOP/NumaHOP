package org.numahop.numahop.repository.administration.digitallibrary;

import org.numahop.numahop.domain.administration.digitallibrary.DigitalLibraryConfiguration;
import org.numahop.numahop.domain.library.Library;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DigitalLibraryConfigurationRepository
		extends JpaRepository<DigitalLibraryConfiguration, String>, DigitalLibraryConfigurationRepositoryCustom {

	@Query("""
			select conf from DigitalLibraryConfiguration conf
			where conf.library.identifier = ?1
			""")
	Set<DigitalLibraryConfiguration> findByLibrary(String libraryId);

	Set<DigitalLibraryConfiguration> findByLibraryAndActive(Library library, boolean active);

	@Query("""
			select conf from DigitalLibraryConfiguration conf
			where active = ?1
			""")
	List<DigitalLibraryConfiguration> findAll(boolean active);

	@Query("""
			select conf from DigitalLibraryConfiguration conf
			join fetch conf.library
			where conf.identifier = ?1
			""")
	DigitalLibraryConfiguration findOneWithDependencies(String identifier);

	@Query("""
			select conf.password from DigitalLibraryConfiguration conf
			where conf.identifier = ?1
			""")
	String findPasswordByIdentifier(String identifier);

}
