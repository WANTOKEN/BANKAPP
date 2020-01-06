package com.example.demo.bean;

import javax.persistence.*;

@Entity
@Table(name="bankBind") //银行卡绑定表
public class bankBind implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; //自增id
	String bank_name; //银行卡名
	String act_number; //银行账号
	String act_password; //账号密码
	String type; //银行卡类型
	String userid;//绑定用户ID;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}
