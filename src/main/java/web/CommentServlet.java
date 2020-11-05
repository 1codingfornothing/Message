package web;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Comment;
import pojo.Message;
import pojo.User;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CommentServlet")
public class CommentServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User)request.getSession().getAttribute("user");
        String content = request.getParameter("content");
        System.out.println(content);

        String id = request.getParameter("id");
        System.out.println(id);
//        String id=request.getQueryString();
//        String id= request.getRequestURL().toString();




        int message_id = Integer.parseInt(id);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setMessage_id(message_id);
        comment.setCreated_by(user.getUsername());








        try {
            Connection conn = JdbcUtils.getConnection();



            String sql = "insert into comment (content, message_id, created_by) values(?,?,?)";
            String sql1 = "select * from comment where message_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, content);
            statement.setInt(2, message_id);
            statement.setString(3,user.getUsername());

            System.out.println(user.getId());
            int res = statement.executeUpdate();

            QueryRunner runner = new QueryRunner();
            BeanListHandler<Comment> handler = new BeanListHandler<>(Comment.class);
            List<Comment> commentList = runner.query(conn, sql1, handler, message_id);



            if(res==1){
                System.out.println("评论成功");
                request.setAttribute("commentList",commentList);
                request.getRequestDispatcher("/pages/showcomment.jsp").forward(request, response);
            }
            statement.close();
            conn.close();




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/pages/comment.jsp").forward(request, response);

    }
}
