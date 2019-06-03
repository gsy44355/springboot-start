package com.gsy.springboot.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created By Gsy on 2019/5/4
 */
@Controller
public class TestController {
    @GetMapping("error403")
    public String filetransfer(){
        return "error403";
    }
    @GetMapping("index")
    public String indexPage(){
        return "index";
    }
}
