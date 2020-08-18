package cn.itcast.estore.web.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.print.attribute.standard.Fidelity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.factory.ProductServiceFactory;
import cn.itcast.estore.service.ProductService;
import cn.itcast.estore.service.ProductServiceImpl;
import cn.itcast.estore.utils.PicUtils;
import cn.itcast.estore.utils.UploadUtils;

public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		if ("findById".equals(method)) {
			// ����id������Ʒ
			findById(request, response);
		} else if ("findAll".equals(method) || method == null) {
			// ����ȫ����Ʒ
			findAll(request, response);
		}
	}

	// ����id������Ʒ
	public void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1.�õ�Ҫ��ѯ����Ʒ��id
		String id = request.getParameter("id");

		// 2.����service�в�ѯ����
		try {
			ProductService service = ProductServiceFactory.getInstance();
			Product p = service.findById(id);

			request.setAttribute("p", p);

			request.getRequestDispatcher("/productInfo.jsp").forward(request,
					response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ����������Ʒ
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.��ѯ������Ʒ
			ProductService service = ProductServiceFactory.getInstance();
			List<Product> ps = service.findAll();

			// 2.��ת��page.jspҳ��
			request.setAttribute("ps", ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/page.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
