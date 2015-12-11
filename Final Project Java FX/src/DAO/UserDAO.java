package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User;

public class UserDAO extends DataSource {
		private Connection connection;
	    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	    private String usernam = "fpuser";
	    private String password = "510";

	public User findByUsername(String username) {
				try {
		            connection = DriverManager.getConnection(url, usernam, password);
		        } catch(SQLException e) {
		            System.out.println("Error creating connection to database: " + e);
		            System.exit(-1);
		        }
		User user = null;
		String query = "SELECT * FROM sriram_user WHERE username = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	user = new User(
            			resultSet.getInt("user_Id"),
            			resultSet.getString("UserName"),
            			resultSet.getString("password"),
            			resultSet.getInt("roleAdmin")
            			);
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Username: " + e);
        }
        return user;
    }
}
