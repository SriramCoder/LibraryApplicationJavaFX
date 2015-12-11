package Controller;

import DialogForm.DialogForm;
import DAO.BookDAO;
import Model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddBookController extends DialogForm{

	@FXML
	private TextField name;

	@FXML
	private TextField author;

	@FXML
	private TextField id;

	public void save() {
		String name = this.name.getText();
		String author = this.author.getText();
		String id = this.id.getText();

		if(name == null || name.trim().equals("")) {
			return;
		}
		if(author == null || author.trim().equals("")) {
			return;
		}
		Integer bookid = Integer.parseInt(id);

		Book book = new Book();
		book.setId(bookid);
		book.setName(name);
		book.setAuthor(author);
		try(BookDAO b = new BookDAO()) {
			b.create(book);

		}
		close();
	}

}