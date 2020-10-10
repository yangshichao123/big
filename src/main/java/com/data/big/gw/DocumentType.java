
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DocumentType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DocumentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="elementName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="externalDeclarations" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="internalDeclarations" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="publicID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="systemID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentType", namespace = "http://dom4j.org/xsd", propOrder = {
    "elementName",
    "externalDeclarations",
    "internalDeclarations",
    "publicID",
    "systemID"
})
public class DocumentType {

    @XmlElementRef(name = "elementName", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> elementName;
    @XmlElementRef(name = "externalDeclarations", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> externalDeclarations;
    @XmlElementRef(name = "internalDeclarations", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> internalDeclarations;
    @XmlElementRef(name = "publicID", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> publicID;
    @XmlElementRef(name = "systemID", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> systemID;

    /**
     * ��ȡelementName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getElementName() {
        return elementName;
    }

    /**
     * ����elementName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setElementName(JAXBElement<String> value) {
        this.elementName = value;
    }

    /**
     * ��ȡexternalDeclarations���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getExternalDeclarations() {
        return externalDeclarations;
    }

    /**
     * ����externalDeclarations���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setExternalDeclarations(JAXBElement<Object> value) {
        this.externalDeclarations = value;
    }

    /**
     * ��ȡinternalDeclarations���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getInternalDeclarations() {
        return internalDeclarations;
    }

    /**
     * ����internalDeclarations���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setInternalDeclarations(JAXBElement<Object> value) {
        this.internalDeclarations = value;
    }

    /**
     * ��ȡpublicID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPublicID() {
        return publicID;
    }

    /**
     * ����publicID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPublicID(JAXBElement<String> value) {
        this.publicID = value;
    }

    /**
     * ��ȡsystemID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSystemID() {
        return systemID;
    }

    /**
     * ����systemID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSystemID(JAXBElement<String> value) {
        this.systemID = value;
    }

}
