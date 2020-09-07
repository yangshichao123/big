package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Serverstatus")
public class Serverstatus implements Serializable {
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
     * CPU温度
     */
    private String cpuTemp;

    /**
     * 内存使用率
     */
    private String memoryUsage;

    /**
     * 网口工作状态 up-正常，down-异常
     */
    private String portStatus;

    /**
     * 硬盘状态 1-正常，0-异常
     */
    private String diskStatus;

    /**
     * 分区空间使用率
     */
    private String diskPartitionUsage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table serverstatus
     *
     * @mbg.generated Wed Aug 12 10:24:48 CST 2020
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

    public String getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(String cpuTemp) {
        this.cpuTemp = cpuTemp == null ? null : cpuTemp.trim();
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage == null ? null : memoryUsage.trim();
    }

    public String getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(String portStatus) {
        this.portStatus = portStatus == null ? null : portStatus.trim();
    }

    public String getDiskStatus() {
        return diskStatus;
    }

    public void setDiskStatus(String diskStatus) {
        this.diskStatus = diskStatus == null ? null : diskStatus.trim();
    }

    public String getDiskPartitionUsage() {
        return diskPartitionUsage;
    }

    public void setDiskPartitionUsage(String diskPartitionUsage) {
        this.diskPartitionUsage = diskPartitionUsage == null ? null : diskPartitionUsage.trim();
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
        sb.append(", cpuTemp=").append(cpuTemp);
        sb.append(", memoryUsage=").append(memoryUsage);
        sb.append(", portStatus=").append(portStatus);
        sb.append(", diskStatus=").append(diskStatus);
        sb.append(", diskPartitionUsage=").append(diskPartitionUsage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}