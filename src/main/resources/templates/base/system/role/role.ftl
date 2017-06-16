<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
<!-- 页面独有文件 -->
<script type="text/javascript" src="${ctxPath}/static/base/js/role/role.js?v=${v}"></script>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-black">
        <div class="container cl">
            <nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
                <ul class="cl" id="head">
                    <#--<li class="current"><a href="/">首页</a></li>
                    <li><a href="#">核心</a></li>
                    <li><a href="#">扩展</a></li>
                    <li><a href="#">联系我们</a></li>-->
                </ul>
            </nav>
        </div>
    </div>
</header>
<div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <#if QX.DEL_QX == "true"><a href="javascript:;" onclick="del()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 删除角色</a></#if>
            <#if QX.EDIT_QX == "true"><a href="javascript:;" onclick="setFatherJurisdiction()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 分配权限</a></#if>
            <#if QX.ADD_QX == "true"><a href="javascript:;" onclick="add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增角色</a></#if>
            <#if QX.EDIT_QX == "true"><a href="javascript:;" onclick="edit()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe60c;</i> 编辑角色</a></#if>
        </span>
</div>
<table class="table table-border table-bordered table-hover">
    <thead>
        <tr>
            <th class="text-c">角色名称</th>
            <th class="text-c">增</th>
            <th class="text-c">删</th>
            <th class="text-c">改</th>
            <th class="text-c">查</th>
            <th class="text-c">排序</th>
            <th class="text-c">操作</th>
        </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
</body>
</html>