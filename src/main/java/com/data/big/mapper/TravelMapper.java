package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Travel;

public interface TravelMapper extends BaseMapper<Travel> {
    int insert(Travel record);

    int insertSelective(Travel record);

    Travel selectByPrimaryId(String id);
}