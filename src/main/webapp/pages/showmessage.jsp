

<%@ page import="pojo.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/10/30
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin:0px auto;" class="keleyitable"><h2>留言板</h2>
        <%

    List<Message> messageList = (List<Message>) request.getAttribute("messageList");


    %>




    <table>






        <% for(Message message: messageList){ %>
        <tr><td class="klytd">留言人：</td> <td> <%=message.getName()%></td></tr>

        <tr><td class="klytd">标题：</td> <td> <%=message.getTitle()%></td><td class="klytd">点击量：</td> <td> <%=message.getCount()%></td><td class="klytd">点赞数：</td> <td> <%=message.getLikecount()%></td></tr>
        <tr><td class="klytd">内容：</td> <td> <%=message.getContent()%></td></tr>
        <tr><td class="klytd">图片：</td> <td> <%=message.getFilepath()%></td></tr>
        <td><a href="detailServlet?id=<%=message.getId()%>">详情</a></td>
        <td><a href="commentServlet?id=<%=message.getId()%>">评论</a></td>
        <td><a href="LikeServlet?id=<%=message.getId()%>">点赞</a></td>
        <% } %>



    </table>
</body>

</html>
