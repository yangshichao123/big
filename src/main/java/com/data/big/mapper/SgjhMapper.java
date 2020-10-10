package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Sgjh;

public interface SgjhMapper extends BaseMapper<Sgjh> {
    int insert(Sgjh record);

    int insertSelective(Sgjh record);

    Sgjh selectByPrimaryId(String id);
}