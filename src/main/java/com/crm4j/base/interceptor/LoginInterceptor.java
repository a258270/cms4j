package com.crm4j.base.interceptor;

import com.crm4j.base.util.Const;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.SessionUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 登陆Interceptor
 *
 * @author: zmj
 * @create: 2017/5/7
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        //过滤无需拦截的url
        if(path.matches(Const.REG_NOTERCEPTOR_URL))
            return true;

        DataMap user = (DataMap) request.getSession().getAttribute(SessionUtil.SESSION_USER_KEY);
        if(user == null){
            //跳转至登录界面
            response.sendRedirect(request.getContextPath() + Const.LOGIN);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
