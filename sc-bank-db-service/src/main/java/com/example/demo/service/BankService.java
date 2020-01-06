package com.example.demo.service;

import com.example.demo.bean.Bank;
import com.example.demo.bean.BankOrders;
import com.example.demo.repository.BankOrdersRepository;
import com.example.demo.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tools.DateTools;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankOrdersRepository bankOrdersRepository;

    public Bank queryBankCard(String num) {
        return bankRepository.queryBankCard(num);
    }
    public int existBankCard(String name,String tel,String id,String pass) {

        return bankRepository.existBankCard(name,tel,id,pass);
    }
    public List<BankOrders> queryBankCardDetail(String num) {
        return bankOrdersRepository.queryBankCardDetail(num);
    }
    //银行转账业务

    /**
     *
     * @param from_account 来自用户
     * @param to_account   转入用户
     * @param money         金钱
     * @param code         支付码
     * @return
     */
    @Transactional
    public List<Map<String, Object>> transferMoney(String from_account, String to_account, Integer money, String code){
        System.out.println(from_account+""+to_account+""+money+""+code);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = null;
        //通过来自账户和密码查询账户实体：密码不正确
        Bank fromBank = bankRepository.queryBankCard(from_account);
        //查询转入账户实体
        Bank toBank = bankRepository.queryBankCard(to_account);
        if(money<1){
            map.put("code", 400);
            map.put("data", null);
            map.put("msg","转入金额有误!");
            list.add(map);
            return list;
        }
        if(toBank ==null){
            map.put("code", 400);
            map.put("data", null);
            map.put("msg","转入账号不存在!");
            list.add(map);
            return list;
        }
        if(fromBank==null){
            map.put("code", 400);
            map.put("data", null);
            map.put("msg","账号不存在!");
            list.add(map);
            return list;
        }
        if(toBank==fromBank){
            map.put("code", 401);
            map.put("data", null);
            map.put("msg","不可转入同账户!");
            list.add(map);
            return list;
        }
        //判断账户够不够钱：余额不足
        double fromBankMoney = fromBank.getMoney();
        if(fromBankMoney<money){//账户金额小于操作金额
            map.put("code", 400);
            map.put("data", null);
            map.put("msg","账户余额不足!");
            list.add(map);
            return list;
        }
        //如果余额充足：转入待转用户账户+钱，来自账户-钱：返回成功码，from_account，to_account，money，type（）
        String fromBankPass = fromBank.getAct_password();
        if(!fromBankPass.equals(code)){
            map.put("code", 400);
            map.put("data", null);
            map.put("msg","密码不正确!");
            list.add(map);
            return list;
        }
        String type;
        boolean transferFlag;
        if(fromBank.getBank_name().equals(toBank.getBank_name())){
            //同行转入
            transferFlag = true;
            type = "同行转帐";
        }else{
            transferFlag = false;
            type = "跨行转账，收取手续费";
        }
        try {
            fromBank.setMoney(fromBankMoney-money);
            toBank.setMoney(toBank.getMoney()+money);
            bankRepository.save(fromBank);
            bankRepository.save(toBank);

            DateTools dateTools = new DateTools();
            BankOrders fromOrders = new BankOrders();
            fromOrders.setCommandAccount(from_account);
            fromOrders.setFromAccount(from_account);
            fromOrders.setToAccount(to_account);
            fromOrders.setCommandTime(dateTools.currentDateTime());
            fromOrders.setFlag("0");//+钱：1，减钱：0
            fromOrders.setMoney(money);
            fromOrders.setOrderNum(dateTools.createNum("T"));
            if(transferFlag){//同行
                String mmsg;
                mmsg = fromBank.getBank_name()+"同行转出";
                fromOrders.setType(mmsg);
            }else{
                String mmsg;
                mmsg = fromBank.getBank_name()+"转出"+toBank.getBank_name();
                fromOrders.setType(mmsg);
            }
            bankOrdersRepository.save(fromOrders);

            BankOrders toOrders = new BankOrders();
            toOrders.setCommandAccount(to_account);
            toOrders.setFromAccount(from_account);
            toOrders.setToAccount(to_account);
            toOrders.setCommandTime(dateTools.currentDateTime());
            toOrders.setFlag("1");//+钱：1，减钱：0
            toOrders.setMoney(money);
            toOrders.setOrderNum(dateTools.createNum("T"));
            if(transferFlag){//同行
                String mmsg;
                mmsg = fromBank.getBank_name()+"同行转入";
                toOrders.setType(mmsg);
            }else{
                String mmsg;
                mmsg = fromBank.getBank_name()+"转入"+toBank.getBank_name();
                toOrders.setType(mmsg);
            }
            bankOrdersRepository.save(toOrders);

            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put("from_account",from_account);
            msgMap.put("from_bank",fromBank.getBank_name());
            msgMap.put("to_account",to_account);
            msgMap.put("to_bank",toBank.getBank_name());
            msgMap.put("money",money);
            msgMap.put("type",type);
            data.add(msgMap);
            map.put("code", 200);
            map.put("msg","转账成功!");
            map.put("data",data);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            map.put("code", 444);
            map.put("data", null);
            map.put("msg","转账失败!");
            list.add(map);
            return list;
        }
        list.add(map);
        return list;
    }

}
