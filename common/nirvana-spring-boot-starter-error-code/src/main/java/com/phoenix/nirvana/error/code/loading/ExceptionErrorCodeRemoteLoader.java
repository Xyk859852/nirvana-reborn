package com.phoenix.nirvana.error.code.loading;

import com.phoenix.nirvana.admin.web.api.SysErrorCodeService;
import com.phoenix.nirvana.admin.web.api.vo.SysErrorCodeVO;
import com.phoenix.nirvana.common.exception.util.ServiceExceptionUtil;
import com.phoenix.nirvana.common.util.CollectionUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

public class ExceptionErrorCodeRemoteLoader {

    private Logger logger = LoggerFactory.getLogger(ExceptionErrorCodeRemoteLoader.class);

    private static final int REFRESH_ERROR_CODE_PERIOD = 60 * 1000;

    private String group;

    private Date minUpdateTime;

    @DubboReference
    SysErrorCodeService errorCodeService;

    public ExceptionErrorCodeRemoteLoader(String group) {
        this.group = group;
    }

    @Async
    @EventListener(value = ApplicationReadyEvent.class)
    public void loadingErrorCodeMessage() {
        CommonResult<List<SysErrorCodeVO>> commonResult = errorCodeService.listErrorCodes(group);
        commonResult.checkError();
        commonResult.getData().forEach(sysErrorCodeVO -> ServiceExceptionUtil.put(sysErrorCodeVO.getCode(), sysErrorCodeVO.getMessage()));
        minUpdateTime = new Date();
    }

    @Scheduled(fixedDelay = REFRESH_ERROR_CODE_PERIOD, initialDelay = REFRESH_ERROR_CODE_PERIOD)
    public void refreshErrorCodes() {
        CommonResult<List<SysErrorCodeVO>> commonResult = errorCodeService.listErrorCodes(group, minUpdateTime);
        commonResult.checkError();
        if (CollectionUtils.isEmpty(commonResult.getData())) {
            return;
        }
        logger.info("[refreshErrorCodes][从 group({}) 增量加载到 {} 个 ErrorCode 错误码]", group, commonResult.getData().size());
        commonResult.getData().forEach(sysErrorCodeVO -> ServiceExceptionUtil.put(sysErrorCodeVO.getCode(), sysErrorCodeVO.getMessage()));
        minUpdateTime = new Date();
    }


}
