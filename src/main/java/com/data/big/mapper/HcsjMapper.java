package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Hcsj;

public interface HcsjMapper extends BaseMapper<Hcsj> {
    int insert(Hcsj record);

    int insertSelective(Hcsj record);

    Hcsj selectByPrimaryId(String id);
}