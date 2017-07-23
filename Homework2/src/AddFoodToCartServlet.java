import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu/add")
public class AddFoodToCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		List<FoodEntry> cart = (List<FoodEntry>) getServletContext().getAttribute("cart");
		cart.add(new FoodEntry(id, leEntry.getName(), leEntry.getPrice(), leEntry.getComment(),
				leEntry.getImage()

		));

		getServletContext().setAttribute("cart", cart);
		response.sendRedirect("/Homework2/shopping-cart");

	}
}