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
		out.println("<h1>** Editing Food Item **</h1>");
		out.println("<form method=\"post\">");
		out.println("Name:</br> <input name='name' type='text' value='" + leEntry.getName() + "'/></br></br>");
		out.println("Price:</br> <input name='price' type='text' value='" + leEntry.getPrice() + "'/></br></br>");
		out.println("Description:</br><textarea name='comment'>" + leEntry.getComment() + "</textarea></br></br>");
		out.println("Image:</br> <input name='image' type='text' value='" + leEntry.getImage() + "'/></br></br>");
		out.println("<button>Edit</button>");
		out.println("</form>");
		
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

		response.sendRedirect("/cs3220xstu02/admin/foods/");
	}
}