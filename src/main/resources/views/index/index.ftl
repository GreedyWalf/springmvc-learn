<#assign ctx=Request.contextPath/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>

    <#-- 加载layui样式-->
    <link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css"  media="all">
</head>
<body>

<form class="layui-form" action="">

    <div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
            <select name="city" lay-verify="required">
                <option value=""></option>
                <option value="0">北京</option>
                <option value="1">上海</option>
                <option value="2">广州</option>
                <option value="3">深圳</option>
                <option value="4">杭州</option>
            </select>
        </div>
    </div>
</form>

<#-- 引入layui全局js -->
<script src="${ctx}/assets/layui/layui.js"></script>

<script>
    /* 直接使用use方式 */
    // layui.use('form', function(){
    //     var form = layui.form;
    //
    //     //监听提交
    //     form.on('submit(formDemo)', function(data){
    //         layer.msg(JSON.stringify(data.field));
    //         return false;
    //     });
    //
    //     form.render();
    // });


    /* 定义加载自定义模块方式（自定义js文件） */
    layui.config({
        base:'${ctx}/assets/js/user/'
    }).use('index');
</script>

</body>
</html>