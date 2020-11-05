<%@ page import="pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="pojo.Message" %>
<%@ page import="pojo.Comment" %>
<%@ page import="pojo.Follow" %><%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/11/1
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <table>
        <tr><td class="klytd">Hello,${sessionScope.user.username}</td><td class="hvttd"></td></tr>

    </table>
    <td><a href="SendMessageServlet">返回首页</a></td>
</head>
<body>
<div style="margin:0px auto;" class="keleyitable"><h2>用户列表</h2>
    <%

        List<User> userList = (List<User>) request.getAttribute("userList");
        List<Message> messageList = (List<Message>) request.getAttribute("messageList");
        List<Comment> commentList = (List<Comment>) request.getAttribute("commentList");
        List<Follow> followList = (List<Follow>) request.getAttribute("followList");
    %>




    <table>


        <% for(User user: userList){ %>
        <tr><td class="klytd">用户ID：</td> <td> <%=user.getId()%></td></tr>
        <td><a href="followServlet?id=<%=user.getId()%>">关注</a></td>


        <% } %>

    </table>
</
<div style="margin:0px auto;" class="keleyitable"><h2>我的关注</h2>
    <table>
        <% for(Follow follow: followList){ %>
        <tr><td class="klytd">用户ID：</td> <td> <%=follow.getFollowed_id()%></td></tr>


        <% } %>
    </table>

        <div style="margin:0px auto;" class="keleyitable"><h2>我的留言</h2>
            <table>


                <% for(Message message: messageList){ %>
                <tr><td class="klytd">标题：</td> <td> <%=message.getTitle()%></td></tr>
                <tr><td class="klytd">留言内容：</td> <td> <%=message.getContent()%></td></tr>

                <% } %>

            </table>
<div style="margin:0px auto;" class="keleyitable"><h2>我的评论</h2>
            <table>


                <% for(Comment comment: commentList){ %>
                <tr><td class="klytd">留言ID：</td> <td> <%=comment.getMessage_id()%></td></tr>
                <tr><td class="klytd">评论内容：</td> <td> <%=comment.getContent()%></td></tr>

                <% } %>




</body>
</html>
