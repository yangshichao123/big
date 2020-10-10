
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Document complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Document"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="XMLEncoding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docType" type="{http://dom4j.org/xsd}DocumentType" minOccurs="0"/&gt;
 *         &lt;element name="entityResolver" type="{http://sax.xml.org/xsd}EntityResolver" minOccurs="0"/&gt;
 *         &lt;element name="rootElement" type="{http://dom4j.org/xsd}Element" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", namespace = "http://dom4j.org/xsd", propOrder = {
    "xmlEncoding",
    "docType",
    "entityResolver",
    "rootElement"
})
public class Document {

    @XmlElementRef(name = "XMLEncoding", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xmlEncoding;
    @XmlElementRef(name = "docType", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<DocumentType> docType;
    @XmlElementRef(name = "entityResolver", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<EntityResolver> entityResolver;
    @XmlElementRef(name = "rootElement", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Element> rootElement;

    /**
     * ��ȡxmlEncoding���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXMLEncoding() {
        return xmlEncoding;
    }

    /**
     * ����xmlEncoding���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXMLEncoding(JAXBElement<String> value) {
        this.xmlEncoding = value;
    }

    /**
     * ��ȡdocType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DocumentType }{@code >}
     *     
     */
    public JAXBElement<DocumentType> getDocType() {
        return docType;
    }

    /**
     * ����docType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DocumentType }{@code >}
     *     
     */
    public void setDocType(JAXBElement<DocumentType> value) {
        this.docType = value;
    }

    /**
     * ��ȡentityResolver���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EntityResolver }{@code >}
     *     
     */
    public JAXBElement<EntityResolver> getEntityResolver() {
        return entityResolver;
    }

    /**
     * ����entityResolver���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EntityResolver }{@code >}
     *     
     */
    public void setEntityResolver(JAXBElement<EntityResolver> value) {
        this.entityResolver = value;
    }

    /**
     * ��ȡrootElement���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Element }{@code >}
     *     
     */
    public JAXBElement<Element> getRootElement() {
        return rootElement;
    }

    /**
     * ����rootElement���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Element }{@code >}
     *     
     */
    public void setRootElement(JAXBElement<Element> value) {
        this.rootElement = value;
    }

}
