<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="search.SearchDao.*" language="java" %>--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--<!DOCTYPE html>--%>
<html>

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <script>
        //显示时间
        setInterval("show_time0.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
    </script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <script>var username = '<%=session.getAttribute("username")%>'</script>
    <script>var msg = '<%=request.getAttribute("msg")%>'</script>
    <![endif]-->
    <style>
        .abc {
            color: #00F
        }
    </style>
</head>

<body class="layui-anim layui-anim-up">
<%--<script>--%>
<%--function updateorder(username) {--%>

<%--<%--%>
<%--if (username!=null)--%>
<%--{SearchDao u=new SearchDao();--%>
<%--u.updateuserstatusbyname(username);--%>
<%--}--%>
<%--%>--%>

<%--}--%>

<%--</script>--%>

<div class="x-nav">
      <span class="layui-breadcrumb">
        <%--<a href="/javawebservlet/index.jsp">首页</a>--%>
        <%--<a href="/javawebservlet/QueryTodayOrderUserServlet">订餐页面</a>--%>
            <span style="color: red ;font-size:18px">注意：加班自己订餐的人,请自己准备发票！订餐时间：17:30--18:15</span>
        <%--<a>--%>
          <%--<cite>导航元素</cite></a>--%>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <blockquote class="layui-elem-quote">欢迎订餐：
        <span style="margin-right:24px;" class="abc">${username}</span><span id="show_time0"></span>
    </blockquote>
    <%--<div class="layui-row">--%>
    <%--<form action="/member-list_search.html" class="layui-form layui-col-md12 x-so">--%>
    <%--<input class="layui-input" placeholder="开始日" name="start" id="start">--%>
    <%--<input class="layui-input" placeholder="截止日" name="end" id="end">--%>
    <%--<input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">--%>
    <%--<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>--%>
    <%--</form>--%>
    <%--</div>--%>
    <%--<xblock>--%>
    <%--<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>--%>
    <%--<button class="layui-btn" onclick="x_admin_show('添加用户','./member-add.html',600,400)"><i class="layui-icon"></i>添加</button>--%>
    <%--<span class="x-right" style="line-height:40px">共有数据：88 条</span>--%>
    <%--</xblock>--%>

    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>姓名</th>
            <th>订餐操作</th>
            <th>订餐操作</th>
            <th>当前时间</th>
            <%--<th>订餐备注</th>--%>
        </tr>
        </thead>
        <tbody>
        <%
            Date d = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = df.format(d);
        %>


        <%--<tr>--%>
        <%--<td>--%>
        <%--<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i>--%>
        <%--</div>--%>
        <%--</td>--%>
        <%--<tr>--%>
        <%--<%!--%>
        <%--//            private String username;--%>
        <%--//            private SearchDao searchDao;--%>
        <%--%><c:forEach items="${QueryTodayOrderUserServlet}" var="user">--%>
        <%--<td>--%>
        <%--<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i--%>
        <%--class="layui-icon">&#xe605;</i>--%>
        <%--</div>--%>
        <%--</td>--%>
        <%--<td>${user.name}</td>--%>
        <%--<c:if test="${user.status==0}">--%>
        <%--<td><c:out value="未订餐"/></td>--%>
        <%--</c:if>--%>
        <%--<c:if test="${user.status==1}">--%>
        <%--<td><c:out value="已订餐"/></td>--%>
        <%--</c:if>--%>
        <%--<td class="td-status">--%>
        <%--<span class="layui-btn layui-btn-normal layui-btn-mini" onclick='updateorder("${user.name}")'>订餐</span>--%>
        <%--</td>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>
        <td>
            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                    class="layui-icon">&#xe605;</i>
            </div>
        </td>
        <td>${username}</td>
        <td class="td-status">
            <span class="layui-btn layui-btn-normal layui-btn-mini" onclick='updateorder("${username}","1")'>一起订餐</span>
        </td>
        <td class="td-status">
            <span class="layui-btn layui-btn-normal layui-btn-mini"
                  onclick='updateorder("${username}","2")'>加班自己订餐</span>
        </td>

        <td><%=now %>
        </td>
        <%--<td>--%>
        <%--<input class="layui-input"  name="comment" id="comment">--%>
        <%--</td>--%>
        </tbody>
    </table>
    <%--<div class="page">--%>
    <%--<div>--%>
    <%--<a class="prev" href="">&lt;&lt;</a>--%>
    <%--<a class="num" href="">1</a>--%>
    <%--<span class="current">2</span>--%>
    <%--<a class="num" href="">3</a>--%>
    <%--<a class="num" href="">489</a>--%>
    <%--<a class="next" href="">&gt;&gt;</a>--%>
    <%--</div>--%>
    <%--</div>--%>

</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {

                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 5, time: 1000});

            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 5, time: 1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？' + data, function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }

    function updateorder(username, status) {
        //1. 创建Date对象
        var date = new Date();
        //5. 获得当前小时
        var hour = date.getHours();
        //6. 获得当前分钟
        var min = date.getMinutes();
        //7. 获得当前秒
        var sec = date.getSeconds();
//        var form=document.forms[0];
//        form.action="/UpdateStatusServlet?username="+username;
//        form.submit();
//        alert(username);
//        alert(status);
        var curr = hour * 60 * 60 + min * 60 + sec;
        //alert(curr);
        var startime = 17 * 60 * 60 + 30 * 60;
        var endtime = 18 * 60 * 60 + 15 * 60;
        var startimetext="还没有到订餐时间！";
        var endtimetext="吃饭不积极，订餐已经结束了！";
        if (username.length == 0) {
            layer.msg('请重新登录！', {icon: 1, time: 3000});
            //重定向，打开新页面同时把老页面关闭
            window.top.location.href = "/javawebservlet/login.jsp"
        }else {
            if (curr < startime) {
                //alert("还没有开始");
                layer.open({
                    type: 1
                    ,offset: 'auto'
                    ,id: 'layerDemo'+1 //防止重复弹出
                    ,content: '<div style="padding: 20px 100px;">'+ startimetext +'</div>'
                    ,btn: '关闭全部'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                });
            } else if (curr > endtime) {
//            alert("已经结束");
                layer.open({
                    type: 1
                    ,offset: 'auto'
                    ,id: 'layerDemo'+1 //防止重复弹出
                    ,content: '<div style="padding: 20px 100px;">'+ endtimetext +'</div>'
                    ,btn: '关闭全部'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                });
            } else if (curr >= startime && curr <= endtime) {
                layer.msg('订餐成功！', {icon: 1, time: 3000});
                window.location.href = "/javawebservlet/UpdateStatusServlet?username=" + username + "&status=" + status;
            }

        }

    }

    <%--<%--%>
    <%--if (username!=null)--%>
    <%--{SearchDao u=new SearchDao();--%>
    <%--u.updateuserstatusbyname(username);--%>
    <%--}--%>
    <%--%>--%>
    <%--this.updateorder(username);--%>
    <%--}--%>

</script>

</body>

</html>