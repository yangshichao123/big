package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Diskstatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiskstatusMapper extends BaseMapper<Diskstatus> {
    int insert(Diskstatus record);

    int insertSelective(Diskstatus record);

    Diskstatus selectByPrimaryId(String id);
}