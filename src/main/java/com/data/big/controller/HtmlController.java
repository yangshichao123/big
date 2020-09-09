package com.data.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HtmlController {
    @RequestMapping("/login")
    public String index(){
        return "login";
    }
}
