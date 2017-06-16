var exit = function () {
    layer.confirm("确定要退出？", {
        btn: ["是", "否"]
    }, function(index) {
        location.replace(ctxPath + "/logout");
    });
};