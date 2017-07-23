package Lab4;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminFoodList")
public class InventoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodEntry> food_items = (List<FoodEntry>) request.getSession().getAttribute("food_items");
		if (food_items == null) {
			food_items = new ArrayList<>();
			food_items.add(new FoodEntry(food_items.size(), "Alfredo Chicken Penne", "$15.89", "Italian",
					"https://copymethat.blob.core.windows.net/media/one_pan_chicken_alfredo_20170111021020782111lot1cj.jpg"));
			food_items.add(new FoodEntry(food_items.size(), "Caesar Salad", "$12.08", "Italian",
					"https://dw1ixebl10gex.cloudfront.net/wp-content/uploads/2016/09/Classic-HH-Recipes-Caesar-Salad-HH94-300x200.jpg"));
			food_items.add(new FoodEntry(food_items.size(), "Chicken Fillet With Salsa Sauce", "$11.89", "Italian",
					"http://flavormosaic.com/wp-content/uploads/2014/05/Salsa-Chicken-11-300x200.jpg"));
		}
		String foodName=request.getParameter("Submit");
		
		if(foodName!=null)
		{
			for (Iterator<FoodEntry> new_food_items =food_items.listIterator(); new_food_items.hasNext(); ) {
			    FoodEntry item = new_food_items.next();
			    if (item.getName().equals(foodName)) {
			        new_food_items.remove();
			    }
			}
		}
		
		request.getSession(true).setAttribute("food_items", food_items);
		request.getSession().setAttribute("date", new Date());
		request.getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(request.getContextPath() + "/adminCreatePanel");

	}

	
}