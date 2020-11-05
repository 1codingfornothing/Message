package web;

import utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;

@WebFilter(filterName = "CountFilter")
public class CountFilter implements Filter {
    private int hitCount;
    public void destroy() {

        try {
            Connection conn = JdbcUtils.getConnection();
            String sql = "UPDATE comment SET count = ? WHERE count = ?";

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        hitCount++;

        System.out.println("Site visits count :"+ hitCount );

        chain.doFilter(req, resp);


    }

    public void init(FilterConfig config) throws ServletException {
        hitCount = 0;

    }

}
