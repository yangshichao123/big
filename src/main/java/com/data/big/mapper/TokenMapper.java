package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Token;

public interface TokenMapper extends BaseMapper<Token> {
    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryId(Integer id);
}