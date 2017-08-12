package homework4;

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

import homework4.FoodEntry;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/customerFoodList" })
public class CustomerMenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<FoodEntry> entries_cart = (List<FoodEntry>) getServletContext().getAttribute("entries_cart");
		FoodEntryDao foodDao = new FoodEntryDao();

		if (request.getParameter("Submit") != null) {
			int addfoodname = Integer.parseInt(request.getParameter("Submit"));
			FoodEntry addEntry = null;
			for (FoodEntry entries : foodDao.list()) {
				if (entries.getId() == addfoodname) {
					addEntry = entries;
					entries_cart.add(new FoodEntry(addEntry.getId(), addEntry.getName(), addEntry.getPrice(),
							addEntry.getComment(), addEntry.getImage()));
				}
			}
		}
		getServletContext().setAttribute("entries_cart", entries_cart);
		request.setAttribute("list", foodDao.list());
		request.getRequestDispatcher("/WEB-INF/homework4/index.jsp").forward(request, response);
	}
}