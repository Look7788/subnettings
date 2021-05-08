package com.vetor.been;

public class Fruit {

	private String name;
	private String price;
	private String unit;
	private String count;
	public Fruit(String name, String price, String unit, String count) {
		super();
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.count = count;
	}
	public Fruit() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	/*@Override
	public String toString() {
		return "Fruit [name=" + name + ", price=" + price + ", unit=" + unit + ", count=" + count + "]";
	}*/
	
	
}
