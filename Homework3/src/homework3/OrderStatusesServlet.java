package homework3;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminOrderStatus")
public class OrderStatusesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Order> entries_order = (List<Order>) getServletContext().getAttribute("entries_order");

		request.getRequestDispatcher("WEB-INF/orderStatuses.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Order> entries_order = (List<Order>) getServletContext().getAttribute("entries_order");
		String editFood = (request.getParameter("Edit"));
		//System.out.println(editFood);
		Order leEntry = null;
		int index = -1;
		for (int i = 0; i < entries_order.size(); i++) {
			if (entries_order.get(i).getFood().getName().equals(editFood)) {
				leEntry = entries_order.get(i);
				index = i;
			}
		}
		String inputStat = request.getParameter("status");
		Order.Status stat = Order.Status.valueOf(inputStat);
		leEntry.setStatus(stat);
		entries_order.set(index, new Order(leEntry.getId(), leEntry.getFood(), leEntry.getCustomerName(),
				leEntry.getStatus(), leEntry.getDate()));

		getServletContext().setAttribute("entries_order", entries_order);

		request.getRequestDispatcher("WEB-INF/orderStatuses.jsp").forward(request, response);
	}
}