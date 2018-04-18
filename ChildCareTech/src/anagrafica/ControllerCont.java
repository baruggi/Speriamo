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

public class ControllerCont implements Initializable{
	@FXML
	private Label NomePed;
	
	@FXML
	private Label numcheck;
	
	@FXML
	private Label NumPed;
	
	@FXML
	private TableView<Contatto> table;
	
	@FXML
	private TableColumn<Contatto, String> Nome;
	
	@FXML
	private TableColumn<Contatto,String> Numero;
	
	private String cf,nome,numero;
	
	@FXML
	private TextField NomCont;
	
	@FXML
	private TextField NumCont;
	
	@FXML
	private Label Bimbo;
	
	public void init(String cf) throws Exception {
		this.cf = cf;
		setPed();
	}

	private void setPed() throws Exception  {
		datalog d;
		try {
			d = new datalog();
			Contatto cont = new Contatto();
			cont = d.getPed(cf);
			this.nome=cont.getNome();
			this.numero=cont.getNumero();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.nome);
		NomePed.setText(nome);
		NumPed.setText(numero);
		try {
			table.setItems(getContatti());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datalog c = new datalog();
		Bimbo Bambino = new Bimbo();
		Bambino = c.getBamb(cf);
		System.out.println(Bambino.getNome()+"1111");
		Bimbo.setText(Bambino.getNome()+ " "+Bambino.getCognome());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Nome.setCellValueFactory(new PropertyValueFactory<Contatto,String>("Nome"));
		Numero.setCellValueFactory(new PropertyValueFactory<Contatto,String>("Numero"));
	
	}

	private ObservableList<Contatto> getContatti() throws Exception {
		datalog d = new datalog();
		ObservableList<Contatto> contatti = FXCollections.observableArrayList();
		contatti = d.getContatti(cf);
		System.out.println("appa");
		return contatti;
	}
	
	public void addCont(ActionEvent ev) throws Exception {
		if(NumCont.getText().length()==10) {	
			datalog f = new datalog();
			System.out.println("");
			f.InsertCont(NomCont.getText(), NumCont.getText(), cf);
			Contatto newCont = new Contatto();
			newCont.setNome(NomCont.getText());
			newCont.setNumero(NumCont.getText());
			table.getItems().add(newCont);
			NumCont.clear();
			NomCont.clear();
		}else
		{
			numcheck.setText("Numero incorretto");
		}
		}

}
