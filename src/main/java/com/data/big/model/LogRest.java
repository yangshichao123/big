package com.data.big.model;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "log_rest")
public class LogRest implements Serializable {
    /**
     * 录入时间
     */
    private Date lrsj;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求传入参数
     */
    private String paramin;

    /**
     * 类型 请求0返回1
     */
    private String type;

    /**
     * 接口名称
     */
    private String funname;

    /**
     * 请求返回数据
     */
    private String redata;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table log_rest
     *
     * @mbg.generated Wed Aug 12 10:59:18 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getParamin() {
        return paramin;
    }

    public void setParamin(String paramin) {
        this.paramin = paramin == null ? null : paramin.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFunname() {
        return funname;
    }

    public void setFunname(String funname) {
        this.funname = funname == null ? null : funname.trim();
    }

    public String getRedata() {
        return redata;
    }

    public void setRedata(String redata) {
        this.redata = redata == null ? null : redata.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lrsj=").append(lrsj);
        sb.append(", ip=").append(ip);
        sb.append(", paramin=").append(paramin);
        sb.append(", type=").append(type);
        sb.append(", funname=").append(funname);
        sb.append(", redata=").append(redata);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}