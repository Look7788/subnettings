package org.vector.been;

public class Transfer {

	private String transfer_people;
	private String transfer_object;
	private double transfer_money;
	private String transfer_data;
	private double money;
	
	public Transfer() {
		super();
	}

	public Transfer(String transfer_people, String transfer_object, double transfer_money, String transfer_data,
			double money) {
		super();
		this.transfer_people = transfer_people;
		this.transfer_object = transfer_object;
		this.transfer_money = transfer_money;
		this.transfer_data = transfer_data;
		this.money = money;
	}
	
	public String getTransfer_people() {
		return transfer_people;
	}
	public void setTransfer_people(String transfer_people) {
		this.transfer_people = transfer_people;
	}
	public String getTransfer_object() {
		return transfer_object;
	}
	public void setTransfer_object(String transfer_object) {
		this.transfer_object = transfer_object;
	}
	
	public double getTransfer_money() {
		return transfer_money;
	}

	public void setTransfer_money(double transfer_money) {
		this.transfer_money = transfer_money;
	}

	public String getTransfer_data() {
		return transfer_data;
	}
	public void setTransfer_data(String transfer_data) {
		this.transfer_data = transfer_data;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Transfer [transfer_people=" + transfer_people + ", transfer_object=" + transfer_object
				+ ", transfer_money=" + transfer_money + ", transfer_data=" + transfer_data + ", money=" + money + "]";
	}
	
}

