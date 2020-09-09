package com.data.big.controller;

import com.data.big.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/controller")
public class Controller {


    @Autowired
    private Service service;

    @RequestMapping("/GetCameraInfo")
    @ResponseBody
    public Map GetCameraInfo() {

        return service.GetCameraInfo();
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

        return service.GetCameraStatus("2020-09-06 20:00:00", "2020-09-07 20:00:00");
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

}
