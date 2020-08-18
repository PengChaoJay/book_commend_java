package cn.itcast.estore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.estore.dao.ProductDao;
import cn.itcast.estore.domain.Product;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.PrivilegeException;

public class ProductServiceImpl implements ProductService {

	// �����Ʒ
	public void add(User user, Product product) throws PrivilegeException,
			SQLException {

		ProductDao dao = new ProductDao();

		dao.addProduct(product);

	}

	// ����������Ʒ
	public List<Product> findAll() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findAll();
	}

	// ����id������Ʒ
	public Product findById(String id) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findById(id);
	}

	// �õ����۰���Ϣ
	public List<Product> findSell(User user) throws PrivilegeException,
			SQLException {
		ProductDao dao = new ProductDao();
		return dao.findSell();
	}
}
