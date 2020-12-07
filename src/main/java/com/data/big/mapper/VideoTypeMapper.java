package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.VideoType;

import java.util.List;

public interface VideoTypeMapper extends BaseMapper<VideoType> {
    int insert(VideoType record);

    int insertSelective(VideoType record);

    VideoType selectByPrimaryId(String id);

    /**
     * 批量保存
     * @param listVideType
     */
    void saveAll(List<VideoType> listVideType);

    /**
     * 批量更新
     * @param listVideType
     */
    void updateAll(List<VideoType> listVideType);
    /**
     * 批量删除
     * @param listVideType
     */
    void deleteAll(List<VideoType> listVideType);

    /**
     * 根据属性查询
     * @param videType
     * @return
     */
    List<VideoType>  selectVideoTypeAll(VideoType videType);
}