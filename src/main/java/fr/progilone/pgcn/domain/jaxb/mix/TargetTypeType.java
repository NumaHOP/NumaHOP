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
 * Classe Java pour targetTypeType.
 * </p>
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * </p>
 *
 * <pre>
 * &lt;simpleType name="targetTypeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="external"/&gt;
 *     &lt;enumeration value="internal"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 */
@XmlType(name = "targetTypeType")
@XmlEnum
public enum TargetTypeType {

    @XmlEnumValue("external")
    EXTERNAL("external"),
    @XmlEnumValue("internal")
    INTERNAL("internal");

    private final String value;

    TargetTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TargetTypeType fromValue(String v) {
        for (TargetTypeType c : TargetTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
