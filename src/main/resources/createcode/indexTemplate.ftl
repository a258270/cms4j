templateParam.put("basePackage", basePackage);
templateParam.put("upperPackage", upperPackage);
templateParam.put("compeletePackage", compeletePackage);
templateParam.put("jsPath", jsPath);
templateParam.put("ftlPath", ftlPath);
templateParam.put("className", className);
templateParam.put("classNameLower", classNameLower);
templateParam.put("classNameUpper", classNameUpper);
templateParam.put("menuName", menuName);
templateParam.put("showName", showName);
templateParam.put("fatherMenuId", fatherMenuId);
templateParam.put("fatherMenuName", fatherMenuName);
templateParam.put("isTalbeFront", isTalbeFront);
templateParam.put("curDate", curDate);
templateParam.put("menuId", menuId);
templateParam.put("datas", datas);
data.put("propertyName", params[i]);//属性名称
data.put("propertyNameUpper", params[i].toUpperCase());//属性名称（大写）
data.put("remark", params[i + 1]);//备注
data.put("dataType", params[i + 2]);//数据类型
data.put("isRequired", params[i + 3]);//是否必填
data.put("isFront", params[i + 4]);//是否前台录入
data.put("default", params[i + 5]);//默认值
data.put("isList", params[i + 6]);//是否列表可见
data.put("isSearch", params[i + 7]);//是否为搜索项
data.put("searchCondition", params[i + 8]);//检索条件
data.put("isDic", params[i + 9]);//是否为数据字典
data.put("dicCode", params[i + 10]);//字典内码
<!DOCTYPE html>
<html>
<head>
<#include "${r"${ctxPath}"}/base/include/head.ftl" />
<#include "${r"${ctxPath}"}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${r"${ctxPath}"}/static/plugin/h-ui/lib/datatables/1.10.0/jquery.dataTables.min.js?v=${r"${v}"}"></script>
    <script type="text/javascript" src="${r"${ctxPath}"}/static/${jsPath}/index.js?v=${r"${v}"}"></script>
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

        </#if>

        <#if data.dataType == 'String'><input type="text" class="input-text" style="width:150px" placeholder="${data.remark}" id="${data.propertyNameUpper}"></#if>
    </#if>
</#list>
        用户名：<input type="text" class="input-text" style="width:150px" placeholder="输入用户名" id="USERNAME">
        姓名：<input type="text" class="input-text" style="width:150px" placeholder="输入姓名" id="NAME">
        角色：<select class="select" style="width:150px;height: 31px;" size="1" id="ROLE_ID">
        <option value="" selected>不限</option>
    <#list roleObjs as roleObj>
        <option value="${roleObj.ROLE_ID}">${roleObj.ROLENAME}</option>
    </#list>
    </select>
        状态： <select class="select" style="width:150px;height: 31px;" size="1" id="STATUS">
        <option value="" selected>不限</option>
        <option value="0">冻结</option>
        <option value="1">正常</option>
    </select>
        <button type="button" class="btn btn-success radius" onclick="onTableQuery();"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <#if QX.DEL_QX == "true"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量冻结</a></#if>
            <#if QX.ADD_QX == "true"><a href="javascript:;" onclick="add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增用户</a></#if>
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
            <th class="text-c">用户名</th>
            <th class="text-c">姓名</th>
            <th class="text-c">所属角色</th>
            <th class="text-c">联系电话</th>
            <th class="text-c">最后登录时间</th>
            <th class="text-c">IP地址</th>
            <th class="text-c">状态</th>
            <th class="text-c">操作</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>