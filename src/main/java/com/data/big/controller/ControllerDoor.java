package com.data.big.controller;

import com.data.big.model.Dooripcrelation;
import com.data.big.model.Trainloaction;
import com.data.big.service.ServiceDoor;
import com.data.big.service.ServiceTrain;
import com.data.big.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ControllerDoor")
@CrossOrigin
public class ControllerDoor {


    @Autowired
    private ServiceDoor serviceDoor;


    @RequestMapping("/addDooripcrelation")
    public Message addDooripcrelation(Dooripcrelation dooripcrelation) {

        return serviceDoor.addDooripcrelation(dooripcrelation);
    }

    @RequestMapping("/updateDooripcrelation")
    public Message updateDooripcrelation(Dooripcrelation dooripcrelation) {

        return serviceDoor.updateDooripcrelation(dooripcrelation);
    }

    @RequestMapping("/deleteDooripcrelation")
    public Message deleteDooripcrelation(Dooripcrelation dooripcrelation) {

        return serviceDoor.deleteDooripcrelation(dooripcrelation);
    }

    @RequestMapping("/getDooripcrelation")
    public Message getDooripcrelation(Dooripcrelation dooripcrelation) {

        return serviceDoor.getDooripcrelation(dooripcrelation);
    }

}
