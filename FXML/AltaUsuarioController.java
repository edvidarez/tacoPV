package FXML;

import java.sql.SQLException;

import application.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AltaUsuarioController {
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
	Database db = new Database();
	
	//db.insertData(name, age);
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


}
