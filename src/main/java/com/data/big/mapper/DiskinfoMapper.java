package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Diskinfo;

public interface DiskinfoMapper extends BaseMapper<Diskinfo> {
    int insert(Diskinfo record);

    int insertSelective(Diskinfo record);

    Diskinfo selectByPrimaryId(String deviceId);
}