<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${ctxPath}/static/base/js/createcode/createcode.js?v=${v}"></script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 代码生成 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <table class="table table-border table-bordered table-striped">
        <tbody>
        <tr>
            <td class="text-r"><code>完整包名：<span class="c-red">*</span></code></td>
            <td><input type="text" class="input-text radius" placeholder="输入完整包名" id="COMPLETEPACK" name="COMPLETEPACK"></td>
            <td class="text-l"><code class="c-red">该类的完整包名,如：输入com.cms4j.base.test，则实际包名为：com.cms4j.base.test</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>类名：<span class="c-red">*</span></code></td>
            <td><input type="text" class="input-text radius" placeholder="输入类名" id="CLASSNAME" name="CLASSNAME"></td>
            <td class="text-l"><code class="c-red">首字母需大写</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>显示名称：<span class="c-red">*</span></code></td>
            <td><input type="text" class="input-text radius" placeholder="输入显示名称" id="SHOWNAME" name="SHOWNAME"></td>
            <td class="text-l"><code class="c-red">用于对该类型进行新增或修改时的显示名称</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>上级菜单：<span class="c-red">*</span></code></td>
            <td>
                <select class="select input-text radius" size="1" name="PARENT_ID" id="FATHERMENU" name="FATHERMENU">
                    <option value="" selected></option>
                <#list menuObjs as menuObj>
                    <option value="${menuObj.MENU_ID}">${menuObj.NAME}</option>
                </#list>
                </select>
            </td>
            <td class="text-l"><code class="c-red">数据库表的前缀</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>表前缀：<span class="c-red">*</span></code></td>
            <td><input type="text" class="input-text radius" value="TB_" placeholder="输入表前缀" id="TABLEFRONT" name="TABLEFRONT"></td>
            <td class="text-l"><code class="c-red">左侧菜单栏处显示</code></td>
        </tr>
        </tbody>
    </table>
    <div class="mt-20">
        <table class="table table-border table-bg">
            <thead>
                <th class="text-c">序号</th>
                <th class="text-c">属性名称</th>
                <th class="text-c">备注</th>
                <th class="text-c">数据类型</th>
                <th class="text-c">必填项</th>
                <th class="text-c">前台录入</th>
                <th class="text-c">默认值</th>
                <th class="text-c">列表可见</th>
                <th class="text-c">搜索项</th>
                <th class="text-c">检索条件</th>
                <th class="text-c">数据字典</th>
                <th class="text-c">字典内码</th>
                <th class="text-c">操作</th>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="add();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增属性</a>
            <a href="javascript:;" onclick="save();" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 生成</a>
        </span>
    </div>
</div>
</body>
</html>