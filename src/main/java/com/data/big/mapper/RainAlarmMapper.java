package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.RainAlarm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RainAlarmMapper extends BaseMapper<RainAlarm> {
    int insert(RainAlarm record);

    int insertSelective(RainAlarm record);

    RainAlarm selectByPrimaryId(String id);

    /**
     * 批量修改 添加视频下载任务表 状态
     * @param rainAlarms
     */
    void updateAll(List<RainAlarm> rainAlarms);
}