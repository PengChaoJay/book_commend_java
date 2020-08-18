package cn.itcast.estore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.estore.dao.OrderDao;
import cn.itcast.estore.dao.OrderItemDao;
import cn.itcast.estore.dao.ProductDao;
import cn.itcast.estore.domain.Order;
import cn.itcast.estore.domain.OrderItem;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.OrderException;
import cn.itcast.estore.exception.PrivilegeException;
import cn.itcast.estore.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {
	// ��Ӷ���
	public void add(User user, Order order) throws PrivilegeException {
		OrderDao dao = new OrderDao();
		OrderItemDao idao = new OrderItemDao();
		ProductDao pdao = new ProductDao();

		try {

			// ��������
			DataSourceUtils.startTransaction();
			// 1.��orders�����������
			dao.createOrder(order);
			// 2.��orderitem�����������
			idao.addOrderItem(order);
			// 3.�޸�products��������
			pdao.updateProductCount(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback(); // ����ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.commitAndReleased(); // �����ύ���ͷ�
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// �����û����Ҷ���
	public List<Order> find(User user) throws PrivilegeException, SQLException {

		List<Order> orders = new OrderDao().findOrder(user); // ��ѯ������Ϣ,�����������е���Ʒ��Ϣ

		OrderItemDao idao = new OrderItemDao();

		// ���ݵõ��Ķ�������ѯ��������Ʒ��Ϣ.
		for (Order order : orders) {

			List<OrderItem> items = idao.findOrderItemByOrderId(order);

			order.setOrderItems(items);

		}

		return orders;
	}

	// ����idɾ������
	public void delete(String id) throws OrderException {

		OrderDao dao = new OrderDao();
		OrderItemDao idao = new OrderItemDao();
		ProductDao pdao = new ProductDao();
		// 1.�޸���Ʒ������Ʒ����

		try {
			DataSourceUtils.startTransaction(); //��������
			// 1.1 �õ���Ʒ������
			List<OrderItem> items = idao.findOrderItemByOrderId(id);
			// 1.2�޸���Ʒ������
			pdao.updateProductCount(items);
			// 2.ɾ��������
			idao.delOrderItem(id);
			// 3.ɾ������
			dao.delOrder(id);

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				DataSourceUtils.rollback(); //����ع�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			throw new OrderException("ɾ������ʧ��");

		} finally {
			try {
				DataSourceUtils.commitAndReleased(); //�����ύ
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// ���ݶ������޸Ķ���״̬
	public void updateState(String id) throws SQLException {
		OrderDao dao = new OrderDao();

		dao.updateState(id);
	}

}
