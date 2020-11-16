
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
 *         &lt;element name="cxdj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="xm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rows" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "cxdj",
    "xm",
    "page",
    "rows"
})
@XmlRootElement(name = "getHcsj")
public class GetHcsj {

    @XmlElementRef(name = "qsrq", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qsrq;
    @XmlElementRef(name = "jsrq", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jsrq;
    @XmlElementRef(name = "cxdj", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cxdj;
    @XmlElementRef(name = "xm", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xm;
    @XmlElementRef(name = "page", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> page;
    @XmlElementRef(name = "rows", namespace = "http://services.itcmor.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rows;

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
     * ��ȡcxdj���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCxdj() {
        return cxdj;
    }

    /**
     * ����cxdj���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCxdj(JAXBElement<String> value) {
        this.cxdj = value;
    }

    /**
     * ��ȡxm���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXm() {
        return xm;
    }

    /**
     * ����xm���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXm(JAXBElement<String> value) {
        this.xm = value;
    }

    /**
     * ��ȡpage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPage() {
        return page;
    }

    /**
     * ����page���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPage(JAXBElement<String> value) {
        this.page = value;
    }

    /**
     * ��ȡrows���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRows() {
        return rows;
    }

    /**
     * ����rows���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRows(JAXBElement<String> value) {
        this.rows = value;
    }

}
