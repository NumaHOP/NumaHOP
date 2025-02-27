package fr.progilone.pgcn.service.library.mapper;

import fr.progilone.pgcn.domain.dto.library.LibraryDTO;
import fr.progilone.pgcn.domain.library.Library;
import fr.progilone.pgcn.service.administration.mapper.SimpleViewsFormatConfigurationMapper;
import fr.progilone.pgcn.service.checkconfiguration.mapper.SimpleCheckConfigurationMapper;
import fr.progilone.pgcn.service.ftpconfiguration.mapper.SimpleFTPConfigurationMapper;
import fr.progilone.pgcn.service.ocrlangconfiguration.mapper.OcrLangConfigurationMapper;
import fr.progilone.pgcn.service.user.mapper.AddressMapper;
import fr.progilone.pgcn.service.user.mapper.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { AddressMapper.class, SimpleFTPConfigurationMapper.class, SimpleCheckConfigurationMapper.class,
		SimpleViewsFormatConfigurationMapper.class, RoleMapper.class, OcrLangConfigurationMapper.class })
public interface LibraryMapper {

	LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

	LibraryDTO libraryToLibraryDTO(Library library);

}
