package com.cms4j.base;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: 临时LoginController
 * 由于本系统的loginInterceptor将没有登陆信息的用户redirect至Const.LOGIN
 * 这样做为了满足系统既有后台管理又有其他有登陆功能的页面（比如提供给用户使用的系统）
 * 这里临时将Const.LOGIN重定向至/admin/login，若需要使用Const.LOGIN就将该controller删掉，自己再另写
 * @see com.cms4j.base.util.Const
 * 若使用用户登陆功能，请将用户信息存入SessionUtil.SESSION_USER_KEY
 * 管理员用户会存入SessionUtil.SESSION_ADMIN_USER_KEY
 * @see com.cms4j.base.util.SessionUtil
 * @author: zmj
 * @create: 2017/6/29
 */
@Controller
public class LoginTempController extends PageBaseController {
    @RequestMapping(value = Const.LOGIN)
    public String login() {
        return "redirect:/admin/login";
    }
}
