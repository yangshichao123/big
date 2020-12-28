package com.data.big.service.impl;

import com.data.big.mapper.AnalysiresultMapper;
import com.data.big.mapper.AnalysisvideoMapper;
import com.data.big.mapper.IpcseelocationMapper;
import com.data.big.mapper.ScreenresolvingMapper;
import com.data.big.model.*;
import com.data.big.service.ServiceAnalysis;
import com.data.big.service.ServiceMap;
import com.data.big.util.UUIDHelper;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service("ServiceAnalysis")
public class ServiceAnalysisImpl implements ServiceAnalysis {


    @Autowired
    private AnalysiresultMapper analysiresultMapper;
    @Autowired
    private AnalysisvideoMapper analysisvideoMapper;


    @Override
    public Message addAnalysiresult(Analysiresult analysiresult) {
        Message message = new Message();
        try {
            analysiresult.setId(UUIDHelper.getUUID());
            analysiresult.setCreatetime(new Date());
            analysiresultMapper.insertSelective(analysiresult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", analysiresult.getId());
        return message;
    }

    @Override
    public Message updateAnalysiresult(Analysiresult analysiresult) {
        Message message = new Message();
        try {
            analysiresultMapper.updateByPrimaryKeySelective(analysiresult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("修改失败");
            return message;
        }
        message.ok("成功成功", "");
        return message;
    }

    @Override
    public Message deleteAnalysiresult(Analysiresult analysiresult) {
        Message message = new Message();
        try {
            analysiresultMapper.deleteByPrimaryKey(analysiresult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getAnalysiresult(Analysiresult analysiresult) {
        Message message = new Message();
        List<String> list = new ArrayList<>();
        List<VideoType> videoTypes = null;
        if (analysiresult.getPageIndex() == null || analysiresult.getPageSize() == null) {
            analysiresult.setPageIndex("1");
            analysiresult.setPageSize("1000");
        }
        try {
            PageHelper.startPage(Integer.parseInt(analysiresult.getPageIndex()), Integer.parseInt(analysiresult.getPageSize()));
            PageInfo<Analysiresult> pageInfo = new PageInfo<>(analysiresultMapper.findByExample(analysiresult));
            List<Analysiresult> ipcseelocations = pageInfo.getList();
            ipcseelocations.forEach(ipc -> list.add(ipc.getId()));
            if (list.size() > 0) {
                List<Analysiresult> ipcseelocationList = analysiresultMapper.findScreenresolving(list);
                ipcseelocationList.forEach(ipcseelocation1 -> {
                    ipcseelocations.forEach(ipcseelocation2 -> {
                        if (StringUtils.equals(ipcseelocation1.getId(), ipcseelocation2.getId())) {
                            ipcseelocation2.setAnalysiresultList(ipcseelocation1.getAnalysiresultList());
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
    public Message addAnalysisvideo(Analysisvideo analysisvideo) {
        Message message = new Message();
        try {
            analysisvideo.setId(UUIDHelper.getUUIDStr());
            analysisvideo.setRksj(new Date());
            analysisvideo.setStatus("0");
            analysisvideo.setCreatetime(new Date());
            analysisvideo.setBz5("手动添加");
            analysisvideoMapper.insertSelective(analysisvideo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }
    @Override
    public Message addAnalysisvideoAll(List<Analysisvideo> analysisvideo) {
        Message message = new Message();
        try {
            analysisvideoMapper.insertAll(analysisvideo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功", "");
        return message;
    }

    @Override
    public Message updateAnalysisvideo(Analysisvideo analysisvideo) {
        Message message = new Message();
        try {
            analysisvideoMapper.updateByPrimaryKeySelective(analysisvideo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("修改失败");
            return message;
        }
        message.ok("修改成功", "");
        return message;
    }

    @Override
    public Message deleteAnalysisvideo(Analysisvideo analysisvideo) {
        Message message = new Message();
        try {
            analysisvideoMapper.deleteByPrimaryKey(analysisvideo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功", "");
        return message;
    }

    @Override
    public Message getAnalysisvideo(Analysisvideo analysisvideo) {
        Message message = new Message();
        List<VideoType> videoTypes = null;
        if (analysisvideo.getPageIndex() == null || analysisvideo.getPageSize() == null) {
            analysisvideo.setPageIndex("1");
            analysisvideo.setPageSize("1000");
        }
        try {
            PageHelper.startPage(Integer.parseInt(analysisvideo.getPageIndex()), Integer.parseInt(analysisvideo.getPageSize()));
            PageInfo<Analysisvideo> pageInfo = new PageInfo<>(analysisvideoMapper.findByExample(analysisvideo));

            message.ok("查询成功", pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return message;
    }


    @Override
    public Message getAnalysiresultByIds(String ids) {
        Message message = new Message();
        List idList=new ArrayList();
        String[] idStr=ids.split(";");
        if(idStr.length<1){
            return message.error("参数错误");
        }
        for (String s : idStr) {
            idList.add(s);
        }

        try {
            if(idList.size()>0){
                Example example = new Example(Analysiresult.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andIn("gdid",idList);
                List<Analysiresult> analysisvideos = analysiresultMapper.selectByExample(example);
                message.ok("查询成功", analysisvideos);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            message.error("查询失败");
            return message;
        }
        return  message;
    }
}
