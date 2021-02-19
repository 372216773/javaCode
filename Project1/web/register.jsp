<%--
  Created by IntelliJ IDEA.
  User: WJ
  Date: 2021/2/18
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <style>
        form{
            width:400px;
            height: 400px;
            display: flex;
            flex-direction: column;
            justify-content: normal;
            margin: 0 auto;
        }
        div{
            margin: 10px auto 0px;
            display: flex;
            flex-direction: row;
            justify-content: space-around;
        }
        label{
            width:60px;
        }
        .input_2{
            width: 66px;
            height: 20px;
            margin: 10px auto 0px;
        }
    </style>
    <script type="text/javascript" src="./js/jquery-1.9.1.js"></script>
</head>

<body>
<form method="post" action="/register">
    <div>
        <label>用户名: </label> <input name="nickname" type="text" placeholder="请输入账号:">
    </div>
    <div>
        <label>密码: </label> <input name="password" type="text" placeholder="请输入密码:">
    </div>
    <div>
        <label>地址: </label>
        <select name="province" onchange="handlerProvinceChange(this.value)">
            <option value="陕西">陕西</option>
            <option value="山西">山西</option>
        </select>
        <select name="city">
            <option value="西安市">西安市</option>
            <option value="渭南市">渭南市</option>
        </select>
    <input name="extra" class="input_1" type="text" placeholder="请输入具体街道门牌号">
    </div>
    <input class="input_2" type="submit" value="注册">
</form>
<script>
    /**
     * 处理下拉改变,根据省份的id查询省份里边的所有市区
     */
    function handlerProvinceChange() {
        $("select[name='city']").empty();
        $.ajax({
            url: "/city/listByPid?pid="+value,
            success(resp) {
                for (const i in resp) {
                    $("select[name='city']").append("<option value='"+resp[i].id+"'>"+resp[i].name+"</option>")
                }
            }
        })
    }

//    页面加载之后
$(function () {
    $.ajax({
        //请求的url
        url: "/province/list",
        success(resp) {
            console.log(resp)
            for (const x in resp) {
                $("select[name='province']").append("<option value='" + resp[x].id + "'>" + resp[x].name + "</option>")
                if (x == 0) {
                    $({
                        url: "/city/listByPid?pid=" + resp[x].id,
                        success(resp) {
                            $("select[name='city']").append("<option value='" + resp[x].id + "'>" + resp[x].name + "</option>")
                        }
                    })
                }
            }
        }
    })
})
</script>
</body>
</html>
