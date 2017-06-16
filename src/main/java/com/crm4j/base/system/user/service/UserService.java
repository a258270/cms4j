package com.crm4j.base.system.user.service;

import com.crm4j.base.dao.DaoSupport;
import com.crm4j.base.system.user.online.service.SessionService;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.MD5Util;
import com.crm4j.base.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * description: 用户service
 *
 * @author: zmj
 * @create: 2017/5/31
 */
@Service
public class UserService {

    @Autowired
    private DaoSupport daoSupport;
    @Autowired
    private SessionService sessionService;

    /**
     * 根据用户名获取数据
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap getUserByUsername(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("UserMapper.getUserByUsername", dataMap);
    }

    /**
     * 验证用户
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap verifyUser(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("UserMapper.verifyUser", dataMap);
    }

    /**
     * 更新用户登录记录
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void editUserLoginRecord(DataMap dataMap) throws Exception {
        daoSupport.update("UserMapper.editUserLoginRecord", dataMap);
    }

    /**
     * 获取用户分页数据
     * @param page
     * @return
     * @throws Exception
     */
    public List<DataMap> getUsers(Page page) throws Exception {
        return (List<DataMap>) daoSupport.findForList("UserMapper.getUsers", page);
    }

    /**
     * 批量冻结用户
     * @param objs
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoves(String objs) throws Exception {
        String[] objArr = objs.split(",");
        List<String> objList = Arrays.asList(objArr);
        daoSupport.update("UserMapper.batchRemoves", objList);
    }

    /**
     * 解冻用户
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void unRemove(DataMap dataMap) throws Exception {
        daoSupport.update("UserMapper.unRemove", dataMap);
    }

    /**
     * 新增用户
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(DataMap dataMap) throws Exception {
        daoSupport.save("UserMapper.addUser", dataMap);
    }

    /**
     * 根据USER_ID获取用户数据
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap getUserById(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("UserMapper.getUserById", dataMap);
    }

    /**
     * 编辑用户
     * @param dataMap
     * @param isSelf
     * @return 1----编辑成功，0----编辑失败，原因：密码两次输入不一致
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer editUser(DataMap dataMap, boolean isSelf) throws Exception {
        if(!StringUtils.isBlank(dataMap.getString("PASSWORD")) || !StringUtils.isBlank(dataMap.getString("REPASSWORD"))){
            if(!dataMap.getString("PASSWORD").equals(dataMap.getString("REPASSWORD"))) {
                return 0;
            }

            dataMap.put("PASSWORD", MD5Util.getMD5(dataMap.getString("PASSWORD")));
        }
        else {
            dataMap.put("PASSWORD", null);
        }
        if(StringUtils.isBlank(dataMap.getString("PHONE")))
            dataMap.put("PHONE", null);
        if(isSelf)
            daoSupport.update("UserMapper.editSelf", dataMap);
        else
            daoSupport.update("UserMapper.editUser", dataMap);

        sessionService.updateUserFromSession(dataMap.getString("USER_ID"));
        return 1;
    }
}
