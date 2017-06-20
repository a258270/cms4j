package server.basepackage.basepackage.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.dictionary.service.DictionaryService;
import server.basepackage.basepackage.service.TTestService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import com.cms4j.base.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
* Description: TTestController
* Created by zmj on 2017/06/20.
*/
@Controller
@RequestMapping(value = "/ttest")
public class TTestController extends PageBaseController {
    public TTestController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "17";
    }

    @Autowired
    private TTestService ttestService;

    @Autowired
    private DicitonaryService dictionaryService;

    /**
    * 加载测试名称页面
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        logger.begin("加载测试名称页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/ftlpath/basepackage/index");


        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }

    /**
    * 加载新增测试名称页面
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() throws Exception {
        logger.begin("加载新增测试名称页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/ftlpath/basepackage/add");


        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();
        return modelAndView;
    }

    /**
    * 加载编辑测试名称页面
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) throws Exception {
        logger.begin("加载编辑测试名称页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/ftlpath/basepackage/edit");
        DataMap dataMap = new DataMap();
        dataMap.put("TTEST_ID", id);
        dataMap = ttestService.getTTestById(dataMap);
        modelAndView.addObject("ttest", dataMap);


        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();
        return modelAndView;
    }
}
