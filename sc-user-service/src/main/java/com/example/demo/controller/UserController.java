package com.example.demo.controller;



import com.example.demo.bean.User;
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

public class UserController {
    @Resource
    private UserService userService;

    /* @PostMapping(value = "/save")
     public Map<String, String> save(@RequestBody User user) {
         userService.save(user);
         Map<String, String> p = new HashMap<>();
         p.put("code", "success");
         return p;
     }

     @RequestMapping(value = "/stuinfo/{uid}",method = RequestMethod.GET,
             produces = MediaType.APPLICATION_JSON_VALUE)
     public User findById(@PathVariable("uid") String uid)
     {
         User user =  userService.selectByUid(uid);
         return user;
     }
     @RequestMapping(value = "/hello",method = RequestMethod.GET)
     public String hello(String uid)
     {
         String ui =""+11;
        return "123";
     }*/

    //查询用户表所有信息
    @RequestMapping(value = "/queryAllUser")
    public List<User> queryAllUser() {
        return userService.findAllUser();
    }

    @RequestMapping(value = "/userRegister")
    public List<Map<String, Object>> register(@RequestParam(value = "account", required = true) String account, @RequestParam(value = "password", required = true) String password) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(account);
        if (account.equals("") || account.equals(null) || account.length() != 11 || password.equals("") || password.equals(null)) {
            map.put("code", 400);
            map.put("msg", "账号密码不能为空!");
        } else {
            try {
                String name = "用户" + account.substring(7);
                System.out.println(name);
                int count = userService.register(name, account, password);
                if (count > 0) {
                    map.put("code", 200);
                    map.put("msg", "注册成功！");
                } else {
                    map.put("code", 400);
                    map.put("msg", "账号已注册！");
                }
            } catch (Exception e) {
                map.put("code", 400);
                map.put("msg", "注册失败，请检查账号密码！");
            }
        }
        list.add(map);
        return list;
    }

    @RequestMapping(value = "/userLogin")
    public List<Map<String, Object>> login(String account, String password) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (account == "" || account == null || account.length() != 11 || password == "" || password == null) {
            map.put("code", 400);
            map.put("msg", "账号密码不能为空!");
        } else {
            List<User> users = userService.login(account, password);
            if (users.isEmpty()) {
                map.put("code", 400);
                map.put("msg", "登录失败，请检查账号密码！");
            } else {
                map.put("code", 200);
                map.put("msg", "登录成功！");

            }
        }
        list.add(map);
        return list;
    }

    @RequestMapping(value = "/queryUserInfo")
    public List<Map<String, Object>> queryUserInfo(String account) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (account == "" || account == null || account.length() != 11) {
            map.put("code", 400);
            map.put("data", null);
        } else {
            User users = userService.queryUserInfo(account);
           /* User returnUser = new User();
            returnUser.setAccount(users.getAccount());
            returnUser.setUsername(users.getUsername());
            returnUser.setUserimg(users.getUserimg());*/
            if (users != null) {
                String flag = users.getIdentify_card();
                if (flag == "" || flag == null || flag.length() < 1) {
                    map.put("isIdentity", 0);
                } else {
                    map.put("isIdentity", 1);//已经认证，且绑定身份证号
                }
                map.put("code", 200);
                map.put("userimg", users.getUserimg());
                map.put("username", users.getUsername());
            } else {
                map.put("code", 400);
                map.put("data", null);
            }
        }
        list.add(map);
        return list;
    }

}
