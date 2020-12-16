package com.data.big.service.impl;

import com.data.big.mapper.DictionaryMapper;
import com.data.big.mapper.DooripcrelationMapper;
import com.data.big.mapper.TrainloactionMapper;
import com.data.big.model.*;
import com.data.big.service.ServiceDoor;
import com.data.big.service.ServiceTrain;
import com.data.big.util.UUIDHelper;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service("ServiceDoor")
public class ServiceDoorImpl implements ServiceDoor {


    @Autowired
    private DooripcrelationMapper dooripcrelationMapper;


    @Override
    public Message addDooripcrelation(Dooripcrelation dooripcrelation) {
        Message message = new Message();
        try {
            dooripcrelationMapper.insertSelective(dooripcrelation);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }

    @Override
    public Message updateDooripcrelation(Dooripcrelation dooripcrelation) {
        Message message = new Message();
        try {
            dooripcrelationMapper.updateByPrimaryKeySelective(dooripcrelation);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("修改失败");
            return message;
        }
        message.ok("成功成功", "");
        return message;
    }

    @Override
    public Message deleteDooripcrelation(Dooripcrelation dooripcrelation) {
        Message message = new Message();
        try {
            dooripcrelationMapper.deleteByPrimaryKey(dooripcrelation);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getDooripcrelation(Dooripcrelation dooripcrelation) {
        Message message = new Message();
        List<String> list = new ArrayList<>();
        List<VideoType> videoTypes = null;
        if (dooripcrelation.getPageIndex() == null || dooripcrelation.getPageSize() == null) {
            dooripcrelation.setPageIndex("1");
            dooripcrelation.setPageSize("1000");
        }
        try {
            PageHelper.startPage(Integer.parseInt(dooripcrelation.getPageIndex()), Integer.parseInt(dooripcrelation.getPageSize()));
            PageInfo<Dooripcrelation> pageInfo = new PageInfo<>(dooripcrelationMapper.select(dooripcrelation));

            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return message;
    }


}
