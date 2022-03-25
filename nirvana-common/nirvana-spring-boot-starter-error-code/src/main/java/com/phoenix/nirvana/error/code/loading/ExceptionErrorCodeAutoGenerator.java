package com.phoenix.nirvana.error.code.loading;

import com.phoenix.nirvana.admin.web.api.admin.SysErrorCodeService;
import com.phoenix.nirvana.admin.web.api.admin.domain.dto.ErrorCodeAutoGenerateDTO;
import com.phoenix.nirvana.common.exception.ErrorCode;
import com.phoenix.nirvana.common.util.StringUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionErrorCodeAutoGenerator {

    /**
     * 应用分组
     */
    private final String group;
    @DubboReference
    SysErrorCodeService errorCodeService;
    private Logger logger = LoggerFactory.getLogger(ExceptionErrorCodeAutoGenerator.class);
    /**
     * 错误码枚举类
     */
    private String errorCodeConstantsClass;


    public ExceptionErrorCodeAutoGenerator(String group) {
        this.group = group;
    }

    public ExceptionErrorCodeAutoGenerator setErrorCodeConstantsClass(String errorCodeConstantsClass) {
        this.errorCodeConstantsClass = errorCodeConstantsClass;
        return this;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Async // 异步，保证项目的启动过程，毕竟非关键流程
    public void execute() {
        // 校验 errorCodeConstantsClass 参数
        if (!StringUtils.hasText(errorCodeConstantsClass)) {
            logger.info("[execute][未配置 mall.error-code.constants-class 配置项，不进行自动写入到 system-service 服务]");
            return;
        }
        Class errorCodeConstantsClazz;
        try {
            errorCodeConstantsClazz = Class.forName(errorCodeConstantsClass);
        } catch (ClassNotFoundException e) {
            logger.error("[execute][配置的 ({}) 找不到对应的类]", errorCodeConstantsClass);
            return;
        }
        // 写入 system-service 服务
        logger.info("[execute][自动将 ({}) 类的错误码，准备写入到 system-service 服务]", errorCodeConstantsClass);
        List<ErrorCodeAutoGenerateDTO> autoGenerateDTOs = new ArrayList<>();
        Arrays.stream(errorCodeConstantsClazz.getFields()).forEach(field -> {
            if (field.getType() != ErrorCode.class) {
                return;
            }
            try {
                ErrorCode errorCode = (ErrorCode) field.get(errorCodeConstantsClazz);
                autoGenerateDTOs.add(new ErrorCodeAutoGenerateDTO().setGroup(group)
                        .setCode(errorCode.getCode()).setMessage(errorCode.getMessage()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        CommonResult<Boolean> autoGenerateErrorCodesResult = errorCodeService.autoGenerateErrorCodes(autoGenerateDTOs);
        if (autoGenerateErrorCodesResult.isSuccess()) {
            logger.info("[execute][自动将 ({}) 类的错误码，成功写入到 admin-service 服务]", errorCodeConstantsClass);
        } else {
            logger.error("[execute][自动将 ({}) 类的错误码，失败写入到 admin-service 服务，原因为 ({}/{}/{})]", errorCodeConstantsClass,
                    autoGenerateErrorCodesResult.getCode(), autoGenerateErrorCodesResult.getMessage(), autoGenerateErrorCodesResult.getDetailMessage());
        }
    }

}