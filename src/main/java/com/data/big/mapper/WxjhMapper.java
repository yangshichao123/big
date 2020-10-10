package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Wxjh;

public interface WxjhMapper extends BaseMapper<Wxjh> {
    int insert(Wxjh record);

    int insertSelective(Wxjh record);

    Wxjh selectByPrimaryId(String id);
}