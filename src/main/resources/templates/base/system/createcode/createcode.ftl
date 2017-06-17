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
            <td class="text-r"><code>基础包名：</code></td>
            <td><input type="text" class="input-text radius" placeholder="输入基础包名"></td>
            <td class="text-l" style="color: #FF0000"><code>该类的基础包名,如：输入test，则实际包名为：com.cms4j.test</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>上级包名：</code></td>
            <td><input type="text" class="input-text radius" placeholder="输入上级包名"></td>
            <td class="text-l" style="color: #FF0000"><code>该类的上级包名,如：基础报名输入base，上级报名输入test，则实际包名为：com.cms4j.base.test</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>类名：</code></td>
            <td><input type="text" class="input-text radius" placeholder="输入类名"></td>
            <td class="text-l" style="color: #FF0000"><code>首字母需大写</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>上级菜单：</code></td>
            <td><input type="text" class="input-text radius" placeholder="输入类名"></td>
            <td class="text-l" style="color: #FF0000"><code>左侧菜单栏处显示</code></td>
        </tr>
        <tr>
            <td class="text-r"><code>表前缀：</code></td>
            <td><input type="text" class="input-text radius" value="TB_" placeholder="表前缀"></td>
            <td class="text-l" style="color: #FF0000"><code>数据库表的前缀</code></td>
        </tr>
        <#--<tr>
            <td class="text-r"><code>菜单名称：</code></td>
            <td><input type="text" class="input-text radius" placeholder="文本框"></td>
            <td class="text-l" style="color: #FF0000"><code>input-text radius</code></td>
            <td class="text-r"><code>类名：</code></td>
            <td><input type="text" class="input-text radius" placeholder="文本框"></td>
            <td class="text-r"><code></code></td>
        </tr>-->
        </tbody>
    </table>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            <a href="javascript:;" onclick="add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增菜单</a>
            <a href="javascript:;" onclick="back2Top()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe66b;</i> 返回顶级</a>
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
            <th class="text-c">菜单名称</th>
            <th class="text-c">链接地址</th>
            <th class="text-c">排序</th>
            <th class="text-c">操作</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>