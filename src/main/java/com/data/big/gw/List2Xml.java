
package com.data.big.gw;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="list" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="element" type="{http://dom4j.org/xsd}Element" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "list",
    "element"
})
@XmlRootElement(name = "list2xml")
public class List2Xml {

    @XmlElementRef(name = "list", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> list;
    @XmlElementRef(name = "element", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Element> element;

    /**
     * ��ȡlist���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getList() {
        return list;
    }

    /**
     * ����list���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setList(JAXBElement<Object> value) {
        this.list = value;
    }

    /**
     * ��ȡelement���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Element }{@code >}
     *     
     */
    public JAXBElement<Element> getElement() {
        return element;
    }

    /**
     * ����element���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Element }{@code >}
     *     
     */
    public void setElement(JAXBElement<Element> value) {
        this.element = value;
    }

}
