<%@ page import="pojo.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anlizhaomi
  Date: 2020/10/29
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <a href="LogInServlet">登入</a>
    <a href="registServlet">注册</a>
    <a href="userServlet">个人中心</a>
    <title>留言板 </title>
    <style>
        .keleyitable {
            width: 800px;
        }

        .keleyitable table, td, th {
            border: 1px solid green;margin-top:10px;
        }
        .klytd {width:100px;text-align:right
        }
        .hvttd {
            width:500px}
    </style>

</head>
<body>
<form action="SendMessageServlet" method="post" enctype="multipart/form-data">
    <table  border="1" align="center">


        <tr>
            <td><label for="title">标题</label></td>
            <td><input type="title" name="title" placeholder="请输入标题" id="title"></td>
        </tr>
        <tr>
            <td><label for="content">内容</label></td>
            <td><input type="content" name="content" placeholder="请输入留言内容" id="content"></td>
        </tr>
        <tr>
            <td>图片上传</td>
            <td><input type="file" name="file"/></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="留言" ></td>
        </tr>

    </table>
</form>
<form method="post" action="UpLoadServlet" enctype="multipart/form-data" >

    <table>



        <tr>
            <td>图片上传</td>
            <td><input type="file" name="myfile"/></td>
            <td></td>
        </tr>



        <tr>
            <td></td>
            <td>
                <input type="submit" value="提交" id="sub" disabled="disabled"/>

            </td>
            <td></td>
        </tr>
    </table>
</form>



</body>
</html>
