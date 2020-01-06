package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AdminController {


    @RequestMapping(value = "/bank")
    public String bank() {
        return "bank";
    }
    @RequestMapping(value = "/idcard")
    public String idcard() {
        return "idcard";
    }
    @RequestMapping(value = "/user")
    public String user() {
        return "user";
    }
    @RequestMapping(value = "/")
    public String index() {
        return "idcard";
    }
}
