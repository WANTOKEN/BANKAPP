package com.example.demo.controller;

import com.example.demo.bean.bankBind;
import com.example.demo.service.BankBindService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class bankBindController {

    @Resource
    private BankBindService bankBindService;

    //用户绑定银行卡
    @RequestMapping(value = "/userBindBank")
    public List<Map<String, Object>> userBindBank(
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "bankname", required = true) String bankname,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "userid", required = true) String userid)
            throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (userid.length() != 11) {
            map.put("code", 400);
            map.put("msg", "填写信息不符合要求!");
        } else {
            try {
                int count = bankBindService.existBankBind(num);//>0表示存在绑定记录
                if (count>0) {
                    map.put("code", 400);
                    map.put("msg", "已绑定过！");
                } else {
                    int flag = bankBindService.bindBank(num, bankname, type, code, userid);
                    if(flag>0){
                        map.put("code", 200);
                        map.put("msg", "绑定成功！");
                    }else{
                        map.put("code", 400);
                        map.put("msg", "绑定失败！");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", 444);
                map.put("msg", "" +
                        "绑定失败！");
            }
        }
        list.add(map);
        return list;
    }


    @RequestMapping(value = "/queryBankBindCard")
    public List<Map<String, Object>> queryBankBindCard(
            @RequestParam(value = "userid", required = true) String userid)
            throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (userid.length() != 11) {
            map.put("code", 400);
            map.put("msg", "不符合查询要求!");
        } else {
            List<bankBind> data = bankBindService.queryBankBindCard(userid);
            map.put("code", 200);
            map.put("msg", "查询成功！");
            map.put("data",data);

        }
        list.add(map);
        return list;
    }


}

