package com.data.big.service;


import com.data.big.model.Modular;
import com.data.big.model.Role;
import com.data.big.model.User;
import org.apache.cxf.endpoint.Client;

import java.util.Map;

public interface ServiceFZ {

    /**
     * 用户登陆
     *
     * @return
     */
    Map<String,Object> login();


    /**
     * 获取告警信息
     *
     * @param map
     * @return
     */
    Map<String,Object> getFZAlarm(Map<String,Object> map);

    /**
     * 添加到视频下载任务类
     */
    void addFZTask();

    /**
     * 分页查询 防灾告警
     *
     * @param alarmType 告警类型
     * @param km        公里标
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageIndex 开始页
     * @param pageSize  页大小
     * @return
     */
    Map<String,Object> getFzAlarm(String alarmType, String km, String startTime, String endTime, String pageIndex, String pageSize);

    /**
     * 获取连接防灾服务器
     *
     * @return
     */
    Client getClient();
}
