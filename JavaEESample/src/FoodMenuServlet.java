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
		out.println("<head>");
		out.println("<style>body { " + "}</style>");
		out.println("</head>");

		out.println("<h1> ** Menu **</h1>");
		out.println("<table>");
		for (FoodEntry entry : entries) {
			out.println("<tr style=\"padding:1em;\">" + "<td style=\"padding:1em;\">" + "<h3>Name:</h3>"
					+ entry.getName() + "</td>" + "<td style=\"padding:1em;\">" + "<h3>Pice:</h3>" + entry.getPrice()
					+ "</td>" + "<td style=\"padding:1em;\">" + "<h3>Description:</h3>" + entry.getComment() + "</td>"
					+ "<td style=\"padding:1em;\">" + "<img src=" + entry.getImage() + ">" + "</td>" + "</tr>");
		}
		out.println("</table>");
	}
}
