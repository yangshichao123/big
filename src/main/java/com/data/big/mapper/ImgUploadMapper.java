package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.ImgTask;
import com.data.big.model.ImgUpload;

import java.util.List;

public interface ImgUploadMapper extends BaseMapper<ImgUpload> {
    int insert(ImgUpload record);

    int insertSelective(ImgUpload record);

    ImgUpload selectByPrimaryId(String id);

    void saveAll(List<ImgUpload> imgUploads);
    void updateAll(List<ImgUpload> imgUploads);
    void deleteAll(List<ImgUpload> imgUploads);


    /**
     * 按条件查询 根据实体类属性查询
     * @param imgUpload
     * @return
     */
    List<ImgUpload> findAll(ImgUpload imgUpload);

    List<ImgUpload> selectByVideoFileId(List<String> videoFileIds);
}