//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source.
// Généré le : 2017.08.25 à 03:15:17 PM CEST
//


package fr.progilone.pgcn.domain.jaxb.mix;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour mixType complex type.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 *
 * <pre>
 * &lt;complexType name="mixType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BasicDigitalObjectInformation" type="{http://www.loc.gov/mix/v20}BasicDigitalObjectInformationType" minOccurs="0"/>
 *         &lt;element name="BasicImageInformation" type="{http://www.loc.gov/mix/v20}BasicImageInformationType" minOccurs="0"/>
 *         &lt;element name="ImageCaptureMetadata" type="{http://www.loc.gov/mix/v20}ImageCaptureMetadataType" minOccurs="0"/>
 *         &lt;element name="ImageAssessmentMetadata" type="{http://www.loc.gov/mix/v20}ImageAssessmentMetadataType" minOccurs="0"/>
 *         &lt;element name="ChangeHistory" type="{http://www.loc.gov/mix/v20}ChangeHistoryType" minOccurs="0"/>
 *         &lt;element name="Extension" type="{http://www.loc.gov/mix/v20}extensionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mixType", propOrder = {
    "basicDigitalObjectInformation",
    "basicImageInformation",
    "imageCaptureMetadata",
    "imageAssessmentMetadata",
    "changeHistory",
    "extension"
})
@XmlSeeAlso({
    Mix.class
})
public class MixType {

    @XmlElement(name = "BasicDigitalObjectInformation")
    protected BasicDigitalObjectInformationType basicDigitalObjectInformation;
    @XmlElement(name = "BasicImageInformation")
    protected BasicImageInformationType basicImageInformation;
    @XmlElement(name = "ImageCaptureMetadata")
    protected ImageCaptureMetadataType imageCaptureMetadata;
    @XmlElement(name = "ImageAssessmentMetadata")
    protected ImageAssessmentMetadataType imageAssessmentMetadata;
    @XmlElement(name = "ChangeHistory")
    protected ChangeHistoryType changeHistory;
    @XmlElement(name = "Extension")
    protected List<ExtensionType> extension;

    /**
     * Obtient la valeur de la propriété basicDigitalObjectInformation.
     *
     * @return
     *     possible object is
     *     {@link BasicDigitalObjectInformationType }
     *
     */
    public BasicDigitalObjectInformationType getBasicDigitalObjectInformation() {
        return basicDigitalObjectInformation;
    }

    /**
     * Définit la valeur de la propriété basicDigitalObjectInformation.
     *
     * @param value
     *     allowed object is
     *     {@link BasicDigitalObjectInformationType }
     *
     */
    public void setBasicDigitalObjectInformation(BasicDigitalObjectInformationType value) {
        this.basicDigitalObjectInformation = value;
    }

    /**
     * Obtient la valeur de la propriété basicImageInformation.
     *
     * @return
     *     possible object is
     *     {@link BasicImageInformationType }
     *
     */
    public BasicImageInformationType getBasicImageInformation() {
        return basicImageInformation;
    }

    /**
     * Définit la valeur de la propriété basicImageInformation.
     *
     * @param value
     *     allowed object is
     *     {@link BasicImageInformationType }
     *
     */
    public void setBasicImageInformation(BasicImageInformationType value) {
        this.basicImageInformation = value;
    }

    /**
     * Obtient la valeur de la propriété imageCaptureMetadata.
     *
     * @return
     *     possible object is
     *     {@link ImageCaptureMetadataType }
     *
     */
    public ImageCaptureMetadataType getImageCaptureMetadata() {
        return imageCaptureMetadata;
    }

    /**
     * Définit la valeur de la propriété imageCaptureMetadata.
     *
     * @param value
     *     allowed object is
     *     {@link ImageCaptureMetadataType }
     *
     */
    public void setImageCaptureMetadata(ImageCaptureMetadataType value) {
        this.imageCaptureMetadata = value;
    }

    /**
     * Obtient la valeur de la propriété imageAssessmentMetadata.
     *
     * @return
     *     possible object is
     *     {@link ImageAssessmentMetadataType }
     *
     */
    public ImageAssessmentMetadataType getImageAssessmentMetadata() {
        return imageAssessmentMetadata;
    }

    /**
     * Définit la valeur de la propriété imageAssessmentMetadata.
     *
     * @param value
     *     allowed object is
     *     {@link ImageAssessmentMetadataType }
     *
     */
    public void setImageAssessmentMetadata(ImageAssessmentMetadataType value) {
        this.imageAssessmentMetadata = value;
    }

    /**
     * Obtient la valeur de la propriété changeHistory.
     *
     * @return
     *     possible object is
     *     {@link ChangeHistoryType }
     *
     */
    public ChangeHistoryType getChangeHistory() {
        return changeHistory;
    }

    /**
     * Définit la valeur de la propriété changeHistory.
     *
     * @param value
     *     allowed object is
     *     {@link ChangeHistoryType }
     *
     */
    public void setChangeHistory(ChangeHistoryType value) {
        this.changeHistory = value;
    }

    /**
     * Gets the value of the extension property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extension property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtension().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensionType }
     *
     *
     */
    public List<ExtensionType> getExtension() {
        if (extension == null) {
            extension = new ArrayList<>();
        }
        return this.extension;
    }

}
