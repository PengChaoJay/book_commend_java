package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.ActiveUserException;
import cn.itcast.estore.exception.LoginException;
import cn.itcast.estore.exception.RegistException;
import cn.itcast.estore.service.UserServiceImpl;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ��������method,�жϵ�ǰ��ʲô����
		String method = request.getParameter("method");

		if ("login".equals(method)) { // ��¼����̨
			login(request, response);
		} else if ("regist".equals(method)) { // ע�����
			regist(request, response);
		} else if ("logout".equals(method)) {
			// ע������
			logout(request, response);
		} else if ("activeuser".equals(method)) {
			activecode(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// �����û�����
	public void activecode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1.�õ�������
		String activecode = request.getParameter("activecode");

		// 2.����UserService�еķ�����ɼ������
		UserServiceImpl service = new UserServiceImpl();

		try {
			service.activeUser(activecode);
		} catch (RegistException e) {
			e.printStackTrace();

			response.getWriter().write(
					e.getMessage() + ",����<a href='" + request.getContextPath()
							+ "/regist.jsp'>ע��</a>");
			return;

		} catch (ActiveUserException e) {
			e.printStackTrace();
			response.getWriter().write(
					e.getMessage() + ",����<a href='" + request.getContextPath()
							+ "/regist.jsp'>ע��</a>");
			return;
		}

		response.getWriter().write(
				"�û�����ɹ�,��<a href='" + request.getContextPath()
						+ "/index.jsp'>����ҳ</a>");
		return;
	}

	// ע������
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().invalidate(); // ����session

		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");

		response.addCookie(cookie);

		response.sendRedirect(request.getContextPath() + "/index.jsp");

	}

	// ��¼����
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.�õ��û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 2.����service�е�¼����
		UserServiceImpl service = new UserServiceImpl();
		try {
			User user = service.login(username, password);

			if (user != null) {

				// �û������¼�ɹ����ж��Ƿ�ѡ�˼�ס�û���.
				String saveUsername = request.getParameter("remember");

				if ("on".equals(saveUsername)) {
					// ��ס�û���
					Cookie cookie = new Cookie("saveusername",
							URLEncoder.encode(username, "utf-8")); // �洢utf-8��
					cookie.setMaxAge(7 * 24 * 60 * 60); // ���Լ�ס7��
					cookie.setPath("/");
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("saveusername",
							URLEncoder.encode(username, "utf-8")); // �洢utf-8��
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}

				// �Զ���¼
				String autologin = request.getParameter("autologin");

				if ("on".equals(autologin)) {
					// ��ѡ���Զ���¼���ͽ��û���������洢��cookie��.
					Cookie cookie = new Cookie("autologin", URLEncoder.encode(
							username, "utf-8") + "%itcast%" + password);
					cookie.setMaxAge(7 * 24 * 60 * 60);
					cookie.setPath("/");
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("autologin", URLEncoder.encode(
							username, "utf-8") + "%itcast%" + password);
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}

				// ��¼�ɹ��󣬽��û��洢��session��.
				request.getSession().invalidate();
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			} else {
				request.setAttribute("login.message", "�û������������");
				request.getRequestDispatcher("/page.jsp").forward(request,
						response);
				return;
			}

		} catch (LoginException e) {
			e.printStackTrace();
			request.setAttribute("login.message", e.getMessage());
			request.getRequestDispatcher("/page.jsp")
					.forward(request, response);
			return;
		}
	}

	// ע�����
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��֤�����
		String checkcode = request.getParameter("checkcode");

		String _checkcode = (String) request.getSession().getAttribute(
				"checkcode_session");

		request.getSession().removeAttribute("checkcode_session");
		if (_checkcode == null || (!_checkcode.equals(checkcode))) {
			request.setAttribute("regist.message", "��֤�벻��ȷ");
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}

		// 1.�õ������������װ��javaBean��.
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// ���з�����������У��
		Map<String, String> map = user.validateRegist();

		if (map.size() > 0) {
			// ˵���д�����Ϣ
			request.setAttribute("map", map);
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}

		// �ֶ���װһ��������
		user.setActivecode(UUID.randomUUID().toString());

		// 2.����service����

		UserServiceImpl service = new UserServiceImpl();

		try {
			service.regist(user);

			response.getWriter().write(
					"ע��ɹ����������<a href='" + request.getContextPath()
							+ "/index.jsp'>��¼</a>");

		} catch (RegistException e) {
			request.setAttribute("regist.message", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
			return;
		}

	}

}
