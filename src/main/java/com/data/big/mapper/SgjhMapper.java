package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Sgjh;
import com.data.big.model.Wxjh;

import java.util.List;
import java.util.Map;

public interface SgjhMapper extends BaseMapper<Sgjh> {
    int insert(Sgjh record);

    int insertSelective(Sgjh record);

    Sgjh selectByPrimaryId(String id);

    /**
     * 批量修改
     * @param sgjhList
     */
    void updateAll(List<Sgjh> sgjhList);
    /**
     * 批量修改
     * @param sgjhList
     */
    void updateChartParamByAccountAndChartid(List<Map<String,Object>>sgjhList);

    /**
     * 批量修改
     * @param sgjhList
     */
    void insertCodeBatch(List<Sgjh> sgjhList);

    List<Sgjh> findByExample(Sgjh sgjh);
}