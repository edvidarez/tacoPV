package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class AdminVentaController implements javafx.fxml.Initializable{

	
	@FXML
	BorderPane borderPaneAdminVentasMain;	
	@FXML
	MenuBar menuBarAdmin;
	@FXML
	Menu menuUsuarios;
	@FXML
	MenuItem addUser;
	@FXML
	Label labelEmailMenu;
	@FXML
	MenuItem menuItemModifyUser;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	public void openAddUser() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../FXML/AltaUsuario.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Alta de Usuario");
            stage.setScene(new Scene(root1));  
            stage.show();   
            //loader.setLocation(Main.class.getResource("../FXML/AltaUsuario.fxml"));
            
		}catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			
		}
	}
	
	public void openModifyItem(){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../FXML/EditarUsuario.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Modificar usuarios");
            stage.setScene(new Scene(root1));  
            stage.show();   
            
		}catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			
		}
	}
	

}
