$(function () {
    var announceId=getQueryString('announceId');
    var isEdit=announceId?true:false;
    var addUrl='/epSystem/announceadmin/addannounce';
    var infoUrl="/epSystem/announceadmin/getannouncebyid?announceId="+announceId;
    var editUrl='/epSystem/announceadmin/modifyannounce';

    if (isEdit){
        getInfo();
    }

    function getInfo() {
        $.getJSON(infoUrl,function (data) {
           if (data.success){
               var announce=data.announce;
               $('#title').val(announce.title);
               $('#content').val(announce.content);
               if(announce.type==2){
                   $('#back').attr('href','/epSystem/whut/index?listType=3');
               }
           }
        });
    }
    $('#submit').click(function () {
        var listType=4;
        $.getJSON(infoUrl,function (data) {
            if (data.success){
                var announce=data.announce;
                if(announce.type==2){
                    listType=3;
                }
            }
        });

        var announce={};
        if (isEdit){
            announce.announceId=announceId;
        }
        announce.title=$('#title').val();
        announce.content=$('#content').val();
        var formData=new FormData();
        formData.append('announceStr',JSON.stringify(announce));
        $.ajax({
            url:(isEdit?editUrl:addUrl),
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if (data.success){
                    $.toast('提交成功！');
                    setTimeout(function () {
                        window.open("/epSystem/whut/index?listType="+listType,'_self');
                    },2000);
                }else {
                    $.toast('提交失败！'+data.errMsg);
                }
            }
        });
    });
});