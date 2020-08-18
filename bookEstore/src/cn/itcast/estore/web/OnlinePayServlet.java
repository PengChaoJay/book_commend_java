package cn.itcast.estore.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.utils.PaymentUtil;

public class OnlinePayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.��Ҫ�ύ�����ݵõ�
		// ��� ֧�������������
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		// ����
		String pd_FrpId = request.getParameter("pd_FrpId");

		// ����֧����˾��Ҫ��Щ����
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString(
				"p1_MerId");
		String p2_Order = orderid;
		String p3_Amt = money;
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// ֧���ɹ��ص���ַ ---- ������֧����˾����ʡ��û�����
		// ������֧�����Է�����ַ
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString(
				"responseURL");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";

		// ����hmac ��Ҫ��Կ
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);

		// 2.�������ύ��ָ����·��

		// response.sendRedirect("https://www.yeepay.com/app-merchant-proxy/node?p0_Cmd="+p0_Cmd+"&p1_MerId="+p1_MerId);

		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);

		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
