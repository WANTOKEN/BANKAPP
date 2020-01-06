package com.example.demo.repository;




import com.example.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {

    @Query("select s from User s where s.account = ?1 and  s.password = ?2")
    List<User> login(@Param("account") String account, @Param("password") String password);

    //查询用户是否存在
    @Query("select count(s) from User s where s.account = ?1")
    int exituser(@Param("account") String account);

    //获取实体
    @Query("select s from User s where s.account = ?1")
    public User queryUserInfo(String account);
}
