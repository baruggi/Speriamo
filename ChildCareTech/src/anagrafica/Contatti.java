package anagrafica;

import java.net.URL;
import java.util.ResourceBundle;

import database.datalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Contatti implements Initializable {

	@FXML
	private Label NomePediatra;

	@FXML
	private Label NumeroPediatra;
	

	@FXML
	private TextField Nome;
	

	@FXML
	private TextField Numero;
	
	@FXML
	private TableView<Contatto> table;
	

	@FXML
	private TableColumn<Contatto, String> NomeCont;
	
	@FXML
	private TableColumn<Contatto, String> NumCont;
	
	private String cf;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		NomeCont.setCellValueFactory(new PropertyValueFactory<Contatto,String>("Nome"));
		NumCont.setCellValueFactory(new PropertyValueFactory<Contatto,String>("Numero"));
		try {
			table.setItems(getCont());
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		try {
			System.out.println("9");
			datalog d = new datalog();
			Contatto c = new Contatto();
			c = d.getPed(cf);
			System.out.println("10");
			NomePediatra.setText(c.getNome());
			System.out.println(c.getNome());
			NumeroPediatra.setText(c.getNumero());
			System.out.println(c.getNumero());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ObservableList<Contatto> getCont() throws Exception {
		ObservableList<Contatto> list = FXCollections.observableArrayList();
		datalog d = new datalog();
		list = d.getContatti(cf);
		//devo controllare se la lista è vuota e nel caso la inizializzo con il cf
		//asdas
		//dafadf
		
		//sas djdvn
		
		return list;
	}

	public void SeeCont(String cf) {
		this.cf = cf;
		
	}
	public void AddCont(ActionEvent ev) throws Exception {
	     	datalog d = new datalog();
	    	Contatto bimbo = new Contatto();
	        bimbo.setNome(Nome.getText());
	        bimbo.setNumero(Numero.getText());

	        table.getItems().add(bimbo);
	        d.InsertCont(Nome.getText(), Numero.getText(),cf);
	}

	public void initialize(Stage stage) {
		stage.show();
	}
	
}
