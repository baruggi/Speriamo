package mensa;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import client.LoginController;
import database.datalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Mensa implements Initializable{

	@FXML
	private TextField PrimoField;
	
	@FXML
	private TextField SecondoField;
	
	@FXML
	private TextField FruttaField;
	
	@FXML
	private TextField DolceField;
	
	
	@FXML
	private TableView<Menu> table;
	
	@FXML
	private TableColumn<Menu, String> Primo;

	@FXML
	private TableColumn<Menu, String> Secondo;
	
	@FXML
	private TableColumn<Menu, String> Frutta;
	
	@FXML
	private TableColumn<Menu, String> Dolce;
	
	
	
	public ObservableList<Menu> getMensa() throws Exception {
		ObservableList<Menu> Mensa = FXCollections.observableArrayList();
		datalog d = new datalog();
	
		Mensa = d.ListaMense();
		return Mensa;
	}

	@Override
	public void initialize(URL loacation, ResourceBundle resources) {
		Primo.setCellValueFactory(new PropertyValueFactory<Menu,String>("primo"));
		Secondo.setCellValueFactory(new PropertyValueFactory<Menu,String>("secondo"));
		Frutta.setCellValueFactory(new PropertyValueFactory<Menu,String>("frutta"));
		Dolce.setCellValueFactory(new PropertyValueFactory<Menu,String>("dolce"));
		
		try {
			table.setItems(getMensa());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
    public void addButtonClicked() throws Exception{
        datalog d = new datalog();
    	Menu mensa = new Menu();
        mensa.setPrimo(PrimoField.getText());
        mensa.setSecondo(SecondoField.getText());
        mensa.setFrutta(FruttaField.getText());
        mensa.setDolce(DolceField.getText());
        table.getItems().add(mensa);
        d.InsetMenu(PrimoField.getText(), SecondoField.getText(), FruttaField.getText(), DolceField.getText());
        PrimoField.clear();
        SecondoField.clear();
        FruttaField.clear();
        DolceField.clear();
        
    }
    
    
   /* public void deleteButtonClicked(){
        ObservableList<Bimbo> bimboSelezionato, elencobambini;
        elencobambini = table.getItems();
        bimboSelezionato = table.getSelectionModel().getSelectedItems();

        bimboSelezionato.forEach(elencobambini::remove);
    }*/
    public void Back(ActionEvent ev) throws Exception {
		((Node) ev.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/menprinc/Home.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		}
	
	
	
}