package lab6;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AdminFooter extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<footer> ");
        out.println("<div>");
        out.println("<a class=\"FooterItem\" href=\"http:/Lab6/adminFoodMenu\">Term & Conditions</a>");
        out.println("</div>");
        out.println("<div>");
        out.println("<a class=\"FooterItem\" href=\"http:/Lab6/adminFoodMenu\">Privacy Statement</a>");
        out.println("</div>");
        out.println("<div>");
        out.println("<a class=\"FooterItem\" href=\"http:/Lab6/adminFoodMenu\">Cookie Policy</a>");
        out.println("</div>");
        out.println("<span class=\"fill\"></span> ");
        out.println("<div>");
        out.println("<p class=\"FooterItemLast\">© 2017 Burrito Cafe. All Rights Reserved</p>");
        out.println("</div>");
        out.println(" </footer> ");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../app.css\">");
    }
}