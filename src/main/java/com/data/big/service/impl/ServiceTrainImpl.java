package com.data.big.service.impl;

import com.data.big.mapper.IpcseelocationMapper;
import com.data.big.mapper.ScreenresolvingMapper;
import com.data.big.mapper.TrainloactionMapper;
import com.data.big.model.Ipcseelocation;
import com.data.big.model.Screenresolving;
import com.data.big.model.Trainloaction;
import com.data.big.model.VideoType;
import com.data.big.service.ServiceMap;
import com.data.big.service.ServiceTrain;
import com.data.big.util.UUIDHelper;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service("ServiceTrain")
public class ServiceTrainImpl implements ServiceTrain {


    @Autowired
    private TrainloactionMapper trainloactionMapper;



    @Override
    public Message addTrainloaction(Trainloaction trainloaction){
        Message message=new Message();
        try {
            trainloaction.setId(UUIDHelper.getUUID());
            trainloactionMapper.insertSelective(trainloaction);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            message.error("保存失败");
            return message;
        }
        message.ok("保存成功",trainloaction.getId());
        return message;
    }

    @Override
    public Message updateTrainloaction(Trainloaction trainloaction) {
        Message message=new Message();
        try {
            trainloactionMapper.updateByPrimaryKeySelective(trainloaction);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            message.error("修改失败");
            return message;
        }
        message.ok("成功成功","");
        return message;
    }

    @Override
    public Message deleteTrainloaction(Trainloaction trainloaction) {
        Message message=new Message();
        try {
            trainloactionMapper.deleteByPrimaryKey(trainloaction);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            message.error("删除失败");
            return message;
        }
        message.ok("删除成功","");
        return message;
    }

    @Override
    public Message getTrainloaction(Trainloaction trainloaction) {
        Message message=new Message();
        List<String> list=new ArrayList<>();
        List<VideoType> videoTypes=null;
        if(trainloaction.getPageIndex()==null || trainloaction.getPageSize()==null){
            trainloaction.setPageIndex("1");
            trainloaction.setPageSize("1000");
        }
        try {
            PageHelper.startPage(Integer.parseInt(trainloaction.getPageIndex()), Integer.parseInt(trainloaction.getPageSize()));
            PageInfo<Trainloaction> pageInfo = new PageInfo<>( trainloactionMapper.select(trainloaction));
            /*List<Ipcseelocation> ipcseelocations =pageInfo.getList();
            ipcseelocations.forEach(ipc -> list.add(ipc.getId()));
            if(list.size()>0){
                List<Ipcseelocation> ipcseelocationList=ipcseelocationMapper.findScreenresolving(list);
               ipcseelocationList.forEach(ipcseelocation1 -> {
                   ipcseelocations.forEach(ipcseelocation2 -> {
                       if(StringUtils.equals(ipcseelocation1.getId(),ipcseelocation2.getId())){
                           ipcseelocation2.setScreenresolvingList(ipcseelocation1.getScreenresolvingList());
                       }
                   });

               });
            }*/
            message.ok("查询成功",pageInfo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            message.error("查询失败");
            return message;
        }
        return message;
    }


}
