package com.example.demo.repository;

import com.example.demo.bean.IdentifyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdcardRepository extends JpaRepository<IdentifyCard,String>, JpaSpecificationExecutor<IdentifyCard> {

    @Query("select s from IdentifyCard s")
    List<IdentifyCard> findAllUser();

    @Query("select count(s) from IdentifyCard s where s.username = ?1 and  s.identify_card = ?2")
    int queryIdCard(@Param("username") String username, @Param("id") String id);

    @Query("select i.username from IdentifyCard i where i.identify_card=?1")
    String findNameById(@Param("identify_card") String identify_card);

    //查找身份证号码是否存在
    @Query("select count(i) from IdentifyCard i where i.identify_card=?1")
    int exitid_card(@Param("identify_card") String identify_card);
}
