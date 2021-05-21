package com.phoenix.nirvana.admin.web.api.auth.login.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("返回平台登录二维码")
public class AuthenticationLoginCodeVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("验证码图片")
    private String image;

    @ApiModelProperty("验证图片唯一id")
    private String uuId;
}
