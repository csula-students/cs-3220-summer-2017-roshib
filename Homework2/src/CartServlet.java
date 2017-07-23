import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.Date;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/shopping-cart" })
public class CartServlet extends HttpServlet {
	public void init() {
		List<FoodEntry> cart = new ArrayList<>();
		getServletContext().setAttribute("cart", cart);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodEntry> food = (List<FoodEntry>) getServletContext().getAttribute("food");
		response.setContentType("text/html");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"app.css\">");
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
		out.println("<a class=\"headList\" href='/Homework2/menu'>Menu</a>");
		out.println("<a class=\"headList\"  href='/Homework2/shopping-cart'>Cart</a>");
		out.println("<a class=\"headList\" href='/Homework2/orders'>Order Status</a>");
		out.println("</nav> ");
		out.println("</section> ");
		out.println(" </header> ");
		out.println(" <main> ");
		out.println(" <section class=\"order\"> ");
		out.println(" <label class=\"orderLable\">Cart</label> ");
		List<FoodEntry> cart = (List<FoodEntry>) getServletContext().getAttribute("cart");

		if (cart.size() == 0) {
			out.println("<table class=\"order_status_table center\" id=\"myTable\">");
			out.println("<tr style=\"padding:1em;\">" + "<td style=\"padding:1em;\">"
					+ "<h3>**** Your cart is empty*** </h3>" + "</td>" + "</tr>");
			out.println("</table>");

		} else {
			out.println("<table class=\"order_status_table center\" id=\"myTable\">");
			out.println(" <thead> ");
			out.println(" <tr>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(0)\">Name</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(1)\">Price</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(2)\">Description</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(3)\">Image</th>\r\n"
					+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(3)\">Action</th>\r\n"
					+ "					</tr> ");
			out.println(" </thead> ");
			out.println(" <tbody> ");
			for (FoodEntry entry : cart) {
				out.println("<tr>" + "<td>" + entry.getName() + "</td>" + "<td >" + entry.getPrice() + "</td>" + "<td >"
						+ entry.getComment() + "</td>" + "<td >" + "<img src=" + entry.getImage() + ">" + "</td>"
						+ "<td>"
						+ "<a style=\"padding:1em; text-decoration: none; color:black; \" href='/Homework2/shopping-cart/delete?id="
						+ entry.getId() + "'>Delete</a></td>" + "</tr>");
			}

			out.println(" </tbody> ");
			out.println("</table>");
		}
		out.println(" <table> ");
		out.println(" <tr><td> ");
		out.println("<h3>** Type Your Name **</h3>");
		out.println("<form method=\"post\">");
		out.println("<input name='name' type='text'/></br><br>");
		out.println("<button>Place order</button>");
		out.println("</form>");
		out.println(" </td></tr> ");
		out.println(" </table> ");
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

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		List<FoodEntry> cart = (List<FoodEntry>) getServletContext().getAttribute("cart");

		for (FoodEntry entry : cart) {

			order.add(
					new Order(order.size(), entry, request.getParameter("name"), Order.Status.IN_PROGRESS, new Date()));
			getServletContext().setAttribute("order", order);
		}

		cart.clear();
		response.sendRedirect("/Homework2/orders");
	}
}
