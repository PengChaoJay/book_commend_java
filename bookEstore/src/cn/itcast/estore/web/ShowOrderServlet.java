package cn.itcast.estore.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Order;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.factory.OrderServiceFactory;
import cn.itcast.estore.service.OrderService;
import cn.itcast.estore.service.OrderServiceImpl;

public class ShowOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.得到当前用户
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			response.getWriter().write(
					"请先<a href='" + request.getContextPath()
							+ "/index.jsp'>登录</a>");
			return;
		}

		// 2.调用OrderService中查询订单操作
		OrderService service = OrderServiceFactory.getInstance();

		try {
			List<Order> orders = service.find(user);

			request.setAttribute("orders", orders);

			request.getRequestDispatcher("/showOrder.jsp").forward(request,
					response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
