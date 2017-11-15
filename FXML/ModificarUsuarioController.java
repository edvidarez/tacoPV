package FXML;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Database;
import application.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ModificarUsuarioController implements Initializable{
	
	private int id;
	private String email;
	private String pass;
	private int role;
	private String rfc;
	private String username;
	
	@FXML
	ComboBox<User> comboBoxEditarUsuario;
	
	@FXML
	TextField textFieldMUEmail;
	
	@FXML
	TextField textFieldMURFC;
	
	@FXML
	TextField textFieldMUUsername;
	
	@FXML
	TextField textFieldContrase�a;
	
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
		textFieldContrase�a.setText(user.getPass());
		textFieldMURol.setText(String.valueOf(user.getRole()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
			textFieldMUEmail.setText("");
			textFieldMURFC.setText("");
			textFieldMUUsername.setText("");
			textFieldContrase�a.setText("");
			textFieldMURol.setText("");
			textFieldMUEmail.setDisable(true);
			textFieldMURFC.setDisable(true);
			textFieldMUUsername.setDisable(true);
			textFieldContrase�a.setDisable(true);
			textFieldMURol.setDisable(true);
			botonAceptar.setDisable(true);
			botonEliminar.setDisable(true);
		}else {
			textFieldMUEmail.setDisable(false);
			textFieldMURFC.setDisable(false);
			textFieldMUUsername.setDisable(false);
			textFieldContrase�a.setDisable(false);
			textFieldMURol.setDisable(false);
			botonAceptar.setDisable(false);
			botonEliminar.setDisable(false);
		}
	}
	
	public void close() {
		Stage stage = (Stage) textFieldContrase�a.getScene().getWindow();
		stage.close();
	}
	
	public void modifyUser() throws SQLException, ClassNotFoundException {
		fetchUserInputs();
		if(!email.equals("") && !pass.equals("") && !rfc.equals("") && !username.equals("")){
			Database db = new Database();
			db.updateUser(id, email, pass, role, rfc, username);
			populateUsers();
		}
	}
	
	
	public void deleteUser() throws SQLException, ClassNotFoundException {
		fetchUserInputs();
		Database db = new Database();
		db.deleteUser(id);
		this.close();
	}
	
	public void fetchUserInputs() {
		email = textFieldMUEmail.getText();
		pass = textFieldContrase�a.getText();
		role = Integer.parseInt(textFieldMURol.getText());
		rfc = textFieldMURFC.getText();
		username = textFieldMUUsername.getText();
	}
	
	
}
