package Lab4;
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
		 
		 	List<FoodEntry> food_items = (List<FoodEntry>) request.getSession(false).getAttribute("food_items");
		 	if(food_items==null)
		 		food_items=new ArrayList<FoodEntry>();
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String description = request.getParameter("description");
			String image = request.getParameter("image");		
			food_items.add(new FoodEntry(food_items.size(),name,price,description,image));
			request.setAttribute("date", new Date());
			request.getSession(false).setAttribute("food_items", food_items);
			response.sendRedirect(request.getContextPath()+"/adminFoodList");
	    }
	

}