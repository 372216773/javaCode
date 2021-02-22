<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/2/22
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品列表</title>

    <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_2336024_4ov4xyhijr.css">
    <style>
        .iconfont{
            font-size: 22px;
        }
        table{
            margin: 0 auto;
        }
        td{
            text-align: center;
        }
        .head{
            height: 40px;
            width: 680px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            margin: 0 auto;
            background-color: bisque;
        }
        .head_1{
            margin: 0 auto;
            padding-left: 80px;
        }
        .head_2{
            width: 120px;
            height: 40px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
        }
        .head_2_a_1{
            display: block;
            margin-top: 10px;
            font-size: 15px;
            /*margin-right: 10px;*/
            color: black;
        }
        .head_2_a_1:visited{
            color: black;
        }
        .head_2_a_2{
            margin-top: 10px;
            font-size: 15px;
            margin-right: 10px;
        }
        i{
            margin-top: 9px;
            font-size: 21px;
            display: block;
        }
        img{
            width: 40px;
            height: 40px;
        }
        a:visited{
            color: #fc5531;
        }
        a{
            text-decoration: none;
        }
        .a_del{
            width: 80px;
            height: 40px;
            background-color: bisque;
            color: #fc5531;
        }
    </style>
    <script src="js/jquery-1.9.1.min.js"></script>
</head>
<body>
<div class="head">
    <div class="head_1">商品列表</div>
    <div class="head_2">
        <i class="iconfont icon-xingmingyonghumingnicheng"></i>
        <a class="head_2_a_1" href="member.jsp">${sessionScope.nickname}</a>
        <a class="head_2_a_2" href="addGoods.jsp">添加</a>
    </div>
</div>
<table border="1px" cellpadding="0px" cellspacing="0px">
    <tr>
        <td>商品编号</td>
        <td>名称</td>
        <td>图片</td>
        <td>价格</td>
        <td colspan="2">操作</td>
<%--        <td>操作</td>--%>
    </tr>
    <c:forEach items="${Goods}" var="good">
        <tr>
            <td>${good.id}</td>
            <td>${good.title}</td>
            <td><img src="${good.image}" alt=""></td>
            <td>${good.price/100}</td>
            <td><a class="a_del" href="#">删除</a></td>
            <td><a class="a_del" href="#">修改</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
