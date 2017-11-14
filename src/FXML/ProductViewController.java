package FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Database;
import application.Manager;
import application.Producto;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ProductViewController implements Initializable {
	
	@FXML
	private FlowPane flowPaneProductsDisplay;
	
	@FXML
	private Button btnCancelProductAgregation;
	
	@FXML
	private void btnCancelProductAgregation() {
		System.out.println("Cancel");
	}
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		Database d;
		try {
			d = new Database();
			ArrayList<Producto> productos = d.fetchProductos();
            for(Producto p : productos){
				//System.out.println(p.descripcion);
				Button b = new Button(p.descripcion);
				int pid = p.idProducto;
				b.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		                Manager m = Manager.getInstance();
		                Producto prod = new Producto(b.getText(), 0,pid);
		                
		                for(Producto pr : productos){
		                	if(pr.descripcion == prod.descripcion)
		                	{
		                		prod.precio = pr.precio;
		                	}
		                }
		                m.currentMesa.productos.add(prod);
		                m.currentMesa.olp.add(prod.descripcion);
		                System.out.println("added "+prod.descripcion);
		            }
		        });
				b.setWrapText(true);
				b.setTextAlignment(TextAlignment.CENTER);
				b.setPrefHeight(74);
				b.setPrefWidth(120);
				
				flowPaneProductsDisplay.getChildren().add(b);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		btnCancelProductAgregation.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				//FlowPane flowPaneProductsDisplay = (FlowPane) root1.lookup("#flowPaneProductsDisplay");
				Stage stage = (Stage) btnCancelProductAgregation.getScene().getWindow();
			    stage.close();
	            
				
			}
		});
	}
}
