package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.NewForeignBodyAlarm;

public interface NewForeignBodyAlarmMapper extends BaseMapper<NewForeignBodyAlarm> {
    int insert(NewForeignBodyAlarm record);

    int insertSelective(NewForeignBodyAlarm record);

    NewForeignBodyAlarm selectByPrimaryId(String id);
}