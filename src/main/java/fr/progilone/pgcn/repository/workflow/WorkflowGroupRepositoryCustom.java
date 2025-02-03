package fr.progilone.pgcn.repository.workflow;

import fr.progilone.pgcn.domain.workflow.WorkflowGroup;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author jbrunet Créé le 17 juil. 2017
 */
public interface WorkflowGroupRepositoryCustom {

	Page<WorkflowGroup> search(String search, final String initiale, List<String> libraries, Pageable pageRequest);

}
