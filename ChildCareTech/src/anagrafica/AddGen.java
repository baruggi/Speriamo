package anagrafica;

import java.io.IOException;

import database.datalog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class AddGen {
	private Stage window;

	private String cf;
	
	@FXML
	private TextField Nome;

	@FXML
	private TextField Numero;
	
	@FXML
	private TextField Nome2;

	@FXML
	private TextField Numero2;
	

	
	public void InsertParent(ActionEvent ev) throws Exception {
		datalog d = new datalog();
		d.insertParent(Nome.getText(),Numero.getText(),cf);
		d.insertParent(Nome2.getText(),Numero2.getText(),cf);
		((Node) ev.getSource()).getScene().getWindow().hide();
	}

	public void Cancel(ActionEvent ev) throws Exception {
		
		System.out.println(cf);
		String Query =  "DELETE FROM login.bambini WHERE Codicefiscale = '"+cf+"'";
		datalog d = new datalog();
		d.deleteChild(Query);
		((Node) ev.getSource()).getScene().getWindow().hide();
	}

	public void init(String cf2, Stage window) {
		this.cf = cf2;
		this.window = window;
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				System.out.println(cf);
				String Query =  "DELETE FROM login.bambini WHERE Codicefiscale = '"+cf+"'";
				datalog d;
				try {
					d = new datalog();
					d.deleteChild(Query);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("/anagrafica/Bambini.fxml"));
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
	}
	
}
