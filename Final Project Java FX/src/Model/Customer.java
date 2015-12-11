package Model;

import java.util.ArrayList;

public class Customer extends User {

	private Book bank;
	private String firstName;
	private String lastName;
	private ArrayList<Account> accounts;

	public Customer() {

	}

	public Customer(Integer id, String username, String password, Integer admin, Book bank, String firstName, String lastName) {
		super(id, username, password, admin);
		this.bank = bank;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Book getBank() {
		return bank;
	}

	public void setBank(Book bank) {
		this.bank = bank;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public void setAdmin(int b) {
		// TODO Auto-generated method stub

	}

}