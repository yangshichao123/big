package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.AnBao3;

public interface AnBao3Mapper extends BaseMapper<AnBao3> {
    int insert(AnBao3 record);

    int insertSelective(AnBao3 record);

    AnBao3 selectByPrimaryId(String anBaoId);
}