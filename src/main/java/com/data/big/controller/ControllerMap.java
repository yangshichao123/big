package com.data.big.controller;

import com.data.big.model.*;
import com.data.big.service.ServiceMap;
import com.data.big.service.ServiceVideo;
import com.data.big.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/controllerMap")
@CrossOrigin
public class ControllerMap {


    @Autowired
    private ServiceMap serviceMap;


    @RequestMapping("/addIpcseelocation")
    public Message addIpcseelocation(Ipcseelocation ipcseelocation) {

        return serviceMap.addIpcseelocation(ipcseelocation);
    }

    @RequestMapping("/updateIpcseelocation")
    public Message updateIpcseelocation(Ipcseelocation ipcseelocation) {

        return serviceMap.updateIpcseelocation(ipcseelocation);
    }

    @RequestMapping("/deleteIpcseelocation")
    public Message deleteIpcseelocation(Ipcseelocation ipcseelocation) {

        return serviceMap.deleteIpcseelocation(ipcseelocation);
    }

    @RequestMapping("/getIpcseelocation")
    public Message getIpcseelocation(Ipcseelocation ipcseelocation) {

        return serviceMap.getIpcseelocation(ipcseelocation);
    }


    @RequestMapping("/addScreenresolving")
    public Message addScreenresolving(Screenresolving screenresolving) {

        return serviceMap.addScreenresolving(screenresolving);
    }

    @RequestMapping("/updateScreenresolving")
    public Message updateScreenresolving(Screenresolving screenresolving) {

        return serviceMap.updateScreenresolving(screenresolving);
    }

    @RequestMapping("/deleteScreenresolving")
    public Message deleteScreenresolving(Screenresolving screenresolving) {

        return serviceMap.deleteScreenresolving(screenresolving);
    }

    @RequestMapping("/getScreenresolving")
    public Message getScreenresolving(Screenresolving screenresolving) {

        return serviceMap.getScreenresolving(screenresolving);
    }


}
