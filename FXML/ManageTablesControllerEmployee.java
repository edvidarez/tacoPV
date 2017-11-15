package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Manager;
import application.Mesa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManageTablesControllerEmployee implements Initializable {
	
	
	@FXML
	ScrollPane scrollPaneManageTables;
	
	@FXML
	ListView<Button> listViewManageTable;

	
	@FXML
	Button btnCloseTableManagement;
	

	@FXML
	private void btnClose() {
		try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("../FXML/tacoAppFXML.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root1));  
            stage.show();
            Stage stage2 = (Stage) btnCloseTableManagement.getScene().getWindow();
    	    stage2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("inicializacion chida");
		Manager m = Manager.getInstance();
		System.out.println(m.Mesas.size());
		for(Mesa me : m.Mesas)
		{
			System.out.println("nueva Mesa");
			Button b = new Button("Mesa"+me.numero);
			b.setPrefHeight(50);
			b.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	int index = Integer.parseInt(b.getText().substring(4));
	            	m.currentMesa = m.Mesas.get(index-1);
	            	System.out.println(m.currentMesa.numero);
	            	try {
	                    FXMLLoader fxmlLoader = new FXMLLoader();
	                    fxmlLoader.setLocation(Main.class.getResource("../FXML/TableDetail.fxml"));
	                    AnchorPane root1 = (AnchorPane) fxmlLoader.load();
	                    Stage stage = new Stage();
	                    stage.setTitle("Productos");
	                    stage.setScene(new Scene(root1));  
	                    stage.show();
	        		} catch (Exception e) {
	        			e.printStackTrace();
	        		}
	            }
	        });
			m.olb.add(b);
			listViewManageTable.setItems(m.olb);

		}
		
		
	}
	
}
