package homework3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(loadOnStartup = 1, urlPatterns = { "/customerOrderStatus" })
public class CustomerOrderStatusServlet extends HttpServlet{
	public void init() {

		List<Order> entries_order = new ArrayList<>();
		getServletContext().setAttribute("entries_order", entries_order);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> entries_order = (List<Order>) getServletContext().getAttribute("entries_order");

		// send food menu data to index.jsp
		request.getRequestDispatcher("WEB-INF/statuses.jsp").forward(request, response);
	}
}
