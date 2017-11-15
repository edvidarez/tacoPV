package FXML;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Database;
import application.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AltaUsuarioController implements Initializable{
@FXML
Label labelAltausuarioTitulo;
@FXML
Label labelContraMenu;
@FXML
Label labelContrasenaMenu;
@FXML
Label labelRfcMenu;
@FXML
Label labelNombreusuarioMenu;
@FXML
Label labelRolMenu;
@FXML
TextField textboxEmail;
@FXML
TextField textboxContrasena;
@FXML
TextField textboxRfc;
@FXML
TextField textboxNombreusuario;
@FXML
TextField textboxRol;
@FXML
Button botonAceptar;
@FXML
Button botonCancelar;

@FXML
public void insert_user() throws ClassNotFoundException, SQLException { // insert to db a tabla users
	String email = textboxEmail.getText().toString();
	String pass = textboxContrasena.getText().toString();
	String rfc = textboxRfc.getText().toString();
	String nombre = textboxNombreusuario.getText().toString();
	String rol = textboxRol.getText().toString();
	int r = Integer.parseInt(rol);
	if(!pass.equals("")&& !nombre.equals("")&& !nombre.equals("Ingrese Nombre de Usuario")&&!pass.equals("Ingrese una password")) {
		Database db = new Database();
		db.insertUser(email, pass, rfc, nombre, r);
	}
	else {
		textboxNombreusuario.setText("Ingrese Nombre de Usuario");
		textboxContrasena.setText("Ingrese una password");
	}
	
}

@FXML
public void cancelar() {
	// borrar textos
	textboxEmail.setText("");
	textboxContrasena.setText("");
	textboxRfc.setText("");
	textboxNombreusuario.setText("");
	textboxRol.setText("");
	
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
	labelAltausuarioTitulo.setText(rb.getString("lbl.titulo"));
	
}


}