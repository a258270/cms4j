package com.cms4j.base.system.menu.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.menu.service.MenuService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by my on 2017/6/13.
 */
@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController extends PageBaseController {
    @Autowired
    private MenuService menuService;

    public MenuController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "6";
    }

    /**
     * 加载菜单管理页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        logger.begin("加载菜单管理页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/menu/menu");
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();

        return modelAndView;
    }

    /**
     * 加载新增菜单页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() throws Exception {
        logger.begin("加载新增菜单页面");
        ModelAndView modelAndView = this.getModelAndView();
        List<DataMap> fatherMenus = menuService.getAllFatherMenus();
        modelAndView.addObject("fatherMenus", fatherMenus);
        modelAndView.setViewName("base/system/menu/menu_add");
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();

        return modelAndView;
    }

    /**
     * 加载编辑菜单页面
     * @param MENU_ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit/{MENU_ID}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String MENU_ID) throws Exception {
        logger.begin("加载编辑菜单页面");
        ModelAndView modelAndView = this.getModelAndView();
        DataMap dataMap = this.getDataMap();
        dataMap.put("MENU_ID", MENU_ID);
        dataMap = menuService.getMenuById(dataMap);
        modelAndView.addObject("menu", dataMap);

        List<DataMap> fatherMenus = menuService.getAllFatherMenus();
        modelAndView.addObject("menuObjs", fatherMenus);
        modelAndView.setViewName("base/system/menu/menu_edit");
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();

        return modelAndView;
    }
}
