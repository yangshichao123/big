package com.data.big.service;


import com.data.big.model.*;

import java.util.List;
import java.util.Map;

public interface Service {


    /**
     * 获取 摄像机资产信息
     *
     * @return map
     */
    Map GetCameraInfo();
    /**
     * 获取 摄像机资产信息
     *
     * @return map
     */
    Map GetCamera();

    /**
     * 获取节点信息
     *
     * @return
     */
    Map GetNodeInfo();

    /**
     * 获取服务器信息
     *
     * @return
     */
    Map GetServerInfo();

    /**
     * 获取磁盘信息
     *
     * @return
     */
    Map GetDiskInfo();

    /**
     * 获取交换机信息
     *
     * @return
     */
    Map GetSwitchInfo();

    /**
     * 执行定时任务
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     */
    void executeTask(String beginTime, String endTime);

    /**
     * 获取摄像机状态
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetCameraStatus(String starttime, String endtime);

    /**
     * 获取服务器状态
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetServerStatus(String starttime, String endtime);

    /**
     * 获取磁盘状态
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GeDiskStatus(String starttime, String endtime);

    /**
     * 获取交换机状态
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetSwitchStatus(String starttime, String endtime);

    /**
     * 获取摄像机告警
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetCameraAlarm(String starttime, String endtime);

    /**
     * 获取服务器告警
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetServerAlarm(String starttime, String endtime);

    /**
     * 获取磁盘告警
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetDiskAlarm(String starttime, String endtime);

    /**
     * 获取交换机告警
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetSwitchAlarm(String starttime, String endtime);

    /**
     * 获取智能分析告警
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @return
     */
    public Map<String,Object> GetIVSAlarm(String starttime, String endtime);

    /**
     * 发送文件
     *
     * @param sendUrl
     * @param fileUrl
     * @return
     */
    public List<Map<String,Object>> sendFile(String sendUrl, String fileUrl, String authorization);


    /**
     * 删除已经上传得文件
     */
    void deleteFile(VideoFile videoFile);

    /**
     * 添加视频任务 查询录像信息
     *
     * @param videoId   摄像机id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return map
     */
    Map<String,String> saveVideoTask(String videoId, String startTime, String endTime);

    /**
     * @param beginTime
     * @param endTime
     * @param xm
     * @param lj
     * @return
     */
    Map<String,String> getANBAO3(String beginTime, String endTime, String xm, String lj);

    /**
     * 执行查询任务  根据任务时间和方法 去查询
     */
    void queryTask();

    /**
     * 返回所有的方法名称类型
     * @return
     */
    List<MethodType> getMethodName();

    /**
     * 添加查询任务
     * @param startTime 开始时间
     * @param endTime  结束时间
     * @param methodId  查询方法id
     * @return
     */
    Map<String,String> addTask(String startTime, String endTime, String methodId);

    /**
     * 查询摄像机列表
     *
     * @return
     */
    List<Camerainfo> getVideo();

    /**
     * 添加摄像机对应的公里标
     * @param videoKilometer  摄像机参数类
     * @return
     */
    Map<String,String> addVideoKilometer(VideoKilometer videoKilometer);

    /**
     * 1、	晃车数据
     * @param qsrq 检测起始日期 yyyy-MM-dd
     * @param jsrq 检测终止日期yyyy-MM-dd
     * @param cxdj 超限等级（1、2、3、4）
     * @return
     */
    Map<String,String> getHcsj(String qsrq, String jsrq, String cxdj,String xm);

    /**
     * 2、	施工计划
     * @param qsrq 检测起始日期 yyyy-MM-dd
     * @param jsrq 检测终止日期yyyy-MM-dd
     * @return
     */
    Map<String,String> getSgjh(String qsrq, String jsrq,String xm);
    /**
     * 3、	维修计划
     * @param qsrq 检测起始日期 yyyy-MM-dd
     * @param jsrq 检测终止日期yyyy-MM-dd
     * @return
     */
    Map<String,String> getWxjh(String qsrq, String jsrq,String xm);

    /**
     * 旅服 预警信息
     * @param beginTime 开始时间
     * @param endTime  结束时间
     * @return
     */
    Map<String,Object> queryCurrentDayWarningData(String beginTime, String endTime);

    /**
     * 旅服 下载文件
     * @param sourceUrl 源文件
     * @param targetUrl 保存位置
     */
    void download(String sourceUrl, String targetUrl);

    /**
     * 添加视频下载任务
     * @param videoCode 摄像机code
     * @param benginTime  下载开始时间
     * @param endTime 下载结束时间
     * @param intervalTime 添加任务间隔时间  单位为 天
     * @param timeRange  任务执行 到几号结束
     * @return
     */
    Map<String,String> addVideoTask(String videoCode, String benginTime, String endTime, String intervalTime, String timeRange);

    /**
     * 添加公务告警到视频下载任务表
     */
    void addGWTask();

    /**
     * 查询摄像头资产信息
     * @return
     */
    Map<String,Object> getCamerainfoList();

    /**
     * 根据公里标查询摄像机信息
     * @param kMark
     * @return
     */
    Map<String,Object> getCamerainfoByk(String kMark);

    /**
     * 向大数据中心添加摄像头资产信息
     * @return
     */
    Map<String,Object> addTable();

    /**
     * 从京张高铁现场直接获取实时或历史视频播放。
     * @param ipcid 摄像机id
     * @param type 视频类型
     * @param starttime 开始时间
     * @param endtime  结束时间
     * @return
     */
    String videoPlayOpen(String ipcid, String type, String starttime, String endtime);

    /**
     * 查询字典表
     * @param dictionary 可根据类的属性查询
     * @return
     */
    Map<String,Object> getDictionary(Dictionary dictionary);

    /**
     * 查询视频记录
     * @param videoType 视频告警类型
     * @param cameraType 摄像机类型
     * @param cameraName 摄像机名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Map<String,Object> getVideoRecord(String videoType, String cameraType, String cameraName, String startTime, String endTime);
}
