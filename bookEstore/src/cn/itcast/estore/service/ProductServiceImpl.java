package cn.itcast.estore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.estore.dao.ProductDao;
import cn.itcast.estore.domain.Product;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.PrivilegeException;

public class ProductServiceImpl implements ProductService {

	// 添加商品
	public void add(User user, Product product) throws PrivilegeException,
			SQLException {

		ProductDao dao = new ProductDao();

		dao.addProduct(product);

	}

	// 查找所有商品
	public List<Product> findAll() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findAll();
	}

	// 根据id查找商品
	public Product findById(String id) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findById(id);
	}

	// 得到销售榜单信息
	public List<Product> findSell(User user) throws PrivilegeException,
			SQLException {
		ProductDao dao = new ProductDao();
		return dao.findSell();
	}
}
