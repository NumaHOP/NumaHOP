//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.16 à 10:56:55 AM CEST
//

package fr.progilone.pgcn.domain.jaxb.ead;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Classe Java pour row complex type.
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="row">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entry" type="{urn:isbn:1-931666-22-9}entry" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:isbn:1-931666-22-9}a.common"/>
 *       &lt;attribute name="rowsep" type="{urn:isbn:1-931666-22-9}yesorno" />
 *       &lt;attribute name="valign">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="top"/>
 *             &lt;enumeration value="middle"/>
 *             &lt;enumeration value="bottom"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "row", propOrder = { "entry" })
public class Row {

	@XmlElement(required = true)
	protected List<Entry> entry;

	@XmlAttribute(name = "rowsep")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	protected String rowsep;

	@XmlAttribute(name = "valign")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	protected String valign;

	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String id;

	@XmlAttribute(name = "altrender")
	@XmlSchemaType(name = "anySimpleType")
	protected String altrender;

	@XmlAttribute(name = "audience")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	protected String audience;

	/**
	 * Gets the value of the entry property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present inside the
	 * JAXB object. This is why there is not a <CODE>set</CODE> method for the entry
	 * property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getEntry().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Entry }
	 *
	 *
	 */
	public List<Entry> getEntry() {
		if (entry == null) {
			entry = new ArrayList<>();
		}
		return this.entry;
	}

	/**
	 * Obtient la valeur de la propriété rowsep.
	 * @return possible object is {@link String }
	 *
	 */
	public String getRowsep() {
		return rowsep;
	}

	/**
	 * Définit la valeur de la propriété rowsep.
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setRowsep(String value) {
		this.rowsep = value;
	}

	/**
	 * Obtient la valeur de la propriété valign.
	 * @return possible object is {@link String }
	 *
	 */
	public String getValign() {
		return valign;
	}

	/**
	 * Définit la valeur de la propriété valign.
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setValign(String value) {
		this.valign = value;
	}

	/**
	 * Obtient la valeur de la propriété id.
	 * @return possible object is {@link String }
	 *
	 */
	public String getId() {
		return id;
	}

	/**
	 * Définit la valeur de la propriété id.
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Obtient la valeur de la propriété altrender.
	 * @return possible object is {@link String }
	 *
	 */
	public String getAltrender() {
		return altrender;
	}

	/**
	 * Définit la valeur de la propriété altrender.
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setAltrender(String value) {
		this.altrender = value;
	}

	/**
	 * Obtient la valeur de la propriété audience.
	 * @return possible object is {@link String }
	 *
	 */
	public String getAudience() {
		return audience;
	}

	/**
	 * Définit la valeur de la propriété audience.
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setAudience(String value) {
		this.audience = value;
	}

}
