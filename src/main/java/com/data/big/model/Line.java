
package com.data.big.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


public class Line implements java.io.Serializable {

    private static final Long serialVersionUID = -641442809596535488L;

    private String XLDM;

    private String XLDM_OLD;

    private String XLM;

    private String XLBS;

    private BigDecimal XLCD;

    private String PYM;

    private Short JMXSSX;

    private String KZBS;

    private String PWMIS;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getXLDM() {
        return XLDM;
    }

    public void setXLDM(String XLDM) {
        this.XLDM = XLDM;
    }

    public String getXLDM_OLD() {
        return XLDM_OLD;
    }

    public void setXLDM_OLD(String XLDM_OLD) {
        this.XLDM_OLD = XLDM_OLD;
    }

    public String getXLM() {
        return XLM;
    }

    public void setXLM(String XLM) {
        this.XLM = XLM;
    }

    public String getXLBS() {
        return XLBS;
    }

    public void setXLBS(String XLBS) {
        this.XLBS = XLBS;
    }

    public BigDecimal getXLCD() {
        return XLCD;
    }

    public void setXLCD(BigDecimal XLCD) {
        this.XLCD = XLCD;
    }

    public String getPYM() {
        return PYM;
    }

    public void setPYM(String PYM) {
        this.PYM = PYM;
    }

    public Short getJMXSSX() {
        return JMXSSX;
    }

    public void setJMXSSX(Short JMXSSX) {
        this.JMXSSX = JMXSSX;
    }

    public String getKZBS() {
        return KZBS;
    }

    public void setKZBS(String KZBS) {
        this.KZBS = KZBS;
    }

    public String getPWMIS() {
        return PWMIS;
    }

    public void setPWMIS(String PWMIS) {
        this.PWMIS = PWMIS;
    }

    @Override
    public String toString() {
        return "Line{" +
                "XLDM='" + XLDM + '\'' +
                ", XLDM_OLD='" + XLDM_OLD + '\'' +
                ", XLM='" + XLM + '\'' +
                ", XLBS='" + XLBS + '\'' +
                ", XLCD=" + XLCD +
                ", PYM='" + PYM + '\'' +
                ", JMXSSX=" + JMXSSX +
                ", KZBS='" + KZBS + '\'' +
                ", PWMIS='" + PWMIS + '\'' +
                '}';
    }
}
