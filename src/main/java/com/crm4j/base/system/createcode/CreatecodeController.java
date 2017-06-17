package com.crm4j.base.system.createcode;

import com.crm4j.base.controller.PageBaseController;
import com.crm4j.base.util.JurisdictionUtil;
import com.crm4j.base.util.LoggerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        logger.begin("加载代码生成页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/createcode/createcode");

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }
}
