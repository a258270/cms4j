package com.crm4j.base.system.user.online.service;

import com.crm4j.base.system.user.service.UserService;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.Page;
import com.crm4j.base.util.SessionUtil;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/16.
 */
@Service
public class SessionService {
    @Autowired
    private MemorySessionDAO memorySessionDAO;
    @Autowired
    private UserService userService;

    /**
     * 踢出用户
     */
    public void kickUser(String userId) {
        Collection<Session> sessions = memorySessionDAO.getActiveSessions();
        for(Session session : sessions) {
            DataMap user = (DataMap) session.getAttribute(SessionUtil.SESSION_USER_KEY);
            if(user == null)
                continue;
            if(userId.equals(user.getString("USER_ID"))){
                try {
                    session.setTimeout(0);
                }catch (ExpiredSessionException e){

                }
                break;
            }
        }
    }

    /**
     * 获取当前用户分页信息
     * @param page
     * @return
     */
    public List<DataMap> getOnlineUsers(Page page) {
        Collection<Session> sessions = memorySessionDAO.getActiveSessions();

        List<DataMap> users = new ArrayList<DataMap>();
        List<DataMap> usersOut = new ArrayList<DataMap>();
        for(Session session : sessions) {
            DataMap user = (DataMap) session.getAttribute(SessionUtil.SESSION_USER_KEY);
            if(user == null)
                continue;

            users.add(user);
        }

        boolean bUsername = false;
        boolean bName = false;
        boolean bRole = false;
        boolean bStatus = false;
        if(page.getParams() != null && page.getParams().get("USERNAME") != null)
            bUsername = true;
        if(page.getParams() != null && page.getParams().get("NAME") != null)
            bName = true;
        if(page.getParams() != null && page.getParams().get("ROLE_ID") != null)
            bRole = true;
        if(page.getParams() != null && page.getParams().get("STATUS") != null)
            bStatus = true;
        for(int i = page.getPageNumber() * page.getPageSize(), len = users.size(); i < len; i++) {
            boolean bUsernameTmp = false;
            boolean bNameTmp = false;
            boolean bRoleTmp = false;
            boolean bStatusTmp = false;
            if(!bUsername)
                bUsernameTmp = true;
            if(!bName)
                bNameTmp = true;
            if(!bRole)
                bRoleTmp = true;
            if(!bStatus)
                bStatusTmp = true;

            if(bUsername){
                if(users.get(i).getString("USERNAME").contains(String.valueOf(page.getParams().get("USERNAME"))))
                    bUsernameTmp = true;
            }

            if(bName){
                if(users.get(i).getString("NAME").contains(String.valueOf(page.getParams().get("NAME"))))
                    bNameTmp = true;
            }

            if(bRole){
                if(users.get(i).getString("ROLE_ID").equals(String.valueOf(page.getParams().get("ROLE_ID"))))
                    bRoleTmp = true;
            }

            if(bStatus){
                if(users.get(i).getString("STATUS").equals(String.valueOf(page.getParams().get("STATUS"))))
                    bStatusTmp = true;
            }

            if(bUsernameTmp && bNameTmp && bRoleTmp && bStatusTmp){
                usersOut.add(users.get(i));
            }

            if(page.getPageSize() == usersOut.size())
                break;
        }

        return usersOut;
    }

    public void updateUserFromSession(String userId) throws Exception {
        Collection<Session> sessions = memorySessionDAO.getActiveSessions();

        for(Session session : sessions) {
            DataMap user = (DataMap) session.getAttribute(SessionUtil.SESSION_USER_KEY);
            if(user == null)
                continue;
            if(userId.equals(user.getString("USER_ID"))){
                user = userService.getUserById(user);
                SessionUtil.addUser2Session(user);
                break;
            }
        }
    }
}
