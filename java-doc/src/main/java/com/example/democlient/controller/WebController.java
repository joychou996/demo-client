package com.example.democlient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @RequestMapping("/")
    public String Index() {
        return "index";
    }



}
