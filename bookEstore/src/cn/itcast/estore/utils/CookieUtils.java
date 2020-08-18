package cn.itcast.estore.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

	// �������Ʋ���ָ����cookie
	public static Cookie findCookieByName(Cookie[] cs, String name) {
		if (cs == null || cs.length == 0) {
			return null;
		}

		for (Cookie c : cs) {
			if (c.getName().equals(name)) {
				return c;
			}
		}

		return null;
	}
}
