var tab;
$(function () {
    tab = $("#tab").dataTable({
        "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
        "bServerSide" : true, //是否启动服务器端数据导入
        "bStateSave" : false, //是否打开客户端状态记录功能,此功能在ajax刷新纪录的时候不会将个性化设定回复为初始化状态
        "aLengthMenu" : [pageNumber, pageNumber * 2, pageNumber * 4, pageNumber * 6], //更改显示记录数选项
        "iDisplayLength" : pageNumber, //默认显示的记录数
        "bAutoWidth" : false, //是否自适应宽度
        //"bScrollInfinite" : false, //是否启动初始化滚动条
        "bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
        "bPaginate" : true, //是否显示（应用）分页器
        "bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数
        "bSort" : false, //是否启动各个字段的排序功能
        "bFilter" : false, //是否启动过滤、搜索功能
        "columns" : [
            {
                className: "td-checkbox",
                orderable : false,
                bSortable : false,
                data : "USER_ID",
                sClass : "text-c",
                render : function(data, type, row, meta) {
                    var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';
                    content += '    <input type="checkbox" class="group-checkable" value="' + data + '" />';
                    content += '    <span></span>';
                    content += '</label>';
                    return content;
                }
            },
            {
                "data" : "USERNAME",
                "className" : "text-c"
            },
            {
                "data" : "NAME",
                "className" : "text-c"
            },
            {
                "data" : "ROLENAME",
                "className" : "text-c"
            },
            {
                "data" : "PHONE",
                "className" : "text-c"
            },
            {
                "data" : "LAST_LOGIN",
                "className" : "text-c",
                "render" : function (data, type, row, meta) {
                    if(typeof (data) == "undefined" || data == null){
                        return "";
                    }
                    var newDate = new Date();
                    newDate.setTime(data);
                    return newDate.toLocaleString();
                }
            },
            {
                "data" : "IP",
                "className" : "text-c"
            },
            {
                "data" : "STATUS",
                "className" : "text-c",
                "render" : function (data, type, row, meta) {
                    if(data){
                        return "正常";
                    }
                    return "冻结";
                }
            },
            {
                "data" : "HANDLE",
                "className" : "text-c",
                "render" : function (data, type, row, meta) {
                    var innerStr = "";
                    if(QUERY_QX) {
                        innerStr += "<a title='踢出' href='javascript:;' onclick='online_kick(\"" + row.USER_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe706;</i></a>";
                    }

                    return innerStr;
                }
            }
        ],

        "sAjaxSource" : ctxPath + "/admin/user/online/api/getonlineusers?now=" + new Date().getTime(),
        //服务器端，数据回调处理
        "fnServerData" : function(sSource, aDataSet, fnCallback) {
            $.ajax({
                "dataType" : 'json',
                "type" : "POST",
                "url" : sSource,
                "data" : aDataSet,
                "success" : fnCallback
            });
        },
        drawCallback : function() {
            // 取消全选
            $(":checkbox[name='ck_all']").prop('checked', false);
        },
    });

    $('#tab').on("change", ":checkbox", function() {
        // 列表复选框
        if ($(this).is("[name='ck_all']")) {
            // 全选
            $(":checkbox", '#tab').prop("checked",$(this).prop("checked"));
        }else{
            // 一般复选
            var checkbox = $("tbody :checkbox", '#tab');
            $(":checkbox[name='ck_all']", '#tab').prop('checked', checkbox.length == checkbox.filter(':checked').length);
        }
    });
    $("#ROLE_ID").chosen({
        no_results_text: "没有找到结果！",//搜索无结果时显示的提示
        search_contains:true,   //关键字模糊搜索，设置为false，则只从开头开始匹配
        allow_single_deselect:true
    });
    $("#STATUS").chosen({
        no_results_text: "没有找到结果！",//搜索无结果时显示的提示
        search_contains:true,   //关键字模糊搜索，设置为false，则只从开头开始匹配
        allow_single_deselect:true
    });
});

var successFn = function (res) {
    onTableQuery();
};

var onTableQuery = function () {
    var params = {};
    params.USERNAME = $("#USERNAME").val();
    params.NAME = $("#NAME").val();
    params.ROLE_ID = $("#ROLE_ID").val();
    params.STATUS = $("#STATUS").val();
    tableQuery(tab, params);
};

var online_kick = function (id) {
    layer.confirm("确定要踢出该用户？", {
        btn: ["是", "否"]
    }, function(index) {
        sendRequest(ctxPath + "/admin/user/online/api/kick", {USER_ID: id}, "POST", successFn);
    });
};