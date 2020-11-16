package com.data.big.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.big.gw.GwaqscJxglService;
import com.data.big.gw.GwaqscJxglServicePortType;
import com.data.big.mapper.*;
import com.data.big.model.Dictionary;
import com.data.big.service.Service;
import com.data.big.service.ServiceNetty;
import com.data.big.task.KeepTask;
import com.data.big.util.*;
import com.data.big.model.*;
import com.data.big.util.Properties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;


import javax.xml.ws.BindingProvider;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@org.springframework.stereotype.Service("Service")
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
    @Autowired
    private VideoKilometerMapper videoKilometerMapper;
    @Autowired
    private CameraMapper cameraMapper;
    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private com.data.big.queue.TaskQueue taskQueue;
    @Autowired
    private HcsjMapper hcsjMapper;
    @Autowired
    private SgjhMapper sgjhMapper;
    @Autowired
    private WxjhMapper wxjhMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private IpcTagMapper ipcTagMapper;

    @Autowired
    private ServiceNetty serviceNetty;

    @Autowired
    private VideoTagMapper videoTagMapper;

    // 日志记录器
    private static final Logger logger = LogManager.getLogger(ServiceImpl.class);

    @Override
    public Map GetCamera() {
        Map<String,Object> map = new HashMap<>();
        Map<String,Camera> camerMap = new HashMap<>();
        Map<String,Camera> deleteCamerMap = new HashMap<>();
        List<Camera> listCamer = new ArrayList<>();
        String snedUrl = Properties.getSnedUrl();
        String data = HttpClientUt.doPost(snedUrl, "");
        if (data == null) {
            map.put("message", "连接错误 返回值为null");
        } else {

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    String deviceName = (String) o.get("device_name");
                    Camera camer = new Camera();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setDeviceName(deviceName);
                    camer.setNodeId((String) o.get("node_id"));
                    //  camer.setDeviceType((String) o.get("device_type"));
                    camer.setManufacturer((String) o.get("manufacturer"));
                    camer.setCameraType((String) o.get("camera_type"));
                    camer.setCameraDpi((String) o.get("Camera_dpi"));
                    camer.setIpAddr((String) o.get("ip_addr"));
                    camer.setIpPort((String) o.get("ip_port"));
                    camer.setUsername((String) o.get("username"));
                    camer.setPassword((String) o.get("password"));
                    camer.setInstallTime((String) o.get("install_time"));
                    camer.setAffiliation((String) o.get("affiliation"));
                    camer.setUpDown((String) o.get("up_down"));
                    camer.setAssociatedLine((String) o.get("associated_line"));
                    if (deviceName != null) {
                        String[] strs = deviceName.split(" ");
                        if (strs.length > 1 && strs[1].contains("K")) {
                            camer.setkMark(Integer.parseInt(getNum(strs[1])));
                        }
                        if (deviceName.contains("[") && deviceName.contains("]")) {
                            String type = deviceName.substring(deviceName.indexOf("[") + 1, deviceName.indexOf("]"));
                            camer.setDeviceType(type);
                        }

                    }
                    camer.setJoinStation((String) o.get("join_station"));
                    camer.setDirection((String) o.get("direction"));
                    camer.setTargetLocation((String) o.get("target_location"));
                    camer.setCatalogue((String) o.get("catalogue"));
                    camer.setLongitude((String) o.get("longitude"));
                    camer.setLatitude((String) o.get("latitude"));
                    listCamer.add(camer);
                }

                List<Camera> cameras = cameraMapper.selectAll();
                for (Camera camera : cameras) {
                    camerMap.put(camera.getDeviceId(), camera);
                }
                for (Camera camera : listCamer) {
                    deleteCamerMap.put(camera.getDeviceId(), camera);
                    boolean contains = camerMap.containsKey(camera.getDeviceId());
                    if (contains) {
                        Example example = new Example(Camera.class);
                        Example.Criteria criteria = example.createCriteria();
                        criteria.andEqualTo("deviceId", camera.getDeviceId());
                        cameraMapper.updateByExample(camera, example);
                    } else {
                        cameraMapper.insert(camera);
                    }
                }
                for (Camera camera : cameras) {
                    boolean contains = deleteCamerMap.containsKey(camera.getDeviceId());
                    if (!contains) {
                        cameraMapper.delete(camera);
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

    private String getNum(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

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
        } else {

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
                            camer.setkMark(Integer.parseInt(strs[1]));
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
        } else {

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
        } else {

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
        } else {

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
        } else {

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
        } else {

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
        } else {

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
        } else {

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
        } else {

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
        } else {
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
        } else {

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
        } else {

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
        } else {

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
        } else {

            HashMap<String,Object> dataMapList = HttpClientUt.getDataMapList(data);
            if ("success".equals(dataMapList.get("result"))) {
                List<Map<String,Object>> list = (List) dataMapList.get("data");
                for (Map<String,Object> o : list) {
                    Ivsalarm camer = new Ivsalarm();
                    camer.setDeviceId((String) o.get("device_id"));
                    camer.setId(UUIDHelper.getUUIDStr());
                    camer.setAlarmTime(o.get("alarm_time").toString());
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

            if ("200".equals(dataMapList.get("statusCode"))) {

                String dataR = (String) dataMapList.get("data");
                VideoFile vf = new VideoFile();
                vf.setFileid(dataR);
                vf.setStatus("3");
                vf.setUptime(DateUtils.getDateTime());

                Example example1 = new Example(VideoFile.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("id", videoFile.getId());
                videoFileMapper.updateByExampleSelective(vf, example1);
                map.put("statusCode", dataMapList.get("statusCode"));
                map.put("data", dataMapList.get("data"));
                logger.error("上传视频文件成功返回数据为：" + data);

              //  this.deleteFile(videoFile);
            } else {
                map.put("stateCode", dataMapList.get("statusCode"));
                logger.error("上传视频文件:失败 错误码 stateCode " + dataMapList.get("statusCode") + " message " + dataMapList.get("message"));
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

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
        } else {

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

    @Override
    public List<MethodType> getMethodName() {
        List<MethodType> methodTypes = methodTypeMapper.selectAll();
        return methodTypes;
    }

    @Override
    public Map<String,String> addTask(String startTime, String endTime, String methodId) {
        Map<String,String> resurtMap = new HashMap<>();
        if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime) || StringUtils.isEmpty(methodId)) {
            resurtMap.put("message", "参数错误");
            resurtMap.put("status", "1");
            logger.info("参数错误");
            return resurtMap;
        }
        QueryTask queryTask = new QueryTask();
        queryTask.setId(UUIDHelper.getUUIDStr());
        queryTask.setStatus(0);
        queryTask.setKssj(startTime);
        queryTask.setJssj(endTime);
        queryTask.setType(Integer.parseInt(methodId));
        int i = queryTaskMapper.insertSelective(queryTask);
        if (i > 0) {
            resurtMap.put("message", "添加成功");
            resurtMap.put("status", "0");
            logger.info("添加成功");
        } else {
            resurtMap.put("message", "添加失败");
            resurtMap.put("status", "1");
            logger.info("添加失败");
        }
        return resurtMap;
    }

    @Override
    public List<Camerainfo> getVideo() {

        List<Camerainfo> camerainfoList = camerainfoMapper.selectAll();
        return camerainfoList;
    }

    @Override
    public Map<String,String> addVideoKilometer(VideoKilometer videoKilometer) {
        Map<String,String> resurtMap = new HashMap<>();
        if (StringUtils.isEmpty(videoKilometer.getStartkilometer()) || StringUtils.isEmpty(videoKilometer.getEndkilometer()) || StringUtils.isEmpty(videoKilometer.getVideoCode()) || StringUtils.isEmpty(videoKilometer.getInstalltype())) {

            resurtMap.put("message", "参数错误");
            resurtMap.put("status", "1");
            logger.info("参数错误");
            return resurtMap;
        }
        videoKilometer.setId(UUIDHelper.getUUIDStr());
        videoKilometer.setStartkilometer("K" + Integer.parseInt(videoKilometer.getStartkilometer()) / 1000 + "+" + Integer.parseInt(videoKilometer.getStartkilometer()) % 1000);
        videoKilometer.setEndkilometer("K" + Integer.parseInt(videoKilometer.getEndkilometer()) / 1000 + "+" + Integer.parseInt(videoKilometer.getEndkilometer()) % 1000);
        int i = videoKilometerMapper.insertSelective(videoKilometer);
        if (i > 0) {
            resurtMap.put("message", "添加成功");
            resurtMap.put("status", "0");
            logger.info("添加成功");
        } else {
            resurtMap.put("message", "添加失败");
            resurtMap.put("status", "1");
            logger.info("添加失败");
        }
        return resurtMap;
    }
    @Override
    public Map<String,String> getportType(){
        Map<String,String> map = new HashMap<>();
        GwaqscJxglServicePortType portType=null;
        try {
            GwaqscJxglService services = new GwaqscJxglService();//创建接口方法类
             portType = services.getGwaqscJxglServiceHttpSoap12Endpoint();//获取接口对象

            ((BindingProvider)portType).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", 6*1000);
            ((BindingProvider)portType).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 6*1000);

        }catch (Exception e){
            logger.error("================连接工务服务失败====================");
            logger.error(e.getMessage(),e);
            map.put("message", "获取连接失败");
            map.put("status", "1");
            return map;
        }
        if(portType!=null){
            /*Client proxy = ClientProxy.getClient(portType);
            HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setConnectionTimeout(30000); // 连接超时时间
            policy.setReceiveTimeout(30000);// 请求超时时间.
            conduit.setClient(policy);*/
            try {
                    FZMap.clientTokenLock.writeLock().lock();
                    FZMap.clientToken.put("GW",portType);

                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    FZMap.clientTokenLock.writeLock().unlock();
                }
            map.put("message", "连接成功");
            map.put("status", "0");

        }
        logger.info("连接工务服务成功");
        return map;
    }

    @Override
    public Map<String,String> getHcsj(String qsrq, String jsrq, String cxdj, String xm,String page,String rows) {
        Map<String,String> map = new HashMap<>();
        if (StringUtils.isEmpty(qsrq) || StringUtils.isEmpty(jsrq) || StringUtils.isEmpty(xm)) {
            map.put("message", "参数错误");
            map.put("status", "1");
            logger.info("参数错误");
            return map;
        }
        GwaqscJxglServicePortType portType=null;
        String wsdlUrl = Properties.getWsdlUrl();
        String wsdlNamespace = Properties.getWsdlNamespace();
        String wsdlName = Properties.getWsdlName();

        try {
            FZMap.clientTokenLock.readLock().lock();
            portType=(GwaqscJxglServicePortType) FZMap.clientToken.get("GW");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            FZMap.clientTokenLock.readLock().unlock();
        }

        if(portType==null){
           logger.error("连接工务服务失败");
            map.put("message", "连接工务服务失败");
            map.put("status", "1");
            return map;
        }
        /*GwaqscJxglService services = new GwaqscJxglService();//创建接口方法类
        GwaqscJxglServicePortType portType = services.getGwaqscJxglServiceHttpSoap12Endpoint();//获取接口对象

        Client proxy = ClientProxy.getClient(portType);
        HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(30000); // 连接超时时间
        policy.setReceiveTimeout(30000);// 请求超时时间.
        conduit.setClient(policy);*/
        List<Hcsj> hcsjList = new ArrayList<>();
        StringBuffer buffer=new StringBuffer();
        int i=Integer.parseInt(page);
        while(true){

            String resU = portType.getHcsj(qsrq, jsrq, cxdj, xm,i+"",rows);
            i++;
            if (StringUtils.isEmpty(resU)) {
               break;
            }
            buffer.append(resU);

            List<Map> data = Dom.getData(resU);
            if (data.get(0).get("hcsj") instanceof Map) {
                Map mapData = (Map) data.get(0).get("hcsj");
                hcsjList.add(getHcsjToMap(mapData));
            } else {
                List<Map> listData = (List) data.get(0).get("hcsj");
                for (Map listDatum : listData) {
                    hcsjList.add(getHcsjToMap(listDatum));
                }
            }
            LogRest log = new LogRest();
            log.setFunname("getHcsj");
            log.setIp(wsdlUrl);
            log.setLrsj(new Date());
            log.setParamin("公务晃车数据 查询开始时间：" + qsrq + " 线名:" + xm + " 结束时间：" + jsrq);
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("data", resU);
            log.setRedata("" + jsonParam.toJSONString());
            log.setType(0 + "");
            logRestMapper.insert(log);
        }

        hcsjMapper.insertCodeBatch(hcsjList);
       /* for (Hcsj hcsj : hcsjList) {
            Hcsj hcsj1 = hcsjMapper.selectByPrimaryId(hcsj.getId());
            if (hcsj1 != null) {
                continue;
            }
            int i = hcsjMapper.insertSelective(hcsj);
           *//* if(i>0){
                String jcsj = hcsj.getJcsj();
               Date date= DateUtils.parseDate(jcsj.substring(0,jcsj.indexOf(".")),"yyyy-MM-dd HH:mm:ss");
               Long dateL=date.getTime();
                String kaishi = DateUtils.getDate(new Date(dateL - 5000), "yyyy-MM-dd HH:mm:ss");
                String jieshu = DateUtils.getDate(new Date(dateL + 5000), "yyyy-MM-dd HH:mm:ss");
                serviceNetty.addVideoTask(hcsj.getLc().replace(".",""),kaishi,jieshu);
            }*//*
        }*/
        map.put("message", "成功");
        map.put("status", "0");
        logger.info("公务晃车数据 查询开始时间：" + qsrq + " 结束时间：" + jsrq + " 线名:" + xm + " 返回数据：" + buffer.toString());
        map.put("data", buffer.toString());
        map.put("size", hcsjList.size()+"");



        return map;
    }

    private Hcsj getHcsjToMap(Map map) {
        Hcsj hcsj = new Hcsj();
        hcsj.setId((String) map.get("fid"));
        hcsj.setDwmc((String) map.get("dwmc"));
        hcsj.setXm((String) map.get("xm"));
        hcsj.setXbh((String) map.get("xbh"));
        hcsj.setXb((String) map.get("xb"));
        hcsj.setLc((String) map.get("lc"));
        hcsj.setJcsj((String) map.get("jcsj"));
        hcsj.setJssj((String) map.get("jssj"));
        hcsj.setCzjsd((String) map.get("czjsd"));
        hcsj.setCzjsdjb((String) map.get("czjsdjb"));
        hcsj.setSpjsd((String) map.get("spjsd"));
        hcsj.setSpjsdjb((String) map.get("spjsdjb"));
        hcsj.setSd((String) map.get("sd"));
        hcsj.setJcxh((String) map.get("jcxh"));
        hcsj.setJch((String) map.get("jch"));
        hcsj.setCc((String) map.get("cc"));
        hcsj.setStatus("0");
        return hcsj;
    }

    @Override
    public Map<String,String> getSgjh(String qsrq, String jsrq, String xm) {
        Map<String,String> map = new HashMap<>();
        if (StringUtils.isEmpty(qsrq) || StringUtils.isEmpty(jsrq) || StringUtils.isEmpty(xm)) {
            map.put("message", "参数错误");
            map.put("status", "1");
            logger.info("参数错误");
            return map;
        }
        GwaqscJxglServicePortType portType=null;
        ArrayList<Object> listO = new ArrayList<>();
        listO.add(qsrq);
        listO.add(jsrq);
        String wsdlUrl = Properties.getWsdlUrl();
        String wsdlNamespace = Properties.getWsdlNamespace();
        String wsdlName = Properties.getWsdlName();

        try {
            FZMap.clientTokenLock.readLock().lock();
            portType=(GwaqscJxglServicePortType) FZMap.clientToken.get("GW");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            FZMap.clientTokenLock.readLock().unlock();
        }

        if(portType==null){
            logger.error("连接工务服务失败");
            map.put("message", "连接工务服务失败");
            map.put("status", "1");
            return map;
        }

        String resU = portType.getSgjh(qsrq, jsrq, xm);
        if (StringUtils.isEmpty(resU)) {
            map.put("data", "接收的数据为null");
            logger.info("请求错误 wsdlUrl " + wsdlUrl + " wsdlNamespace" + wsdlNamespace + " wsdlName" + wsdlName + " qsrq" + qsrq + " jsrq" + jsrq + " 线名:" + xm);
            return map;
        }
        List<Map> data = Dom.getData(resU);

        List<Sgjh> sgjhList = new ArrayList<>();
        if (data.get(0).get("sgjh") instanceof Map) {
            Map mapData = (Map) data.get(0).get("sgjh");
            sgjhList.add(getSgjhToMap(mapData));
        } else {
            List<Map> listData = (List) data.get(0).get("sgjh");
            for (Map listDatum : listData) {
                sgjhList.add(getSgjhToMap(listDatum));
            }
        }
        sgjhMapper.insertCodeBatch(sgjhList);
       /* for (Sgjh sgjh : sgjhList) {
            Sgjh sgjh1 = sgjhMapper.selectByPrimaryId(sgjh.getId());
            if (sgjh1 != null) {
                continue;
            }
            int i = sgjhMapper.insertSelective(sgjh);
           *//* if(i>0){
                addVideoTask(sgjh.getZyqslc().replace(".",""),sgjh.getZyzzlc().replace(".",""),sgjh.getJhqssj().substring(0,sgjh.getJhqssj().indexOf(".")),sgjh.getJhzzsj().substring(0,sgjh.getJhzzsj().indexOf(".")));
            }*//*
        }*/
        map.put("message", "成功");
        map.put("status", "0");
        logger.info("公务施工计划 查询开始时间：" + qsrq + " 结束时间：" + jsrq + " 线名:" + xm + " 返回数据：" + resU);
        map.put("data", resU);
        map.put("size", sgjhList.size()+"");


        LogRest log = new LogRest();
        log.setFunname("getSgjh");
        log.setIp(wsdlUrl);
        log.setLrsj(new Date());
        log.setParamin("公务施工计划 查询开始时间：" + qsrq + " 线名:" + xm + " 结束时间：" + jsrq);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data", resU);
        log.setRedata("" + jsonParam.toJSONString());
        log.setType(0 + "");
        logRestMapper.insert(log);

        return map;
    }

    private Sgjh getSgjhToMap(Map listDatum) {
        Sgjh sgjh = new Sgjh();
        sgjh.setId((String) listDatum.get("fid"));
        sgjh.setZywz((String) listDatum.get("fzywz"));
        sgjh.setQschzm((String) listDatum.get("fqschzm"));
        sgjh.setZzchzm((String) listDatum.get("fzzchzm"));
        sgjh.setXm((String) listDatum.get("fxm"));
        sgjh.setZyrq((String) listDatum.get("fzyrq"));
        sgjh.setJhqssj((String) listDatum.get("fjhqssj"));
        sgjh.setJhzzsj((String) listDatum.get("fjhzzsj"));
        sgjh.setZydj((String) listDatum.get("fzydj"));
        sgjh.setZylx((String) listDatum.get("fzylx"));
        sgjh.setRjhh((String) listDatum.get("frjhh"));
        sgjh.setSgnrjyxfw((String) listDatum.get("fsgnrjyxfw"));
        sgjh.setXsjxcfs((String) listDatum.get("fxsjxcfs"));
        sgjh.setZyqslc((String) listDatum.get("fzyqslc"));
        sgjh.setZyzzlc((String) listDatum.get("fzyzzlc"));
        sgjh.setSbztw((String) listDatum.get("fsbztw"));
        sgjh.setZyxm((String) listDatum.get("fzyxm"));
        sgjh.setXb((String) listDatum.get("fxb"));
        sgjh.setSgztdw((String) listDatum.get("fsgztdw"));
        sgjh.setPhdw((String) listDatum.get("fphdw"));
        sgjh.setFzrname((String) listDatum.get("ffzrname"));
        sgjh.setLycxx((String) listDatum.get("flycxx"));
        sgjh.setStatus("0");
        return sgjh;
    }

    @Override
    public Map<String,String> getWxjh(String qsrq, String jsrq, String xm) {
        Map<String,String> map = new HashMap<>();
        if (StringUtils.isEmpty(qsrq) || StringUtils.isEmpty(jsrq) || StringUtils.isEmpty(xm)) {
            map.put("message", "参数错误");
            map.put("status", "1");
            logger.info("参数错误");
            return map;
        }
        GwaqscJxglServicePortType portType=null;
        ArrayList<Object> listO = new ArrayList<>();
        listO.add(qsrq);
        listO.add(jsrq);
        String wsdlUrl = Properties.getWsdlUrl();
        String wsdlNamespace = Properties.getWsdlNamespace();
        String wsdlName = Properties.getWsdlName();

        try {
            FZMap.clientTokenLock.readLock().lock();
            portType=(GwaqscJxglServicePortType) FZMap.clientToken.get("GW");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            FZMap.clientTokenLock.readLock().unlock();
        }

        if(portType==null){
            logger.error("连接工务服务失败");
            map.put("message", "连接工务服务失败");
            map.put("status", "1");
            return map;
        }

        String resU = portType.getWxjh(qsrq, jsrq, xm);

        if (StringUtils.isEmpty(resU)) {
            map.put("data", "接收的数据为null");
            logger.info("请求错误 wsdlUrl " + wsdlUrl + " wsdlNamespace" + wsdlNamespace + " wsdlName" + wsdlName + " qsrq" + qsrq + " jsrq" + jsrq + " 线名:" + xm);
            return map;
        }
        List<Map> data = Dom.getData(resU);

        List<Wxjh> wxjhList = new ArrayList<>();
        if (data.get(0).get("wxjh") instanceof Map) {
            Map mapData = (Map) data.get(0).get("wxjh");
            wxjhList.add(getWxjhToMap(mapData));
        } else {
            List<Map> listData = (List) data.get(0).get("wxjh");
            for (Map listDatum : listData) {
                wxjhList.add(getWxjhToMap(listDatum));
            }
        }
        wxjhMapper.insertCodeBatch(wxjhList);
       /* for (Wxjh wxjh : wxjhList) {
            Wxjh wxjh1 = wxjhMapper.selectByPrimaryId(wxjh.getId());
            if (wxjh1 != null) {
                continue;
            }
            int i = wxjhMapper.insertSelective(wxjh);
           *//* if(i>0){
                String zyrq = wxjh.getZyrq();
                String zysd = wxjh.getZysd();
                String kaishi=zyrq.substring(0,zyrq.indexOf(" "))+" "+zysd.substring(0,zysd.indexOf("-"))+":00";
                String jieshu=zyrq.substring(0,zyrq.indexOf(" "))+" "+zysd.substring(zysd.indexOf("-")+1,zysd.indexOf("("))+":00";
                addVideoTask(wxjh.getZyqslc().replace(".",""),wxjh.getZyzzlc().replace(".",""),kaishi,jieshu);
            }*//*
            LogRest log = new LogRest();
            log.setFunname("getWxjh");
            log.setIp(wsdlUrl);
            log.setLrsj(new Date());
            log.setParamin("公务维修计划 查询开始时间：" + qsrq + " 线名:" + xm + " 结束时间：" + jsrq);
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("data", resU);
            log.setRedata("" + jsonParam.toJSONString());
            log.setType(0 + "");
            logRestMapper.insert(log);
        }*/
        map.put("message", "成功");
        map.put("status", "0");
        logger.info("公务维修计划 查询开始时间：" + qsrq + " 结束时间：" + jsrq + " 线名:" + xm + " 返回数据：" + resU);
        map.put("data", resU);
        map.put("size", wxjhList.size()+"");

        LogRest log = new LogRest();
        log.setFunname("getWxjh");
        log.setIp(wsdlUrl);
        log.setLrsj(new Date());
        log.setParamin("公务维修计划 查询开始时间：" + qsrq + " 线名:" + xm + " 结束时间：" + jsrq);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data", resU);
        log.setRedata("" + jsonParam.toJSONString());
        log.setType(0 + "");
        logRestMapper.insert(log);

        return map;
    }

    private Wxjh getWxjhToMap(Map listDatum) {
        Wxjh wxjh = new Wxjh();
        wxjh.setId((String) listDatum.get("fid"));
        wxjh.setBzdwmc((String) listDatum.get("fbzdwmc"));
        wxjh.setZylx((String) listDatum.get("fzylx"));
        wxjh.setZydj((String) listDatum.get("fzydj"));
        wxjh.setZyxm((String) listDatum.get("fzyxm"));
        wxjh.setZyrq((String) listDatum.get("fzyrq"));
        wxjh.setZysd((String) listDatum.get("fzysd"));
        wxjh.setXm((String) listDatum.get("fxm"));
        wxjh.setXb((String) listDatum.get("fxb"));
        wxjh.setQschzm((String) listDatum.get("fqschzm"));
        wxjh.setZzchzm((String) listDatum.get("fzzchzm"));
        wxjh.setZyqslc((String) listDatum.get("fzyqslc"));
        wxjh.setZyzzlc((String) listDatum.get("fzyzzlc"));
        wxjh.setBanci((String) listDatum.get("fbanci"));
        wxjh.setZzfhr((String) listDatum.get("fzzfhr"));
        wxjh.setGdfhr((String) listDatum.get("fgdfhr"));
        wxjh.setYffhr((String) listDatum.get("fyffhr"));
        wxjh.setDbr((String) listDatum.get("fdbr"));
        wxjh.setSbztw((String) listDatum.get("fsbztw"));
        wxjh.setStatus("0");
        return wxjh;
    }

    @Override
    public Map<String,Object> queryCurrentDayWarningData(String beginTime, String endTime) {

        Map<String,Object> map = new HashMap<>();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        String url = Properties.getLfUrl();
        String jsonData = HttpClientUt.doPostMap(url, map);
        HashMap<String,Object> mapObj = JSONObject.parseObject(jsonData, HashMap.class);
        List<Travel> travelList = new ArrayList<>();
        if ("200".equals(mapObj.get("status"))) {
            Map<String,Object> mapDate = JSONObject.parseObject(mapObj.get("data")
                    .toString(), HashMap.class);
            for (String s : mapDate.keySet()) {
                String type = "1";
                switch (s) {
                    case "yxbj":
                        type = "1";
                        break;
                    case "pdcd":
                        type = "2";
                        break;
                    case "dbrq":
                        type = "3";
                        break;
                    case "rynx":
                        type = "4";
                        break;
                    case "klmd":
                        type = "5";
                        break;
                    case "cztdnx":
                        type = "6";
                        break;
                    default:
                        type = "0";
                        break;

                }
                List<Object> list = JSONObject.parseArray(mapDate.get(s).toString(), Object.class);
                for (Object object : list) {
                    Map<String,Object> mapDate1 = JSONObject.parseObject(object
                            .toString(), HashMap.class);

                    Travel travel = new Travel();
                    travel.setId(UUIDHelper.getUUIDStr());
                    travel.setAlarmtype(type);
                    travel.setAlarmname(s);
                    travel.setAlarmid((String) mapDate1.get("id"));
                    travel.setStatus((String) mapDate1.get("STATUS"));
                    travel.setDeptcode((String) mapDate1.get("DeptCode"));
                    travel.setCreatetime(DateUtils.getDate(new Date(Long.parseLong((String) mapDate1.get("createTime"))), "yyyy-MM-dd HH:mm:ss"));
                    travel.setImgPath((String) mapDate1.get("img_path"));
                    travel.setIp((String) mapDate1.get("ip"));
                    travel.setVidPath((String) mapDate1.get("vid_path"));
                    travel.setCameraname((String) mapDate1.get("cameraName"));
                    travelList.add(travel);

                }
            }

        }
        Example example = new Example(Travel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween("createtime", beginTime, endTime);
        List<Travel> travelList1 = travelMapper.selectByExample(example);
        List<String> idList = new ArrayList<>();
        for (Travel travel : travelList1) {
            idList.add(travel.getAlarmid() + travel.getAlarmname());
        }
        for (Travel travel : travelList) {
            if (idList.contains(travel.getAlarmid() + travel.getAlarmname())) {
                break;
            }
            int i = travelMapper.insertSelective(travel);
            if (i > 0) {
                try {
                    taskQueue.produce(travel.getImgPath());
                    taskQueue.produce(travel.getVidPath());
                    logger.info("以存入队列里" + " img " + travel.getImgPath() + " video " + travel.getVidPath());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        map.put("data", jsonData);
        logger.info("查询旅服 预警 查询开始时间；" + beginTime + "结束时间：" + endTime + " 返回内容：" + jsonData);


        LogRest log = new LogRest();
        log.setFunname("lf");
        log.setIp(url);
        log.setLrsj(new Date());
        log.setParamin("查询旅服 预警 查询开始时间；" + beginTime + " 结束时间：" + endTime);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data", jsonData);
        log.setRedata("" + jsonParam.toJSONString());
        log.setType(0 + "");
        logRestMapper.insert(log);
        return map;
    }

    private int addVideoTask(String alarmId, String alarmName, String k, String j, String startTime, String endTime) {
       /* Example example = new Example(VideoFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("kssj", startTime);
        criteria.andEqualTo("jssj", endTime);
        List<VideoFile> videoFiles = videoFileMapper.selectByExample(example);
        for (VideoFile videoFile : videoFiles) {
            if (videoFile.getAlarmId() != null && videoFile.getAlarmId().equals(alarmId) && videoFile.getVideoType() != null && videoFile.getVideoType().equals(alarmName)) {
                return -1;
            }
        }*/
        List<VideoFile> videoFileList = new ArrayList<>();
        /*Camera camera = cameraMapper.getMinVideoCode(Integer.parseInt(k));
        if (camera != null && camera.getDeviceId() != null) {

            VideoFile videoFile = new VideoFile();
            videoFile.setId(UUIDHelper.getUUIDStr());
            videoFile.setIpcid(camera.getDeviceId());
            videoFile.setKssj(startTime);
            videoFile.setJssj(endTime);
            videoFile.setStatus("0");
            videoFile.setVideoType(alarmName);
            videoFile.setAlarmId(alarmId);
            videoFileList.add(videoFile);
        }
        Camera camera1 = cameraMapper.getMxnVideoCode(Integer.parseInt(j));
        if (camera1 != null && camera1.getDeviceId() != null) {
            VideoFile videoFile = new VideoFile();
            videoFile.setId(UUIDHelper.getUUIDStr());
            videoFile.setIpcid(camera1.getDeviceId());
            videoFile.setKssj(startTime);
            videoFile.setJssj(endTime);
            videoFile.setStatus("0");
            videoFile.setVideoType(alarmName);
            videoFile.setAlarmId(alarmId);
            videoFileList.add(videoFile);
        }
        if (camera1 != null && camera1.getDeviceId() != null && camera != null && camera.getDeviceId() != null) {
            List<Camera> cameraList = cameraMapper.getVideoCode(camera.getkMark(), camera1.getkMark());
            for (Camera camera2 : cameraList) {
                VideoFile videoFile = new VideoFile();
                videoFile.setId(UUIDHelper.getUUIDStr());
                videoFile.setIpcid(camera2.getDeviceId());
                videoFile.setKssj(startTime);
                videoFile.setJssj(endTime);
                videoFile.setStatus("0");
                videoFile.setVideoType(alarmName);
                videoFile.setAlarmId(alarmId);
                videoFileList.add(videoFile);

            }
        }*/
        List<String> time = getTime(startTime, endTime);
        List<Camera> cameraList = cameraMapper.getVideoByKJ(Integer.parseInt(k),Integer.parseInt(j));
        for (Camera camera : cameraList) {
            for (int i=0;i<time.size()-1;i++) {

                VideoFile videoFile = new VideoFile();
                videoFile.setId(UUIDHelper.getUUIDStr());
                videoFile.setIpcid(camera.getDeviceId());
                videoFile.setKssj(time.get(i));
                videoFile.setJssj(time.get(i+1));
                videoFile.setStatus("0");
                videoFile.setVideoType(alarmName);
                videoFile.setAlarmId(alarmId);
                videoFileList.add(videoFile);
            }
        }
        for (VideoFile videoFile : videoFileList) {
            Example example = new Example(VideoFile.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("ipcid", videoFile.getIpcid());
            criteria.andEqualTo("alarmId", videoFile.getAlarmId());
            criteria.andEqualTo("videoType", videoFile.getVideoType());
            criteria.andEqualTo("kssj", videoFile.getKssj());
            criteria.andEqualTo("jssj", videoFile.getJssj());
            List<VideoFile> videoFiles = videoFileMapper.selectByExample(example);
            if (videoFiles.size() > 0) {
                continue;
            }
            int i1 = videoFileMapper.insertSelective(videoFile);//添加到任务表里
        }
        return 1;
    }

    /**
     * 一半小时为单位存时间
     * @return
     */
    @Override
    public List<String > getTime(String dateK,String dateJ){
        List<String > listTime=new ArrayList<>();
        double l = DateUtils.dateMinusHalf(dateK, dateJ);
        listTime.add(dateK);
        for(int i=0,j=0;i<l;i++){
            j=i;
            if(++j>l){
                listTime.add(dateJ);
            }else{
                listTime.add(DateUtils.getDate(new Date(DateUtils.parseDate(listTime.get(i), "yyyy-MM-dd HH:mm:ss").getTime()+1800000L),"yyyy-MM-dd HH:mm:ss"));
            }
        }
        return  listTime;
    }


    @Override
    public void download(String sourceUrl, String targetUrl) {

        URL url = null;
        try {
            url = new URL(sourceUrl);
            File temp = new File(targetUrl);
            FileUtils.copyURLToFile(url, temp);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("下载文件成功 资源文件路径；" + sourceUrl + "保存路径：" + targetUrl);
    }


    @Override
    public Map<String,String> addVideoTask(String videoCode, String benginTime, String endTime, String intervalTime, String timeRange) {
        Map<String,String> map = new HashMap<>();

        if (StringUtils.isEmpty(videoCode) || StringUtils.isEmpty(benginTime) || StringUtils.isEmpty(endTime) || StringUtils.isEmpty(intervalTime) || StringUtils.isEmpty(timeRange)) {
            map.put("message", "参数错误");
            map.put("status", "1");
            logger.info("参数错误");
            return map;
        }
        VideoFile videoFile = new VideoFile();
        videoFile.setStatus("-1");
        Example example = new Example(VideoFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ipcid", videoCode);
        criteria.andEqualTo("status", "0");
        criteria.andEqualTo("bz", "自动生成");
        videoFileMapper.updateByExampleSelective(videoFile, example);

        long l = DateUtils.pastDays(DateUtils.parseDate(timeRange, "yyyy-MM-dd"));
        int i1 = Integer.parseInt(intervalTime);
        for (int i = 0; i < l; i++) {
            Date date = DateUtils.nextDay(i);
            String date1 = DateUtils.getDate(date, "yyyy-MM-dd");
            VideoFile videoFile1 = new VideoFile();
            videoFile1.setId(UUIDHelper.getUUIDStr());
            videoFile1.setStatus("0");
            videoFile1.setKssj(date1 + " " + benginTime);
            videoFile1.setJssj(date1 + " " + endTime);
            videoFile1.setIpcid(videoCode);
            videoFile1.setBz("自动生成");
            videoFileMapper.insertSelective(videoFile1);
            i = i + i1;
        }

        map.put("message", "添加任务成功");
        map.put("status", "0");
        logger.info("添加查询任务模板 摄像机编码：" + videoCode + " 开始时间：" + benginTime + " 结束时间：" + endTime + " 每间隔 ：" + intervalTime + "天执行一次  执行到：" + timeRange);
        return map;
    }

    @Override
    public void addGWTask() {
        logger.info("333333333333333333333333333333333333333333333333333333333333333333333333");
        {
            try {
                Example example = new Example(Wxjh.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("status", "0");
                List<Wxjh> wxjhList = wxjhMapper.selectByExample(example);
                List<Map<String,Object>> mapList = new ArrayList<>();
                for (Wxjh wxjh : wxjhList) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("id", wxjh.getId());
                    map.put("status", "1");
                    mapList.add(map);
                    String zyrq = wxjh.getZyrq();
                    String zysd = wxjh.getZysd();
                    if (zysd.contains("(")) {
                        zysd = zysd.substring(0, zysd.indexOf("("));
                    }
                    logger.error("zyrq 字符串："+zyrq+"   zysd :"+zysd );
                    if(!zyrq.contains(" ")||!zysd.contains("-")){
                        wxjhList.remove(wxjh);
                        continue;
                    }
                    String kaishi = zyrq.substring(0, zyrq.indexOf(" ")) + " " + zysd.split("-")[0] + ":00";
                    String jieshu = zyrq.substring(0, zyrq.indexOf(" ")) + " " + zysd.split("-")[1] + ":00";
                    Date kdate = DateUtils.parseDate(kaishi, "yyyy-MM-dd HH:mm:ss");
                    Date jdate = DateUtils.parseDate(jieshu, "yyyy-MM-dd HH:mm:ss");
                    if (kdate.getTime() > jdate.getTime()) {
                        jieshu = DateUtils.getDate(new Date(kdate.getTime() + 24 * 3600 * 1000), "yyyy-MM-dd") + " " + zysd.split("-")[1] + ":00";
                    }
                    if (wxjh != null && wxjh.getId() != null && wxjh.getZyzzlc() != null && wxjh.getZyqslc() != null) {
                        addVideoTask(wxjh.getId(), "wxjh", wxjh.getZyqslc().replace(".", ""), wxjh.getZyzzlc().replace(".", ""), kaishi, jieshu);
                    }
                }
                if (wxjhList != null && wxjhList.size() > 0) {

                    wxjhMapper.updateChartParamByAccountAndChartid(mapList);
                }
            } catch (Exception e) {
                logger.error("执行维修计划定时插入视频任务失败");
                logger.error(e.getMessage(), e);
            }


        }
        {
            try {
                Example example = new Example(Hcsj.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("status", "0");
                List<Hcsj> hcsjList = hcsjMapper.selectByExample(example);
                List<Map<String,Object>> mapList = new ArrayList<>();
                for (Hcsj hcsj : hcsjList) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("id", hcsj.getId());
                    map.put("status", "1");
                    mapList.add(map);
                    String jcsj = hcsj.getJcsj();
                    Date date = DateUtils.parseDate(jcsj.substring(0, jcsj.indexOf(".")), "yyyy-MM-dd HH:mm:ss");
                    Long dateL = date.getTime();
                    String kaishi = DateUtils.getDate(new Date(dateL - 10000), "yyyy-MM-dd HH:mm:ss");
                    String jieshu = DateUtils.getDate(new Date(dateL + 10000), "yyyy-MM-dd HH:mm:ss");
                    if (hcsj != null && hcsj.getId() != null && hcsj.getLc() != null) {

                        serviceNetty.addVideoTask(hcsj.getId(), "hcsj", hcsj.getLc().replace(".", ""), kaishi, jieshu);
                    }
                    hcsj.setStatus("1");
                }
                if (hcsjList != null && hcsjList.size() > 0) {

                    hcsjMapper.updateChartParamByAccountAndChartid(mapList);
                }
            } catch (Exception e) {
                logger.error("执行晃车定时插入视频任务失败");
                logger.error(e.getMessage(), e);
            }
        }
        {
            try {
                Example example = new Example(Sgjh.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("status", "0");
                List<Sgjh> sgjhList = sgjhMapper.selectByExample(example);
                List<Map<String,Object>> mapList = new ArrayList<>();
                for (Sgjh sgjh : sgjhList) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("id", sgjh.getId());
                    map.put("status", "1");
                    mapList.add(map);
                    if (sgjh != null && sgjh.getId() != null && sgjh.getZyqslc() != null && sgjh.getZyzzlc() != null && sgjh.getJhqssj() != null && sgjh.getJhzzsj() != null) {

                        addVideoTask(sgjh.getId(), "sgjh", sgjh.getZyqslc().replace(".", ""), sgjh.getZyzzlc().replace(".", ""), sgjh.getJhqssj().substring(0, sgjh.getJhqssj().indexOf(".")), sgjh.getJhzzsj().substring(0, sgjh.getJhzzsj().indexOf(".")));
                    }
                    sgjh.setStatus("1");
                }
                if (sgjhList != null && sgjhList.size() > 0) {

                    sgjhMapper.updateChartParamByAccountAndChartid(mapList);
                }
            } catch (Exception e) {
                logger.error("执行施工计划定时插入视频任务失败");
                logger.error(e.getMessage(), e);
            }
        }
        logger.info("44444444444444444444444444444444444444444444444444444444444444444");
    }

    @Override
    public Map<String,Object> getCamerainfoList() {
        Map<String,Object> map = new HashMap<>();
        try {
            List<Camera> cameras = cameraMapper.selectAll();
            map.put("status", "0");
            map.put("data", cameras);
            map.put("message", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }
        return map;
    }
    @Override
    public Map<String,Object> getCamerainfoListPage(Camera camera,String pageIndex, String pageSize) {

        Map<String,Object> map = new HashMap<>();
        try {

            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<Camera> pageInfo = new PageInfo<>( cameraMapper.selectAllCamera(camera));

            List<Camera> cameras = pageInfo.getList();
            map.put("status", "0");
            map.put("data", pageInfo);
            map.put("message", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getCamerainfoByk(String kMark) {
        Map<String,Object> map = new HashMap<>();
        try {
            Example example = new Example(Camera.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("kMark", kMark);
            List<Camera> cameras = cameraMapper.selectByExample(example);
            map.put("status", "0");
            map.put("data", cameras);
            map.put("message", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> addTable() {
        Map<String,Object> map=new HashMap<>();

        JSONArray jsonArray = new JSONArray();
        List<Camera> cameras = cameraMapper.selectAll();
        List<String > cameraList=new ArrayList<>();
        for (Camera camera : cameras) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("device_id",camera.getDeviceId()==null?"":camera.getDeviceId());
            jsonObject.put("device_name",camera.getDeviceName()==null?"":camera.getDeviceName());
            jsonObject.put("node_id",camera.getNodeId()==null?"":camera.getNodeId());
            jsonObject.put("device_type",camera.getDeviceType()==null?"":camera.getDeviceType());
            jsonObject.put("manufacturer",camera.getManufacturer()==null?"":camera.getManufacturer());
            jsonObject.put("camera_type",camera.getCameraType()==null?"":camera.getCameraType());
            jsonObject.put("Camera_dpi",camera.getCameraDpi()==null?"":camera.getCameraDpi());
            jsonObject.put("ip_addr",camera.getIpAddr()==null?"":camera.getIpAddr());
            jsonObject.put("ip_port",camera.getIpPort()==null?"":camera.getIpPort());
            jsonObject.put("username",camera.getUsername()==null?"":camera.getUsername());
            jsonObject.put("password",camera.getPassword()==null?"":camera.getPassword());
            jsonObject.put("install_time",camera.getInstallTime()==null?"":camera.getInstallTime());
            jsonObject.put("affiliation",camera.getAffiliation()==null?"":camera.getAffiliation());
            jsonObject.put("up_down",camera.getUpDown()==null?"":camera.getUpDown());
            jsonObject.put("associated_line",camera.getAssociatedLine()==null?"":camera.getAssociatedLine());
            jsonObject.put("k_mark",camera.getkMark()==null?"":camera.getkMark());
            jsonObject.put("join_station",camera.getJoinStation()==null?"":camera.getJoinStation());
            jsonObject.put("direction",camera.getDirection()==null?"":camera.getDirection());
            jsonObject.put("target_location",camera.getTargetLocation()==null?"":camera.getTargetLocation());
            jsonObject.put("catalogue",camera.getCatalogue()==null?"":camera.getCatalogue());
            jsonObject.put("longitude",camera.getLongitude()==null?"":camera.getLongitude());
            jsonObject.put("latitude",camera.getLatitude()==null?"":camera.getLatitude());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("total",cameras.size());
        jsonParam.put("fieldNum",22);
        jsonParam.put("data",jsonArray);
        String sendAddTableUrl = Properties.getSendAddTableUrl();
        String Authorization=Properties.getAuthorization();
        String s = HttpClientUt.doPost(sendAddTableUrl, jsonParam.toJSONString(), Authorization);
        map.put("返回数据",s);
        logger.info(jsonParam.toJSONString());
        return map;
    }

    @Override
    public String videoPlayOpen(String ipcid, String type, String starttime, String endtime) {
        Map<String,Object> map = new HashMap<>();
        map.put("ipcid", ipcid);
        map.put("type", type);
        map.put("starttime", starttime);
        map.put("endtime", endtime);
        String url = Properties.getVideoPlayOpenUrl();
        String jsonData = HttpClientUt.doPostMap(url, map);
        logger.info("url"+url);
        return jsonData;
    }

    @Override
    public Map<String,Object> getDictionary(Dictionary dictionary) {
        Map<String,Object> map = new HashMap<>();
        try {
            List<Dictionary> dictionaryList=new ArrayList<>();
            List<Dictionary> select = dictionaryMapper.select(dictionary);
            for (Dictionary dictionary1 : select) {
                if(dictionary1.getType()!=null&&dictionary1.getType().equals("videoAlarmType")&&dictionary1.getName()!=null&&dictionary1.getName().equals("lstj")){
                    dictionaryList.add(dictionary1);
                }
            }
            for (Dictionary dictionary1 : select) {
                if(dictionary1.getType()!=null&&dictionary1.getType().equals("videoAlarmType")&&dictionary1.getName()!=null&&dictionary1.getName().equals("lstj")){
                }else{
                    dictionaryList.add(dictionary1);
                }
            }
            map.put("status", "0");
            map.put("data", dictionaryList);
            map.put("message", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }

        return map;
    }

    @Override
    public Map<String,Object> getVideoRecord(String videoType, String cameraType, String cameraName, String startTime, String endTime) {


        Map<String,Object> map = new HashMap<>();
        try {
            List<String > list=new ArrayList<>();
            List<VideoFile> videoFileList=videoFileMapper.getVideoRecord(videoType,cameraType,cameraName,startTime,endTime,list);
            map.put("status", "0");
            map.put("data", videoFileList);
            map.put("message", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }
        return map;
    }
    @Override
    public Map<String,Object> getVideoRecordPage(String videoType, String cameraType, String cameraName, String startTime, String endTime,String  status,String pageIndex,String pageSize) {


        Map<String,Object> map = new HashMap<>();
        try {
            List<String> list=null;
            if(StringUtils.isNotEmpty(status)){
                list=new ArrayList<>();
                String[] strs=status.split(",");
                for (String str : strs) {
                    list.add(str);
                }
            }
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<VideoFile> pageInfo = new PageInfo<>( videoFileMapper.getVideoRecord(videoType,cameraType,cameraName,startTime,endTime,list));
            List<String> videoFileIds=new ArrayList<>();
            List<String> videoIPCId=new ArrayList<>();
            List<VideoFile> videoFileList=pageInfo.getList();
            for (VideoFile videoFile : videoFileList) {
                videoFileIds.add(videoFile.getId());
                videoIPCId.add(videoFile.getIpcid());
            }
            if(videoFileIds.size()>0){
                List<VideoFile> videotags= videoFileMapper.selectByVideoFileId(videoFileIds);
                for (VideoFile videotag : videotags) {
                    for (VideoFile videoFile : videoFileList) {
                        if(StringUtils.equals(videoFile.getId(),videotag.getId())){
                            videoFile.setVideoTag(videotag.getVideoTag());
                        }
                    }
                }
            }

            if(videoIPCId.size()>0){
                List<VideoFile> videotags=videoFileMapper.selectByVideoFag(videoIPCId);
                for (VideoFile videotag : videotags) {
                    for (VideoFile videoFile : videoFileList) {
                        if(StringUtils.equals(videoFile.getId(),videotag.getId())){
                            videoFile.setIpcTags(videotag.getIpcTags());
                        }
                    }
                }
            }

            map.put("status", "0");
            map.put("data", pageInfo);
            map.put("message", "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("status", "1");
            map.put("message", "查询失败");
        }
        return map;
    }
    @Override
    public Client  getGWConnection(String url){
        if(url==null||"".equals(url))
        url=Properties.getWsdlUrl();
        Client connection = CXFUtil.getConnection(url);
        if(connection!=null){
            try {
                FZMap.clientTokenLock.writeLock().lock();
                FZMap.clientToken.put("GW",connection);

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                FZMap.clientTokenLock.writeLock().unlock();
            }
        }
        return connection;
    }

    @Override
    public Map<String,String> addIpcTag(IpcTag ipcTag) {
        Map<String,String > map = new HashMap<>();
        Dictionary dictionary = dictionaryMapper.selectByPrimaryId(Integer.parseInt(ipcTag.getTag()));
        ipcTag.setTag(dictionary.getDescs());
        ipcTag.setId(UUIDHelper.getUUID());
        int i = ipcTagMapper.insertSelective(ipcTag);
        map.put("status", "1");
        map.put("message", "保存失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "保存成功");
        }
        logger.info("调用addIpcTag 添加摄像机标签 "+ipcTag.toString());
        return map;
    }

    @Override
    public Map<String,Object> updataIpcTag(IpcTag ipcTag) {
        Map<String,Object > map = new HashMap<>();
        int i = ipcTagMapper.updateByPrimaryKeySelective(ipcTag);
        map.put("status", "1");
        map.put("message", "跟新失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "跟新成功");
        }
        logger.info("调用updataIpcTag 跟新摄像机标签 "+ipcTag.toString() );
        return map;
    }

    @Override
    public Map<String,Object> deleteIpcTag(List ids) {
        Map<String,Object > map = new HashMap<>();

        Example example = new Example(IpcTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        int i = ipcTagMapper.deleteByExample(example);
        map.put("status", "1");
        map.put("message", "删除失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "删除成功");
        }
        logger.info("调用deleteIpcTag 删除摄像机标签 id="+ids);
        return map;
    }

    @Override
    public Map<String,Object> getIpcTag(IpcTag ipcTag,String pageIndex,String pageSize) {
        Map<String,Object > map = new HashMap<>();

        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        PageInfo<IpcTag> pageInfo = new PageInfo<>( ipcTagMapper.selectIpc(ipcTag));

        map.put("status", "1");
        map.put("message", "查询失败");
        if(pageInfo!=null){
            map.put("status", "0");
            map.put("data",pageInfo);
            map.put("message", "查询摄像机标签成功");
        }
        logger.info("调用getIpcTag 查询摄像机标签");
        return map;
    }

    @Override
    public Map<String,Object> deleteVideoRecord(List ids) {
        Map<String,Object > map = new HashMap<>();

        Example example = new Example(IpcTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        int i = videoFileMapper.deleteByExample(example);
        map.put("status", "1");
        map.put("message", "删除失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "删除成功");
        }
        LogRest log = new LogRest();
        log.setFunname("deleteVideoRecord");
        log.setIp("");
        log.setLrsj(new Date());
        log.setParamin("调用deleteVideoRecord 删除视频任务");
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data", ids);
        log.setRedata("" + jsonParam.toJSONString());
        log.setType(0 + "");
        logRestMapper.insert(log);

        logger.info("调用deleteVideoRecord 删除视频任务 id="+ids);
        return map;
    }

    @Override
    public Map<String,Object> updateVideoRecord(VideoFile videoFile) {
        Map<String,Object > map = new HashMap<>();
        int i = videoFileMapper.updateByPrimaryKeySelective(videoFile);
        map.put("status", "1");
        map.put("message", "更新失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "更新成功");
        }
        logger.info("调用updataIpcTag 更新摄像机标签 "+videoFile.toString());
        return map;
    }

    @Override
    public Map<String,String> addVideoRecord(VideoFile videoFile) {
        Map<String,String > map = new HashMap<>();
        videoFile.setId(UUIDHelper.getUUIDStr());
        videoFile.setStatus("0");
        if(StringUtils.isEmpty(videoFile.getVideoType())){
            videoFile.setVideoType("zidingyi");
        }else{
            Dictionary dictionary = dictionaryMapper.selectByPrimaryId(Integer.parseInt(videoFile.getVideoType()));
            if(dictionary!=null){
                videoFile.setVideoType(dictionary.getName());
            }else{
                videoFile.setVideoType("没有查找字典");
            }
        }
        int i = videoFileMapper.insertSelective(videoFile);
        map.put("status", "1");
        map.put("message", "保存视频任务失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "保存视频任务成功");
            map.put("data", videoFile.getId());
        }
        logger.info("调用addVideoRecord 保存视频任务 "+videoFile.toString());
        return map;
    }

    @Override
    public Map<String,Object> addDictionary(Dictionary dictionary) {
        Map<String,Object > map = new HashMap<>();

        int i = dictionaryMapper.insertSelective(dictionary);
        map.put("status", "1");
        map.put("message", "保存字典失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "保存字典成功");
        }
        logger.info("调用addDictionary 保存字典 "+dictionary.toString());
        return map;
    }

    @Override
    public Map<String,Object> updateDictionary(Dictionary dictionary) {
        Map<String,Object > map = new HashMap<>();
        int i = dictionaryMapper.updateByPrimaryKeySelective(dictionary);
        map.put("status", "1");
        map.put("message", "修改字典失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "修改字典成功");
        }
        logger.info("调用 updateDictionary 修改字典 "+ dictionary.toString());
        return map;
    }

    @Override
    public Map<String,Object> deleteDictionary(Dictionary dictionary) {
        Map<String,Object > map = new HashMap<>();
        int i = dictionaryMapper.deleteByPrimaryKey(dictionary.getId());
        map.put("status", "1");
        map.put("message", "删除字典失败");
        if(i>0){
            map.put("status", "0");
            map.put("message", "删除字典成功");
        }

        LogRest log = new LogRest();
        log.setFunname("deleteDictionary");
        log.setIp("");
        log.setLrsj(new Date());
        log.setParamin("调用 deleteDictionary 删除字典");
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data",  dictionary.toString());
        log.setRedata("" + jsonParam.toJSONString());
        log.setType(0 + "");
        logRestMapper.insert(log);

        logger.info("调用 deleteDictionary 删除字典 "+ dictionary.toString());
        return map;
    }

    @Override
    public Map<String,Object> addVideoTag(VideoTag videoTag) {
        Map<String,Object > map = new HashMap<>();
        List<VideoTag> listTags=new ArrayList<>();
        if(videoTag==null||StringUtils.isEmpty(videoTag.getIpcid())
                ||StringUtils.isEmpty(videoTag.getVideoFileId())
                ||videoTag.getTags()==null){
            map.put("status", "1");
            map.put("message", "参数错误");
            return map;
        }
        List<String> tags = videoTag.getTags();
        for (String tag : tags) {
            VideoTag vt=new VideoTag();
            vt.setIpcid(videoTag.getIpcid());
            vt.setVideoFileId(videoTag.getVideoFileId());
            vt.setTag(tag);
            vt.setId(UUIDHelper.getUUID());
            listTags.add(vt);
        }
        videoTagMapper.saveAll(listTags);
        map.put("status", "0");
        map.put("message", "保存成功");
        return map;
    }

    @Override
    public Map<String,Object> updateVideoTag(VideoTag videoTag) {
        Map<String,Object > map = new HashMap<>();
        List<VideoTag> listTags=new ArrayList<>();
        if(videoTag==null||StringUtils.isEmpty(videoTag.getId())){
            map.put("status", "1");
            map.put("message", "参数错误");
            return map;
        }

        int i = videoTagMapper.updateByPrimaryKeySelective(videoTag);
        if(i>0){

            map.put("status", "0");
            map.put("message", "保存成功");
        }else{
            map.put("status", "1");
            map.put("message", "保存失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> deleteVideoTag(List<String> ids) {
        Map<String,Object > map = new HashMap<>();
        List<VideoTag> listTags=new ArrayList<>();
        if(ids==null||ids.size()<1){
            map.put("status", "1");
            map.put("message", "参数错误");
            return map;
        }
        Example example = new Example(VideoTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        int i = videoTagMapper.deleteByExample(example);
        if(i>0){

            map.put("status", "0");
            map.put("message", "删除成功");
        }else{
            map.put("status", "1");
            map.put("message", "删除失败");
        }
        return map;
    }

    @Override
    public Map<String,Object> getVideoTag(VideoTag videoTag, String pageIndex, String pageSize) {
        Map<String,Object > map = new HashMap<>();

        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        PageInfo<VideoTag> pageInfo = new PageInfo<>( videoTagMapper.selectIpc(videoTag));

        map.put("status", "1");
        map.put("message", "查询失败");
        if(pageInfo!=null){
            map.put("status", "0");
            map.put("data",pageInfo);
            map.put("message", "查询摄像机标签成功");
        }
        logger.info("调用getIpcTag 查询摄像机标签");
        return map;
    }
}
