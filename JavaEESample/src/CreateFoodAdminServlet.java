import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/admin/foods/create")
public class CreateFoodAdminServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1> ** Create new Item ** </h1>");
		out.println("<form method=\"post\">");
		out.println("Name:</br><input name='name' type='text'/></br></br>");
		out.println("Price:</br><input name='price' type='text'/></br></br>");
		out.println("Description:</br><textarea name='comment'></textarea></br></br>");
		out.println("Image:</br><input name='image' type='text'/></br></br>");
		out.println("<button>Add</button>");
		out.println("</form>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodEntry> entries = (List<FoodEntry>) getServletContext().getAttribute("entries");
		entries.add(new FoodEntry(entries.size(), request.getParameter("name"),request.getParameter("price"),request.getParameter("comment"), request.getParameter("image")));
		getServletContext().setAttribute("entries", entries);
		PrintWriter out = response.getWriter();
		response.sendRedirect("/cs3220xstu02/admin/foods/");
	}
}