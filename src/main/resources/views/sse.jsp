<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSE实例</title>
</head>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<body>
<div id="msgFromPush">


</div>

<script type="text/javascript">
    //EventSource对象只有新版的浏览器才有(Chrome、FireFox)，EventSource是SSE的客户端
    if(!!window.EventSource){
        //访问后台控制器的push方法
        var source = new EventSource('push');
        s = '';
        //添加SSE的客户端监听
        source.addEventListener('message', function (e) {
            s += e.data + "<br/>";
            $("#msgFromPush").html(s);
        });

        source.addEventListener('open', function (e) {
            console.log("连接打开..");
        }, false);

        source.addEventListener('error', function (e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log("连接关闭。");
            } else {
                console.log(e.readyState);
            }
        }, false);
    }else{
        console.log("你的浏览器不支持SSE。。");
    }
</script>
</body>
</html>
