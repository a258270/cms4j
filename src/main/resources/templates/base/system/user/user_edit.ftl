<!DOCTYPE html>
<html>
<head>
<#include "${ctxPath}/base/include/head.ftl" />
<#include "${ctxPath}/base/include/jurisdiction.ftl" />
    <!-- 页面独有文件 -->
    <script type="text/javascript" src="${ctxPath}/static/base/js/user/user_edit.js?v=${v}"></script>
</head>
<body>
<article class="page-container">
    <form action="${ctxPath}/user/api/edit" method="post" class="form form-horizontal" id="form">
        <input type="hidden" name="USER_ID" value="${user.USER_ID}" />
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.USERNAME}" placeholder="请输入用户名" disabled>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" value="" placeholder="请输入密码，为空则为不修改" id="PASSWORD" name="PASSWORD">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">确认密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" value="" placeholder="请输入确认密码，为空则为不修改" id="REPASSWORD" name="REPASSWORD">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.NAME}" placeholder="请输入姓名" id="NAME" name="NAME">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属角色：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="ROLE_ID" id="ROLE_ID">
                    <#list roleObjs as roleObj>
                        <#if roleObj.ROLE_ID == user.ROLE_ID>
                        <option value="${roleObj.ROLE_ID}" selected>${roleObj.ROLENAME}</option>
                        <#else>
                        <option value="${roleObj.ROLE_ID}">${roleObj.ROLENAME}</option>
                        </#if>
                    </#list>
				</select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">联系电话：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <#if user.PHONE??>
                <input type="text" class="input-text" value="${user.PHONE}" placeholder="请输入联系电话" id="PHONE" name="PHONE">
                <#else>
                    <input type="text" class="input-text" value="" placeholder="请输入联系电话" id="PHONE" name="PHONE">
                </#if>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>状态：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" disabled>
                    <#if user.STATUS>
                        <option value="1" selected>正常</option>
                    <#else>
                        <option value="0" selected>冻结</option>
                    </#if>
				</select>
				</span>
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