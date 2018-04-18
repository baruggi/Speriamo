package anagrafica;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import client.LoginController;
import database.datalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Bambini implements Initializable{

	
	@FXML
	private TextField NameField;
	
	@FXML
	private TextField SurnameField;
	
	@FXML
	private TextField BirthField;
	
	@FXML
	private TextField CfField;
	
	@FXML
	private DatePicker BirthdayField;
	
	@FXML
	private TableView<Bimbo> table;
	
	@FXML
	private TableColumn<Bimbo, String> Nome;

	@FXML
	private TableColumn<Bimbo, String> Cognome;
	
	@FXML
	private TableColumn<Bimbo, String> Cf;

	@FXML
	private TableColumn<Bimbo, String> LuogoNascita;

	@FXML
	private TableColumn<Bimbo, LocalDate> Birthday;

	@FXML
	private TextField Pediatra;

	@FXML
	private TextField NumeroPediatra;






	// ora basta prendere i bambini dal database anzichè scriverli a mano
	public ObservableList<Bimbo> getBambini() throws Exception {
		ObservableList<Bimbo> Bambini = FXCollections.observableArrayList();
		datalog d = new datalog();
		/*	Bambini.add(DataBimbo());
		Bambini.add(new Bimbo("Gian","sd","re","ddd",LocalDate.of(1999, 8,23)));	
		Bambini.add(new Bimbo("Lorenzo","Pal","pore","Bisceglie",LocalDate.of(1999, 8,23)));

		 */
		Bambini = d.ListaBimbi();
		return Bambini;
	}

	@Override
	public void initialize(URL loacation, ResourceBundle resources) {
		Nome.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("nome"));
		Cognome.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("cognome"));
		Cf.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("cf"));
		LuogoNascita.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("LuogoNascita"));
		Birthday.setCellValueFactory(new PropertyValueFactory<Bimbo,LocalDate>("Birthday"));
		try {
			table.setItems(getBambini());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@SuppressWarnings("deprecation")
	public void addButtonClicked(ActionEvent ev) throws Exception{
		((Node) ev.getSource()).getScene().getWindow().hide();
		datalog d = new datalog();
		Bimbo bimbo = new Bimbo();
		bimbo.setNome(NameField.getText());
		bimbo.setCognome(SurnameField.getText());
		bimbo.setCf(CfField.getText());
		bimbo.setLuogoNascita(BirthField.getText());
		bimbo.setBirthday(BirthdayField.getValue());
		table.getItems().add(bimbo);
		d.InsertChild(NameField.getText(), SurnameField.getText(), BirthField.getText(), CfField.getText(),BirthdayField.getValue());

		//System.out.println("0");
		d.insertPed(Pediatra.getText(),NumeroPediatra.getText(),CfField.getText());
		//salvo il cf nei contatti per ritrovarlo
		//System.out.println("0.5");
		//insertParents(CfField.getText());
		String cf = CfField.getText();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/anagrafica/AddGen.fxml"));
			Parent parent = (Parent) loader.load();
			
			Scene parentScene = new Scene(parent);
			
			
			//access the controller and call the method
			AddGen controller = loader.getController();
			
			Stage window = new Stage();
			window.setScene(parentScene);
			controller.init(cf,window);
			window.setAlwaysOnTop(true);
			window.showAndWait();
			
		
			
			}catch(Exception e){
					System.err.println(e);
			}

			NameField.clear();
			SurnameField.clear();
			CfField.clear();
			BirthField.clear();
			Pediatra.clear();
			NumeroPediatra.clear();

	}


	/*private void insertParents(String cf) throws IOException {
	
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/anagrafica/AddGen.fxml"));
		Parent parent = (Parent) loader.load();
		
		Scene parentScene = new Scene(parent);
		
		
		//access the controller and call the method
		AddGen controller = loader.getController();
		
		Stage window = new Stage();
		window.setScene(parentScene);
		controller.init(cf,window);
		window.show();
		window.setAlwaysOnTop(true);
		
		
		}catch(Exception e){
				System.err.println(e);
		}

		NameField.clear();
		SurnameField.clear();
		CfField.clear();
		BirthField.clear();
		Pediatra.clear();
		NumeroPediatra.clear();
	

	}*/

	public void deleteButtonClicked() throws Exception{
		String Query,CodiceFiscale;
		datalog d = new datalog();
		ObservableList<Bimbo> bimboSelezionato, elencobambini;
		Bimbo Bimb;
		Bimb = table.getSelectionModel().getSelectedItem();
		CodiceFiscale = Bimb.getCf();
		Query = "DELETE FROM login.bambini WHERE Codicefiscale = '"+CodiceFiscale+"'";
		d.deleteChild(Query);



		//aggiorno la tabella
		elencobambini = table.getItems();
		bimboSelezionato = table.getSelectionModel().getSelectedItems();

		bimboSelezionato.forEach(elencobambini::remove);
	}

	public void VediContatti(ActionEvent ev) throws Exception {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Contatti.fxml"));
		Parent parent = (Parent) loader.load();
		
		Scene parentScene = new Scene(parent);
		
		
		//access the controller and call the method
		ControllerCont controller = loader.getController();
		controller.init(table.getSelectionModel().getSelectedItem().getCf());
		Stage window = new Stage();
		window.setScene(parentScene);
		window.show();
		}catch(Exception e){
				System.err.println(e);
		}

	}
	public void Back(ActionEvent ev) throws Exception {
		((Node) ev.getSource()).getScene().getWindow().hide();
		Anag C = new Anag();
		Stage stage = new Stage();
		C.initialize(stage);
		
	}
	public void refresh(ActionEvent ev) {
		Nome.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("nome"));
		Cognome.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("cognome"));
		Cf.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("cf"));
		LuogoNascita.setCellValueFactory(new PropertyValueFactory<Bimbo,String>("LuogoNascita"));
		Birthday.setCellValueFactory(new PropertyValueFactory<Bimbo,LocalDate>("Birthday"));
		try {
			table.setItems(getBambini());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}




