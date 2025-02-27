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
 * Classe Java pour captureDeviceType.
 * </p>
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * </p>
 *
 * <pre>
 * &lt;simpleType name="captureDeviceType"&gt;
 * &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 * &lt;enumeration value="transmission scanner"/&gt;
 * &lt;enumeration value="reflection print scanner"/&gt;
 * &lt;enumeration value="digital still camera"/&gt;
 * &lt;enumeration value="still from video"/&gt;
 * &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "captureDeviceType")
@XmlEnum
public enum CaptureDeviceType {

	@XmlEnumValue("transmission scanner")
	TRANSMISSION_SCANNER("transmission scanner"), @XmlEnumValue("reflection print scanner")
	REFLECTION_PRINT_SCANNER("reflection print scanner"), @XmlEnumValue("digital still camera")
	DIGITAL_STILL_CAMERA("digital still camera"), @XmlEnumValue("still from video")
	STILL_FROM_VIDEO("still from video");

	private final String value;

	CaptureDeviceType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static CaptureDeviceType fromValue(String v) {
		for (CaptureDeviceType c : CaptureDeviceType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
