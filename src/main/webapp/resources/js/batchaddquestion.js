$(function () {
    var listId=getQueryString('listId');
    var Url="/epSystem/questionadmin/addquestions?listId="+listId;

    var i=0;
    $('#back').attr('href','/epSystem/questionadmin/questionlistadmin?listId='+listId);

    $('#add-one').click(function () {
       var html='<div class="list-block">\n' +
           '                <ul>\n' +
           '                    <li>\n' +
           '                        <div class="item-content">\n' +
           '                            <div class="item-inner">\n' +
           '                                <div class="item-title label">题目内容</div>\n' +
           '                                <div class="item-input">\n' +
           '                                    <textarea class="que-content" placeholder="题目内容"></textarea>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </div>\n' +
           '                    </li>\n' +
           '                    <li>\n' +
           '                        <div class="item-content">\n' +
           '                            <div class="item-inner">\n' +
           '                                <div class="item-title label">题目类别</div>\n' +
           '                                <div class="item-input">\n' +
           '                                    <select class="que-type" data-i="'+
           i+
           '">\n' +
           '                                        <option id="1">主观题</option>\n' +
           '                                        <option id="2">客观题</option>\n' +
           '                                    </select>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </div>\n' +
           '                    </li>\n' +
           '                    <div class="not-choice'+
           i+
           '">\n' +
           '                        <li>\n' +
           '                            <div class="item-content">\n' +
           '                                <div class="item-inner">\n' +
           '                                    <div class="item-title label">答案</div>\n' +
           '                                    <div class="item-input">\n' +
           '                                        <textarea class="que-answer" placeholder="答案"></textarea>\n' +
           '                                    </div>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </li>\n' +
           '                    </div>\n' +
           '                    <div class="choice'+
           i+
           '" style="display: none">\n' +
           '                        <li>\n' +
           '                            <div class="item-content">\n' +
           '                                <div class="item-inner">\n' +
           '                                    <div class="item-title label">选项A</div>\n' +
           '                                    <div class="item-input">\n' +
           '                                        <textarea class="que-optionA" placeholder="选项A"></textarea>\n' +
           '                                    </div>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </li>\n' +
           '                        <li>\n' +
           '                            <div class="item-content">\n' +
           '                                <div class="item-inner">\n' +
           '                                    <div class="item-title label">选项B</div>\n' +
           '                                    <div class="item-input">\n' +
           '                                        <textarea class="que-optionB" placeholder="选项B"></textarea>\n' +
           '                                    </div>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </li>\n' +
           '                        <li>\n' +
           '                            <div class="item-content">\n' +
           '                                <div class="item-inner">\n' +
           '                                    <div class="item-title label">选项C</div>\n' +
           '                                    <div class="item-input">\n' +
           '                                        <textarea class="que-optionC" placeholder="选项C"></textarea>\n' +
           '                                    </div>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </li>\n' +
           '                        <li>\n' +
           '                            <div class="item-content">\n' +
           '                                <div class="item-inner">\n' +
           '                                    <div class="item-title label">选项D</div>\n' +
           '                                    <div class="item-input">\n' +
           '                                        <textarea class="que-optionD" placeholder="选项D"></textarea>\n' +
           '                                    </div>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </li>\n' +
           '                        <li>\n' +
           '                            <div class="item-content">\n' +
           '                                <div class="item-inner">\n' +
           '                                    <div class="item-title label">答案</div>\n' +
           '                                    <div class="item-input">\n' +
           '                                        <select class="que-answer-sel">\n' +
           '                                            <option data-id="1">A</option>\n' +
           '                                            <option data-id="2">B</option>\n' +
           '                                            <option data-id="3">C</option>\n' +
           '                                            <option data-id="4">D</option>\n' +
           '                                        </select>\n' +
           '                                    </div>\n' +
           '                                </div>\n' +
           '                            </div>\n' +
           '                        </li>\n' +
           '                    </div>\n' +
           '                </ul>\n' +
           '<div class="content-padded icons-demo">\n' +
           '                    <span class="icon icon-remove"></span>\n' +
           '                </div>'+
           '            </div>';
       $('#hereWeGo').append(html);
       i++;

        $('.que-type').change(function (e) {
            var type=$(this).get(0).selectedIndex;
            var target=e.currentTarget;
            var num=target.dataset.i;
            if (type==0){
                $(".not-choice"+num).attr('style','');
                $(".choice"+num).attr('style','display: none');
            }
            if (type==1){
                $(".not-choice"+num).attr('style','display: none');
                $(".choice"+num).attr('style','');
            }
        });
        $('.icon-remove').click(function () {
            $(this).parent().parent().remove();
        });
    });

    $('#submit').click(function () {
        var tempArr=$('.list-block');
        var questionList=[];
        tempArr.map(function (index,value) {
            var tempObj={};
            tempObj.queContent=$(value).find('.que-content').val();
            tempObj.queType=$(value).find('.que-type').get(0).selectedIndex+1;
            if (tempObj.queType==1){
                tempObj.queAnswer=$(value).find('.que-answer').val();
            }else if (tempObj.queType==2){
                tempObj.queOptionA=$(value).find('.que-optionA').val();
                tempObj.queOptionB=$(value).find('.que-optionB').val();
                tempObj.queOptionC=$(value).find('.que-optionC').val();
                tempObj.queOptionD=$(value).find('.que-optionD').val();
                var queAnswer=$(value).find('.que-answer-sel').get(0).selectedIndex;
                if (queAnswer==0){
                    tempObj.queAnswer="A";
                }else if (queAnswer==1){
                    tempObj.queAnswer="B";
                }else if (queAnswer==2){
                    tempObj.queAnswer="C";
                }else if (queAnswer==3){
                    tempObj.queAnswer="D";
                }
            }
            if (tempObj.queContent){
                questionList.push(tempObj);
            }
        });
        $.ajax({
            url:Url,
            type:'POST',
            data:JSON.stringify(questionList),
            contentType:'application/json',
            success:function (data) {
                if (data.success){
                    $.toast('提交成功！');
                    setTimeout(function () {
                        window.open("/epSystem/questionadmin/questionlistadmin?listId="+listId,'_self');
                    },2000);

                }else {
                    $.toast('提交失败！');
                }
            }
        });
    });

});
