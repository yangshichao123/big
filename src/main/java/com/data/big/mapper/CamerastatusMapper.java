package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Camerastatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CamerastatusMapper extends BaseMapper<Camerastatus> {
    int insert(Camerastatus record);

    int insertSelective(Camerastatus record);

    Camerastatus selectByPrimaryId(String id);
}