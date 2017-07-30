package homework3;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/customerFoodList" })
public class CustomerMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodEntry> entries_food = (List<FoodEntry>) getServletContext()
				.getAttribute("entries_food");
		
		// add food in cart
		List<FoodEntry> entries_cart = (List<FoodEntry>) getServletContext()
				.getAttribute("entries_cart");

		String addfoodname = (request.getParameter("Submit"));

		FoodEntry addEntry = null;

		for (FoodEntry entries : entries_food) {
			if (entries.getName().equals(addfoodname)) {
				addEntry = entries;
				entries_cart.add(new FoodEntry(addEntry.getId(), addEntry.getName(), addEntry.getPrice(),
						addEntry.getComment(), addEntry.getImage()));
			}
		}
		

		getServletContext().setAttribute("entries_cart", entries_cart);
		
		
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}


}