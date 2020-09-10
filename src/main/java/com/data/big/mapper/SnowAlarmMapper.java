package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.SnowAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SnowAlarmMapper extends BaseMapper<SnowAlarm> {
    int insert(SnowAlarm record);

    int insertSelective(SnowAlarm record);

    SnowAlarm selectByPrimaryId(String id);
}