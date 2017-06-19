<!DOCTYPE html>
<html>
<head>
<#include "${r"${ctxPath}"}/base/include/head.ftl" />
<#include "${r"${ctxPath}"}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${r"${ctxPath}"}/static/plugin/h-ui/lib/datatables/1.10.0/jquery.dataTables.min.js?v=${r"${v}"}"></script>
    <script type="text/javascript" src="${r"${ctxPath}"}/static/${jsPath}/${upperPackage}/index.js?v=${r"${v}"}"></script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> ${fatherMenuName} <span class="c-gray en">&gt;</span> ${menuName} <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
<#list datas as data>
    <#if data.isSearch == '是'>
        ${data.remark}：
        <#if data.isDic == '是'>
            <select class="select" style="width:150px;height: 31px;" size="1" id="${data.propertyNameUpper}">
                <option value="" selected>不限</option>
                ${r"<#list"} ${dicCode}s as ${dicCode}${r">"}
                <option value="${r"${"}${dicCode}.DIC_ID${r"}"}">${r"${"}${dicCode}.NAME${r"}"}</option>
                ${r"<#/list>"}
            </select>
        <#else>
            <#if data.dataType == 'String' || data.dataType == 'Integer' || data.dataType == 'Double'><input type="text" class="input-text" style="width:150px" placeholder="请输入${data.remark}" id="${data.propertyNameUpper}"></#if>
            <#if data.dataType == 'boolean'>
                <select class="select" style="width:150px;height: 31px;" size="1" id="STATUS">
                    <option value="" selected>不限</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </#if>
            <#if data.dataType == 'Date'><input type="text" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })" id="${data.propertyNameUpper}" class="input-text Wdate" style="width:120px;" readOnly></#if>
            <#if data.dataType == 'Datetime'><input type="text" onClick="WdatePicker({ dateFmt:'yyyy-MM-dd HH:mm:ss' })" id="${data.propertyNameUpper}" class="input-text Wdate" style="width:120px;" readOnly></#if>
        </#if>
    </#if>
</#list>
        <button type="button" class="btn btn-success radius" onclick="onTableQuery();"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            ${r"<#if QX.DEL_QX == 'true'>"}<a href="javascript:;" onclick="batchdel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>${r"</#if>"}
            ${r"<#if QX.ADD_QX == 'true'>"}<a href="javascript:;" onclick="add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增</a>${r"</#if>"}
        </span>
    </div>
    <div class="mt-20">
        <table id="tab" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <th class="center">
                <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                    <input type="checkbox" name="ck_all" class="group-checkable" data-set="#sample_1 .checkboxes" />
                    <span></span>
                </label>
            </th>
            <#list datas as data>
                <#if data.isList == '是'>
                    <th class="text-c">${data.remark}</th>
                </#if>
            </#list>
            <th class="text-c">操作</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>