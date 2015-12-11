package Model;

import java.util.ArrayList;

public class Account {

	private Integer id;
	private Customer customer;
	private String number;
	private Double balance;

	public Account() {

	}

	public Account(Integer id, Customer customer, String number, Double balance) {
		this.id = id;
		this.customer = customer;
		this.number = number;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}