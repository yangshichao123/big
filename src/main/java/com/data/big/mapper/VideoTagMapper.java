package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.VideoTag;

import java.util.List;

public interface VideoTagMapper extends BaseMapper<VideoTag> {
    int insert(VideoTag record);

    int insertSelective(VideoTag record);

    VideoTag selectByPrimaryId(String id);

    /**
     * 批量保存
     * @param listVideTags
     */
    void saveAll(List<VideoTag> listVideTags);

    /**
     * 查询视频标签记录
     * @param videoTag
     * @return
     */
    List<VideoTag> selectIpc(VideoTag videoTag);
}