package com.data.big.controller;

import com.data.big.model.ImgTask;
import com.data.big.model.ImgUpload;
import com.data.big.model.VideoType;
import com.data.big.model.VideoUpload;
import com.data.big.service.ServiceFZ;
import com.data.big.service.ServiceVideo;
import com.data.big.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/controllerVideo")
@CrossOrigin
public class ControllerVideo {


    @Autowired
    private ServiceVideo serviceVideo;


    @RequestMapping("/addVideoType")
    public Message addVideoType(@RequestBody List<VideoType> listVideType) {

        return serviceVideo.addVideoType(listVideType);
    }
    @RequestMapping("/updateVideoType")
    public Message updateVideoType(@RequestBody List<VideoType> listVideType) {

        return serviceVideo.updateVideoType(listVideType);
    }
    @RequestMapping("/deleteVideoType")
    public Message deleteVideoType(@RequestBody List<VideoType> listVideType) {

        return serviceVideo.deleteVideoType(listVideType);
    }
    @RequestMapping("/getVideoType")
    public Message getVideoType(VideoType videType) {

        return serviceVideo.getVideoType(videType);
    }





    @RequestMapping("/addVideoUpload")
    public Message addVideoUpload(@RequestBody List<VideoUpload> listVideUpoad) {

        return serviceVideo.addVideoUpload(listVideUpoad);
    }

    @RequestMapping("/updateVideoUpload")
    public Message updateVideoUpload(@RequestBody List<VideoUpload> listVideUpoad) {

        return serviceVideo.updateVideoUpload(listVideUpoad);
    }
    @RequestMapping("/deleteVideoUpload")
    public Message deleteVideoUpload(@RequestBody List<VideoUpload> listVideUpoad) {

        return serviceVideo.deleteVideoUpload(listVideUpoad);
    }
    @RequestMapping("/getVideoUpload")
    public Message getVideoUpload(String type,String videoTypeTag,String videoType, String cameraType,String cameraName ,String startTime,String endTime,String  status,String pageIndex,String pageSize) {

        return serviceVideo.getVideoUpload(type,videoTypeTag,videoType,cameraType,cameraName,startTime,endTime,status,pageIndex,pageSize);
    }

    @RequestMapping("/addVideoImgTask")
    public Message  addVideoImgTask(String bz,String groupId,String tag,String ipcCodes,String beginTime,String endTime,String intervalTime,String time) {

        return serviceVideo.addVideoImgTask(bz,groupId,tag,ipcCodes,beginTime,endTime,intervalTime,time);
    }
    @RequestMapping("/updateImgTask")
    public Message  updateImgTask(@RequestBody List<ImgTask> imgTasks) {

        return serviceVideo.updateImgTask(imgTasks);
    }
    @RequestMapping("/deleteImgTask")
    public Message  deleteImgTask(@RequestBody List<ImgTask> imgTasks) {

        return serviceVideo.deleteImgTask(imgTasks);
    }
    @RequestMapping("/getImgTask")
    public Message  getImgTask(ImgTask imgTask) {

        return serviceVideo.getImgTask(imgTask);
    }
    @RequestMapping("/addImgUpload")
    public Message  addImgUpload(@RequestBody List<ImgUpload> imgUploads) {

        return serviceVideo.addImgUpload(imgUploads);
    }
    @RequestMapping("/deleteImgUpload")
    public Message  deleteImgUpload(@RequestBody List<ImgUpload> imgUploads) {

        return serviceVideo.deleteImgUpload(imgUploads);
    }
    @RequestMapping("/updateImgUpload")
    public Message  updateImgUpload(@RequestBody List<ImgUpload> imgUploads) {

        return serviceVideo.updateImgUpload(imgUploads);
    }
    @RequestMapping("/getImgUpload")
    public Message  getImgUpload(ImgUpload imgUpload) {

        return serviceVideo.getImgUpload(imgUpload);
    }
}
