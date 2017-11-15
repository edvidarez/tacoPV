package FXML;

import java.net.URL;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Database;
import application.Session;
import javafx.fxml.Initializable;
import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
public void cancelar() throws IOException {
	// borrar textos
	textboxEmail.setText("");
	textboxContrasena.setText("");
	textboxRfc.setText("");
	textboxNombreusuario.setText("");
	textboxRol.setText("");
	returnToParent();
	
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
	labelAltausuarioTitulo.setText(rb.getString("lbl.titulo"));
	
}


public void returnToParent() throws IOException {
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
    Stage stage2 = (Stage) textboxEmail.getScene().getWindow();
    stage2.close();
}


}
