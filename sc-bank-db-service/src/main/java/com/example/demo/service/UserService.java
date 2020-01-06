package com.example.demo.service;


import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //用户注册
    @Transactional
    public int register(String name,String account, String password) {
        int flag = userRepository.exituser(account);
        if(flag>0){
            //存在
            return 0;
        }else{
            //不存在
            User user = new User();
            user.setUsername(name);
            user.setAccount(account);
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));//md5加密
            userRepository.save(user);
            return 1;
        }
    }
    //登录
    public List<User> login(String account, String password){
        return userRepository.login(account, DigestUtils.md5DigestAsHex(password.getBytes()));
    }
    public User queryUserInfo(String uid) {
        return userRepository.queryUserInfo(uid);
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }


}
