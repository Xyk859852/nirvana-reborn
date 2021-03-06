package com.phoenix.nirvana.admin.web.api.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OnlineUserBO implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 当前用户是否可用
     */
    private Boolean isEnable;
}
