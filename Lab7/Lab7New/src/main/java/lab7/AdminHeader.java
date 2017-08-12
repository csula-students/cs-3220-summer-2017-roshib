package lab7;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AdminHeader extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<header> ");
		out.println("<section class=\"container\">");
		out.println(
				"<img class=\"logo\"" + "src=\"http://burrito-grill.com/wp-content/uploads/cropped-2-300x300.png\">");
		out.println("<h3 class=\"title\">Burrito Cafe-Admin</h3>");
		out.println("<span class=\"fill\"></span>");
		out.println("<nav> ");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/adminFoodMenu\">Inventory</a>");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/adminCreateFood\">Create Food</a>");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/adminOrderStatus\">Order Status</a>");
		out.println("<a class=\"headList\" href=\"http://localhost:8080/customerFoodList\">Customer Menu</a>");
		out.println("</nav> ");
		out.println("</section> ");
		out.println(" </header> ");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../app.css\">");
	}
}