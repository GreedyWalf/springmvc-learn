<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义HttpMessageConverter</title>
</head>
<script src="assets/js/jquery.js" type="text/javascript"></script>
<body>
<div>
    <div id="resp">
        <input type="button" onclick="req();" value="请求"/>
    </div>


    <script>
        function req() {
            $.ajax({
                url: "convert",
                data: "1-qinyupeng", //
                type: "POST",
                contentType: "application/x-wisely",
                success: function () {
                    $("#resp").html(data);
                }
            });
        }
    </script>
</div>
</body>
</html>
