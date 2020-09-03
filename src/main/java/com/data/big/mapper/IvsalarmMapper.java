package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Ivsalarm;

public interface IvsalarmMapper extends BaseMapper<Ivsalarm> {
    int insert(Ivsalarm record);

    int insertSelective(Ivsalarm record);

    Ivsalarm selectByPrimaryId(String id);
}