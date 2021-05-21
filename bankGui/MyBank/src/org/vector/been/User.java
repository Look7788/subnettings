package org.vector.been;

public class User {

	private String name;
	private String cord;  //密码
	private String id;  //身份证号
	private String number;  //银行卡号
	private String payment; //支付密码
	private String phone;   //手机号
	private double balance;  //余额
	private String acount;   //账号
	
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCord() {
		return cord;
	}
	public void setCord(String cord) {
		this.cord = cord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", cord=" + cord + ", id=" + id + ", number=" + number + ", payment=" + payment
				+ ", phone=" + phone + ", balance=" + balance + ",acount="+ acount +"]";
	}
	
}
