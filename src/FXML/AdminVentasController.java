package FXML;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Database;
import application.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class AdminVentasController implements javafx.fxml.Initializable {

    @FXML
    private MenuBar menuBarAdmin;

    @FXML
    private TableView<Venta> tableViewAdmin;

    @FXML
    private ImageView imageViewAdmin;

    @FXML
    private Button btnAdminExit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 ObservableList<Venta> data = FXCollections.observableArrayList();
		
		try {
			ResultSet rs;
			
			Database d = new Database();
			rs = d.getVentas();
			while(rs.next()){
			    //Iterate Row
				Venta v = new Venta(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getFloat(6));
			    
				data.add(v);
			}
			tableViewAdmin.setItems(data);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
