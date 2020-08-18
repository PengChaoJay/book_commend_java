package cn.itcast.estore.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.PrivilegeException;
import cn.itcast.estore.factory.ProductServiceFactory;
import cn.itcast.estore.service.ProductService;
import cn.itcast.estore.service.ProductServiceImpl;
import cn.itcast.estore.utils.PicUtils;
import cn.itcast.estore.utils.UploadUtils;

public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.��װ���ݵ�javaBean--�Ƚ����ݷ�װ��map������.
		Map<String, String[]> map = new HashMap<String, String[]>();

		// 2.����DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024 * 5); // ���û�������СΪ5m
		factory.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));// ������ʱ�ļ��洢λ��

		// 3.����ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);

		// �ж��Ƿ����ϴ�����
		if (upload.isMultipartContent(request)) {
			upload.setHeaderEncoding("utf-8");// ����ϴ��ļ�����������

			// 4.�õ����е�FileItem
			try {
				List<FileItem> items = upload.parseRequest(request);

				// 5.����items
				for (FileItem item : items) {
					// �ж��Ƿ����ϴ���
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String value = item.getString("utf-8");

						map.put(fieldName, new String[] { value }); // ��װ���ϴ��������Ϣ��map
					} else {
						// ���ϴ����
						String fileName = item.getName(); // �õ��ϴ��ļ����� ע�⣺���԰���·��.
						// c:/a/a.txt a.txt
						// �õ���ʵ����
						fileName = UploadUtils.subFileName(fileName); // a.txt

						// �õ��������
						String uuidFileName = UploadUtils
								.generateRandonFileName(fileName);

						// �õ����Ŀ¼
						String randomDir = UploadUtils
								.generateRandomDir(uuidFileName);

						String path = this.getServletContext().getRealPath(
								"/upload" + randomDir);

						File pathFile = new File(path);

						if (!pathFile.exists()) { // Ŀ¼�����ڣ�����
							pathFile.mkdirs();
						}

						// �õ�һ��imgurl
						String imgurl = "/upload" + randomDir + "/"
								+ uuidFileName;

						map.put("imgurl", new String[] { imgurl }); // ��װ�ϴ�ͼƬ��·��.

						try {
							item.write(new File(pathFile, uuidFileName)); // �ļ��ϴ�����
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			// ��װ��Ʒ��id
			map.put("id", new String[] { UUID.randomUUID().toString() });

			Product product = new Product();

			try {
				BeanUtils.populate(product, map); // ��װ���ݵ�Product����.
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			// ��һ�����ܣ��õ���Ʒ����ͼ
			PicUtils putils = new PicUtils(this.getServletContext()
					.getRealPath(product.getImgurl()));
			putils.resize(200, 200);

			// ����ProductService�е������Ʒ����.

			ProductService service = ProductServiceFactory.getInstance();

			try {
				User user = (User) request.getSession().getAttribute("user");
				service.add(user, product);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (PrivilegeException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath()
						+ "/error/error.jsp");
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
