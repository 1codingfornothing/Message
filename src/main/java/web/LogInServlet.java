package web;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Message;
import pojo.User;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LogInServlet")
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = new User();
        loginUser.setUsername(name);
        loginUser.setPassword(password);




        try {
            Connection conn = JdbcUtils.getConnection();
            String sql = "select id from user where name=? and password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();

            QueryRunner runner = new QueryRunner();
            String sql1 = "select * from user ";
            BeanListHandler<User> handler = new BeanListHandler<>(User.class);
            List<User> userList = runner.query(conn, sql1, handler);




            if(resultSet.next()){

                request.getSession().setAttribute("user",loginUser);
                User user = (User)request.getSession().getAttribute("user");
                System.out.println(request.getSession().getId());
                if(request.getSession().getId().equals(request.getSession().getAttribute("s_id"))){
                    System.out.println("重复登录");
                    response.sendRedirect("/pages/faillogin.jsp");
                }else {


                    System.out.println("登录成功");
                    request.getSession().setAttribute("s_id",request.getSession().getId());
                    request.setAttribute("userList",userList);
                    request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
                }

                //response.sendRedirect("/pages/index.jsp");
            }else {
                System.out.println("登入失败");
                request.getRequestDispatcher("/pages/login.html").forward(request, response);
            }
            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login.html").forward(request, response);

    }
}
