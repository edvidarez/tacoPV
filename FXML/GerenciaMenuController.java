package FXML;

import java.io.IOException;
import java.util.Calendar;

import application.Main;
import application.Manager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GerenciaMenuController {
	@FXML
	Button admMesas;
	@FXML
	Button gerenteStats;
	@FXML
	Label Gerente_date;
	@FXML
	Label GerenteTitulo;

	
	public void currentDate(){					
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		++month;
		int day =  now.get(Calendar.DAY_OF_MONTH);
		Gerente_date.setText(day+" / "+month+" / "+year);
				
	}
	
	@FXML
	public void launchStats() throws Exception { // invoca a Estadisticas
		
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../FXML/pieChart1.fxml"));
        
        AnchorPane root1 = (AnchorPane) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Productos");
        stage.setScene(new Scene(root1));  
        stage.show();
        
	}
	@FXML
	public void adm_Mesas() throws IOException { // invoca a Administrar mesas
		System.out.println("Gerente");
		Manager m = Manager.getInstance();
		m.startServer();
		Thread t = new Thread() {
			public void run(){
				m.server.startServer();
			}
		};
		t.start();
		Thread t2 = new Thread() {
			public void run(){
				m.server.listenForChanges();
			}
		};
		t2.start();
		FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("../FXML/ManageTables.fxml"));
        
        AnchorPane root1 = (AnchorPane) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initModality(Modality.WINDOW_MODAL);
        //stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Productos");
        stage.setScene(new Scene(root1));  
        stage.show();
       // s.startClient();
        Stage stage2 = (Stage) admMesas.getScene().getWindow();
	    stage2.close();
	}
	public void initialize() {

		currentDate();
	}
}
