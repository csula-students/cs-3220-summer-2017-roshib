import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin/foods/edit")
public class EditFoodAdminServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodEntry> entries = (List<FoodEntry>) getServletContext().getAttribute("entries");
		FoodEntry leEntry = null;
		for (FoodEntry entry: entries) {
			if (entry.getId() == id) {
				leEntry = entry;
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
		out.println(" <label class=\"orderLable\">Editing Food Item</label> ");
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
		out.println(" <tr> ");
		out.println("<form method=\"post\">");
		out.println("<td> <input name='name' type='text' value='" + leEntry.getName() + "'/></td>");
		out.println("<td> <input name='price' type='text' value='" + leEntry.getPrice() + "'/></td>");
		out.println("<td><textarea name='comment'>" + leEntry.getComment() + "</textarea></td>");
		out.println("<td> <input name='image' type='text' value='" + leEntry.getImage() + "'/></td>");
		out.println("<td> <button>Edit</button></td>");
		out.println("</form>");
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
		out.println("<p class=\"FooterItemLast\">� 2017 Burrito Cafe. All Rights Reserved</p>");
		out.println("</div>");
		out.println(" </footer> ");
		out.println(" </body> ");
		out.println("</html >");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodEntry> entries = (List<FoodEntry>) getServletContext().getAttribute("entries");
		FoodEntry leEntry = null;
		int index = -1;
		for (int i = 0; i < entries.size(); i ++) {
			if (entries.get(i).getId() == id) {
				leEntry = entries.get(i);
				index = i;
			}
		}
		entries.set(index, new FoodEntry(
			leEntry.getId(),
			request.getParameter("name"),
			request.getParameter("price"),
			request.getParameter("comment"),
			request.getParameter("image")
		));
		getServletContext().setAttribute("entries", entries);

		response.sendRedirect("/Homework2/admin/foods/");
	}
}