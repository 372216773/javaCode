<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/8/31
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <link rel="stylesheet" href="/css/addGoods.css">
</head>
<body>
<form enctype="multipart/form-data" method="post" action="/goodsList/replace">
    <input style="text-decoration: none" type="text" name="id" value="${good.id}" readonly="readonly">
    <div>
        <label for="">商品名称</label><input type="text" name="title" value="${good.title}" placeholder="${good.title}">
    </div>
    <div>
        <label for="">商品价格</label><input type="text" name="price" value="${good.price}" placeholder="${good.price}">
    </div>
    <div>
        <label for="">商品图片</label><input type="file" value="${good.image}" name="image">
    </div>

    <div>
        <label for="">商品描述</label><input type="text" name="description" value="${good.description}" placeholder="${good.description}">
    </div>
    <div>
        <button type="submit">添加</button>
    </div>
</form>
</body>
</html>
