<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <%--<a href="/javawebservlet/index.jsp">首页</a>--%>
        <%--<a href="/javawebservlet/QueryResultServlet">订餐结果搜索页</a>--%>
        <span style="color: red;font-size:18px">注意：加班自己订餐的人,请自己准备发票！</span>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="/javawebservlet/QueryOrderByDateServlet">
            <input class="layui-input" placeholder="开始日" name="start" id="start">
            <input class="layui-input" placeholder="截止日" name="end" id="end">
            <input class="layui-input" placeholder="姓名" name="uname" id="uname">
            <%--<div class="layui-input-inline">--%>
            <%--<select name="contrller">--%>
            <%--<option>支付状态</option>--%>
            <%--<option>已支付</option>--%>
            <%--<option>未支付</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--<div class="layui-input-inline">--%>
            <%--<select name="contrller">--%>
            <%--<option>支付方式</option>--%>
            <%--<option>支付宝</option>--%>
            <%--<option>微信</option>--%>
            <%--<option>货到付款</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <div class="layui-input-inline">
                <select name="contrller" id="contrller">
                    <option value="">订餐状态</option>
                    <%--<option value="0">未订餐</option>--%>
                    <option value="1">加班已订餐</option>
                    <option value="2">加班未订餐</option>
                    <%--<option value="2">已收货</option>--%>
                    <%--<option value="3">已取消</option>--%>
                    <%--<option value="4">已完成</option>--%>
                    <%--<option value="5">已作废</option>--%>
                </select>
            </div>
            <%--<input type="text" name="username" placeholder="请输入订单号" autocomplete="off" class="layui-input">--%>
            <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
            <%--<button class="layui-btn" lay-filter="sreach" onclick=""><i class="layui-icon">&#xe73f;</i></button>--%>

            <%--<button class="layui-btn" layui-btn-normal onclick=""><i class="layui-icon"></i></button>--%>
        </form>
        <%--<button class="layui-btn layui-btn-normal" onclick="exporExcel()">导出</button>--%>
    </div>
    <xblock>
        <%--<button class="layui-btn layui-btn-danger" onclick=""><i class="layui-icon"></i>批量删除</button>--%>
        <button class="layui-btn layui-btn-danger" onclick="exporExcel()">导出Excel</button>
        <%--<button class="layui-btn" onclick="x_admin_show('添加用户','./order-add.html')"><i class="layui-icon"></i>添加</button>--%>
        <span class="x-right" style="line-height:40px">共有数据：${pageView.totalrecordnumber} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>编号</th>
            <th>姓名</th>
            <th>状态</th>
            <th>更新时间</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${QueryResult}" var="user">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i
                            class="layui-icon">&#xe605;</i>
                    </div>
                </td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <c:if test="${user.status==0}">
                    <td><c:out value="未订餐"/></td>
                </c:if>
                <c:if test="${user.status==1}">
                    <td><c:out value="加班已订餐"/></td>
                </c:if>
                <c:if test="${user.status==2}">
                    <td><c:out value="加班未订餐"/></td>
                </c:if>
                <td>${user.updatetime}</td>
                    <%--<td>${post.id}</td>--%>
                    <%--<td>${post.name}</td>--%>
                    <%--<td>${post.status}</td>--%>
                    <%--<td>${post.updatetime}</td>--%>
                    <%--<td>待确认</td>--%>
                    <%--<td>未支付</td>--%>
                    <%--<td>未发货</td>--%>
                    <%--<td>其他方式</td>--%>
                    <%--<td>申通物流</td>--%>
                    <%--<td>2017-08-17 18:22</td>--%>
                    <%--<td class="td-manage">--%>
                    <%--<a title="查看" onclick="x_admin_show('编辑','order-view.html')" href="javascript:;">--%>
                    <%--<i class="layui-icon">&#xe63c;</i>--%>
                    <%--</a>--%>
                    <%--<a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">--%>
                    <%--<i class="layui-icon">&#xe640;</i>--%>
                    <%--</a>--%>
                    <%--</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a class="prev" href="javascript:topage('${pageView.currentpage-1}')">&lt;&lt;</a>
            <%--<form method="post" action="/javawebservlet/QueryOrderByDateServlet">--%>
            <c:forEach begin="${pageView.startindex}" end="${pageView.endindex}" var="wp">
                <c:if test="${pageView.currentpage==wp}">
                    <span class="current">${wp}</span>
                </c:if>
                <c:if test="${pageView.currentpage!=wp}">
                    <a class="num" href="javascript:topage('${wp}')">${wp}</a>
                </c:if>
            </c:forEach>
            <%--<a class="num" href="">1</a>--%>
            <span class="current">共${pageView.totalpagenumber}页</span>
            <%--<a class="num" href="">3</a>--%>
            <%--<a class="num" href="">489</a>--%>
            <a class="next" href="javascript:topage('${pageView.currentpage+1}')">&gt;&gt;</a>
            <%--</form>--%>
        </div>
    </div>

</div>
<script>
    function topage(currentpage) {
        var start = '<%=request.getAttribute("start")%>';
        var end = '<%=request.getAttribute("end")%>';
        var contrller = '<%=request.getAttribute("contrller")%>';
        var uname = '<%=request.getAttribute("uname")%>';
        //alert(uname);
        <%--alert(<%=request.getAttribute("msg")%>);--%>
        <%--<%--%>
        <%--String start=String.valueOf(request.getAttribute("start"));--%>
        <%--String end=String.valueOf(request.getAttribute("end"));--%>
        <%--String contrller=String.valueOf(request.getAttribute("contrller"));--%>
        <%--%>--%>
        window.location.href = "/javawebservlet/QueryOrderByDateServlet?currentpage=" + currentpage + "&" + "start=" + start + "&" + "end=" + end + "&" + "contrller=" + contrller + "&" + "uname=" + uname;

    }


    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
            , type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
            , type: 'datetime'
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
    function exporExcel() {
        var obj = document.getElementById("contrller"); //定位id
        var index = obj.selectedIndex; // 选中索引
        //var text = obj.options[index].text; // 选中文本
        var contrller = obj.options[index].value; // 选中值
        var startdate = document.getElementById("start").value;
        var startend = document.getElementById("end").value;

        window.location.href = "/javawebservlet/ExportExcelServlet?startdate="+startdate+"&"+"startend="+startend+"&"+"contrller="+contrller;
    }
</script>

</body>

</html>