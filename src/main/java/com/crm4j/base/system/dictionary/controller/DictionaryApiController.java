package com.crm4j.base.system.dictionary.controller;

import com.crm4j.base.controller.ApiBaseController;
import com.crm4j.base.system.dictionary.service.DictionaryService;
import com.crm4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/7
 */
@RestController
@RequestMapping(value = "/dictionary/api")
public class DictionaryApiController extends ApiBaseController {

    public DictionaryApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "5";
    }

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 获取字典的分页数据
     * @param parentid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getdictionaries/{parentid}", "/getdictionaries"}, method = RequestMethod.POST)
    public PageDto getDictionaries(@PathVariable(required = false) String parentid) throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
            return new PageDto();

        logger.begin("获取字典分页数据");
        DataMap dataMap = this.getDataMap();
        dataMap.put("PARENT_ID", parentid);
        Page page = new Page();
        page.setParams(dataMap);
        page.setPageNumber(Integer.valueOf(dataMap.getString("iDisplayStart")));
        page.setPageSize(Integer.valueOf(dataMap.getString("iDisplayLength")));
        List<DataMap> list = dictionaryService.getDictionaries(page);

        page.setResults(list);
        logger.end();
        return PageConverter.toPageDto(page, Integer.valueOf(dataMap.getString("sEcho")));
    }

    /**
     * 新增字典
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
            return this.interceptorJurisdiction();

        logger.begin("新增字典");
        DataMap dataMap = this.getDataMap();

        DataMap dictionaryTest = dictionaryService.getDictionaryByCode(dataMap);
        if(dictionaryTest != null)
            return InvokeResult.failure("该字典内码已存在，请修改！");

        if(StringUtils.isBlank(dataMap.getString("PARENT_ID")))
            dataMap.put("PARENT_ID", null);
        if(!StringUtils.isBlank(dataMap.getString("PARENT_ID"))){
            DataMap fatherDictionary = new DataMap();
            fatherDictionary.put("DIC_ID", dataMap.get("PARENT_ID"));
            fatherDictionary = dictionaryService.getDictionaryById(fatherDictionary);
            dataMap.put("LEVEL", Integer.valueOf(fatherDictionary.getString("LEVEL")) + 1);
        }
        else
            dataMap.put("LEVEL", 1);

        dataMap.put("DIC_ID", ShortUUID.randomUUID());

        dictionaryService.addDictionary(dataMap);

        logger.end();
        return InvokeResult.success();
    }

    /**
     * 批量删除字典
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchremoves", method = RequestMethod.POST)
    public InvokeResult batchRemoves() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
            return this.interceptorJurisdiction();
        logger.begin("批量删除字典");
        DataMap dataMap = this.getDataMap();
        dictionaryService.batchRemoveDictionaries(dataMap.getString("batchremoves"));

        logger.end();
        return InvokeResult.success();
    }

    /**
     * 编辑字典
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public InvokeResult update() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
            return this.interceptorJurisdiction();

        logger.begin("编辑字典");
        DataMap dataMap = this.getDataMap();

        DataMap dictionaryTest = dictionaryService.getDictionaryByCode(dataMap);
        if(dictionaryTest != null && !dictionaryTest.getString("DIC_ID").equals(dataMap.getString("DIC_ID")))
            return InvokeResult.failure("该字典内码已存在，请修改！");

        dictionaryService.updateDictionary(dataMap);

        logger.end();
        return InvokeResult.success();
    }

}
