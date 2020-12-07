package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Screenresolving;

import java.util.List;

public interface ScreenresolvingMapper extends BaseMapper<Screenresolving> {
    int insert(Screenresolving record);

    int insertSelective(Screenresolving record);

    Screenresolving selectByPrimaryId(String id);

    List<Screenresolving> findAll(Screenresolving screenresolving);
}