var dataRows = [];
var selectEdit = "";

var add = function () {
    showWindow("新增属性", ctxPath + "/createcode/add");
};

var save = function () {
    
};

var edit = function (id) {
    selectEdit = id;
    showWindow("编辑属性", ctxPath + "/createcode/add");
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