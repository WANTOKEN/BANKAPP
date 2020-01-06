package com.example.demo.bean;

import javax.persistence.*;

@Entity
@Table(name="bank") //银行卡表
public class Bank implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; //自增id
	String bank_name; //银行卡名
	String act_number; //银行账号
	String act_password; //账号密码
	String id_card; //身份证号码
	String username; //用户姓名
	String tel;//银行卡预留手机号
	String type; //银行卡类型
	double money; //余额

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAct_number() {
		return act_number;
	}

	public void setAct_number(String act_number) {
		this.act_number = act_number;
	}

	public String getAct_password() {
		return act_password;
	}

	public void setAct_password(String act_password) {
		this.act_password = act_password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
