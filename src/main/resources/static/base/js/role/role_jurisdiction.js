var setting = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};

var successFn = function (res) {
    $.fn.zTree.init($("#tree"), setting, res.zTrees);
};

var setSuccessFn = function (res) {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};
$(function () {
    sendRequest(ctxPath + "/role/api/gettrees", {type: type, id: id}, "POST", successFn);
});

var save = function () {
    var nodes = $.fn.zTree.getZTreeObj("tree").getCheckedNodes(true);
    var params = [];
    for(var i = 0, len = nodes.length; i < len; i++) {
        params.push(nodes[i].id);
    }
    if(params.length == 0){
        showError("当前没有菜单，无法保存");
    }

    var paramStr = "";
    for(var i = 0, len = params.length; i < len; i++){
        paramStr += params[i] + ",";
    }
    paramStr = paramStr.substring(0, paramStr.length - 1);
    sendRequest(ctxPath + "/role/api/setjurisdiction", {nodes: paramStr, type: type, id: id}, "POST", setSuccessFn);
};
