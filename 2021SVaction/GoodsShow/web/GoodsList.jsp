<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/8/28
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <title>Goods</title>
    <link rel="stylesheet" href="/css/GoodsList.css">
    <link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_2336024_n1qcjkeqri.css">
</head>
<body>
<%--EL表达式--%>
<div class="div_head"><a href="/user.jsp">${nickname}</a><a href="/addGoods.jsp"><i class="iconfont icon-tianjia"></i></a></div>
<table cellpadding="0" cellspacing="0">
    <tr class="tr_top">
        <td>商品编号</td>
        <td class="td_title">商品名称</td>
        <td>商品图片</td>
        <td>价格</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${ResultMap.GoodsList}" var="good">
        <tr class="tr_goods">
            <td>${good.id}</td>
            <td class="td_title">${good.title}</td>
            <td><img src="${good.image}" alt="" class="tr_img"></td>
            <td>${good.price/100}</td>
            <td class="td_ops">
                <button class="button_ops" onclick="deleteGood('${good.id}',${ResultMap.current})">删除</button>
                <button class="button_ops" onclick="replaceGood('${good.id}')">修改</button>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="div_page">
    <button onclick="tag(1)">首页</button>
    <button onclick="pre(${ResultMap.current})">上一页</button>
    <span>${ResultMap.current}/${ResultMap.pages}</span>
    <button onclick="next(${ResultMap.current})">下一页</button>
    <button onclick="tag(${ResultMap.pages})">尾页</button>
</div>
<script>
    /*跳转至指定页*/
    function tag(page) {
        window.location.href = "/goodsList/page?current=" + page + "&size=5";
    }

    /*前一页*/
    function pre(page) {
        if (page > 1) {
            page = page - 1;
        }
        window.location.href = "/goodsList/page?current=" + page + "&size=5";
    }

    /*下一页*/
    function next(page) {
        if (page < ${ResultMap.pages}) {
            page = page + 1;
        }
        window.location.href = "/goodsList/page?current=" + page + "&size=5";
    }

    /*删除商品,ajax*/
    function deleteGood(id, page) {
        $.ajax({
            //去指定路径下进行操作
            url: "/goodsList/delete?id=" + id + "&page=" + page,
            success(resp) {
                console.log(resp.page)
                if (resp.code === 666) {
                    window.location.href = "/goodsList/page?current=" + resp.page + "&size=5"
                }
            }
        })
    }

    /*修改商品*/
    function replaceGood(id) {
        $.ajax({
            url: "/goodsList/getGood?id=" + id,
            success() {
                window.location.href = "/replaceGoods.jsp"
            }
        })
    }
</script>
</body>
</html>
