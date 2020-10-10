
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DocumentFactory complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DocumentFactory"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QNames" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="XPathNamespaceURIs" type="{http://util.java/xsd}Map" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentFactory", namespace = "http://dom4j.org/xsd", propOrder = {
    "qNames",
    "xPathNamespaceURIs"
})
public class DocumentFactory {

    @XmlElementRef(name = "QNames", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> qNames;
    @XmlElementRef(name = "XPathNamespaceURIs", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Map> xPathNamespaceURIs;

    /**
     * ��ȡqNames���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getQNames() {
        return qNames;
    }

    /**
     * ����qNames���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setQNames(JAXBElement<Object> value) {
        this.qNames = value;
    }

    /**
     * ��ȡxPathNamespaceURIs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Map }{@code >}
     *     
     */
    public JAXBElement<Map> getXPathNamespaceURIs() {
        return xPathNamespaceURIs;
    }

    /**
     * ����xPathNamespaceURIs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Map }{@code >}
     *     
     */
    public void setXPathNamespaceURIs(JAXBElement<Map> value) {
        this.xPathNamespaceURIs = value;
    }

}
