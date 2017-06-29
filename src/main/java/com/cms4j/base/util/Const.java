package com.cms4j.base.util;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/30
 */
public class Const {
    public static final String LOGIN = "/login";//登录页面路径
    public static final String REG_NOTERCEPTOR_URL = ".*/((login)|(logout)|(tologin)|(getcode)|(404)|(401)|(500)).*";//logininterceptor不拦截url规则
    public static final String REG_MANAGE_URL = ".*/((admin)).*";
    public static final String PROFILE_DEV = "dev";//开发环境
    public static final String PROFILE_PRO = "pro";//生产环境

    public static final String SUADMIN = "suadmin";//超级管理员

    public static final String NOLOGIN_CODE = "-1";//用户没有登陆的状态码
}
