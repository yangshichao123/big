package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Modular;

public interface ModularMapper extends BaseMapper<Modular> {
    int insert(Modular record);

    int insertSelective(Modular record);

    Modular selectByPrimaryId(Integer id);
}