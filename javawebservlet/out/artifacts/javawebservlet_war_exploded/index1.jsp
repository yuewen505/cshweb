<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/10/11
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>测试get</title>
</head>
<body>
<form action="demo2" method="get">
    网址名：<input type="text" name="name">
    网址：<input type="text" name="url">
    <input type="submit" value="提交">
</form>

<c:set value="wolfy" var="username" scope="request"></c:set>
${username}

</body>
</html>
