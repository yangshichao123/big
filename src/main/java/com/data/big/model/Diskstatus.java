package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Diskstatus")
public class Diskstatus implements Serializable {
    /**
     * 主键guid
     */
    @Id
    private String id;

    /**
     * 设备标识
     */
    private String deviceId;

    /**
     * 时间
     */
    private String statusTime;

    /**
     * 在线状态 1-在线，0-离线
     */
    private String onlineStatus;

    /**
     * CPU使用率
     */
    private String cpuUsage;

    /**
     * 风扇状态 1-正常，0-异常
     */
    private String fanDisk;

    /**
     * 磁盘状态 1-正常，0-异常
     */
    private String diskStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table diskstatus
     *
     * @mbg.generated Wed Aug 12 10:24:04 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime == null ? null : statusTime.trim();
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus == null ? null : onlineStatus.trim();
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage == null ? null : cpuUsage.trim();
    }

    public String getFanDisk() {
        return fanDisk;
    }

    public void setFanDisk(String fanDisk) {
        this.fanDisk = fanDisk == null ? null : fanDisk.trim();
    }

    public String getDiskStatus() {
        return diskStatus;
    }

    public void setDiskStatus(String diskStatus) {
        this.diskStatus = diskStatus == null ? null : diskStatus.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", statusTime=").append(statusTime);
        sb.append(", onlineStatus=").append(onlineStatus);
        sb.append(", cpuUsage=").append(cpuUsage);
        sb.append(", fanDisk=").append(fanDisk);
        sb.append(", diskStatus=").append(diskStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}