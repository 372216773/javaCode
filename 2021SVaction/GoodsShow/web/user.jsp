<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/9/1
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<form action="/user" enctype="application/x-www-form-urlencoded" method="post">
    <div>基本信息</div>
    <label for="">用户名</label><input type="text" placeholder="${user.nickname}" value="${user.nickname}">
    <label for="">性别</label><input type="text" placeholder="${user.sex}" value="${user.sex}">
    <label for="">简介</label><input type="text" value="${user.description}">
    <button type="submit">确定修改</button>
</form>
</body>
</html>
