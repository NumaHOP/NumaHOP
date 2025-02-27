package fr.progilone.pgcn.repository.es;

import fr.progilone.pgcn.domain.es.project.EsProject;
import fr.progilone.pgcn.repository.es.helper.EsSearchOperation;
import fr.progilone.pgcn.repository.es.helper.SearchResultPage;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface EsProjectRepositoryCustom {

	/**
	 * Recherche paginée
	 * @param search
	 * @param libraries
	 * @param fuzzy
	 * @param filters
	 * @param pageable
	 * @param facet
	 */
	SearchResultPage<EsProject> search(final EsSearchOperation[] search, final List<String> libraries,
			final boolean fuzzy, final EsSearchOperation[] filters, final PageRequest pageable, final boolean facet);

}
