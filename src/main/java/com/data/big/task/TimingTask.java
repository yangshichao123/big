package com.data.big.task;

import com.data.big.service.Service;
import com.data.big.service.ServiceNetty;
import com.data.big.util.DateFormatHelper;
import com.data.big.util.DateUtils;
import com.data.big.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@EnableAsync
@Component
public class TimingTask {


    @Autowired
    private Service service;
    @Autowired
    private ServiceNetty serviceNetty;
    // 10秒检测用户和服务 是否超过3个心跳没有连接   是 删除缓存
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    /*@Scheduled(fixedRateString ="${cron}", initialDelay=1000)
    private void configur() {
        System.out.println(DateFormatHelper.date2String(new Date(),"mm:ss")+" 44444444444444444444444");
    }*/


    // @Scheduled(cron="* * 0/1 * * ?")
    @Scheduled(cron = "${sendFileCron}")
    private void sendFile() {
        System.out.println(DateFormatHelper.date2String(new Date(), "mm:ss") + " 44444444444444444444444");
        List<Map<String,Object>> maps = service.sendFile("", "", "");

    }

    @Scheduled(cron = "${getANBAO3.cron}")
    private void getANBAO3() {
        String time = Properties.getGetANBAO3Cron();
        String[] strs = time.split(" ");
        String beginTime = "";
        String endTime = "";
         /*for (String str : strs) {

             System.out.println(str);
         }*/
        if ("*".equals(strs[3])) {
            String hour = strs[2].split("/")[1];
            beginTime = DateUtils.getBeforeDateTime(Integer.parseInt(hour), "yyyyMMddHH") + "0000";
            endTime = DateUtils.getBeforeDateTime(1, "yyyyMMddHH") + "5959";
        } else {
            beginTime = DateUtils.getBeforeDate(1, "yyyyMMdd") + " 000000";
            endTime = DateUtils.getBeforeDate(1, "yyyyMMdd") + " 235959";
        }
        if ("".equalsIgnoreCase(beginTime) || "".equals(endTime)) {
            return;
        }
        service.getANBAO3(beginTime, endTime, Properties.getXm(), Properties.getLj());

    }

  /*  @Scheduled(cron = "${alarmSelectTime.cron}")
    private void getData() {
        String time = Properties.getGetDataCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.executeTask(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }*/

    private Map<String ,String> getTiemMap(String time){
        Map<String ,String > map=new HashMap<>();

        String[] strs = time.split(" ");
        String beginTime = "";
        String endTime = "";
         /*for (String str : strs) {

             System.out.println(str);
         }*/
        if ("*".equals(strs[3])) {
            String[] split = strs[2].split("/");
            if(split.length>1){
                String hour = split[1];
                beginTime = DateUtils.getBeforeDateTime(Integer.parseInt(hour), "yyyy-MM-dd HH") + ":00:00";
                endTime = DateUtils.getBeforeDateTime(1, "yyyy-MM-dd HH") + ":59:59";
            }
        } else {
            beginTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 00:00:00";
            endTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 23:59:59";
        }
        if ("".equalsIgnoreCase(beginTime) || "".equals(endTime)) {
            return null;
        }
        map.put("beginTime",beginTime);
        map.put("endTime",endTime);
        return  map;
    }

   /* @Scheduled(cron = "${sendGetCameraInfo.cron}")
    private void sendGetCameraInfo() {

        service.GetCameraInfo();

    }
    @Scheduled(cron = "${sendGetNodeInfo.cron}")
    private void sendGetNodeInfo() {

        service.GetNodeInfo();

    }
    @Scheduled(cron = "${sendGetServerInfo.cron}")
    private void sendGetServerInfo() {

        service.GetServerInfo();

    }
    @Scheduled(cron = "${sendGetDiskInfo.cron}")
    private void sendGetDiskInfo() {

        service.GetDiskInfo();

    }
    @Scheduled(cron = "${sendGetSwitchInfo.cron}")
    private void sendGetSwitchInfo() {

        service.GetSwitchInfo();

    }*/
    @Scheduled(cron = "${sendGetCameraAlarm.cron}")
    private void sendGetCameraAlarm() {
        String time = Properties.getSendGetCameraAlarmCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetCameraAlarm(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetCameraStatus.cron}")
    private void sendGetCameraStatus() {
        String time = Properties.getSendGetCameraStatusCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetCameraStatus(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetServerAlarm.cron}")
    private void sendGetServerAlarm() {
        String time = Properties.getSendGetServerAlarmCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetServerAlarm(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetServerStatus.cron}")
    private void sendGetServerStatus() {
        String time = Properties.getSendGetServerStatusCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetServerStatus(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetDiskAlarm.cron}")
    private void sendGetDiskAlarm() {
        String time = Properties.getSendGetDiskAlarmCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetDiskAlarm(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGeDiskStatus.cron}")
    private void sendGeDiskStatus() {
        String time = Properties.getSendGeDiskStatusCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GeDiskStatus(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetSwitchAlarm.cron}")
    private void sendGetSwitchAlarm() {
        String time = Properties.getSendGetSwitchAlarmCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetSwitchAlarm(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetSwitchStatus.cron}")
    private void sendGetSwitchStatus() {
        String time = Properties.getSendGetSwitchStatusCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetSwitchStatus(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    @Scheduled(cron = "${sendGetIVSAlarm.cron}")
    private void sendGetIVSAlarm() {
        String time = Properties.getSendGetIVSAlarmCron();
        Map<String,String> tiemMap = this.getTiemMap(time);
        if(tiemMap==null){
            return;
        }
        service.GetIVSAlarm(tiemMap.get("beginTime"),tiemMap.get("endTime"));

    }
    //定时查询任务
    @Scheduled(cron = "${queryTaskTime}")
    private void queryTaskTime() {

        service.queryTask();

    }
    //发送心跳 向防灾系统
    @Scheduled(fixedRate=500)
    private void sendKeepAlive() {

        serviceNetty.sendKeepAlive();

    }
    //定时查询 防灾告警和安监告警 添加到任务表里
    @Scheduled(cron = "0 0 0/1 * * ?")
    private void addTask() {

        serviceNetty.addTask();

    }

}
