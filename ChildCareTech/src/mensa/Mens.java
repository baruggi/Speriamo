package mensa;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mens {
	
	public void VediMenu(ActionEvent ev) throws Exception {
		((Node) ev.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/mensa/MensaInserimento.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	
	}

}
