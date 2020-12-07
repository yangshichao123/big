package com.data.big.service;


import com.data.big.model.ImgTask;
import com.data.big.model.ImgUpload;
import com.data.big.model.VideoType;
import com.data.big.model.VideoUpload;
import com.data.big.vo.Message;
import org.apache.cxf.endpoint.Client;

import java.util.List;
import java.util.Map;

public interface ServiceVideo {


    /**
     * 批量插入 视频类型
     *
     * @param listVideType 视频类型集合
     * @return 消息类
     */
    Message addVideoType(List<VideoType> listVideType);

    /**
     * 批量修改视频类型
     *
     * @param listVideType 视频类型集合
     * @return 消息类
     */
    Message updateVideoType(List<VideoType> listVideType);

    /**
     * 批量删除视频类型
     *
     * @param listVideType 视频类型集合
     * @return 消息类
     */
    Message deleteVideoType(List<VideoType> listVideType);

    /**
     * 获取视频类型
     *
     * @param videType 视频类型
     * @return 消息类
     */
    Message getVideoType(VideoType videType);

    /**
     * 批量插入视频下载
     *
     * @param listVideUpoad 视频下载集合
     * @return 消息类
     */
    Message addVideoUpload(List<VideoUpload> listVideUpoad);

    /**
     * 批量跟新视频下载
     *
     * @param listVideUpoad 视频下载集合
     * @return 消息类
     */
    Message updateVideoUpload(List<VideoUpload> listVideUpoad);

    /**
     * 批量删除视频下载
     *
     * @param listVideUpoad 视频下载集合
     * @return 消息类
     */
    Message deleteVideoUpload(List<VideoUpload> listVideUpoad);

    /**
     * 获取视频下载
     *
     * @return 消息类
     */
    Message getVideoUpload(String type,String videoTypeTag, String videoType, String cameraType, String cameraName, String startTime, String endTime, String status, String pageIndex, String pageSize);

    /**
     * 手动添加下载图片任务
     *
     * @param ipcCodes     摄像机code 集合
     * @param benginTime   开始日期
     * @param endTime      结束日期
     * @param intervalTime 间隔
     * @param time         执行时间点
     * @return
     */
    Message addVideoImgTask(String bz,String groupId,String tag,String ipcCodes, String benginTime, String endTime, String intervalTime, String time);

    /**
     * 批量修改
     * @param imgTasks
     * @return
     */
    Message updateImgTask(List<ImgTask> imgTasks);

    /**
     * 批量删除 根据id
     * @param imgTasks
     * @return
     */
    Message deleteImgTask(List<ImgTask> imgTasks);

    /**
     * 根据属性查询数据
     * @param imgTask
     * @return
     */
    Message getImgTask(ImgTask imgTask);

    /**
     * 批量添加图片下载
     * @param imgUploads
     * @return
     */
    Message addImgUpload(List<ImgUpload> imgUploads);
    /**
     * 批量删除图片下载  根据id
     * @param imgUploads
     * @return
     */
    Message deleteImgUpload(List<ImgUpload> imgUploads);
    /**
     * 批量修改图片下载
     * @param imgUploads
     * @return
     */
    Message updateImgUpload(List<ImgUpload> imgUploads);

    /**
     * 查询图片下载
     * @param imgUpload
     * @return
     */
    Message getImgUpload(ImgUpload imgUpload);
}
