$(function () {
    var listType=getQueryString('listType');

    function handleList(data,typeId) {
        var html='';
        if (data[0]==undefined){
            html+='<div class="content-block">\n' +
                '  <p>暂时还没有</p>\n' +
                '</div>';
            html+='<div class="content-block">\n' +
                '    <p><a href="/epSystem/exerciseslistadmin/exerciseslistoperation?typeId='+
                typeId+
                '" class="button button-big external">+  创建 </a></p>\n' +
                '  </div>';
            $('#hereWeGo').html(html);
        }else {
            var htmltem='<div class="list-block media-list inset">\n' +
                '        <ul id="hereNew"></ul>\n' +
                '      </div>';
            data.map(function (value, index) {
                html+='<li>\n' +
                    '          <a href="/epSystem/questionadmin/questionlist?listId='+
                    value.listId+
                    '" class="item-link item-content external">\n' +
                    '            <div class="item-inner">\n' +
                    '              <div class="item-title-row">\n' +
                    '                <div class="item-title">\n' +
                    value.listName +
                    '                </div>\n' +
                    '              </div>\n' +
                    '              <div class="item-subtitle">\n' +
                    value.listDesc +
                    '              </div>\n' +
                    '            </div>\n' +

                    '          </a>\n' +
                    '<div class="setting-icon" listid="'+
                    value.listId+
                    '" typeid="'+
                    typeId+
                    '"><a class="icon icon-settings"></a></div>'+
                    '        </li>';
            });
            $('#hereWeGo').html(htmltem);
            $('#hereNew').html(html);
        }
    }

    function handleList2(data,typeId) {
        var html='';
        if (data[0]==undefined) {
            html += '<div class="content-block">\n' +
                '  <p>暂时还没有</p>\n' +
                '</div>';
        }else {
            data.map(function (value, index2) {
                html+='<div class="card demo-card-header-pic">\n' +
                    '      <div valign="bottom" class="card-header color-white no-border">'+
                    value.title+
                    '</div>\n' +
                    '      <div class="card-content">\n' +
                    '        <div class="card-content-inner">\n' +
                    '          <p class="color-gray">'+
                    timestampToTime(value.lastEditTime)+
                    '</p><p></p>\n' +
                    toBr(value.content)+
                    '        </div>\n' +
                    '      </div>\n' +
                    '      <div class="card-footer">\n' +
                    '        <a href="/epSystem/announceadmin/announceoperation?announceId='+
                    value.announceId+
                    '" class="link external">编辑</a>\n';
                if (typeId==1){
                    html+='        <a href="#" class="link delete" data-id="'+
                        value.announceId+
                        '">删除</a>\n' ;
                }
                html+='      </div>\n' +
                    '</div>';
            });
        }
        if(typeId==1){
            html += '<div class="content-block new">\n' +
                '    <p><a href="/epSystem/announceadmin/announceoperation" class="button button-big external">+  创建 </a></p>\n' +
                '  </div>';
        }
        $('#hereWeGo').html(html);
    }

    $(document).ready(function () {
        //点击事件
        $(".tab-item").click(function () {
            // 取消当前激活状态
            $(".active").removeClass("active");
            // 添加新状态
            $(this).addClass("active");
        });

        $('#announce').click(function () {
            getList();
            function getList(e) {
                $.ajax({
                    url:"/epSystem/announceadmin/getannouncelist?type=1",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        if (data.success){
                            handleList2(data.announceList,1);
                        }
                    }
                });
            }
        });

        $('#score').click(function () {
            getList();
            function getList(e) {
                $.ajax({
                    url:"/epSystem/announceadmin/getannouncelist?type=2",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        if (data.success){
                            handleList2(data.announceList,2);
                        }
                    }
                });
            }
        });

        $('#testList').click(function () {
            getlist();
            function getlist(e) {
                $.ajax({
                    url:"/epSystem/exerciseslistadmin/getexerciseslist?listType=1",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        if (data.success){
                            handleList(data.exercisesLists,1);
                        }
                    }
                });
            }
        });

        $('#examList').click(function () {
            getlist();
            function getlist(e) {
                $.ajax({
                    url:"/epSystem/exerciseslistadmin/getexerciseslist?listType=2",
                    type:"get",
                    dataType:"json",
                    success:function (data) {
                        if (data.success){
                            handleList(data.exercisesLists,2);
                        }
                    }
                });
            }
        });

        if (listType==1){
            $(".tab-item")[2].click();
        }
        else if (listType==2){
            $(".tab-item")[3].click();
        }
        else if (listType==3){
            $(".tab-item")[1].click();
        }
        else {
            // 自动点击第一个菜单
            $(".tab-item")[0].click();
        }

        $.ajax({
            url:"/epSystem/personinfoadmin/getuserinfo",
            type:"get",
            dataType:"json",
            success:function (data) {
                if (data.success){
                    var html='';
                    html+='<p>欢迎你，'+data.user.name+'</p>'+'<p><a href="/epSystem/personinfoadmin/logout" class="external">退出登录</a></p>';
                    $('#userinfo').html(html);
                }
            }
        })
    });

    $(document).on('click','.delete',function () {
        var announceId=$(this).attr('data-id');
        function getList(e) {
            $.ajax({
                url:"/epSystem/announceadmin/getannouncelist?type=1",
                type:"get",
                dataType:"json",
                success:function (data) {
                    if (data.success){
                        handleList2(data.announceList,1);
                    }
                }
            });
        }
        $.confirm('确定删除？',function () {
            $.ajax({
                url:'/epSystem/announceadmin/removeannounce',
                type:'POST',
                data:{announceId:announceId},
                datatype:'json',
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

    $(document).on('click','.setting-icon', function () {
        var listId=$(this).attr('listid');
        var typeId=$(this).attr('typeid');
        function getnewlist(e) {
            $.ajax({
                url:"/epSystem/exerciseslistadmin/getexerciseslist?listType="+typeId,
                type:"get",
                dataType:"json",
                success:function (data) {
                    if (data.success){
                        handleList(data.exercisesLists,typeId);
                    }
                }
            });
        }
        var buttons1 = [
            {
                text: '请选择',
                label: true
            },
            {
                text: '新增',
                onClick: function() {
                    window.open('/epSystem/exerciseslistadmin/exerciseslistoperation?typeId='+typeId,'_self');
                }
            },
            {
                text: '修改',
                onClick: function() {
                    window.open("/epSystem/exerciseslistadmin/exerciseslistoperation?listId="+listId,'_self');
                }
            },
            {
                text: '题目管理',
                onClick: function() {
                    window.open("/epSystem/questionadmin/questionlistadmin?listId="+listId,'_self');
                }
            },
            {
                text: '删除',
                bold: true,
                color: 'danger',
                onClick: function() {
                    $.confirm('确定删除？',function () {
                        $.ajax({
                            url:'/epSystem/exerciseslistadmin/removeexercise',
                            type:'POST',
                            data:{listId:listId},
                            datatype:'json',
                            success:function (data) {
                                if (data.success){
                                    $.toast('删除成功！');
                                    getnewlist();
                                }else {
                                    $.toast('删除失败！');
                                }
                            }
                        });
                    });
                }
            }
        ];
        var buttons2 = [
            {
                text: '取消',
                bg: 'danger'
            }
        ];
        var groups = [buttons1, buttons2];
        $.actions(groups);
    });

});
