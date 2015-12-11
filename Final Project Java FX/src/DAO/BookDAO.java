package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Book;

public class BookDAO extends DataSource {

	public Book create(Book book) {
		String query = "INSERT INTO sriram_book (book_Id, name, author) VALUES (?, ?, ?) ;";
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

        } catch(SQLException e){
        	book = null;
            System.out.println("Error Adding Book" + e);
        }
		return book;
	}

}