$(function () {
    var loginUrl='/epSystem/personinfoadmin/checklogin';
    var registerUrl='/epSystem/personinfoadmin/register';

    $('#login').click(function () {
        var person={};
        person.number=$('#number').val();
        person.password=$('#password').val();
        var formData=new FormData();
        formData.append('personStr',JSON.stringify(person));
        $.ajax({
            url:loginUrl,
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            success:function (data) {
                if (data.success){
                    $.toast('登录成功！');
                    setTimeout(function () {
                        window.open("/epSystem/whut/index",'_self');
                    },2000);
                }else {
                    $.toast('登录失败！'+data.errMsg);
                }
            }
        });
    });
});