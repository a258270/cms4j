package com.cms4j.base.system.createcode.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.menu.service.MenuService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * description:
 * @author: zmj
 * @create: 2017/6/17
 */
@Controller
@RequestMapping(value = "/createcode")
public class CreatecodeController extends PageBaseController {
    public CreatecodeController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "9";
    }

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/index")
    public ModelAndView index() throws Exception {
        logger.begin("加载代码生成页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/createcode/createcode");

        List<DataMap> menuObjs = menuService.getAllFatherMenus();
        modelAndView.addObject("menuObjs", menuObjs);

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add() throws Exception {
        logger.begin("加载新增属性页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/createcode/createcode_add");

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();
        return modelAndView;
    }
}
