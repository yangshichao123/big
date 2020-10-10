
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
 *         &lt;element name="lineCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gjqs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gjzz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "lineCode",
    "xb",
    "gjqs",
    "gjzz"
})
@XmlRootElement(name = "getQLdata")
public class GetQLdata {

    @XmlElementRef(name = "lineCode", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> lineCode;
    @XmlElementRef(name = "xb", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xb;
    @XmlElementRef(name = "gjqs", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> gjqs;
    @XmlElementRef(name = "gjzz", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> gjzz;

    /**
     * ��ȡlineCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLineCode() {
        return lineCode;
    }

    /**
     * ����lineCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLineCode(JAXBElement<String> value) {
        this.lineCode = value;
    }

    /**
     * ��ȡxb���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXb() {
        return xb;
    }

    /**
     * ����xb���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXb(JAXBElement<String> value) {
        this.xb = value;
    }

    /**
     * ��ȡgjqs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGjqs() {
        return gjqs;
    }

    /**
     * ����gjqs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGjqs(JAXBElement<String> value) {
        this.gjqs = value;
    }

    /**
     * ��ȡgjzz���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGjzz() {
        return gjzz;
    }

    /**
     * ����gjzz���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGjzz(JAXBElement<String> value) {
        this.gjzz = value;
    }

}
