package ua.nure.admin.summarytask.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ErrorTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<h1 align=\"center\">ACCESS DENIED! <a href=\"/\">Home</a></h1>");
    }
}
