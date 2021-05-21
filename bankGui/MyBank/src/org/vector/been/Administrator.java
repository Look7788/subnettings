package org.vector.been;

public class Administrator {

	private String A_name;
	private String A_cord;
	
	public Administrator() {
		super();
	}
	
	public String getA_name() {
		return A_name;
	}
	public void setA_name(String a_name) {
		A_name = a_name;
	}
	public String getA_cord() {
		return A_cord;
	}
	public void setA_cord(String a_cord) {
		A_cord = a_cord;
	}

	@Override
	public String toString() {
		return "Administrator [A_name=" + A_name + ", A_cord=" + A_cord + "]";
	}
	
}
