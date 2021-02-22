<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/2/22
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <style>
        form{
            width: 300px;
            height: 300px;
            background: #fc5531;
            margin: 0 auto;
        }
        .div_1{
            width: 300px;
            height: 40px;
            text-align: center;
            line-height: 40px;
            margin: 100px auto 0px;
        }
        label{
            width: 80px;
        }
    </style>
</head>
<body>
<div class="div_1">添加商品</div>
<form action="">
    <div style="margin: 20px auto 0px"><label for="">名称</label><input name="title" type="text"></div>
    <div style="margin: 20px auto 0px"><label for="">图片</label><input name="image" type="file"></div>
    <div style="margin: 20px auto 0px"><label for="">价格</label><input name="price" type="text"></div>
</form>
</body>
</html>
