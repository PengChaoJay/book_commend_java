package cn.itcast.estore.service;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cn.itcast.estore.dao.UserDao;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.exception.ActiveUserException;
import cn.itcast.estore.exception.LoginException;
import cn.itcast.estore.exception.RegistException;
import cn.itcast.estore.utils.MailUtils;

public class UserServiceImpl implements UserService{

	// ע�Ṧ��
	public void regist(User user) throws RegistException {

		// ����dao������û������ķ���

		try {
			new UserDao().addUser(user);

			// �����ʼ�����
			String emailMsg = "ע��ɹ�������12Сʱ��<a href='http://www.bookEstore.com/user?method=activeuser&activecode="
					+ user.getActivecode()
					+ "'>����</a>����������"
					+ user.getActivecode();
			MailUtils.sendMail(user.getEmail(), emailMsg);

		} catch (SQLException e) {
			throw new RegistException("ע��ʧ��");
		} catch (Exception e) {
			throw new RegistException("�ʼ�����ʧ��");
		}
	}

	public void activeUser(String activecode) throws RegistException,
			ActiveUserException {

		// ����һ��dao�еķ���������activecode�����û�
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.findUserByActiveCode(activecode);
		} catch (SQLException e) {
			throw new RegistException("���ݼ���������û�ʧ��");
		}
		long time = System.currentTimeMillis() - user.getUpdatetime().getTime();

		// �ж����Ƿ�ʱ ������12Сʱ������ 1����
		if (time > 12 * 60 * 60 * 1000) {
			throw new ActiveUserException("���������");
		}

		// ���м������
		try {
			dao.activeUserByActivecode(activecode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("����ʧ��");
		}

	}

	public User login(String username, String password) throws LoginException {
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.findUserByUserNameAndPassword(username, password);

			if (user == null) {
				throw new LoginException("�û��������벻��ȷ");
			}
			// �ж��û���״̬��
			if (user.getState() == 0) {
				// �û�״̬δ������ܽ��е�¼����
				throw new LoginException("�û�δ����");
			}

		} catch (SQLException e) {
			throw new LoginException("��¼ʧ��");
		}

		return user;
	}
}
