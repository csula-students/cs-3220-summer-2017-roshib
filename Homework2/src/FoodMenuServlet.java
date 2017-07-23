import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/menu" })
public class FoodMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodEntry> entries = (List<FoodEntry>) getServletContext().getAttribute("entries");
		// tell browser this is html document
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
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
		out.println(" <label class=\"orderLable\">Menu</label> ");
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
		for (FoodEntry entry : entries) {
			out.println("<tr>" + "<td>" 
					+ entry.getName() + "</td>" + "<td >" + entry.getPrice()
					+ "</td>" + "<td>"  + entry.getComment() + "</td>"
					+ "<td>" + "<img src=" + entry.getImage() + ">" + "</td>"
					+"<td><a style=\"padding:1em; text-decoration: none; color:black; font-size:0.8em;\" href='/Homework2/menu/add?id="
					+ entry.getId() + "'>Add to Cart</a> "
					+ "</tr>");
		}
		out.println(" </tbody> ");
		out.println("</table>");
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
