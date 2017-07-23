import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/shopping-cart/delete")
public class DeleteFoodItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FoodEntry> cart = (List<FoodEntry>) getServletContext().getAttribute("cart");
		int index = -1;
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getId() == id) {
				index = i;
			}
		}
		cart.remove(index);
		getServletContext().setAttribute("cart", cart);
		response.sendRedirect("/Homework2/shopping-cart");
	}
}