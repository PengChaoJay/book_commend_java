package cn.itcast.estore.service;

import cn.itcast.estore.domain.User;

public interface UserService {
	// ע�����
	public void regist(User user) throws Exception;

	// ��¼����
	public User login(String username, String password) throws Exception;

	// �������
	public void activeUser(String activecode) throws Exception;
}
