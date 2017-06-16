package com.crm4j.base.interceptor;

import com.crm4j.base.util.Const;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.InvokeResult;
import com.crm4j.base.util.SessionUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * description: 登陆Interceptor
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

        DataMap user = SessionUtil.getCurUser();
        if(user == null){
            //跳转至登录界面
            PrintWriter out = null;
            try {
                if (request.getParameter("ajax") != null) {
                    out = response.getWriter();
                    out.append(new InvokeResult().failure(Const.NOLOGIN_CODE, "请重新登陆").toString());
                } else {
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
