package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Algorithmdata;

public interface AlgorithmdataMapper extends BaseMapper<Algorithmdata> {
    int insert(Algorithmdata record);

    int insertSelective(Algorithmdata record);

    Algorithmdata selectByPrimaryId(String id);
}