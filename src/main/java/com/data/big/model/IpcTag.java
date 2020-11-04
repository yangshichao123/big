package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "ipc_tag")
public class IpcTag implements Serializable {
    /**
     * 
     */
    @Id
    private String id;

    /**
     * 摄像头ID
     */
    private String ipcid;

    /**
     * 标签记录摄像机的特征信息
     */
    private String tag;

    /**
     * 
     */
    private Date rktime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ipc_tag
     *
     * @mbg.generated Wed Nov 04 09:22:47 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIpcid() {
        return ipcid;
    }

    public void setIpcid(String ipcid) {
        this.ipcid = ipcid == null ? null : ipcid.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Date getRktime() {
        return rktime;
    }

    public void setRktime(Date rktime) {
        this.rktime = rktime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ipcid=").append(ipcid);
        sb.append(", tag=").append(tag);
        sb.append(", rktime=").append(rktime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}