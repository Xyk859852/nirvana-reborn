package com.phoenix.nirvana.admin.web.impl.enums;

import com.phoenix.nirvana.common.exception.ErrorCode;

public interface AdminWebCodeConstants {

    ErrorCode USER_IS_NULL = new ErrorCode(100010, "用户不存在");

    ErrorCode USER_IS_ENABLE = new ErrorCode(100011, "该用户已被禁用");

    ErrorCode USER_PASSWORD_ERROR = new ErrorCode(100012, "用户密码错误");

    ErrorCode CACHE_CODE_ERROR = new ErrorCode(100013, "登录验证码错误");

    ErrorCode USER_PHONE_EXIST = new ErrorCode(100014, "当前手机号已存在");
}
