package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.SensorStatusAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SensorStatusAlarmMapper extends BaseMapper<SensorStatusAlarm> {
    int insert(SensorStatusAlarm record);

    int insertSelective(SensorStatusAlarm record);

    SensorStatusAlarm selectByPrimaryId(String id);
}