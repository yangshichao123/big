package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.WindSpeedAlarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
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

    /**
     * 批量修改 添加视频下载表任务 状态
     * @param windSpeedAlarms
     */
    void updateAll(List<WindSpeedAlarm> windSpeedAlarms);
}