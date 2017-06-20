var sendRequest = function (url, param, ajaxType, successFn, errorFn) {
    url = ctxPath + url;
    if(url.indexOf("?") > 0) {
        url += "&ajax=true";
    }
    else{
        url += "?ajax=true";
    }
    param = param ? param : {};
    ajaxType = ajaxType ? ajaxType : "POST";
    //needFormPostCfg = needFormPostCfg ? needFormPostCfg : true;
    var loader = layer.load(0);
    return $.ajax({
        type : ajaxType,
        url : url,
        data : param,
        dataType : "json",
        success : function (res) {
            layer.close(loader);
            if(res.errorCode == "-1"){
                location.replace(ctxPath + "/login");
                return false;
            }
            if(res.hasErrors){
                showError(res.errorMessage);
                return false;
            }
            if(typeof (successFn) != "undefined"){
                successFn(res);
            }
        },
        error : function (res) {
            layer.close(loader);
            showError("系统出错，请稍后重试，或联系管理员！");
            if(typeof (errroFn) != "undefined"){
                errorFn(res);
            }
        }
    });
};

var showError = function (message) {
    layer.alert(message, {icon: 2});
};

var tableQuery = function (obj, params) {
    var url = obj.fnSettings().sAjaxSource;
    var paramInUrl = "";
    for(var name in params) {
        paramInUrl += "&" + name + "=" + params[name];
    }
    if(url.indexOf("?") == -1){
        url += "?ajax=true&now=" + Math.random();
    }
    else{
        url = url.substring(0, url.indexOf("?")) + "?ajax=true&now=" + Math.random();
    }

    url += paramInUrl;
    obj.fnSettings().sAjaxSource = url;
    obj.fnDraw();
};

var showWindow = function (title, url, width, height) {
    if(typeof (width) == "undefined" || width == null) {
        width = $(window).width() / 2;
    }
    if(typeof (height) == "undefined" || height == null) {
        height = $(window).height() / 2;
    }
    layer_show(title, url, width, height);
};

var getLocalTime = function (nS) {
    var now = new Date(nS);
    var year=now.getFullYear();
    var month=now.getMonth()+1;
    var date=now.getDate();
    var hour=now.getHours();
    var minute=now.getMinutes();
    var second=now.getSeconds();
    return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
};