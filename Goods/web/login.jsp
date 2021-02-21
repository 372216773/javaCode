<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/2/20
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        form{
            width: 300px;
            height: 300px;
            margin: 100px auto;
            background: bisque;
        }
        label{
            display: inline-block;
            width: 80px;
        }
        div{
            margin: 20px auto 0px;
            display: flex;
            justify-content: center;
            flex-direction: row;
        }
        .d1{
            padding-top: 50px;
        }
        .d3>input{
            display: block;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<form action="">
        <div class="d1">
            <label>用户名</label><input name="nickname" type="text" placeholder="请输入用户名">
        </div>
        <div class="d2">
            <label>密码</label><input name="password" type="text" placeholder="请输入密码">
        </div>
        <div class="d3"><input type="submit" value="登录"></div>

</form>
</body>
</html>
