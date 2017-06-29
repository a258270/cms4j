package com.cms4j.base.interceptor;

import com.cms4j.base.util.Const;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.InvokeResult;
import com.cms4j.base.util.SessionUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * description: 登陆Interceptor
 * 由于考虑到系统不只是会有后台管理界面，可能会有提供给用户使用的界面
 * 特把登陆检测拆分开，后台管理的url会统一格式，当url匹配Const.REG_MANAGE_URL时
 * 则为后台管理页面的url
 * @see Const
 *
 * @author: zmj
 * @create: 2017/5/7
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getServletPath();
        //过滤无需拦截的url
        if(path.matches(Const.REG_NOTERCEPTOR_URL))
            return true;

        DataMap user = null;
        if(path.matches(Const.REG_MANAGE_URL)){//url匹配Const.REG_MANAGE_URL
            user = SessionUtil.getCurAdminUser();
        }
        else {
            user = SessionUtil.getCurUser();
        }
        if(user == null){
            //跳转至登录界面
            PrintWriter out = null;
            try {
                if (request.getParameter("ajax") != null) {
                    out = response.getWriter();
                    out.append(new InvokeResult().failure(Const.NOLOGIN_CODE, "please relogin").toString());
                } else {
                    //就算管理员没登陆也最好不要直接redirect至后台登陆页面，所以这里redirect至另外的url
                    response.sendRedirect(request.getContextPath() + Const.LOGIN);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if(out != null)
                    out.close();
            }
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
