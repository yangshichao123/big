package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.Name;
import java.io.Serializable;
@Table(name = "video_kilometer")
public class VideoKilometer implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 摄像机编号
     */
    private String videoCode;

    /**
     * 安装类型 1上行 2下行 3室内 4室外
     */
    private String installtype;

    /**
     * 起始公里标
     */
    private String startkilometer;

    /**
     * 结束公里标
     */
    private String endkilometer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table video_kilometer
     *
     * @mbg.generated Thu Sep 10 09:07:19 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode == null ? null : videoCode.trim();
    }

    public String getInstalltype() {
        return installtype;
    }

    public void setInstalltype(String installtype) {
        this.installtype = installtype == null ? null : installtype.trim();
    }

    public String getStartkilometer() {
        return startkilometer;
    }

    public void setStartkilometer(String startkilometer) {
        this.startkilometer = startkilometer == null ? null : startkilometer.trim();
    }

    public String getEndkilometer() {
        return endkilometer;
    }

    public void setEndkilometer(String endkilometer) {
        this.endkilometer = endkilometer == null ? null : endkilometer.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoCode=").append(videoCode);
        sb.append(", installtype=").append(installtype);
        sb.append(", startkilometer=").append(startkilometer);
        sb.append(", endkilometer=").append(endkilometer);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}