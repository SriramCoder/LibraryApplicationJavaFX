package Controller;

import DAO.ReserveBookDAO;
import Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewBooksCustomerController {

	@FXML
	private TableView<Book> books;
	private static String rb1;
	//private static String Id;
	//private static String name;
	//private static String author;

	public void setData(ObservableList<Book> books) {
		this.books.setItems(books);
	}
	public void reserveBook(){
		Boolean locked = false;
		try{ObservableList<Book> bs;
		bs = books.getSelectionModel().getSelectedItems();
		Integer a= bs.get(0).getId();
		String b= bs.get(0).getName();
		String c= bs.get(0).getAuthor();
		//System.out.println("This is bs toint "+bs.get(0).getId());
		//System.out.println("This is bs tostring "+bs.get(0).getName());
		//System.out.println("This is bs tostring2 "+bs.get(0).getAuthor());

		ReserveBookDAO rb = new ReserveBookDAO();
		rb1 = rb.reservebookfunction(a, b, c);
		rb.close();
		AnchorPane root;
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/BookReserved.fxml"));
		Scene scene = new Scene(root);
		Stage dialogStage = new Stage();
        dialogStage.setTitle("Books Reserved");
        dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(scene);
		dialogStage.show();
		}catch(Exception e){
			System.out.println("Error reserving book " + e);
		}
	}
}

