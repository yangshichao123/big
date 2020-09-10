package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.VideoFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoFileMapper extends BaseMapper<VideoFile> {
    int insert(VideoFile record);

    int insertSelective(VideoFile record);

    VideoFile selectByPrimaryId(String id);
}