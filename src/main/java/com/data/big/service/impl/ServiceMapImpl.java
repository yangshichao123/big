package com.data.big.service.impl;

import com.data.big.log.LogExeManager;
import com.data.big.log.LogTaskFactory;
import com.data.big.mapper.*;
import com.data.big.model.*;
import com.data.big.service.ServiceMap;
import com.data.big.service.ServiceVideo;
import com.data.big.util.DateUtils;
import com.data.big.util.UUIDHelper;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
@org.springframework.stereotype.Service("ServiceMap")
public class ServiceMapImpl implements ServiceMap {


    @Autowired
    private IpcseelocationMapper ipcseelocationMapper;
    @Autowired
    private ScreenresolvingMapper screenresolvingMapper;


    @Override
    public Message addIpcseelocation(Ipcseelocation ipcseelocation) {
        Message message = new Message();
        try {
            ipcseelocation.setId(UUIDHelper.getUUID());
            ipcseelocationMapper.insertSelective(ipcseelocation);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", ipcseelocation.getId());
        return message;
    }

    @Override
    public Message updateIpcseelocation(Ipcseelocation ipcseelocation) {
        Message message = new Message();
        try {
            ipcseelocationMapper.updateByPrimaryKeySelective(ipcseelocation);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("修改失败");
            return message;
        }
        message.ok("成功成功", "");
        return message;
    }

    @Override
    public Message deleteIpcseelocation(Ipcseelocation ipcseelocation) {
        Message message = new Message();
        try {
            ipcseelocationMapper.deleteByPrimaryKey(ipcseelocation);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getIpcseelocation(Ipcseelocation ipcseelocation) {
        Message message = new Message();
        List<String> list = new ArrayList<>();
        List<VideoType> videoTypes = null;
        if (ipcseelocation.getPageIndex() == null || ipcseelocation.getPageSize() == null) {
            ipcseelocation.setPageIndex("1");
            ipcseelocation.setPageSize("1000");
        }
        try {
            PageHelper.startPage(Integer.parseInt(ipcseelocation.getPageIndex()), Integer.parseInt(ipcseelocation.getPageSize()));
            PageInfo<Ipcseelocation> pageInfo = new PageInfo<>(ipcseelocationMapper.findAll(ipcseelocation));
            List<Ipcseelocation> ipcseelocations = pageInfo.getList();
            ipcseelocations.forEach(ipc -> list.add(ipc.getId()));
            if (list.size() > 0) {
                List<Ipcseelocation> ipcseelocationList = ipcseelocationMapper.findScreenresolving(list);
                ipcseelocationList.forEach(ipcseelocation1 -> {
                    ipcseelocations.forEach(ipcseelocation2 -> {
                        if (StringUtils.equals(ipcseelocation1.getId(), ipcseelocation2.getId())) {
                            ipcseelocation2.setScreenresolvingList(ipcseelocation1.getScreenresolvingList());
                        }
                    });

                });
            }
            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return message;
    }

    @Override
    public Message addScreenresolving(Screenresolving screenresolving) {
        Message message = new Message();
        try {
            screenresolving.setId(UUIDHelper.getUUID());
            screenresolvingMapper.insertSelective(screenresolving);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }

    @Override
    public Message updateScreenresolving(Screenresolving screenresolving) {
        Message message = new Message();
        try {
            screenresolvingMapper.updateByPrimaryKeySelective(screenresolving);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("修改失败");
            return message;
        }
        message.ok("修改成功", "");
        return message;
    }

    @Override
    public Message deleteScreenresolving(Screenresolving screenresolving) {
        Message message = new Message();
        try {
            screenresolvingMapper.deleteByPrimaryKey(screenresolving);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getScreenresolving(Screenresolving screenresolving) {
        Message message = new Message();
        List<VideoType> videoTypes = null;
        if (screenresolving.getPageIndex() == null || screenresolving.getPageSize() == null) {
            screenresolving.setPageIndex("1");
            screenresolving.setPageSize("1000");
        }
        try {
            PageHelper.startPage(Integer.parseInt(screenresolving.getPageIndex()), Integer.parseInt(screenresolving.getPageSize()));
            PageInfo<Screenresolving> pageInfo = new PageInfo<>(screenresolvingMapper.findAll(screenresolving));

            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return message;
    }
}
