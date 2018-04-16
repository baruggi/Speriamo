package client;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*BorderPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,604.0,367.0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Child Care Tech");
			primaryStage.setScene(scene);*/
			LoginController C = new LoginController();
			C.initialize(primaryStage);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
