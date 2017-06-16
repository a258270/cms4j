$(function () {
    $("#form").validate({
        rules:{
            PASSWORD:{
                isPwd:true,
            },
            REPASSWORD:{
                isPwd:true,
            },
            NAME:{
                required:true,
            },
            ROLE_ID:{
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