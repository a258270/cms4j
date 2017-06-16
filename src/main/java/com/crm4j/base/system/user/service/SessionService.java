package com.crm4j.base.system.user.service;

import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.SessionUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/16.
 */
@Service
public class SessionService {
    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 检测有无重复登录(实现单点登录)
     */
    public void checkReLogin(String username) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session : sessions) {
            DataMap user = (DataMap) session.getAttribute(SessionUtil.SESSION_USER_KEY);
            if(user == null)
                continue;
            if(username.equals(user.getString("USERNAME"))){
                session.setTimeout(0);
                break;
            }
        }
    }

}
