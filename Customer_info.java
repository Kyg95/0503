package yg;

public class Customer_info {
	private String cust_id;
	private String cust_name;
	private String cust_addr;
	private String cust_reg_date;
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public void setCust_addr(String cust_addr) {
		this.cust_addr = cust_addr;
	}
	public void setCust_reg_date(String cust_reg_date) {
		this.cust_reg_date = cust_reg_date;
	}
	public String getCust_id() {
		return cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public String getCust_addr() {
		return cust_addr;
	}
	public String getCust_reg_date() {
		return cust_reg_date;
	}
}
