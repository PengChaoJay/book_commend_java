package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.service.ProductServiceImpl;

public class CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");

		if ("add".equals(method)) {// �����Ʒ�����ﳵ
			add(request, response);
		} else if ("remove".equals(method)) { // �ӹ��ﳵɾ����Ʒ
			remove(request, response);
		} else if ("update".equals(method)) {// �޸Ĺ��ﳵ��Ʒ
			update(request, response);
		}
	}

	// �����Ʒ�����ﳵ
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.�õ�Ҫ��ӵ����ﳵ�е���Ʒ��id
		String id = request.getParameter("id");

		// 2.����id������Ʒ
		try {
			Product p = new ProductServiceImpl().findById(id);

			// 3.�õ����ﳵ
			HttpSession session = request.getSession();
			Map<Product, Integer> cart = (Map<Product, Integer>) session
					.getAttribute("cart");

			if (cart == null) {// ���cart�����ڣ�˵���ǵ�һ�ι���.
				cart = new HashMap<Product, Integer>();
			}
			// �жϹ��ﳵ���Ƿ���Ҫ�����Ʒ��

			Integer count = cart.put(p, 1);
			if (count != null) {
				// ˵������
				cart.put(p, count + 1);
			}

			session.setAttribute("cart", cart);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	// �ӹ��ﳵ��ɾ����Ʒ
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		Product p = new Product();
		p.setId(id);

		// �õ����ﳵ
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");

		cart.remove(p);

		if (cart.size() == 0) {
			request.getSession().removeAttribute("cart");
		}

		response.sendRedirect(request.getContextPath() + "/showCart.jsp");
	}

	// �޸Ĺ��ﳵ����Ʒ����
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// �õ�Ҫ�޸ĵ���Ʒ����������Ʒid��
		String id = request.getParameter("id");
		int count = Integer.parseInt(request.getParameter("count"));

		// ����Ҫ�޸ĵ���Ʒ
		Product p = new Product();
		p.setId(id);

		// �õ����ﳵ
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");

		// �޸���Ʒ������
		if (count == 0) {
			cart.remove(p); // ����Ʒ�ӹ��ﳵ���Ƴ�
		} else {
			cart.put(p, count);
		}
		request.getSession().setAttribute("cart", cart);

		response.sendRedirect(request.getContextPath() + "/showCart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
