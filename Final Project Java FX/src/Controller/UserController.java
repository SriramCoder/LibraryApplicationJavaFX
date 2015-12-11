package Controller;

import Main.Main;
import DAO.UserDAO;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UserController {

	private static User user;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	@FXML
	private TextField error;

	public void login() {
		User u;
		String username = this.username.getText();
		String password = this.password.getText();

		if(username == null || username.trim().equals("")) {
			error.setText("Username Cannot be empty or spaces");
			return;
		}
		if(password == null || password.trim().equals("")) {
			error.setText("Password Cannot be empty or spaces");
			return;
		}

		UserDAO us = new UserDAO();
		u = us.findByUsername(username);
		us.close();

		if(u == null) {
			error.setText("Username cannot be found");
			return;
		}

		if(!(u.getPassword().equals(password.trim()))) {
			error.setText("Username and Password dont match");
			return;
		}

		user = u;
		try {
			AnchorPane root;
			if(user.roleAdmin()==1) {

				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
			} else {

				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
			}
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Home");
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public static User getUser() {
		return user;
	}

	public static void logout() {
		user = null;
	}
}