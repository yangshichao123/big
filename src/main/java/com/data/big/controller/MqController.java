package com.data.big.controller;
import com.data.big.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqController")
public class MqController {

    @Autowired
    private Sender sender;

    @GetMapping("/sendDirectQueue")
    public Object sendDirectQueue() {
        sender.sendDirectQueue();
        return "ok";
    }
    @GetMapping("/sendTopic")
    public Object sendTopic() {
        sender.sendTopic();
        return "ok";
    }

    @GetMapping("/sendFanout")
    public Object sendFanout() {
        sender.sendFanout();
        return "ok";
    }
}
