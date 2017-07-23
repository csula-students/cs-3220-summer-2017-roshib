import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Admin can see customer's order status */

@WebServlet(loadOnStartup = 1, urlPatterns = { "/admin/orders" })
public class OrderStatusesAdminServlet extends HttpServlet {
	public void init() {

		List<Order> order = new ArrayList<>();
		getServletContext().setAttribute("order", order);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodEntry> cart = (List<FoodEntry>) getServletContext().getAttribute("cart");
		// tell browser this is html document
		response.setContentType("text/html");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../app.css\">");
		out.println("<style>body { " + "}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header> ");
		out.println("<section class=\"container\">");
		out.println(
				"<img class=\"logo\"" + "src=\"http://burrito-grill.com/wp-content/uploads/cropped-2-300x300.png\">");
		out.println("<h3 class=\"title\">Burrito Cafe-Admin</h3>");
		out.println("<span class=\"fill\"></span>");
		out.println("<nav> ");
		out.println("<a class=\"headList\" href='/Homework2/admin/foods/create'>Create Food</a>");
		out.println("<a class=\"headList\" href='/Homework2/menu'>Customer Menu</a>");
		out.println("<a class=\"headList\" href='/Homework2/admin/orders'>Order Status</a>");
		out.println("</nav> ");
		out.println("</section> ");
		out.println(" </header> ");
		out.println(" <main> ");
		out.println(" <section class=\"order\"> ");
		out.println(" <label class=\"orderLable\">Admin Food Order Status</label> ");

		List<Order> order = (List<Order>) getServletContext().getAttribute("order");

		if (order.size() == 0) {
			out.println("<table  class=\"order_status_table center\" id=\"myTable\">");
			out.println("<tr>" + "<td>"
					+ "<h3>**** Order is empty*** </h3>" + "</td>" + "</tr>");
			out.println("</table>");
			out.println("<div style=\"padding-top:180px;\"></div>");

		} else {
			out.println("<table class=\"order_status_table center\" id=\"myTable\">");
			out.println(" <thead> ");
			out.println(" <tr>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(0)\">Name</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(1)\">Price</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(3)\">Image</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(2)\">Customer</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(2)\">Status</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(2)\">date</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(3)\">Action</th>\r\n"
					+ "					</tr> ");
			out.println(" </thead> ");
			out.println(" <tbody> ");
			for (Order newOrder : order) {
				out.println("<tr>" + "<td>" 
						+ newOrder.getFood().getName() + "</td>" + "<td>"
						+ newOrder.getFood().getPrice() + "</td>" + "<td>" + "<img src="
						+ newOrder.getFood().getImage() + ">" + "</td>" + "<td>"
						+ newOrder.getCustomerName() + "</td>" + "<td>"
						+ newOrder.getStatus() + "</td>" + "<td>"
					    + newOrder.getDate() + "</td>" + "<td><a style=\"padding:1em; text-decoration: none; color:black;\" href='/Homework2/admin/orders/edit?id="
						+ newOrder.getId() + "'>Edit</a>" + "</td>" + "</tr>");

			}

			out.println(" </tbody> ");
			out.println("</table>");

		}
		out.println("</section>");
		out.println("</main>");
		out.println("<footer> ");
		out.println("<div>");
		out.println("<a class=\"FooterItem\" href='/Homework2/admin/foods/'>Term & Conditions</a>");
		out.println("</div>");
		out.println("<div>");
		out.println("<a class=\"FooterItem\" href='/Homework2/admin/foods/'>Privacy Statement</a>");
		out.println("</div>");
		out.println("<div>");
		out.println("<a class=\"FooterItem\" href='/Homework2/admin/foods/'>Cookie Policy</a>");
		out.println("</div>");
		out.println("<span class=\"fill\"></span> ");
		out.println("<div>");
		out.println("<p class=\"FooterItemLast\">© 2017 Burrito Cafe. All Rights Reserved</p>");
		out.println("</div>");
		out.println(" </footer> ");
		out.println(" </body> ");
		out.println("</html >");
	}
}