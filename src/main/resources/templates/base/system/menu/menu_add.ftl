<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
<!-- 页面独有文件 -->
<script type="text/javascript" src="${ctxPath}/static/base/js/menu/menu_add.js?v=${v}"></script>
</head>
<body>
<article class="page-container">
    <form action="${ctxPath}/menu/api/add" method="post" class="form form-horizontal" id="form">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>父级菜单：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="PARENT_ID" id="selectbox">
					<option value="" selected>无</option>
                    <#list fatherMenus as fatherMenu>
                    <option value="${fatherMenu.MENU_ID}">${fatherMenu.NAME}</option>
                    </#list>
				</select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入菜单名称" id="NAME" name="NAME">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>链接地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="#" placeholder="请输入链接地址" id="URL" name="URL" disabled>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">图标编码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入图标编码" id="ICON" name="ICON">
                <a class="maincolor" target="_blank" href="http://www.h-ui.net/Hui-3.7-Hui-iconfont.shtml">不清楚图标编码？点击查看</a>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入排序码" id="SORT" name="SORT">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="提交">
            </div>
        </div>
    </form>
</article>
</body>
</html>