<#assign ctx=Request.contextPath/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>登录页面</title>
</head>

<#-- 引入js -->
<script type="text/javascript" src="${ctx}/assets/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/common.js"></script>

<body>

<div>
    <form id="registerForm" action="login/ajaxRegister">
        用户名：<input type="text" name="userName" placeholder="请输入用户名"/>　<br>
        密码：<input type="password" name="password" placeholder="请输入密码"/> <br>
        <input type="button" value="注册" id="btnRegister"/>
    </form>
</div>
</body>


<script>
    //页面加载的时候，在cookie中获取是否已经保存了用户的登录信息
    $(function () {

        //注册
        $("#btnRegister").click(function () {
            ajaxRegister();
        });

        var ajaxRegister = function () {
            var $registerForm = $("#registerForm");
            $.ajax({
                url: "${ctx}/login/ajaxRegister",
                dataType: "json",
                type: "post",
                data: $registerForm.serialize(),
                success: function (data) {
                    if (data.status === "SUCCESS") {
                        alert(data.message || "注册成功");
                    } else {
                        alert(data.message || "好像出现问题了，失败咯！");
                    }
                }
            });
        };
    });
</script>
</html>


