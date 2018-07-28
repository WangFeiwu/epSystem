$(function () {
    var listId=getQueryString('listId');
    var typeId=getQueryString('typeId');
    var isEdit=listId?true:false;

    var addListUrl='/epSystem/exerciseslistadmin/addexerciseslist';
    var ListInfoUrl="/epSystem/exerciseslistadmin/getexerciseslistbyid?listId="+listId;
    var editListUrl='/epSystem/exerciseslistadmin/modifyexerciseslist';

    if (isEdit) {
        getListInfo();
    }else {
        initType(typeId);
    }
    function initType(typeId) {
        if (typeId==1){
            $('#exercisesList-type').val($('#1').text());
            $('#back').attr('href','/epSystem/whut/index?listType=1');
        }else if(typeId==2){
            $('#exercisesList-type').val($('#2').text());
            $('#back').attr('href','/epSystem/whut/index?listType=2');
        }
    }
    function getListInfo() {
        $.getJSON(ListInfoUrl, function (data) {
            if (data.success) {
                var exercisesList = data.exercisesList;
                $('#exercisesList-name').val(exercisesList.listName);
                $('#exercisesList-desc').val(exercisesList.listDesc);
                if (exercisesList.listType==1){
                    $('#exercisesList-type').val($('#1').text());
                    $('#back').attr('href','/epSystem/whut/index?listType=1');
                }else if(exercisesList.listType==2){
                    $('#exercisesList-type').val($('#2').text());
                    $('#back').attr('href','/epSystem/whut/index?listType=2');
                }
            }
        });
    }
    $('#submit').click(function () {
        var exercisesList={};
        if (isEdit){
            exercisesList.listId=listId;
        }
        exercisesList.listName=$('#exercisesList-name').val();
        exercisesList.listDesc=$('#exercisesList-addr').val();
        exercisesList.listDesc=$('#exercisesList-desc').val();
        exercisesList.listType=$('#exercisesList-type').get(0).selectedIndex+1;
        typeId=exercisesList.listType;
        var formData=new FormData();
        formData.append('listStr',JSON.stringify(exercisesList));
        $.ajax({
            url:(isEdit?editListUrl:addListUrl),
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if (data.success){
                    $.toast('提交成功！');
                    setTimeout(function () {
                        window.open("/epSystem/whut/index?listType="+typeId,'_self');
                    },2000);
                }else {
                    $.toast('提交失败！'+data.errMsg);
                }
            }
        });
    });
});