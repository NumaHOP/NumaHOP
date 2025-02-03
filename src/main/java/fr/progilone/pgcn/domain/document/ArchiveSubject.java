package fr.progilone.pgcn.domain.document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import fr.progilone.pgcn.domain.AbstractDomainObject;
import jakarta.persistence.*;

/**
 * Classe représentant un sujet d'un objet d'export IA
 */
@Entity
@Table(name = ArchiveSubject.TABLE_NAME)
@JsonSubTypes({ @JsonSubTypes.Type(name = "doc_archive_subject", value = ArchiveSubject.class) })
public class ArchiveSubject extends AbstractDomainObject {

	public static final String TABLE_NAME = "doc_archive_subject";

	/**
	 * Valeur
	 */
	@Column(name = "value", columnDefinition = "text")
	private String value;

	/**
	 * Notice rattachée
	 */
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "item")
	private ArchiveItem item;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public ArchiveItem getItem() {
		return item;
	}

	public void setItem(ArchiveItem item) {
		this.item = item;
	}

}
