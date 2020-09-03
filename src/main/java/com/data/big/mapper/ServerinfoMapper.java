package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Serverinfo;

public interface ServerinfoMapper extends BaseMapper<Serverinfo> {
    int insert(Serverinfo record);

    int insertSelective(Serverinfo record);

    Serverinfo selectByPrimaryId(String deviceId);
}