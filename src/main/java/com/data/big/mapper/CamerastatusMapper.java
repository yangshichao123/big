package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Camerastatus;

public interface CamerastatusMapper extends BaseMapper<Camerastatus> {
    int insert(Camerastatus record);

    int insertSelective(Camerastatus record);

    Camerastatus selectByPrimaryId(String id);
}