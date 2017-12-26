var messageDialog = {
    message: function (str, setting) {
        var cont = '<strong><i class="ace-icon fa fa-times"></i>' + setting.key + '</strong>' + setting.comment;
        $.extend(str, setting);
        $(str).html(cont).show(100).delay(3000).hide(100);
    }
};

//设置cookie值
var setCookie = function (name, value, seconds) {
    //seconds有值就赋值，没有为0
    seconds = seconds || 0;
    var expires = "";
    if (seconds !== 0) {
        var date = new Date();
        date.setTime(date.getTime() + (seconds * 10000));
        expires = "; expires=" + date.toUTCString();
    }

    //转码并赋值
    document.cookie = name + "=" + encodeURI(value) + expires + "; path=/";
};

//获取存储在客户端的cookie信息（登录信息，包括后台代码返回session_id值）
var getCookie = function (cname) {
    var name = cname + "=";
    var ca = document.cookie.split(";");
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
};
