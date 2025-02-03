package fr.progilone.pgcn.service.administration.mapper;

import fr.progilone.pgcn.domain.administration.InternetArchiveConfiguration;
import fr.progilone.pgcn.domain.dto.administration.InternetArchiveConfigurationDTO;
import fr.progilone.pgcn.service.library.mapper.SimpleLibraryMapper;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jbrunet Créé le 19 avr. 2017
 */
@Mapper(uses = { SimpleLibraryMapper.class, InternetArchiveCollectionMapper.class })
public interface InternetArchiveConfigurationMapper {

	InternetArchiveConfigurationMapper INSTANCE = Mappers.getMapper(InternetArchiveConfigurationMapper.class);

	InternetArchiveConfigurationDTO configurationIAToDto(InternetArchiveConfiguration conf);

	Set<InternetArchiveConfigurationDTO> configurationIAToDtos(Set<InternetArchiveConfiguration> conf);

}
