package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.VideoUpload;

import java.util.ArrayList;
import java.util.List;

public interface VideoUploadMapper extends BaseMapper< VideoUpload> {
    int insert(VideoUpload record);

    int insertSelective(VideoUpload record);

    VideoUpload selectByPrimaryId(String id);

    /**
     * 批量添加
     * @param listVideUpoad
     */
    void saveAll(List<VideoUpload> listVideUpoad);
    /**
     * 批量更新
     * @param listVideUpoad
     */
    void updateAll(List<VideoUpload> listVideUpoad);

    /**
     * 批量删除
     * @param listVideUpoad
     */
    void deleteAll(List<VideoUpload> listVideUpoad);

    /**
     * 根据添加查询
     * @return
     */
    ArrayList<VideoUpload> selectVideoUpoadAll(String type, String videoTypeTag, String videoType, String cameraType, String cameraName, String startTime, String endTime, List<String > list);

    List<VideoUpload> selectByVideoFileId(List<String> videoFileIds);

    List<VideoUpload> selectByVideoFag(List<String> videoIPCId);

    /**
     * 根据videoupload
     * @param videoFileIds
     * @return
     */
    List<VideoUpload> selectVideoTypeByVideoFileId(List<String> videoFileIds);
}