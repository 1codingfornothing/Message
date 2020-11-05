package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.List;

public class NewServlet extends HttpServletRequestWrapper {
    public NewServlet(HttpServletRequest request){
        super(request);
    }

    @Override
    public String getParameter(String name) {
        List<String> dirty = new ArrayList<String>();
        dirty.add("cnm");
        String content = super.getParameter(name);
        for(String s:dirty) {
            if(content.contains("cnm")) {
                content = content.replaceAll(s, "***");
            }
        }
        return content;
    }
}
