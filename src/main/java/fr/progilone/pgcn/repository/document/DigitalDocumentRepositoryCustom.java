package fr.progilone.pgcn.repository.document;

import fr.progilone.pgcn.domain.document.DigitalDocument;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lebouchp on 10/03/2017.
 */
public interface DigitalDocumentRepositoryCustom {

	Page<DigitalDocument> search(String search, List<DigitalDocument.DigitalDocumentStatus> status,
			List<String> libraries, List<String> projects, List<String> lots, List<String> trains,
			List<String> deliveries, LocalDate dateFrom, LocalDate dateTo, LocalDate dateLimitFrom,
			LocalDate dateLimitTo, boolean relivraison, String searchPgcnId, String searchTitre, String searchRadical,
			List<String> searchFileFormats, List<String> searchMaxAngles, Integer searchPageFrom, Integer searchPageTo,
			Long searchPageCheckFrom, Long searchPageCheckTo, Double searchMinSize, Double searchMaxSize,
			boolean validated, Pageable pageRequest);

}
