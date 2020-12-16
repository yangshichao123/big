package com.data.big.controller;

import com.data.big.model.Trainloaction;
import com.data.big.service.ServiceTrain;
import com.data.big.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/controllerTrain")
@CrossOrigin
public class ControllerTrain {


    @Autowired
    private ServiceTrain serviceTrain;


    @RequestMapping("/addTrainloaction")
    public Message addTrainloaction(Trainloaction trainloaction) {

        return serviceTrain.addTrainloaction(trainloaction);
    }

    @RequestMapping("/updateTrainloaction")
    public Message updateTrainloaction(Trainloaction trainloaction) {

        return serviceTrain.updateTrainloaction(trainloaction);
    }

    @RequestMapping("/deleteTrainloaction")
    public Message deleteTrainloaction(Trainloaction trainloaction) {

        return serviceTrain.deleteTrainloaction(trainloaction);
    }

    @RequestMapping("/getTrainloaction")
    public Message getTrainloaction(Trainloaction trainloaction) {

        return serviceTrain.getTrainloaction(trainloaction);
    }

}
