package cn.itcast.estore.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * �û���
 * 
 */
public class User implements Serializable{
	private int id; // �û����
	private String username; // �û���
	private String password; // ����
	private String nickname; // �ǳ�
	private String email; // ����
	private String role; // ��ɫ
	private int state; // �Ƿ񼤻�
	private String activecode; // ������ UUID��ȡ
	private Timestamp updatetime; // ����ʱ��

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getActivecode() {
		return activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Map<String, String> validateRegist() {
		Map<String, String> map = new HashMap<String, String>();

		if (username == null || username.trim().isEmpty()) {
			map.put("username.message", "�û�������Ϊ��");
		}

		if (password == null || password.trim().isEmpty()) {
			map.put("password.message", "���벻��Ϊ��");
		}

		if (nickname == null || nickname.trim().isEmpty()) {
			map.put("nickname.message", "�ǳƲ���Ϊ��");
		}

		if (email == null || email.trim().isEmpty()) {
			map.put("email.message", "���䲻��Ϊ��");
		}

		return map;
	}

}
