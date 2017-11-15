package FXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import application.Database;
import application.Main;
import application.Manager;
import application.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class LoginController implements Initializable {
	
//fx:id
	@FXML
	TextField userLoginText;
	@FXML
	TextField userLoginPass;
	@FXML
	Button loginBtn;
	@FXML
	ComboBox<String> comboType;
	@FXML
	Hyperlink loginForgPass;
	@FXML
	Label loginLabel;
	@FXML
	MenuItem espTxt;
	@FXML
	MenuItem engTxt;
	
	@FXML
	public void engText(){
		String resourcesLocation = "i18n.mensaje_en";
		Locale locale = new Locale("EN");
		Session.resourcesLocation = resourcesLocation;
		Session.locale = locale;
		ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
		loginLabel.setText(rb.getString("lbl.login"));
	}
	@FXML
	public void espText(){
		String resourcesLocation = "i18n.mensajes";
		
		Locale locale = new Locale("ES");
		Session.resourcesLocation = resourcesLocation;
		Session.locale = locale;
		ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
		loginLabel.setText(rb.getString("lbl.login"));
		
	}

	@FXML
	private void login_Btn(){
		checkEmployee(userLoginText.getText(),userLoginPass.getText(),comboType.getSelectionModel().getSelectedItem()); // esto va ver si es valido y ejecutara lo necesario para cambiar de stage
		Session s = Session.getInstance();
		s.describeUser();
		try {
			if(s.getRole_()==1) { //admin
				FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(Main.class.getResource("../FXML/AdminVentas.fxml"));
	            
	            BorderPane root1 = (BorderPane) fxmlLoader.load();
	            Stage stage = new Stage();
	            //stage.initModality(Modality.WINDOW_MODAL);
	            //stage.initStyle(StageStyle.DECORATED);
	            stage.setTitle("Admin");
	            stage.setScene(new Scene(root1)); 
	            stage.setResizable(false);
	            stage.show();
	            Stage stage2 = (Stage) loginBtn.getScene().getWindow();
	    	    stage2.close();
			}
			else
			if(s.getRole_() ==3){ //empleado
			
				
	            s.startClient();
	            s.cliente.getMesas();
	            FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(Main.class.getResource("../FXML/ManageTableEmployee.fxml"));
	            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
	            Stage stage = new Stage();
	            //stage.initModality(Modality.WINDOW_MODAL);
	            //stage.initStyle(StageStyle.DECORATED);
	            stage.setTitle("Productos");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	            Stage stage2 = (Stage) loginBtn.getScene().getWindow();
	    	    stage2.close();
			}
			else
			if(s.getRole_() == 2){ 
				System.out.println("Gerente");
				Manager m = Manager.getInstance();
				m.startServer();
				Thread t = new Thread() {
					public void run(){
						m.server.startServer();
					}
				};
				t.start();
				Thread t2 = new Thread() {
					public void run(){
						m.server.listenForChanges();
					}
				};
				t2.start();
				FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(Main.class.getResource("../FXML/ManageTables.fxml"));
	            
	            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
	            Stage stage = new Stage();
	            //stage.initModality(Modality.WINDOW_MODAL);
	            //stage.initStyle(StageStyle.DECORATED);
	            stage.setTitle("Productos");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	           // s.startClient();
	            Stage stage2 = (Stage) loginBtn.getScene().getWindow();
	    	    stage2.close();
			}
			else
			if(s.getRole_() == 2){ // gerente
				
				FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(Main.class.getResource("../FXML/GerenciaMenu.fxml"));
	            
	            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
	            Stage stage = new Stage();
	            //stage.initModality(Modality.WINDOW_MODAL);
	            //stage.initStyle(StageStyle.DECORATED);
	            stage.setTitle("Manager");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	            Stage stage2 = (Stage) loginBtn.getScene().getWindow();
	    	    stage2.close();
			}
            
	       
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Admin",
			        "Gerente",
			        "Empleado"
			    );
		comboType.getItems().addAll(options);
		
	}
	private void createSession(String user,String role,int uid) {
		int role_ = -1;
		switch (role) {
		case "Admin": role_ = 1;
		break;
		case "Gerente": role_ = 2;
		break;
		case "Empleado": role_ = 3;
		break;
		}
		Session session = Session.getInstance();
		session.setRole(role);
		session.setRole_(role_);
		session.setUser(user);
		session.setId(uid);
	}
	private void checkEmployee(String user,String pass,String role) { // buscar en BD para ver que el usuario y pass y rol existen y concue
		try {
			Database d = new Database();
			int uid = d.validateUser(user,pass,role);
			if(uid!=0)
			{
				System.out.println("Valido");
				createSession(user,role,uid);
			}
			else
			{
				System.out.println("Invalido");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private void btnLogin(String user, String pass) { // hacer validacion de datos del usuario
		if(userLoginText.getText().equals(user) && userLoginPass.getText().equals(pass)) {
			//hide LoginStage
			//Show userStages
			System.out.println(userLoginText.getText().equals(user)+" "+userLoginText.getText().equals(pass));
		}else {
			userLoginText.setText("Incorrect user or pass");
			userLoginPass.setText("");
		}
	}
	
	

}
