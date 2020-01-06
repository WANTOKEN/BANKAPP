package com.example.demo.repository;


import com.example.demo.bean.bankBind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankBindRepository extends JpaRepository<bankBind,String>, JpaSpecificationExecutor<bankBind> {

    @Query("select count(s) from bankBind s where s.act_number = ?1")
    int  existBankBind(@Param("act_number") String act_number);

    @Query("select s from bankBind s where s.userid = ?1")
    List<bankBind> queryBankBindCard(@Param("userid") String userid);


}
