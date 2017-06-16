<!DOCTYPE html>
<html>
<head>
    <#include "${ctxPath}/base/include/head.ftl" />

    <!-- 页面独有文件 -->
    <link href="${ctxPath}/static/plugin/h-ui/h-ui.admin/css/H-ui.login.css?v=${v}" rel="stylesheet" type="text/css" />
    <link href="${ctxPath}/static/plugin/h-ui/h-ui.admin/css/style.css?v=${v}" rel="stylesheet" type="text/css" />
    <script src="${ctxPath}/static/base/js/user/login.js?v=${v}"></script>
</head>
<script>
    if (window.top !== window.self) {
        window.top.location = window.location;
    }
</script>
<body>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="${ctxPath}/tologin" method="post" id="form">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="USERNAME" name="USERNAME" type="text" placeholder="请输入用户名" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="PASSWORD" name="PASSWORD" type="password" placeholder="请输入密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe613;</i></label>
                <div class="formControls col-xs-8">
                    <input class="input-text size-L" type="text" placeholder="请输入验证码" id="CODE" name="CODE" style="width:150px;">
                    <img src="" id="randomcode"> <a id="kanbuq" onclick="getCode();">看不清，换一张</a>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="submit" class="btn btn-success radius size-L" value="登  录">
                    <input name="" type="reset" class="btn btn-default radius size-L" value="重  置">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright by ${baseSetting.title}</div>
</body>
</html>