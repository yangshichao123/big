package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.UnitEquipmentAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UnitEquipmentAlarmMapper extends BaseMapper<UnitEquipmentAlarm> {
    int insert(UnitEquipmentAlarm record);

    int insertSelective(UnitEquipmentAlarm record);

    UnitEquipmentAlarm selectByPrimaryId(String id);
}