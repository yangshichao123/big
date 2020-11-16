package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.VideoFile;

import java.util.List;

public interface VideoFileMapper extends BaseMapper<VideoFile> {
    int insert(VideoFile record);

    int insertSelective(VideoFile record);

    VideoFile selectByPrimaryId(String id);

    /**
     * 根据条件查询视频告警信息
     * @param videoType 视频类型
     * @param cameraType 摄像机类型
     * @param cameraName 摄像机名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<VideoFile> getVideoRecord(String videoType, String cameraType, String cameraName, String startTime, String endTime,List<String > list);

    List<VideoFile> selectByVideoFileId(List<String> videoFileIds);

    List<VideoFile> selectByVideoFag(List<String> videoIPCId);
}