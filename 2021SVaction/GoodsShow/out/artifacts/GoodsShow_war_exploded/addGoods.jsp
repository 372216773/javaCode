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
<form enctype="multipart/form-data" method="post" action="/goodsList/addGoods">
    <div>
        <label for="">商品名称</label><input type="text" name="title" placeholder="商品名称">
    </div>
    <div>
        <label for="">商品价格</label><input type="text" name="price" placeholder="商品价格">
    </div>
    <div>
        <label for="">商品图片</label><input type="file" name="image">
    </div>

    <div>
        <label for="">商品描述</label><input type="text" name="description" placeholder="商品描述">
    </div>
    <div>
        <button type="submit">添加</button>
    </div>
</form>
</body>
</html>
