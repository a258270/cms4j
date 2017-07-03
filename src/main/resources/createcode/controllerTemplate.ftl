package ${compeletePackage}.controller;

import com.cms4j.base.controller.PageBaseController;
import com.cms4j.base.system.dictionary.service.DictionaryService;
import ${compeletePackage}.service.${className}Service;
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
* Description: ${className}Controller
* Created by zmj on ${curDate}.
*/
@Controller
@RequestMapping(value = "/admin/${classNameLower}")
public class ${className}Controller extends PageBaseController {
    public ${className}Controller() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "${menuId}";
    }

    @Autowired
    private ${className}Service ${classNameLower}Service;

    @Autowired
    private DictionaryService dictionaryService;

    /**
    * 加载${showName}页面
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        logger.begin("加载${showName}页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("${ftlPath}/${upperPackage}/index");

        <#list datas as data>
            <#if data.isDic == '是'>
        DataMap ${data.dicCode} = new DataMap();
        dataMap.put("CODE", "${data.dicCode}");
        List<DataMap> ${data.dicCode}s = dictionaryService.getDictionaryByCode(${data.dicCode});
        modelAndView.addObject("${data.dicCode}s", ${data.dicCode}s);
            </#if>
        </#list>

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.QUERY_QX);
        logger.end();
        return modelAndView;
    }

    /**
    * 加载新增${showName}页面
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() throws Exception {
        logger.begin("加载新增${showName}页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("${ftlPath}/${upperPackage}/add");

        <#list datas as data>
            <#if data.isDic == '是'>
        DataMap ${data.dicCode} = new DataMap();
        dataMap.put("CODE", "${data.dicCode}");
        List<DataMap> ${data.dicCode}s = dictionaryService.getDictionaryByCode(${data.dicCode});
        modelAndView.addObject("${data.dicCode}s", ${data.dicCode}s);
            </#if>
        </#list>

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.ADD_QX);
        logger.end();
        return modelAndView;
    }

    /**
    * 加载编辑${showName}页面
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable String id) throws Exception {
        logger.begin("加载编辑${showName}页面");
        ModelAndView modelAndView = this.getModelAndView();
        modelAndView.setViewName("${ftlPath}/${upperPackage}/edit");
        DataMap dataMap = new DataMap();
        dataMap.put("${classNameUpper}_ID", id);
        dataMap = ${classNameLower}Service.get${className}ById(dataMap);
        modelAndView.addObject("${classNameLower}", dataMap);

        <#list datas as data>
            <#if data.isDic == '是'>
        DataMap ${data.dicCode} = new DataMap();
        dataMap.put("CODE", "${data.dicCode}");
        List<DataMap> ${data.dicCode}s = dictionaryService.getDictionaryByCode(${data.dicCode});
        modelAndView.addObject("${data.dicCode}s", ${data.dicCode}s);
            </#if>
        </#list>

        this.setJurisdictionInfo(modelAndView, JurisdictionUtil.EDIT_QX);
        logger.end();
        return modelAndView;
    }
}
