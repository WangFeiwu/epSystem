$(function () {
    var Url='/epSystem/personinfoadmin/addUsers';
    $('#submit').click(function () {
        var personFile=$('#person-file')[0].files[0];
        var formData=new FormData();
        formData.append('personFile',personFile);
        $.ajax({
            url:Url,
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if (data.success){
                    $.toast('提交成功！');
                }else {
                    $.toast('提交失败！'+data.errMsg);
                }
            }
        });
    });
});