package cn.itcast.estore.domain;

public class OrderItem {

	private String order_id; // ������
	private String product_id; // ��Ʒ��
	private int buynum; // ��������

	// Ҫ��ѯ��������Ʒ��Ϣʱ�����Խ���Ʒ��Ϣ��װ��OrderItem��.
	private String name;
	private double price;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
