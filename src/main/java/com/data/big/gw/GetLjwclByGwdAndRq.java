
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
 *         &lt;element name="zzrq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "zzrq",
    "gwd"
})
@XmlRootElement(name = "getLjwclByGwdAndRq")
public class GetLjwclByGwdAndRq {

    @XmlElementRef(name = "qsrq", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qsrq;
    @XmlElementRef(name = "zzrq", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> zzrq;
    @XmlElementRef(name = "gwd", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> gwd;

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
     * ��ȡzzrq���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getZzrq() {
        return zzrq;
    }

    /**
     * ����zzrq���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setZzrq(JAXBElement<String> value) {
        this.zzrq = value;
    }

    /**
     * ��ȡgwd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGwd() {
        return gwd;
    }

    /**
     * ����gwd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGwd(JAXBElement<String> value) {
        this.gwd = value;
    }

}
