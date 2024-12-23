//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB),
// v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.05.16 à 10:56:55 AM CEST
//

package fr.progilone.pgcn.domain.jaxb.ead;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Classe Java pour occupation complex type.
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="occupation">
 * &lt;complexContent>
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * &lt;group ref="{urn:isbn:1-931666-22-9}m.phrase.bare" maxOccurs="unbounded" minOccurs="0"/>
 * &lt;attGroup ref="{urn:isbn:1-931666-22-9}a.access"/>
 * &lt;attGroup ref="{urn:isbn:1-931666-22-9}a.common"/>
 * &lt;attribute name="encodinganalog" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 * &lt;/restriction>
 * &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "occupation", propOrder = { "content" })
public class Occupation {

	@XmlElementRefs({
			@XmlElementRef(name = "extptr", namespace = "urn:isbn:1-931666-22-9", type = JAXBElement.class,
					required = false),
			@XmlElementRef(name = "emph", namespace = "urn:isbn:1-931666-22-9", type = JAXBElement.class,
					required = false),
			@XmlElementRef(name = "ptr", namespace = "urn:isbn:1-931666-22-9", type = JAXBElement.class,
					required = false),
			@XmlElementRef(name = "lb", namespace = "urn:isbn:1-931666-22-9", type = JAXBElement.class,
					required = false) })
	@XmlMixed
	protected List<Serializable> content;

	@XmlAttribute(name = "encodinganalog")
	@XmlSchemaType(name = "anySimpleType")
	protected String encodinganalog;

	@XmlAttribute(name = "source")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String source;

	@XmlAttribute(name = "rules")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String rules;

	@XmlAttribute(name = "authfilenumber")
	@XmlSchemaType(name = "anySimpleType")
	protected String authfilenumber;

	@XmlAttribute(name = "normal")
	@XmlSchemaType(name = "anySimpleType")
	protected String normal;

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
	 * Gets the value of the content property.
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present inside the
	 * JAXB object. This is why there is not a <CODE>set</CODE> method for the content
	 * property.
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getContent ().add (newItem);
	 * </pre>
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link JAXBElement
	 * }{@code <}{@link Extptr }{@code >} {@link String } {@link JAXBElement
	 * }{@code <}{@link Emph }{@code >} {@link JAXBElement }{@code <}{@link Ptr }{@code >}
	 * {@link JAXBElement }{@code <}{@link Lb }{@code >}
	 */
	public List<Serializable> getContent() {
		if (content == null) {
			content = new ArrayList<>();
		}
		return this.content;
	}

	/**
	 * Obtient la valeur de la propriété encodinganalog.
	 * @return possible object is {@link String }
	 */
	public String getEncodinganalog() {
		return encodinganalog;
	}

	/**
	 * Définit la valeur de la propriété encodinganalog.
	 * @param value allowed object is {@link String }
	 */
	public void setEncodinganalog(String value) {
		this.encodinganalog = value;
	}

	/**
	 * Obtient la valeur de la propriété source.
	 * @return possible object is {@link String }
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Définit la valeur de la propriété source.
	 * @param value allowed object is {@link String }
	 */
	public void setSource(String value) {
		this.source = value;
	}

	/**
	 * Obtient la valeur de la propriété rules.
	 * @return possible object is {@link String }
	 */
	public String getRules() {
		return rules;
	}

	/**
	 * Définit la valeur de la propriété rules.
	 * @param value allowed object is {@link String }
	 */
	public void setRules(String value) {
		this.rules = value;
	}

	/**
	 * Obtient la valeur de la propriété authfilenumber.
	 * @return possible object is {@link String }
	 */
	public String getAuthfilenumber() {
		return authfilenumber;
	}

	/**
	 * Définit la valeur de la propriété authfilenumber.
	 * @param value allowed object is {@link String }
	 */
	public void setAuthfilenumber(String value) {
		this.authfilenumber = value;
	}

	/**
	 * Obtient la valeur de la propriété normal.
	 * @return possible object is {@link String }
	 */
	public String getNormal() {
		return normal;
	}

	/**
	 * Définit la valeur de la propriété normal.
	 * @param value allowed object is {@link String }
	 */
	public void setNormal(String value) {
		this.normal = value;
	}

	/**
	 * Obtient la valeur de la propriété id.
	 * @return possible object is {@link String }
	 */
	public String getId() {
		return id;
	}

	/**
	 * Définit la valeur de la propriété id.
	 * @param value allowed object is {@link String }
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Obtient la valeur de la propriété altrender.
	 * @return possible object is {@link String }
	 */
	public String getAltrender() {
		return altrender;
	}

	/**
	 * Définit la valeur de la propriété altrender.
	 * @param value allowed object is {@link String }
	 */
	public void setAltrender(String value) {
		this.altrender = value;
	}

	/**
	 * Obtient la valeur de la propriété audience.
	 * @return possible object is {@link String }
	 */
	public String getAudience() {
		return audience;
	}

	/**
	 * Définit la valeur de la propriété audience.
	 * @param value allowed object is {@link String }
	 */
	public void setAudience(String value) {
		this.audience = value;
	}

}
