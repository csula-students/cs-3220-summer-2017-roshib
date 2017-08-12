package lab7;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminFoodMenu")
public class InventoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FoodEntryDao foodDao = new FoodEntryDao();
		
		if (request.getParameter("Delete") != null) {
			int id = Integer.parseInt(request.getParameter("Delete"));
			foodDao.delete(id);
			}
	
		request.setAttribute("list", foodDao.list());
		request.getRequestDispatcher("/WEB-INF/lab7/inventory.jsp").forward(request, response);
	}

}