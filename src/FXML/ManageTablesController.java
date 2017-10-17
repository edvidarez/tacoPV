package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class ManageTablesController implements javafx.fxml.Initializable {
	
	@FXML
	ScrollPane scrollPaneManageTables;
	
	@FXML
	ListView<?> listViewManageTable;
	
	@FXML
	Button btnAddTable;
	
	@FXML
	Button btnCloseTableManagement;
	
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}
