package com.data.big.controller;

import com.data.big.service.ServiceFZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/ControllerFZ")
@CrossOrigin
public class ControllerFZ {


    @Autowired
    private ServiceFZ ServiceFZ;


    @RequestMapping("/login")
    @ResponseBody
    public Map<String ,Object >  login() {

        return ServiceFZ.login();
    }

    @RequestMapping("/getFZAlarm")
    @ResponseBody
    public Map<String ,Object >  getFZAlarm(@RequestParam Map<String ,Object> map) {

        return ServiceFZ.getFZAlarm(map);
    }

}
