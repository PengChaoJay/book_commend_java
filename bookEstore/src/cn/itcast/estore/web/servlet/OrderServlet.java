package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.estore.domain.Order;
import cn.itcast.estore.domain.OrderItem;
import cn.itcast.estore.domain.Product;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.OrderException;
import cn.itcast.estore.factory.OrderServiceFactory;
import cn.itcast.estore.service.OrderService;
import cn.itcast.estore.service.OrderServiceImpl;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		if ("add".equals(method)) {
			add(request, response);
		} else if ("del".equals(method)) {
			del(request, response);
		} else if ("search".equals(method)) {
			search(request, response);
		}

	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.�����������װ��Order������.
		Order order = new Order();
		// 1.1 ������
		try {
			BeanUtils.populate(order, request.getParameterMap()); // ֻ��װ�˱����ݵ�javaBean����˵��ֻ��receiverinfo
																	// money����
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 1.2 ������� ��ǰ�û�id
		order.setId(UUID.randomUUID().toString());

		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
			return;
		}
		order.setUser_id(user.getId());

		// 1.3 ���������װ��������.
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart"); // �õ����ﳵ
		List<OrderItem> items = new ArrayList<OrderItem>();
		for (Product p : cart.keySet()) {

			OrderItem item = new OrderItem();

			item.setOrder_id(order.getId());
			item.setProduct_id(p.getId());
			item.setBuynum(cart.get(p));

			items.add(item);
		}

		order.setOrderItems(items);

		// 2.����OrderService�з�������������
		OrderService service = OrderServiceFactory.getInstance();

		try {
			service.add(user, order);
			response.getWriter().write(
					"�µ��ɹ�,<a href='" + request.getContextPath()
							+ "/index.jsp'>��������</a>��<a href='"
							+ request.getContextPath()
							+ "/order?method=search'>�鿴����</a>");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id"); // �õ�Ҫɾ���Ķ�����id��

		// ����OrderService��ɾ����������
		OrderService service = OrderServiceFactory.getInstance();

		try {
			service.delete(id);

			// �ڴβ�ѯ����
			response.sendRedirect(request.getContextPath()
					+ "/order?method=search");
			return;
		} catch (OrderException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// 1.�õ���ǰ�û�
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			response.getWriter().write(
					"����<a href='" + request.getContextPath()
							+ "/index.jsp'>��¼</a>");
			return;
		}

		// 2.����OrderService�в�ѯ��������
		OrderService service = OrderServiceFactory.getInstance();

		try {
			List<Order> orders = service.find(user);

			request.setAttribute("orders", orders);

			request.getRequestDispatcher("/showOrder.jsp").forward(request,
					response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
