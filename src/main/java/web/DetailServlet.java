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
import java.util.List;

@WebServlet(name = "DetailServlet")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int message_id = Integer.parseInt(id);
        try {
            User user = (User)request.getSession().getAttribute("user");
            Connection conn = JdbcUtils.getConnection();

            String sql = "select * from message where id =?";

            QueryRunner runner = new QueryRunner();
            String name = user.getUsername();


            BeanListHandler<Message> handler1 = new BeanListHandler<>(Message.class);



            List<Message> messageList = runner.query(conn,sql,handler1,id);
            Integer count = messageList.get(0).getCount()+1;
            String sql1 = "update message set count = ? where id = ?";
            runner.execute(conn,sql1, count,id);


            request.setAttribute("messageList",messageList);

            request.getRequestDispatcher("/pages/detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}
