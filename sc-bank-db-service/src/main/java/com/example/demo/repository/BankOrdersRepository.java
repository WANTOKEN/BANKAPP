package com.example.demo.repository;

import com.example.demo.bean.BankOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankOrdersRepository extends JpaRepository<BankOrders,String>, JpaSpecificationExecutor<BankOrders> {

    @Query("select s from BankOrders s where s.commandAccount = ?1 order by s.commandTime desc ")
    List<BankOrders> queryBankCardDetail(@Param("num") String num);

}
