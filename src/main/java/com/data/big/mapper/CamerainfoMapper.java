package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Camerainfo;

public interface CamerainfoMapper extends BaseMapper<Camerainfo> {
    int insert(Camerainfo record);

    int insertSelective(Camerainfo record);

    Camerainfo selectByPrimaryId(String deviceId);
}