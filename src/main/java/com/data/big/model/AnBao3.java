package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "an_bao3")
public class AnBao3 implements Serializable {
    /**
     * id
     */
    @Id
    private String anBaoId;

    /**
     * 事故id
     */
    private String jtsgGkbId;

    /**
     * 事故编号
     */
    private String jtsgGkbSgbh;

    /**
     * 事故所在路局简称
     */
    private String jtsgGkbJ;

    /**
     * 事故所在线路名称
     */
    private String jtsgGkbX;

    /**
     * 线别名称
     */
    private String jtsgGkbXb;

    /**
     * 发生时间上行
     */
    private String jtsgGkbFssj;

    /**
     * 发生时间下行
     */
    private String jtsgGkbFssjSxx;

    /**
     * 开通时间上行
     */
    private String jtsgGkbKtsj;

    /**
     * 开通时间下行
     */
    private String jtsgGkbKtsjSxx;

    /**
     * 中断时间上行
     */
    private String jtsgGkbZdsj;

    /**
     * 中断时间下行
     */
    private String jtsgGkbZdsjXx;

    /**
     * 上下行
     */
    private String jtsgGkbSxx;

    /**
     * 公里数
     */
    private String jtsgGkbGl;

    /**
     * 股道数
     */
    private String jtsgGkbGlgd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table an_bao3
     *
     * @mbg.generated Mon Aug 24 15:14:33 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getAnBaoId() {
        return anBaoId;
    }

    public void setAnBaoId(String anBaoId) {
        this.anBaoId = anBaoId == null ? null : anBaoId.trim();
    }

    public String getJtsgGkbId() {
        return jtsgGkbId;
    }

    public void setJtsgGkbId(String jtsgGkbId) {
        this.jtsgGkbId = jtsgGkbId == null ? null : jtsgGkbId.trim();
    }

    public String getJtsgGkbSgbh() {
        return jtsgGkbSgbh;
    }

    public void setJtsgGkbSgbh(String jtsgGkbSgbh) {
        this.jtsgGkbSgbh = jtsgGkbSgbh == null ? null : jtsgGkbSgbh.trim();
    }

    public String getJtsgGkbJ() {
        return jtsgGkbJ;
    }

    public void setJtsgGkbJ(String jtsgGkbJ) {
        this.jtsgGkbJ = jtsgGkbJ == null ? null : jtsgGkbJ.trim();
    }

    public String getJtsgGkbX() {
        return jtsgGkbX;
    }

    public void setJtsgGkbX(String jtsgGkbX) {
        this.jtsgGkbX = jtsgGkbX == null ? null : jtsgGkbX.trim();
    }

    public String getJtsgGkbXb() {
        return jtsgGkbXb;
    }

    public void setJtsgGkbXb(String jtsgGkbXb) {
        this.jtsgGkbXb = jtsgGkbXb == null ? null : jtsgGkbXb.trim();
    }

    public String getJtsgGkbFssj() {
        return jtsgGkbFssj;
    }

    public void setJtsgGkbFssj(String jtsgGkbFssj) {
        this.jtsgGkbFssj = jtsgGkbFssj == null ? null : jtsgGkbFssj.trim();
    }

    public String getJtsgGkbFssjSxx() {
        return jtsgGkbFssjSxx;
    }

    public void setJtsgGkbFssjSxx(String jtsgGkbFssjSxx) {
        this.jtsgGkbFssjSxx = jtsgGkbFssjSxx == null ? null : jtsgGkbFssjSxx.trim();
    }

    public String getJtsgGkbKtsj() {
        return jtsgGkbKtsj;
    }

    public void setJtsgGkbKtsj(String jtsgGkbKtsj) {
        this.jtsgGkbKtsj = jtsgGkbKtsj == null ? null : jtsgGkbKtsj.trim();
    }

    public String getJtsgGkbKtsjSxx() {
        return jtsgGkbKtsjSxx;
    }

    public void setJtsgGkbKtsjSxx(String jtsgGkbKtsjSxx) {
        this.jtsgGkbKtsjSxx = jtsgGkbKtsjSxx == null ? null : jtsgGkbKtsjSxx.trim();
    }

    public String getJtsgGkbZdsj() {
        return jtsgGkbZdsj;
    }

    public void setJtsgGkbZdsj(String jtsgGkbZdsj) {
        this.jtsgGkbZdsj = jtsgGkbZdsj == null ? null : jtsgGkbZdsj.trim();
    }

    public String getJtsgGkbZdsjXx() {
        return jtsgGkbZdsjXx;
    }

    public void setJtsgGkbZdsjXx(String jtsgGkbZdsjXx) {
        this.jtsgGkbZdsjXx = jtsgGkbZdsjXx == null ? null : jtsgGkbZdsjXx.trim();
    }

    public String getJtsgGkbSxx() {
        return jtsgGkbSxx;
    }

    public void setJtsgGkbSxx(String jtsgGkbSxx) {
        this.jtsgGkbSxx = jtsgGkbSxx == null ? null : jtsgGkbSxx.trim();
    }

    public String getJtsgGkbGl() {
        return jtsgGkbGl;
    }

    public void setJtsgGkbGl(String jtsgGkbGl) {
        this.jtsgGkbGl = jtsgGkbGl == null ? null : jtsgGkbGl.trim();
    }

    public String getJtsgGkbGlgd() {
        return jtsgGkbGlgd;
    }

    public void setJtsgGkbGlgd(String jtsgGkbGlgd) {
        this.jtsgGkbGlgd = jtsgGkbGlgd == null ? null : jtsgGkbGlgd.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", anBaoId=").append(anBaoId);
        sb.append(", jtsgGkbId=").append(jtsgGkbId);
        sb.append(", jtsgGkbSgbh=").append(jtsgGkbSgbh);
        sb.append(", jtsgGkbJ=").append(jtsgGkbJ);
        sb.append(", jtsgGkbX=").append(jtsgGkbX);
        sb.append(", jtsgGkbXb=").append(jtsgGkbXb);
        sb.append(", jtsgGkbFssj=").append(jtsgGkbFssj);
        sb.append(", jtsgGkbFssjSxx=").append(jtsgGkbFssjSxx);
        sb.append(", jtsgGkbKtsj=").append(jtsgGkbKtsj);
        sb.append(", jtsgGkbKtsjSxx=").append(jtsgGkbKtsjSxx);
        sb.append(", jtsgGkbZdsj=").append(jtsgGkbZdsj);
        sb.append(", jtsgGkbZdsjXx=").append(jtsgGkbZdsjXx);
        sb.append(", jtsgGkbSxx=").append(jtsgGkbSxx);
        sb.append(", jtsgGkbGl=").append(jtsgGkbGl);
        sb.append(", jtsgGkbGlgd=").append(jtsgGkbGlgd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}