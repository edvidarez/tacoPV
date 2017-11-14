package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Manager;
import application.Producto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableDetailController implements javafx.fxml.Initializable {
	
	@FXML
	ScrollPane scrollPaneProductsInTable;
	
	@FXML
	ListView<String> listViewProductsInTable;
	
	@FXML
	Button btnAddProductTableDetail;
	
	@FXML
	Button btnPayTableBill;
	
	@FXML

	Text totalSumado;
	
	@FXML

	private void btnAddProductTableDetail() {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../FXML/ProductView.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Productos");
            stage.setScene(new Scene(root1));  
            stage.show();
           
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void btnPayTableBill() {
		Manager m = Manager.getInstance();
		m.pagarMesa(m.currentMesa.numero);
		Stage stage2 = (Stage) btnPayTableBill.getScene().getWindow();
 	   	stage2.close();
	}

	public void reloadData() {
		Manager m = Manager.getInstance();
		
		listViewProductsInTable.setItems(m.Mesas.get(m.currentMesa.numero-1).olp);
	}
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		reloadData();
	}
	
}
