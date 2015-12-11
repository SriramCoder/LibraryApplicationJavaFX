package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.AccountDAO;
import DAO.BookDAO;
import DAO.CustomerDAO;
import DAO.DeleteBookDAO;
import DAO.UserDAO;
import DAO.UpdateBookDAO;
import Main.Main;
import Model.Account;
import Model.Accounts2;
import Model.Book;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminController {

	private static String d;
    private static String up;
	@FXML
	private TextField name1;

	@FXML
	private TextField author1;

	@FXML
	private TextField name2;

	@FXML
	private TextField author2;

	@FXML
	private TextField originalname;

	private Boolean locked = false;

	public void createBook() {
		if(locked) {
			return;
		}
		locked = true;
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddBook.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            	public void handle(WindowEvent we) {
                	locked = false;
                }
            });
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            AddBookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public void viewBook(){
		if(locked) {
			return;
		}
		locked = true;
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewBooksAdmin.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("View Books");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            	public void handle(WindowEvent we) {
                	locked = false;
                }
            });
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ViewBooksAdminController controller = loader.getController();
            List<Book> books = new ArrayList<>();
            try(CustomerDAO c = new CustomerDAO()) {
            	//Customer customer2 = c.findByUser2(LoginController.getUser());
            	books = c.getBooks();
            	//System.out.println("These r book fetched " + books.toString());
            }
            controller.setData(FXCollections.observableArrayList(books));
            dialogStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}

	public void viewAccounts() {
		if(locked) {
			return;
		}
		locked = true;
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewAccountsAdmin.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("View Accounts for Admin");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            	public void handle(WindowEvent we) {
                	locked = false;
                }
            });
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ViewAccountsAdminController controller = loader.getController();
            List<Accounts2> accounts2;
            try(AccountDAO c1 = new AccountDAO()) {
            	//Customer customer = c.findByUser(UserController.getUser());
            	accounts2 = c1.getAccounts2();
            }
            controller.setData(FXCollections.observableArrayList(accounts2));
            dialogStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}


	public void updateBook(){

		String name1 = this.name1.getText();
		String author1 = this.author1.getText();
		String originalname = this.originalname.getText();

		if(name1 == null || name1.trim().equals("")) {

			return;
		}
		if(author1 == null || author1.trim().equals("")) {

			return;
		}

		UpdateBookDAO up1 = new UpdateBookDAO();
		up = up1.updateByName(name1,author1,originalname);
		up1.close();
	}

	public void deleteBook(){

		String name2 = this.name2.getText();
		String author2 = this.author2.getText();

		if(name2 == null || name2.trim().equals("")) {

			return;
		}
		if(author2 == null || author2.trim().equals("")) {

			return;
		}

		DeleteBookDAO d1 = new DeleteBookDAO();
		d = d1.deleteByName(name2,author2);
		d1.close();
	}

	public void logout() {
		try {
			UserController.logout();
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/User.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
}
