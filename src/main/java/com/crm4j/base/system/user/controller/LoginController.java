package com.crm4j.base.system.user.controller;

import com.crm4j.base.controller.PageBaseController;
import com.crm4j.base.util.LoggerUtil;
import com.crm4j.base.util.SessionUtil;
import com.crm4j.base.util.VerifyCodeUtil;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/31
 */
@Controller
public class LoginController extends PageBaseController {
    private LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

    /**
     * 加载登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.begin("加载登录页面");
        logger.end();
        return "base/system/user/login";
    }

    /**
     * 生成验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getcode", method = RequestMethod.GET)
    public void getCode(HttpServletResponse response) throws IOException {
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String strCode = VerifyCodeUtil.getCode(4);

        ImageIO.write(VerifyCodeUtil.createImage(strCode), "JPEG", response.getOutputStream());

        Session session = SessionUtil.getSession();

        session.setAttribute(SessionUtil.SESSION_VERIFYCODE_KEY, strCode);

        response.getOutputStream().flush();
    }
}
