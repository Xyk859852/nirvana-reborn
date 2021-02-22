package com.phoenix.nirvana.admin.web.api.dto.user;

import com.phoenix.nirvana.common.dto.CommonPage;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserPageDTO extends CommonPage implements Serializable {

    /**
     * 模糊查询
     */
    private String keyboard;

}
