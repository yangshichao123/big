package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Analysisvideo;

import java.util.List;

public interface AnalysisvideoMapper extends BaseMapper<Analysisvideo> {
    int insert(Analysisvideo record);

    int insertSelective(Analysisvideo record);

    Analysisvideo selectByPrimaryId(String id);
    List<Analysisvideo> findByExample(Analysisvideo analysisvideo);

    void insertAll(List<Analysisvideo> analysisvideo);
}