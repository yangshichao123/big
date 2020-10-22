package com.data.big.controller;

import com.data.big.gw.GwaqscJxglService;
import com.data.big.gw.GwaqscJxglServicePortType;
import com.data.big.mapper.WxjhMapper;
import com.data.big.model.*;
import com.data.big.service.Service;
import com.data.big.util.HttpClientUt;
import com.data.big.util.UUIDHelper;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;


@RestController
@RequestMapping("/controller")
@CrossOrigin
public class Controller {


    @Autowired
    private Service service;
  @Autowired
    private WxjhMapper wxjhMapper;

    @RequestMapping("/test")
    @ResponseBody
    public Map test(String startTime, String endTime) {
        Map<String,String> map=new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    @RequestMapping("/GetCameraInfo")
    @ResponseBody
    public Map GetCameraInfo() {

        return service.GetCameraInfo();
    }
    @RequestMapping("/GetCamera")
    @ResponseBody
    public Map GetCamera() {

        return service.GetCamera();
    }

    @RequestMapping("/GetNodeInfo")
    @ResponseBody
    public Map GetNodeInfo() {
        return service.GetNodeInfo();
    }

    @RequestMapping("/GetServerInfo")
    @ResponseBody
    public Map GetServerInfo() {
        return service.GetServerInfo();
    }

    @RequestMapping("/GetDiskInfo")
    @ResponseBody
    public Map GetDiskInfo() {
        return service.GetDiskInfo();
    }

    @RequestMapping("/GetSwitchInfo")
    @ResponseBody
    public Map GetSwitchInfo() {
        return service.GetSwitchInfo();
    }

    @RequestMapping("/GetCameraStatus")
    @ResponseBody
    public Map GetCameraStatus(String startTime, String endTime) {

        return service.GetCameraStatus(startTime, endTime);
    }

    @RequestMapping("/GetServerStatus")
    @ResponseBody
    public Map GetServerStatus(String startTime, String endTime) {
        return service.GetServerStatus(startTime, endTime);
    }

    @RequestMapping("/GeDiskStatus")
    @ResponseBody
    public Map GeDiskStatus(String startTime, String endTime) {
        return service.GeDiskStatus(startTime, endTime);
    }

    @RequestMapping("/GetSwitchStatus")
    @ResponseBody
    public Map GetSwitchStatus(String startTime, String endTime) {
        return service.GetSwitchStatus(startTime, endTime);
    }

    @RequestMapping("/GetCameraAlarm")
    @ResponseBody
    public Map GetCameraAlarm(String startTime, String endTime) {
        return service.GetCameraAlarm(startTime, endTime);
    }

    @RequestMapping("/GetServerAlarm")
    @ResponseBody
    public Map GetServerAlarm(String startTime, String endTime) {
        return service.GetServerAlarm(startTime, endTime);
    }

    @RequestMapping("/GetDiskAlarm")
    @ResponseBody
    public Map GetDiskAlarm(String startTime, String endTime) {
        return service.GetDiskAlarm(startTime, endTime);
    }

    @RequestMapping("/GetSwitchAlarm")
    @ResponseBody
    public Map GetSwitchAlarm(String startTime, String endTime) {
        return service.GetSwitchAlarm(startTime, endTime);
    }

    @RequestMapping("/GetIVSAlarm")
    @ResponseBody
    public Map GetIVSAlarm(String startTime, String endTime) {
        return service.GetIVSAlarm(startTime, endTime);
    }

    @RequestMapping("/executeTask")
    @ResponseBody
    public void executeTask(String startTime, String endTime) {

        service.executeTask(startTime, endTime);
    }

    @RequestMapping("/sendFile")
    @ResponseBody
    public List<Map<String,Object>> sendFile(String sendUrl, String fileUrl, String token) {
        return service.sendFile(sendUrl, fileUrl, token);
    }

    @RequestMapping("/saveVideoTask")
    @ResponseBody
    public Map<String,String> saveVideoTask(String videoId, String startTime, String endTime) {
        return service.saveVideoTask(videoId, startTime, endTime);
    }

    @RequestMapping("/getANBAO3")
    @ResponseBody
    public Map<String,String> getANBAO3() {
        return service.getANBAO3("20161010101010", "20200808010101", "京包客专线", "03");
    }
    @RequestMapping("/getMethodName")
    @ResponseBody
    public List<MethodType> getMethodName() {
        return service.getMethodName();
    }

    @RequestMapping("/addTask")
    @ResponseBody
    public Map<String,String> addTask(String startTime,String endTime,String methodId) {
        return service.addTask(startTime,endTime,methodId);
    }

    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/getVideo")
    @ResponseBody
    public List<Camerainfo> getVideo() {
        return service.getVideo();
    }

    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/addVideoKilometer")
    @ResponseBody
    public Map<String,String> addVideoKilometer(VideoKilometer videoKilometer) {
        return service.addVideoKilometer(videoKilometer);
    }
    @RequestMapping("/getHcsj")
    @ResponseBody
    public Map<String,String> getHcsj(String qsrq, String jsrq, String cxdj,String xm) {

       return service.getHcsj(qsrq,jsrq,cxdj,xm);
    }
    @RequestMapping("/getSgjh")
    @ResponseBody
    public Map<String,String> getSgjh(String qsrq, String jsrq,String xm) {


        return service.getSgjh(qsrq,jsrq,xm);
    }
    @RequestMapping("/getWxjh")
    @ResponseBody
    public Map<String,String> getWxjh(String qsrq, String jsrq,String xm) {
        return service.getWxjh(qsrq,jsrq,xm);
    }
    @RequestMapping("/queryCurrentDayWarningData")
    @ResponseBody
    public Map<String,Object> queryCurrentDayWarningData(String beginTime,String endTime) {
        return service.queryCurrentDayWarningData(beginTime,endTime);
    }
    @RequestMapping("/download")
    @ResponseBody
    public String  download(String sourceUrl,String targetUrl) {
        service.download(sourceUrl,targetUrl);
        return "成功";
    }
    /**
     * 提供接口 给前端
     * @return
     */
    @RequestMapping("/addVideoTask")
    @ResponseBody
    public Map<String ,String >  addVideoTask(String videoCode,String benginTime,String endTime,String intervalTime,String timeRange) {
        return service.addVideoTask(videoCode,benginTime,endTime,intervalTime,timeRange);
    }
    @RequestMapping("/tess")
    @ResponseBody
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
    @ResponseBody
    public Map<String ,Object >  getCamerainfoList() {
        return service.getCamerainfoList();
    }
    /**
     * 提供接口
     * @return
     */
    @RequestMapping("/getCamerainfoByk")
    @ResponseBody
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
    @RequestMapping("/addTbale")
    @ResponseBody
    public Map<String ,Object >  addTbale(@RequestBody String jsonStr) {
        //HttpClientUt.doPost11("http://127.0.0.1:10001/fdCon/addTbale",jsonStr,"33333333");
        //System.out.println( Authorization+"       "+ jsonStr+"    "+tableName);
       // return service.addTable();
        return  null;
    }
    /**
     * 提供接口
     * @return
     */
    @RequestMapping("/getDictionary")
    @ResponseBody
    public Map<String ,Object >  getDictionary(Dictionary dictionary) {

        return  service.getDictionary(dictionary);
    }
    /**
     * 提供接口  查询视频记录 根据条件
     * @return
     */
    @RequestMapping("/getVideoRecord")
    @ResponseBody
    public Map<String ,Object >  getVideoRecord(String videoType, String cameraType,String cameraName ,String startTime,String endTime) {

        return  service.getVideoRecord(videoType,cameraType,cameraName,startTime,endTime);
    }
}
