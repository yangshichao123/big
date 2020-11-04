package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.NewSnowAlarm;

public interface NewSnowAlarmMapper extends BaseMapper<NewSnowAlarm> {
    int insert(NewSnowAlarm record);

    int insertSelective(NewSnowAlarm record);

    NewSnowAlarm selectByPrimaryId(String id);
}