package com.phoenix.nirvana.admin.web.api.auth.login.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ApiModel("管理员认证 DTO")
@Data
@Accessors(chain = true)
public class AdminAuthenticationDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "登陆账号", required = true, example = "18252113278")
    @NotEmpty(message = "登陆账号不能为空")
//    @Length(min = 11, max = 11, message = "账号长度为 11 位")
//    @Pattern(regexp = "^[0-9]+$", message = "账号为手机号")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @ApiModelProperty(value = "登录验证码", required = true, example = "1234")
    private String code;

    @ApiModelProperty(value = "登录验证码codeId", required = true, example = "1234")
    @NotEmpty(message = "登录验证码codeId不能为空")
    private String codeId;
}
