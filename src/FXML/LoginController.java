package FXML;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Database;
import application.Producto;
import application.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController implements Initializable {
	private boolean AdminEnable = false;
	private boolean GerenteEnable = false;
	private boolean EmployeeEnable = false;
	private String Rol = "";
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
	private void login_Btn(){
		/*
		System.out.println("Pressed button");
		System.out.println(userLoginText.getText());
		System.out.println(userLoginPass.getText());
		System.out.println(comboType.getSelectionModel().getSelectedItem());
		*/
		checkEmployee(userLoginText.getText(),userLoginPass.getText(),comboType.getSelectionModel().getSelectedItem()); // esto va ver si es valido y ejecutara lo necesario para cambiar de stage
		Session s = Session.getInstance();
		s.describeUser();getClass();
		Database d;
		try {
			d = new Database();
			ArrayList<Producto> productos = d.fetchProductos();
			
			
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));  
            FlowPane flowPaneProductsDisplay = (FlowPane) root1.lookup("#flowPaneProductsDisplay"); 
            for(Producto p : productos){
				//System.out.println(p.descripcion);
				Button b = new Button(p.descripcion);
				b.setWrapText(true);
				b.setTextAlignment(TextAlignment.CENTER);
				b.setPrefHeight(74);
				b.setPrefWidth(120);
				
				flowPaneProductsDisplay.getChildren().add(b);
			}
            stage.show();
	       // System.out.println();
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	@FXML
	private void AdminEnabler(){
		AdminEnable = true;
		GerenteEnable = false;
		EmployeeEnable = false;
		Rol = "Administrador";
	}
	@FXML
	private void GerenteEnabler(){
		AdminEnable = false;
		GerenteEnable = true;
		EmployeeEnable = false;
		Rol = "Gerente";
	}
	@FXML
	private void EmployeeEnabler(){
		AdminEnable = false;
		GerenteEnable = false;
		EmployeeEnable = true;
		Rol = "Empleado";
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
	private void createSession(String user,String role) {
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
	}
	private void checkEmployee(String user,String pass,String role) { // buscar en BD para ver que el usuario y pass y rol existen y concue
		try {
			Database d = new Database();
			if(d.validateUser(user,pass,role))
			{
				System.out.println("Valido");
				createSession(user,role);
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
