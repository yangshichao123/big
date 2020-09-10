package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Diskalarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiskalarmMapper extends BaseMapper<Diskalarm> {
    int insert(Diskalarm record);

    int insertSelective(Diskalarm record);

    Diskalarm selectByPrimaryId(String id);
}