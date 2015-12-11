package Controller;

import Model.Account;
import Model.Accounts2;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ViewAccountsAdminController {

	@FXML
	private TableView<Accounts2> accounts2;

	public void setData(ObservableList<Accounts2> observableList) {
		this.accounts2.setItems(observableList);
	}
}