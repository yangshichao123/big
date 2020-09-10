package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.ForeignBodyAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ForeignBodyAlarmMapper extends BaseMapper<ForeignBodyAlarm> {
    int insert(ForeignBodyAlarm record);

    int insertSelective(ForeignBodyAlarm record);

    ForeignBodyAlarm selectByPrimaryId(String id);
}