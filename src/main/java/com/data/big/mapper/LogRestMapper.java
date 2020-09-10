package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.LogRest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogRestMapper extends BaseMapper<LogRest> {
    int insert(LogRest record);

    int insertSelective(LogRest record);
}