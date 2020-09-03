package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.WindSpeedAlarm;

public interface WindSpeedAlarmMapper extends BaseMapper<WindSpeedAlarm> {
    int insert(WindSpeedAlarm record);

    int insertSelective(WindSpeedAlarm record);

    WindSpeedAlarm selectByPrimaryId(String id);
}