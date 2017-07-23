import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/orders/edit")
public class EditOrderStatusServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		Order leEntry = null;

		for (Order entries : order) {
			if (entries.getId() == id) {
				leEntry = entries;
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../app.css\">");
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
		out.println(" <label class=\"orderLable\">Editing Order Status</label> ");
		out.println("<table class=\"order_status_table center\" id=\"myTable\">");
		out.println(" <thead> ");
		out.println(" <tr>\r\n"
				+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(3)\">Status Update</th>\r\n"
				+ "						<th style=\"line-height: 2em;color: black;border: 3px solid #3a4a34; text-align: center;border-radius: 5px;font-weight: bold;\" class=\"TableHeader\" onclick=\"sortTable(3)\">Action</th>\r\n"
				+ "					</tr> ");
		out.println(" </thead> ");
		out.println(" <tbody> ");
		out.println(" <tr> ");
		out.println("<td><form method=\"post\">");
		out.println("<select id =\"status\" name = \"status\">");
		out.println("<option value =\"IN_QUEUE\" selected>" + "IN_QUEUE" + "</option>");
		out.println("<option value =\"IN_PROGRESS\">" + "IN_PROGRESS" + "</option>");
		out.println("<option value =\"COMPLETED\">" + "COMPLETED" + "</option>");
		out.println("</select>");
		out.println(" <td><button>Edit</button></td>");
		out.println("</form></td>");
		out.println(" </tr> ");
		out.println(" </tbody> ");
		out.println("</table>");
		out.println("</section>");
		out.println("<div style=\"padding-top:75px;\"></div>");
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
		int id = Integer.parseInt(request.getParameter("id"));
		List<Order> order = (List<Order>) getServletContext().getAttribute("order");
		Order leEntry = null;
		int index = -1;
		for (int i = 0; i < order.size(); i++) {
			if (order.get(i).getId() == id) {
				leEntry = order.get(i);
				index = i;
			}
		}
		String inputStat = request.getParameter("status");
		Order.Status stat = Order.Status.valueOf(inputStat);
		leEntry.setStatus(stat);
		order.set(index, new Order(leEntry.getId(), leEntry.getFood(), leEntry.getCustomerName(), leEntry.getStatus(),
				leEntry.getDate()));
		getServletContext().setAttribute("order", order);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.sendRedirect("/Homework2/admin/orders");

	}
}