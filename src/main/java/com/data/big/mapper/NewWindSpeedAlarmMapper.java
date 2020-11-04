package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.NewWindSpeedAlarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NewWindSpeedAlarmMapper extends BaseMapper<NewWindSpeedAlarm> {
    int insert(NewWindSpeedAlarm record);

    int insertSelective(NewWindSpeedAlarm record);

    NewWindSpeedAlarm selectByPrimaryId(String id);

    /**
     * 获取防灾告警
     * @param alarmType
     * @param km
     * @param startTime
     * @param endTime
     * @return
     */
    List<NewWindSpeedAlarm> getFzAlarm(String alarmType, String km, String startTime, String endTime);

    List<HashMap> getFzAlarmMap(String alarmType, String km, String startTime, String endTime);
}