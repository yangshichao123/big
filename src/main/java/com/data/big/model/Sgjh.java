package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Table(name = "sgjh")
public class Sgjh implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 日计划号
     */
    private String rjhh;

    /**
     * 计划状态 "-1 驳回", "0车间上报", "2段审批", "3计划下达", "4车间派单", "6计划销号"	
     */
    private String sbztw;

    /**
     * 点内外(0点内 1点外 2超临修)
     */
    private String zylx;

    /**
     * 作业日期
     */
    private String zyrq;

    /**
     * 计划开始时间
     */
    private String jhqssj;

    /**
     * 计划终止时间
     */
    private String jhzzsj;

    /**
     * 施工项目
     */
    private String zyxm;

    /**
     * 施工等级
     */
    private String zydj;

    /**
     * 线名
     */
    private String xm;

    /**
     * 行别
     */
    private String xb;

    /**
     * 作业位置：区间/站内
     */
    private String zywz;

    /**
     * 起始车站
     */
    private String qschzm;

    /**
     * 终止车站
     */
    private String zzchzm;

    /**
     * 作业起始里程
     */
    private String zyqslc;

    /**
     * 作业终止里程
     */
    private String zyzzlc;

    /**
     * 主体单位
     */
    private String sgztdw;

    /**
     * 配合单位
     */
    private String phdw;

    /**
     * 施工负责人
     */
    private String fzrname;

    /**
     * 施工内容及影响范围
     */
    private String sgnrjyxfw;

    /**
     * 限速及行车方式
     */
    private String xsjxcfs;

    /**
     * 路用车信息
     */
    private String lycxx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sgjh
     *
     * @mbg.generated Thu Sep 24 16:08:04 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRjhh() {
        return rjhh;
    }

    public void setRjhh(String rjhh) {
        this.rjhh = rjhh == null ? null : rjhh.trim();
    }

    public String getSbztw() {
        return sbztw;
    }

    public void setSbztw(String sbztw) {
        this.sbztw = sbztw == null ? null : sbztw.trim();
    }

    public String getZylx() {
        return zylx;
    }

    public void setZylx(String zylx) {
        this.zylx = zylx == null ? null : zylx.trim();
    }

    public String getZyrq() {
        return zyrq;
    }

    public void setZyrq(String zyrq) {
        this.zyrq = zyrq == null ? null : zyrq.trim();
    }

    public String getJhqssj() {
        return jhqssj;
    }

    public void setJhqssj(String jhqssj) {
        this.jhqssj = jhqssj == null ? null : jhqssj.trim();
    }

    public String getJhzzsj() {
        return jhzzsj;
    }

    public void setJhzzsj(String jhzzsj) {
        this.jhzzsj = jhzzsj == null ? null : jhzzsj.trim();
    }

    public String getZyxm() {
        return zyxm;
    }

    public void setZyxm(String zyxm) {
        this.zyxm = zyxm == null ? null : zyxm.trim();
    }

    public String getZydj() {
        return zydj;
    }

    public void setZydj(String zydj) {
        this.zydj = zydj == null ? null : zydj.trim();
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb == null ? null : xb.trim();
    }

    public String getZywz() {
        return zywz;
    }

    public void setZywz(String zywz) {
        this.zywz = zywz == null ? null : zywz.trim();
    }

    public String getQschzm() {
        return qschzm;
    }

    public void setQschzm(String qschzm) {
        this.qschzm = qschzm == null ? null : qschzm.trim();
    }

    public String getZzchzm() {
        return zzchzm;
    }

    public void setZzchzm(String zzchzm) {
        this.zzchzm = zzchzm == null ? null : zzchzm.trim();
    }

    public String getZyqslc() {
        return zyqslc;
    }

    public void setZyqslc(String zyqslc) {
        this.zyqslc = zyqslc == null ? null : zyqslc.trim();
    }

    public String getZyzzlc() {
        return zyzzlc;
    }

    public void setZyzzlc(String zyzzlc) {
        this.zyzzlc = zyzzlc == null ? null : zyzzlc.trim();
    }

    public String getSgztdw() {
        return sgztdw;
    }

    public void setSgztdw(String sgztdw) {
        this.sgztdw = sgztdw == null ? null : sgztdw.trim();
    }

    public String getPhdw() {
        return phdw;
    }

    public void setPhdw(String phdw) {
        this.phdw = phdw == null ? null : phdw.trim();
    }

    public String getFzrname() {
        return fzrname;
    }

    public void setFzrname(String fzrname) {
        this.fzrname = fzrname == null ? null : fzrname.trim();
    }

    public String getSgnrjyxfw() {
        return sgnrjyxfw;
    }

    public void setSgnrjyxfw(String sgnrjyxfw) {
        this.sgnrjyxfw = sgnrjyxfw == null ? null : sgnrjyxfw.trim();
    }

    public String getXsjxcfs() {
        return xsjxcfs;
    }

    public void setXsjxcfs(String xsjxcfs) {
        this.xsjxcfs = xsjxcfs == null ? null : xsjxcfs.trim();
    }

    public String getLycxx() {
        return lycxx;
    }

    public void setLycxx(String lycxx) {
        this.lycxx = lycxx == null ? null : lycxx.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", rjhh=").append(rjhh);
        sb.append(", sbztw=").append(sbztw);
        sb.append(", zylx=").append(zylx);
        sb.append(", zyrq=").append(zyrq);
        sb.append(", jhqssj=").append(jhqssj);
        sb.append(", jhzzsj=").append(jhzzsj);
        sb.append(", zyxm=").append(zyxm);
        sb.append(", zydj=").append(zydj);
        sb.append(", xm=").append(xm);
        sb.append(", xb=").append(xb);
        sb.append(", zywz=").append(zywz);
        sb.append(", qschzm=").append(qschzm);
        sb.append(", zzchzm=").append(zzchzm);
        sb.append(", zyqslc=").append(zyqslc);
        sb.append(", zyzzlc=").append(zyzzlc);
        sb.append(", sgztdw=").append(sgztdw);
        sb.append(", phdw=").append(phdw);
        sb.append(", fzrname=").append(fzrname);
        sb.append(", sgnrjyxfw=").append(sgnrjyxfw);
        sb.append(", xsjxcfs=").append(xsjxcfs);
        sb.append(", lycxx=").append(lycxx);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}