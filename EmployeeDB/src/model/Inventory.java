package model;

/**
 * The auction class represents a single employee.
 * 
 * @author John Phillips
 * @version 20151015
 *
 */
public class Inventory {

	private int prodctId;
	private String lastName;
	private String firstName;
	private String homePhone;
	private double salary;
	private int auction;
	private int productn;
	
	

	public Inventory() {
		prodctId = 0;
		lastName = "";
		firstName = "";
		homePhone = "";
		salary = 0;
		auction = 0;
		productn = 0; 
	}

	public Inventory(int empId, String lastName, String firstName, String homePhone, double salary, int auction , int productn) {
		this.prodctId = empId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.homePhone = homePhone;
		this.salary = salary;
		this.productn = productn;
		this.auction = auction;
	}
	public int getAuction() {
		return auction;
	}

	public void setAuction(int auction) {
		this.auction = auction;
	}

	public int getProductn() {
		return productn;
	}

	public void setProductn(int productn) {
		this.productn = productn;
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getEmpId() {
		return prodctId;
	}

	public void setEmpId(int empId) {
		this.prodctId = empId;
	}

	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return String.format("%5d : %s, %s, %s, %.2f %d, %d", this.getEmpId(), this.getLastName(),
				this.getFirstName(), this.getHomePhone(), this.getSalary(), this.getAuction(), this.getProductn());
	}
}