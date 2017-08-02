<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
<!-- 页面独有文件 -->
<script type="text/javascript" src="${ctxPath}/static/base/js/dictionary/dictionary_add.js?v=${v}"></script>
</head>
<body>
<article class="page-container">
    <form action="${ctxPath}/admin/dictionary/api/add" method="post" class="form form-horizontal" id="form">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>父级字典：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="chosen-select select" size="1" name="PARENT_ID">
					<option value="" selected>无</option>
                    <#list dictionaries as dictionary>
                    <option value="${dictionary.DIC_ID}">${dictionary.NAME}</option>
                    </#list>
				</select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>字典名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入字典名称" id="NAME" name="NAME">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>字典内码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入字典内码" id="CODE" name="CODE">
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