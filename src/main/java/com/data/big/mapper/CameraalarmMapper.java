package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Cameraalarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CameraalarmMapper extends BaseMapper<Cameraalarm> {
    int insert(Cameraalarm record);

    int insertSelective(Cameraalarm record);

    Cameraalarm selectByPrimaryId(String id);
}