package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.TaskDoor;

import java.util.List;

public interface TaskDoorMapper extends BaseMapper<TaskDoor> {
    int insert(TaskDoor record);

    int insertSelective(TaskDoor record);

    TaskDoor selectByPrimaryId(Integer id);

    /**
     * 批量添加
     * @param taskDoors
     */
    void saveAll(List<TaskDoor> taskDoors);

    /**
     * 获取任务门列表
     * @return
     */
    List<TaskDoor> getAllByStatus();

    /**
     * 批量跟新
     * @param taskDoors
     */
    void updateAllStatus(List<TaskDoor> taskDoors);
}