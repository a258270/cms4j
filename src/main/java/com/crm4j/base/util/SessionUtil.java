package com.crm4j.base.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * description: Session工具类
 *
 * @author: zmj
 * @create: 2017/5/7
 */
public class SessionUtil {

    /**
     * session中user信息存放的key值
     */
    public static final String SESSION_USER_KEY = "session_user_key";
    /**
     * session中验证码信息存放的key值
     */
    public static final String SESSION_VERIFYCODE_KEY = "CODE";
    /**
     * session中角色信息存放的key值
     */
    public static final String SESSION_ROLE_KEY = "session_role_key";

    public static Session getSession() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        return session;
    }
}
