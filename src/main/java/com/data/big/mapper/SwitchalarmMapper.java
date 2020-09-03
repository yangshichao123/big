package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Switchalarm;

public interface SwitchalarmMapper extends BaseMapper<Switchalarm> {
    int insert(Switchalarm record);

    int insertSelective(Switchalarm record);

    Switchalarm selectByPrimaryId(String id);
}