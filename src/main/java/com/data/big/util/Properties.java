package com.data.big.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Properties {


    /**
     * #京张综合视频 摄像机资产信息 url
     */
    private static String snedUrl;
    /**
     * #摄像机告警
     */
    private static String sendVideoAlarm;
    /**
     * 视频服务器告警
     */
    private static String sendServerAlarm;
    /**
     * #视频磁盘阵列告警
     */
    private static String sendDiskAlarm;
    /**
     * #交换机告警
     */
    private static String sendSwitchAlarm;
    /**
     * #智能分析告警
     */
    private static String sendIVSAlarm;


    /**
     * #是否查询前一天  0查询前一天数据  1查询当天数据
     */
    private static String getDataCron;
    /**
     * 摄像机状态
     */
    private static String sendVideoStatus;
    /**
     * 视频服务器状态
     */
    private static String sendServerStatus;
    /**
     * 视频磁盘阵列状态
     */
    private static String sendDiskStatus;
    /**
     * 交换机状态
     */
    private static String sendSwitchStatus;
    /**
     * 获取节点信息
     */
    private static String sendGetNodeInfo;
    /**
     * 获取服务器信息
     */
    private static String sendGetServerInfo;
    /**
     * 获取磁盘信息
     */
    private static String sendGetDiskInfo;
    /**
     * 获取交换机信息
     */
    private static String sendGetSwitchInfo;
    /**
     * 发送上报文件url
     */
    private static String sendFileUrl;
    /**
     * 上报文件 token
     */
    private static String token;
    /**
     * 保存文件地址
     */
    private static String saveFileUrl;
    /**
     * 保存文件地址
     */
    private static String fid;
    /**
     * 获取安保3信息 任务时间
     */
    private static String getANBAO3Cron;
    /**
     * 安保3 线路
     */
    private static String xm;
    /**
     * 安保3 路局
     */
    private static String lj;
    /**
     * 安保3 url
     */
    private static String anbao3Url;
    /**
     * 安保3 url
     */
    private static String anbao3Name;
    /**
     * 安保3 url
     */
    private static String sendGetCameraInfoCron;
    /**
     * 安保3 url
     */
    private static String sendGetNodeInfoCron;
    /**
     * 安保3 url
     */
    private static String sendGetServerInfoCron;
    /**
     * 安保3 url
     */
    private static String sendGetDiskInfoCron;
    /**
     * 安保3 url
     */
    private static String sendGetSwitchInfoCron;
    /**
     * 安保3 url
     */
    private static String sendGetCameraAlarmCron;
    /**
     * 安保3 url
     */
    private static String sendGetCameraStatusCron;
    /**
     * 安保3 url
     */
    private static String sendGetServerAlarmCron;
    /**
     * 安保3 url
     */
    private static String sendGetServerStatusCron;
    /**
     * 安保3 url
     */
    private static String sendGetDiskAlarmCron;
    /**
     * 安保3 url
     */
    private static String sendGeDiskStatusCron;
    /**
     * 安保3 url
     */
    private static String sendGetSwitchAlarmCron;
    /**
     * 安保3 url
     */
    private static String sendGetSwitchStatusCron;
    /**
     * 安保3 url
     */
    private static String sendGetIVSAlarmCron;
    /**
     * nettyHost url
     */
    private static String nettyHost;
    /**
     * nettyPost 端口
     */
    private static String nettyPost;

    /**
     * 版本号
     */
    private static String version;

    /**
     * 防灾用户名密码
     */
    private static String nameAndPassword;

    /**
     * 是否启动防灾客户端
     */
    private static String nettyStartOrNot;
    /**
     * 公务webserivce接口 url
     */
    private static String wsdlUrl;
    /**
     *  命名空间
     */
    private static String wsdlNamespace;
    /**
     * 服务名称
     */
    private static String wsdlName;
    /**
     * 旅服 访问url
     */
    private static String lfUrl;
    /**
     * 旅服 图片 视频保存地址
     */
    private static String lfSaveAddress;
    /**
     * 向大数据中心发送摄像头资产信息 url
     */
    private static String sendAddTableUrl;

    /**
     * 向大数据中心发送摄像头资产信息 token
     */
    private static String Authorization;


    public static String getSendAddTableUrl() {
        return sendAddTableUrl;
    }
    @Value("${sendAddTableUrl}")
    public  void setSendAddTableUrl(String sendAddTableUrl) {
        Properties.sendAddTableUrl = sendAddTableUrl;
    }



    public static String getAuthorization() {
        return Authorization;
    }
    @Value("${Authorization}")
    public  void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public static String getLfUrl() {
        return lfUrl;
    }
    @Value("${lf.url}")
    public  void setLfUrl(String lfUrl) {
        Properties.lfUrl = lfUrl;
    }

    public static String getLfSaveAddress() {
        return lfSaveAddress;
    }
    @Value("${lf.saveAddress}")
    public  void setLfSaveAddress(String lfSaveAddress) {
        Properties.lfSaveAddress = lfSaveAddress;
    }

    public static String getWsdlUrl() {
        return wsdlUrl;
    }
    @Value("${gw.wsdlUrl}")
    public  void setWsdlUrl(String wsdlUrl) {
        Properties.wsdlUrl = wsdlUrl;
    }

    public static String getWsdlNamespace() {
        return wsdlNamespace;
    }
    @Value("${gw.wsdlNamespace}")
    public  void setWsdlNamespace(String wsdlNamespace) {
        Properties.wsdlNamespace = wsdlNamespace;
    }

    public static String getWsdlName() {
        return wsdlName;
    }
    @Value("${gw.wsdlName}")
    public  void setWsdlName(String wsdlName) {
        Properties.wsdlName = wsdlName;
    }

    public static String getNettyStartOrNot() {
        return nettyStartOrNot;
    }
    @Value("${netty.startOrNot}")
    public  void setNettyStartOrNot(String nettyStartOrNot) {
        Properties.nettyStartOrNot = nettyStartOrNot;
    }

    public static String getVersion() {
        return version;
    }
    @Value("${netty.version}")
    public  void setVersion(String version) {
        Properties.version = version;
    }

    public static String getNameAndPassword() {
        return nameAndPassword;
    }
    @Value("${netty.nameAndPassword}")
    public  void setNameAndPassword(String nameAndPassword) {
        Properties.nameAndPassword = nameAndPassword;
    }

    public static String getNettyHost() {
        return nettyHost;
    }
    @Value("${netty.host}")
    public  void setNettyHost(String nettyHost) {
        Properties.nettyHost = nettyHost;
    }

    public static String getNettyPost() {
        return nettyPost;
    }
    @Value("${netty.port}")
    public  void setNettyPost(String nettyPost) {
        Properties.nettyPost = nettyPost;
    }

    public static String getSendGetCameraInfoCron() {
        return sendGetCameraInfoCron;
    }
    @Value("${sendGetCameraInfo.cron}")
    public  void setSendGetCameraInfoCron(String sendGetCameraInfoCron) {
        Properties.sendGetCameraInfoCron = sendGetCameraInfoCron;
    }

    public static String getSendGetNodeInfoCron() {
        return sendGetNodeInfoCron;
    }
    @Value("${sendGetNodeInfo.cron}")
    public  void setSendGetNodeInfoCron(String sendGetNodeInfoCron) {
        Properties.sendGetNodeInfoCron = sendGetNodeInfoCron;
    }

    public static String getSendGetServerInfoCron() {
        return sendGetServerInfoCron;
    }
    @Value("${sendGetServerInfo.cron}")
    public  void setSendGetServerInfoCron(String sendGetServerInfoCron) {
        Properties.sendGetServerInfoCron = sendGetServerInfoCron;
    }

    public static String getSendGetDiskInfoCron() {
        return sendGetDiskInfoCron;
    }
    @Value("${sendGetDiskInfo.cron}")
    public  void setSendGetDiskInfoCron(String sendGetDiskInfoCron) {
        Properties.sendGetDiskInfoCron = sendGetDiskInfoCron;
    }

    public static String getSendGetSwitchInfoCron() {
        return sendGetSwitchInfoCron;
    }
    @Value("${sendGetSwitchInfo.cron}")
    public  void setSendGetSwitchInfoCron(String sendGetSwitchInfoCron) {
        Properties.sendGetSwitchInfoCron = sendGetSwitchInfoCron;
    }

    public static String getSendGetCameraAlarmCron() {
        return sendGetCameraAlarmCron;
    }
    @Value("${sendGetCameraAlarm.cron}")
    public  void setSendGetCameraAlarmCron(String sendGetCameraAlarmCron) {
        Properties.sendGetCameraAlarmCron = sendGetCameraAlarmCron;
    }

    public static String getSendGetCameraStatusCron() {
        return sendGetCameraStatusCron;
    }
    @Value("${sendGetCameraStatus.cron}")
    public  void setSendGetCameraStatusCron(String sendGetCameraStatusCron) {
        Properties.sendGetCameraStatusCron = sendGetCameraStatusCron;
    }

    public static String getSendGetServerAlarmCron() {
        return sendGetServerAlarmCron;
    }
    @Value("${sendGetServerAlarm.cron}")
    public  void setSendGetServerAlarmCron(String sendGetServerAlarmCron) {
        Properties.sendGetServerAlarmCron = sendGetServerAlarmCron;
    }

    public static String getSendGetServerStatusCron() {
        return sendGetServerStatusCron;
    }
    @Value("${sendGetServerStatus.cron}")
    public  void setSendGetServerStatusCron(String sendGetServerStatusCron) {
        Properties.sendGetServerStatusCron = sendGetServerStatusCron;
    }

    public static String getSendGetDiskAlarmCron() {
        return sendGetDiskAlarmCron;
    }
    @Value("${sendGetDiskAlarm.cron}")
    public  void setSendGetDiskAlarmCron(String sendGetDiskAlarmCron) {
        Properties.sendGetDiskAlarmCron = sendGetDiskAlarmCron;
    }

    public static String getSendGeDiskStatusCron() {
        return sendGeDiskStatusCron;
    }
    @Value("${sendGeDiskStatus.cron}")
    public  void setSendGeDiskStatusCron(String sendGeDiskStatusCron) {
        Properties.sendGeDiskStatusCron = sendGeDiskStatusCron;
    }

    public static String getSendGetSwitchAlarmCron() {
        return sendGetSwitchAlarmCron;
    }
    @Value("${sendGetSwitchAlarm.cron}")
    public  void setSendGetSwitchAlarmCron(String sendGetSwitchAlarmCron) {
        Properties.sendGetSwitchAlarmCron = sendGetSwitchAlarmCron;
    }

    public static String getSendGetSwitchStatusCron() {
        return sendGetSwitchStatusCron;
    }
    @Value("${sendGetSwitchStatus.cron}")
    public  void setSendGetSwitchStatusCron(String sendGetSwitchStatusCron) {
        Properties.sendGetSwitchStatusCron = sendGetSwitchStatusCron;
    }

    public static String getSendGetIVSAlarmCron() {
        return sendGetIVSAlarmCron;
    }
    @Value("${sendGetIVSAlarm.cron}")
    public  void setSendGetIVSAlarmCron(String sendGetIVSAlarmCron) {
        Properties.sendGetIVSAlarmCron = sendGetIVSAlarmCron;
    }

    public static String getAnbao3Name() {
        return anbao3Name;
    }
    @Value("${getANBAO3.name}")
    public  void setAnbao3Name(String anbao3Name) {
        Properties.anbao3Name = anbao3Name;
    }

    public static String getAnbao3Url() {
        return anbao3Url;
    }

    @Value("${getANBAO3.url}")
    public void setAnbao3Url(String anbao3Url) {
        Properties.anbao3Url = anbao3Url;
    }

    public static String getXm() {
        return xm;
    }

    @Value("${getANBAO3.xm}")
    public void setXm(String xm) {
        Properties.xm = xm;
    }

    public static String getLj() {
        return lj;
    }

    @Value("${getANBAO3.lj}")
    public void setLj(String lj) {
        Properties.lj = lj;
    }

    public static String getGetANBAO3Cron() {
        return getANBAO3Cron;
    }

    @Value("${getANBAO3.cron}")
    public void setGetANBAO3Cron(String getANBAO3Cron) {
        Properties.getANBAO3Cron = getANBAO3Cron;
    }

    public static String getFid() {
        return fid;
    }

    @Value("${fid}")
    public void setFid(String fid) {
        Properties.fid = fid;
    }

    public static String getSaveFileUrl() {
        return saveFileUrl;
    }

    @Value("${saveFileUrl}")
    public void setSaveFileUrl(String saveFileUrl) {
        Properties.saveFileUrl = saveFileUrl;
    }

    public static String getSendFileUrl() {
        return sendFileUrl;
    }

    @Value("${sendFileUrl}")
    public void setSendFileUrl(String sendFileUrl) {
        Properties.sendFileUrl = sendFileUrl;
    }

    public static String getToken() {
        return token;
    }

    @Value("${token}")
    public void setToken(String token) {
        Properties.token = token;
    }

    public static String getSendGetNodeInfo() {
        return sendGetNodeInfo;
    }

    @Value("${sendGetNodeInfo.url}")
    public void setSendGetNodeInfo(String sendGetNodeInfo) {
        Properties.sendGetNodeInfo = sendGetNodeInfo;
    }

    public static String getSendGetServerInfo() {
        return sendGetServerInfo;
    }

    @Value("${sendGetServerInfo.url}")
    public void setSendGetServerInfo(String sendGetServerInfo) {
        Properties.sendGetServerInfo = sendGetServerInfo;
    }

    public static String getSendGetDiskInfo() {
        return sendGetDiskInfo;
    }

    @Value("${sendGetDiskInfo.url}")
    public void setSendGetDiskInfo(String sendGetDiskInfo) {
        Properties.sendGetDiskInfo = sendGetDiskInfo;
    }

    public static String getSendGetSwitchInfo() {
        return sendGetSwitchInfo;
    }

    @Value("${sendGetSwitchInfo.url}")
    public void setSendGetSwitchInfo(String sendGetSwitchInfo) {
        Properties.sendGetSwitchInfo = sendGetSwitchInfo;
    }

    public static String getSendVideoStatus() {
        return sendVideoStatus;
    }

    @Value("${sendGetCameraStatus.url}")
    public void setSendVideoStatus(String sendVideoStatus) {
        Properties.sendVideoStatus = sendVideoStatus;
    }

    public static String getSendServerStatus() {
        return sendServerStatus;
    }

    @Value("${sendGetServerStatus.url}")
    public void setSendServerStatus(String sendServerStatus) {
        Properties.sendServerStatus = sendServerStatus;
    }

    public static String getSendDiskStatus() {
        return sendDiskStatus;
    }

    @Value("${sendGeDiskStatus.url}")
    public void setSendDiskStatus(String sendDiskStatus) {
        Properties.sendDiskStatus = sendDiskStatus;
    }

    public static String getSendSwitchStatus() {
        return sendSwitchStatus;
    }

    @Value("${sendGetSwitchStatus.url}")
    public void setSendSwitchStatus(String sendSwitchStatus) {
        Properties.sendSwitchStatus = sendSwitchStatus;
    }


    public static String getSendVideoAlarm() {
        return sendVideoAlarm;
    }

    @Value("${sendGetCameraAlarm.url}")
    public void setSendVideoAlarm(String sendVideoAlarm) {
        Properties.sendVideoAlarm = sendVideoAlarm;
    }

    public static String getSendServerAlarm() {
        return sendServerAlarm;
    }

    @Value("${sendGetServerAlarm.url}")
    public void setSendServerAlarm(String sendServerAlarm) {
        Properties.sendServerAlarm = sendServerAlarm;
    }

    public static String getSendDiskAlarm() {
        return sendDiskAlarm;
    }

    @Value("${sendGetDiskAlarm.url}")
    public void setSendDiskAlarm(String sendDiskAlarm) {
        Properties.sendDiskAlarm = sendDiskAlarm;
    }

    public static String getSendSwitchAlarm() {
        return sendSwitchAlarm;
    }

    @Value("${sendGetSwitchAlarm.url}")
    public void setSendSwitchAlarm(String sendSwitchAlarm) {
        Properties.sendSwitchAlarm = sendSwitchAlarm;
    }

    public static String getSendIVSAlarm() {
        return sendIVSAlarm;
    }

    @Value("${sendGetIVSAlarm.url}")
    public void setSendIVSAlarm(String sendIVSAlarm) {
        Properties.sendIVSAlarm = sendIVSAlarm;
    }


    public static String getSnedUrl() {
        return snedUrl;
    }

    @Value("${sendGetCameraInfo.url}")
    public void setSnedUrl(String snedUrl) {
        Properties.snedUrl = snedUrl;
    }

    public static String getGetDataCron() {
        return getDataCron;
    }
    @Value("${alarmSelectTime.cron}")
    public  void setGetDataCron(String getDataCron) {
        Properties.getDataCron = getDataCron;
    }
}
