<%@ page import="pojo.Comment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/11/1
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评论</title>
</head>
<body>
<div style="margin:0px auto;" class="keleyitable"><h2>评论区</h2>
<%

    List<Comment> commentList = (List<Comment>) request.getAttribute("commentList");



%>




<table>





    <% for(Comment comment: commentList){ %>
    <tr><td class="klytd">评论内容：</td> <td> <%=comment.getContent()%></td></tr>
    <tr><td class="klytd">评论人：</td> <td> <%=comment.getCreated_by()%></td></tr>
    <td><a href="LikeServlet">点赞！</a></td>
    <td>
        <form action="LikeServlet" method="post">
            <input type="submit" value="点赞">
        </form>
    </td>



    <% } %>



</table>
</body>
</html>
