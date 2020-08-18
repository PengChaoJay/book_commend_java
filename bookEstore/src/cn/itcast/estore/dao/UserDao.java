package cn.itcast.estore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.utils.DataSourceUtils;
import cn.itcast.estore.utils.Md5Utils;

public class UserDao {

	// ����û�����
	public void addUser(User user) throws SQLException {

		// 1.����QueryRunner
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		// 2.ִ��sql���
		//Ĭ���û�role=user  state=0 ����δ����
		String sql = "insert into users values(null,?,?,?,?,'user','0',?,null)";

		runner.update(sql, user.getUsername(),
				Md5Utils.md5(user.getPassword()), user.getNickname(),
				user.getEmail(), user.getActivecode());

	}

	public User findUserByActiveCode(String activecode) throws SQLException {

		// 1.����QueryRunner
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		// 2.ִ��sql���

		String sql = "select * from users where activecode=?";

		return runner.query(sql, new BeanHandler<User>(User.class), activecode);

	}

	public void activeUserByActivecode(String activecode) throws SQLException {

		// 1.����QueryRunner
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		// 2.ִ��sql���
		String sql = "update users set state=1 where activecode=?";

		runner.update(sql, activecode);
	}

	public User findUserByUserNameAndPassword(String username, String password) throws SQLException {
		// 1.����QueryRunner
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="select * from users where username=? and password=?";
		
		return runner.query(sql, new BeanHandler<User>(User.class),username,Md5Utils.md5(password));
	}
}
