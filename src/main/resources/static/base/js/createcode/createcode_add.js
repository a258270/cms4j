/**
 * NAME  属性名称
 * REMARK  备注
 * DATATYPE  数据类型
 * ISREQUIRED  是否必选
 * ISFRONT  是否前台录入
 * DEFUALT  默认值
 * ISLIST  是否列表可见
 * ISSEARCH  是否为搜索项
 * SEARCHCONDITION  检索条件
 */
var selectIndex = "";
$(function () {
    $("#ISFRONT").change(function () {
       if($(this).val() == "是"){
           $("#DEFUALT").val("无");
           $("#DEFUALT").prop("disabled", true);
       }
       else{
           $("#DEFUALT").val("");
           $("#DEFUALT").prop("disabled", false);
       }
    });

    $("#ISSEARCH").change(function () {
        if($(this).val() == "是"){
            $("#SEARCHCONDITION").val("=");
            $("#SEARCHCONDITION").prop("disabled", false);
        }
        else{
            $("#SEARCHCONDITION").val("无");
            $("#SEARCHCONDITION").prop("disabled", true);
        }
    });

    $("#ISDIC").change(function () {
        if($(this).val() == "是") {
            $("#DICCODE").val("");
            $("#DICCODE").prop("disabled", false);
        }
        else{
            $("#DICCODE").val("无");
            $("#DICCODE").prop("disabled", true);
        }
    });

    if(parent.selectEdit != "") {
        var data = parent.dataRows[parent.selectEdit].split(",");
        $("#NAME").val(data[0]);
        $("#REMARK").val(data[1]);
        $("#DATATYPE").val(data[2]);
        $("#ISREQUIRED").val(data[3]);
        $("#ISFRONT").val(data[4]);
        $("#DEFUALT").val(data[5]);
        $("#ISLIST").val(data[6]);
        $("#ISSEARCH").val(data[7]);
        $("#SEARCHCONDITION").val(data[8]);
        $("#ISDIC").val(data[9]);
        $("#DICCODE").val(data[10]);

        if($("#ISFRONT").val() == "否"){
            $("#DEFUALT ").prop("disabled", false);
        }

        if($("#ISDIC").val() == "是") {
            $("#DICCODE").prop("disabled", false);
        }

        selectIndex = parent.selectEdit;

        parent.selectEdit = "";
    }
});

var save = function () {
    if($("#NAME").val() == ""){
        showError("属性名称不能为空！");
        return false;
    }

    if($("#REMARK").val() == "") {
        showError("备注不能为空！");
        return false;
    }

    if($("#DICCODE").val() == "") {
        showError("字典内码不能为空！");
        return false;
    }

    var data = "";
    data += $("#NAME").val() + ",";
    data += $("#REMARK").val() + ",";
    data += $("#DATATYPE").val() + ",";
    data += $("#ISREQUIRED").val() + ",";
    data += $("#ISFRONT").val() + ",";
    data += $("#DEFUALT").val() + ",";
    data += $("#ISLIST").val() + ",";
    data += $("#ISSEARCH").val() + ",";
    data += $("#SEARCHCONDITION").val() + ",";
    data += $("#ISDIC").val() + ",";
    data += $("#DICCODE").val();

    if(selectIndex != ""){
        parent.dataRows[selectIndex] = data;
    }
    else{
        parent.dataRows.push(data);
    }

    parent.createTable();

    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
};