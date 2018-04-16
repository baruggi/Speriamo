package database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;					//gestire eventualità di username uguali
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import anagrafica.Bimbo;
import anagrafica.Contatto;
import database.datalog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class datalog {
	//private final int PORT = 3306;
	private final String URL = "jdbc:mysql://localhost:3306/login";
	private final String usr = "qwerty";
	private final String pass = "12345678";
	private final Connection conn;

	public datalog() throws Exception {
		conn = login();

		System.out.println("Connesso");

	}


	public Connection login() {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(URL,this.usr,this.pass);
			return conn;
		}catch(Exception e) {
			System.out.println(e);
		}
		return null; // in caso di errore non torno nulla
	}


	public boolean Repeat(String usr) {
		//Connection conn = login();
		String r = "SELECT user FROM login.credenziali ";
		try {


			System.out.println("Verifica dei dati in corso...");
			//Thread.sleep(20);
			PreparedStatement state = conn.prepareStatement(r);
			ResultSet res = state.executeQuery();

			ArrayList<String> utente = new ArrayList<String>();
			while(utente.size()>0) {
				int i = 0;
				String[]a=utente.toArray(null);
				System.out.println(a[i]);
				i++;


			}
			while(res.next()) {
				utente.add(res.getString("user"));
				//System.out.println(res.getString("user"));
				//System.out.println(res.getString("password"));
				if(utente.contains(usr)) {

					return true;
				}

			}

		}catch(Exception e) {
			System.err.println(e);
		}
		return false;
	}
	public boolean check(String user,String pw) {
		//Connection conn = login();
		String r = "SELECT user,password FROM login.credenziali WHERE user="+ '"'+user+'"' +" AND password = "+'"'+pw+'"';
		//System.out.println(r);
		try {


			System.out.println("Verifica dei dati in corso...");

			PreparedStatement state = conn.prepareStatement(r);
			ResultSet res = state.executeQuery();
	
				

			ArrayList<String> utente = new ArrayList<String>();
		//	ArrayList<String> codice = new ArrayList<String>();
			while(res.next()) {
				
				utente.add(res.getString("user"));
				//utente.add(res.getString("password"));
				System.out.println(res.getString("user"));
				System.out.println(res.getString("password"));
				if(!utente.isEmpty())
					return true;

			}

		}catch(Exception e) {
			System.err.println(e);
		}
		return false;

	}

	public void Signup(String utente, String codice) {
		try {
			//Connection conn = login();
			String s = "INSERT INTO credenziali (user,password) VALUES ('"+utente+"','"+codice+"') ";
			PreparedStatement state = conn.prepareStatement(s);
			state.executeUpdate();
		}catch(Exception e) {
			System.err.println(e);
		}
		System.out.println("Registrazione avvenuta con successo!!");



	}
	
	
	public ObservableList<Bimbo> ListaBimbi() throws Exception {
		String r = "SELECT * FROM login.bambini";

		PreparedStatement state = conn.prepareStatement(r);
		ResultSet res = state.executeQuery();
		ObservableList<Bimbo> list = FXCollections.observableArrayList();
		while(res.next()) {

			Bimbo bimbo = new Bimbo();
			bimbo.setNome(res.getString("Nome"));
			
			bimbo.setCognome(res.getString("Cognome"));
			
			bimbo.setCf(res.getString("Codicefiscale"));
			bimbo.setLuogoNascita(res.getString("LuogoDiNascita"));
			bimbo.setBirthday(res.getDate("Compleanno").toLocalDate());
		
			list.add(bimbo);
		
			
		}
		return list;	
		
	}

	public void InsetChild(String nome, String cognome, String luogodiNascita, String Cf,LocalDate Compleanno) throws SQLException {
		Cf.toUpperCase();
		String query = "INSERT INTO login.bambini (Nome,Cognome,Codicefiscale,LuogoDiNascita,Compleanno) VALUES ('"+nome+"','"+cognome+"','"+luogodiNascita+"','"+Cf+"','"+Compleanno+"') ";
		java.sql.Date.valueOf( Compleanno );
		PreparedStatement state = conn.prepareStatement(query);
		state.executeUpdate();
		
		
	}


	public void deleteChild(String query) throws Exception {
	PreparedStatement state = conn.prepareStatement(query);
	state.executeUpdate();
		
	}


	public ObservableList<Contatto> getContatti(String cf) throws Exception {
		String r = "SELECT * FROM login.contatti WHERE CodicFisc =" +'"'+cf+'"';

		PreparedStatement state = conn.prepareStatement(r);
		ResultSet res = state.executeQuery();
		ObservableList<Contatto> list = FXCollections.observableArrayList(); 
		while(res.next()) {

			Contatto bimbo = new Contatto();
			bimbo.setNome(res.getString("Nome"));
			
			bimbo.setNumero(res.getString("Numero"));
	
			list.add(bimbo);
		
			
		}
		return list;	
		
	}


	public void insertPed(String nome, String numero, String cf) throws Exception {
		String r = "INSERT INTO login.pediatra (Nome,Numero,Cod) VALUES ('"+nome+"','"+numero+"','"+cf+"') ";
		PreparedStatement state = conn.prepareStatement(r);
		state.executeUpdate();
	}


	
	


	public void insertParent(String nome, String numero, String cf) throws Exception {
		String r = "INSERT INTO login.genitori (Nome,Numero,Figlio) VALUES ('"+nome+"','"+numero+"','"+cf+"') ";
		PreparedStatement state = conn.prepareStatement(r);
		state.executeUpdate();		
	}


	public void InsertCont(String nome, String numero, String cf) throws Exception {
		String r = "INSERT INTO login.contatti (Nome,Numero,Cod) VALUES ('"+nome+"','"+numero+"','"+cf+"') ";
		PreparedStatement state = conn.prepareStatement(r);
		state.executeUpdate();			
	}


	public Contatto getPed(String cf) throws Exception {
		String r = "SELECT * FROM login.pediatra WHERE Cod =" +'"'+cf+'"';

		PreparedStatement state = conn.prepareStatement(r);
		ResultSet res = state.executeQuery();
		Contatto cont = new Contatto();
		cont.setNome(res.getString("nome"));
		cont.setNumero(res.getString("numero"));
		return cont;
	}
	
	/*public void DeleteChild(String nome, String cognome, String luogodiNascita, String Cf) throws SQLException{
		
	}*/

}

