<%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/11/1
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>

    <h3>评论</h3>
    <form  action="commentServlet?<%=request.getQueryString()%>" method="post">
        <fieldset>
            <br style="clear:both" />
            <textarea cols="25" rows="5" name="content" >你的评论</textarea>
            <br style="clear:both" />
            <input type="submit" name="submit" class="button"  value="提交"/>
        </fieldset>
    </form>

</body>
</html>
