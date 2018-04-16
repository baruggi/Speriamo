package anagrafica;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import database.datalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
	
	
    public void addButtonClicked() throws Exception{
        datalog d = new datalog();
    	Bimbo bimbo = new Bimbo();
        bimbo.setNome(NameField.getText());
        bimbo.setCognome(SurnameField.getText());
        bimbo.setCf(CfField.getText().toUpperCase());
        bimbo.setLuogoNascita(BirthField.getText());
        bimbo.setBirthday(BirthdayField.getValue());
        table.getItems().add(bimbo);
        d.InsetChild(NameField.getText(), SurnameField.getText(), BirthField.getText(), CfField.getText(),BirthdayField.getValue());
        NameField.clear();
        SurnameField.clear();
        CfField.clear();
        BirthField.clear();
        d.insertPed(Pediatra.getText(),NumeroPediatra.getText(),CfField.getText());
        
    }
    
    
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
    	Parent root = FXMLLoader.load(getClass().getResource("/anagrafica/Contatti.fxml"));
    	Contatti cont = new Contatti();
        Bimbo Bimb;
        Bimb = table.getSelectionModel().getSelectedItem();
    	cont.SeeCont(Bimb.getCf());
    	Scene scene = new Scene(root);
    	Stage stage= new Stage();
    	stage.setScene(scene);
    	
    }
	
	
	
}
