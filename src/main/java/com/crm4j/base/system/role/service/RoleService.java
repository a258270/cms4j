package com.crm4j.base.system.role.service;

import com.crm4j.base.dao.DaoSupport;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.JurisdictionUtil;
import com.crm4j.base.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/14.
 */
@Service
public class RoleService {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * 根据ROLE_ID获取数据
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap getRoleById(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("RoleMapper.getRoleById", dataMap);
    }

    /**
     * 获取所有顶级角色
     * @return
     * @throws Exception
     */
    public List<DataMap> getAllFatherRoles() throws Exception {
        return (List<DataMap>) daoSupport.findForList("RoleMapper.getAllFatherRoles");
    }

    /**
     * 通过父级Id获取角色
     * @param dataMap
     * @return
     * @throws Exception
     */
    public List<DataMap> getSonRolesByFatherId(DataMap dataMap) throws Exception {
        return (List<DataMap>) daoSupport.findForList("RoleMapper.getSonRolesByFatherId", dataMap);
    }

    /**
     * 获取除管理员角色外其他数据
     * @return
     * @throws Exception
     */
    public List<DataMap> getFatherRolesExceptSuadmin() throws Exception {
        return (List<DataMap>) daoSupport.findForList("RoleMapper.getFatherRolesExceptSuadmin");
    }

    /**
     * 新增角色
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRole(DataMap dataMap) throws Exception {
        daoSupport.save("RoleMapper.addRole", dataMap);
    }

    /**
     * 编辑角色基础信息（不包含QX信息）
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void editRoleExceptQX(DataMap dataMap) throws Exception {
        daoSupport.update("RoleMapper.editRoleExceptQX", dataMap);
    }

    /**
     * 删除角色
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeRole(DataMap dataMap) throws Exception {
        daoSupport.delete("RoleMapper.removeRole", dataMap);
    }

    /**
     * 编辑角色权限信息
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void editRoleQX(DataMap dataMap) throws Exception {
        daoSupport.update("RoleMapper.editRoleQX", dataMap);
    }

    /**
     * 设置权限
     * @param strNodes
     * @param type
     * @param id
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void setJruisdiction(String strNodes, String type, String id) throws Exception {
        String[] nodes = strNodes.split(",");
        BigInteger jurisdiction = JurisdictionUtil.setJurisdiction(nodes);
        String strJurisdiction = String.valueOf(jurisdiction);

        DataMap role = new DataMap();
        role.put("ROLE_ID", id);
        role = this.getRoleById(role);

        role.put(type, strJurisdiction);
        this.editRoleQX(role);

        List<DataMap> sonRoles = this.getSonRolesByFatherId(role);
        if(!ListUtils.isEmptyList(sonRoles)) {
            for(DataMap sonRole : sonRoles) {
                this.setJruisdiction(strNodes, type, sonRole.getString("ROLE_ID"));
            }
        }
    }

    /**
     * 获取所有子级角色
     * @return
     * @throws Exception
     */
    public List<DataMap> getAllSonRoles() throws Exception {
        return (List<DataMap>) daoSupport.findForList("RoleMapper.getAllSonRoles");
    }
}
