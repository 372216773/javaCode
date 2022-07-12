<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>jsp页面</h1>
    <%--脚本片段,用来写Java代码--%>
    <%
        int a = 10;
        System.out.println("hello" + a);
    %>
    <%= a%>
    <% request.setAttribute("name", "wa");%>
    <% System.out.println(request.getAttribute("name"));%>
    <%= request.getAttribute("name") %>

    <%--<% session.setAttribute("sessionAttribute", "123");%>
    <% System.out.println(session.getAttribute("sessionAttribute"));%>
    <%= session.getAttribute("sessionAttribute")%>
    <%= session.getAttribute("sessionAttribute")%>--%>

    <%--<%pageContext.setAttribute("name", "234");%>--%>
    <%--out.println();输出到页面中--%>
    <%--System.out.println();输出到控制台中--%>
    <%--<%out.println("out.println();" + pageContext.getAttribute("name"));%>
    <%out.println(pageContext.getAttribute("name"));%>
    <%=pageContext.getAttribute("name")%>--%>

    <%--<c:set var="name" value="admin" scope="request"></c:set>--%>
    <%--<c:out value="name"></c:out>--%>

    <%--pageContext.setAttribute("name", "234");--%>
    <%--<c:if test="${name=='234'}" scope="page" var="m">
        <h1>条件成立</h1>
    </c:if>--%>
    <%--session.setAttribute("sessionAttribute", "123");--%>
    <%--<c:if test="${sessionAttribute=='123'}" scope="session" var="m">
        <h1>条件成立</h1>
    </c:if>--%>

    <c:if test="${name=='wa'}" scope="request" var="name">
        <h1>1231</h1>
    </c:if>

    <%request.setAttribute("age",70);%>
    <%= request.getAttribute("age")%>
    <c:choose>
        <c:when test="${age}>20">
            <h1>awdac</h1>
        </c:when>
        <c:when test="${age}30">
            <h1>awdac</h1>
        </c:when>
        <c:when test="${age}>40">
            <h1>awdac</h1>
        </c:when>
        <c:when test="${age}>50">
            <h1>awdac</h1>
        </c:when>
    </c:choose>

    <%
        int[] numList = new int[]{1,2,3,4,5,6,7,8,9,10};
        request.setAttribute("List",numList);
    %>
    <c:forEach items="${List}" var="num" begin="0" end="9" step="2">
        <p>${num}</p>
    </c:forEach>

    <div>
        <%=request.getAttribute("user")%>
    </div>
</div>
</body>
</html>