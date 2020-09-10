package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.VideoKilometer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoKilometerMapper extends BaseMapper<VideoKilometer> {
    int insert(VideoKilometer record);

    int insertSelective(VideoKilometer record);

    VideoKilometer selectByPrimaryId(String id);
}