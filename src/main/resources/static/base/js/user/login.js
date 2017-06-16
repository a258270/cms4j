$(function () {
   getCode();
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
            CODE:{
                required:true,
                isDigits:true,
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

                window.location.href = ctxPath + "/index";
            });

        }
    });
});

var getCode = function () {
    var random = Math.random();
    $("#randomcode").attr("src", ctxPath + "/getcode?r=" + random);
};