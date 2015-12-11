package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.AutoCloseable;
import Model.Book;
import Model.User;


public class DeleteBookDAO extends DataSource {

	public String deleteByName(String name2, String author2) {
		String query = "DELETE FROM sriram_book WHERE name = ?;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, name2);
			//statement.setString(2, author2);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
           //ResultSet resultSet = statement.getGeneratedKeys();
            }
         catch(SQLException e){

            System.out.println("Error Deleting Book: " + e);
        }
		return name2;
	}

}
