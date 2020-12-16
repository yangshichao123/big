package com.data.big.task;

import com.alibaba.fastjson.JSONObject;
import com.data.big.gw.GwaqscJxglServicePortType;
import com.data.big.mapper.LogRestMapper;
import com.data.big.model.LogRest;
import com.data.big.model.Token;
import com.data.big.model.User;
import com.data.big.service.Service;
import com.data.big.service.ServiceFZ;
import com.data.big.service.ServiceNetty;
import com.data.big.service.ServiceUser;
import com.data.big.util.*;
import com.data.big.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Slf4j
@EnableAsync
@Component
public class TimingTask {


    @Autowired
    private Service service;
    @Autowired
    private ServiceNetty serviceNetty;
    @Autowired
    private ServiceFZ serviceFZ;
    @Autowired
    private LogRestMapper logRestMapper;
    @Autowired
    private ServiceUser serviceUser;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    // 10秒检测用户和服务 是否超过3个心跳没有连接   是 删除缓存
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    /*@Scheduled(fixedRateString ="${cron}", initialDelay=1000)
    private void configur() {
        System.out.println(DateFormatHelper.date2String(new Date(),"mm:ss")+" 44444444444444444444444");
    }*/
    @Scheduled(cron = "30 */5 * * * ?")
    private void getGWTask() {
        log.info("*************定时执行公务添加到视频任务*************************");
        service.addGWTask();
        LogRest log = new LogRest();
        log.setFunname("getGWTask");
        log.setLrsj(new Date());
        log.setParamin("定时执行公务添加到视频任务");

        log.setType(0 + "");
        logRestMapper.insert(log);
    }
//    @Scheduled(cron = "50 */5 * * * ?")
//    private void addGWTaskDoor() {
//        log.info("*************定时执行公务作业门添加到视频任务*************************");
//        service.addGWTaskDoor();
//        LogRest log = new LogRest();
//        log.setFunname("addGWTaskDoor");
//        log.setLrsj(new Date());
//        log.setParamin("定时执行公务作业门添加到视频任务");
//
//        log.setType(0 + "");
//        logRestMapper.insert(log);
//    }

    @Scheduled(cron = "0 */5 * * * ?")
    private void getFZTask() {
        log.info("*************定时执行防灾添加到视频任务*************************");
        //serviceNetty.addFZTask();
        serviceFZ.addFZTask();

        LogRest log = new LogRest();
        log.setFunname("getFZTask");
        log.setLrsj(new Date());
        log.setParamin("定时执行防灾添加到视频任务");

        log.setType(0 + "");
        logRestMapper.insert(log);
    }


    @Scheduled(cron = "${getANBAO3.cron}")
    private void getANBAO3() {
        log.info("*************开始定时查询anbao3数据*************************");
        String time = Properties.getGetANBAO3Cron();
        String[] strs = time.split(" ");
        String beginTime = "";
        String endTime = "";
        for (String str : strs) {

            System.out.println(str);
        }
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

        LogRest log1 = new LogRest();
        log1.setFunname("getANBAO3");
        log1.setLrsj(new Date());
        log1.setParamin("定时查询anbao3数据");

        log1.setType(0 + "");
        logRestMapper.insert(log1);
        log.info("*************结束定时查询anbao3数据*************************");
    }


    private Map<String,String> getTiemMap(String time) {
        Map<String,String> map = new HashMap<>();

        String[] strs = time.split(" ");
        String beginTime = "";
        String endTime = "";
         /*for (String str : strs) {

             System.out.println(str);
         }*/
        if ("*".equals(strs[3])) {
            String[] split = strs[2].split("/");
            if (split.length > 1) {
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
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return map;
    }


    // @Scheduled(cron="* * 0/1 * * ?")
    @Scheduled(cron = "${gw.cron}")
    private void sendGW() {
        String data = "";
        LogRest log1 = new LogRest();
        log1.setFunname("sendGW");
        log1.setLrsj(new Date());
        log1.setParamin("定时执行查询公务任务");

        log1.setType(0 + "");


        log.info("*************开始定时执行查询公务任务*************************");
        String beginTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd");
        String endTime = DateUtils.getBeforeDate(0, "yyyy-MM-dd");
        try {
            Map<String,String> hcsj = service.getHcsj(beginTime, endTime, "", "京包客专", "1", "1000");
            data = " 查询到晃车数据个数" + hcsj.get("size");

        } catch (Exception e) {
            data = "开始定时执行查询公务任务______getHcsj______---失败";
            log.error("开始定时执行查询公务任务______getHcsj______---失败");
            log.error(e.getMessage(), e);
        }
        try {

            Map<String,String> sgjh = service.getSgjh(beginTime, endTime, "京包客专");
            data = data + "  查询到施工计划数据个数" + sgjh.get("size");
        } catch (Exception e) {
            data = data + "  开始定时执行查询公务任务_______getSgjh_____---失败";
            log.error("开始定时执行查询公务任务_______getSgjh_____---失败");
            log.error(e.getMessage(), e);
        }
        try {

            Map<String,String> wxjh = service.getWxjh(beginTime, endTime, "京包客专");
            data = data + "  查询到维修计划数据个数" + wxjh.get("size");
        } catch (Exception e) {
            data = data + "  开始定时执行查询公务任务_______getWxjh_____---失败";
            log.error("开始定时执行查询公务任务_______getWxjh_____---失败");
            log.error(e.getMessage(), e);
        }
        log1.setRedata(data);
        logRestMapper.insert(log1);
        log.info("*************结束定时执行查询公务任务*************************");
    }

    // @Scheduled(cron="* * 0/1 * * ?")
    @Scheduled(cron = "${fz.cron}")
    private void sendFZ() {
        log.info("*************开始定时执行防灾任务*************************");
        String beginTime = DateUtils.getBeforeDate(2, "yyyy-MM-dd") + " 00:00:00";
        String endTime = DateUtils.getBeforeDate(2, "yyyy-MM-dd") + " 23:59:59";
        //serviceFZ.login();

        Map<String,Object> map = new HashMap<>();
        map.put("bureauCode", "C");
        map.put("lineCode", "30142");
        map.put("monitorCode", "");
        map.put("alarmLevel", "");
        map.put("type", "");
        map.put("startTime", beginTime);
        map.put("endTime", endTime);
        //map.put("startTime","2018-06-24 17:06:41");
        //map.put("endTime","2021-08-25 14:16:56");
        serviceFZ.getFZAlarm(map);
        LogRest log1 = new LogRest();
        log1.setFunname("sendFZ");
        log1.setLrsj(new Date());
        log1.setParamin("定时执行防灾任务");

        log1.setType(0 + "");
        logRestMapper.insert(log1);

        log.info("*************结束定时执行防灾任务*************************");
    }

    @Scheduled(cron = "0 */5 * * * ?")
    private void getGWConn() {
        Map map = Thread.getAllStackTraces();
        log.info("-------------当前线程个数：" + map.size());

        MyThread myThreadGW = new MyThread(service);
        myThreadGW.start();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            boolean alive = myThreadGW.isAlive();
            if (alive) {
                myThreadGW.stop();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        MyThread myThreadFZ = new MyThread(serviceFZ);
        myThreadFZ.start();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            boolean alive = myThreadFZ.isAlive();
            if (alive) {
                myThreadFZ.stop();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Scheduled(cron = "0/30 * * * * ?")
    private void checkToken() {

        serviceUser.checkToken();

    }

}
