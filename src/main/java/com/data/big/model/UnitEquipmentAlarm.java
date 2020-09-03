package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "unit_equipment_alarm")
public class UnitEquipmentAlarm implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 监控单元编码 监测点公里标，单位m
     */
    private String checkLocaleCode;

    /**
     * A主机状态 两位表示一个状态，从低位到高位写入，分别是主备状态（0表示主，1表示备）、工作状态（0表示正常，1表示故障、2表示未知、3表示失联）、网络状态（0表示正常，1表示故障、2表示未知、3表示失联）。
     */
    private String aHostStatus;

    /**
     * B主机状态 两位表示一个状态，从低位到高位写入，分别是主备状态（0表示主，1表示备）、工作状态（0表示正常，1表示故障、2表示未知、3表示失联）、网络状态（0表示正常，1表示故障、2表示未知、3表示失联）。
     */
    private String bHostStatus;

    /**
     * 数据长度 板卡状态字节长度
     */
    private String cardDataLength;

    /**
     * 板卡状态 UTF-8编码格式，板卡名称:板卡状态,板卡名称:板卡状态,……；状态有0表示正常、1表示故障、2表示未知、3表示失联
     */
    private String cardStatus;

    /**
     * 电源A状态  两位表示一个状态，从低位到高位写入，分别是输入状态（0表示正常，1表示故障、2未知、3表示失联）、输出状态（0表示正常，1表示故障、2未知、3表示失联）、通讯状态（0表示正常，1表示故障、2未知、3表示失联）
     */
    private String sourceAStatus;

    /**
     * 电源B状态 两位表示一个状态，从低位到高位写入，分别是输入状态（0表示正常，1表示故障、2未知、3表示失联）、输出状态（0表示正常，1表示故障、2未知、3表示失联）、通讯状态（0表示正常，1表示故障、2未知、3表示失联）
     */
    private String sourceBStatus;

    /**
     * 电源A输入电压值 单位0.1V
     */
    private String sourceAInVoltageValue;

    /**
     * 电源B输入电压值 单位0.1V
     */
    private String sourceBInVoltageValue;

    /**
     * 电源A输出电压值 无符号整数，单位0.1V
     */
    private String sourceAOutVoltageValue;

    /**
     * 电源B输出电压值 单位0.1V
     */
    private String sourceBOutVoltageValue;

    /**
     * 电源A剩余容量 百分比（0…100）
     */
    private String sourceARemainingElectricity;

    /**
     * 电源B剩余容量 百分比（0…100）
     */
    private String sourceBRemainingElectricity;

    /**
     * 数据长度 运行状态字节长度
     */
    private String runDataLength;

    /**
     * 电源运行状态  UTF-8编码格式，名称:状态,名称:状态,……；状态有0表示正常、1表示故障、2表示未知、3表示失联。
     */
    private String sourceRunStatus;

    /**
     * 故障类型 0表示故障开始，1表示故障结束（所有故障恢复）
     */
    private String faultType;

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
     * 原始数据
     */
    private String data;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table unit_equipment_alarm
     *
     * @mbg.generated Fri Aug 28 15:39:42 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCheckLocaleCode() {
        return checkLocaleCode;
    }

    public void setCheckLocaleCode(String checkLocaleCode) {
        this.checkLocaleCode = checkLocaleCode == null ? null : checkLocaleCode.trim();
    }

    public String getaHostStatus() {
        return aHostStatus;
    }

    public void setaHostStatus(String aHostStatus) {
        this.aHostStatus = aHostStatus == null ? null : aHostStatus.trim();
    }

    public String getbHostStatus() {
        return bHostStatus;
    }

    public void setbHostStatus(String bHostStatus) {
        this.bHostStatus = bHostStatus == null ? null : bHostStatus.trim();
    }

    public String getCardDataLength() {
        return cardDataLength;
    }

    public void setCardDataLength(String cardDataLength) {
        this.cardDataLength = cardDataLength == null ? null : cardDataLength.trim();
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    public String getSourceAStatus() {
        return sourceAStatus;
    }

    public void setSourceAStatus(String sourceAStatus) {
        this.sourceAStatus = sourceAStatus == null ? null : sourceAStatus.trim();
    }

    public String getSourceBStatus() {
        return sourceBStatus;
    }

    public void setSourceBStatus(String sourceBStatus) {
        this.sourceBStatus = sourceBStatus == null ? null : sourceBStatus.trim();
    }

    public String getSourceAInVoltageValue() {
        return sourceAInVoltageValue;
    }

    public void setSourceAInVoltageValue(String sourceAInVoltageValue) {
        this.sourceAInVoltageValue = sourceAInVoltageValue == null ? null : sourceAInVoltageValue.trim();
    }

    public String getSourceBInVoltageValue() {
        return sourceBInVoltageValue;
    }

    public void setSourceBInVoltageValue(String sourceBInVoltageValue) {
        this.sourceBInVoltageValue = sourceBInVoltageValue == null ? null : sourceBInVoltageValue.trim();
    }

    public String getSourceAOutVoltageValue() {
        return sourceAOutVoltageValue;
    }

    public void setSourceAOutVoltageValue(String sourceAOutVoltageValue) {
        this.sourceAOutVoltageValue = sourceAOutVoltageValue == null ? null : sourceAOutVoltageValue.trim();
    }

    public String getSourceBOutVoltageValue() {
        return sourceBOutVoltageValue;
    }

    public void setSourceBOutVoltageValue(String sourceBOutVoltageValue) {
        this.sourceBOutVoltageValue = sourceBOutVoltageValue == null ? null : sourceBOutVoltageValue.trim();
    }

    public String getSourceARemainingElectricity() {
        return sourceARemainingElectricity;
    }

    public void setSourceARemainingElectricity(String sourceARemainingElectricity) {
        this.sourceARemainingElectricity = sourceARemainingElectricity == null ? null : sourceARemainingElectricity.trim();
    }

    public String getSourceBRemainingElectricity() {
        return sourceBRemainingElectricity;
    }

    public void setSourceBRemainingElectricity(String sourceBRemainingElectricity) {
        this.sourceBRemainingElectricity = sourceBRemainingElectricity == null ? null : sourceBRemainingElectricity.trim();
    }

    public String getRunDataLength() {
        return runDataLength;
    }

    public void setRunDataLength(String runDataLength) {
        this.runDataLength = runDataLength == null ? null : runDataLength.trim();
    }

    public String getSourceRunStatus() {
        return sourceRunStatus;
    }

    public void setSourceRunStatus(String sourceRunStatus) {
        this.sourceRunStatus = sourceRunStatus == null ? null : sourceRunStatus.trim();
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType == null ? null : faultType.trim();
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
        sb.append(", checkLocaleCode=").append(checkLocaleCode);
        sb.append(", aHostStatus=").append(aHostStatus);
        sb.append(", bHostStatus=").append(bHostStatus);
        sb.append(", cardDataLength=").append(cardDataLength);
        sb.append(", cardStatus=").append(cardStatus);
        sb.append(", sourceAStatus=").append(sourceAStatus);
        sb.append(", sourceBStatus=").append(sourceBStatus);
        sb.append(", sourceAInVoltageValue=").append(sourceAInVoltageValue);
        sb.append(", sourceBInVoltageValue=").append(sourceBInVoltageValue);
        sb.append(", sourceAOutVoltageValue=").append(sourceAOutVoltageValue);
        sb.append(", sourceBOutVoltageValue=").append(sourceBOutVoltageValue);
        sb.append(", sourceARemainingElectricity=").append(sourceARemainingElectricity);
        sb.append(", sourceBRemainingElectricity=").append(sourceBRemainingElectricity);
        sb.append(", runDataLength=").append(runDataLength);
        sb.append(", sourceRunStatus=").append(sourceRunStatus);
        sb.append(", faultType=").append(faultType);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", reserve2=").append(reserve2);
        sb.append(", date=").append(date);
        sb.append(", alarmStatus=").append(alarmStatus);
        sb.append(", relieveTime=").append(relieveTime);
        sb.append(", videoUpStatus=").append(videoUpStatus);
        sb.append(", data=").append(data);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}