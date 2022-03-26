package com.phoenix.nirvana.service.system.enums;

import com.phoenix.nirvana.common.exception.ErrorCode;

public interface AdminWebCodeConstants {

    /**
     * 用户模块 10001*
     */
    ErrorCode USER_IS_NULL = new ErrorCode(100010, "用户不存在");

    ErrorCode USER_IS_ENABLE = new ErrorCode(100011, "该用户已被禁用");

    ErrorCode USER_PASSWORD_ERROR = new ErrorCode(100012, "用户密码错误");

    ErrorCode CACHE_CODE_ERROR = new ErrorCode(100013, "登录验证码错误");

    ErrorCode USER_PHONE_EXIST = new ErrorCode(100014, "当前手机号已存在");

    /**
     * 菜单模块 10002*
     */
    ErrorCode MENU_ID_EQUALS_PID = new ErrorCode(100020, "菜单父级不能是自己");

    ErrorCode MENU_IFRAME_URL_TOP_ERROR = new ErrorCode(100021, "外链必须以http://或者https://开头");
    ErrorCode MENU_IS_NULL = new ErrorCode(100022, "菜单不存在");

}
