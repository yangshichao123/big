package com.data.big.mapper;

import java.util.List;

import com.data.big.base.BaseMapper;
import com.data.big.model.Alarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {
    int insert(Alarm record);

    int insertSelective(Alarm record);

    List<Alarm> getAlarmInfoList(Alarm alarm);
}