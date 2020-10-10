
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>QName complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="QName"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="documentFactory" type="{http://dom4j.org/xsd}DocumentFactory" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="namespace" type="{http://dom4j.org/xsd}Namespace" minOccurs="0"/&gt;
 *         &lt;element name="namespacePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="namespaceURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qualifiedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QName", namespace = "http://dom4j.org/xsd", propOrder = {
    "documentFactory",
    "name",
    "namespace",
    "namespacePrefix",
    "namespaceURI",
    "qualifiedName"
})
public class QName {

    @XmlElementRef(name = "documentFactory", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<DocumentFactory> documentFactory;
    @XmlElementRef(name = "name", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> name;
    @XmlElementRef(name = "namespace", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Namespace> namespace;
    @XmlElementRef(name = "namespacePrefix", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> namespacePrefix;
    @XmlElementRef(name = "namespaceURI", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> namespaceURI;
    @XmlElementRef(name = "qualifiedName", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qualifiedName;

    /**
     * ��ȡdocumentFactory���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DocumentFactory }{@code >}
     *     
     */
    public JAXBElement<DocumentFactory> getDocumentFactory() {
        return documentFactory;
    }

    /**
     * ����documentFactory���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DocumentFactory }{@code >}
     *     
     */
    public void setDocumentFactory(JAXBElement<DocumentFactory> value) {
        this.documentFactory = value;
    }

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = value;
    }

    /**
     * ��ȡnamespace���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Namespace }{@code >}
     *     
     */
    public JAXBElement<Namespace> getNamespace() {
        return namespace;
    }

    /**
     * ����namespace���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Namespace }{@code >}
     *     
     */
    public void setNamespace(JAXBElement<Namespace> value) {
        this.namespace = value;
    }

    /**
     * ��ȡnamespacePrefix���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNamespacePrefix() {
        return namespacePrefix;
    }

    /**
     * ����namespacePrefix���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNamespacePrefix(JAXBElement<String> value) {
        this.namespacePrefix = value;
    }

    /**
     * ��ȡnamespaceURI���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNamespaceURI() {
        return namespaceURI;
    }

    /**
     * ����namespaceURI���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNamespaceURI(JAXBElement<String> value) {
        this.namespaceURI = value;
    }

    /**
     * ��ȡqualifiedName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQualifiedName() {
        return qualifiedName;
    }

    /**
     * ����qualifiedName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQualifiedName(JAXBElement<String> value) {
        this.qualifiedName = value;
    }

}
