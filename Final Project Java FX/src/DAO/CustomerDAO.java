package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Model.Book;
import Model.Customer;
import Model.User;

public class CustomerDAO extends DataSource {

	public Customer findByUser(User user) {
		Customer customer = null;
		String query = "SELECT * FROM sriram_customer WHERE user_Id = ?;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	customer = new Customer();
            	customer.setId(resultSet.getInt(1));
            	customer.setAdmin(1);
            	customer.setFirstName(resultSet.getString(3));
            	customer.setLastName(resultSet.getString(4));
            }
        } catch(SQLException e){
            System.out.println("Error fetching Customer: " + e);
        }
		return customer;
	}

	public List<Account> getAccounts(Customer customer) {
		List<Account> accounts = new ArrayList<>();
		String query = "SELECT * FROM sriram_account WHERE customer_Id = ?;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, customer.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	Account account = new Account();
            	account.setId(resultSet.getInt(1));
            	account.setNumber(resultSet.getString(2));
            	account.setBalance(resultSet.getDouble(3));
            	accounts.add(account);
            }
        } catch(SQLException e){
            System.out.println("Error fetching Accounts: " + e);
        }
		return accounts;
	}








	public Customer findByUser2(User user) {
		Customer customer = null;
		String query = "SELECT * FROM sriram_customer WHERE user_Id = ?;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	customer = new Customer();
            	customer.setId(resultSet.getInt(1));
            	customer.setAdmin(1);
            	customer.setFirstName(resultSet.getString(2));
            	customer.setLastName(resultSet.getString(3));
            }
        } catch(SQLException e){
            System.out.println("Error fetching Customer: " + e);
        }
		return customer;
	}

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM sriram_book";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            //statement.setInt(1, customer.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	Book book = new Book();
            	book.setId(resultSet.getInt(1));
            	book.setName(resultSet.getString(2));
            	book.setAuthor(resultSet.getString(3));
            	books.add(book);
            }
        } catch(SQLException e){
            System.out.println("Error fetching Books: " + e);
        }
		return books;
	}

	public String addAccount(Integer id1, String number, String balance, Customer customer){
		String query = "INSERT INTO sriram_account (account_Id, Number, Balance, customer_Id) VALUES (?, ?, ?, ?);";
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(4, customer.getId());
            statement.setInt(1, id1);
            statement.setString(2, number);
            statement.setString(3, balance);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            }

	catch(SQLException e){
            System.out.println("Error adding account: " + e);
	}
	return null;
	}

	public String deleteAccount(String numberdelete, Customer customer){
		String query = "DELETE FROM sriram_account WHERE (Number = ? AND customer_Id = ?);";
		try(PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, numberdelete);
			statement.setInt(2, customer.getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            resultSet = statement.getGeneratedKeys();
            }
         catch(SQLException e){

            System.out.println("Error Deleting Account: " + e);
        }
		return null;


	}
	public String updateAccount(Integer originalno, Integer updatedno, String updatedbalance){
		String query = "UPDATE sriram_account SET Number = ?, Balance = ? WHERE Number = ? ;";
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, updatedno);
			statement.setString(2, updatedbalance);
			statement.setInt(3, originalno);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            }
         catch(SQLException e){

            System.out.println("Error Updating Account: " + e);
        }
		return null;
	}

	}























