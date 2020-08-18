package cn.itcast.estore.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.itcast.estore.annotation.PrivilegeInfo;
import cn.itcast.estore.dao.PrivilegeDao;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.PrivilegeException;
import cn.itcast.estore.service.OrderService;
import cn.itcast.estore.service.OrderServiceImpl;
import cn.itcast.estore.utils.DataSourceUtils;

public class OrderServiceFactory {
	private static OrderService service = new OrderServiceImpl();

	public static OrderService getInstance() {

		return (OrderService) Proxy.newProxyInstance(service.getClass()
				.getClassLoader(), service.getClass().getInterfaces(),
				new InvocationHandler() {

					public Object invoke(Object proxy, Method method,
							Object[] args) throws IllegalAccessException,
							IllegalArgumentException, InvocationTargetException {
						// 1.�ж��Ƿ���ע��
						if (method.isAnnotationPresent(PrivilegeInfo.class)) {
							// 2���õ�ע�����
							PrivilegeInfo pif = method
									.getAnnotation(PrivilegeInfo.class);
							// 3.�õ�Ȩ������
							String pname = pif.value();

							// 4.�õ��û�
							User user = (User) args[0];

							if (user == null) {
								throw new PrivilegeException();
							}
							// 5.�����û���ѯ�Ƿ����Ȩ��
							PrivilegeDao pdao = new PrivilegeDao();
							if (!pdao.checkPrivilege(user.getRole(), pname)) {
								throw new PrivilegeException(); // �׳�Ȩ�޲����쳣

							}

						}

						return method.invoke(service, args);

					}
				});

	}
}
