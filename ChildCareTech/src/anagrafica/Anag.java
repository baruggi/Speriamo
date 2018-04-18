package anagrafica;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Anag {

	
	public void VediBambini(ActionEvent ev) throws Exception {
		((Node) ev.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/anagrafica/Bambini.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	
	}

	public void initialize(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/anagrafica/Anag.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
		
	}
	public void Back(ActionEvent ev) throws IOException {
		((Node) ev.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/menprinc/Home.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
	}


	
}
