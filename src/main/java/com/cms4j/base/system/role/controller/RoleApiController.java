package com.cms4j.base.system.role.controller;

import com.cms4j.base.controller.ApiBaseController;
import com.cms4j.base.system.menu.service.MenuService;
import com.cms4j.base.system.role.service.RoleService;
import com.cms4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/14.
 */
@RestController
@RequestMapping(value = "/admin/role/api")
public class RoleApiController extends ApiBaseController {
    public RoleApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "3";
    }

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 获取角色分页信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getroles", "/getroles/{id}"}, method = RequestMethod.POST)
    public InvokeResult getRoles(@PathVariable(required = false) String id) throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
            return this.interceptorJurisdiction();

        logger.begin("获取角色信息");
        List<DataMap> roles = roleService.getAllFatherRoles();
        if (StringUtils.isBlank(id) && !ListUtils.isEmptyList(roles)){
            id = roles.get(0).getString("ROLE_ID");
        }

        DataMap dataMap = new DataMap();
        dataMap.put("ROLE_ID", id);
        List<DataMap> sonRoles = roleService.getSonRolesByFatherId(dataMap);

        DataMap returnInfo = new DataMap();
        returnInfo.put("roles", roles);
        returnInfo.put("sonRoles", sonRoles);
        returnInfo.put("current", id);
        logger.end();

        return InvokeResult.success(returnInfo);
    }

    /**
     * 新增角色信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
            return this.interceptorJurisdiction();

        logger.begin("新增角色信息");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("PARENT_ID")))
            dataMap.put("PARENT_ID", null);

        dataMap.put(JurisdictionUtil.ADD_QX, "0");
        dataMap.put(JurisdictionUtil.DEL_QX, "0");
        dataMap.put(JurisdictionUtil.EDIT_QX, "0");
        dataMap.put(JurisdictionUtil.QUERY_QX, "0");

        dataMap.put("ROLE_ID", ShortUUID.randomUUID());

        roleService.addRole(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 编辑角色信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public InvokeResult edit() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
            return this.interceptorJurisdiction();

        logger.begin("编辑角色信息");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("PARENT_ID")))
            dataMap.put("PARENT_ID", null);

        roleService.editRoleExceptQX(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 删除角色信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public InvokeResult remove() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
            return this.interceptorJurisdiction();

        logger.begin("删除角色信息");
        DataMap dataMap = this.getDataMap();
        if(Const.SUADMIN.equals(dataMap.getString("ROLE_ID"))){
            return InvokeResult.failure("该角色无法删除");
        }
        List<DataMap> sons = roleService.getSonRolesByFatherId(dataMap);
        if(!ListUtils.isEmptyList(sons)){
            return InvokeResult.failure("该角色下有子角色，无法删除");
        }
        roleService.removeRole(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 获取权限树形菜单
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/gettrees", method = RequestMethod.POST)
    public InvokeResult getTrees() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
            return this.interceptorJurisdiction();

        logger.begin("获取权限树形菜单");
        DataMap dataMap = this.getDataMap();
        List<ZTree> zTrees = new ArrayList<ZTree>();

        List<DataMap> fatherMenus = menuService.getAllFatherMenus();
        DataMap role = new DataMap();
        role.put("ROLE_ID", dataMap.getString("id"));
        role = roleService.getRoleById(role);
        for(DataMap fatherMenu : fatherMenus){
            zTrees.add(ZTree.getzTree(fatherMenu, role.getString(dataMap.getString("type"))));
            List<DataMap> sonMenus = menuService.getSonMenus(fatherMenu);
            for(DataMap sonMenu : sonMenus){
                zTrees.add(ZTree.getzTree(sonMenu, role.getString(dataMap.getString("type"))));
            }
        }
        logger.end();

        return InvokeResult.success("zTrees", zTrees);
    }

    /**
     * 设置权限
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setjurisdiction", method = RequestMethod.POST)
    public InvokeResult setJruisdiction() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
            return this.interceptorJurisdiction();

        logger.begin("设置权限");
        DataMap dataMap = this.getDataMap();

        roleService.setJruisdiction(dataMap.getString("nodes"), dataMap.getString("type"), dataMap.getString("id"));
        logger.end();
        return InvokeResult.success();
    }

}
