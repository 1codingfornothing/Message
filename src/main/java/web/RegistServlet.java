package web;

import pojo.User;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");

        String password = req.getParameter("password");
        String email = req.getParameter("email");


        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());


        // 查询数据库是否已存在该用户
        String sql = "select id from user where name =? or email=?";
        try {
            Connection conn = JdbcUtils.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,email);
            ResultSet res = statement.executeQuery();
            if(res.next()){
                System.out.println("注册失败，用户已存在");
                req.getRequestDispatcher("/pages/regist.html").forward(req, resp);
            }else {
                String sql1 = "insert into user (name, password, email) values(?,?,?)";
                statement = conn.prepareStatement(sql1);
                statement.setString(1, name);
                statement.setString(2, password);
                statement.setString(3, email);
                int res1 = statement.executeUpdate();
                if(res1 == 1){
                    System.out.println("注册成功");
                    req.setAttribute("remind","success");
                    req.getRequestDispatcher("/pages/regist_success.html").forward(req, resp);

                }else {
                    System.out.println("注册失败");
                    req.getRequestDispatcher("/pages/regist.html").forward(req, resp);
                }

                res.close();

                statement.close();
                conn.close();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/pages/regist.html").forward(req, resp);
    }
}
