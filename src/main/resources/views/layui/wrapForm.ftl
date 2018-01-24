<#assign ctx=Request.contextPath/>

<#--<html>-->
<#--<head>-->
    <#--<title>layui</title>-->
    <#--<link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css"  media="all">-->
<#--</head>-->
<#--<body>-->

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

<script src="${ctx}/assets/layui/layui.js" charset="utf-8"></script>

<script>
    layui.config({
        dir: '${ctx}/assets/layui/'
    });

    layui.use(['form','layer'], function () {
        var form = layui.layer
                ,form = layui.form;
    });
</script>

<#--</body>-->
<#--</html>-->



