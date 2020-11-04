package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.IpcTag;

import java.util.List;

public interface IpcTagMapper extends BaseMapper<IpcTag> {
    int insert(IpcTag record);

    int insertSelective(IpcTag record);

    IpcTag selectByPrimaryId(String id);

    List<IpcTag> selectIpc(IpcTag ipcTag);
}