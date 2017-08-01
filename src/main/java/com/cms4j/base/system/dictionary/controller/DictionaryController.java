package com.cms4j.base.system.dictionary.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.dictionary.service.DictionaryService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/6
 */
@Controller
@RequestMapping(value = "/admin/dictionary")
public class DictionaryController extends PageBaseController {
    public DictionaryController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "5";
    }

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 加载字典管理页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        logger.begin("加载字典管理页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("base/system/dictionary/dictionary");
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }

    /**
     * 加载字典新增页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(@RequestParam(required = false) String pId) throws Exception {
        ModelAndView modelAndView = this.getModelAndView();
        logger.begin("加载新增字典页面");

        if(StringUtils.isBlank(pId)){
            List<DataMap> dictionaries = dictionaryService.getAllFatherDictionaries();
            modelAndView.addObject("dictionaries", dictionaries);
        }
        else{
            DataMap dictionary = new DataMap();
            dictionary.put("DIC_ID", pId);
            dictionary = dictionaryService.getDictionaryById(dictionary);
            List<DataMap> dictionaries = new ArrayList<DataMap>();
            dictionaries.add(dictionary);
            modelAndView.addObject("dictionaries", dictionaries);
        }

        modelAndView.setViewName("base/system/dictionary/dictionary_add");
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();

        return modelAndView;
    }

    /**
     * 加载字典编辑页面
     * @param DIC_ID 字典id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit/{DIC_ID}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String DIC_ID, @RequestParam(required = false) String pId) throws Exception {
        ModelAndView modelAndView = this.getModelAndView();

        logger.begin("加载编辑字典页面");
        DataMap dataMap = new DataMap();
        dataMap.put("DIC_ID", DIC_ID);
        dataMap = dictionaryService.getDictionaryById(dataMap);

        modelAndView.addObject("dictionary", dataMap);

        if(StringUtils.isBlank(pId)){
            List<DataMap> dictionaries = dictionaryService.getAllFatherDictionaries();
            modelAndView.addObject("dictionarieObjs", dictionaries);
        }
        else{
            DataMap dictionary = new DataMap();
            dictionary.put("DIC_ID", pId);
            dictionary = dictionaryService.getDictionaryById(dictionary);
            List<DataMap> dictionaries = new ArrayList<DataMap>();
            dictionaries.add(dictionary);
            modelAndView.addObject("dictionarieObjs", dictionaries);
        }



        modelAndView.setViewName("base/system/dictionary/dictionary_edit");
        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();

        return modelAndView;
    }
}
