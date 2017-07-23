import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/admin/foods/" })
public class FoodItemListAdminServlet extends HttpServlet {
	public void init() {
		// init the application scope to have pre-set values
		List<FoodEntry> entries = new ArrayList<>();
		entries.add(new FoodEntry(entries.size(), "Alfredo Chicken Penne", "$15.89", "Italian",
				"https://copymethat.blob.core.windows.net/media/one_pan_chicken_alfredo_20170111021020782111lot1cj.jpg"));
		entries.add(new FoodEntry(entries.size(), "Caesar Salad", "$12.08", "Italian",
				"https://dw1ixebl10gex.cloudfront.net/wp-content/uploads/2016/09/Classic-HH-Recipes-Caesar-Salad-HH94-300x200.jpg"));
		getServletContext().setAttribute("entries", entries);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<FoodEntry> entries = (List<FoodEntry>) getServletContext().getAttribute("entries");
		// tell browser this is html document
		response.setContentType("text/html");
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
		out.println(" <label class=\"orderLable\">Admin Food List</label> ");
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
			out.println("<tr> " + "<td>" + entry.getName() + "</td>" + "<td>" + entry.getPrice() + "</td>" + "<td>"
					+ entry.getComment() + "</td>" + "<td>" + "<img src=" + entry.getImage() + ">" + "</td>"
					+ "<td ><a style=\"padding:1em; text-decoration: none; font-size:0.8em; color: black;\" href='/Homework2/admin/foods/edit?id="
					+ entry.getId() + "'>Edit</a> "
					+ "<a style=\"padding:1em; text-decoration: none; font-size:0.8em; color: black;\" href='/Homework2/admin/foods/delete?id="
					+ entry.getId() + "'>Delete</a></td>" + "</tr>");
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
