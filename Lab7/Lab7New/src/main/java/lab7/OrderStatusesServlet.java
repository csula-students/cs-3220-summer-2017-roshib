package lab7;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
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

		OrderDao dao = new OrderDao();
		request.setAttribute("list", dao.list());
		request.getRequestDispatcher("/WEB-INF/lab7/orderStatuses.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDao dao = new OrderDao();
		if (request.getParameter("Edit") != null) {
			int editFood = Integer.parseInt(request.getParameter("Edit"));
			System.out.println(editFood);
			Order orderToUpdate = new Order(dao.get(editFood).get().getId(), dao.get(editFood).get().getFood(),
					dao.get(editFood).get().getCustomerName(), Order.Status.valueOf(request.getParameter("status")),
					dao.get(editFood).get().getDate());
			dao.update(orderToUpdate);
		}
		
		//request.getRequestDispatcher("/WEB-INF/lab7/orderStatuses.jsp").forward(request, response);
		response.sendRedirect("/adminOrderStatus");
	}
}