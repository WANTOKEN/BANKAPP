package com.example.demo.service;


import com.example.demo.bean.bankBind;
import com.example.demo.repository.BankBindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class BankBindService {
    @Autowired
    private BankBindRepository bankBindRepository;

    // //查询某个用户所有绑定银行卡
    public List<bankBind> queryBankBindCard(String userid) {
        return bankBindRepository.queryBankBindCard(userid);
    }
    public int existBankBind(String num) {
        return bankBindRepository.existBankBind(num);
    }
    @Transactional
    public int bindBank(String num,String bankname,String type,String code,String userid) {
        int flag = 0;
        try {
            bankBind bankBind = new bankBind();
            bankBind.setAct_number(num);
            bankBind.setAct_password(code);
            bankBind.setBank_name(bankname);
            bankBind.setType(type);
            bankBind.setUserid(userid);
            bankBindRepository.save(bankBind);
            flag = 1;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            flag = 0;
        }
      return flag;

    }


}
