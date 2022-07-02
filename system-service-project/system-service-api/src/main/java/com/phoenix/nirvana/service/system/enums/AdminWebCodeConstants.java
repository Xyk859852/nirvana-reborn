package com.phoenix.nirvana.service.system.enums;

import com.phoenix.nirvana.common.exception.ErrorCode;

public interface AdminWebCodeConstants {

    // ========== AUTH 模块 1002000000 ==========
    ErrorCode AUTH_LOGIN_BAD_CREDENTIALS = new ErrorCode(1002000000, "登录失败，账号密码不正确");
    ErrorCode AUTH_LOGIN_USER_DISABLED = new ErrorCode(1002000001, "登录失败，账号被禁用");
    ErrorCode AUTH_LOGIN_FAIL_UNKNOWN = new ErrorCode(1002000002, "登录失败"); // 登录失败的兜底，未知原因
    ErrorCode AUTH_LOGIN_CAPTCHA_NOT_FOUND = new ErrorCode(1002000003, "验证码不存在");
    ErrorCode AUTH_LOGIN_CAPTCHA_CODE_ERROR = new ErrorCode(1002000004, "验证码不正确");
    ErrorCode AUTH_THIRD_LOGIN_NOT_BIND = new ErrorCode(1002000005, "未绑定账号，需要进行绑定");
    ErrorCode AUTH_TOKEN_EXPIRED = new ErrorCode(1002000006, "Token 已经过期");
    ErrorCode AUTH_MOBILE_NOT_EXISTS = new ErrorCode(1002000007, "手机号不存在");

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
