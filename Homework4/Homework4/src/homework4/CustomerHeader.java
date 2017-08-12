package homework4;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomerHeader extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<header> ");
		out.println("<section class=\"container\">");
		out.println("<img class=\"logo\"" + "src=\"http://burrito-grill.com/wp-content/uploads/cropped-2-300x300.png\">");
		out.println("<h3 class=\"title\">Burrito Cafe</h3>");
		out.println("<span class=\"fill\"></span>");
		out.println("<nav> ");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/Homework4/customerFoodList\">Menu</a>");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/Homework4/customerCart\">Order</a>");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/Homework4/customerOrderStatus\">Statuses</a>");
		out.println("</nav> ");
		out.println("</section> ");
		out.println(" </header> ");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../app.css\">");
	}
}