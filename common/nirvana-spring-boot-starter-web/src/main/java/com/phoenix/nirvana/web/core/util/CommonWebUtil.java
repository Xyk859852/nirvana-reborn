package com.phoenix.nirvana.web.core.util;


import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.web.core.constant.CommonNirvanaConstants;

import javax.servlet.ServletRequest;
import java.util.Date;

public class CommonWebUtil {

    public static Integer getUserId(ServletRequest request) {
        return (Integer) request.getAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_ID_KEY);
    }

    public static void setUserId(ServletRequest request, Integer userId) {
        request.setAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_ID_KEY, userId);
    }

    public static Integer getUserType(ServletRequest request) {
        return (Integer) request.getAttribute(CommonNirvanaConstants.REQUEST_ATTR_USER_TYPE_KEY);
    }

    public static void setUserType(ServletRequest request, Integer userType) {
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

}
