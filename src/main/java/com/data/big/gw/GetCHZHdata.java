
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
 *         &lt;element name="dwbh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lineCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="qslc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zzlc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="chzhdj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zyb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="czbh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "dwbh",
    "lineCode",
    "qslc",
    "zzlc",
    "chzhdj",
    "zyb",
    "czbh",
    "xb"
})
@XmlRootElement(name = "getCHZHdata")
public class GetCHZHdata {

    @XmlElementRef(name = "dwbh", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> dwbh;
    @XmlElementRef(name = "lineCode", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> lineCode;
    @XmlElementRef(name = "qslc", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qslc;
    @XmlElementRef(name = "zzlc", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> zzlc;
    @XmlElementRef(name = "chzhdj", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> chzhdj;
    @XmlElementRef(name = "zyb", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> zyb;
    @XmlElementRef(name = "czbh", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> czbh;
    @XmlElementRef(name = "xb", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xb;

    /**
     * ��ȡdwbh���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDwbh() {
        return dwbh;
    }

    /**
     * ����dwbh���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDwbh(JAXBElement<String> value) {
        this.dwbh = value;
    }

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
     * ��ȡqslc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQslc() {
        return qslc;
    }

    /**
     * ����qslc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQslc(JAXBElement<String> value) {
        this.qslc = value;
    }

    /**
     * ��ȡzzlc���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getZzlc() {
        return zzlc;
    }

    /**
     * ����zzlc���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setZzlc(JAXBElement<String> value) {
        this.zzlc = value;
    }

    /**
     * ��ȡchzhdj���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChzhdj() {
        return chzhdj;
    }

    /**
     * ����chzhdj���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChzhdj(JAXBElement<String> value) {
        this.chzhdj = value;
    }

    /**
     * ��ȡzyb���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getZyb() {
        return zyb;
    }

    /**
     * ����zyb���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setZyb(JAXBElement<String> value) {
        this.zyb = value;
    }

    /**
     * ��ȡczbh���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCzbh() {
        return czbh;
    }

    /**
     * ����czbh���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCzbh(JAXBElement<String> value) {
        this.czbh = value;
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

}
