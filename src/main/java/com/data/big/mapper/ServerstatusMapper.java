package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Serverstatus;

public interface ServerstatusMapper extends BaseMapper<Serverstatus> {
    int insert(Serverstatus record);

    int insertSelective(Serverstatus record);

    Serverstatus selectByPrimaryId(String id);
}