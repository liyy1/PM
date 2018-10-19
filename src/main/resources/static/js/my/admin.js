function req(url, data, callback) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function(data){
            if(data.code==0){
                callback;
            } else if(data.code==1){
                layer.msg(data.message, {icon: 5});
            } else if(data.code==2){
                if(url.indexOf("exit") > -1) callback();
                else{
                    layer.msg('登录超时，请重新登录！', {icon: 5,time: 1000},function(){
                        location.href = '/toLogin';
                    });
                }
            } else if(data.code==3){
                layer.msg('您没有权限进行该操作！', {icon: 5});
            }
        }, 
        error:function(e){
            if(url.indexOf("exit") > -1) callback();
            else layer.msg('服务器异常！', {icon: 5});
        }
    });
}

function exit() {
    req("/exit",null,function () {
        layer.msg('退出成功', {icon: 1,time: 1000}, function(){
            location.href = '/toLogin';
        });
    });
}