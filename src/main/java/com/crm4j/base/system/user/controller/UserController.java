package com.crm4j.base.system.user.controller;

import com.crm4j.base.controller.PageBaseController;
import com.crm4j.base.system.role.service.RoleService;
import com.crm4j.base.system.user.service.UserService;
import com.crm4j.base.util.DataMap;
import com.crm4j.base.util.JurisdictionUtil;
import com.crm4j.base.util.LoggerUtil;
import com.crm4j.base.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/15.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends PageBaseController {
    public UserController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "4";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 加载用户管理页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        logger.begin("加载用户管理页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/user/user");

        List<DataMap> roles = roleService.getAllSonRoles();
        modelAndView.addObject("roleObjs", roles);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }

    /**
     * 加载新增用户页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() throws Exception {
        logger.begin("加载新增用户页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/user/user_add");
        List<DataMap> roleObjs = roleService.getAllSonRoles();
        modelAndView.addObject("roleObjs", roleObjs);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();
        return modelAndView;
    }

    /**
     * 加载新增用户页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) throws Exception {
        logger.begin("加载新增用户页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/user/user_edit");
        DataMap dataMap = new DataMap();
        dataMap.put("USER_ID", id);
        dataMap = userService.getUserById(dataMap);
        modelAndView.addObject("user", dataMap);
        List<DataMap> roleObjs = roleService.getAllSonRoles();
        modelAndView.addObject("roleObjs", roleObjs);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();
        return modelAndView;
    }

    @RequestMapping(value = "/editself", method = RequestMethod.GET)
    public ModelAndView editSelf() throws Exception {
        logger.begin("加载编辑个人信息页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/user/user_editself");
        DataMap user = SessionUtil.getCurUser();
        modelAndView.addObject("user", user);
        List<DataMap> roleObjs = roleService.getAllSonRoles();
        modelAndView.addObject("roleObjs", roleObjs);

        logger.end();
        return modelAndView;
    }
}
