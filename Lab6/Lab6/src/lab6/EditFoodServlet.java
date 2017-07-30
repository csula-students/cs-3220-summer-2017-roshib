package lab6;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditFoodServlet
 */
@WebServlet(urlPatterns = { "/adminEditFood" })
public class EditFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		FoodEntryDao foodDao = new FoodEntryDao();
		FoodEntry item = foodDao.get(id).get();
		request.setAttribute("item", item);
		request.getRequestDispatcher("/WEB-INF/jdbc/edit-food.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FoodEntry updatedFood = new FoodEntry(Integer.parseInt(request.getParameter("id")),
				request.getParameter("name"), request.getParameter("price"), request.getParameter("description"),
				request.getParameter("image"));
		FoodEntryDao foodDao = new FoodEntryDao();
		foodDao.update(updatedFood);

		response.sendRedirect("/Lab6/adminFoodMenu");
	}

}
