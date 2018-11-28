<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!doctype html>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订餐系统V1.0</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script src="http://g.alicdn.com/dingding/dinglogin/0.0.5/ddLogin.js"></script>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">订餐系统-钉钉扫码登录</div>
    <div id="darkbannerwrap"></div>
    <div id="login_container"></div>
    <%--<form method="post" class="layui-form" >--%>
    <%--<input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >--%>
    <%--<hr class="hr15">--%>
    <%--<input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">--%>
    <%--<hr class="hr15">--%>
    <%--<input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">--%>
    <%--<hr class="hr20" >--%>
    <%--</form>--%>
</div>


<script>
    //2.2.扫码触发事件
    //https%3a%2f%2foapi.dingtalk.com%2fconnect%2foauth2%2fsns_authorize%3fappid%3ddingoav8ulanmhofptrhio%26response_type%3dcode%26scope%3dsnsapi_login%26state%3dSTATE%26redirect_uri%3dhttp%3a%2f%2f192.168.211.26%3a8080%2fServletRequst
    //https%3a%2f%2foapi.dingtalk.com%2fconnect%2foauth2%2fsns_authorize%3fappid%3ddingoav8ulanmhofptrhio%26response_type%3dcode%26scope%3dsnsapi_login%26state%3dSTATE%26redirect_uri%3dhttp%3a%2f%2f192.168.211.26%3a8080%2findex.jsp
    var obj = DDLogin({
        id: "login_container",
        goto: "https%3a%2f%2foapi.dingtalk.com%2fconnect%2foauth2%2fsns_authorize%3fappid%3ddingoav8ulanmhofptrhio%26response_type%3dcode%26scope%3dsnsapi_login%26state%3dSTATE%26redirect_uri%3dhttp%3a%2f%2f192.168.211.26%3a8080%2fjavawebservlet%2fServletRequst",
//        goto: "https%3a%2f%2foapi.dingtalk.com%2fconnect%2foauth2%2fsns_authorize%3fappid%3ddingoav8ulanmhofptrhio%26response_type%3dcode%26scope%3dsnsapi_login%26state%3dSTATE%26redirect_uri%3dhttp%3a%2f%2fjsdingcai.drcuiyutao.com%2fjavawebservlet%2fServletRequst",
        style: "border:none;background-color:#FFFFFF;",
        width: "365",
        height: "400"
    });
    var hanndleMessage = function (event) {
        var origin = event.origin;
        console.log("origin", event.origin);
        if (origin == "https://login.dingtalk.com") { //判断是否来自ddLogin扫码事件。
            var loginTmpCode = event.data; //拿到loginTmpCode后就可以在这里构造跳转链接进行跳转了
            // DingLogin.goDingLogin(loginTmpCode);
            console.log("loginTmpCode", loginTmpCode);
//            window.location.href = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=dingoav8ulanmhofptrhio&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=http://192.168.211.26:8080/javawebservlet/ServletRequst&loginTmpCode=" + loginTmpCode
            window.location.href = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=dingoav8ulanmhofptrhio&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=http://jsdingcai.drcuiyutao.com/javawebservlet/ServletRequst&loginTmpCode=" + loginTmpCode
            //request.post("http://192.168.211.26:8080/ServletRequst?loginTmpCode="+loginTmpCode);
            /*DingLogin dl=new DingLogin();
             dl.goDingLogin(loginTmpCode);*/
        }
    };
    if (typeof window.addEventListener != 'undefined') {
        window.addEventListener('message', hanndleMessage, false);

    } else if (typeof window.attachEvent != 'undefined') {
        window.attachEvent('onmessage', hanndleMessage);

    }


    //    //声明钉钉二维码参数包括【扫描授权id、企业id】
    //    var ding = {"appid": "", "agentid": "", "corpid": "", "uri": "", "redirect_uri": "", "code": "", "accesstoken": ""};
    //    //1.5.1.设置扫描授权id
    //    ding.appid = "dingoav8ulanmhofptrhio";
    //    //1.5.2.设置重定向地址
    //    ding.redirect_uri = "http://192.168.211.26:8080/index.jsp";
    //    //1.5.3.扫码触发跳转钉钉接口地址
    //    ding.uri = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=" + ding.appid + "&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=" + ding.redirect_uri;
    //
    //
    //    //2.2.扫码触发事件
    //    var obj = DDLogin({
    //        id: "login_container",
    //        goto: encodeURIComponent(ding.uri),
    //        style: "border:none;background-color:#FFFFFF;",
    //        width: "240",
    //        height: "300",
    //    });
    //
    //    //2.3.获取code
    //    var getcode = function (event) {
    //        var origin = event.origin;
    //        console.log("origin", event.origin);
    //        if (origin == "https://login.dingtalk.com") { //判断是否来自ddLogin扫码事件。
    //            var loginTmpCode = event.data; //拿到loginTmpCode后就可以在这里构造跳转链接进行跳转了
    //            ding.code = loginTmpCode;
    //            console.log("获取到code", loginTmpCode);
    //            window.parent.postMessage(loginTmpCode, '*');
    //            //1.跳转到主页面，微应用自身截取code比对用户信息
    //            var redirect_uri_check = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=dingoazjmclpvolnxq3wba&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=http://&loginTmpCode=";
    //            window.location.href = redirect_uri_check + loginTmpCode;
    //        }
    //        ;
    //    };
    //    //2.4.钉钉扫码监听
    //    if (typeof window.addEventListener != 'undefined') {
    //        window.addEventListener('message', getcode, false);
    //    } else if (typeof window.attachEvent != 'undefined') {
    //        window.attachEvent('onmessage', getcode);
    //    }
    //    ;
    //    //2.5.这儿主要是获取code，返回钉钉id，进行后端的钉钉id比对,api接口见下
    //    var url = window.location.search.split("&");
    //    var code = url[0].substring(url[0].lastIndexOf("=") + 1);
    //    //alert(code);
    //    if (code != "") {
    //        var uri = "https://oapi.dingtalk.com/sns/gettoken?appid=dingoav8ulanmhofptrhio&appsecret=r2fbezOVDZBdRxdW5mylJpz5CnWb1SZL40lFjgoi4e3K-TrMDbuTsWEwV7Rz879w"
    //        $http({
    //            url: uri,
    //            method: "GET",
    //            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    //        }).success(function (data) {
    //            console.log(data)
    //            if (data != "") {
    //                //$window.localStorage["user"] = angular.toJson(data[0]);
    //                window.location.href = "http://192.168.211.26:8080/ServletRequst?" + code;
    //            } else {
    //                alert(data[0].msg);
    //                $scope.user.info = "";
    //                window.location.href = "/";
    //            }
    //        });
    //    };

    //        $(function  () {
    //            layui.use('form', function(){
    //              var form = layui.form;
    //              // layer.msg('玩命卖萌中', function(){
    //              //   //关闭后的操作
    //              //   });
    //              //监听提交
    //              form.on('submit(login)', function(data){
    //                // alert(888)
    //                layer.msg(JSON.stringify(data.field),function(){
    //                    location.href='index.jsp'
    //                });
    //                return false;
    //              });
    //            });
    //        })


</script>

</body>
</html>