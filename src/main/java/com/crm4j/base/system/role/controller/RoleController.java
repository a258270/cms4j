package com.crm4j.base.system.role.controller;

import com.crm4j.base.controller.PageBaseController;
import com.crm4j.base.system.role.service.RoleService;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.JurisdictionUtil;
import com.crm4j.base.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/14.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends PageBaseController {
    private LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    public RoleController() {
        this.menuId = "3";
    }


    /**
     * 加载角色管理页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        logger.begin("加载角色管理页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/role/role");

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }

    /**
     * 加载新增角色页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() throws Exception {
        logger.begin("加载新增角色页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/role/role_add");

        List<DataMap> fatherRoles = roleService.getAllFatherRoles();
        modelAndView.addObject("fatherRoles", fatherRoles);
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();
        return modelAndView;
    }

    /**
     * 加载编辑角色页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) throws Exception {
        logger.begin("加载编辑角色页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/role/role_edit");

        DataMap role = new DataMap();
        role.put("ROLE_ID", id);
        role = roleService.getRoleById(role);
        modelAndView.addObject("role", role);

        List<DataMap> roleObjs = roleService.getAllFatherRoles();
        modelAndView.addObject("roleObjs", roleObjs);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();
        return modelAndView;
    }

    /**
     * 加载设置权限页面
     * @param type
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jurisdiction/{type}/{id}", method = RequestMethod.GET)
    public ModelAndView jurisdiction(@PathVariable String type, @PathVariable String id) throws Exception {
        logger.begin("加载设置权限页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/role/role_jurisdiction");

        modelAndView.addObject("type",type);
        modelAndView.addObject("id", id);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();
        return modelAndView;
    }
}
