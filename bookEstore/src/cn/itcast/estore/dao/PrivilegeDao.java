package cn.itcast.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.itcast.estore.utils.DataSourceUtils;

public class PrivilegeDao {

	// role ��ɫ����
	// pname Ȩ������
	public boolean checkPrivilege(String role, String pname) {
		String sql = "SELECT privileges.name FROM PRIVILEGES,role,userprivilege WHERE privileges.id=userprivilege.privilege_id AND role.role=userprivilege.role AND role.role=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> pnames = null;
		try {
			pnames = runner.query(sql, new ColumnListHandler(), role);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("��ǰ�û���ɫ��:" + role + "  ��Ҫ��Ȩ����:" + pname);
		System.out.println("��ǰ��ɫ���е�Ȩ����:" + pnames);
		return (pnames.contains(pname));
	}
}
