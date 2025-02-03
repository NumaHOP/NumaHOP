/**
 *
 */
package fr.progilone.pgcn.domain.document.sample;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import fr.progilone.pgcn.domain.AbstractDomainObject;
import fr.progilone.pgcn.domain.delivery.Delivery;
import fr.progilone.pgcn.domain.document.DigitalDocument;
import fr.progilone.pgcn.domain.document.DocPage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Emmanuel RIZET
 *
 */
@Entity
@Table(name = Sample.TABLE_NAME)
@JsonSubTypes({ @JsonSubTypes.Type(name = "doc_sample", value = Sample.class) })
public class Sample extends AbstractDomainObject {

	public static final String TABLE_NAME = "doc_sample";

	/**
	 * Lien vers la Delivery
	 */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery")
	private Delivery delivery;

	/**
	 * Lien vers le DigitalDocument
	 */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "digital_document")
	private DigitalDocument digitalDocument;

	/**
	 * Liste des pages echantillonnees.
	 */
	@OneToMany(mappedBy = "sample", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private final Set<DocPage> pages = new HashSet<>();

	@Column(name = "sampling_mode")
	private String samplingMode;

	/**
	 * @return the delivery
	 */
	public Delivery getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery the delivery to set
	 */
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return the digitalDocument
	 */
	public DigitalDocument getDigitalDocument() {
		return digitalDocument;
	}

	/**
	 * @param digitalDocument the digitalDocument to set
	 */
	public void setDigitalDocument(DigitalDocument digitalDocument) {
		this.digitalDocument = digitalDocument;
	}

	/**
	 * @return the samplingMode
	 */
	public String getSamplingMode() {
		return samplingMode;
	}

	/**
	 * @param samplingMode the samplingMode to set
	 */
	public void setSamplingMode(String samplingMode) {
		this.samplingMode = samplingMode;
	}

	/**
	 * @return the pages
	 */
	public Set<DocPage> getPages() {
		return pages;
	}

}
