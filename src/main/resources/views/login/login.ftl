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
    <form id="loginForm" action="login/ajaxLogin">
        用户名：<input type="text" name="userName"/>　<br>
        密码：<input type="password" name="password"/> <br>
        <input type="checkbox" id="rememberMe" name="rememberMe" value="true"/>记住我 <br>
        <input type="button" value="登录" id="btnLogin"/>  <a href="${ctx!}/login/register">注册</a>
    </form>
</div>
</body>


<script>
    var base64 = new Base64();

    //页面加载的时候，在cookie中获取是否已经保存了用户的登录信息
    $(function () {
        var $userName = $("input[name='userName']");
        $userName.focus();
        if (getCookie("rememberMe") === "true") {
            $("#rememberMe").prop("checked", true);
            $("input[name='userName']").val(getCookie("userName"));
            $("input[name='password']").val(base64.decode(getCookie("password")));
        }
    });

    //登录
    $("#btnLogin").click(function () {
        ajaxLogin();
    });

    //注册
    $("#btnRegister").click(function(){
        window.location.href="${ctx}/login/register";
    });


    var ajaxLogin = function () {
        var $form = $("#loginForm");
        var oldPwd = $form.find("input[name='password']").val();
        var newPwd = base64.encode(oldPwd);

        if ($("#rememberMe").is(":checked")) {
            setCookie("rememberMe", "true", 60);
            setCookie("userName", $("input[name='userName']").val(), 60);
            setCookie("password", newPwd, 60);
        } else {
            setCookie("rememberMe", "false", 0);
            setCookie("userName", $("input[name='userName']").val(), 0);
            setCookie("password", newPwd, 0);
        }

        $.ajax({
            url: "${ctx}/login/ajaxLogin",
            dataType: "json",
            type: "post",
            data: $form.serialize(),
            success: function (data) {
                console.log(data);
                if (data.status === "SUCCESS") {
                    alert(data.message);
                    window.location.href = "${ctx}/login/index";
                } else {
                    alert(data.message || "登录失败哦！");
                }
            }
        });
    };

    function Base64() {
        // private property
        _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        // public method for encoding
        this.encode = function (input) {
            var output = "";
            var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
            var i = 0;
            input = _utf8_encode(input);
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output +
                        _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                        _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
            }
            return output;
        };
        // public method for decoding
        this.decode = function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = _keyStr.indexOf(input.charAt(i++));
                enc2 = _keyStr.indexOf(input.charAt(i++));
                enc3 = _keyStr.indexOf(input.charAt(i++));
                enc4 = _keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 !== 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 !== 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = _utf8_decode(output);
            return output;
        };
        // private method for UTF-8 encoding
        _utf8_encode = function (string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }
            }
            return utftext;
        };
        // private method for UTF-8 decoding
        _utf8_decode = function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while (i < utftext.length) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    }

</script>
</html>


