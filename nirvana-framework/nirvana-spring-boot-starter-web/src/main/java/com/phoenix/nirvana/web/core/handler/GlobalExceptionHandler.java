package com.phoenix.nirvana.web.core.handler;

import com.phoenix.nirvana.common.exception.GlobalException;
import com.phoenix.nirvana.common.exception.ServiceException;
import com.phoenix.nirvana.common.exception.enums.GlobalErrorCodeConstants;
import com.phoenix.nirvana.common.util.ExceptionUtil;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.web.core.util.WebFrameworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import static com.phoenix.nirvana.common.exception.enums.GlobalErrorCodeConstants.*;


/**
 * 全局异常处理器，将 Exception 翻译成 CommonResult + 对应的异常编号
 */
@Order(999999)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO 应该还有其它的异常，需要进行翻译

//    /**
//     * 异常总数 Metrics
//     */
//    private static final Counter EXCEPTION_COUNTER = Metrics.counter("mall.exception.total");

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.application.name}")
    private String applicationName;

    // TODO 目前存在一个问题，如果未引入 system-rpc-api 依赖，GlobalExceptionHandler 会报类不存在。未来封装出 Repository 解决该问题
//    @Reference(version = "${dubbo.consumer.SystemExceptionLogRpc.version}")
//    private SystemExceptionLogRpc systemExceptionLogRpc;

    /**
     * 处理所有异常，主要是提供给 Filter 使用
     * 因为 Filter 不走 SpringMVC 的流程，但是我们又需要兜底处理异常，所以这里提供一个全量的异常处理过程，保持逻辑统一。
     *
     * @param request 请求
     * @param ex      异常
     * @return 通用返回
     */
    public CommonResult<?> allExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionExceptionHandler((MethodArgumentNotValidException) ex);
        }
        if (ex instanceof BindException) {
            return bindExceptionHandler((BindException) ex);
        }
        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
        }
        if (ex instanceof ValidationException) {
            return validationException((ValidationException) ex);
        }
        if (ex instanceof NoHandlerFoundException) {
            return noHandlerFoundExceptionHandler((NoHandlerFoundException) ex);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return httpRequestMethodNotSupportedExceptionHandler((HttpRequestMethodNotSupportedException) ex);
        }
//        if (ex instanceof RequestNotPermitted) {
//            return requestNotPermittedExceptionHandler(request, (RequestNotPermitted) ex);
//        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex);
        }
        if (ex instanceof AccessDeniedException) {
            return accessDeniedExceptionHandler(request, (AccessDeniedException) ex);
        }
        return defaultExceptionHandler(request, ex);
    }

    /**
     * 处理 Spring Security 权限不足的异常
     * <p>
     * 来源是，使用 @PreAuthorize 注解，AOP 进行权限拦截
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest req, AccessDeniedException ex) {
        logger.warn("[accessDeniedExceptionHandler][userId({}) 无法访问 url({})]", WebFrameworkUtils.getLoginUserId(req),
                req.getRequestURL(), ex);
        return CommonResult.error(FORBIDDEN);
    }


    /**
     * 处理 SpringMVC 请求参数缺失
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getParameterName()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 请求参数缺失
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public CommonResult BadCredentialsException(BadCredentialsException ex) {
        logger.warn("[BadCredentialsException]", ex);
        return CommonResult.error(BAD_REQUEST.getCode(), "用户不存在")
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * Spring Secret 用户鉴权不存在
     * <p>
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        logger.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null; // 断言，避免告警
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public CommonResult bindExceptionHandler(BindException ex) {
        logger.warn("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null; // 断言，避免告警
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", constraintViolation.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 请求地址不存在
     * <p>
     * 注意，它需要设置如下两个配置项：
     * 1. spring.mvc.throw-exception-if-no-handler-found 为 true
     * 2. spring.mvc.static-path-pattern 为 /statics/**
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        logger.warn("[noHandlerFoundExceptionHandler]", ex);
        return CommonResult.error(GlobalErrorCodeConstants.NOT_FOUND.getCode(), String.format("请求地址不存在:%s", ex.getRequestURL()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * <p>
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        logger.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return CommonResult.error(GlobalErrorCodeConstants.METHOD_NOT_ALLOWED.getCode(), String.format("请求方法不正确:%s", ex.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理业务异常 ServiceException
     * <p>
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException ex) {
        logger.info("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理全局异常 ServiceException
     * <p>
     * 例如说，Dubbo 请求超时，调用的 Dubbo 服务系统异常
     */
    @ExceptionHandler(value = GlobalException.class)
    public CommonResult globalExceptionHandler(HttpServletRequest req, GlobalException ex) {
        // 系统异常时，才打印异常日志
        if (INTERNAL_SERVER_ERROR.getCode().equals(ex.getCode())) {
            // 插入异常日志
//            this.createExceptionLog(req, ex);
            // 普通全局异常，打印 info 日志即可
        } else {
            logger.info("[globalExceptionHandler]", ex);
        }
        // 返回 ERROR CommonResult
        return CommonResult.error(ex);
    }

    /**
     * 处理 Dubbo Consumer 本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public CommonResult validationException(ValidationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        // 无法拼接明细的错误信息，因为 Dubbo Consumer 抛出 ValidationException 异常时，是直接的字符串信息，且人类不可读
        return CommonResult.error(BAD_REQUEST.getCode(), "请求参数不正确")
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        logger.error("[defaultExceptionHandler]", ex);
        // 插入异常日志
//        this.createExceptionLog(req, ex);
        // 返回 ERROR CommonResult
        return CommonResult.error(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage())
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

//    public void createExceptionLog(HttpServletRequest req, Throwable e) {
//        // 插入异常日志
//        SystemExceptionLogCreateDTO exceptionLog = new SystemExceptionLogCreateDTO();
//        try {
//            // 增加异常计数 metrics TODO 暂时去掉
////            EXCEPTION_COUNTER.increment();
//            // 初始化 exceptionLog
//            initExceptionLog(exceptionLog, req, e);
//            // 执行插入 exceptionLog
//            createExceptionLog(exceptionLog);
//        } catch (Throwable th) {
//            logger.error("[createExceptionLog][插入访问日志({}) 发生异常({})", JSON.toJSONString(exceptionLog), ExceptionUtils.getRootCauseMessage(th));
//        }
//    }

    // TODO 优化点：后续可以增加事件
//    @Async
//    public void createExceptionLog(SystemExceptionLogCreateDTO exceptionLog) {
//        try {
//            systemExceptionLogRpc.createSystemExceptionLog(exceptionLog);
//        } catch (Throwable th) {
//            logger.error("[addAccessLog][插入异常日志({}) 发生异常({})", JSON.toJSONString(exceptionLog), ExceptionUtils.getRootCauseMessage(th));
//        }
//    }

//    private void initExceptionLog(SystemExceptionLogCreateDTO exceptionLog, HttpServletRequest request, Throwable e) {
//        // 设置账号编号
//        exceptionLog.setUserId(CommonWebUtil.getUserId(request));
//        exceptionLog.setUserType(CommonWebUtil.getUserType(request));
//        // 设置异常字段
//        exceptionLog.setExceptionName(e.getClass().getName());
//        exceptionLog.setExceptionMessage(ExceptionUtil.getMessage(e));
//        exceptionLog.setExceptionRootCauseMessage(ExceptionUtil.getRootCauseMessage(e));
//        exceptionLog.setExceptionStackTrace(ExceptionUtil.getStackTrace(e));
//        StackTraceElement[] stackTraceElements = e.getStackTrace();
//        Assert.notEmpty(stackTraceElements, "异常 stackTraceElements 不能为空");
//        StackTraceElement stackTraceElement = stackTraceElements[0];
//        exceptionLog.setExceptionClassName(stackTraceElement.getClassName());
//        exceptionLog.setExceptionFileName(stackTraceElement.getFileName());
//        exceptionLog.setExceptionMethodName(stackTraceElement.getMethodName());
//        exceptionLog.setExceptionLineNumber(stackTraceElement.getLineNumber());
//        // 设置其它字段
//        exceptionLog.setTraceId(MallUtils.getTraceId())
//                .setApplicationName(applicationName)
//                .setUri(request.getRequestURI()) // TODO 提升：如果想要优化，可以使用 Swagger 的 @ApiOperation 注解。
//                .setQueryString(HttpUtil.buildQueryString(request))
//                .setMethod(request.getMethod())
//                .setUserAgent(HttpUtil.getUserAgent(request))
//                .setIp(HttpUtil.getIp(request))
//                .setExceptionTime(new Date());
//    }

}
