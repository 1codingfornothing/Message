package web;

import pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        String id = session.getId();
        if(session.isNew()){
            User user =(User) session.getAttribute("user");

        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
