<!DOCTYPE html>
<html>
<head>
    <#include "${ctxPath}/base/include/head.ftl" />
    <!-- 页面独有文件 -->
    <script src="${ctxPath}/static/base/js/index.js?v=${v}"></script>
</head>
<script>
if (window.top !== window.self) {
    window.top.location = window.location;
}
</script>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">${baseSetting.title}</a>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li><#if Session.session_user_key??>${Session.session_user_key.NAME}</#if></li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">操作中心 <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" onclick="myselfinfo();">个人信息</a></li>
                            <li><a href="javascript:;" onclick="exit();">退出</a></li>
                        </ul>
                    </li>
                    <#--<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>-->
                    <li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                            <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                            <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                            <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                            <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                            <li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <#if menus??>
        <#list menus as menu>
        <dl>
            <dt><i class="Hui-iconfont">${menu.ICON}</i> ${menu.NAME}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <#if menu.sonMenus?? && (menu.sonMenus?size > 0)>
                <ul>
                    <#list menu.sonMenus as sonMenus>
                    <li><a data-href="${sonMenus.URL}" data-title="${sonMenus.NAME}" href="javascript:void(0)"><i class="Hui-iconfont">${sonMenus.ICON}</i>${sonMenus.NAME}</a></li>
                    </#list>
                </ul>
                </#if>
            </dd>
        </dl>
        </#list>
        </#if>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src=""></iframe>
        </div>
    </div>
</section>
</body>
<script language="JavaScript">
    var myselfinfo = function () {
        showWindow("编辑个人信息", ctxPath + "/user/editself")
    };
</script>
</html>