package homework3;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.Iterator;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/customerCart" })
public class CustomerOrderServlet extends HttpServlet {
	public void init() {

		List<FoodEntry> entries_cart = new ArrayList<>();
		getServletContext().setAttribute("entries_cart", entries_cart);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<FoodEntry> entries_cart = (List<FoodEntry>) getServletContext().getAttribute("entries_cart");
		String deleteFood = (request.getParameter("Delete"));
		int index = -1;
		for (int i = 0; i < entries_cart.size(); i++) {
			if (entries_cart.get(i).getName().equals(deleteFood)) {
				index = i;
				entries_cart.remove(index);
			}
		}

		getServletContext().setAttribute("entries_cart", entries_cart);
		request.getRequestDispatcher("WEB-INF/order.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Order> entries_order = (List<Order>) getServletContext().getAttribute("entries_order");
		List<FoodEntry> entries_cart = (List<FoodEntry>) getServletContext().getAttribute("entries_cart");

		for (FoodEntry entry : entries_cart) {

			entries_order.add(new Order(entries_order.size(), entry, request.getParameter("name"),
					Order.Status.IN_QUEUE, new Date()));
			getServletContext().setAttribute("entries_order", entries_order);

		}

		entries_cart.clear();
		request.getRequestDispatcher("WEB-INF/statuses.jsp").forward(request, response);

	}
}
