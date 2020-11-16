package com.data.big.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.big.mapper.*;
import com.data.big.model.*;
import com.data.big.util.*;
import com.data.big.service.ServiceFZ;
import com.data.big.util.Properties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Slf4j
@org.springframework.stereotype.Service("ServiceFZ")
public class ServiceFZImpl implements ServiceFZ {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ModularMapper modularMapper;
    @Autowired
    private NewForeignBodyAlarmMapper newForeignBodyAlarmMapper;
    @Autowired
    private NewWindSpeedAlarmMapper windSpeedAlarmMapper;

    @Autowired
    private NewRainAlarmMapper newRainAlarmMapper;

    @Autowired
    private NewSnowAlarmMapper newSnowAlarmMapper;

    @Autowired
    private CameraMapper cameraMapper;

    @Autowired
    private VideoFileMapper videoFileMapper;


    @Override
    public Map<String,Object> login() {

        Map<String,Object> map = new HashMap<>();

        String userName = Properties.getFzUserName();
        String passWord = Properties.getFzPassWord();
        Client fz = null;
        try {
            FZMap.clientTokenLock.readLock().lock();
            fz = (Client) FZMap.clientToken.get("FZ");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            FZMap.clientTokenLock.readLock().unlock();
        }
        if (fz == null) {
          /*  Client client = this.getClient();
            if(client==null){

                map.put("status", 1);
                map.put("message", "操作失败获取连接失败");
                return map;
            }
            fz=client;*/

            map.put("message", "没有连接到防灾服务器");
            log.error("----------没有连接到防灾服务器----------");
            return map;
        }
        //获取公钥
        String keyPair = CXFUtil.getKeyPair(fz);
        //重置上下文
        Map<String,String> map1 = new HashMap<>();
        map1.put("userName", userName);
        map1.put("passWord", passWord);
        //请求授权
        String token = CXFUtil.auth(fz, keyPair, map1);
        if (token != null && !"".equals(token)) {

            try {
                FZMap.loginTokenLock.writeLock().lock();
                FZMap.loginToken.put("FZ", token);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                FZMap.loginTokenLock.writeLock().unlock();
            }

            map.put("status", 0);
            map.put("message", "登陆成功");

        } else {
            map.put("status", 1);
            map.put("message", "登陆失败没有获取到token");
        }
        log.info("登陆到防灾系统 status " + map.get("status"));
        return map;
    }

    @Override
    public Client getClient() {
        Client fz = null;
        Client connection = null;
        try {
            FZMap.clientTokenLock.readLock().lock();
            fz = (Client) FZMap.clientToken.get("FZ");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            FZMap.clientTokenLock.readLock().unlock();
        }

        if (fz == null) {

            String url = Properties.getFzUrl();
            log.info("防灾url：" + url);
            connection = CXFUtil.getConnection(url);
            if (connection != null) {
                try {
                    FZMap.clientTokenLock.writeLock().lock();
                    FZMap.clientToken.put("FZ", connection);

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    FZMap.clientTokenLock.writeLock().unlock();
                }
            }
        }


        return connection;
    }


    @Override
    public Map<String,Object> getFZAlarm(Map<String,Object> mapR) {
        Map<String,Object> map = new HashMap<>();
        Client fz = null;
        try {
            FZMap.clientTokenLock.readLock().lock();
            fz = (Client) FZMap.clientToken.get("FZ");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            FZMap.clientTokenLock.readLock().unlock();
        }

        if (fz == null) {
           /* Client client = this.getClient();
            if(client==null){

                map.put("status", 1);
                map.put("message", "操作失败获取连接失败");
                log.error("连接防灾系统操作失败 获取连接失败");
                return map;
            }
            fz=client;*/
            map.put("message", "没有连接到防灾服务器");
            log.error("----------没有连接到防灾服务器----------");
            return map;
        }

        //获取公钥
        String keyPair = CXFUtil.getKeyPair(fz);
        String str = null;
        try {
            FZMap.loginTokenLock.readLock().lock();
            str = FZMap.loginToken.get("FZ");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            FZMap.loginTokenLock.readLock().unlock();
        }
        if (str != null && !"".equals(str)) {
            mapR.put("token", str);
        } else {
            this.login();
            map.put("status", 1);
            map.put("message", "没有登陆，请重试");

            return map;
        }

        //请求授权
        String s = CXFUtil.bigDataAlarm(fz, keyPair, mapR);


        if (s != null && !"".equals(s)) {
            map.put("status", 0);
            map.put("message", "获取数据成功");
            map.put("data", s);
            HashMap<String,Object> mapObj1 = JSONObject.parseObject(s, HashMap.class);
            if ("1".equals(mapObj1.get("code"))) {
                List<Object> list = JSONObject.parseArray(mapObj1.get("message").toString(), Object.class);
                for (int i = 0; i < list.size(); i++) {
                    List<Object> list1 = JSONObject.parseArray(list.get(i)
                            .toString(), Object.class);
                    if (i == 0) {
                        for (Object o : list1) {
                            HashMap<String,Object> mapObj = JSONObject.parseObject(o.toString(), HashMap.class);
                            NewWindSpeedAlarm alarm = new NewWindSpeedAlarm();
                            alarm.setId((String) mapObj.get("id"));

                            List<NewWindSpeedAlarm> select = windSpeedAlarmMapper.select(alarm);
                            if (select.size() > 0) {
                                if (!select.get(0).getAlarmstatus().equals((String) mapObj.get("alarmStatus"))) {
                                    alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                                    alarm.setEndtime((String) mapObj.get("endTime"));
                                    windSpeedAlarmMapper.updateByPrimaryKeySelective(alarm);
                                }
                                continue;
                            }
                            alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                            alarm.setActualsnowdepth((String) mapObj.get("actualSnowDepth"));
                            alarm.setAlarmbasic((String) mapObj.get("alarmBasic"));
                            alarm.setAlarmconfirmtime((String) mapObj.get("alarmConfirmTime"));
                            alarm.setAlarmflag((String) mapObj.get("alarmFlag"));
                            alarm.setAlarmlevel((String) mapObj.get("alarmLevel"));
                            alarm.setAlarmresumeconfirmtime((String) mapObj.get("alarmResumeConfirmTime"));
                            alarm.setAlarmresumetime((String) mapObj.get("alarmResumeTime"));
                            alarm.setAlarmvalue((String) mapObj.get("alarmValue"));
                            alarm.setArea((String) mapObj.get("area"));
                            alarm.setStarttime((String) mapObj.get("startTime"));
                            alarm.setBenchmarksnowdepth((String) mapObj.get("benchMarkSnowDepth"));
                            alarm.setBureaucode((String) mapObj.get("bureauCode"));
                            alarm.setBureauname((String) mapObj.get("bureauName"));
                            alarm.setContinuousrainfall((String) mapObj.get("continuousRainfall"));
                            alarm.setCumulativerainfall((String) mapObj.get("cumulativeRainfall"));
                            alarm.setDayrainfall((String) mapObj.get("dayRainfall"));
                            alarm.setDbcreatetime((String) mapObj.get("dbCreatetime"));
                            alarm.setDescription((String) mapObj.get("description"));
                            alarm.setDevicetype((String) mapObj.get("deviceType"));
                            alarm.setDowntrackcontrolarea((String) mapObj.get("downTrackControlArea"));
                            alarm.setDowntrackcontrolnum((String) mapObj.get("downTrackControlNum"));
                            alarm.setDtype((String) mapObj.get("dtype"));
                            alarm.setDvalue((String) mapObj.get("dvalue"));
                            alarm.setDyss((String) mapObj.get("dyss"));
                            alarm.setEndtime((String) mapObj.get("endTime"));
                            alarm.setKm((String) mapObj.get("km"));
                            alarm.setHandleaction((String) mapObj.get("handleAction"));
                            alarm.setHourrainfall((String) mapObj.get("hourRainfall"));
                            alarm.setLimitsegment((String) mapObj.get("limitSegment"));
                            alarm.setLimitspeed((String) mapObj.get("limitSpeed"));
                            alarm.setLinecode((String) mapObj.get("lineCode"));
                            alarm.setLinename((String) mapObj.get("lineName"));
                            alarm.setMonitorpoint((String) mapObj.get("monitorPoint"));
                            alarm.setMonitortime((String) mapObj.get("monitorTime"));
                            alarm.setMonitorunit((String) mapObj.get("monitorUnit"));
                            alarm.setMonitoringtime((String) mapObj.get("monitoringtime"));
                            alarm.setProsegmenttype((String) mapObj.get("prosegmentType"));
                            alarm.setRainfallintensity((String) mapObj.get("rainfallIntensity"));
                            alarm.setSegment((String) mapObj.get("segment"));
                            alarm.setSegmenttype((String) mapObj.get("segmentType"));
                            alarm.setSensorcode((String) mapObj.get("sensorCode"));
                            alarm.setSnowdepth((String) mapObj.get("snowDepth"));
                            alarm.setTenminuterainfall((String) mapObj.get("tenMinuteRainfall"));
                            alarm.setTweentyfourhourrainfall((String) mapObj.get("tweentyFourHourRainfall"));
                            alarm.setUptrackcontrolarea((String) mapObj.get("upTrackControlArea"));
                            alarm.setUptrackcontrolnum((String) mapObj.get("upTrackControlNum"));
                            alarm.setWinddirection((String) mapObj.get("windDirection"));
                            alarm.setWinddirection1((String) mapObj.get("windDirection1"));
                            alarm.setWinddirection2((String) mapObj.get("windDirection2"));
                            alarm.setWindspeed((String) mapObj.get("windSpeed"));
                            alarm.setWindspeed1((String) mapObj.get("windSpeed1"));
                            alarm.setWindspeed2((String) mapObj.get("windSpeed2"));
                            alarm.setXyes((String) mapObj.get("xyes"));
                            alarm.setXyesw((String) mapObj.get("xyesw"));
                            alarm.setXys((String) mapObj.get("xys"));
                            alarm.setXyss((String) mapObj.get("xyss"));
                            alarm.setXysw((String) mapObj.get("xysw"));
                            alarm.setXyw((String) mapObj.get("xyw"));
                            alarm.setVideostatus("0");
                            alarm.setId((String) mapObj.get("id"));
                            windSpeedAlarmMapper.insertSelective(alarm);
                        }

                    } else if (i == 1) {
                        for (Object o : list1) {
                            HashMap<String,Object> mapObj = JSONObject.parseObject(o.toString(), HashMap.class);
                            NewRainAlarm alarm = new NewRainAlarm();
                            alarm.setId((String) mapObj.get("id"));
                            List<NewRainAlarm> select = newRainAlarmMapper.select(alarm);
                            if (select.size() > 0) {
                                if (!select.get(0).getAlarmstatus().equals((String) mapObj.get("alarmStatus"))) {
                                    alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                                    alarm.setEndtime((String) mapObj.get("endTime"));
                                    newRainAlarmMapper.updateByPrimaryKeySelective(alarm);
                                }
                                continue;
                            }
                            alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                            alarm.setActualsnowdepth((String) mapObj.get("actualSnowDepth"));
                            alarm.setAlarmbasic((String) mapObj.get("alarmBasic"));
                            alarm.setAlarmconfirmtime((String) mapObj.get("alarmConfirmTime"));
                            alarm.setAlarmflag((String) mapObj.get("alarmFlag"));
                            alarm.setAlarmlevel((String) mapObj.get("alarmLevel"));
                            alarm.setAlarmresumeconfirmtime((String) mapObj.get("alarmResumeConfirmTime"));
                            alarm.setAlarmresumetime((String) mapObj.get("alarmResumeTime"));
                            alarm.setAlarmvalue((String) mapObj.get("alarmValue"));
                            alarm.setArea((String) mapObj.get("area"));
                            alarm.setBenchmarksnowdepth((String) mapObj.get("benchMarkSnowDepth"));
                            alarm.setBureaucode((String) mapObj.get("bureauCode"));
                            alarm.setBureauname((String) mapObj.get("bureauName"));
                            alarm.setContinuerainfall((String) mapObj.get("continueRainfall"));
                            alarm.setContinuousrainfall((String) mapObj.get("continuousRainfall"));
                            alarm.setCumulativerainfall((String) mapObj.get("cumulativeRainfall"));
                            alarm.setDayrainfall((String) mapObj.get("dayRainFall"));
                            alarm.setDayrainfall2((String) mapObj.get("dayRainfall"));
                            alarm.setKm((String) mapObj.get("km"));
                            alarm.setStarttime((String) mapObj.get("startTime"));
                            alarm.setDbcreatetime((String) mapObj.get("dbCreatetime"));
                            alarm.setDescription((String) mapObj.get("description"));
                            alarm.setDevicetype((String) mapObj.get("deviceType"));
                            alarm.setDowntrackcontrolarea((String) mapObj.get("downTrackControlArea"));
                            alarm.setDowntrackcontrolnum((String) mapObj.get("downTrackControlNum"));
                            alarm.setDtype((String) mapObj.get("dtype"));
                            alarm.setDvalue((String) mapObj.get("dvalue"));
                            alarm.setEndtime((String) mapObj.get("endTime"));
                            alarm.setHandleaction((String) mapObj.get("handleAction"));
                            alarm.setHourrainfall((String) mapObj.get("hourRainfall"));
                            alarm.setLimitsegment((String) mapObj.get("limitSegment"));
                            alarm.setLimitspeed((String) mapObj.get("limitSpeed"));
                            alarm.setLinecode((String) mapObj.get("lineCode"));
                            alarm.setLinename((String) mapObj.get("lineName"));
                            alarm.setMonitorpoint((String) mapObj.get("monitorPoint"));
                            alarm.setMonitortime((String) mapObj.get("monitorTime"));
                            alarm.setMonitorunit((String) mapObj.get("monitorUnit"));
                            alarm.setMonitoringtime((String) mapObj.get("monitoringtime"));
                            alarm.setProsegmenttype((String) mapObj.get("prosegmentType"));
                            alarm.setRainaccumulation((String) mapObj.get("rainAccumulation"));
                            alarm.setRainfallintensity((String) mapObj.get("rainFallIntensity"));
                            alarm.setRainfallintensity2((String) mapObj.get("rainfallIntensity"));
                            alarm.setSegment((String) mapObj.get("segment"));
                            alarm.setSegmenttype((String) mapObj.get("segmentType"));
                            alarm.setSensorcode((String) mapObj.get("sensorCode"));
                            alarm.setSnowdepth((String) mapObj.get("snowDepth"));
                            alarm.setTenminuterainfall((String) mapObj.get("tenMinuteRainfall"));
                            alarm.setTenminutefall((String) mapObj.get("tenminuteFall"));
                            alarm.setTweentyfourhourrainfall((String) mapObj.get("tweentyFourHourRainfall"));
                            alarm.setUptrackcontrolarea((String) mapObj.get("upTrackControlArea"));
                            alarm.setUptrackcontrolnum((String) mapObj.get("upTrackControlNum"));
                            alarm.setWinddirection((String) mapObj.get("windDirection"));
                            alarm.setWinddirection1((String) mapObj.get("windDirection1"));
                            alarm.setWinddirection2((String) mapObj.get("windDirection2"));
                            alarm.setWindspeed((String) mapObj.get("windSpeed"));
                            alarm.setWindspeed1((String) mapObj.get("windSpeed1"));
                            alarm.setWindspeed2((String) mapObj.get("windSpeed2"));
                            alarm.setVideostatus("0");
                            alarm.setId((String) mapObj.get("id"));
                            newRainAlarmMapper.insertSelective(alarm);
                        }
                    } else if (i == 2) {
                        for (Object o : list1) {
                            HashMap<String,Object> mapObj = JSONObject.parseObject(o.toString(), HashMap.class);
                            NewSnowAlarm alarm = new NewSnowAlarm();
                            alarm.setId((String) mapObj.get("id"));
                            List<NewSnowAlarm> select = newSnowAlarmMapper.select(alarm);
                            if (select.size() > 0) {
                                if (!select.get(0).getAlarmstatus().equals((String) mapObj.get("alarmStatus"))) {
                                    alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                                    alarm.setEndtime((String) mapObj.get("endTime"));
                                    newSnowAlarmMapper.updateByPrimaryKeySelective(alarm);
                                }
                                continue;
                            }
                            alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                            alarm.setActualsnowdepth((String) mapObj.get("actualSnowDepth"));
                            alarm.setAlarmbasic((String) mapObj.get("alarmBasic"));
                            alarm.setAlarmconfirmtime((String) mapObj.get("alarmConfirmTime"));
                            alarm.setAlarmflag((String) mapObj.get("alarmFlag"));
                            alarm.setAlarmlevel((String) mapObj.get("alarmLevel"));
                            alarm.setAlarmresumeconfirmtime((String) mapObj.get("alarmResumeConfirmTime"));
                            alarm.setAlarmresumetime((String) mapObj.get("alarmResumeTime"));
                            alarm.setAlarmvalue((String) mapObj.get("alarmValue"));
                            alarm.setArea((String) mapObj.get("area"));
                            alarm.setKm((String) mapObj.get("km"));
                            alarm.setStarttime((String) mapObj.get("startTime"));
                            alarm.setBenchmarksnowdepth((String) mapObj.get("benchMarkSnowDepth"));
                            alarm.setBureaucode((String) mapObj.get("bureauCode"));
                            alarm.setBureauname((String) mapObj.get("bureauName"));
                            alarm.setContinuousrainfall((String) mapObj.get("continuousRainfall"));
                            alarm.setCumulativerainfall((String) mapObj.get("cumulativeRainfall"));
                            alarm.setDayrainfall((String) mapObj.get("dayRainFall"));
                            alarm.setDbcreatetime((String) mapObj.get("dbCreatetime"));
                            alarm.setDescription((String) mapObj.get("description"));
                            alarm.setDevicetype((String) mapObj.get("deviceType"));
                            alarm.setDowntrackcontrolarea((String) mapObj.get("downTrackControlArea"));
                            alarm.setDowntrackcontrolnum((String) mapObj.get("downTrackControlNum"));
                            alarm.setDtype((String) mapObj.get("dtype"));
                            alarm.setDvalue((String) mapObj.get("dvalue"));
                            alarm.setEndtime((String) mapObj.get("endTime"));
                            alarm.setHandleaction((String) mapObj.get("handleAction"));
                            alarm.setHourrainfall((String) mapObj.get("hourRainfall"));
                            alarm.setLimitsegment((String) mapObj.get("limitSegment"));
                            alarm.setLimitspeed((String) mapObj.get("limitSpeed"));
                            alarm.setLinecode((String) mapObj.get("lineCode"));
                            alarm.setLinename((String) mapObj.get("lineName"));
                            alarm.setMonitorpoint((String) mapObj.get("monitorPoint"));
                            alarm.setMonitortime((String) mapObj.get("monitorTime"));
                            alarm.setMonitorunit((String) mapObj.get("monitorUnit"));
                            alarm.setMonitoringtime((String) mapObj.get("monitoringtime"));
                            alarm.setProsegmenttype((String) mapObj.get("prosegmentType"));
                            alarm.setRainfallintensity((String) mapObj.get("rainFallIntensity"));
                            alarm.setSegment((String) mapObj.get("segment"));
                            alarm.setSegmenttype((String) mapObj.get("segmentType"));
                            alarm.setSensorcode((String) mapObj.get("sensorCode"));
                            alarm.setSnowdepth((String) mapObj.get("snowDepth"));
                            alarm.setSnowdepthactual((String) mapObj.get("snowDepthActual"));
                            alarm.setSnowdepthbenchmark((String) mapObj.get("snowDepthBenchmark"));
                            alarm.setTenminuterainfall((String) mapObj.get("tenMinuteRainfall"));
                            alarm.setTweentyfourhourrainfall((String) mapObj.get("tweentyFourHourRainfall"));
                            alarm.setUptrackcontrolarea((String) mapObj.get("upTrackControlArea"));
                            alarm.setUptrackcontrolnum((String) mapObj.get("upTrackControlNum"));
                            alarm.setWinddirection((String) mapObj.get("windDirection"));
                            alarm.setWinddirection1((String) mapObj.get("windDirection1"));
                            alarm.setWinddirection2((String) mapObj.get("windDirection2"));
                            alarm.setWindspeed((String) mapObj.get("windSpeed"));
                            alarm.setWindspeed1((String) mapObj.get("windSpeed1"));
                            alarm.setWindspeed2((String) mapObj.get("windSpeed2"));
                            alarm.setVideostatus("0");
                            alarm.setId((String) mapObj.get("id"));
                            newSnowAlarmMapper.insertSelective(alarm);
                        }
                    } else if (i == 3) {
                        for (Object o : list1) {
                            HashMap<String,Object> mapObj = JSONObject.parseObject(o.toString(), HashMap.class);
                            NewForeignBodyAlarm alarm = new NewForeignBodyAlarm();
                            alarm.setId((String) mapObj.get("id"));
                            List<NewForeignBodyAlarm> select = newForeignBodyAlarmMapper.select(alarm);
                            if (select.size() > 0) {
                                if (!select.get(0).getAlarmstatus().equals((String) mapObj.get("alarmStatus"))) {
                                    alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                                    alarm.setEndtime((String) mapObj.get("endTime"));
                                    newForeignBodyAlarmMapper.updateByPrimaryKeySelective(alarm);
                                }
                                continue;
                            }
                            alarm.setAlarmstatus((String) mapObj.get("alarmStatus"));
                            alarm.setActualsnowdepth((String) mapObj.get("actualSnowDepth"));
                            alarm.setAlarmbasic((String) mapObj.get("alarmBasic"));
                            alarm.setAlarmconfirmtime((String) mapObj.get("alarmConfirmTime"));
                            alarm.setAlarmflag((String) mapObj.get("alarmFlag"));
                            alarm.setAlarmlevel((String) mapObj.get("alarmLevel"));
                            alarm.setAlarmresumeconfirmtime((String) mapObj.get("alarmResumeConfirmTime"));
                            alarm.setAlarmresumetime((String) mapObj.get("alarmResumeTime"));
                            alarm.setAlarmvalue((String) mapObj.get("alarmValue"));
                            alarm.setArea((String) mapObj.get("area"));
                            alarm.setKm((String) mapObj.get("km"));
                            alarm.setStarttime((String) mapObj.get("startTime"));
                            alarm.setBenchmarksnowdepth((String) mapObj.get("benchMarkSnowDepth"));
                            alarm.setBureaucode((String) mapObj.get("bureauCode"));
                            alarm.setBureauname((String) mapObj.get("bureauName"));
                            alarm.setContinuousrainfall((String) mapObj.get("continuousRainfall"));
                            alarm.setCumulativerainfall((String) mapObj.get("cumulativeRainfall"));
                            alarm.setDayrainfall((String) mapObj.get("dayRainFall"));
                            alarm.setDbcreatetime((String) mapObj.get("dbCreatetime"));
                            alarm.setDescription((String) mapObj.get("description"));
                            alarm.setDevicetype((String) mapObj.get("deviceType"));
                            alarm.setDowntrackcontrolarea((String) mapObj.get("downTrackControlArea"));
                            alarm.setDowntrackcontrolnum((String) mapObj.get("downTrackControlNum"));
                            alarm.setDtype((String) mapObj.get("dtype"));
                            alarm.setDvalue((String) mapObj.get("dvalue"));
                            alarm.setEndtime((String) mapObj.get("endTime"));
                            alarm.setHandleaction((String) mapObj.get("handleAction"));
                            alarm.setHourrainfall((String) mapObj.get("hourRainfall"));
                            alarm.setIkm((String) mapObj.get("ikm"));
                            alarm.setImeter((String) mapObj.get("imeter"));
                            alarm.setLimitsegment((String) mapObj.get("limitSegment"));
                            alarm.setLimitspeed((String) mapObj.get("limitSpeed"));
                            alarm.setLinecode((String) mapObj.get("lineCode"));
                            alarm.setLinename((String) mapObj.get("lineName"));
                            alarm.setMonitorpoint((String) mapObj.get("monitorPoint"));
                            alarm.setMonitortime((String) mapObj.get("monitorTime"));
                            alarm.setMonitorunit((String) mapObj.get("monitorUnit"));
                            alarm.setMonitoringpointstate((String) mapObj.get("monitoringPointstate"));
                            alarm.setMonitoringtime((String) mapObj.get("monitoringtime"));
                            alarm.setProsegmenttype((String) mapObj.get("prosegmentType"));
                            alarm.setRailwayadministrator((String) mapObj.get("railwayadministrator"));
                            alarm.setRainfallintensity((String) mapObj.get("rainFallIntensity"));
                            alarm.setSegment((String) mapObj.get("segment"));
                            alarm.setSegmenttype((String) mapObj.get("segmentType"));
                            alarm.setSensorcode((String) mapObj.get("sensorCode"));
                            alarm.setSnowdepth((String) mapObj.get("snowDepth"));
                            alarm.setTenminuterainfall((String) mapObj.get("tenMinuteRainfall"));
                            alarm.setTweentyfourhourrainfall((String) mapObj.get("tweentyFourHourRainfall"));
                            alarm.setUptrackcontrolarea((String) mapObj.get("upTrackControlArea"));
                            alarm.setUptrackcontrolnum((String) mapObj.get("upTrackControlNum"));
                            alarm.setWinddirection((String) mapObj.get("windDirection"));
                            alarm.setWinddirection1((String) mapObj.get("windDirection1"));
                            alarm.setWinddirection2((String) mapObj.get("windDirection2"));
                            alarm.setWindspeed((String) mapObj.get("windSpeed"));
                            alarm.setWindspeed1((String) mapObj.get("windSpeed1"));
                            alarm.setWindspeed2((String) mapObj.get("windSpeed2"));
                            alarm.setVideostatus("0");
                            alarm.setId((String) mapObj.get("id"));
                            newForeignBodyAlarmMapper.insertSelective(alarm);
                        }
                    }
                }
            }
        } else {
            map.put("status", 1);
            map.put("message", "获取数据失败");
        }
        log.info("getFZAlarm查询防灾系统告警 status " + map.get("status"));
        return map;
    }

    @Override
    public void addFZTask() {
        {
            try {
                NewWindSpeedAlarm alarm = new NewWindSpeedAlarm();
                alarm.setAlarmstatus("0");
                alarm.setVideostatus("0");
                List<NewWindSpeedAlarm> select = windSpeedAlarmMapper.select(alarm);
                for (NewWindSpeedAlarm newWindSpeedAlarm : select) {
                    newWindSpeedAlarm.setVideostatus("1");
                    windSpeedAlarmMapper.updateByPrimaryKeySelective(newWindSpeedAlarm);
               /* if(newWindSpeedAlarm.getStarttime()!=null&&newWindSpeedAlarm.getAlarmresumetime()==null){
                    try {
                        Date date = DateUtils.parseDate(newWindSpeedAlarm.getStarttime(), "yyyy-MM-dd HH:mm:ss");
                        newWindSpeedAlarm.setEndtime(DateUtils.getDate(new Date(date.getTime()+60*1000),"yyyy-MM-dd HH:mm:ss"));
                    } catch (Exception ex) {
                        log.error(ex.getMessage(),ex);

                    }
                }*/
                    if (newWindSpeedAlarm.getStarttime() != null && newWindSpeedAlarm.getAlarmresumetime() != null && newWindSpeedAlarm.getKm() != null) {


                        addVideoTask(newWindSpeedAlarm.getId(), "windSpeedAlarm", newWindSpeedAlarm.getKm(), newWindSpeedAlarm.getStarttime(), newWindSpeedAlarm.getAlarmresumetime());
                    }
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);

            }
        }
        {
            try {
                NewRainAlarm alarm = new NewRainAlarm();
                alarm.setAlarmstatus("0");
                alarm.setVideostatus("0");
                List<NewRainAlarm> select = newRainAlarmMapper.select(alarm);
                for (NewRainAlarm newWindSpeedAlarm : select) {
                    newWindSpeedAlarm.setVideostatus("1");
                    newRainAlarmMapper.updateByPrimaryKeySelective(newWindSpeedAlarm);
                    if (newWindSpeedAlarm.getStarttime() != null && newWindSpeedAlarm.getAlarmresumetime() != null && newWindSpeedAlarm.getKm() != null) {

                        addVideoTask(newWindSpeedAlarm.getId(), "rainAlarm", newWindSpeedAlarm.getKm(), newWindSpeedAlarm.getStarttime(), newWindSpeedAlarm.getAlarmresumetime());
                    }
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);

            }
        }
        {
            try {
                NewSnowAlarm alarm = new NewSnowAlarm();
                alarm.setAlarmstatus("0");
                alarm.setVideostatus("0");
                List<NewSnowAlarm> select = newSnowAlarmMapper.select(alarm);
                for (NewSnowAlarm newWindSpeedAlarm : select) {
                    newWindSpeedAlarm.setVideostatus("1");
                    newSnowAlarmMapper.updateByPrimaryKeySelective(newWindSpeedAlarm);
                    if (newWindSpeedAlarm.getStarttime() != null && newWindSpeedAlarm.getAlarmresumetime() != null && newWindSpeedAlarm.getKm() != null) {

                        addVideoTask(newWindSpeedAlarm.getId(), "snowAlarm", newWindSpeedAlarm.getKm(), newWindSpeedAlarm.getStarttime(), newWindSpeedAlarm.getAlarmresumetime());
                    }
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);

            }
        }
        {
            try {
                NewForeignBodyAlarm alarm = new NewForeignBodyAlarm();
                alarm.setAlarmstatus("0");
                alarm.setVideostatus("0");
                List<NewForeignBodyAlarm> select = newForeignBodyAlarmMapper.select(alarm);
                for (NewForeignBodyAlarm newWindSpeedAlarm : select) {
                    newWindSpeedAlarm.setVideostatus("1");
                    newForeignBodyAlarmMapper.updateByPrimaryKeySelective(newWindSpeedAlarm);
                    if (newWindSpeedAlarm.getStarttime() != null && newWindSpeedAlarm.getAlarmresumetime() != null && newWindSpeedAlarm.getKm() != null) {
                        addVideoTask(newWindSpeedAlarm.getId(), "foreignBodyAlarm", newWindSpeedAlarm.getKm(), newWindSpeedAlarm.getStarttime(), newWindSpeedAlarm.getAlarmresumetime());
                    }
                }
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);

            }
        }
    }

    public int addVideoTask(String alarmId, String alarmName, String k, String startTime, String endTime) {
       /* Example example = new Example(VideoFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("kssj", startTime);
        criteria.andEqualTo("jssj", endTime);
        List<VideoFile> videoFiles = videoFileMapper.selectByExample(example);
        for (VideoFile videoFile :videoFiles) {
            if(videoFile.getAlarmId()!=null&&videoFile.getAlarmId().equals(alarmId)&&videoFile.getVideoType()!=null&&videoFile.getVideoType().equals(alarmName)){
                return -1;
            }
        }*/
        List<VideoFile> videoFileList = new ArrayList<>();
        Camera camera = cameraMapper.getMinVideoCode(Integer.parseInt(k));
        if (camera != null && camera.getDeviceId() != null) {

            VideoFile videoFile = new VideoFile();
            videoFile.setId(UUIDHelper.getUUIDStr());
            videoFile.setIpcid(camera.getDeviceId());
            videoFile.setKssj(startTime);
            videoFile.setJssj(endTime);
            videoFile.setStatus("0");
            videoFile.setAlarmId(alarmId);
            videoFile.setVideoType(alarmName);
            videoFileList.add(videoFile);
        }
        Camera camera1 = cameraMapper.getMxnVideoCode(Integer.parseInt(k));
        if (camera1 != null && camera1.getDeviceId() != null) {
            VideoFile videoFile = new VideoFile();
            videoFile.setId(UUIDHelper.getUUIDStr());
            videoFile.setIpcid(camera1.getDeviceId());
            videoFile.setKssj(startTime);
            videoFile.setJssj(endTime);
            videoFile.setStatus("0");
            videoFile.setAlarmId(alarmId);
            videoFile.setVideoType(alarmName);
            videoFileList.add(videoFile);
        }
        for (VideoFile videoFile : videoFileList) {
            Example example = new Example(VideoFile.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("ipcid", videoFile.getIpcid());
            criteria.andEqualTo("alarmId", videoFile.getAlarmId());
            criteria.andEqualTo("videoType", videoFile.getVideoType());
            List<VideoFile> videoFiles = videoFileMapper.selectByExample(example);
            if (videoFiles.size() > 0) {
                continue;
            }
            int i1 = videoFileMapper.insertSelective(videoFile);//添加到任务表里
        }
        return 1;
    }

    @Override
    public Map<String,Object> getFzAlarm(String alarmType, String km, String startTime, String endTime, String pageIndex, String pageSize) {
        Map<String,Object> map = new HashMap<>();
        try {

            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<HashMap> pageInfo = new PageInfo<>(windSpeedAlarmMapper.getFzAlarmMap(alarmType, km, startTime, endTime));

            map.put("status", "0");
            map.put("data", pageInfo);
            map.put("message", "查询成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }
        log.info("getFzAlarm 获取分页防灾告警信息 status " + map.get("status"));
        return map;
    }
}
