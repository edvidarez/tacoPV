package src.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class AdminVentaController implements javafx.fxml.Initializable{

	
	@FXML
	BorderPane borderPaneAdminVentasMain;
	
	@FXML
	ImageView imageViewAdminVentasPerfil;
	
	@FXML
	Label labelAdminVentasAdministrador;
	
	//@FXML
	//TableView tableViewadminVentasTabla;
	
	@FXML
	MenuItem menuItemAdminVentasEspañol;
	
	@FXML
	MenuItem menuItemAdminVentasIngles;
	
	@FXML
	MenuItem menuItemAdminVentasPesos;
	
	@FXML
	MenuItem menuItemAdminVentasDolares;
	
	@FXML
	MenuItem menuItemAdminVentasAgregarUsuario;
	
	
	@FXML
	MenuItem menuItemAdminVentasModificarUsuario;
	
	
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	

}
