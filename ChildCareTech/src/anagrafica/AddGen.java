package anagrafica;

import database.datalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddGen {

	private String cf;
	
	@FXML
	private TextField Nome;

	@FXML
	private TextField Numero;
	
	@FXML
	private TextField Nome2;

	@FXML
	private TextField Numero2;
	
	public void insert(String cf) {
		this.cf = cf;
		
	}
	
	public void InsertParent(ActionEvent ev) throws Exception {
		datalog d = new datalog();
		d.insertParent(Nome.getText(),Numero.getText(),cf);
		d.insertParent(Nome2.getText(),Numero2.getText(),cf);
	}

	public void Cancel(ActionEvent ev) {
		//TODO 
	}

	public void initialize(Stage stage) {
		stage.show();
	}
}
