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

		out.println("<head>");
		out.println("<style>body { " + "}</style>");
		out.println("</head>");

		out.println("<h1> ** Admin Food List ** </h1>");
		out.println("<table>");
		for (FoodEntry entry : entries) {
			out.println("<tr style=\"padding:1em;\">" + "<td style=\"padding:1em;\">" + "<h3>Name:</h3>"
					+ entry.getName() + "</td>" + "<td style=\"padding:1em;\">" + "<h3>Pice:</h3>" + entry.getPrice()
					+ "</td>" + "<td style=\"padding:1em;\">" + "<h3>Description:</h3>" + entry.getComment() + "</td>"
					+ "<td style=\"padding:1em;\">" + "<img src=" + entry.getImage() + ">" + "</td>"
					+ "<td style=\"padding:1em; \"><a style=\"padding:1em; text-decoration: none;\" href='/cs3220xstu02/admin/foods/edit?id="
					+ entry.getId() + "'>Edit</a> "
					+ "<a style=\"padding:1em; text-decoration: none; \" href='/cs3220xstu02/admin/foods/delete?id="
					+ entry.getId() + "'>Delete</a></td>" + "</tr>");
		}
		out.println("</table>");
		out.println(
				"<a style=\"padding:1em; text-decoration: none;\" href='/cs3220xstu02/admin/foods/create'>Add Food</a>");
		out.println(
				"<a style=\"padding:1em; text-decoration: none;\" href='/cs3220xstu02/menu'>Add Food to the Original Menu</a>");
	}
}
