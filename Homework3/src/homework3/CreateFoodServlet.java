package homework3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/adminCreatePanel"})
public class CreateFoodServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/create-food.jsp").forward(request, response);
		 
	       
	 	}
	
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 List<FoodEntry> entries_food = (List<FoodEntry>) getServletContext()
					.getAttribute("entries_food");
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String image = request.getParameter("image");

			entries_food
					.add(new FoodEntry(entries_food.size(), name, price, description, image));

			request.setAttribute("entries_food", entries_food);

			response.sendRedirect(request.getContextPath() + "/adminFoodList");
	    }
}
	

