<%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/11/3
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>
        <%
			int count = (Integer) request.getAttribute("count");
		%>
    <h2>
        点赞量:[<%=count%>]
    </h2>
    <button onclick="window.location.href='count.jsp'">返回</button>

</html>
