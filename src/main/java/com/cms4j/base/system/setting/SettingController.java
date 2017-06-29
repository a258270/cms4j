package com.cms4j.base.system.setting;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.plugin.BaseSetting;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:
 * Created by zmj on 2017/6/16.
 */
@Controller
@RequestMapping(value = "/admin/setting")
public class SettingController extends PageBaseController {
    public SettingController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "1";
    }

    @Autowired
    private BaseSetting baseSetting;

    /**
     * 加载系统设置界面
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        logger.begin("加载系统设置界面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/setting/setting");

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }
}
