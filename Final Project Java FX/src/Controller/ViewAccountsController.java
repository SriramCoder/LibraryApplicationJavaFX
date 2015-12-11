package Controller;

import Model.Account;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ViewAccountsController {

	@FXML
	private TableView<Account> accounts;

	public void setData(ObservableList<Account> accounts) {
		this.accounts.setItems(accounts);
	}
}