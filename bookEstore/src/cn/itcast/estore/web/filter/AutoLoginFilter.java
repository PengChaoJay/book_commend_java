package cn.itcast.estore.web.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.LoginException;
import cn.itcast.estore.service.UserServiceImpl;
import cn.itcast.estore.utils.CookieUtils;

public class AutoLoginFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 1.ǿת
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 2.����

		// 2.1�жϵ�ǰ�û��Ƿ��¼
		User user = (User) req.getSession().getAttribute("user");
		if (user == null) { // userΪnull˵���û�û�е�¼�����Խ����Զ���¼����
			// 2.2 �õ����ʵ���Դ·��
			String uri = req.getRequestURI();
			String contextPath = req.getContextPath();
			String path = uri.substring(contextPath.length());

			if (!("/regist.jsp".equalsIgnoreCase(path)
					|| "/login".equalsIgnoreCase(path) || "/regist"
						.equalsIgnoreCase(path))) {
				// �����������ǿ��Խ����Զ���¼������.

				// 2.3 �õ�cookie����cookie�л�ȡusername,password
				Cookie cookie = CookieUtils.findCookieByName(req.getCookies(),
						"autologin");

				if (cookie != null) {
					// ˵�����û��������룬���Խ����Զ���¼
					String username = URLDecoder.decode(cookie.getValue()
							.split("%itcast%")[0], "utf-8");
					String password = cookie.getValue().split("%itcast%")[1];

					// 2.4����UserService�������е�¼����.
					UserServiceImpl service = new UserServiceImpl();
					try {
						User existUser = service.login(username, password);

						if (existUser != null) {
							// ���Խ��е�¼����
							req.getSession().setAttribute("user", existUser);
						}

					} catch (LoginException e) {
						e.printStackTrace();
					}
				}

			}

		}
		// 3.����
		chain.doFilter(req, resp);
	}

	public void destroy() {

	}

}
