package com.crm4j.base.controller;

import com.crm4j.base.util.InvokeResult;
import com.crm4j.base.util.JurisdictionUtil;
import com.crm4j.base.util.LoggerUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/31
 */
@ControllerAdvice
public class ApiBaseController extends BaseController {

    public ApiBaseController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
    }

    @ExceptionHandler({Exception.class})
    protected InvokeResult handleException(Exception e) {

        logger.error("捕捉异常", e);
        return InvokeResult.failure("系统出错，请联系管理员！");
    }

    protected boolean validJurisdiction(String type) {
        return JurisdictionUtil.hasJurisdiction(type, menuId);
    }

    protected InvokeResult interceptorJurisdiction() {
        return InvokeResult.failure("没有进行该操作的权限！");
    }
}
