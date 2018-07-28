$(function () {
    var listId=getQueryString('listId');
    var Url="/epSystem/questionadmin/getquestionlist?listId="+listId;
    var deleteUrl='/epSystem/questionadmin/removequestion';
    getList();
    function getList(e) {
        $.ajax({
            url:Url,
            type:"get",
            dataType:"json",
            success:function (data) {
                if (data.success){
                    handleList(data.questionList);
                    $('#add').attr('href',"/epSystem/questionadmin/batchaddquestion?listId="+listId);
                }
            }
        });

        var ListInfoUrl="/epSystem/exerciseslistadmin/getexerciseslistbyid?listId="+listId;
        $.getJSON(ListInfoUrl, function (data) {
            if (data.success) {
                var exercisesList = data.exercisesList;
                if (exercisesList.listType==1){
                    $('#back').attr('href','/epSystem/whut/index?listType=1');
                }else if(exercisesList.listType==2){
                    $('#back').attr('href','/epSystem/whut/index?listType=2');
                }
            }
        });
    }

    function handleList(data) {
        var html='';
        if (data[0]==undefined){
            html+='<div class="content-block">\n' +
                '  <p>暂时还没有</p>\n' +
                '</div>';

            $('#hereWeGo').html(html);
        }else {
            data.map(function (value,index) {
                var html1='';
                html1= '<div class="list-block">\n' +
                    '                <ul>\n' +
                    '                    <li>\n' +
                    '                        <div class="item-content">\n' +
                    '                            <div class="item-inner">\n' +
                    '                                <div class="item-title label">题目内容</div>\n' +
                    '                                <div class="item-input">\n' +
                    '                                    <textarea id="que-content" readonly>'+
                    value.queContent+
                    '</textarea>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </li>\n' +
                    '                    <li>\n' +
                    '                        <div class="item-content">\n' +
                    '                            <div class="item-inner">\n' +
                    '                                <div class="item-title label">题目类别</div>\n' +
                    '                                <div class="item-input">\n' +
                    '                                    <select id="que-type" disabled >\n';
                if (value.queType==2){
                    html1+='<option id="1">主观题</option>\n' +
                        '                                        <option id="2" selected>客观题</option>\n' +
                        '                                    </select>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>'+
                        '<li>\n' +
                        '                        <div class="item-content">\n' +
                        '                            <div class="item-inner">\n' +
                        '                                <div class="item-title label">选项A</div>\n' +
                        '                                <div class="item-input">\n' +
                        '                                    <textarea id="que-optionA" readonly>'+
                        value.queOptionA+
                        '</textarea>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>\n' +
                        '                    <li>\n' +
                        '                        <div class="item-content">\n' +
                        '                            <div class="item-inner">\n' +
                        '                                <div class="item-title label">选项B</div>\n' +
                        '                                <div class="item-input">\n' +
                        '                                    <textarea id="que-optionB" readonly>'+
                        value.queOptionB+
                        '</textarea>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>\n' +
                        '                    <li>\n' +
                        '                        <div class="item-content">\n' +
                        '                            <div class="item-inner">\n' +
                        '                                <div class="item-title label">选项C</div>\n' +
                        '                                <div class="item-input">\n' +
                        '                                    <textarea id="que-optionC" readonly>'+
                        value.queOptionC+
                        '</textarea>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>\n' +
                        '                    <li>\n' +
                        '                        <div class="item-content">\n' +
                        '                            <div class="item-inner">\n' +
                        '                                <div class="item-title label">选项D</div>\n' +
                        '                                <div class="item-input">\n' +
                        '                                    <textarea id="que-optionD" readonly>'+
                        value.queOptionD+
                        '</textarea>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>'+
                        '<li>\n' +
                        '                        <div class="item-content">\n' +
                        '                            <div class="item-inner">\n' +
                        '                                <div class="item-title label">答案</div>\n' +
                        '                                <div class="item-input">\n' +
                        '                                    <select id="que-answer" disabled >';
                    if (value.queAnswer=='B'){
                        html1+='<option data-id="1">A</option>\n' +
                            '                                        <option data-id="2" selected>B</option>\n' +
                            '                                        <option data-id="3">C</option>\n' +
                            '                                        <option data-id="4">D</option>\n' +
                            '                                    </select>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </li>';
                    }else if (value.queAnswer=='C'){
                        html1+='<option data-id="1">A</option>\n' +
                            '                                        <option data-id="2">B</option>\n' +
                            '                                        <option data-id="3" selected>C</option>\n' +
                            '                                        <option data-id="4">D</option>\n' +
                            '                                    </select>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </li>';
                    }else if (value.queAnswer=='D'){
                        html1+='<option data-id="1">A</option>\n' +
                            '                                        <option data-id="2">B</option>\n' +
                            '                                        <option data-id="3">C</option>\n' +
                            '                                        <option data-id="4" selected>D</option>\n' +
                            '                                    </select>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </li>';
                    }else{
                        html1+='<option data-id="1" selected>A</option>\n' +
                            '                                        <option data-id="2">B</option>\n' +
                            '                                        <option data-id="3">C</option>\n' +
                            '                                        <option data-id="4">D</option>\n' +
                            '                                    </select>\n' +
                            '                                </div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </li>';
                    }
                }else {
                    html1+='<option id="1" selected>主观题</option>\n' +
                        '                                        <option id="2">客观题</option>\n' +
                        '                                    </select>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>'+
                        '<li>\n' +
                        '                        <div class="item-content">\n' +
                        '                            <div class="item-inner">\n' +
                        '                                <div class="item-title label">答案</div>\n' +
                        '                                <div class="item-input">\n' +
                        '                                    <textarea id="que-answer" readonly>'+
                        value.queAnswer+
                        '</textarea>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </li>\n';
                }
                html1+='                    <li>\n' +
                    '                        <div class="row">\n' +
                    '                            <div class="col-50"><a href="/epSystem/questionadmin/modifyquestion?queId='+
                    value.queId+
                    '" class="button button-big button-fill button-warning external" id="edit">编辑</a></div>\n' +
                    '                            <div class="col-50"><a href="#" class="button button-big button-fill button-danger delete" data-id="'+
                    value.queId+
                    '">删除</a></div>\n' +
                    '                        </div>\n' +
                    '                    </li>\n' +
                    '                </ul>\n' +
                    '            </div>';
                html+=html1;
            });

            $('#hereWeGo').html(html);
        }
    }

    $('#hereWeGo').on('click','.delete',function (e) {
        var target=e.currentTarget;
        $.confirm('确定么？',function () {
            $.ajax({
                url:deleteUrl,
                type:'POST',
                data:{queId:target.dataset.id},
                dataType:'json',
                success:function (data) {
                    if (data.success){
                        $.toast('删除成功！');
                        getList();
                    }else {
                        $.toast('删除失败！');
                    }
                }
            });
        });
    });

});