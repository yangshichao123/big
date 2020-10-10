
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Element complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Element"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QName" type="{http://dom4j.org/xsd}QName" minOccurs="0"/&gt;
 *         &lt;element name="attributes" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="namespace" type="{http://dom4j.org/xsd}Namespace" minOccurs="0"/&gt;
 *         &lt;element name="namespacePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="namespaceURI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qualifiedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rootElement" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="stringValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="textOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="textTrim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Element", namespace = "http://dom4j.org/xsd", propOrder = {
    "qName",
    "attributes",
    "data",
    "namespace",
    "namespacePrefix",
    "namespaceURI",
    "qualifiedName",
    "rootElement",
    "stringValue",
    "text",
    "textOnly",
    "textTrim"
})
public class Element {

    @XmlElementRef(name = "QName", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<QName> qName;
    @XmlElementRef(name = "attributes", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> attributes;
    @XmlElementRef(name = "data", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> data;
    @XmlElementRef(name = "namespace", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Namespace> namespace;
    @XmlElementRef(name = "namespacePrefix", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> namespacePrefix;
    @XmlElementRef(name = "namespaceURI", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> namespaceURI;
    @XmlElementRef(name = "qualifiedName", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qualifiedName;
    protected Boolean rootElement;
    @XmlElementRef(name = "stringValue", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stringValue;
    @XmlElementRef(name = "text", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> text;
    protected Boolean textOnly;
    @XmlElementRef(name = "textTrim", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> textTrim;

    /**
     * ��ȡqName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link QName }{@code >}
     *     
     */
    public JAXBElement<QName> getQName() {
        return qName;
    }

    /**
     * ����qName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link QName }{@code >}
     *     
     */
    public void setQName(JAXBElement<QName> value) {
        this.qName = value;
    }

    /**
     * ��ȡattributes���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getAttributes() {
        return attributes;
    }

    /**
     * ����attributes���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setAttributes(JAXBElement<Object> value) {
        this.attributes = value;
    }

    /**
     * ��ȡdata���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getData() {
        return data;
    }

    /**
     * ����data���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setData(JAXBElement<Object> value) {
        this.data = value;
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

    /**
     * ��ȡrootElement���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRootElement() {
        return rootElement;
    }

    /**
     * ����rootElement���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRootElement(Boolean value) {
        this.rootElement = value;
    }

    /**
     * ��ȡstringValue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStringValue() {
        return stringValue;
    }

    /**
     * ����stringValue���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStringValue(JAXBElement<String> value) {
        this.stringValue = value;
    }

    /**
     * ��ȡtext���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText() {
        return text;
    }

    /**
     * ����text���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText(JAXBElement<String> value) {
        this.text = value;
    }

    /**
     * ��ȡtextOnly���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTextOnly() {
        return textOnly;
    }

    /**
     * ����textOnly���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTextOnly(Boolean value) {
        this.textOnly = value;
    }

    /**
     * ��ȡtextTrim���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTextTrim() {
        return textTrim;
    }

    /**
     * ����textTrim���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTextTrim(JAXBElement<String> value) {
        this.textTrim = value;
    }

}
