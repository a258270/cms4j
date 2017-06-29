<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${ctxPath}/static/base/js/role/role_add.js?v=${v}"></script>
</head>
<body>
<article class="page-container">
    <form action="${ctxPath}/admin/role/api/edit" method="post" class="form form-horizontal" id="form">
        <input type="hidden" value="${role.ROLE_ID}" name="ROLE_ID" />
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>父级角色：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="PARENT_ID" id="selectbox" disabled>
                    <#if !role.PARENT_ID??>
                    <option value="" selected>无</option>
                    <#list roleObjs as roleObj>
                        <option value="${roleObj.ROLE_ID}">${roleObj.ROLENAME}</option>
                    </#list>
                    <#else>
                    <#list roleObjs as roleObj>
                        <#if roleObj.ROLE_ID == role.PARENT_ID>
                            <option value="${roleObj.ROLE_ID}" selected>${roleObj.ROLENAME}</option>
                        <#else>
                            <option value="${roleObj.ROLE_ID}">${roleObj.ROLENAME}</option>
                        </#if>
                    </#list>
                    </#if>
				</select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${role.ROLENAME}" placeholder="请输入角色名称" id="ROLENAME" name="ROLENAME">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>排序：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${role.SORT}" placeholder="请输入排序码" id="SORT" name="SORT">
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