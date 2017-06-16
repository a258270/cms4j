<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${ctxPath}/static/plugin/h-ui/lib/datatables/1.10.0/jquery.dataTables.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctxPath}/static/base/js/user/online/online.js?v=${v}"></script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 在线管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c">
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