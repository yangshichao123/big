//package com.data.big.task;
//
//import com.data.big.service.Service;
//import com.data.big.service.ServiceNetty;
//import com.data.big.util.DateFormatHelper;
//import com.data.big.util.DateUtils;
//import com.data.big.util.Properties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@EnableAsync
//@Component
//public class TimingTask {
//
//
//    @Autowired
//    private Service service;
//    @Autowired
//    private ServiceNetty serviceNetty;
//
//    @Bean
//    public TaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(10);
//        return scheduler;
//    }
//    // 10秒检测用户和服务 是否超过3个心跳没有连接   是 删除缓存
//    //@Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    /*@Scheduled(fixedRateString ="${cron}", initialDelay=1000)
//    private void configur() {
//        System.out.println(DateFormatHelper.date2String(new Date(),"mm:ss")+" 44444444444444444444444");
//    }*/
//    @Scheduled(cron = "0 */5 * * * ?")
//    private void getGWTask() {
//        service.addGWTask();
//    }
//    @Scheduled(cron = "0 */5 * * * ?")
//    private void getFZTask() {
//        serviceNetty.addFZTask();
//    }
//
//
//    @Scheduled(cron = "${getANBAO3.cron}")
//    private void getANBAO3() {
//        String time = Properties.getGetANBAO3Cron();
//        String[] strs = time.split(" ");
//        String beginTime = "";
//        String endTime = "";
//         for (String str : strs) {
//
//             System.out.println(str);
//         }
//        if ("*".equals(strs[3])) {
//            String hour = strs[2].split("/")[1];
//            beginTime = DateUtils.getBeforeDateTime(Integer.parseInt(hour), "yyyyMMddHH") + "0000";
//            endTime = DateUtils.getBeforeDateTime(1, "yyyyMMddHH") + "5959";
//        } else {
//            beginTime = DateUtils.getBeforeDate(1, "yyyyMMdd") + " 000000";
//            endTime = DateUtils.getBeforeDate(1, "yyyyMMdd") + " 235959";
//        }
//        if ("".equalsIgnoreCase(beginTime) || "".equals(endTime)) {
//            return;
//        }
//        service.getANBAO3(beginTime, endTime, Properties.getXm(), Properties.getLj());
//
//    }
//
//
//    private Map<String ,String> getTiemMap(String time){
//        Map<String ,String > map=new HashMap<>();
//
//        String[] strs = time.split(" ");
//        String beginTime = "";
//        String endTime = "";
//         /*for (String str : strs) {
//
//             System.out.println(str);
//         }*/
//        if ("*".equals(strs[3])) {
//            String[] split = strs[2].split("/");
//            if(split.length>1){
//                String hour = split[1];
//                beginTime = DateUtils.getBeforeDateTime(Integer.parseInt(hour), "yyyy-MM-dd HH") + ":00:00";
//                endTime = DateUtils.getBeforeDateTime(1, "yyyy-MM-dd HH") + ":59:59";
//            }
//        } else {
//            beginTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 00:00:00";
//            endTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 23:59:59";
//        }
//        if ("".equalsIgnoreCase(beginTime) || "".equals(endTime)) {
//            return null;
//        }
//        map.put("beginTime",beginTime);
//        map.put("endTime",endTime);
//        return  map;
//    }
//
//
//
//    // @Scheduled(cron="* * 0/1 * * ?")
//    @Scheduled(cron = "${gw.cron}")
//    private void sendGu() {
//        String beginTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 00:00:00";
//        String endTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 23:59:59";
//        service.getHcsj(beginTime, endTime, "3","京包客专");
//        service.getSgjh(beginTime, endTime,"京包客专");
//        service.getWxjh(beginTime, endTime,"京包客专");
//
//    }
//
//
//}
