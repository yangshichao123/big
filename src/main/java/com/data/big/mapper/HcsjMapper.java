package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Hcsj;
import com.data.big.model.Sgjh;

import java.util.List;
import java.util.Map;

public interface HcsjMapper extends BaseMapper<Hcsj> {
    int insert(Hcsj record);

    int insertSelective(Hcsj record);

    Hcsj selectByPrimaryId(String id);

    /**
     * 批量修改
     * @param hcsjList
     */
    void updateAll(List<Hcsj> hcsjList);

    /**
     * 批量修改
     * @param hcsjList
     */
    void updateChartParamByAccountAndChartid(List<Map<String,Object>> hcsjList);

    /**
     * 批量修改
     * @param hcsjList
     */
    void insertCodeBatch(List<Hcsj> hcsjList);
}