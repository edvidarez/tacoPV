package FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class barChartController implements Initializable{
	@FXML
	BarChart<String, Number> bchart;
	@FXML
	CategoryAxis xAx;
	@FXML
	NumberAxis yAx;
	
	Connection conObj;
	Statement stObj;
	public void generar() throws ClassNotFoundException, SQLException, IOException {
		Database db = new Database();	
		XYChart.Series<String,Number> olist = new XYChart.Series<String,Number>();
        olist = db.getProdctosReporteB();

		xAx.setLabel("Vendedor");
		yAx.setLabel("Ventas");

		bchart.setLegendSide(Side.LEFT);
		bchart.getData().add(olist);
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
