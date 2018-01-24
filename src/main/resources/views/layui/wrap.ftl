<#assign ctx=Request.contextPath/>

<html>
<head>
<#--<meta charset="utf-8">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
<#--<meta name="renderer" content="webkit">-->
<#--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<#--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">-->
<#--<meta name="apple-mobile-web-app-status-bar-style" content="black">-->
<#--<meta name="apple-mobile-web-app-capable" content="yes">-->
<#--<meta name="format-detection" content="telephone=no">-->

<#-- 加载layui样式-->
    <link rel="stylesheet" href="${ctx}/assets/layui/css/layui.css" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">测试layui</a>
                    <dl class="layui-nav-child">
                        <dd data-type="wrapForm"><a href="javascript:;">表单</a></dd>
                        <dd data-type="wrapMenu"><a href="javascript:;">菜单</a></dd>
                        <dd data-type="more"><a href="javascript:;">待续</a></dd>
                        <dd><a href="http://www.layui.com/demo/">layui实例demo</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="http://www.layui.com/demo/">layui实例demo</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            内容主体区域

            <br><br>
            <blockquote class="layui-elem-quote">
                layui 之所以赢得如此多人的青睐，更多是在于它“前后台系统通吃”的能力。既可编织出绚丽的前台页面，又可满足繁杂的后台功能需求。
                <br>layui 后台布局， 致力于让每一位开发者都能轻松搭建自己的后台模板。
            </blockquote>

            <a href="/doc/element/layout.html#admin" target="_blank" class="layui-btn layui-btn-big">获取该布局代码</a>

            <br><br><br><br>
            下面是充数内容，为的是出现滚动条<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>

<script src="${ctx}/assets/layui/layui.js"></script>
<script>

    layui.config({
        dir: '${ctx}/assets/layui/'
    });


    //JavaScript代码区域
    layui.use(['element', 'layer', 'form', 'layedit', 'laydate'], function () {
        var element = layui.element
                , layer = layui.layer
                , layedit = layui.layedit
                , form = layui.form
                , laydate = layui.laydate
                , $ = layui.$; //重点处，引入jquery

        <#--//监听导航点击（！！遇到的问题，点击触发load页面一次之后，再次点击监听不到事件）-->
        <#--element.on('nav(test)', function (elem) {-->
            <#--debugger-->
            <#--console.log(elem);-->
            <#--var dataType = elem.attr('data-type');-->
            <#--if (dataType && dataType !== 'mores') {-->
                <#--$(".layui-body").load("${ctx}/lay/" + dataType);-->
            <#--} else {-->
                <#--layer.msg('稍后更精彩哈！！');-->
            <#--}-->
        <#--});-->

        //实践证明还是jquery绑定点击事件好用
        $("ul[lay-filter='test'").find("dd").click(function (e) {
            alert($(this).text());
            var dataType = $(this).attr('data-type');
            if (dataType && dataType !== 'mores') {
                $(".layui-body").load("${ctx}/lay/" + dataType);
            } else {
                layer.msg('稍后更精彩哈！！');
            }
        });
    });
</script>
</body>
</html>
