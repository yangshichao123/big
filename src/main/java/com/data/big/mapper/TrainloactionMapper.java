package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Trainloaction;

import java.util.List;

public interface TrainloactionMapper extends BaseMapper<Trainloaction> {
    int insert(Trainloaction record);

    int insertSelective(Trainloaction record);

    Trainloaction selectByPrimaryId(String id);

    List<Trainloaction> getByExample(Trainloaction trainloaction);
}