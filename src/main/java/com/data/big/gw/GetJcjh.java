
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
 *         &lt;element name="qsrq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jsrq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jhlx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "qsrq",
    "jsrq",
    "jhlx"
})
@XmlRootElement(name = "getJcjh")
public class GetJcjh {

    @XmlElementRef(name = "qsrq", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qsrq;
    @XmlElementRef(name = "jsrq", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jsrq;
    @XmlElementRef(name = "jhlx", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jhlx;

    /**
     * ��ȡqsrq���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQsrq() {
        return qsrq;
    }

    /**
     * ����qsrq���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQsrq(JAXBElement<String> value) {
        this.qsrq = value;
    }

    /**
     * ��ȡjsrq���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJsrq() {
        return jsrq;
    }

    /**
     * ����jsrq���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJsrq(JAXBElement<String> value) {
        this.jsrq = value;
    }

    /**
     * ��ȡjhlx���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJhlx() {
        return jhlx;
    }

    /**
     * ����jhlx���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJhlx(JAXBElement<String> value) {
        this.jhlx = value;
    }

}
