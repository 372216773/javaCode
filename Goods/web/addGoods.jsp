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
            background: #ffe4c4;
            margin: 100px auto 0px;
        }
        .div_1{
            margin: 0 auto 40px;
            width: 300px;
            height: 20px;
            text-align: center;
            line-height: 20px;
        }
        label{
            width: 40px;
            display: inline-block;
        }
        .submit{
            margin: 40px auto 0px;
            display: block;
        }
        div{
            margin: 20px auto 0px;
            width: 222px;
        }
        .icon-upload{
            display: inline-block;
            width: 168px;
        }
        input:focus{
            outline: none;
        }
    </style>
    <script src="./js/jquery-1.9.1.min.js"></script>
</head>
<body>
<%--multipart/form-data:传输格式改为多元格式--%>
<%--自动提交到/goods/add--%>
<form action="/goods/add" method="post" enctype="multipart/form-data">
    <div class="div_1">添加商品</div>
    <div style="margin: 20px auto 0px">
        <label for="">名称</label>
        <input autocomplete="off" name="title" type="text" placeholder="请输入名称">
    </div>
    <div style="margin: 20px auto 0px">
        <label for="">图片</label>
        <input autocomplete="off" class="icon-upload" name="image" type="file">
    </div>
    <div style="margin: 20px auto 0px">
        <label for="">价格</label><input autocomplete="off" name="price" type="text" placeholder="请输入价格">
    </div>
    <input style="margin: 10px auto;width: 60px;display: block;" type="submit" placeholder="确认提交">
</form>
</body>
</html>
