var tab;
var bDictionaryGetted = false;
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
                data : "DIC_ID",
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
                "data" : "NAME",
                "className" : "text-c"
            },
            {
                "data" : "CODE",
                "className" : "text-c"
            },
            {
                "data" : "SORT",
                "className" : "text-c"
            },
            {
                "data" : "HANDLE",
                "className" : "text-c",
                "render" : function (data, type, row, meta) {
                    var innerStr = "";
                    if(QUERY_QX && !bDictionaryGetted) {
                        innerStr += "<a title='查看子项' href='javascript:;' onclick='dic_get(\"" + row.DIC_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe695;</i></a>";
                    }
                    if(EDIT_QX) {
                        innerStr += "<a title='编辑' href='javascript:;' onclick='dic_edit(\"" + row.DIC_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a>";
                    }
                    if(DEL_QX) {
                        innerStr += "<a title='删除' href='javascript:;' onclick='dic_del(\"" + row.DIC_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                    }

                    return innerStr;
                }
            }
        ],

        "sAjaxSource" : "/dictionary/api/getdictionaries?now=" + new Date().getTime(),
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
});

var successFn = function (res) {
    onTableQuery();
};

//新增字典
var add = function () {
    showWindow("新增字典", ctxPath + "/dictionary/add");
};

//批量删除
var datadel = function () {
    var checkboxs = $("tbody :checkbox", '#tab');
    var batchremoves = "";
    checkboxs.each(function () {
        if($(this).prop("checked")) {
            batchremoves += $(this).val() + ","
        }
    });

    if(batchremoves == "") {
        showError("请选择至少一项");
        return false;
    }

    layer.confirm("确定要删除这些项以及其子项？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        batchremoves = batchremoves.substring(0, batchremoves.length - 1);
        sendRequest(ctxPath + "/dictionary/api/batchremoves", {batchremoves : batchremoves}, "POST", successFn);
    });


};

var onTableQuery = function () {
    var params = {};
    params.NAME = $("#NAME").val();
    params.CODE = $("#CODE").val();
    tableQuery(tab, params);
}

var dic_edit = function (id) {
    showWindow("编辑字典", ctxPath + "/dictionary/edit/" + id);
};

var dic_del = function (id) {
    layer.confirm("确定要删除该项以及其子项？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        sendRequest(ctxPath + "/dictionary/api/batchremoves", {batchremoves : id}, "POST", successFn);
    });
};

var dic_get = function (id) {
    bDictionaryGetted = true;
    tab.fnSettings().sAjaxSource = ctxPath + "/dictionary/api/getdictionaries/" + id + "?now=" + Math.random();
    tab.fnDraw();
};

var back2Top = function () {
    bDictionaryGetted = false;
    tab.fnSettings().sAjaxSource = ctxPath + "/dictionary/api/getdictionaries?now=" + Math.random();
    tab.fnDraw();
};