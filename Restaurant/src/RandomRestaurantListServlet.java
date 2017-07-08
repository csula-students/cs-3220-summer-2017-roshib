import java.util.List;
import java.util.Random;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/suggest/restaurants/random/list")
public class RandomRestaurantListServlet extends HttpServlet {
	public Restaurant getRandomRestaurant(List<Restaurant> list) {
		return list.get(new Random().nextInt(list.size()));
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Restaurant> entries = (List<Restaurant>) getServletContext().getAttribute("entries");
		// tell browser this is html document
		response.setContentType("text/html");

		out.println("<head>");
		out.println("<style>body { " + "}</style>");
		out.println("</head>");

		out.println("<h1> ** whats for lunch? ** </h1>");
		out.println("<table>");
		for (Restaurant entry : entries) {
			out.println("<tr style=\"padding:1em;\">" + "<td style=\"padding:1em;\">" + "<h3>Name:</h3>"
					+ entry.getName() + "</td>" + "<td style=\"padding:1em;\">" + "<h3>URL:</h3>" + entry.geturl()
					+ "</td>" + "<td style=\"padding:1em;\">" + "<h3>Design Rating:</h3>" + entry.getDesignRatings() + "</td>"
					+ "<td style=\"padding:1em;\">" +"<h3>Taste Rating:</h3>"+ "<img src=" + entry.getTasteRatingse() + ">" + "</td>"
					+ "<td style=\"padding:1em; \"><a style=\"padding:1em; text-decoration: none;\" href='/cs3220xstu02/admin/foods/edit?id="
					+ entry.getId() + "'>Edit</a> "
					+ "<a style=\"padding:1em; text-decoration: none; \" href='/cs3220xstu02/admin/foods/delete?id="
					+ entry.getId() + "'>Delete</a></td>" + "</tr>");
		}
		out.println("</table>");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.sendRedirect("/cs3220xstu02/admin/foods/");
	}
}