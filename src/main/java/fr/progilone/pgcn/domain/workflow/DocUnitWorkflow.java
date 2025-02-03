package fr.progilone.pgcn.domain.workflow;

import fr.progilone.pgcn.domain.AbstractDomainObject;
import fr.progilone.pgcn.domain.document.DocUnit;
import fr.progilone.pgcn.service.util.NumahopCollectors;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Un workflow attaché à un {@link DocUnit} Il possède n {@link DocUnitState}
 *
 * @author jbrunet Créé le 5 juil. 2017
 */
@Entity
@Table(name = DocUnitWorkflow.TABLE_NAME)
public class DocUnitWorkflow extends AbstractDomainObject {

	public static final String TABLE_NAME = "doc_workflow";

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "model", nullable = false)
	private WorkflowModel model;

	@OneToMany(mappedBy = "workflow", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private final Set<DocUnitState> states = new HashSet<>();

	/**
	 * Date de début effective du workflow
	 */
	@Column(name = "start_date")
	private LocalDateTime startDate;

	/**
	 * Date de fin effective ddu workflow (réalisation)
	 */
	@Column(name = "end_date")
	private LocalDateTime endDate;

	@OneToOne(mappedBy = "workflow", fetch = FetchType.EAGER)
	private DocUnit docUnit;

	public WorkflowModel getModel() {
		return model;
	}

	public void setModel(final WorkflowModel model) {
		this.model = model;
	}

	public Set<DocUnitState> getStates() {
		return states;
	}

	public void setStates(final Set<DocUnitState> states) {
		this.states.clear();
		if (states != null) {
			states.forEach(this::addState);
		}
	}

	public void addState(final DocUnitState state) {
		if (state != null) {
			state.setWorkflow(this);
			states.add(state);
		}
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(final LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(final LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public DocUnit getDocUnit() {
		return docUnit;
	}

	public void setDocUnit(final DocUnit docUnit) {
		this.docUnit = docUnit;
	}

	/**
	 * Retourne la liste des étapes en cours ie l'ensemble des étapes dont le statut est
	 * {@link WorkflowStateStatus#PENDING} ou {@link WorkflowStateStatus#WAITING}
	 */
	public List<DocUnitState> getCurrentStates() {
		return states.stream()
			.filter(state -> WorkflowStateStatus.PENDING.equals(state.getStatus())
					|| WorkflowStateStatus.WAITING.equals(state.getStatus()))
			.collect(Collectors.toList());
	}

	/**
	 * Retourne l'étape en cours dont la {@link WorkflowStateKey} est spécifiée Retourne
	 * null si ce n'est pas une étape en cours sauf cas particulier de Validation Constat
	 * Etat.
	 */
	public DocUnitState getCurrentStateByKey(final WorkflowStateKey key) {
		if (key == null) {
			return null;
		}
		return states.stream()
			.filter(state -> (WorkflowStateStatus.PENDING.equals(state.getStatus())
					|| WorkflowStateStatus.WAITING.equals(state.getStatus())
					|| WorkflowStateStatus.WAITING_NEXT_COMPLETED.equals(state.getStatus()))
					&& key.equals(state.getKey()))
			.collect(NumahopCollectors.zeroOrOneCollector());
	}

	/**
	 * Retourne l'ensemble des étapes pour une clé (passée ou non, il peut y en avoir
	 * plusieurs avec la même clé comme la RELIVRAISON)
	 */
	public List<DocUnitState> getByKey(final WorkflowStateKey key) {
		return states.stream().filter(state -> state.getKey().equals(key)).collect(Collectors.toList());
	}

	/**
	 * Retourne l'étape correspondant à la clé qui ne s'est pas encore déroulée (est
	 * forcément unique ou inexistante s'il s'est déjà déroulée)
	 * @param key étape de workflow
	 */
	public DocUnitState getFutureOrRunningByKey(final WorkflowStateKey key) {
		return states.stream()
			.filter(state -> state.getKey().equals(key) && state.isFutureOrCurrentState())
			.collect(NumahopCollectors.zeroOrOneCollector());
	}

	/**
	 * Retourne toutes les étapes non déroulées
	 * @return boolean
	 */
	public List<DocUnitState> getFutureOrRunning() {
		return states.stream().filter(DocUnitState::isFutureOrCurrentState).collect(Collectors.toList());
	}

	/**
	 * Retourne vrai si le document a été rejeté.
	 * @return boolean
	 */
	public boolean isDocumentRejected() {
		return states.stream()
			.anyMatch(state -> state.getKey().equals(WorkflowStateKey.VALIDATION_DOCUMENT) && state.isRejected());
	}

	/**
	 * Retourne vrai si le document a été validé.
	 * @return boolean
	 */
	public boolean isDocumentValidated() {
		return states.stream()
			.anyMatch(state -> state.getKey().equals(WorkflowStateKey.VALIDATION_DOCUMENT) && state.isValidated());
	}

	public boolean isNoticeValidated() {
		return states.stream()
			.anyMatch(state -> state.getKey().equals(WorkflowStateKey.VALIDATION_NOTICES) && state.isValidated());
	}

	/**
	 * Retourne vrai si le rapport de controles a été envoyé au prestataire.
	 * @return boolean
	 */
	public boolean isRapportSent() {
		return states.stream()
			.anyMatch(state -> state.getKey().equals(WorkflowStateKey.RAPPORT_CONTROLES) && state.isValidated());
	}

	/**
	 * Retourne vrai si le rapport de controles a échoué à l'envoi
	 * @return boolean
	 */
	public boolean isRapportFailed() {
		return states.stream()
			.anyMatch(state -> state.getKey().equals(WorkflowStateKey.RAPPORT_CONTROLES) && state.isRejected());
	}

	/**
	 * Retourne vrai si le workflow est terminé
	 * @return boolean
	 */
	public boolean isDone() {
		return endDate != null;
	}

}
