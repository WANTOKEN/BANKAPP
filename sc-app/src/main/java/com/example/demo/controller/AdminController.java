package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {


    @RequestMapping(value = "/addbankcard")
    public String addbankcard() {
        return "addbankcard";
    }

    @RequestMapping(value = "/bankdetail")
    public String bankdetail() {
        return "bankdetail";
    }

    @RequestMapping(value = "/identity")
    public String identity() {
        return "identity";
    }

    @RequestMapping(value = "/imgcut")
    public String imgcut() {
        return "imgcut";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/addbankcard.html")
    public String addbankcard1() {
        return "addbankcard";
    }

    @RequestMapping(value = "/bankdetail.html")
    public String bankdetail1() {
        return "bankdetail";
    }

    @RequestMapping(value = "/identity.html")
    public String identity1() {
        return "identity";
    }

    @RequestMapping(value = "/imgcut.html")
    public String imgcut1() {
        return "imgcut";
    }

    @RequestMapping(value = "/login.html")
    public String login1() {
        return "login";
    }

    @RequestMapping(value = "/main.html")
    public String main1() {
        return "main";
    }

    @RequestMapping(value = "/register.html")
    public String register1() {
        return "register";
    }
}
