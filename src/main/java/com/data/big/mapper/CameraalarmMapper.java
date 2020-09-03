package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Cameraalarm;

public interface CameraalarmMapper extends BaseMapper<Cameraalarm> {
    int insert(Cameraalarm record);

    int insertSelective(Cameraalarm record);

    Cameraalarm selectByPrimaryId(String id);
}