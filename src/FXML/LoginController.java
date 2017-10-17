package FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;


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
	SplitMenuButton loginUserMenu;
	@FXML
	MenuItem TypeAdm;
	@FXML
	MenuItem TypeGer;
	@FXML
	MenuItem TypeEmp;
	@FXML
	Hyperlink loginForgPass;
	@FXML
	Label loginLabel;

	@FXML
	private void login_Btn(){
		System.out.println("Pressed button");
		checkEmployee(); // esto va ver si es valido y ejecutara lo necesario para cambiar de stage
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
		
	}
	private void checkEmployee() { // buscar en BD para ver que el usuario y pass y rol existen y concue
		// checar base de datos
		//String rol = (Select e.Rol from Employees e where e.).toString(); Algo asi
		//String user = (Select e.CodigoUsuario from Employees e where e.nombre = userLoginText.getText()).toString(); Algo asi
		//String pass = (Select e.Password from Employees e where pass = userLoginPass.getText()).toString(); Algo asi
		// if(rol.equals(Rol)):
		//ejecutar btnLogin(user,pass);
		//else userLoginText.setText("Incorrect user or pass");
		
	}
	private void btnLogin(String user, String pass) { // hacer validacion de datos del usuario
		if(userLoginText.getText().equals(user) && userLoginPass.getText().equals(pass)) {
			//hide LoginStage
			//Show userStages
		}else {
			userLoginText.setText("Incorrect user or pass");
			userLoginPass.setText("");
		}
	}
	
	

}
