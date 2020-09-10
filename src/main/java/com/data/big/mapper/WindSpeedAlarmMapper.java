package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.WindSpeedAlarm;

import java.util.List;

public interface WindSpeedAlarmMapper extends BaseMapper<WindSpeedAlarm> {
    int insert(WindSpeedAlarm record);

    int insertSelective(WindSpeedAlarm record);

    WindSpeedAlarm selectByPrimaryId(String id);

    /**
     * 查询没有添加到任务表里得数据
     * @param windSpeedAlarm
     * @return
     */
    List<WindSpeedAlarm> selectAllByStatus(WindSpeedAlarm windSpeedAlarm);
}