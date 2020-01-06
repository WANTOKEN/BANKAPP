package com.example.demo.repository;


import com.example.demo.bean.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankRepository extends JpaRepository<Bank,String>, JpaSpecificationExecutor<Bank> {


    @Query("select count(s) from Bank s where s.act_number = ?1 and  s.tel = ?2 and s.id_card = ?3 and s.act_password =?4")
    int  existBankCard(@Param("act_number") String act_number,
                       @Param("tel") String tel,
                       @Param("id_card") String id_card,
                       @Param("act_password") String act_password
    );
    @Query("select s from Bank s where s.act_number = ?1")
    Bank queryBankCard(@Param("act_number") String act_number);


    /*//查找身份证号码是否存在
    @Query("select count(i) from IdentifyCard i where i.identify_card=?1")
    int exitid_card(@Param("identify_card") String identify_card);*/
}
