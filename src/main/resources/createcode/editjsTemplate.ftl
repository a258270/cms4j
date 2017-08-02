$(function () {
    $("#form").validate({
        rules:{
        <#list datas as data>
            <#if data.isFront == '是'>
        ${data.propertyNameUpper}:{
                <#if data.isRequired == '是'>
                required:true,
                </#if>
                <#if data.dataType == 'Integer'>
                isInteger:true,
                </#if>
                <#if data.dataType == 'Double'>
                isNumber:true,
                </#if>
            }<#if data_index <= maxIsFrontIndex>,</#if>
            </#if>
        </#list>
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
<#list datas as data>
    <#if data.isFront == '是'>
        <#if data.isRequired == '是'>
            <#if data.isDic == '是'>
            if(${r"$('#"}${data.propertyNameUpper}${r"')"}.val() == "" || ${r"$('#"}${data.propertyNameUpper}${r"')"}.val() == null){
                showError("${data.remark}不能为空！");
                return false;
            }
            <#else>
                <#if data.dataType == 'Boolean'>
            if(${r"$('#"}${data.propertyNameUpper}${r"')"}.val() == "" || ${r"$('#"}${data.propertyNameUpper}${r"')"}.val() == null){
                showError("${data.remark}不能为空！");
                return false;
            }
                </#if>
            </#if>
        </#if>
    </#if>
</#list>
            $(form).ajaxSubmit(function (res) {
                if(res.hasErrors){
                    showError(res.errorMessage);
                    return false;
                }

                var index = parent.layer.getFrameIndex(window.name);
                parent.onTableQuery();
                parent.layer.close(index);
            });
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