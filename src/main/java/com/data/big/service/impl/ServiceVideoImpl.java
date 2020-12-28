package com.data.big.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.big.log.LogExeManager;
import com.data.big.log.LogTaskFactory;
import com.data.big.mapper.*;
import com.data.big.model.*;
import com.data.big.service.ServiceFZ;
import com.data.big.service.ServiceVideo;
import com.data.big.util.*;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Slf4j
@org.springframework.stereotype.Service("ServiceVideo")
public class ServiceVideoImpl implements ServiceVideo {


    @Autowired
    private VideoTypeMapper videoTypeMapper;
    @Autowired
    private VideoUploadMapper videoUploadMapper;
    @Autowired
    private LogTaskFactory logTaskFactory;
    @Autowired
    private ImgTaskMapper imgTaskMapper;
    @Autowired
    private ImgUploadMapper imgUploadMapper;


    @Override
    public Message addVideoType(List<VideoType> listVideType) {
        Message message = new Message();
        try {
            videoTypeMapper.saveAll(listVideType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }

    @Override
    public Message updateVideoType(List<VideoType> listVideType) {
        Message message = new Message();
        try {
            videoTypeMapper.updateAll(listVideType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("更新失败");
            return message;
        }
        message.ok("更新成功", "");
        return message;
    }

    @Override
    public Message deleteVideoType(List<VideoType> listVideType) {
        Message message = new Message();
        try {
            videoTypeMapper.deleteAll(listVideType);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getVideoType(VideoType videType) {
        Message message = new Message();
        List<VideoType> videoTypes = null;
        try {
            PageHelper.startPage(Integer.parseInt(videType.getPageIndex()), Integer.parseInt(videType.getPageSize()));
            PageInfo<VideoType> pageInfo = new PageInfo<>(videoTypeMapper.selectVideoTypeAll(videType));

            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return message;
    }

    @Override
    public Message addVideoUpload(List<VideoUpload> listVideUpoad) {
        Message message = new Message();
        try {
            videoUploadMapper.saveAll(listVideUpoad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }

    @Override
    public Message updateVideoUpload(List<VideoUpload> listVideUpoad) {
        Message message = new Message();
        try {
            videoUploadMapper.updateAll(listVideUpoad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("更新失败");
            return message;
        }
        message.ok("更新成功", "");
        return message;
    }

    @Override
    public Message deleteVideoUpload(List<VideoUpload> listVideUpoad) {
        Message message = new Message();
        try {
            videoUploadMapper.deleteAll(listVideUpoad);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getVideoUpload(String type, String videoTypeTag, String videoType, String cameraType, String cameraName, String startTime, String endTime, String status, String pageIndex, String pageSize) {
        Message message = new Message();
        List<VideoType> videoTypes = null;
        try {
            List<String> list = null;
            if (StringUtils.isNotEmpty(status)) {
                list = new ArrayList<>();
                String[] strs = status.split(",");
                for (String str : strs) {
                    list.add(str);
                }
            }
            PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
            PageInfo<VideoUpload> pageInfo = new PageInfo<>(videoUploadMapper.selectVideoUpoadAll(type, videoTypeTag, videoType, cameraType, cameraName, startTime, endTime, list));
            List<String> videoFileIds = new ArrayList<>();
            List<String> videoIPCId = new ArrayList<>();
            List<VideoUpload> videoFileList = pageInfo.getList();

            for (VideoUpload videoFile : videoFileList) {
                videoFileIds.add(videoFile.getId());
                videoIPCId.add(videoFile.getIpcid());
            }
            if (videoFileIds.size() > 0) {
                List<VideoUpload> videotags = videoUploadMapper.selectByVideoFileId(videoFileIds);
                for (VideoUpload videotag : videotags) {
                    for (VideoUpload videoFile : videoFileList) {
                        if (StringUtils.equals(videoFile.getId(), videotag.getId())) {
                            videoFile.setVideoTag(videotag.getVideoTag());
                        }
                    }
                }

                List<VideoUpload> videoTypess = videoUploadMapper.selectVideoTypeByVideoFileId(videoFileIds);
                for (VideoUpload typess : videoTypess) {
                    for (VideoUpload videoFile : videoFileList) {
                        if (StringUtils.equals(videoFile.getId(), typess.getId())) {
                            videoFile.setVideoTypeList(typess.getVideoTypeList());
                        }
                    }
                }

            }

            if (videoIPCId.size() > 0) {
                List<VideoUpload> videotags = videoUploadMapper.selectByVideoFag(videoIPCId);
                for (VideoUpload videotag : videotags) {
                    for (VideoUpload videoFile : videoFileList) {
                        if (StringUtils.equals(videoFile.getId(), videotag.getId())) {
                            videoFile.setIpcTags(videotag.getIpcTags());
                        }
                    }
                }
            }

            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return message;
    }

    @Override
    public Message addVideoImgTask(String bz, String groupId, String tag, String ipcCodes, String benginTime, String endTime, String intervalTime, String time) {
        Message message = new Message();
        if (StringUtils.isEmpty(ipcCodes) || StringUtils.isEmpty(benginTime) || StringUtils.isEmpty(endTime) || StringUtils.isEmpty(time) || StringUtils.isEmpty(bz)) {
            message.error("参数错误");

            log.info("参数错误");
            return message;
        }
        if (StringUtils.isEmpty(groupId)) {
            groupId = benginTime + "-" + endTime;
        }
        if (StringUtils.isEmpty(intervalTime)) {
            intervalTime = "0";
        }
        String[] strs = ipcCodes.split(",");
        List<ImgTask> imgTasks = new ArrayList<>();
        List<Map<String,String>> idList = new ArrayList<>();

        long l = DateUtils.pastDays(DateUtils.parseDate(endTime, "yyyy-MM-dd"), DateUtils.parseDate(benginTime, "yyyy-MM-dd"));
        int i1 = Integer.parseInt(intervalTime);
        for (int i = 0; i <= l; i++) {
            for (String str : strs) {
                Date date = DateUtils.nextDayByDate(DateUtils.parseDate(benginTime, "yyyy-MM-dd"), i);
                String date1 = DateUtils.getDate(date, "yyyy-MM-dd");
                ImgTask imgTask = new ImgTask();
                imgTask.setIpcid(str);
                imgTask.setStatus("0");
                imgTask.setGroupId(groupId);
                imgTask.setZprq(DateUtils.parseDate(date1 + " " + time, "yyyy-MM-dd HH:mm:ss"));
                imgTask.setZpsj(time);
                imgTask.setKsrq(benginTime);
                imgTask.setJsrq(endTime);
                imgTask.setVideoType(tag);
                imgTask.setBz(bz);
                imgTask.setId(UUIDHelper.getUUID());
                Map<String,String> idMap = new HashMap<>();
                idMap.put(imgTask.getIpcid(), imgTask.getId());
                idList.add(idMap);
                imgTasks.add(imgTask);
            }
            i = i + i1;
        }
        if (imgTasks.size() > 0) {
            try {
                imgTaskMapper.saveAll(imgTasks);
            } catch (Exception e) {
                //插入日志
                LogExeManager.getInstance().executeLogTask(logTaskFactory.crezteLog("addVideoImgTask", "1", "", "添加查询任务模板 摄像机编码：" + ipcCodes + " 开始时间：" + benginTime + " 结束时间：" + endTime + " 每间隔 ：" + intervalTime + "天执行一次  执行时间：" + time, "添加任务失败"));
                log.error(e.getMessage(), e);
                message.error("添加任务失败");
                return message;
            }
        }

        //插入日志
        LogExeManager.getInstance().executeLogTask(logTaskFactory.crezteLog("addVideoImgTask", "1", "", "添加查询任务模板 摄像机编码：" + ipcCodes + " 开始时间：" + benginTime + " 结束时间：" + endTime + " 每间隔 ：" + intervalTime + "天执行一次  执行时间：" + time, "添加任务成功"));
        message.ok("添加任务成功", idList);
        log.info("添加查询任务模板 摄像机编码：" + ipcCodes + " 开始时间：" + benginTime + " 结束时间：" + endTime + " 每间隔 ：" + intervalTime + "天执行一次  执行时间：" + time);

        return message;
    }

    @Override
    public Message updateImgTask(List<ImgTask> imgTasks) {
        Message message = new Message();
        try {
            imgTaskMapper.updateAll(imgTasks);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("更新失败");
            return message;
        }
        message.ok("更新成功", "");
        return message;
    }

    @Override
    public Message deleteImgTask(List<ImgTask> imgTasks) {
        Message message = new Message();
        try {
            imgTaskMapper.deleteAll(imgTasks);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getImgTask(ImgTask imgTask) {
        Message message = new Message();
        try {
            PageHelper.startPage(Integer.parseInt(imgTask.getPageIndex()), Integer.parseInt(imgTask.getPageSize()));
            PageInfo<ImgTask> pageInfo = new PageInfo<>(imgTaskMapper.findAll(imgTask));
            List<String> videoFileIds = new ArrayList<>();
            List<String> videoIPCId = new ArrayList<>();
            List<ImgTask> videoFileList = pageInfo.getList();
            for (ImgTask videoFile : videoFileList) {
                videoFileIds.add(videoFile.getId());
                videoIPCId.add(videoFile.getIpcid());
            }
            if (videoFileIds.size() > 0) {
                List<ImgTask> videotags = imgTaskMapper.selectByVideoFileId(videoFileIds);
                for (ImgTask videotag : videotags) {
                    for (ImgTask videoFile : videoFileList) {
                        if (StringUtils.equals(videoFile.getId(), videotag.getId())) {
                            videoFile.setVideoTags(videotag.getVideoTags());
                        }
                    }
                }
            }

            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("失败失败");
            return message;
        }
        return message;
    }

    @Override
    public Message addImgUpload(List<ImgUpload> imgUploads) {
        Message message = new Message();
        try {
            imgUploadMapper.saveAll(imgUploads);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }

    @Override
    public Message deleteImgUpload(List<ImgUpload> imgUploads) {
        Message message = new Message();
        try {
            imgUploadMapper.deleteAll(imgUploads);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message updateImgUpload(List<ImgUpload> imgUploads) {
        Message message = new Message();
        try {
            imgUploadMapper.updateAll(imgUploads);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("跟新失败");
            return message;
        }
        message.ok("跟新成功", "");
        return message;
    }

    @Override
    public Message getImgUpload(ImgUpload imgUpload) {
        Message message = new Message();
        try {
            PageHelper.startPage(Integer.parseInt(imgUpload.getPageIndex()), Integer.parseInt(imgUpload.getPageSize()));
            PageInfo<ImgUpload> pageInfo = new PageInfo<>(imgUploadMapper.findAll(imgUpload));
            List<String> videoFileIds = new ArrayList<>();
            List<String> videoIPCId = new ArrayList<>();
            List<ImgUpload> videoFileList = pageInfo.getList();
            for (ImgUpload videoFile : videoFileList) {
                videoFileIds.add(videoFile.getId());
                videoIPCId.add(videoFile.getIpcid());
            }
            if (videoFileIds.size() > 0) {
                List<ImgUpload> videotags = imgUploadMapper.selectByVideoFileId(videoFileIds);
                for (ImgUpload videotag : videotags) {
                    for (ImgUpload videoFile : videoFileList) {
                        if (StringUtils.equals(videoFile.getId(), videotag.getId())) {
                            videoFile.setVideoTags(videotag.getVideoTags());
                        }
                    }
                }
            }


            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("失败失败");
            return message;
        }
        return message;
    }
}
