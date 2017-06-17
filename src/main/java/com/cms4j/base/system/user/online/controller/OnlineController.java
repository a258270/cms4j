package com.cms4j.base.system.user.online.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.role.service.RoleService;
import com.cms4j.base.system.user.online.service.SessionService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/16.
 */
@Controller
@RequestMapping(value = "/user/online")
public class OnlineController extends PageBaseController {
    public OnlineController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "8";
    }

    @Autowired
    private SessionService sessionService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView onlineIndex() throws Exception {
        logger.begin("加载在线管理界面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/user/online/online");

        List<DataMap> roles = roleService.getAllSonRoles();
        modelAndView.addObject("roleObjs", roles);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }
}
