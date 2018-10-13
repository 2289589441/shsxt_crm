function login() {
    var userName = $("#username").val();
    var userPwd = $("#password").val();

    /*1 判断参数非空*/
    if(isEmpty(userName)){
        alert("用户名为空");
        return;
    }
    if(isEmpty(userPwd)){
        alert("密码为空");
        return;
    }

    $.ajax({
        url:ctx + "/user/login",
        type:"post",
        data:{
            userName:userName,
            userPwd:userPwd
        },
        success:function (data) {
            if(data.code===200){
                alert("登录成功");
                $.cookie("userIdStr",data.result.userIdStr);
                window.location.href= ctx + "/main";
            }else {
                alert(data.msg)
            }
        }
    })
}