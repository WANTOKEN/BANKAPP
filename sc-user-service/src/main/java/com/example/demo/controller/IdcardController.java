package com.example.demo.controller;


import com.example.demo.bean.IdentifyCard;
import com.example.demo.bean.User;
import com.example.demo.service.IdcardService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IdcardController {

    @Resource
    private IdcardService idcardService;
    @Resource
    private UserService userService;

    //查询用户表所有信息
    @RequestMapping(value = "/queryUserIdentify")
    public List<IdentifyCard> userList() {
        return idcardService.findAll();
    }

    //用户实名认证
    @RequestMapping(value = "/userIdentify")
    public List<Map<String, Object>> userIdentify(@RequestParam(value = "name", required = true) String name,
                                                  @RequestParam(value = "id", required = true) String id,
                                                  @RequestParam(value = "account", required = true) String account
    ) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(name);
        if (id.equals("") || id.equals(null) || id.length() != 18 || name.equals("") || name.equals(null)) {
            map.put("code", 400);
            map.put("msg", "填写信息不符合要求!");
        } else {
            try {
                int count = idcardService.queryIdCard(name,id);
                if (count > 0) {
                    User user = userService.queryUserInfo(account);
                    user.setIdentify_card(id);
                    userService.save(user);
                    map.put("code", 200);
                    map.put("msg", "认证成功！");
                } else {
                    map.put("code", 400);
                    map.put("msg", "信息不符合！");
                }
            } catch (Exception e) {
                map.put("code", 400);
                map.put("msg", "信息不符合！");
            }
        }
        list.add(map);
        return list;
    }


}

