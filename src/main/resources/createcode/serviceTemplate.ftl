package ${compeletePackage}.service;

import com.cms4j.base.dao.DaoSupport;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.Page;
import com.cms4j.base.util.ShortUUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cms4j.base.util.DateUtil;
import java.util.Arrays;
import java.util.List;

/**
* Description: ${className}Service
* Created by zmj on ${curDate}.
*/
@Service
public class ${className}Service {

    @Autowired
    private DaoSupport daoSupport;

    /**
    * 根据${classNameUpper}_ID获取数据
    * @param dataMap
    * @return
    * @throws Exception
    */
    public DataMap get${className}ById(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("${className}Mapper.get${className}ById", dataMap);
    }

    /**
    * 获取${showName}分页数据
    * @param page
    * @return
    * @throws Exception
    */
    public List<DataMap> get${className}s(Page page) throws Exception {
        return (List<DataMap>) daoSupport.findForList("${className}Mapper.get${className}s", page);
    }

    /**
    * 批量删除${showName}
    * @param objs
    * @throws Exception
    */
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoves(String objs) throws Exception {
        String[] objArr = objs.split(",");
        List<String> objList = Arrays.asList(objArr);
        daoSupport.batchDelete("${className}Mapper.batchRemoves", objList);
    }

    /**
    * 新增${showName}
    * @param dataMap
    * @throws Exception
    */
    @Transactional(rollbackFor = Exception.class)
    public void add${className}(DataMap dataMap) throws Exception {
        dataMap.put("${classNameUpper}_ID", ShortUUID.randomUUID());
        <#list datas as data>
            <#if data.isFront == '否'>
                <#if data.dataType == 'String'>
        dataMap.put("${data.propertyNameUpper}", "${data.default}");
                </#if>
                <#if data.dataType == 'Integer' || data.dataType == 'Double'>
        dataMap.put("${data.propertyNameUpper}", ${data.default});
                </#if>
                <#if data.dataType == 'Date'>
        dataMap.put("${data.propertyNameUpper}", DateUtil.getCurrentDate());
                </#if>
                <#if data.dataType == "Datetime">
        dataMap.put("${data.propertyNameUpper}", DateUtil.getCurrentTime());
                </#if>
                <#if data.dataType == "Boolean">
                    <#if data.default == ''>
        dataMap.put("${data.propertyNameUpper}", false);
                    <#else>
        dataMap.put("${data.propertyNameUpper}", true);
                    </#if>
                </#if>
            </#if>
            <#if data.isFront == '是'>
                <#if data.isRequired == '否'>
        if(StringUtils.isBlank(dataMap.getString("${data.propertyNameUpper}")))
            dataMap.put("${data.propertyNameUpper}", null);
                </#if>
            </#if>
        </#list>
        daoSupport.save("${className}Mapper.add${className}", dataMap);
    }

    /**
    * 编辑${showName}
    * @param dataMap
    * @throws Exception
    */
    @Transactional(rollbackFor = Exception.class)
    public void edit${className}(DataMap dataMap) throws Exception {
        <#list datas as data>
            <#if data.isRequired == '否' && data.isFront == '是'>
        if(StringUtils.isBlank(dataMap.getString("${data.propertyNameUpper}")))
            dataMap.put("${data.propertyNameUpper}", null);
            </#if>
        </#list>
        daoSupport.update("${className}Mapper.edit${className}", dataMap);
    }

    /**
    * 编辑${showName}中的非前台录入属性
    * @param dataMap
    * @throws Exception
    */
    @Transactional(rollbackFor = Exception.class)
    public void editNotFrontOf${className}(DataMap dataMap) throws Exception {
    <#list datas as data>
        <#if data.isFront == '否'>
        if(StringUtils.isBlank(dataMap.getString("${data.propertyNameUpper}")))
            dataMap.put("${data.propertyNameUpper}", null);
        </#if>
    </#list>
        daoSupport.update("${className}Mapper.editNotFrontOf${className}", dataMap);
    }
}
