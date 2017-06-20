$(function () {
    $("#form").validate({
        rules:{
            <#list datas as data>
            <#if data.isFront == '是'>
            ${data.propertyNameUpper}:{
                <#if data.isRequired == '是'>
                required:true,
                </#if>
            }<#if data_index <= maxIsFrontIndex>,</#if>
            </#if>
            </#list>
        },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
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
});