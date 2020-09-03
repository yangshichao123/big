package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Switchalarm")
public class Switchalarm implements Serializable {
    /**
     * 主键guid
     */
    @Id
    private String id;

    /**
     * 告警资源标识
     */
    private String alarmId;

    /**
     * 设备标识 铁标16位设备编码
     */
    private String deviceId;

    /**
     * 告警时间
     */
    private String alarmTime;

    /**
     * 告警级别 告警级别1~3：1重要告警，2次要告警，3一般告警
     */
    private String alarmLevel;

    /**
     * 告警类型 1网络中断,2 CPU使用率过高,3风扇异常,4磁盘剩余空间不足,
     */
    private String alarmType;

    /**
     * 告警状态 0正常，1告警
     */
    private String alarmStatus;

    /**
     * 告警描述
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table switchalarm
     *
     * @mbg.generated Wed Aug 12 10:25:00 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId == null ? null : alarmId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime == null ? null : alarmTime.trim();
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel == null ? null : alarmLevel.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus == null ? null : alarmStatus.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alarmId=").append(alarmId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", alarmTime=").append(alarmTime);
        sb.append(", alarmLevel=").append(alarmLevel);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", alarmStatus=").append(alarmStatus);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}