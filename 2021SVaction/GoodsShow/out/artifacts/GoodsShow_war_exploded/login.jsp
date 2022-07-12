<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/8/25
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="icon" href="https://wangkingjing.gitee.io/myblog/img/zhongli.jpg">
    <script src="./js/jquery-1.9.1.min.js"></script>
</head>
<body>
<%--如果使用from提交数据,页面如果一直在处理数据,就会显示空白,为了避免这种情况,就可以使用ajax,来进行异步请求--%>
<%--onsubmit="return false;"组织表单自动提交--%>
<form onsubmit="return false;" class="box">
    <div>商品管理系统</div>
    <div>
        <label>账号: </label><input name="name" type="text" placeholder="请输入账号"/>
    </div>
    <div>
        <label>密码: </label><input name="password" type="password" placeholder="请输入密码"/>
    </div>
    <button onclick="handlerLoginClick()">登录</button>
</form>
<script type="text/javascript">
    function handlerLoginClick() {
        const nickname = $("input[name='name']").val()
        const password = $("input[name='password']").val()
        $.ajax({
            /*发送地址*/
            url: "/login",
            /*请求的类型*/
            type: "POST",
            /*发送的数据*/
            /*1.
            data:"nickname="+nickname+"&password="+password,*/
            /*2.
            data:{
                nickname,
                password
            }*/
            /*3.
            data:{
                nickname:nickname,
                password:password
            }*/
            /*不使用表单自动提交,就可以序列化表单数据,自动拼接数据*/
            /*4.*/
            data: $("form").serialize(),
            /*接收的数据以json格式进行解析*/
            /*dataType: "json",*/
            success(response) {
                console.log(response)
                if (response.code===666) {
                   /* alert("success")*/
                    /*直接重定向到第一页*/
                    window.location.href="/goodsList/page?current=1&size=5"
                }else {
                    alert("账号或密码错误!请重试")
                }
            }
        });
    }
</script>
</body>
</html>
