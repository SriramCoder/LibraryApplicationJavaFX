package Controller;

import java.util.List;

import Main.Main;
import DAO.CustomerDAO;
import DAO.UpdateBookDAO;
import Model.Account;
import Model.Book;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CustomerController {
	@FXML
	private TextField id;

	@FXML
	private TextField number;

	@FXML
	private TextField balance;

	@FXML
	private TextField numberdelete;

	@FXML
	private TextField balancedelete;

	@FXML
	private TextField originalnumber;

	@FXML
	private TextField updatednumber;

	@FXML
	private TextField updatedbalance;

	private Boolean locked = false;



	public void addAccount() {
		String id = this.id.getText();
		String number = this.number.getText();
		String balance = this.balance.getText();

		if(id == null || id.trim().equals("")) {

			return;
		}
		if(number == null || number.trim().equals("")) {

			return;
		}
		if(balance == null || balance.trim().equals("")) {

			return;
		}
		Integer id1 = Integer.parseInt(id);
		CustomerDAO c1 = new CustomerDAO();
		Customer customer = c1.findByUser(UserController.getUser());
		String d = c1.addAccount(id1, number, balance, customer);
	}

	public void deleteAccount(){
		String numberdelete = this.numberdelete.getText();
		//String balancedelete = this.balancedelete.getText();
		if(numberdelete == null || numberdelete.trim().equals("")) {

			return;
		}
		//if(balancedelete == null || balancedelete.trim().equals("")) {
//
	//		return;
		//}
			CustomerDAO c2 = new CustomerDAO();
			Customer customer = c2.findByUser(UserController.getUser());
			String d1 = c2.deleteAccount(numberdelete, customer);

	}

	public void updateAccount(){
		String originalnumber = this.originalnumber.getText();
		String updatednumber = this.updatednumber.getText();
		String updatedbalance = this.updatedbalance.getText();
		if(originalnumber == null || originalnumber.trim().equals("")) {

			return;
		}
		if(updatednumber == null || updatednumber.trim().equals("")) {

			return;
		}
		if(updatedbalance == null || updatedbalance.trim().equals("")) {

			return;
		}
           Integer originalnumber1 = Integer.parseInt(originalnumber);
           Integer updatednumber1 = Integer.parseInt(updatednumber);
           CustomerDAO c3 = new CustomerDAO();
           Customer customer = c3.findByUser(UserController.getUser());
           String d2 = c3.updateAccount(originalnumber1, updatednumber1, updatedbalance);


	}

	public void viewAccounts() {
		if(locked) {
			return;
		}
		locked = true;
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewAccounts.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("View Accounts");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            	public void handle(WindowEvent we) {
                	locked = false;
                }
            });
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            ViewAccountsController controller = loader.getController();
            List<Account> accounts;
            try(CustomerDAO c = new CustomerDAO()) {
            	Customer customer = c.findByUser(UserController.getUser());
            	accounts = c.getAccounts(customer);
            }
            controller.setData(FXCollections.observableArrayList(accounts));
            dialogStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public void viewBooks() {
		if(locked) {
			return;
		}
		locked = true;
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewBooksCustomer.fxml"));
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
            ViewBooksCustomerController controller = loader.getController();
            List<Book> books;
            try(CustomerDAO c = new CustomerDAO()) {
            	//Customer customer2 = c.findByUser2(LoginController.getUser());
            	books = c.getBooks();
            }
            controller.setData(FXCollections.observableArrayList(books));
            dialogStage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
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