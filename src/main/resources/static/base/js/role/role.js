var curRole;
$(function () {
    sendRequest(ctxPath + "/admin/role/api/getroles", null, "POST", successFn);
});

var successFn = function (res) {
    var roles = res.roles;
    var sonRoles = res.sonRoles;
    var current = res.current;

    var innerStr = "";
    for(var i = 0, len = roles.length; i < len; i++){
        var role = roles[i];
        var thBefore = "<li";
        if(role.ROLE_ID == current){
            curRole = role;
            thBefore += " class='current'";
        }

        thBefore += " onclick='toGet(\"" + role.ROLE_ID + "\")'>";

        var thInfo = "<a href=\"#\">" + role.ROLENAME + "</a>";
        var thAfter = "</li>";
        innerStr += thBefore + thInfo + thAfter;
    }

    $("#head").html(innerStr);

    var innerTableStr = "";
    for(var i = 0, len = sonRoles.length; i < len; i++) {
        var sonRole = sonRoles[i];
        var tdBefore = "<td class='text-c'>";
        var tdAfter = "</td>";
        var tdInfo = "";
        var innerTableStrTmp = "";
        innerTableStrTmp += tdBefore + sonRole.ROLENAME + tdAfter;
        tdInfo = "<a title='设置新增权限' href='javascript:;' onclick='setJurisdiction(\"" + sonRole.ROLE_ID + "\", \"ADD_QX\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe60c;</i></a>";
        innerTableStrTmp += tdBefore + tdInfo + tdAfter;
        tdInfo = "<a title='设置删除权限' href='javascript:;' onclick='setJurisdiction(\"" + sonRole.ROLE_ID + "\", \"DEL_QX\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe60c;</i></a>";
        innerTableStrTmp += tdBefore + tdInfo + tdAfter;
        tdInfo = "<a title='设置修改权限' href='javascript:;' onclick='setJurisdiction(\"" + sonRole.ROLE_ID + "\", \"EDIT_QX\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe60c;</i></a>";
        innerTableStrTmp += tdBefore + tdInfo + tdAfter;
        tdInfo = "<a title='设置查询权限' href='javascript:;' onclick='setJurisdiction(\"" + sonRole.ROLE_ID + "\", \"QUERY_QX\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe60c;</i></a>";
        innerTableStrTmp += tdBefore + tdInfo + tdAfter;
        innerTableStrTmp += tdBefore + sonRole.SORT + tdAfter;
        tdInfo = "<a title='编辑' href='javascript:;' onclick='role_edit(\"" + sonRole.ROLE_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a>" + "<a title='删除' href='javascript:;' onclick='role_del(\"" + sonRole.ROLE_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
        innerTableStrTmp += tdBefore + tdInfo + tdAfter;
        innerTableStr += "<tr>" + innerTableStrTmp + "</tr>"
    }

    $("#tbody").html(innerTableStr);
};

var delSuccessFn = function (res) {
    window.location.reload();
};

var add = function () {
    showWindow("新增角色", ctxPath + "/admin/role/add");
};

var edit = function () {
    showWindow("编辑角色", ctxPath + "/admin/role/edit/" + curRole.ROLE_ID);
};

var del = function () {
    layer.confirm("确定要删除该项？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        sendRequest(ctxPath + "/admin/role/api/remove", {ROLE_ID : curRole.ROLE_ID}, "POST", delSuccessFn);
    });
};

var toGet = function (id) {
    sendRequest(ctxPath + "/admin/role/api/getroles/" + id, null, "POST", successFn);
};

var role_edit = function (id) {
    showWindow("编辑角色", ctxPath + "/admin/role/edit/" + id);
};

var role_del = function (id) {
    layer.confirm("确定要删除该项？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        sendRequest(ctxPath + "/admin/role/api/remove", {ROLE_ID : id}, "POST", delSuccessFn);
    });
};

var setFatherJurisdiction = function () {
    showWindow("设置权限", ctxPath + "/admin/role/jurisdiction/QUERY_QX/" + curRole.ROLE_ID, $(window).width() / 5);
};

var setJurisdiction = function (id, type) {
    showWindow("设置权限", ctxPath + "/admin/role/jurisdiction/" + type + "/" + id, $(window).width() / 5);
}