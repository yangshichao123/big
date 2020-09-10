package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.RainAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RainAlarmMapper extends BaseMapper<RainAlarm> {
    int insert(RainAlarm record);

    int insertSelective(RainAlarm record);

    RainAlarm selectByPrimaryId(String id);
}