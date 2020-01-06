package com.example.demo.controller;

import com.example.demo.bean.Bank;
import com.example.demo.service.BankService;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {

    @Resource
    private BankService bankService;

    //查询银行卡
    @RequestMapping(value = "/queryBank")
    @Transactional
    public List<Map<String, Object>> queryBank(
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "tel", required = true) String tel,
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "code", required = true) String code
    ) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (id.length() != 18 || tel.length() != 11 || num.length() < 16||code.length()!=6) {
            map.put("code", 400);
            map.put("msg", "填写信息不符合要求!");
        } else {
            try {
                int count = bankService.existBankCard(num,tel,id,code);
                if (count>0) {
                    Bank bank = bankService.queryBankCard(num);
                    map.put("code", 200);
                    map.put("msg", "查询成功！");
                    map.put("data",bank);
                } else {
                    map.put("code", 400);
                    map.put("msg", "信息不符合！");
                }

            } catch (Exception e) {
                e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                map.put("code", 444);
                map.put("msg", "" +
                        "查询失败！");
            }
        }
        list.add(map);
        return list;
    }
    //银行卡转账业务
    @RequestMapping(value = "/transferMoney")
    public List<Map<String, Object>> transferMoney(
            @RequestParam(value = "from_account", required = true) String from_account,
            @RequestParam(value = "to_account", required = true) String to_account,
            @RequestParam(value = "money", required = true) Integer money,
            @RequestParam(value = "code", required = true) String code) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = bankService.transferMoney(from_account, to_account, money, code);
        return list;
    }
    //查询银行卡交易
    @RequestMapping(value = "/queryBankDetail")
    public List queryBankDetail(
            @RequestParam(value = "num", required = true) String num) throws Exception {
       List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Bank bank = bankService.queryBankCard(num);
        Map<String, Object> bankmap = new HashMap<String, Object>();
        List list1 = bankService.queryBankCardDetail(num);
        bankmap.put("code",200);
        bankmap.put("data",list1);
        bankmap.put("money",bank.getMoney());
        bankmap.put("bankname",bank.getBank_name());
        bankmap.put("bankowner",bank.getUsername());//持卡人
        bankmap.put("banktype",bank.getType());//卡的类型
        list.add(bankmap);
        return list;
    }
    //查询所有银行卡信息
    @RequestMapping(value = "/queryAllBank")
    public List<Bank> queryAllBank() {
        return bankService.findAll();
    }

}

