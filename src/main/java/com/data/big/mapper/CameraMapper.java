package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Camera;

import java.util.List;

public interface CameraMapper extends BaseMapper<Camera> {
    int insert(Camera record);

    int insertSelective(Camera record);

    Camera selectByPrimaryId(String deviceId);

    Camera getMinVideoCode(int k);

    Camera getMxnVideoCode(int parseInt);

    List<Camera> getVideoCode(Integer getkMark, Integer getkMark1);
}