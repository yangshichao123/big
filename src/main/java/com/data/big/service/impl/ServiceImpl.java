package com.data.big.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.big.mapper.*;
import com.data.big.service.Service;
import com.data.big.task.KeepTask;
import com.data.big.util.*;
import com.data.big.model.*;
import com.data.big.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.util.*;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private CamerainfoMapper camerainfoMapper;
    @Autowired
    private NodeinfoMapper nodeinfoMapper;
    @Autowired
    private ServerinfoMapper serverinfoMapper;
    @Autowired
    private DiskinfoMapper diskinfoMapper;
    @Autowired
    private SwitchinfoMapper switchinfoMapper;
    @Autowired
    private KeepTask keepTask;
    @Autowired
    private CamerastatusMapper camerastatusMapper;
    @Autowired
    private CameraalarmMapper cameraalarmMapper;
    @Autowired
    private LogRestMapper logRestMapper;
    @Autowired
    private ServerstatusMapper serverstatusMapper;
    @Autowired
    private DiskstatusMapper diskstatusMapper;
    @Autowired
    private SwitchstatusMapper switchstatusMapper;
    @Autowired
    private ServeralarmMapper serveralarmMapper;
    @Autowired
    private DiskalarmMapper diskalarmMapper;
    @Autowired
    private SwitchalarmMapper switchalarmMapper;
    @Autowired
    private IvsalarmMapper ivsalarmMapper;
    @Autowired
    private VideoFileMapper videoFileMapper;
    @Autowired
    private AnBao3Mapper anBao3Mapper;
    @Autowired
    private QueryTaskMapper queryTaskMapper;
    @Autowired
    private MethodTypeMapper methodTypeMapper;
    // 日志记录器
    private static final Logger logger = LogManager.getLogger(ServiceImpl.class);

    @Override
    public Map GetCameraInfo() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Camerainfo> camerMap = new HashMap<>();
        Map<String,Camerainfo> deleteCamerMap = new HashMap<>();
        List<Camerainfo> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSnedUrl();
        String data = HttpClientUt.doPost(snedUrl, "");
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    String deviceName = (String) o.get("device_name");
                    Camerainfo camer = new Camerainfo();
                    camer.setAffiliation((String) o.get("affiliation"));
                    camer.setAssociatedLine((String) o.get("associated_line"));
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setDeviceName(deviceName);
                    if (deviceName != null) {
                        String[] strs = deviceName.split(" ");
                        if (strs.length > 1 && strs[1].contains("K")) {
                            camer.setkMark(strs[1]);
                        }
                        if (deviceName.contains("[") && deviceName.contains("]")) {
                            String type = deviceName.substring(deviceName.indexOf("[") + 1, deviceName.indexOf("]"));
                            camer.setDeviceType(type);
                        }

                    }
                    //camer.setDeviceType((String)o.get("device_type"));
                    camer.setInstallTime((String) o.get("install_time"));
                    camer.setIpAddr((String) o.get("ip_addr"));
                    camer.setIpPort((String) o.get("ip_port"));
                    //camer.setkMark((String)o.get("k_mark"));
                    camer.setManufacturer((String) o.get("manufacturer"));
                    camer.setNodeId((String) o.get("node_id"));
                    camer.setPassword((String) o.get("password"));
                    camer.setUpDown((String) o.get("up_down"));
                    camer.setLongitude((String) o.get("longitude"));
                    camer.setUsername((String) o.get("username"));
                    camer.setLatitude((String) o.get("latitude"));
                    camer.setRegion((String) o.get("region"));
                    camer.setLocationType((String) o.get("Location_type"));
                    camer.setLocationNum((String) o.get("Location_num"));
                    camer.setArea((String) o.get("area"));
                    listCamer.add(camer);
                }

                List<Camerainfo> camerainfos = camerainfoMapper.selectAll();
                for (Camerainfo camerainfo : camerainfos) {
                    camerMap.put(camerainfo.getDeviceId(), camerainfo);
                }
                for (Camerainfo camerainfo : listCamer) {
                    deleteCamerMap.put(camerainfo.getDeviceId(), camerainfo);
                    boolean contains = camerMap.containsKey(camerainfo.getDeviceId());
                    if (contains) {
                        Example example = new Example(Camerainfo.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("deviceId", camerainfo.getDeviceId());
                        camerainfoMapper.updateByExample(camerainfo, example);
                    } else {
                        camerainfoMapper.insert(camerainfo);
                    }
                }
                for (Camerainfo camerainfo : camerainfos) {
                    boolean contains = deleteCamerMap.containsKey(camerainfo.getDeviceId());
                    if (!contains) {
                        camerainfoMapper.delete(camerainfo);
                    }
                }

                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询摄像机资产信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询摄像机资产信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }

        LogRest log = new LogRest();
        log.setFunname("GetCameraInfo");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin("");
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map GetNodeInfo() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Nodeinfo> camerMap = new HashMap<>();
        Map<String,Nodeinfo> deleteCamerMap = new HashMap<>();
        List<Nodeinfo> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendGetNodeInfo();
        String data = HttpClientUt.doPost(snedUrl, "");
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else {

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Nodeinfo camer = new Nodeinfo();
                    camer.setNodeId((String) o.get("node_id"));
                    camer.setNodeName((String) o.get("node_name"));
                    camer.setNodeParent((String) o.get("node_parent"));
                    listCamer.add(camer);
                }

                List<Nodeinfo> camerainfos = nodeinfoMapper.selectAll();
                for (Nodeinfo camerainfo : camerainfos) {
                    camerMap.put(camerainfo.getNodeId(), camerainfo);
                }
                for (Nodeinfo camerainfo : listCamer) {
                    deleteCamerMap.put(camerainfo.getNodeId(), camerainfo);
                    boolean contains = camerMap.containsKey(camerainfo.getNodeId());
                    if (contains) {
                        Example example = new Example(Nodeinfo.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("nodeId", camerainfo.getNodeId());
                        nodeinfoMapper.updateByExample(camerainfo, example);
                    } else {
                        nodeinfoMapper.insert(camerainfo);
                    }
                }
                for (Nodeinfo camerainfo : camerainfos) {
                    boolean contains = deleteCamerMap.containsKey(camerainfo.getNodeId());
                    if (!contains) {
                        nodeinfoMapper.delete(camerainfo);
                    }
                }

                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询节点信息信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询节点信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetNodeInfo");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin("");
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map GetServerInfo() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Serverinfo> camerMap = new HashMap<>();
        Map<String,Serverinfo> deleteCamerMap = new HashMap<>();
        List<Serverinfo> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendGetServerInfo();
        String data = HttpClientUt.doPost(snedUrl, "");
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Serverinfo camer = new Serverinfo();
                    camer.setIpAddr((String) o.get("ip_addr"));
                    camer.setDeviceModel((String) o.get("device_model"));
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setDeviceName((String) o.get("device_name"));
                    camer.setDeviceType((String) o.get("device_type"));
                    camer.setLocation((String) o.get("location"));
                    camer.setManufacturer((String) o.get("manufacturer"));
                    listCamer.add(camer);
                }

                List<Serverinfo> camerainfos = serverinfoMapper.selectAll();
                for (Serverinfo camerainfo : camerainfos) {
                    camerMap.put(camerainfo.getDeviceId(), camerainfo);
                }
                for (Serverinfo camerainfo : listCamer) {
                    deleteCamerMap.put(camerainfo.getDeviceId(), camerainfo);
                    boolean contains = camerMap.containsKey(camerainfo.getDeviceId());
                    if (contains) {
                        Example example = new Example(Serverinfo.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("deviceId", camerainfo.getDeviceId());
                        serverinfoMapper.updateByExample(camerainfo, example);
                    } else {
                        serverinfoMapper.insert(camerainfo);
                    }
                }
                for (Serverinfo camerainfo : camerainfos) {
                    boolean contains = deleteCamerMap.containsKey(camerainfo.getDeviceId());
                    if (!contains) {
                        serverinfoMapper.delete(camerainfo);
                    }
                }

                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询服务器信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询服务器信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetServerInfo");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin("");
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map GetDiskInfo() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Diskinfo> camerMap = new HashMap<>();
        Map<String,Diskinfo> deleteCamerMap = new HashMap<>();
        List<Diskinfo> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendGetDiskInfo();
        String data = HttpClientUt.doPost(snedUrl, "");
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Diskinfo camer = new Diskinfo();
                    camer.setIpAddr((String) o.get("ip_addr"));
                    camer.setDeviceModel((String) o.get("device_model"));
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setDeviceName((String) o.get("device_name"));
                    camer.setDeviceType((String) o.get("device_type"));
                    camer.setLocation((String) o.get("location"));
                    camer.setManufacturer((String) o.get("manufacturer"));
                    camer.setCapacity((String) o.get("capacity"));
                    listCamer.add(camer);
                }

                List<Diskinfo> camerainfos = diskinfoMapper.selectAll();
                for (Diskinfo camerainfo : camerainfos) {
                    camerMap.put(camerainfo.getDeviceId(), camerainfo);
                }
                for (Diskinfo camerainfo : listCamer) {
                    deleteCamerMap.put(camerainfo.getDeviceId(), camerainfo);
                    boolean contains = camerMap.containsKey(camerainfo.getDeviceId());
                    if (contains) {
                        Example example = new Example(Diskinfo.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("deviceId", camerainfo.getDeviceId());
                        diskinfoMapper.updateByExample(camerainfo, example);
                    } else {
                        diskinfoMapper.insert(camerainfo);
                    }
                }
                for (Diskinfo camerainfo : camerainfos) {
                    boolean contains = deleteCamerMap.containsKey(camerainfo.getDeviceId());
                    if (!contains) {
                        diskinfoMapper.delete(camerainfo);
                    }
                }

                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询磁盘信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询磁盘信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetDiskInfo");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin("");
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map GetSwitchInfo() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Switchinfo> camerMap = new HashMap<>();
        Map<String,Switchinfo> deleteCamerMap = new HashMap<>();
        List<Switchinfo> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendGetSwitchInfo();
        String data = HttpClientUt.doPost(snedUrl, "");
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else {

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Switchinfo camer = new Switchinfo();
                    camer.setIpAddr((String) o.get("ip_addr"));
                    camer.setDeviceModel((String) o.get("device_model"));
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setDeviceName((String) o.get("device_name"));
                    camer.setDeviceType((String) o.get("device_type"));
                    camer.setLocation((String) o.get("location"));
                    camer.setManufacturer((String) o.get("manufacturer"));
                    listCamer.add(camer);
                }

                List<Switchinfo> camerainfos = switchinfoMapper.selectAll();
                for (Switchinfo camerainfo : camerainfos) {
                    camerMap.put(camerainfo.getDeviceId(), camerainfo);
                }
                for (Switchinfo camerainfo : listCamer) {
                    deleteCamerMap.put(camerainfo.getDeviceId(), camerainfo);
                    boolean contains = camerMap.containsKey(camerainfo.getDeviceId());
                    if (contains) {
                        Example example = new Example(Switchinfo.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("deviceId", camerainfo.getDeviceId());
                        switchinfoMapper.updateByExample(camerainfo, example);
                    } else {
                        switchinfoMapper.insert(camerainfo);
                    }
                }
                for (Switchinfo camerainfo : camerainfos) {
                    boolean contains = deleteCamerMap.containsKey(camerainfo.getDeviceId());
                    if (!contains) {
                        switchinfoMapper.delete(camerainfo);
                    }
                }

                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询交换机信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询交换机信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetSwitchInfo");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin("");
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public void executeTask(String beginTime, String endTime) {
        /*if ("".equals(beginTime) && "".equals(endTime)) {
            String isYesterday = Properties.getIsYesterday();
            if ("0".equals(isYesterday)) {
                keepTask.setSaKeepAlivePeriod(24 * 60 * 60);
                beginTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 00:00:00";
                endTime = DateUtils.getBeforeDate(1, "yyyy-MM-dd") + " 23:59:59";
            } else if ("1".equals(isYesterday)) {
                String hour = Properties.getHour();
                keepTask.setSaKeepAlivePeriod(Integer.parseInt(hour) * 60 * 60);
                beginTime = DateUtils.getBeforeDateTime(Integer.parseInt(hour), "yyyy-MM-dd HH") + ":00:00";
                endTime = DateUtils.getBeforeDateTime(1, "yyyy-MM-dd HH") + ":59:59";
            } else {
                logger.error("isYesterday 配置错误数据");
            }
        }*/
        this.GetCameraStatus(beginTime, endTime);
        this.GetServerStatus(beginTime, endTime);
        this.GeDiskStatus(beginTime, endTime);
        this.GetSwitchStatus(beginTime, endTime);
        this.GetCameraAlarm(beginTime, endTime);
        this.GetDiskAlarm(beginTime, endTime);
        this.GetIVSAlarm(beginTime, endTime);
        this.GetServerAlarm(beginTime, endTime);
        this.GetSwitchAlarm(beginTime, endTime);

    }

    @Override
    public Map<String,Object> GetCameraStatus(String startTime, String endTime) {
        Map<String,Object> map = new HashMap<>();
        String snedUrl = Properties.getSendVideoStatus();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", startTime);
        mapStr.put("endtime", endTime);
        logger.error("starttime " + startTime + "  endtime " + endTime);

        String data = HttpClientUt.doGetd(snedUrl, mapStr);
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Camerastatus camer = new Camerastatus();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setStatusTime((String) o.get("status_time"));
                    camer.setOnlineStatus((String) o.get("online_status"));
                    camerastatusMapper.insert(camer);
                }
                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询摄像机状态信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询摄像机状态信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetCameraStatus");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(startTime + " " + endTime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetServerStatus(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        String snedUrl = Properties.getSendServerStatus();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Serverstatus camer = new Serverstatus();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setStatusTime((String) o.get("status_time"));
                    camer.setOnlineStatus((String) o.get("online_status"));
                    camer.setCpuUsage((String) o.get("cpu_usage"));
                    camer.setCpuTemp((String) o.get("cpu_temp"));
                    camer.setMemoryUsage((String) o.get("memory_usage"));
                    camer.setPortStatus((String) o.get("port-status"));
                    camer.setDiskStatus((String) o.get("disk_status"));
                    camer.setDiskPartitionUsage((String) o.get("disk_partition_usage"));
                    serverstatusMapper.insert(camer);
                }
                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询服务器状态信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询服务器状态信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetServerStatus");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GeDiskStatus(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        String snedUrl = Properties.getSendDiskStatus();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);

        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Diskstatus camer = new Diskstatus();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setStatusTime((String) o.get("status_time"));
                    camer.setOnlineStatus((String) o.get("online_status"));
                    camer.setCpuUsage((String) o.get("cpu_usage"));
                    camer.setFanDisk((String) o.get("fan_disk"));
                    camer.setDiskStatus((String) o.get("disk_status"));
                    diskstatusMapper.insert(camer);
                }
                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询磁盘状态信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询磁盘状态信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GeDiskStatus");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetSwitchStatus(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        String snedUrl = Properties.getSendSwitchStatus();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);

        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Switchstatus camer = new Switchstatus();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setStatusTime((String) o.get("status_time"));
                    camer.setOnlineStatus((String) o.get("online_status"));
                    camer.setCpuUsage((String) o.get("cpu_usage"));
                    camer.setMemoryUsage((String) o.get("memory_usage"));
                    camer.setPortStatus((String) o.get("port_status"));
                    switchstatusMapper.insert(camer);
                }
                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询交换机状态信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询交换机状态信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetSwitchStatus");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetCameraAlarm(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Cameraalarm> camerMap = new HashMap<>();
        List<Cameraalarm> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendVideoAlarm();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);

        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{
            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Cameraalarm camer = new Cameraalarm();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setAlarmTime((String) o.get("alarm_time"));
                    camer.setAlarmLevel((String) o.get("alarm_level"));
                    camer.setAlarmType((String) o.get("alarm_type"));
                    camer.setAlarmId((String) o.get("alarm_id"));
                    camer.setAlarmStatus((String) o.get("alarm_status"));
                    camer.setDescription((String) o.get("description"));
                    listCamer.add(camer);
                }
                List<Cameraalarm> cameraAlarmList = cameraalarmMapper.selectAll();
                for (Cameraalarm cameraAlarm : cameraAlarmList) {
                    camerMap.put(cameraAlarm.getAlarmId(), cameraAlarm);
                }
                for (Cameraalarm cameraAlarm : listCamer) {
                    boolean contains = camerMap.containsKey(cameraAlarm.getAlarmId());
                    if (contains) {
                        Example example = new Example(Cameraalarm.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("alarmId", cameraAlarm.getAlarmId());
                        cameraalarmMapper.updateByExample(cameraAlarm, example);
                    } else {
                        cameraalarmMapper.insert(cameraAlarm);
                    }
                }
                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询摄像机告警信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询摄像机告警信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetCameraAlarm");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetServerAlarm(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Serveralarm> camerMap = new HashMap<>();
        List<Serveralarm> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendServerAlarm();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);

        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Serveralarm camer = new Serveralarm();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setAlarmTime((String) o.get("alarm_time"));
                    camer.setAlarmLevel((String) o.get("alarm_level"));
                    camer.setAlarmType((String) o.get("alarm_type"));
                    camer.setAlarmId((String) o.get("alarm_id"));
                    camer.setAlarmStatus((String) o.get("alarm_status"));
                    camer.setDescription((String) o.get("description"));
                    listCamer.add(camer);
                }
                List<Serveralarm> serverAlarmList = serveralarmMapper.selectAll();
                for (Serveralarm serverAlarm : serverAlarmList) {
                    camerMap.put(serverAlarm.getAlarmId(), serverAlarm);
                }
                for (Serveralarm serverAlarm : listCamer) {
                    boolean contains = camerMap.containsKey(serverAlarm.getAlarmId());
                    if (contains) {
                        Example example = new Example(Serveralarm.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("alarmId", serverAlarm.getAlarmId());
                        serveralarmMapper.updateByExample(serverAlarm, example);
                    } else {
                        serveralarmMapper.insert(serverAlarm);
                    }
                }


                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询服务器告警信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询服务器告警信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetServerAlarm");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetDiskAlarm(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Diskalarm> camerMap = new HashMap<>();
        List<Diskalarm> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendDiskAlarm();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);

        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else{

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Diskalarm camer = new Diskalarm();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setAlarmTime((String) o.get("alarm_time"));
                    camer.setAlarmLevel((String) o.get("alarm_level"));
                    camer.setAlarmType((String) o.get("alarm_type"));
                    camer.setAlarmId((String) o.get("alarm_id"));
                    camer.setAlarmStatus((String) o.get("alarm_status"));
                    camer.setDescription((String) o.get("description"));
                    listCamer.add(camer);
                }
                List<Diskalarm> diskalarmsList = diskalarmMapper.selectAll();
                for (Diskalarm diskalarms : diskalarmsList) {
                    camerMap.put(diskalarms.getAlarmId(), diskalarms);
                }
                for (Diskalarm diskalarms : listCamer) {
                    boolean contains = camerMap.containsKey(diskalarms.getAlarmId());
                    if (contains) {
                        Example example = new Example(Diskalarm.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("alarmId", diskalarms.getAlarmId());
                        diskalarmMapper.updateByExample(diskalarms, example);
                    } else {
                        diskalarmMapper.insert(diskalarms);
                    }
                }

                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询磁盘告警信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询磁盘告警信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetDiskAlarm");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetSwitchAlarm(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Switchalarm> camerMap = new HashMap<>();
        List<Switchalarm> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendSwitchAlarm();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);

        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else {

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Switchalarm camer = new Switchalarm();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setAlarmTime((String) o.get("alarm_time"));
                    camer.setAlarmLevel((String) o.get("alarm_level"));
                    camer.setAlarmType((String) o.get("alarm_type"));
                    camer.setAlarmId((String) o.get("alarm_id"));
                    camer.setAlarmStatus((String) o.get("alarm_status"));
                    camer.setDescription((String) o.get("description"));
                    listCamer.add(camer);
                }
                List<Switchalarm> switchalarmList = switchalarmMapper.selectAll();
                for (Switchalarm switchalarm : switchalarmList) {
                    camerMap.put(switchalarm.getAlarmId(), switchalarm);
                }
                for (Switchalarm switchalarm : listCamer) {
                    boolean contains = camerMap.containsKey(switchalarm.getAlarmId());
                    if (contains) {
                        Example example = new Example(Switchalarm.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("alarmId", switchalarm.getAlarmId());
                        switchalarmMapper.updateByExample(switchalarm, example);
                    } else {
                        switchalarmMapper.insert(switchalarm);
                    }
                }


                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询交换机告警信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询交换机告警信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetSwitchAlarm");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }

    @Override
    public Map<String,Object> GetIVSAlarm(String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Ivsalarm> camerMap = new HashMap<>();
        List<Ivsalarm> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSendIVSAlarm();
        Map<String,String> mapStr = new HashMap<>();
        mapStr.put("starttime", starttime);
        mapStr.put("endtime", endtime);
        String data = HttpClientUt.doGetd(snedUrl, mapStr);
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        }else {

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Ivsalarm camer = new Ivsalarm();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setAlarmTime( o.get("alarm_time").toString());
                    camer.setAlarmLevel(o.get("alarm_level").toString());
                    camer.setAlarmType(o.get("alarm_type").toString());
                    camer.setAlarmId(o.get("alarm_id").toString());
                    camer.setAlarmStatus(o.get("alarm_status").toString());
                    camer.setDescription(o.get("description").toString());
                    listCamer.add(camer);
                }
                List<Ivsalarm> ivsalarmList = ivsalarmMapper.selectAll();
                for (Ivsalarm ivsalarm : ivsalarmList) {
                    camerMap.put(ivsalarm.getAlarmId(), ivsalarm);
                }
                for (Ivsalarm ivsalarm : listCamer) {
                    boolean contains = camerMap.containsKey(ivsalarm.getAlarmId());
                    if (contains) {
                        Example example = new Example(Ivsalarm.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("alarmId", ivsalarm.getAlarmId());
                        ivsalarmMapper.updateByExample(ivsalarm, example);
                    } else {
                        ivsalarmMapper.insert(ivsalarm);
                    }
                }
                map.put("result", dataMapList.get("result"));
                map.put("data", dataMapList.get("data"));
                logger.error("查询智能分析告警信息 列表 查询时间：" + DateFormatHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss") + "  data " + list.toString());
            } else {
                map.put("result", dataMapList.get("result"));
                map.put("message", dataMapList.get("message"));
                logger.error("查询智能分析告警信息失败 错误码 result " + dataMapList.get("result") + " message " + dataMapList.get("message"));
            }
        }
        LogRest log = new LogRest();
        log.setFunname("GetIVSAlarm");
        log.setIp(snedUrl);
        log.setLrsj(new Date());
        log.setParamin(starttime + " " + endtime);
        log.setRedata(data);
        log.setType(1 + "");
        logRestMapper.insert(log);
        return map;
    }


    @Override
    public List<Map<String,Object>> sendFile(String starttime, String endtime, String authorization) {
        String sendFileUrl = Properties.getSendFileUrl();
        String token = Properties.getToken();
        if (starttime != null && !"".equals(starttime)) {
            sendFileUrl = starttime;
        }
        if (authorization != null && !"".equals(authorization)) {
            token = authorization;
        }
        List<Map<String,Object>> listMap = new ArrayList<>();
        Example example = new Example(VideoFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", "1");
        List<VideoFile> videoFiles = videoFileMapper.selectByExample(example);

        for (VideoFile videoFile : videoFiles) {
            {
                VideoFile vf = new VideoFile();
                vf.setStatus("2");
                Example example1 = new Example(VideoFile.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("id", videoFile.getId());
                videoFileMapper.updateByExampleSelective(vf, example1);
            }
            Map<String,Object> map = new HashMap<>();
            listMap.add(map);
            String data = HttpClientUt.sendFile(sendFileUrl, videoFile.getPath() + "/" + videoFile.getFilename(), token);
            if (data == null) {
                map.put("message", "发送文件失败：" + videoFile.getId() + " 连接错误 返回值为null");
                continue;
            }
            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList2flie(data);

            if ("200".equals(dataMapList.get("stateCode"))) {

                String dataR = (String) dataMapList.get("data");
                VideoFile vf = new VideoFile();
                vf.setFileid(dataR);
                vf.setStatus("3");
                vf.setUptime(DateUtils.getDateTime());

                Example example1 = new Example(VideoFile.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("id", videoFile.getId());
                videoFileMapper.updateByExampleSelective(vf, example1);
                map.put("stateCode", dataMapList.get("stateCode"));
                map.put("data", dataMapList.get("data"));
                logger.error("上传视频文件成功返回数据为：" + data);

                this.deleteFile(videoFile);
            } else {
                map.put("stateCode", dataMapList.get("stateCode"));
                logger.error("上传视频文件:失败 错误码 stateCode " + dataMapList.get("stateCode") + " message " + dataMapList.get("message"));
            }
            map.put("message", dataMapList.get("message"));
            LogRest log = new LogRest();
            log.setFunname("sendFile");
            log.setIp(videoFile.getPath() + "/" + videoFile.getFilename());
            log.setLrsj(new Date());
            log.setParamin("请求地址：" + sendFileUrl + "目录id：" + Properties.getFid() + " token:" + token);
            log.setRedata(data);
            log.setType(1 + "");
            logRestMapper.insert(log);

        }
        return listMap;
    }

    @Override
    public void deleteFile(VideoFile videoFile) {
        Boolean flag = false;
        String sPath = videoFile.getPath() + "/" + videoFile.getFilename();
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                flag = deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                flag = deleteDirectory(sPath);
            }
        }

        LogRest log = new LogRest();
        log.setFunname("deleteFile");
        log.setIp(sPath);
        log.setLrsj(new Date());
        log.setParamin("删除文件 文件id：" + videoFile.getId());
        log.setRedata("" + flag);
        log.setType(0 + "");
        logRestMapper.insert(log);
        logger.error("删除文件： " + flag + " 文件id： " + videoFile.getId() + "文件路径：" + sPath);

    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map<String,String> saveVideoTask(String videoId, String startTime, String endTime) {

        Map<String,String> map = new HashMap<>();
        if (videoId != null && "".equals(videoId) || startTime != null && "".equals(startTime) || endTime != null && "".equals(endTime)) {
            map.put("message", "参数错误");
            map.put("stateCode", "0");
            return map;
        }
        VideoFile vf = new VideoFile();
        vf.setIpcid(videoId);
        vf.setId(UUIDHelper.getUUIDStr());
        vf.setStatus("0");
        vf.setKssj(startTime);
        vf.setJssj(endTime);
        int i = videoFileMapper.insertSelective(vf);
        if (i > 0) {
            map.put("message", "添加成功");
            map.put("stateCode", "1");
        } else {
            map.put("message", "添加失败 服务器异常");
            map.put("stateCode", "0");
        }
        return map;
    }

    @Override
    public Map<String,String> getANBAO3(String beginTime, String endTime, String xm, String lj) {
        Map<String,String> map = new HashMap<>();
        Map<String,AnBao3> camerMap = new HashMap<>();
        List<AnBao3> listCamer = new ArrayList<>();
        ANBAO3[] anbao3s = webServiceUtils.callWebserviceASMX(beginTime, endTime, xm, lj);
        if (anbao3s == null || anbao3s.length < 1) {
            map.put("message", "返回值为空或者null");
            map.put("stateCode", "0");
        }else{

            for (ANBAO3 anbao3 : anbao3s) {
                AnBao3 anbao = new AnBao3();
                anbao.setAnBaoId(UUIDHelper.getUUIDStr());
                anbao.setJtsgGkbFssj(anbao3.getJTSG_GKB_FSSJ());
                anbao.setJtsgGkbFssjSxx(anbao3.getJTSG_GKB_FSSJ_SXX());
                anbao.setJtsgGkbGl(anbao3.getJTSG_GKB_GL());
                anbao.setJtsgGkbGlgd(anbao3.getJTSG_GKB_GLGD());
                anbao.setJtsgGkbId(anbao3.getJTSG_GKB_ID());
                anbao.setJtsgGkbJ(anbao3.getJTSG_GKB_J());
                anbao.setJtsgGkbKtsj(anbao3.getJTSG_GKB_KTSJ());
                anbao.setJtsgGkbKtsjSxx(anbao3.getJTSG_GKB_KTSJ_SXX());
                anbao.setJtsgGkbSgbh(anbao3.getJTSG_GKB_SGBH());
                anbao.setJtsgGkbSxx(anbao3.getJTSG_GKB_SXX());
                anbao.setJtsgGkbX(anbao3.getJTSG_GKB_X());
                anbao.setJtsgGkbXb(anbao3.getJTSG_GKB_XB());
                anbao.setJtsgGkbZdsj(anbao3.getJTSG_GKB_ZDSJ());
                anbao.setJtsgGkbZdsjXx(anbao3.getJTSG_GKB_ZDSJ_XX());
                listCamer.add(anbao);
            }

            List<AnBao3> anBao3s = anBao3Mapper.selectAll();
            for (AnBao3 anBao3 : anBao3s) {
                camerMap.put(anBao3.getJtsgGkbSgbh(), anBao3);
            }
            for (AnBao3 anBao3 : listCamer) {
                boolean contains = camerMap.containsKey(anBao3.getJtsgGkbSgbh());
                if (contains) {
                    Example example = new Example(AnBao3.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("jtsgGkbSgbh", anBao3.getJtsgGkbSgbh());
                    anBao3Mapper.updateByExample(anBao3, example);
                } else {
                    anBao3Mapper.insert(anBao3);
                }
            }
        }
       /* LogRest log = new LogRest();
        log.setFunname("getANBAO3");
        log.setIp(Properties.getAnbao3Url());
        log.setLrsj(new Date());
        log.setParamin("查询安监数据 开始时间：" + beginTime + " 结束时间：" + endTime + " 线路：" + xm + " 路局：" + lj);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data",anbao3s.toString());
        log.setRedata("" + jsonParam.toJSONString());
        log.setType(0 + "");
        logRestMapper.insert(log);
        logger.error("查询安监数据 开始时间：" + beginTime + " 结束时间：" + endTime + " 线路：" + xm + " 路局：" + lj + " 返回数据：" + anbao3s.toString());
*/
        map.put("message", "添加成功");
        map.put("stateCode", "1");
        return map;
    }


    @Override
    public void queryTask() {

        List<QueryTask> select = queryTaskMapper.selectByStatus(0);
        for (QueryTask task : select) {
            switch (task.getMethodName()) {
                case "GetCameraStatu": {
                    Map<String,Object> stringObjectMap = this.GetCameraStatus(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GetServerStatus": {
                    Map<String,Object> stringObjectMap = this.GetServerStatus(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GeDiskStatus": {

                    Map<String,Object> stringObjectMap = this.GeDiskStatus(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GetSwitchStatus": {

                    Map<String,Object> stringObjectMap = this.GetSwitchStatus(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GetCameraAlarm": {

                    Map<String,Object> stringObjectMap = this.GetCameraAlarm(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GetDiskAlarm": {

                    Map<String,Object> stringObjectMap = this.GetDiskAlarm(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GetSwitchAlarm": {

                    Map<String,Object> stringObjectMap = this.GetSwitchAlarm(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }

                }
                break;
                case "GetIVSAlarm": {

                    Map<String,Object> stringObjectMap = this.GetIVSAlarm(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "GetServerAlarm": {

                    Map<String,Object> stringObjectMap = this.GetServerAlarm(task.getKssj(), task.getJssj());
                    if (stringObjectMap.get("result") != null && "success".equals(stringObjectMap.get("result"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                case "getANBAO3": {
                    Map<String,String> stringObjectMap = this.getANBAO3(task.getKssj(), task.getJssj(), Properties.getXm(), Properties.getLj());
                    if (stringObjectMap.get("stateCode") != null && "1".equals(stringObjectMap.get("stateCode"))) {
                        Example example = new Example(QueryTask.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("id", task.getId());
                        task.setStatus(1);
                        queryTaskMapper.updateByExampleSelective(task, example);
                    }
                }
                break;
                default:
                    logger.error("收到未知得 方法名 ：" + task.getMethodName());
                    break;
            }


        }

    }
}
