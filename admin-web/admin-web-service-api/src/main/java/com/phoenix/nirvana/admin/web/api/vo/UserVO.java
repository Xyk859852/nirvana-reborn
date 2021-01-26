package com.phoenix.nirvana.admin.web.api.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

    private String name;

    private String phone;


    public UserVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
