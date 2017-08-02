$(function () {
    $("#form").validate({
        rules:{
            USERNAME:{
                required:true,
                isUname:true,
            },
            PASSWORD:{
                required:true,
                isPwd:true,
            },
            REPASSWORD:{
                required:true,
                isPwd:true,
            },
            NAME:{
                required:true,
            },
            ROLE_ID:{
                required:true,
            },
            STATUS:{
                required:true,
            },
            PHONE:{
                isPhone:true,
            }

        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            if($("#ROLE_ID").val() == "" || $("#ROLE_ID").val() == null){
                showError("所属角色不能为空！");
                return false;
            }
            if($("#STATUS").val() == "" || $("#STATUS").val() == null){
                showError("状态不能为空！");
                return false;
            }
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