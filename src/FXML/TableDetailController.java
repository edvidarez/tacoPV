package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

public class TableDetailController implements javafx.fxml.Initializable {
	
	@FXML
	ScrollPane scrollPaneProductsInTable;
	
	@FXML
	ListView<?> listViewProductsInTable;
	
	@FXML
	Button btnAddProductTableDetail;
	
	@FXML
	Button btnPayTableBill;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}
