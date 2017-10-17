package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class facturarXmlController implements javafx.fxml.Initializable {
	
	@FXML
	TextField tfClientName;
	
	@FXML
	TextField tfClientRfc;
	
	@FXML
	TextField tfPaymentMethod;
	
	@FXML
	TableView<?> tableViewInvoices;
	
	@FXML
	Button btnGenerateInvoice;
	
	
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}
