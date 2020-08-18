package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.annotation.PrivilegeInfo;
import cn.itcast.estore.domain.Product;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.PrivilegeException;

public interface ProductService {

	// �����Ʒ
	@PrivilegeInfo("�����Ʒ")
	public void add(User user, Product product) throws PrivilegeException,
			Exception;

	// ������Ʒ
	public List<Product> findAll() throws Exception;

	// ����id������Ʒ
	public Product findById(String id) throws Exception;

	// ��ѯ���۰�
	@PrivilegeInfo("���ذ�")
	public List<Product> findSell(User user) throws PrivilegeException,
			Exception;
}
