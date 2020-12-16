package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Wxjh;

import java.util.List;
import java.util.Map;

public interface WxjhMapper extends BaseMapper<Wxjh> {
    int insert(Wxjh record);

    int insertSelective(Wxjh record);

    Wxjh selectByPrimaryId(String id);

    /**
     * 批量修改
     * @param wxjhList
     */
    void updateAll(List<Wxjh> wxjhList);
    /**
     * 批量修改
     * @param wxjhList
     */
    void updateChartParamByAccountAndChartid(List<Map<String,Object>> wxjhList);
    /**
     * 批量修改
     * @param wxjhList
     */
    void insertCodeBatch(List<Wxjh>  wxjhList);

    List<Wxjh> findByExample(Wxjh wxjh);
}