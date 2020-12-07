package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.ImgTask;

import java.util.List;

public interface ImgTaskMapper extends BaseMapper<ImgTask> {
    int insert(ImgTask record);

    int insertSelective(ImgTask record);

    ImgTask selectByPrimaryId(String id);

    void saveAll(List<ImgTask> imgTaskList);
    void updateAll(List<ImgTask> imgTaskList);
    void deleteAll(List<ImgTask> imgTaskList);


    /**
     * 按条件查询 根据实体类属性查询
     * @param imgTask
     * @return
     */
    List<ImgTask> findAll(ImgTask imgTask);

    List<ImgTask> selectByVideoFileId(List<String> videoFileIds);
}