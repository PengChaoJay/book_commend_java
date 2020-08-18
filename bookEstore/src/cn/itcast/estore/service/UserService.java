package cn.itcast.estore.service;

import cn.itcast.estore.domain.User;

public interface UserService {
	// ×¢²á²Ù×÷
	public void regist(User user) throws Exception;

	// µÇÂ¼²Ù×÷
	public User login(String username, String password) throws Exception;

	// ¼¤»î²Ù×÷
	public void activeUser(String activecode) throws Exception;
}
