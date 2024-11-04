//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
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
 * Classe Java pour autoFocusType.
 * </p>
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * </p>
 *
 * <pre>
 * &lt;simpleType name="autoFocusType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Auto Focus Used"/&gt;
 *     &lt;enumeration value="Auto Focus Interrupted"/&gt;
 *     &lt;enumeration value="Near Focused"/&gt;
 *     &lt;enumeration value="Soft Focused"/&gt;
 *     &lt;enumeration value="Manual"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 */
@XmlType(name = "autoFocusType")
@XmlEnum
public enum AutoFocusType {

    @XmlEnumValue("Auto Focus Used")
    AUTO_FOCUS_USED("Auto Focus Used"),
    @XmlEnumValue("Auto Focus Interrupted")
    AUTO_FOCUS_INTERRUPTED("Auto Focus Interrupted"),
    @XmlEnumValue("Near Focused")
    NEAR_FOCUSED("Near Focused"),
    @XmlEnumValue("Soft Focused")
    SOFT_FOCUSED("Soft Focused"),
    @XmlEnumValue("Manual")
    MANUAL("Manual");

    private final String value;

    AutoFocusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AutoFocusType fromValue(String v) {
        for (AutoFocusType c : AutoFocusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
