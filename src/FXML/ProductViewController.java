package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class ProductViewController implements javafx.fxml.Initializable {
	
	@FXML
	private Button btnAddProduct;
	
	@FXML
	private FlowPane flowPaneProductsDisplay;
	
	@FXML
	private Button btnCancelProductAgregation;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		btnCancelProductAgregation.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				
			}
		});
	}
}
