//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB),
// v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.08.25 à 03:15:17 PM CEST
//

package fr.progilone.pgcn.domain.jaxb.mix;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour opticalResolutionUnitType.
 * </p>
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * </p>
 *
 * <pre>
 * &lt;simpleType name="opticalResolutionUnitType"&gt;
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 * &lt;enumeration value="no absolute unit"/&gt;
 * &lt;enumeration value="in."/&gt;
 * &lt;enumeration value="cm"/&gt;
 * &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "opticalResolutionUnitType")
@XmlEnum
public enum OpticalResolutionUnitType {

	@XmlEnumValue("no absolute unit")
	NO_ABSOLUTE_UNIT("no absolute unit"), @XmlEnumValue("in.")
	IN("in."), @XmlEnumValue("cm")
	CM("cm");

	private final String value;

	OpticalResolutionUnitType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static OpticalResolutionUnitType fromValue(String v) {
		for (OpticalResolutionUnitType c : OpticalResolutionUnitType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
