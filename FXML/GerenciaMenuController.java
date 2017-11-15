package FXML;

import java.util.Calendar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
	public void launchStats() { // invoca a Estadisticas
		
	}
	@FXML
	public void admMesas() { // invoca a Administrar mesas
		
	}
	public void initialize() {

		currentDate();
	}
}
