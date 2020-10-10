
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AbstractNode complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="AbstractNode"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="document" type="{http://dom4j.org/xsd}Document" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nodeType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="nodeTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="parent" type="{http://dom4j.org/xsd}Element" minOccurs="0"/&gt;
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="readOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="stringValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="uniquePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractNode", namespace = "http://tree.dom4j.org/xsd", propOrder = {
    "document",
    "name",
    "nodeType",
    "nodeTypeName",
    "parent",
    "path",
    "readOnly",
    "stringValue",
    "text",
    "uniquePath"
})
@XmlSeeAlso({
    Namespace.class
})
public class AbstractNode {

    @XmlElementRef(name = "document", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Document> document;
    @XmlElementRef(name = "name", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> name;
    protected Short nodeType;
    @XmlElementRef(name = "nodeTypeName", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nodeTypeName;
    @XmlElementRef(name = "parent", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Element> parent;
    @XmlElementRef(name = "path", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> path;
    protected Boolean readOnly;
    @XmlElementRef(name = "stringValue", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stringValue;
    @XmlElementRef(name = "text", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> text;
    @XmlElementRef(name = "uniquePath", namespace = "http://tree.dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> uniquePath;

    /**
     * ��ȡdocument���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Document }{@code >}
     *     
     */
    public JAXBElement<Document> getDocument() {
        return document;
    }

    /**
     * ����document���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Document }{@code >}
     *     
     */
    public void setDocument(JAXBElement<Document> value) {
        this.document = value;
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
     * ��ȡnodeType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getNodeType() {
        return nodeType;
    }

    /**
     * ����nodeType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setNodeType(Short value) {
        this.nodeType = value;
    }

    /**
     * ��ȡnodeTypeName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNodeTypeName() {
        return nodeTypeName;
    }

    /**
     * ����nodeTypeName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNodeTypeName(JAXBElement<String> value) {
        this.nodeTypeName = value;
    }

    /**
     * ��ȡparent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Element }{@code >}
     *     
     */
    public JAXBElement<Element> getParent() {
        return parent;
    }

    /**
     * ����parent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Element }{@code >}
     *     
     */
    public void setParent(JAXBElement<Element> value) {
        this.parent = value;
    }

    /**
     * ��ȡpath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPath() {
        return path;
    }

    /**
     * ����path���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPath(JAXBElement<String> value) {
        this.path = value;
    }

    /**
     * ��ȡreadOnly���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReadOnly() {
        return readOnly;
    }

    /**
     * ����readOnly���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReadOnly(Boolean value) {
        this.readOnly = value;
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
     * ��ȡuniquePath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUniquePath() {
        return uniquePath;
    }

    /**
     * ����uniquePath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUniquePath(JAXBElement<String> value) {
        this.uniquePath = value;
    }

}
