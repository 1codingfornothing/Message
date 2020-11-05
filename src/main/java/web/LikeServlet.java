package web;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Message;
import pojo.User;
import utils.JdbcUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class LikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        int message_id = Integer.parseInt(id);
        try {
            User user = (User)req.getSession().getAttribute("user");
            Connection conn = JdbcUtils.getConnection();

            String sql = "select * from message where id =?";
            String sql2 = "select * from message";

            QueryRunner runner = new QueryRunner();
            String name = user.getUsername();


            BeanListHandler<Message> handler1 = new BeanListHandler<>(Message.class);



            List<Message> messageList = runner.query(conn,sql,handler1,message_id);

            Integer count = messageList.get(0).getLikecount()+1;
            System.out.println(count);
            String sql1 = "update message set likecount = ? where id = ?";
            runner.execute(conn,sql1, count,message_id);
            List<Message> messageList1 = runner.query(conn,sql2,handler1);



            req.setAttribute("messageList",messageList1);

            req.getRequestDispatcher("/pages/showmessage.jsp").forward(req, resp);
            System.out.println("点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
