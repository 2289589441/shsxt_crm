function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

/*退出操作*/
function logout() {
    $.messager.confirm('来自小可爱的提醒','您确认想要退出登录？',function(r){
        if (r){
            $.removeCookie("userIdStr");
            window.location.href=ctx + "/index"
        }
    });

}

/*修改密码*/
function openPasswordModifyDialog() {
    $.messager.confirm('来自小可爱的提醒','您确认想要修改密码？',function(r){
        if (r){
            $('#dlg').dialog('open');
        }
    });
}
/*关闭修改框*/
function closePasswordModifyDialog() {
    $('#dlg').dialog('close');

}
/*修改密码 保存*/

function modifyPassword() {
    $('#fm').form('submit', {
            url:ctx + "/user/upDateUserPwd",
        onSubmit: function(){
            return $(this).form('validate');
    },
    success:function(data){
        data = JSON.parse(data);
        if(data.code===200){
            $.messager.alert('来自小可爱',data.msg,'info',function () {
                $.removeCookie("userIdStr");
                window.location.href = ctx + '/index';
            });
        }else{
            $.messager.alert('来自小可爱',data.msg,'error');
        }

    }
});
}