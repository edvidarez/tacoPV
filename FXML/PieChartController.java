package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class PieChartController implements Initializable{
	@FXML
	PieChart paygrapf;
	
	Connection conObj;
	Statement stObj;
	public void generar() throws ClassNotFoundException, SQLException, IOException {
		Database db = new Database();	
        ObservableList<Data> olist = FXCollections.observableArrayList();
        /*db.add(new PieChart.Data("uno", 1.0));
        db.add(new PieChart.Data("dos", 2.0));
        db.add(new PieChart.Data("tres", 3.0));
        */
        olist = db.getProdctosReporte();
		
		paygrapf.getData().addAll(olist);
		       
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			generar();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
			
            	
}
