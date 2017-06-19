<!DOCTYPE html>
<html>
<head>
<#include "${r"${ctxPath}"}/base/include/head.ftl" />
<#include "${r"${ctxPath}"}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${r"${ctxPath}"}/static/${jsPath}/${upperPackage}/add.js?v=${r"${v}"}"></script>
</head>
<body>
<article class="page-container">
    <form action="${r"${ctxPath}"}/${classNameLower}/api/add" method="post" class="form form-horizontal" id="form">
        <#list datas as data>
            <#if data.isFront == '是'>
                <#if data.isDic == '是'>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><#if data.isRequired == '是'><span class="c-red">*</span></#if>${data.remark}：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select class="select" size="1" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                    <option value="" selected>无</option>
                ${r"<#list"} ${data.dicCode}s as ${data.dicCode}${r">"}
                    <option value="${r"${"}${data.dicCode}.DIC_ID${r"}"}">${r"${"}${data.dicCode}.NAME${r"}"}</option>
                ${r"</#list>"}
                </select>
            </div>
        </div>
                <#else>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><#if data.isRequired == '是'><span class="c-red">*</span></#if>${data.remark}：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <#if data.dataType == 'String' || data.dataType == 'Integer' || data.dataType == 'Double'>
                <input type="text" class="input-text" value="" placeholder="请输入${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                </#if>
                <#if data.dataType == 'Date'>
                <input type="text" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })" placeholder="请选择${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}" class="input-text Wdate" readOnly>
                </#if>
                <#if data.dataType == 'Datetime'>
                <input type="text" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })" placeholder="请选择${data.remark}" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}" class="input-text Wdate" readOnly>
                </#if>
                <#if data.dataType == 'Boolean'>
                <select class="select" size="1" id="${data.propertyNameUpper}" name="${data.propertyNameUpper}">
                    <option value="" selected>无</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
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