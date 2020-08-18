package cn.itcast.estore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.estore.annotation.PrivilegeInfo;
import cn.itcast.estore.domain.Order;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.PrivilegeException;

public interface OrderService {

	// ��Ӷ���
	@PrivilegeInfo("���ɶ���")
	public void add(User user, Order order) throws PrivilegeException,
			Exception;

	// �����û����Ҷ���
	@PrivilegeInfo("�鿴����")
	public List<Order> find(User user) throws PrivilegeException, Exception;

	// ����idɾ������
	public void delete(String id) throws Exception;

	public void updateState(String id) throws Exception;
}
