//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB),
// v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2018.08.13 à 01:08:19 PM CEST
//

package fr.progilone.pgcn.domain.jaxb.oaipmh;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour granularityType.
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;simpleType name="granularityType"&gt;
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 * &lt;enumeration value="YYYY-MM-DD"/&gt;
 * &lt;enumeration value="YYYY-MM-DDThh:mm:ssZ"/&gt;
 * &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "granularityType")
@XmlEnum
public enum GranularityType {

	@XmlEnumValue("YYYY-MM-DD")
	YYYY_MM_DD("YYYY-MM-DD"), @XmlEnumValue("YYYY-MM-DDThh:mm:ssZ")
	YYYY_MM_DD_THH_MM_SS_Z("YYYY-MM-DDThh:mm:ssZ");

	private final String value;

	GranularityType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static GranularityType fromValue(String v) {
		for (GranularityType c : GranularityType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
