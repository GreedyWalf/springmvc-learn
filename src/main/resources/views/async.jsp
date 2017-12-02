<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Servlet async support</title>
</head>
<script type="text/javascript" src="assets/js/jquery.js"></script>
<body>
<div>
    <font color="blue">测试服务端推送数据功能，开始servlet3.0支持的异步方式</font>
   <div id="showSyncData">

   </div>
</div>

<script type="text/javascript">
    //页面打开后向后台发送请求
    deferred();

    function deferred() {
        $.get('defer', function (data) {
            //在页面控制台输出服务端推送的数据
            console.log(data);
            $("#showSyncData").append("<p>" + data + "</p>");
            //一次请求完成后再向后台发送请求
            deferred();
        })
    }
</script>
</body>
</html>
