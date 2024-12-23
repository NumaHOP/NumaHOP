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
 * Classe Java pour gpsAltitudeRefType.
 * </p>
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * </p>
 *
 * <pre>
 * &lt;simpleType name="gpsAltitudeRefType"&gt;
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 * &lt;enumeration value="Sea level"/&gt;
 * &lt;enumeration value="Sea level reference (negative value)"/&gt;
 * &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "gpsAltitudeRefType")
@XmlEnum
public enum GpsAltitudeRefType {

	@XmlEnumValue("Sea level")
	SEA_LEVEL("Sea level"), @XmlEnumValue("Sea level reference (negative value)")
	SEA_LEVEL_REFERENCE_NEGATIVE_VALUE("Sea level reference (negative value)");

	private final String value;

	GpsAltitudeRefType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static GpsAltitudeRefType fromValue(String v) {
		for (GpsAltitudeRefType c : GpsAltitudeRefType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
