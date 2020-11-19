package framework;

import java.time.LocalDate;

public class Customer {
	private String name;
	private String email;
	private Address address;
	private LocalDate birthdate;
	private int numOfEmployee;

	public Customer(String name, String email, Address address, LocalDate birthdate,int numOfEmployee) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.numOfEmployee = numOfEmployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
