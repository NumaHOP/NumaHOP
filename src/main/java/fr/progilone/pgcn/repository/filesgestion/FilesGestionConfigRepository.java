package fr.progilone.pgcn.repository.filesgestion;

import fr.progilone.pgcn.domain.filesgestion.FilesGestionConfig;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilesGestionConfigRepository extends JpaRepository<FilesGestionConfig, String> {

	@Query("select conf " + "from FilesGestionConfig conf " + "join fetch conf.library l "
			+ "left join fetch l.defaultRole " + "where l.identifier = ?1")
	FilesGestionConfig getOneByLibraryIdentifier(String libraryIdentifier);

	@Query("select conf " + "from FilesGestionConfig conf " + "join fetch conf.library l "
			+ "left join fetch l.defaultRole " + "where conf.identifier = ?1")
	FilesGestionConfig findOne(String id);

	@Override
	@Query("select conf " + "from FilesGestionConfig conf " + "join fetch conf.library l "
			+ "left join fetch l.defaultRole ")
	List<FilesGestionConfig> findAll();

}
