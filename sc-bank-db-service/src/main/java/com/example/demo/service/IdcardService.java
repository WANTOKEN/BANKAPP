package com.example.demo.service;

import com.example.demo.bean.IdentifyCard;
import com.example.demo.repository.IdcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdcardService {
    @Autowired
    private IdcardRepository idcardRepository;

    public List<IdentifyCard> findAll(){
        return  idcardRepository.findAllUser();
    }

    public int queryIdCard(String name,String id) {
        return idcardRepository.queryIdCard(name,id);
    }
    public String findNameById(String id_card) {
        return idcardRepository.findNameById(id_card);
    }

}
