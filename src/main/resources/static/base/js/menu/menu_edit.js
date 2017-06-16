$(function () {
    $("#form").validate({
        rules:{
            NAME:{
                required:true,
            },
            URL:{
                required:true,
            },
            ICON:{
                isEnglishOrNumber:true,
            },
            SORT:{
                required:true,
                isIntGteZero:true,
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