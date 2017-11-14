package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class Main extends Application {
	
	private Stage stage;
	private AnchorPane rootLayout;
	@Override
    public void start(Stage primaryStage) {
        try {
            this.stage = primaryStage;
            this.stage.setTitle("Taco PV");
            LogIn();
        } catch (Exception e) {
            
        }
    }
	
	//------------main-----------------
	public static void main(String[] args) {
		launch(args);
	}
	//----------funciones---------------
	private void LogIn() {
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../FXML/tacoAppFXML.fxml"));
            
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	
}

