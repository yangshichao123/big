package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.ForeignBodyAlarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForeignBodyAlarmMapper extends BaseMapper<ForeignBodyAlarm> {
    int insert(ForeignBodyAlarm record);

    int insertSelective(ForeignBodyAlarm record);

    ForeignBodyAlarm selectByPrimaryId(String id);

    /**
     * 批量修改 添加视频下载任务表状态
     * @param foreignBodyAlarms
     */
    void updateAll(List<ForeignBodyAlarm> foreignBodyAlarms);
}