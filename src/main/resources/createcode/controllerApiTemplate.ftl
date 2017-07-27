package ${compeletePackage}.controller;

import com.cms4j.base.controller.ApiBaseController;
import ${compeletePackage}.service.${className}Service;
import com.cms4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
* Description: ${className}ApiController
* Created by zmj on ${curDate}.
*/
@RestController
@RequestMapping(value = "/admin/${classNameLower}/api")
public class ${className}ApiController extends ApiBaseController {
    public ${className}ApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "${menuId}";
    }

    @Autowired
    private ${className}Service ${classNameLower}Service;

    /**
    * 获取${showName}分页数据
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/get${classNameLower}s", method = RequestMethod.POST)
    public PageDto getUsers() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
        return new PageDto();
        logger.begin("获取${showName}分页数据");
        DataMap dataMap = this.getDataMap();
        <#list datas as data>
            <#if data.isSearch == '是'>
        if(StringUtils.isBlank(dataMap.getString("${data.propertyNameUpper}")))
            dataMap.put("${data.propertyNameUpper}", null);
            </#if>
        </#list>
        Page page = new Page();
        page.setParams(dataMap);
page.setPageNumber(Integer.valueOf(dataMap.getString("iDisplayStart")) / Integer.valueOf(dataMap.getString("iDisplayLength")));
        page.setPageSize(Integer.valueOf(dataMap.getString("iDisplayLength")));
        List<DataMap> ${classNameLower}s = ${classNameLower}Service.get${className}s(page);
        page.setResults(${classNameLower}s);
        logger.end();

        return PageConverter.toPageDto(page, Integer.valueOf(dataMap.getString("sEcho")));
    }

    /**
    * 批量删除${showName}
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/batchremoves", method = RequestMethod.POST)
    public InvokeResult batchRemoves() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
        return this.interceptorJurisdiction();

        logger.begin("批量删除${showName}");
        DataMap dataMap = this.getDataMap();
        ${classNameLower}Service.batchRemoves(dataMap.getString("batchremoves"));
        logger.end();
        return InvokeResult.success();
    }

    /**
    * 新增${showName}
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
        return this.interceptorJurisdiction();

        logger.begin("新增${showName}");
        DataMap dataMap = this.getDataMap();
        ${classNameLower}Service.add${className}(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
    * 编辑${showName}
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public InvokeResult edit() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
        return this.interceptorJurisdiction();

        logger.begin("编辑${showName}");
        DataMap dataMap = this.getDataMap();

        ${classNameLower}Service.edit${className}(dataMap);
        logger.end();
        return InvokeResult.success();
    }
}