package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.QueryTask;

import java.util.List;

public interface QueryTaskMapper extends BaseMapper<QueryTask> {
    int insert(QueryTask record);

    int insertSelective(QueryTask record);

    QueryTask selectByPrimaryId(String id);
    List<QueryTask> selectByStatus(Integer status);
}