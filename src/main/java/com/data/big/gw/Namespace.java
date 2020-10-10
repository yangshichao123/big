
package com.data.big.gw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Namespace complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Namespace"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tree.dom4j.org/xsd}AbstractNode"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="URI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XPathNameStep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nodeType" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stringValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Namespace", namespace = "http://dom4j.org/xsd", propOrder = {
    "rest"
})
public class Namespace
    extends AbstractNode
{

    @XmlElementRefs({
        @XmlElementRef(name = "stringValue", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "URI", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "nodeType", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "prefix", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "text", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "XPathNameStep", namespace = "http://dom4j.org/xsd", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<? extends Serializable>> rest;

    /**
     * ��ȡ����ģ�͵����ಿ�֡�
     * 
     * <p>
     * ��������ԭ��, ����ȡ���Ǵ� "catch-all" ����: 
     * �ֶ����� "NodeType" ��ģʽ��������ͬ����ʹ�á������: 
     * http://127.0.0.1:8000/ItcmorWebJxgl/services/GwaqscJxglService?wsdl�ĵ� 71 ��
     * http://127.0.0.1:8000/ItcmorWebJxgl/services/GwaqscJxglService?wsdl�ĵ� 86 ��
     * <p>
     * Ҫ���������, �뽫���Զ�������Ӧ����������������
     * ������һ���Ը���������: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Short }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Serializable>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<? extends Serializable>>();
        }
        return this.rest;
    }

}
