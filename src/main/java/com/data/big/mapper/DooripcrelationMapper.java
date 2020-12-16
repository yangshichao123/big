package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Dooripcrelation;

public interface DooripcrelationMapper extends BaseMapper<Dooripcrelation> {
    int insert(Dooripcrelation record);

    int insertSelective(Dooripcrelation record);

    Dooripcrelation selectByPrimaryId(String doorid);
}