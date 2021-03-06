package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Serveralarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServeralarmMapper extends BaseMapper<Serveralarm> {
    int insert(Serveralarm record);

    int insertSelective(Serveralarm record);

    Serveralarm selectByPrimaryId(String id);
}