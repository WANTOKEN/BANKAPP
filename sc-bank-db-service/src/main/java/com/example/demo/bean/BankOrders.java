package com.example.demo.bean;

import javax.persistence.*;

//账户记录表
@Entity
@Table(name = "bank_orders")
public class BankOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //自增id
    private String orderNum; //订单号
    private String commandAccount;//操作账户
    private String fromAccount;//来自账户
    private String toAccount;//转入账户
    private String commandTime;//操作时间
    private Integer money;//操作金额
    private String type;//操作类型 工商银行同行转出、工商银行同行转出、xx银行转入xx银行、xx银行转出xx银行
    private String flag;//+钱：2，减钱：1

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCommandAccount() {
        return commandAccount;
    }

    public void setCommandAccount(String commandAccount) {
        this.commandAccount = commandAccount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getCommandTime() {
        return commandTime;
    }

    public void setCommandTime(String commandTime) {
        this.commandTime = commandTime;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
