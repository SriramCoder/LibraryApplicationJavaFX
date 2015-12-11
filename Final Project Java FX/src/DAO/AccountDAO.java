package DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Accounts2;
import Model.Book;
import Model.Customer;
import Model.User;
public class AccountDAO extends DataSource{

	public List<Accounts2> getAccounts2() {
		List<Accounts2> accounts2 = new ArrayList<>();
		String query = "SELECT * FROM sriram_account;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
            	Accounts2 account2 = new Accounts2();
            	account2.setId(resultSet.getInt(1));
            	account2.setNumber(resultSet.getString(2));
            	account2.setBalance(resultSet.getDouble(3));
            	accounts2.add(account2);
            }
        } catch(SQLException e){
            System.out.println("Error fetching Accounts: " + e);
        }
		return accounts2;
	}
}
