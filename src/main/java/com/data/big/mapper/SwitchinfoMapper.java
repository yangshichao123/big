package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Switchinfo;

public interface SwitchinfoMapper extends BaseMapper<Switchinfo> {
    int insert(Switchinfo record);

    int insertSelective(Switchinfo record);

    Switchinfo selectByPrimaryId(Integer deviceId);
}