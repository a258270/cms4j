var dataRows = [];
var selectEdit = "";

var add = function () {
    showWindow("新增属性", ctxPath + "/admin/createcode/add");
};

var save = function () {
    layer.confirm("确定要生成代码？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        toCreate();
    });
};

var successFn = function (res) {
    window.open(ctxPath + "/admin/createcode/download");
};

var toCreate = function () {
    if($("#UPPERPACKAGE").val() == ""){
        showError("上级包名不能为空！");
        return false;
    }

    if($("#JSPATH").val() == "") {
        showError("js存放路径不能为空！");
        return false;
    }

    if($("#FTLPATH").val() == "") {
        showError("ftl存放路径不能为空！");
        return false;
    }

    if($("#MAPPERPATH").val() == "") {
        showError("mapper存放路径不能为空！");
        return false;
    }

    if($("#CLASSNAME").val() == "") {
        showError("类名不能为空！");
        return false;
    }

    if($("#MENUNAME").val() == "") {
        showError("菜单名称不能为空！");
        return false;
    }

    if($("#SHOWNAME").val() == ""){
        showError("显示名称不能为空！");
        return false;
    }

    if($("#FATHERMENU").val() == "") {
        showError("上级菜单不能为空！");
        return false;
    }

    if($("#TABLEFRONT").val() == "") {
        showError("表前缀不能为空！");
        return false;
    }

    if(!(dataRows.length > 0)){
        showError("属性至少为一项");
        return false;
    }

    if(!/^[a-zA-Z0-9]+$/.test($("#UPPERPACKAGE").val())){
        showError("上级包名不合法！</br>格式要求：</br>1、不能包含“.”、“/”等字符</br>2、全名称只能包含字母、数字");
        return false;
    }

    if(!/^[A-Z][a-zA-Z0-9_]+$/.test($("#CLASSNAME").val())){
        showError("类名不合法！</br>格式要求：</br>1、首字母必须为大写字母</br>2、全名称只能包含字母、数字或者下划线");
        return false;
    }

    var param = {};
    param.UPPERPACKAGE = $("#UPPERPACKAGE").val();
    param.JSPATH = $("#JSPATH").val();
    param.FTLPATH = $("#FTLPATH").val();
    param.MAPPERPATH = $("#MAPPERPATH").val();
    param.CLASSNAME = $("#CLASSNAME").val();
    param.MENUNAME = $("#MENUNAME").val();
    param.SHOWNAME = $("#SHOWNAME").val();
    param.FATHERMENU = $("#FATHERMENU").val();
    param.TABLEFRONT = $("#TABLEFRONT").val();
    param.PARAMS = dataRows;

    sendRequest(ctxPath + "/admin/createcode/api/create", param, "POST", successFn);
};

var edit = function (id) {
    selectEdit = id;
    showWindow("编辑属性", ctxPath + "/admin/createcode/add");
};

var del = function (id) {
    layer.confirm("确定要删除该项？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        var dataRowsTmp = [];
        for(var i = 0, len = dataRows.length; i < len; i++) {
            if(i == id)
                continue;
            dataRowsTmp.push(dataRows[i]);
        }
        dataRows = dataRowsTmp;
        createTable();
    });
};

var createTable = function () {
    var innerHtmlStr = "";
    var tdBefore = "<td class='text-c'>";
    var tdAfter = "</td>";
    for(var i = 0, len = dataRows.length; i < len; i++){
        var iNo = i + 1;
        var innerHtmlStrTmp = "";
        var data = dataRows[i].split(",");
        for(var j = 0, len2 = data.length; j < len2; j++){
            innerHtmlStrTmp += tdBefore;
            innerHtmlStrTmp += data[j];
            innerHtmlStrTmp += tdAfter;
        }

        innerHtmlStrTmp = tdBefore + iNo + tdAfter + innerHtmlStrTmp;
        innerHtmlStrTmp += tdBefore + "<a title='编辑' href='javascript:;' onclick='edit(\"" + i + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a>" + "<a title='删除' href='javascript:;' onclick='del(\"" + i + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>" +tdAfter;
        innerHtmlStrTmp = "<tr>" + innerHtmlStrTmp + "</tr>";
        innerHtmlStr += innerHtmlStrTmp;
    }

    $("#tbody").html(innerHtmlStr);
};