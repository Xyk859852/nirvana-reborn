package com.phoenix.nirvana.web.core.util;


import com.phoenix.nirvana.common.constant.CommonNirvanaConstants;
import com.phoenix.nirvana.common.vo.CommonResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class WebFrameworkUtils {

    public static Long getLoginUserId(ServletRequest request) {
        return (Long) request.getAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_ID_KEY);
    }

    public static void setLoginUserId(ServletRequest request, Long userId) {
        request.setAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_ID_KEY, userId);
    }

    public static Integer getLoginUserType(ServletRequest request) {
        return (Integer) request.getAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_TYPE_KEY);
    }

    public static void setLoginUserType(ServletRequest request, Integer userType) {
        request.setAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_TYPE_KEY, userType);
    }

    public static CommonResult getCommonResult(ServletRequest request) {
        return (CommonResult) request.getAttribute(CommonNirvanaConstants.REQUEST_ATTR_COMMON_RESULT);
    }

    public static void setCommonResult(ServletRequest request, CommonResult result) {
        request.setAttribute(CommonNirvanaConstants.REQUEST_ATTR_COMMON_RESULT, result);
    }

    public static void setAccessStartTime(ServletRequest request, Date startTime) {
        request.setAttribute(CommonNirvanaConstants.REQUEST_ATTR_ACCESS_START_TIME, startTime);
    }

    public static Date getAccessStartTime(ServletRequest request) {
        return (Date) request.getAttribute(CommonNirvanaConstants.REQUEST_ATTR_ACCESS_START_TIME);
    }

    public static Integer getLoginUserType() {
        HttpServletRequest request = getRequest();
        return getLoginUserType(request);
    }

    public static Long getLoginUserId() {
        HttpServletRequest request = getRequest();
        return getLoginUserId(request);
    }

    private static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

}
