package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Analysiresult;

import java.util.List;

public interface AnalysiresultMapper extends BaseMapper<Analysiresult> {
    int insert(Analysiresult record);

    int insertSelective(Analysiresult record);

    Analysiresult selectByPrimaryId(String id);

    List<Analysiresult> findScreenresolving(List<String> list);

    List<Analysiresult> findByExample(Analysiresult analysiresult);
}