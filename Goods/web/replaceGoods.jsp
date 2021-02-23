<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/2/23
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改商品</title>
    <style>
        form{
            width: 300px;
            height: 300px;
            margin: 100px auto 0px;
            display: flex;
            flex-direction: row;
            justify-content: center;
        }
        img{
            width: 40px;
            height: 40px;
            display: block;
        }
        label{
            width: 40px;
        }
        div{
            margin: 20px auto 0px;
        }
        input:focus{
            outline: none;
        }
    </style>
</head>
<body>
<%--在表单中加上onsubmit="return false;"可以阻止表单提交。--%>
<%--商品图片展示--%>

<form method="post" onsubmit="return false;">
    <div class="c_1">
        <img src="${sessionScope.image}" alt="">
    </div>
    <div class="c_2">
        <label for="">名称</label><input autocomplete="off" name="title" type="text" placeholder="${sessionScope.title}">
        <label for="">价格</label><input autocomplete="off" name="price" type="text" placeholder="${sessionScope.price}">
        <input type="submit" placeholder="确认修改">
    </div>

</form>
</body>
</html>
