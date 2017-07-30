package homework3;
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

@WebServlet(loadOnStartup = 1, urlPatterns = { "/adminFoodList"})
public class InventoryServlet extends HttpServlet {
	public void init() {
		List<FoodEntry> entries_food = new ArrayList<>();
		entries_food.add(new FoodEntry(entries_food.size(), "Alfredo Chicken Penne", "$15.89", "Italian",
				"https://copymethat.blob.core.windows.net/media/one_pan_chicken_alfredo_20170111021020782111lot1cj.jpg"));
		entries_food.add(new FoodEntry(entries_food.size(), "Caesar Salad", "$12.08", "Italian",
				"https://dw1ixebl10gex.cloudfront.net/wp-content/uploads/2016/09/Classic-HH-Recipes-Caesar-Salad-HH94-300x200.jpg"));
		entries_food.add(new FoodEntry(entries_food.size(), "Pepperoni Stromboli", "$9.89", "Italian",
				"http://samandlouiespizza.com/wp-content/uploads/2016/11/Capture0003-4-1-300x200.jpg"));
		entries_food.add(new FoodEntry(entries_food.size(), "Spaghetti Meatballs", "$15.89", "Italian",
				"https://cmkt-image-prd.global.ssl.fastly.net/0.1.0/ps/376311/300/200/m1/fpc/wm0/biwcxgtxjmeadrrs2cupvh2qg2ilwdap8brdl3ixabtfmp5b53ziym81uwy6q3mz-.jpg?1424951316&s=2e522e44a202bcbc21d732b8b11a7f9b"));
		getServletContext().setAttribute("entries_food", entries_food);

	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<FoodEntry> entries_food = (List<FoodEntry>) getServletContext()
				.getAttribute("entries_food");
		String deletefoodname = (request.getParameter("Submit"));
		int index = -1;
		for (int i = 0; i < entries_food.size(); i++) {
			if (entries_food.get(i).getName().equals(deletefoodname)) {
				index = i;
				entries_food.remove(index);
			}
		}

			
		getServletContext().setAttribute("entries_food", entries_food);
		request.getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

	}
	
}