package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Book;

public class ReserveBookDAO extends DataSource
{

    public String reservebookfunction(Integer a, String b, String c) {

        String query = "INSERT INTO sriram_reservation (reservation_Id, reservation_name, reservation_author) VALUES (?, ?, ?) ;";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, a);
            statement.setString(2, b);
            statement.setString(3, c);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            }
         catch(SQLException e){

            System.out.println("Error Reserving Book: " + e);
        }
        return null;
    }
}
