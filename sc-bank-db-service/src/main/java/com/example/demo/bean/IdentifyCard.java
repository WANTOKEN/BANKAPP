package com.example.demo.bean;

import javax.persistence.*;

@Entity
@Table(name = "tb_idcard")
public class IdentifyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //自增id
    private String username; //真实姓名
    private String identify_card;//身份证号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentify_card() {
        return identify_card;
    }

    public void setIdentify_card(String identify_card) {
        this.identify_card = identify_card;
    }
}
