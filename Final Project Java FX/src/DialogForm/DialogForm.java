package DialogForm;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class DialogForm {
	
	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public abstract void save();
	
	public void cancel() {
		close();
	}
	
	protected void close() {
		dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
