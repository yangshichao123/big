package com.data.big.mapper;

import com.data.big.base.BaseMapper;
import com.data.big.model.Ipcseelocation;

import java.util.List;

public interface IpcseelocationMapper extends BaseMapper<Ipcseelocation> {
    int insert(Ipcseelocation record);

    int insertSelective(Ipcseelocation record);

    Ipcseelocation selectByPrimaryId(String id);

    List<Ipcseelocation> findAll(Ipcseelocation ipcseelocation);

    /**
     * 查询屏幕分辨率
     * @param list
     * @return
     */
    List<Ipcseelocation> findScreenresolving(List<String> list);
}