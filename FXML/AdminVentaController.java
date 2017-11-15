package FXML;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Database;
import application.Main;
import application.User;
import application.UserTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	@FXML
	Button btnAdminExit;
	
	@FXML
	TableView<UserTableView> tableViewAdmin;
	
	@FXML 
	TableColumn<UserTableView, Integer> tableColumnIDAdminVentas;
	
	@FXML 
	TableColumn<UserTableView, String> tableColumnEmailAdminVentas;
	
	@FXML
	TableColumn<UserTableView, Integer> tableColumnRoleAdminVentas;
	
	@FXML 
	TableColumn<UserTableView, String> tableColumnRFCAdminVentas;
	
	@FXML 
	TableColumn<UserTableView, String> tableColumnUsernameAdminVentas;
	
	ObservableList<UserTableView> users;
	
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			populateViews();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		stage.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				try {
					populateViews();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		*/
	}
	
	private void populateViews() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		tableColumnIDAdminVentas.setCellValueFactory(new PropertyValueFactory<UserTableView, Integer>("ID_User"));
		tableColumnEmailAdminVentas.setCellValueFactory(new PropertyValueFactory<UserTableView, String>("email"));
		tableColumnRoleAdminVentas.setCellValueFactory(new PropertyValueFactory<UserTableView, Integer>("role"));
		tableColumnRFCAdminVentas.setCellValueFactory(new PropertyValueFactory<UserTableView, String>("RFC"));
		tableColumnUsernameAdminVentas.setCellValueFactory(new PropertyValueFactory<UserTableView, String>("username"));
		users = FXCollections.observableArrayList();
		parseUserList();
		tableViewAdmin.setItems(users);
		
    }
	
    private void parseUserList() throws SQLException, ClassNotFoundException{
        // parse and construct User datamodel list by looping your ResultSet rs
        // and return the list  
    	ArrayList<User> usersList = new ArrayList<>();
    	Database db = new Database();
		usersList = db.getUsers();
		for(User user: usersList) {
			UserTableView temp = new UserTableView();
			temp.setID_User(user.getId());
			temp.setEmail(user.getEmail());
			temp.setRole(user.getRole());
			temp.setRFC(user.getRfc());
			temp.setUsername(user.getUsername());
			users.add(temp);
		}
		
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
            this.close();
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
            this.close();
            
		}catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			
		}
	}
	
	public void close() {
		Stage stage = (Stage) btnAdminExit.getScene().getWindow();
		stage.close();
	}
	

}
