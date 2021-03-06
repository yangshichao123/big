package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Nodeinfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NodeinfoMapper extends BaseMapper<Nodeinfo> {
    int insert(Nodeinfo record);

    int insertSelective(Nodeinfo record);

    Nodeinfo selectByPrimaryId(String nodeId);
}