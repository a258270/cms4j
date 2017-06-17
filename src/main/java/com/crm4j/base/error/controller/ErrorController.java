package com.crm4j.base.error.controller;

import com.crm4j.base.controller.PageBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 * @author: zmj
 * @create: 2017/6/17
 */
@Controller
public class ErrorController extends PageBaseController {

    /**
     * 无权限页面
     * @return
     */
    @RequestMapping(value = "/403")
    public String to403() {
        return "base/error/403";
    }

    /**
     * 系统错误页面
     * @return
     */
    @RequestMapping(value = "/500")
    public String to500() {
        return "base/error/500";
    }

    /**
     * 丢失页面
     * @return
     */
    @RequestMapping(value = "/404")
    public String to404() {
        return "base/error/404";
    }
}
