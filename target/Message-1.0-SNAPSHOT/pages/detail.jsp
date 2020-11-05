<%@ page import="pojo.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/11/3
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    List<Message> messageList = (List<Message>) request.getAttribute("messageList");

%>
<table>






    <% for(Message message: messageList){ %>
    <tr><td class="klytd">留言人：</td> <td> <%=message.getName()%></td></tr>

    <tr><td class="klytd">标题：</td> <td> <%=message.getTitle()%></td><td class="klytd">  点击量：</td> <td><td> <%=message.getCount()%></td></tr>
    <tr><td class="klytd">内容：</td> <td> <%=message.getContent()%></td></tr>

    <% } %>



</table>

</body>
</html>
