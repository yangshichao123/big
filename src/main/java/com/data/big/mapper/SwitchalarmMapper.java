package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Switchalarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SwitchalarmMapper extends BaseMapper<Switchalarm> {
    int insert(Switchalarm record);

    int insertSelective(Switchalarm record);

    Switchalarm selectByPrimaryId(String id);
}