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
        data : "${classNameUpper}_ID",
        sClass : "text-c",
        render : function(data, type, row, meta) {
        var content = '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';
            content += '    <input type="checkbox" class="group-checkable" value="' + data + '" />';
            content += '    <span></span>';
            content += '</label>';
        return content;
        }
        },
        <#list datas as data>
            <#if data.isList == '是'>
                <#if data.isDic == '是'>
        {
            "data" : "${data.propertyNameUpper}_VALUE",
            "className" : "text-c"
        },
                <#else>
        <#if data.dataType == 'Date'>
        {
            "data" : "${data.propertyNameUpper}",
            "className" : "text-c"
        },
        </#if>
        <#if data.dataType == 'Datetime'>
        {
            "data" : "${data.propertyNameUpper}",
            "className" : "text-c",
            "render" : function (data, type, row, meta) {
                return getLocalTime(data);
            },
        },
        </#if>
        <#if data.dataType == 'Boolean'>
        {
            "data" : "${data.propertyNameUpper}",
            "className" : "text-c",
            "render" : function (data, type, row, meta) {
                if(data){
                return "是";
                }
                return "否";
            }
        },
        </#if>
        <#if data.dataType == 'String' || data.dataType == 'Integer' || data.dataType == 'Double'>
        {
            "data" : "${data.propertyNameUpper}",
            "className" : "text-c",
        },
        </#if>
                </#if>
            </#if>
        </#list>
        {
        "data" : "HANDLE",
        "className" : "text-c",
        "render" : function (data, type, row, meta) {
            var innerStr = "";
            if(EDIT_QX) {
                innerStr += "<a title='编辑' href='javascript:;' onclick='edit(\"" + row.${classNameUpper}_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a>";
            }
            if(DEL_QX) {
                innerStr += "<a title='删除' href='javascript:;' onclick='del(\"" + row.${classNameUpper}_ID + "\")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
            }
            return innerStr;
            }
        }
        ],

        "sAjaxSource" : ctxPath + "/admin/${classNameLower}/api/get${classNameLower}s?now=" + new Date().getTime(),
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
<#list datas as data>
    <#if data.isFront == '是'>
        <#if data.isDic == '是'>
        ${r"$('#"}${data.propertyNameUpper}${r"')"}.chosen({
            no_results_text: "没有找到结果！",//搜索无结果时显示的提示
            search_contains:true,   //关键字模糊搜索，设置为false，则只从开头开始匹配
            allow_single_deselect:true
        });
        <#else>
            <#if data.dataType == 'Boolean'>
            ${r"$('#"}${data.propertyNameUpper}${r"')"}.chosen({
                no_results_text: "没有找到结果！",//搜索无结果时显示的提示
                search_contains:true,   //关键字模糊搜索，设置为false，则只从开头开始匹配
                allow_single_deselect:true
            });
            </#if>
        </#if>
    </#if>
</#list>
});

//请求接口的处理函数
var successFn = function (res) {
    onTableQuery();
};

//新增${showName}
var add = function () {
    showWindow("新增${showName}", ctxPath + "/admin/${classNameLower}/add");
};

//批量删除
var batchdel = function () {
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

    layer.confirm("确定要批量删除？", {
        btn: ["是", "否"]
    }, function(index) {
        layer.close(index);
        batchremoves = batchremoves.substring(0, batchremoves.length - 1);
        sendRequest(ctxPath + "/admin/${classNameLower}/api/batchremoves", {batchremoves : batchremoves}, "POST", successFn);
    });
};

//列表查询
var onTableQuery = function () {
    var params = {};
<#list datas as data>
    <#if data.isSearch == '是'>
    params.${data.propertyNameUpper} = ${r"$('#"}${data.propertyNameUpper}${r"')"}.val();
    </#if>
</#list>
    tableQuery(tab, params);
};

//编辑${showName}
var edit = function (id) {
    showWindow("编辑${showName}", ctxPath + "/admin/${classNameLower}/edit/" + id);
};

//删除${showName}
var del = function (id) {
    layer.confirm("确定要删除？", {
        btn: ["是", "否"]
        }, function(index) {
        layer.close(index);
        sendRequest(ctxPath + "/admin/${classNameLower}/api/batchremoves", {batchremoves : id}, "POST", successFn);
    });
};