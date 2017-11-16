package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Database;
import application.Main;
import application.Session;
import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ModificarUsuarioController implements Initializable{
	
	private int id;
	private String email;
	private String pass;
	private int role;
	private String rfc;
	private String username;
	
	Stage stage;
	
	@FXML
	ComboBox<User> comboBoxEditarUsuario;
	
	@FXML
	TextField textFieldMUEmail;
	
	@FXML
	TextField textFieldMURFC;
	
	@FXML
	TextField textFieldMUUsername;
	
	@FXML
	TextField textFieldContrasena;
	
	@FXML
	TextField textFieldMURol;
	
	@FXML
	Button botonAceptar;
	
	@FXML
	Button botonEliminar;
	
	@FXML
	Button botonCancelar;
	
	ArrayList<User> users = new ArrayList<>();
	
	/*
	public void getUsers() throws SQLException, ClassNotFoundException {
		//set modify button and textfields to disabled except for the id one		
		if(!id.equals("")&&!id.equals("Ingrese in Id")) {
			Database db = new Database();
			User user = db.getUser(id);
		}
		else {
			//Give the user warning to enter an id
			id = "Ingrese in Id";
		}
		//enable textViews and button
	}*/
	
	public void populateUsers() throws ClassNotFoundException, SQLException {
		Database db = new Database();
		users = db.getUsers();
		ObservableList<User> obsUsers = FXCollections.observableArrayList(users);
		comboBoxEditarUsuario.setItems(obsUsers);
		comboBoxEditarUsuario.setConverter(new StringConverter<User>() {
		    @Override
		    public String toString(User object) {
		        return String.valueOf(object.getUsername());
		    }

		    @Override
		    public User fromString(String string) {
		        return null;
		    }
		});
		comboBoxEditarUsuario.setOnAction((e) -> {
        fillData(comboBoxEditarUsuario.getSelectionModel().getSelectedIndex());
		});
	}
	
	public void fillData(int pos){
		User user = users.get(pos);
		disableElements(false);
		id = user.getId();
		textFieldMUEmail.setText(user.getEmail());
		textFieldMURFC.setText(user.getRfc());
		textFieldMUUsername.setText(user.getUsername());
		textFieldContrasena.setText(user.getPass());
		textFieldMURol.setText(String.valueOf(user.getRole()));
	}
	@FXML
	Label labelRolMenu;
	@FXML
	Label labelNombreusuarioMenu;
	@FXML
	Label labelContrasenaMenu;
	@FXML
	Label labelRfcMenu;
	@FXML
	Label labelEmailMenu;
	@FXML
	Label labelEditarusuarioTitulo;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

			ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
			labelEditarusuarioTitulo.setText(rb.getString("lbl.tieditus"));
			labelEmailMenu.setText(rb.getString("lbl.email"));
			labelContrasenaMenu.setText(rb.getString("lbl.contra"));
			labelRfcMenu.setText(rb.getString("lbl.rft"));
			labelNombreusuarioMenu.setText(rb.getString("lbl.nombus"));
			labelRolMenu.setText(rb.getString("lbl.rl"));
			botonAceptar.setText(rb.getString("btn.acp"));
			botonEliminar.setText(rb.getString("btn.elimus"));
			botonCancelar.setText(rb.getString("btn.canc"));
			
		
		disableElements(true);
		try {
			populateUsers();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disableElements(boolean init) {
		if(init) {
			textFieldMUEmail.setDisable(true);
			textFieldMURFC.setDisable(true);
			textFieldMUUsername.setDisable(true);
			textFieldContrasena.setDisable(true);
			textFieldMURol.setDisable(true);
			botonAceptar.setDisable(true);
			botonEliminar.setDisable(true);
		}else {
			textFieldMUEmail.setDisable(false);
			textFieldMURFC.setDisable(false);
			textFieldMUUsername.setDisable(false);
			textFieldContrasena.setDisable(false);
			textFieldMURol.setDisable(false);
			botonAceptar.setDisable(false);
			botonEliminar.setDisable(false);
		}
	}
	
	public void close() throws ClassNotFoundException, SQLException, IOException {
		Stage stage = (Stage) textFieldContrasena.getScene().getWindow();
		stage.close();
		returnToParent();
	}
	
	public void modifyUser() throws SQLException, ClassNotFoundException, IOException {
		fetchUserInputs();
		if(!email.equals("") && !pass.equals("") && !rfc.equals("") && !username.equals("")){
			Database db = new Database();
			db.updateUser(id, email, pass, role, rfc, username);
		}
		returnToParent();
	}
	
	
	public void deleteUser() throws SQLException, ClassNotFoundException, IOException {
		fetchUserInputs();
		Database db = new Database();
		db.deleteUser(id);
		this.close();
		returnToParent();
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
        Stage stage2 = (Stage) textFieldContrasena.getScene().getWindow();
	    stage2.close();
	}
	
	public void fetchUserInputs() {
		email = textFieldMUEmail.getText();
		pass = textFieldContrasena.getText();
		role = Integer.parseInt(textFieldMURol.getText());
		rfc = textFieldMURFC.getText();
		username = textFieldMUUsername.getText();
	}
	

}
