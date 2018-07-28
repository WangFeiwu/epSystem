$(function () {
   var queId=getQueryString('queId');
   var infoUrl="/epSystem/questionadmin/getquestionbyid?queId="+queId;
   var editUrl='/epSystem/questionadmin/modifyquestion';
   getQuestionInfo();
   var listId;
   function getQuestionInfo() {
       $.getJSON(infoUrl,function (data) {
          if (data.success){
              var question=data.question;
              var html='';
              html+='<div class="list-block">\n' +
                  '                <ul>\n' +
                  '                    <li>\n' +
                  '                        <div class="item-content">\n' +
                  '                            <div class="item-inner">\n' +
                  '                                <div class="item-title label">题目内容</div>\n' +
                  '                                <div class="item-input">\n' +
                  '                                    <textarea id="que-content" placeholder="题目内容">'+
                  question.queContent+
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
              if (question.queType==2){
                  html+='<option id="1">主观题</option>\n' +
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
                      '                                    <textarea id="que-optionA" placeholder="选项A">'+
                      question.queOptionA+
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
                      '                                    <textarea id="que-optionB" placeholder="选项B">'+
                      question.queOptionB+
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
                      '                                    <textarea id="que-optionC" placeholder="选项C">'+
                      question.queOptionC+
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
                      '                                    <textarea id="que-optionD" placeholder="选项D">'+
                      question.queOptionD+
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
                      '                                    <select id="que-answer">';
                  if (question.queAnswer=='B'){
                      html+='<option data-id="1">A</option>\n' +
                          '                                        <option data-id="2" selected>B</option>\n' +
                          '                                        <option data-id="3">C</option>\n' +
                          '                                        <option data-id="4">D</option>\n' +
                          '                                    </select>\n' +
                          '                                </div>\n' +
                          '                            </div>\n' +
                          '                        </div>\n' +
                          '                    </li>';
                  }else if (question.queAnswer=='C'){
                      html+='<option data-id="1">A</option>\n' +
                          '                                        <option data-id="2">B</option>\n' +
                          '                                        <option data-id="3" selected>C</option>\n' +
                          '                                        <option data-id="4">D</option>\n' +
                          '                                    </select>\n' +
                          '                                </div>\n' +
                          '                            </div>\n' +
                          '                        </div>\n' +
                          '                    </li>';
                  }else if (question.queAnswer=='D'){
                      html+='<option data-id="1">A</option>\n' +
                          '                                        <option data-id="2">B</option>\n' +
                          '                                        <option data-id="3">C</option>\n' +
                          '                                        <option data-id="4" selected>D</option>\n' +
                          '                                    </select>\n' +
                          '                                </div>\n' +
                          '                            </div>\n' +
                          '                        </div>\n' +
                          '                    </li>';
                  }else{
                      html+='<option data-id="1" selected>A</option>\n' +
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
                  html+='<option id="1" selected>主观题</option>\n' +
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
                      '                                    <textarea id="que-answer" placeholder="答案">'+
                      question.queAnswer+
                      '</textarea>\n' +
                      '                                </div>\n' +
                      '                            </div>\n' +
                      '                        </div>\n' +
                      '                    </li>\n';
              }
              html+='                </ul>\n' +
                  '            </div>';
              $('#hereWeGo').html(html);
              $('#back').attr('href',"/epSystem/questionadmin/questionlistadmin?listId="+question.listId);
              listId=question.listId;
          }
       });
   }

   $('#submit').click(function () {
       var question={};
       question.queId=queId;
       question.queContent=$('#que-content').val();
       var queType=$('#que-type').get(0).selectedIndex;
       if (queType==1){
           question.queOptionA=$('#que-optionA').val();
           question.queOptionB=$('#que-optionB').val();
           question.queOptionC=$('#que-optionC').val();
           question.queOptionD=$('#que-optionD').val();
           var queAnswer=$('#que-answer').get(0).selectedIndex;
           if (queAnswer==0)
               question.queAnswer='A';
           else if (queAnswer==1)
               question.queAnswer='B';
           else if (queAnswer==2)
               question.queAnswer='C';
           else if (queAnswer==3)
               question.queAnswer='D';
       }else {
           question.queAnswer=$('#que-answer').val();
       }
       var formData=new FormData();
       formData.append('questionStr',JSON.stringify(question));
       $.ajax({
           url:editUrl,
           type:'POST',
           data:formData,
           contentType:false,
           processData:false,
           cache:false,
           success:function (data) {
               if (data.success){
                   $.toast('提交成功！');
                   setTimeout(function () {
                       window.open("/epSystem/questionadmin/questionlistadmin?listId="+listId,'_self');
                   },2000);
               }else {
                   $.toast('提交失败！'+data.errMsg);
               }
           }
       });
   });
});