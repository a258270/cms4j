<!DOCTYPE html>
<html>
<head>
${r"<#include"} "${r"${ctxPath}"}/base/include/head.ftl" />
${r"<#include"} "${r"${ctxPath}"}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${r"${ctxPath}"}/static/${jsPath}/${upperPackage}/${classNameLower}/edit.js?v=${r"${v}"}"></script>
</head>
<body>
<article class="page-container">
    <form action="${r"${ctxPath}"}/admin/${classNameLower}/api/edit" method="post" class="form form-horizontal" id="form">
        <input type="hidden" name="${classNameUpper}_ID" value="${r"${"}${classNameLower}.${classNameUpper}_ID${r"}"}" />
<#list datas as data>
    <#if data.isFront == '是'>
        <#if data.isDic == '是'>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><#if data.isRequired == '是'><span class="c-red">*</span></#if>${data.remark}：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <select class="chosen-select select" size="1" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                        ${r"<#if "}${classNameLower}.${data.propertyNameUpper}${r"??>"}
                        <option value="">无</option>
                        ${r"<#list"} ${data.dicCode}s as ${data.dicCode}${r">"}
                        ${r"<#if"} ${data.dicCode}.DIC_ID == ${classNameLower}.${data.propertyNameUpper}${r">"}
                        <option value="${r"${"}${data.dicCode}.DIC_ID${r"}"}" selected>${r"${"}${data.dicCode}.NAME${r"}"}</option>
                        ${r"<#else>"}
                        <option value="${r"${"}${data.dicCode}.DIC_ID${r"}"}">${r"${"}${data.dicCode}.NAME${r"}"}</option>
                        ${r"</#if>"}
                        ${r"</#list>"}
                        ${r"<#else>"}
                        <option value="">无</option>
                        ${r"<#list"} ${data.dicCode}s as ${data.dicCode}${r">"}
                            <option value="${r"${"}${data.dicCode}.DIC_ID${r"}"}">${r"${"}${data.dicCode}.NAME${r"}"}</option>
                        ${r"</#list>"}
                        ${r"</#if>"}
                    </select>
                </div>
            </div>
        <#else>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><#if data.isRequired == '是'><span class="c-red">*</span></#if>${data.remark}：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <#if data.dataType == 'String'>
                        <input type="text" class="input-text" ${r"<#if "}${classNameLower}.${data.propertyNameUpper}${r"??>"}value="${r"${"}${classNameLower}.${data.propertyNameUpper}${r"}"}"${r"<#else>"}value=""${r"</#if>"} placeholder="请输入${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                    </#if>
                    <#if data.dataType == 'Integer' || data.dataType == 'Double'>
                        <input type="text" class="input-text" ${r"<#if "}${classNameLower}.${data.propertyNameUpper}${r"??>"}value="${r"${"}${classNameLower}.${data.propertyNameUpper}${r"?c}"}"${r"<#else>"}value=""${r"</#if>"} placeholder="请输入${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                    </#if>
                    <#if data.dataType == 'Date'>
                        <input type="text" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd' })" ${r"<#if "}${classNameLower}.${data.propertyNameUpper}${r"??>"}value="${r"${"}${classNameLower}.${data.propertyNameUpper}${r"}"}"${r"<#else>"}value=""${r"</#if>"} placeholder="请选择${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}" class="input-text Wdate" readOnly>
                    </#if>
                    <#if data.dataType == 'Datetime'>
                        <input type="text" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })" ${r"<#if "}${classNameLower}.${data.propertyNameUpper}${r"??>"}value="${r"${"}${classNameLower}.${data.propertyNameUpper}${r"}"}"${r"<#else>"}value=""${r"</#if>"} placeholder="请选择${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}" class="input-text Wdate" readOnly>
                    </#if>
                    <#if data.dataType == 'Boolean'>
                        <select class="chosen-select select" size="1" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                            ${r"<#if"} ${classNameLower}.${data.propertyNameUpper}${r"??>"}
                                <option value="">无</option>
                                ${r"<#if"} ${classNameLower}.${data.propertyNameUpper}${r">"}
                                <option value="1" selected>是</option>
                                <option value="0">否</option>
                                ${r"<#else>"}
                                <option value="1">是</option>
                                <option value="0" selected>否</option>
                                ${r"</#if>"}
                            ${r"<#else>"}
                                <option value="" selected>无</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            ${r"</#if>"}
                        </select>
                    </#if>
                </div>
            </div>
        </#if>
    </#if>
</#list>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="提交">
            </div>
        </div>
    </form>
</article>
</body>
</html>