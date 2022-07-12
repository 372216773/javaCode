<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/8/26
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="/css/regist.css">
    <script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
</head>
<body>
<form method="post" action="/regist" enctype="application/x-www-form-urlencoded">
    <div>
        <label for="">用户名</label><input type="text" name="name" placeholder="请输入账号">
    </div>
    <div>
        <label for="">密码</label><input type="text" name="password" placeholder="请输入密码">
    </div>

    <div class="div_province">
        <div class="select_option">
            <label>地址</label>
            <select name="province" onchange="handlerProvinceChange(value)">
            </select>
            <select name="city">
            </select>
        </div>
    <label class="label_desc">
        <input type="text" name="extra" placeholder="请输入详细信息">
    </label>
    <%--sumit会将表单同步提交到指定路径--%>
    <%--也可以通过ajax手动获取一个个的表单控件的值进行提交--%>
    <input type="submit" value="注册" class="submit"/>
</form>

<script>

    /**
     * 处理省份下拉改变
     * @param value
     */
    function handlerProvinceChange(value) {
        //清除原有数据
        $("select[name='city']").empty()
        /*onchange事件触发*/
        $.ajax({
            /*请求转发到指定路径*/
            url: "/city/listByPid?pid=" + value,
            /*从指定路径后来后*/
            success(response) {
                for (const x in response) {
                    $("select[name='city']").append("<option value=" + response[x].id + ">" + response[x].name + "</option>");
                }
            }
        })

    }

    $(function () {
        //先加载一段数据,防止初次加载时的空白
        $.ajax({
            url:"/city/listByPid?pid=1",
            success(response) {
                for (const x in response) {
                    $("select[name='city']").append("<option value=" + response[x].id + ">" + response[x].name + "</option>");
                }
            }
        })
        $.ajax({
            /*请求转发到指定路径*/
            url: "/province/list",
            /*拿到东西回来了,拿的是write中的东西*/
            success(response) {
                console.log(response)
                for (const i in response) {
                    $("select[name='province']").append("<option value=" + response[i].id + ">" + response[i].name + "</option>")
                }
            }
        })
    });
</script>
</body>
</html>
