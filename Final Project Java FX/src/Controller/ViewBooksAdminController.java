package Controller;

import DAO.ReserveBookDAO;
import Model.Book;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ViewBooksAdminController {

	@FXML
	private TableView<Book> books;

	public void setData(ObservableList<Book> books) {
		this.books.setItems(books);
	}

}
