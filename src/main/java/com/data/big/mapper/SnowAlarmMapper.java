package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.SnowAlarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SnowAlarmMapper extends BaseMapper<SnowAlarm> {
    int insert(SnowAlarm record);

    int insertSelective(SnowAlarm record);

    SnowAlarm selectByPrimaryId(String id);

    /**
     * 批量修改视频下载任务表 状态
     * @param snowAlarms
     */
    void updateAll(List<SnowAlarm> snowAlarms);
}