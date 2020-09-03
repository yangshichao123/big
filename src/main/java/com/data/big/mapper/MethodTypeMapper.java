package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.MethodType;

public interface MethodTypeMapper  extends BaseMapper<MethodType> {
    int insert(MethodType record);

    int insertSelective(MethodType record);

    MethodType selectByPrimaryId(Integer id);
}