$(function () {
    var listId=getQueryString('listId');
    var Url="/epSystem/questionadmin/getquestionlist?listId="+listId;
    getList();
    function getList(e) {
        $.ajax({
            url:Url,
            type:"get",
            dataType:"json",
            success:function (data) {
                if (data.success){
                    handleList(data.questionList);
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
                html+='<div class="card">\n' +
                    '      <div class="card-content">\n' +
                    '        <div class="card-content-inner" id="newCard">';
                var html2='';
                html2+=(index+1)+
                    '. '+
                    toBr(value.queContent)+
                    '<p></p>';
                if (value.queType==2){
                    html2+='<div class="list-block media-list" id="'+
                        value.queId+
                        '">\n' +
                        '                            <ul>\n' +
                        '                                <li>\n' +
                        '                                    <label class="label-checkbox item-content">\n' +
                        '                                        <input type="radio" name="my-radio">\n' +
                        '                                        <div class="item-media"><i class="icon icon-form-checkbox"></i>A.</div>\n' +
                        '                                        <div class="item-inner">\n' +
                        '                                            <div class="item-title-row">\n' +
                        '                                                <div class="item-title">'+
                        toBr(value.queOptionA)+
                        '</div>\n' +
                        '                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </label>\n' +
                        '                                </li>\n' +
                        '                                <li>\n' +
                        '                                    <label class="label-checkbox item-content">\n' +
                        '                                        <input type="radio" name="my-radio">\n' +
                        '                                        <div class="item-media"><i class="icon icon-form-checkbox"></i>B.</div>\n' +
                        '                                        <div class="item-inner">\n' +
                        '                                            <div class="item-title-row">\n' +
                        '                                                <div class="item-title">'+
                        toBr(value.queOptionB)+
                        '</div>\n' +
                        '                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </label>\n' +
                        '                                </li>\n' +
                        '                                <li>\n' +
                        '                                    <label class="label-checkbox item-content">\n' +
                        '                                        <input type="radio" name="my-radio">\n' +
                        '                                        <div class="item-media"><i class="icon icon-form-checkbox"></i>C.</div>\n' +
                        '                                        <div class="item-inner">\n' +
                        '                                            <div class="item-title-row">\n' +
                        '                                                <div class="item-title">'+
                        toBr(value.queOptionC)+
                        '</div>\n' +
                        '                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </label>\n' +
                        '                                </li>\n' +
                        '                                <li>\n' +
                        '                                    <label class="label-checkbox item-content">\n' +
                        '                                        <input type="radio" name="my-radio">\n' +
                        '                                        <div class="item-media"><i class="icon icon-form-checkbox"></i>D.</div>\n' +
                        '                                        <div class="item-inner">\n' +
                        '                                            <div class="item-title-row">\n' +
                        '                                                <div class="item-title">'+
                        toBr(value.queOptionD)+
                        '</div>\n' +
                        '                                            </div>\n' +
                        '                                        </div>\n' +
                        '                                    </label>\n' +
                        '                                </li>\n' +
                        '                            </ul>\n' +
                        '                        </div>';
                }
                else {
                    html2+='<div class="list-block">\n' +
                        '                            <div class="item-content">\n' +
                        '                                <div class="item-inner">\n' +
                        '                                    <div class="item-input-lr"></div>\n' +
                        '                                    <div class="item-input">\n' +
                        '                                        <textarea placeholder="请输入你的答案" id="'+
                        value.queId+
                        '"></textarea>\n' +
                        '                                    </div>\n' +
                        '                                    <div class="item-input-lr"></div>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>';
                }
                html+=html2+
                    '</div>\n' +
                    '      </div>\n' +
                    '    </div>';

            });

            $('#hereWeGo').html(html);
        }
    }


});