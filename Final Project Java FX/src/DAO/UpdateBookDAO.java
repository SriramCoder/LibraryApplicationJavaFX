package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.AutoCloseable;
import Model.Book;
import Model.User;
import javafx.scene.control.TextField;

public class UpdateBookDAO extends DataSource{

	public String updateByName(String name1,String author1,String originalname) {
		String query = "UPDATE sriram_book SET name = ?, author = ? WHERE name = ? ;";
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, name1);
			statement.setString(2, author1);
			statement.setString(3, originalname);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            }
         catch(SQLException e){

            System.out.println("Error Updating Book: " + e);
        }
		return null;
	}

}
