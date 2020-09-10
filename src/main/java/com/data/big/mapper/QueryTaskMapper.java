package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.QueryTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface QueryTaskMapper extends BaseMapper<QueryTask> {
    int insert(QueryTask record);

    int insertSelective(QueryTask record);

    QueryTask selectByPrimaryId(String id);
    List<QueryTask> selectByStatus(Integer status);
}