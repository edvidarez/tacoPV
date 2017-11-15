package FXML;

import java.net.URL;
import java.util.ResourceBundle;

import application.Producto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProductController implements Initializable{

	@FXML 
	AnchorPane productPane;
	
	@FXML
	Label titulo;
	
	@FXML
	ImageView imagen;
	@FXML
	Button btnMinus;
	
	@FXML
	Button btnPlus;
	
	@FXML
	Label Cuantity;
	
	public Producto product;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		titulo.setFont(new Font(Font.getDefault().getName(),12));
		titulo.setTextFill(Color.BLACK);
		Cuantity.setTextFill(Color.web("#000000"));
		//System.out.println("initttttttttttttttttttt");
		 btnMinus.setOnAction(new EventHandler<ActionEvent>() {
 			@Override
 			public void handle(ActionEvent  event) {
 				
 				System.out.println(titulo);
 				int n = Integer.parseInt(Cuantity.getText());
 				if(n>0)
 					n--;
 				Cuantity.setText(String.valueOf(n));
 				System.out.println(n);
 				product.cantidad = n;
 			}
 		});
         btnPlus.setOnAction(new EventHandler<ActionEvent>() {
 			@Override
 			public void handle(ActionEvent  event) {
 				System.out.println(titulo);

 				int n = Integer.parseInt(Cuantity.getText());
 				n++;
 				Cuantity.setText(String.valueOf(n));
 				System.out.println(n);
 				product.cantidad = n;
 			}
 		});
	}

}
