package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.NewRainAlarm;

public interface NewRainAlarmMapper extends BaseMapper<NewRainAlarm> {
    int insert(NewRainAlarm record);

    int insertSelective(NewRainAlarm record);

    NewRainAlarm selectByPrimaryId(String id);
}