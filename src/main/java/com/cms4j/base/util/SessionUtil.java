package com.cms4j.base.util;

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

    /**
     * 获取session中的user信息
     * @return
     */
    public static DataMap getCurUser() {
        return (DataMap) SessionUtil.getSession().getAttribute(SessionUtil.SESSION_USER_KEY);
    }

    /**
     * 将用户信息存入session
     * @param user
     */
    public static void addUser2Session(DataMap user) {
        SessionUtil.addInfo2Session(SessionUtil.SESSION_USER_KEY, user);
    }

    /**
     * 将用户信息从session中移除
     */
    public static void removeUserFromSession() {
        SessionUtil.removeInfoFromSession(SessionUtil.SESSION_USER_KEY);
    }

    /**
     * 获取session中的角色信息
     * @return
     */
    public static DataMap getRoleFromSession() {
        return (DataMap) SessionUtil.getInfoFromSession(SessionUtil.SESSION_ROLE_KEY);
    }

    /**
     * 将角色信息存入session
     * @param role
     */
    public static void addRole2Session(DataMap role) {
        SessionUtil.addInfo2Session(SessionUtil.SESSION_ROLE_KEY, role);
    }

    /**
     * 移除session中的角色信息
     */
    public static void removeRoleFromSession() {
        SessionUtil.removeInfoFromSession(SessionUtil.SESSION_ROLE_KEY);
    }

    /**
     * 获取session中的验证码信息
     * @return
     */
    public static String getCodeFromSession() {
        return (String) SessionUtil.getInfoFromSession(SessionUtil.SESSION_VERIFYCODE_KEY);
    }

    /**
     * 将验证码信息存入session
     * @param code
     */
    public static void addCode2Session(String code) {
        SessionUtil.addInfo2Session(SessionUtil.SESSION_VERIFYCODE_KEY, code);
    }

    /**
     * 移除session中的验证码信息
     */
    public static void removeCodeFromSession() {
        SessionUtil.removeInfoFromSession(SessionUtil.SESSION_VERIFYCODE_KEY);
    }

    /**
     * 获取session中的信息
     * @param key
     * @return
     */
    public static Object getInfoFromSession(Object key) {
        return SessionUtil.getSession().getAttribute(key);
    }

    /**
     * 将信息存入session
     * @param key
     * @param value
     */
    public static void addInfo2Session(Object key, Object value){
        SessionUtil.getSession().setAttribute(key, value);
    }

    /**
     * 将session中的信息移除
     * @param key
     */
    public static void removeInfoFromSession(Object key) {
        SessionUtil.getSession().removeAttribute(key);
    }
}
