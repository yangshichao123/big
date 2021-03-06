package com.data.big.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "rain_alarm")
public class RainAlarm implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 监控单元编码 监控单元公里标，单位m
     */
    private String controlUnitCode;

    /**
     * 监测点编码 监测点公里标，单位m
     */
    private String checkLocaleCode;

    /**
     * 报警等级  0x01：一级报警；0x02：二级报警；0x03：三级报警；0x04：四级报警
     */
    private String alarmLevel;

    /**
     * 累计雨量 单位0.1mm 
     */
    private String totalRain;

    /**
     * 限速值 单位km/h
     */
    private String speedLimit;

    /**
     * 限速区段个数 无符号整型
     */
    private String speedLimitSectionNumber;

    /**
     * 支线编码   默认1代表主线，2…n代表支线
     */
    private String branchLineCode;

    /**
     * 限速区段开始   公里标，单位m
     */
    private String speedLimitSectionBegin;

    /**
     * 限速区段结束   公里标，单位m
     */
    private String speedLimitSectionEnd;

    /**
     * 数据长度 影响区间字节长度
     */
    private String dataLength;

    /**
     * 影响区间  UTF-8编码格式，车站名称~车站名称
     */
    private String influenceInterval;

    /**
     * 分钟降雨量 单位0.1mm 
     */
    private String minuteRain;

    /**
     * 10分钟降雨量 单位0.1mm
     */
    @Column(name = "minute_10_rain")
    private String minute10Rain;

    /**
     * 小时降雨量 单位0.1mm
     */
    private String hourRain;

    /**
     * 24小时降雨量 单位0.1mm
     */
    @Column(name = "hour_24_rain")
    private String hour24Rain;

    /**
     * 连续降雨量 单位0.1mm
     */
    private String continuityRain;

    /**
     * 预留1 精度转换倍率（0.1m/s的倍率），举例：精度为0.1m/s，赋值1，精度为0.01m/s，赋值10
     */
    private String reserve1;

    /**
     *  预留2 无符号整型，0-999（毫秒）
     */
    private String reserve2;

    /**
     * 创建时间
     */
    private Date date;

    /**
     * 报警状态
     */
    private String alarmStatus;

    /**
     * 报警解除时间
     */
    private Date relieveTime;

    /**
     * 视频上传状态
     */
    private String videoUpStatus;

    /**
     * 检测点公里标   k444x888 形式
     */
    private String checkLocaleKilometre;

    /**
     * 原始数据
     */
    private String data;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table rain_alarm
     *
     * @mbg.generated Thu Sep 03 09:08:43 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getControlUnitCode() {
        return controlUnitCode;
    }

    public void setControlUnitCode(String controlUnitCode) {
        this.controlUnitCode = controlUnitCode == null ? null : controlUnitCode.trim();
    }

    public String getCheckLocaleCode() {
        return checkLocaleCode;
    }

    public void setCheckLocaleCode(String checkLocaleCode) {
        this.checkLocaleCode = checkLocaleCode == null ? null : checkLocaleCode.trim();
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel == null ? null : alarmLevel.trim();
    }

    public String getTotalRain() {
        return totalRain;
    }

    public void setTotalRain(String totalRain) {
        this.totalRain = totalRain == null ? null : totalRain.trim();
    }

    public String getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(String speedLimit) {
        this.speedLimit = speedLimit == null ? null : speedLimit.trim();
    }

    public String getSpeedLimitSectionNumber() {
        return speedLimitSectionNumber;
    }

    public void setSpeedLimitSectionNumber(String speedLimitSectionNumber) {
        this.speedLimitSectionNumber = speedLimitSectionNumber == null ? null : speedLimitSectionNumber.trim();
    }

    public String getBranchLineCode() {
        return branchLineCode;
    }

    public void setBranchLineCode(String branchLineCode) {
        this.branchLineCode = branchLineCode == null ? null : branchLineCode.trim();
    }

    public String getSpeedLimitSectionBegin() {
        return speedLimitSectionBegin;
    }

    public void setSpeedLimitSectionBegin(String speedLimitSectionBegin) {
        this.speedLimitSectionBegin = speedLimitSectionBegin == null ? null : speedLimitSectionBegin.trim();
    }

    public String getSpeedLimitSectionEnd() {
        return speedLimitSectionEnd;
    }

    public void setSpeedLimitSectionEnd(String speedLimitSectionEnd) {
        this.speedLimitSectionEnd = speedLimitSectionEnd == null ? null : speedLimitSectionEnd.trim();
    }

    public String getDataLength() {
        return dataLength;
    }

    public void setDataLength(String dataLength) {
        this.dataLength = dataLength == null ? null : dataLength.trim();
    }

    public String getInfluenceInterval() {
        return influenceInterval;
    }

    public void setInfluenceInterval(String influenceInterval) {
        this.influenceInterval = influenceInterval == null ? null : influenceInterval.trim();
    }

    public String getMinuteRain() {
        return minuteRain;
    }

    public void setMinuteRain(String minuteRain) {
        this.minuteRain = minuteRain == null ? null : minuteRain.trim();
    }

    public String getMinute10Rain() {
        return minute10Rain;
    }

    public void setMinute10Rain(String minute10Rain) {
        this.minute10Rain = minute10Rain == null ? null : minute10Rain.trim();
    }

    public String getHourRain() {
        return hourRain;
    }

    public void setHourRain(String hourRain) {
        this.hourRain = hourRain == null ? null : hourRain.trim();
    }

    public String getHour24Rain() {
        return hour24Rain;
    }

    public void setHour24Rain(String hour24Rain) {
        this.hour24Rain = hour24Rain == null ? null : hour24Rain.trim();
    }

    public String getContinuityRain() {
        return continuityRain;
    }

    public void setContinuityRain(String continuityRain) {
        this.continuityRain = continuityRain == null ? null : continuityRain.trim();
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus == null ? null : alarmStatus.trim();
    }

    public Date getRelieveTime() {
        return relieveTime;
    }

    public void setRelieveTime(Date relieveTime) {
        this.relieveTime = relieveTime;
    }

    public String getVideoUpStatus() {
        return videoUpStatus;
    }

    public void setVideoUpStatus(String videoUpStatus) {
        this.videoUpStatus = videoUpStatus == null ? null : videoUpStatus.trim();
    }

    public String getCheckLocaleKilometre() {
        return checkLocaleKilometre;
    }

    public void setCheckLocaleKilometre(String checkLocaleKilometre) {
        this.checkLocaleKilometre = checkLocaleKilometre == null ? null : checkLocaleKilometre.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", controlUnitCode=").append(controlUnitCode);
        sb.append(", checkLocaleCode=").append(checkLocaleCode);
        sb.append(", alarmLevel=").append(alarmLevel);
        sb.append(", totalRain=").append(totalRain);
        sb.append(", speedLimit=").append(speedLimit);
        sb.append(", speedLimitSectionNumber=").append(speedLimitSectionNumber);
        sb.append(", branchLineCode=").append(branchLineCode);
        sb.append(", speedLimitSectionBegin=").append(speedLimitSectionBegin);
        sb.append(", speedLimitSectionEnd=").append(speedLimitSectionEnd);
        sb.append(", dataLength=").append(dataLength);
        sb.append(", influenceInterval=").append(influenceInterval);
        sb.append(", minuteRain=").append(minuteRain);
        sb.append(", minute10Rain=").append(minute10Rain);
        sb.append(", hourRain=").append(hourRain);
        sb.append(", hour24Rain=").append(hour24Rain);
        sb.append(", continuityRain=").append(continuityRain);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", reserve2=").append(reserve2);
        sb.append(", date=").append(date);
        sb.append(", alarmStatus=").append(alarmStatus);
        sb.append(", relieveTime=").append(relieveTime);
        sb.append(", videoUpStatus=").append(videoUpStatus);
        sb.append(", checkLocaleKilometre=").append(checkLocaleKilometre);
        sb.append(", data=").append(data);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}