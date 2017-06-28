package com.cms4j.base.index.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.menu.service.MenuService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * @author: zmj
 * @create: 2017/6/5
 */
@Controller
public class IndexController extends PageBaseController {
    private LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

    @Autowired
    private MenuService menuService;

    @RequestMapping("/admin/index")
    public String index(Model model) {
        try {
            logger.begin("加载首页面");
            List<DataMap> fatherMenus = menuService.getAllFatherMenus();
            List<DataMap> hasJurisdictionMenus = new ArrayList<DataMap>();
            if (fatherMenus != null) {
                for (DataMap fatherMenu : fatherMenus) {
                    boolean flag = JurisdictionUtil.hasJurisdiction(JurisdictionUtil.QUERY_QX, fatherMenu.getString("MENU_ID"));
                    if (flag)
                        hasJurisdictionMenus.add(fatherMenu);
                }
            }

            for (DataMap hasJurisdictionMenu : hasJurisdictionMenus) {
                if(!StringUtils.isBlank(hasJurisdictionMenu.getString("ICON")))
                    hasJurisdictionMenu.put("ICON", "&#" + hasJurisdictionMenu.getString("ICON") + ";");
                List<DataMap> sonMenus = menuService.getSonMenus(hasJurisdictionMenu);
                if (sonMenus != null) {
                    List<DataMap> sonMenusList = new ArrayList<DataMap>();
                    for (DataMap sonMenu : sonMenus) {
                        boolean flag = JurisdictionUtil.hasJurisdiction(JurisdictionUtil.QUERY_QX, sonMenu.getString("MENU_ID"));
                        if (flag) {
                            if(!StringUtils.isBlank(sonMenu.getString("ICON")))
                                sonMenu.put("ICON", "&#" + sonMenu.getString("ICON") + ";");
                            sonMenusList.add(sonMenu);
                        }
                    }
                    hasJurisdictionMenu.put("sonMenus", sonMenusList);
                }

                model.addAttribute("menus", hasJurisdictionMenus);
            }
        }
        catch (Exception e) {
            logger.error("index----error");
        }
        finally {
            logger.end();
        }
        return "base/index";
    }
}
