package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Dictionary;

public interface DictionaryMapper extends BaseMapper<Dictionary> {
    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryId(Integer id);
}