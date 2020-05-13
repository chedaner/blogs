package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TTT {

    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
