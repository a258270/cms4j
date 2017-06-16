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