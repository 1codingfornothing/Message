package web;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Message;
import pojo.User;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//标识Servlet可以上传文件
@MultipartConfig
@WebServlet(name = "/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //文件上传

        //存储路径
        String savePath = request.getServletContext().getRealPath("/WEB-INF/lib");
        //Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作
        ////通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
        Part file = request.getPart("file");
        //Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
        //获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
        String header = file.getHeader("content-disposition");
        //获取文件名
        String fileName = getFileName(header);

        //把文件写到指定路径
        file.write(savePath+ File.separator+fileName);
        System.out.println("上传成功");
        System.out.println(savePath+ File.separator+fileName);


        String title = request.getParameter("title");

        String content = request.getParameter("content");
        User user = (User)request.getSession().getAttribute("user");
        Message message = new Message();
        message.setContent(content);
        message.setName(user.getUsername());
        message.setTitle(title);
        message.setFilepath(savePath+ File.separator+fileName);



        try {
            Connection conn = JdbcUtils.getConnection();

            String sql = "insert into message (name, title, content, filepath) values(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            System.out.println(user.getUsername());
            statement.setString(2,title);
            statement.setString(3,content);
            statement.setString(4,savePath+ File.separator+fileName);
            request.setAttribute("scr",savePath+ File.separator+fileName);

            int res = statement.executeUpdate();

            QueryRunner runner = new QueryRunner();

            String sql1 = "select * from message order by count DESC ";

            BeanListHandler<Message> handler = new BeanListHandler<>(Message.class);
            List<Message> messageList = runner.query(conn, sql1, handler);




            if(res==1){
                System.out.println("留言成功");

                // 这里把数据存进去
                request.setAttribute("messageList", messageList);

                request.getRequestDispatcher("/pages/showmessage.jsp").forward(request, response);
            }else {
                System.out.println("留言失败");
                request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
            }


            statement.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);

    }
    public String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
         */
        String[] tempArr1 = header.split(";");
/**
 *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
 *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
 */
        String[] tempArr2 = tempArr1[2].split("=");
//获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
        return fileName;

    }
}



