package com.cms4j.base.system.user.controller;

import com.cms4j.base.controller.ApiBaseController;
import com.cms4j.base.system.role.service.RoleService;
import com.cms4j.base.system.user.online.service.SessionService;
import com.cms4j.base.system.user.service.UserService;
import com.cms4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/31
 */
@RestController
public class LoginApiController extends ApiBaseController {

    public LoginApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SessionService sessionService;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/tologin", method = RequestMethod.POST)
    public InvokeResult toLogin() throws Exception {
            logger.begin("开始登陆");
            DataMap dataMap = this.getDataMap();

            if(StringUtils.isBlank(dataMap.getString("USERNAME")))
                return InvokeResult.failure("用户名不能为空！");

            if(StringUtils.isBlank(dataMap.getString("PASSWORD")))
                return InvokeResult.failure("密码不能为空！");

            if(StringUtils.isBlank(dataMap.getString("CODE")))
                return InvokeResult.failure("验证码不能为空！");

            if(!dataMap.getString("CODE").equals(SessionUtil.getCodeFromSession()))
                return InvokeResult.failure("验证码不正确！");

            dataMap.put("PASSWORD", MD5Util.getMD5(dataMap.getString("PASSWORD")));
            dataMap = userService.verifyUser(dataMap);

            if(dataMap == null)
                return InvokeResult.failure("用户名或密码错误！");

            if(!Boolean.valueOf(dataMap.getString("STATUS")))
                return InvokeResult.failure("用户被冻结，无法登录！");

            sessionService.kickUser(dataMap.getString("USER_ID"));

            dataMap.put("LAST_LOGIN", DateUtil.getCurrentTime());
            dataMap.put("IP", this.getRequestIpAddress());
            userService.editUserLoginRecord(dataMap);

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(dataMap.getString("USERNAME"), dataMap.getString("PASSWORD"));
            subject.login(token);

            SessionUtil.addUser2Session(dataMap);

            DataMap role = roleService.getRoleById(dataMap);
            SessionUtil.addRole2Session(role);

            SessionUtil.removeCodeFromSession();

            logger.end();
            return InvokeResult.success();
    }
}
