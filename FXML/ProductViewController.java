package FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Database;
import application.Main;
import application.Manager;
import application.Producto;
import application.Session;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ProductViewController implements Initializable {
	
	@FXML
	private FlowPane flowPaneProductsDisplay;
	
	@FXML
	private Button botonCancelar;
	
	@FXML
	private void cancelar() {
	}
	
	@FXML
	Button botonAgregar;
	@FXML 
	private void  agregar()
	{
		System.out.println("agregar");
		for(Producto p : productos)
		{
			System.out.println(p.descripcion+" "+p.cantidad);
		}
		Manager m = Manager.getInstance();
		m.Mesas.get(m.currentMesa.numero-1).productos = productos;
		if(Session.getInstance().getRole_() == 3)
		{
			System.out.println("Actualizando "+m.Mesas.size());
			//Session.getInstance().startClient();
			Session.getInstance().cliente.putMesas();
			
		}
			
		System.out.println("Current mesa = "+m.currentMesa.numero);
		Stage stage = (Stage) botonCancelar.getScene().getWindow();
	    stage.close();
	}
	ArrayList<Producto> productos;
	@FXML
	Label labelTituloProducto;
	@FXML
	Label labelDisponibleagregar;
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		
		ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
		labelTituloProducto.setText(rb.getString("lbl.tipro"));
		labelDisponibleagregar.setText(rb.getString("lbl.dispag"));
		botonAgregar.setText(rb.getString("btn.add"));
		botonCancelar.setText(rb.getString("btn.cancel"));
		
		Database d;
		try {
			
			d = new Database();
			if(Manager.getInstance().Mesas.get(Manager.getInstance().currentMesa.numero-1).productos.size() != 0)
			{
				System.out.println(Manager.getInstance().Mesas.get(Manager.getInstance().currentMesa.numero-1).productos.size());
				productos = Manager.getInstance().Mesas.get(Manager.getInstance().currentMesa.numero-1).productos;
			}
			else
			{
				System.out.println("fetch");
				productos  = d.fetchProductos();
			}
            for(Producto p : productos){
				//System.out.println(p.descripcion);
            	try {
            		
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("../FXML/Product.fxml"));
                    AnchorPane b = (AnchorPane) fxmlLoader.load();
                    ProductController pc = fxmlLoader.getController(); 
                    pc.product = p;
                    System.out.println(p.cantidad);
                    Label title = pc.titulo;
                    Label Cuantity = pc.Cuantity;
                    
                    
            		Cuantity.setText(String.valueOf(p.cantidad));

                    ImageView image = pc.imagen;
                    
                    System.out.println(title.getText());
                    image.setStyle("-fx-image: url(\""+ p.img + "\");");
                    title.setStyle("-fx-color: red");
                    title.setText(p.descripcion);
                    title.setStyle("-fx-color: red");
                   
                    
    				flowPaneProductsDisplay.getChildren().add(b);

        		} catch (Exception e) {
        			e.printStackTrace();
        		}
				
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		botonCancelar.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				//FlowPane flowPaneProductsDisplay = (FlowPane) root1.lookup("#flowPaneProductsDisplay");
				Stage stage = (Stage) botonCancelar.getScene().getWindow();
			    stage.close();
	            
				
			}
		});
	}
}
