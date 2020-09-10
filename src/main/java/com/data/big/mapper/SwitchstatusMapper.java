package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Switchstatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SwitchstatusMapper extends BaseMapper<Switchstatus> {
    int insert(Switchstatus record);

    int insertSelective(Switchstatus record);

    Switchstatus selectByPrimaryId(String id);
}