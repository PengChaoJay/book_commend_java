package cn.itcast.estore.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 工具类 提供数据库连接池 和数据库连接
 * 
 * 
 * 
 */
public class DataSourceUtils {

	private static DataSource dataSource = new ComboPooledDataSource();

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		Connection con = tl.get();

		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}

		return con;
	}

	// 开启事务
	public static void startTransaction() throws SQLException {

		getConnection().setAutoCommit(false); //设置事务为手动事务，相当于开启事务

	}

	// 事务回滚
	public static void rollback() throws SQLException {
		getConnection().rollback();
	}

	// 事务提交
	public static void commitAndReleased() throws SQLException {

		getConnection().commit(); // 事务提交
		getConnection().close();// 释放connection，是将其放回到连接池.
		tl.remove();
	}

}
