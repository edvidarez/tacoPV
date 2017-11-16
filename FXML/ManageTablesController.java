package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.Manager;
import application.Session;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManageTablesController implements Initializable {
	
	
	@FXML
	ScrollPane scrollPaneManageTables;
	
	@FXML
	ListView<Button> listViewManageTable;
	
	@FXML
	Button btnAddTable;
	
	@FXML
	Button btnCloseTableManagement;
	
	@FXML 
	private void btnAddTable(){
		Manager m = Manager.getInstance();
		Button b = new Button("Mesa"+m.nMesas);
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
		System.out.println(m.nMesas);
		if(m.newMesa(m.nMesas))
		{
			System.out.println("added NEW Table");
			listViewManageTable.setItems(m.olb);
			
			m.nMesas++;
		}
		m.server.setManager(m);
		
	}
	@FXML
	private void btnClose() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();			
			
			fxmlLoader.setLocation(Main.class.getResource("../FXML/GerenciaMenu.fxml"));
           
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("User");
            stage.setScene(new Scene(root1));  
            stage.show();
            Stage stage2 = (Stage) btnCloseTableManagement.getScene().getWindow();
    	    stage2.close();
    	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	Label labelAdministrarmesa;
	
	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ResourceBundle rb = ResourceBundle.getBundle(Session.resourcesLocation,Session.locale);
		labelAdministrarmesa.setText(rb.getString("lbl.tiadminmesa"));
		btnAddTable.setText(rb.getString("btn.añadir"));
		btnCloseTableManagement.setText(rb.getString("btn.cerrar"));
		
		
	}
	
}
