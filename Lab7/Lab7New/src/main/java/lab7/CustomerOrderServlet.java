package lab7;

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
	OrderDao dao=new OrderDao();
	public void init() {

		List<FoodEntry> entries_cart = new ArrayList<>();
		getServletContext().setAttribute("entries_cart", entries_cart);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<FoodEntry> entries_cart = (List<FoodEntry>) getServletContext().getAttribute("entries_cart");
		
		if(request.getParameter("Delete")!=null){
		int deleteFood = Integer.parseInt(request.getParameter("Delete"));
		int index = -1;
		for (int i = 0; i < entries_cart.size(); i++) {
			if (entries_cart.get(i).getId()==deleteFood) {
				index = i;
				entries_cart.remove(index);
			}
		}
	}
		getServletContext().setAttribute("entries_cart", entries_cart);
		request.getRequestDispatcher("WEB-INF/lab7/order.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Order> entries_order = (List<Order>) getServletContext().getAttribute("entries_order");
		List<FoodEntry> entries_cart = (List<FoodEntry>) getServletContext().getAttribute("entries_cart");
        
		for (FoodEntry entry : entries_cart) {

			int id = dao.list().size();
			String name = request.getParameter("name");
		
			dao.add(new Order(id, entry, name,
					Order.Status.IN_QUEUE, new Date()));
			

		}

		entries_cart.clear();
		request.getRequestDispatcher("WEB-INF/lab7/statuses.jsp").forward(request, response);

	}
}
