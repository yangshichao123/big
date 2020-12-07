package com.data.big.controller;

import com.data.big.mapper.WxjhMapper;
import com.data.big.model.*;
import com.data.big.service.Service;
import com.data.big.service.ServiceFZ;
import com.data.big.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/controller")
@CrossOrigin
public class Controller {


    @Autowired
    private Service service;
  @Autowired
    private WxjhMapper wxjhMapper;
  @Autowired
    private ServiceFZ serviceFZ;

    @RequestMapping("/test")
    public Map test(String startTime, String endTime) {
        Map<String,String> map=new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    @RequestMapping("/GetCameraInfo")
    public Message GetCameraInfo() {

        return service.GetCameraInfo();
    }
    @RequestMapping("/GetCamera")
    public Message GetCamera() {

        return service.GetCamera();
    }

    @RequestMapping("/GetNodeInfo")
    public Message GetNodeInfo() {
        return service.GetNodeInfo();
    }

    @RequestMapping("/GetServerInfo")
    public Map GetServerInfo() {
        return service.GetServerInfo();
    }

    @RequestMapping("/GetDiskInfo")
    public Map GetDiskInfo() {
        return service.GetDiskInfo();
    }

    @RequestMapping("/GetSwitchInfo")
    public Map GetSwitchInfo() {
        return service.GetSwitchInfo();
    }

    @RequestMapping("/GetCameraStatus")
    public Map GetCameraStatus(String startTime, String endTime) {

        return service.GetCameraStatus(startTime, endTime);
    }

    @RequestMapping("/GetServerStatus")
    public Map GetServerStatus(String startTime, String endTime) {
        return service.GetServerStatus(startTime, endTime);
    }

    @RequestMapping("/GeDiskStatus")
    public Map GeDiskStatus(String startTime, String endTime) {
        return service.GeDiskStatus(startTime, endTime);
    }

    @RequestMapping("/GetSwitchStatus")
    public Map GetSwitchStatus(String startTime, String endTime) {
        return service.GetSwitchStatus(startTime, endTime);
    }

    @RequestMapping("/GetCameraAlarm")
    public Map GetCameraAlarm(String startTime, String endTime) {
        return service.GetCameraAlarm(startTime, endTime);
    }

    @RequestMapping("/GetServerAlarm")
    public Map GetServerAlarm(String startTime, String endTime) {
        return service.GetServerAlarm(startTime, endTime);
    }

    @RequestMapping("/GetDiskAlarm")
    public Map GetDiskAlarm(String startTime, String endTime) {
        return service.GetDiskAlarm(startTime, endTime);
    }

    @RequestMapping("/GetSwitchAlarm")
    public Map GetSwitchAlarm(String startTime, String endTime) {
        return service.GetSwitchAlarm(startTime, endTime);
    }

    @RequestMapping("/GetIVSAlarm")
    public Map GetIVSAlarm(String startTime, String endTime) {
        return service.GetIVSAlarm(startTime, endTime);
    }

    @RequestMapping("/executeTask")
    public void executeTask(String startTime, String endTime) {

        service.executeTask(startTime, endTime);
    }

    @RequestMapping("/sendFile")
    public List<Map<String,Object>> sendFile(String sendUrl, String fileUrl, String token) {
        return service.sendFile(sendUrl, fileUrl, token);
    }

    @RequestMapping("/saveVideoTask")
    public Map<String,String> saveVideoTask(String videoId, String startTime, String endTime) {
        return service.saveVideoTask(videoId, startTime, endTime);
    }

    @RequestMapping("/getANBAO3")
    public Map<String,String> getANBAO3() {
        return service.getANBAO3("20161010101010", "20200808010101", "京包客专线", "03");
    }
    @RequestMapping("/getMethodName")
    public List<MethodType> getMethodName() {
        return service.getMethodName();
    }

    @RequestMapping("/addTask")
    public Map<String,String> addTask(String startTime,String endTime,String methodId) {
        return service.addTask(startTime,endTime,methodId);
    }

    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/getVideo")
    public List<Camerainfo> getVideo() {
        return service.getVideo();
    }

    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/addVideoKilometer")
    public Map<String,String> addVideoKilometer(VideoKilometer videoKilometer) {
        return service.addVideoKilometer(videoKilometer);
    }
    @RequestMapping("/getHcsj")
    public Map<String,String> getHcsj(String qsrq, String jsrq, String cxdj,String xm,String size) {
        service.getportType();
       return service.getHcsj(qsrq,jsrq,cxdj,xm,"1",size);
    }
    @RequestMapping("/getSgjh")
    public Map<String,String> getSgjh(String qsrq, String jsrq,String xm) {

        service.getportType();
        return service.getSgjh(qsrq,jsrq,xm);
    }
    @RequestMapping("/getWxjh")
    public Map<String,String> getWxjh(String qsrq, String jsrq,String xm) {
        service.getportType();
        return service.getWxjh(qsrq,jsrq,xm);
    }
    @RequestMapping("/queryCurrentDayWarningData")
    public Map<String,Object> queryCurrentDayWarningData(String beginTime,String endTime) {
        return service.queryCurrentDayWarningData(beginTime,endTime);
    }
    @RequestMapping("/download")
    public String  download(String sourceUrl,String targetUrl) {
        service.download(sourceUrl,targetUrl);
        return "成功";
    }
    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/addVideoTask")
    public Map<String ,String >  addVideoTask(String videoCode,String benginTime,String endTime,String intervalTime,String timeRange) {
        return service.addVideoTask(videoCode,benginTime,endTime,intervalTime,timeRange);
    }
    @RequestMapping("/tess")
    public Map<String ,String >  tess() {
        List<Wxjh> wxjhList = wxjhMapper.selectAll();
        /*for (Wxjh wxjh : wxjhList) {
            wxjh.setId(UUIDHelper.getUUIDStr());
        }*/
        wxjhMapper.insertCodeBatch(wxjhList);


        return null;
    }

    /**
     * 提供接口
     * @return
     */
    @RequestMapping("/getCamerainfoList")
    public Map<String ,Object >  getCamerainfoList() {
        return service.getCamerainfoList();
    } /**
     * 提供接口
     * @return
     */
    @RequestMapping("/getCamerainfoListPage")
    public Map<String ,Object >  getCamerainfoListPage(Camera camera,String pageIndex, String pageSize) {

        return service.getCamerainfoListPage(camera,pageIndex,pageSize);
    }


    /**
     * 提供接口
     * @return
     */
    @RequestMapping("/getCamerainfoByk")
    public Map<String ,Object >  getCamerainfoByk(String KMark) {

        return service.getCamerainfoByk(KMark);
    }
    /**
     * 提供接口
     * @return
     */
    @RequestMapping("/VideoPlayOpen")
    public String   videoPlayOpen(String ipcid,String type,String starttime,String endtime) {

        return service.videoPlayOpen(ipcid,type,starttime,endtime);

    }
    /**
     * 提供接口  查询字典
     * @return
     */
    @RequestMapping("/getDictionary")
    public Map<String ,Object >  getDictionary(Dictionary dictionary) {

        return  service.getDictionary(dictionary);
    }
    /**
     * 提供接口  查询视频记录 根据条件
     * @return
     */
    @RequestMapping("/getVideoRecordPage")
    public Map<String ,Object >  getVideoRecordPage(String videoTypeTag,String videoType, String cameraType,String cameraName ,String startTime,String endTime,String  status,String pageIndex,String pageSize) {

        return  service.getVideoRecordPage(videoTypeTag,videoType,cameraType,cameraName,startTime,endTime,status,pageIndex,pageSize);
    }
    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/getVideoRecord")
    public Map<String ,Object >  getVideoRecord(String videoType, String cameraType,String cameraName ,String startTime,String endTime) {

        return  service.getVideoRecord(videoType,cameraType,cameraName,startTime,endTime);
    }
    @RequestMapping("/addTbale")
    public Map<String ,Object >  addTbale(@RequestBody String jsonStr) {
        //HttpClientUt.doPost11("http://127.0.0.1:10001/fdCon/addTbale",jsonStr,"33333333");
        //System.out.println( Authorization+"       "+ jsonStr+"    "+tableName);
        // return service.addTable();
        return  null;
    }
    /**
     * 提供接口  删除视频任务信息
     * @return
     */
    @RequestMapping("/deleteVideoRecord")
    public Map<String ,Object >  deleteVideoRecord( @RequestParam(value = "ids") List<String> ids) {

        return  service.deleteVideoRecord(ids);
    }
    /**
     * 提供接口  修改视频任务信息
     * @return
     */
    @RequestMapping("/updateVideoRecord")
    public Map<String ,Object >  updateVideoRecord(VideoFile videoFile) {

        return  service.updateVideoRecord(videoFile);
    }

    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/addVideoRecord")
    public Map<String ,String >  addVideoRecord(VideoFile videoFile) {
        return service.addVideoRecord(videoFile);
    }

    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/getFzAlarm")
    public Map<String ,Object >  getFzAlarm( String alarmType,String km ,String startTime,String endTime,String pageIndex,String pageSize) {

        return  serviceFZ.getFzAlarm(alarmType,km,startTime,endTime,pageIndex,pageSize);
    }
    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/getFz")
    public Map<String ,Object >  getFz(@RequestParam Map map) {
        serviceFZ.getClient();
        serviceFZ.login();
        map.put("bureauCode", "C");
        map.put("lineCode", "30142");
        map.put("monitorCode", "");
        map.put("alarmLevel", "");
        map.put("type", "");
        //map.put("startTime", beginTime);
       // map.put("endTime", endTime);

        return  serviceFZ.getFZAlarm(map);
    }

   /* @RequestMapping("/getGWConn")
    @ResponseBody
    public Map<String ,String >  getGWConn( ) {

        return  service.getportType();
    }*/
    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/addIpcTag")
    public Map<String ,String >  addIpcTag( IpcTag ipcTag) {

        return  service.addIpcTag(ipcTag);
    }
    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/updataIpcTag")
    public Map<String ,Object >  updataIpcTag( IpcTag ipcTag) {

        return  service.updataIpcTag(ipcTag);
    }
    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/deleteIpcTag")
    public Map<String ,Object >  deleteIpcTag( @RequestParam(value = "ids") List<String> ids) {

        return  service.deleteIpcTag(ids);
    }
    /**
     * 提供接口  查询视频记录 根据条件 分页
     * @return
     */
    @RequestMapping("/getIpcTag")
    public Map<String ,Object >  getIpcTag( IpcTag ipcTag,String pageIndex,String pageSize) {

        return  service.getIpcTag(ipcTag,pageIndex,pageSize);
    }
    /**
     * 提供接口  增加字典
     * @return
     */
    @RequestMapping("/addDictionary")
    public Map<String ,Object >  addDictionary( Dictionary dictionary) {

        return  service.addDictionary(dictionary);
    }
    /**
     * 提供接口  修改字典
     * @return
     */
    @RequestMapping("/updateDictionary")
    public Map<String ,Object >  updateDictionary( Dictionary dictionary) {

        return  service.updateDictionary(dictionary);
    }
    /**
     * 提供接口  删除字典
     * @return
     */
    @RequestMapping("/deleteDictionary")
    public Map<String ,Object >  deleteDictionary( Dictionary dictionary) {

        return  service.deleteDictionary(dictionary);
    }
    /**
     * 提供接口  添加任务视频标签
     * @return
     */
    @RequestMapping("/addVideoTag")
    public Map<String ,Object >  addVideoTag( VideoTag videoTag) {

        return  service.addVideoTag(videoTag);
    }

    /**
     * 提供接口  修改任务视频标签
     * @return
     */
    @RequestMapping("/updateVideoTag")
    public Map<String ,Object >  updateVideoTag( VideoTag videoTag) {

        return  service.updateVideoTag(videoTag);
    }

    /**
     * 提供接口  删除任务视频标签
     * @return
     */
    @RequestMapping("/deleteVideoTag")
    public Map<String ,Object >  deleteVideoTag(@RequestParam(value = "ids") List<String> ids ) {

        return  service.deleteVideoTag(ids);
    }

    /**
     * 提供接口  获取任务视频标签
     * @return
     */
    @RequestMapping("/getVideoTag")
    public Map<String ,Object >  getVideoTag( VideoTag videoTag,String pageIndex,String pageSize) {

        return  service.getVideoTag(videoTag,pageIndex,pageSize);
    }

}
