package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;



public class Main extends Application {
 
	@Override
	public void start(Stage primaryStage) {
		try {
   Parent root = FXMLLoader.load(getClass().getResource("fw.fxml"));
		
			Scene scene = new Scene(root,700,600);
			ComboBox cb = (ComboBox) scene.lookup("#typauswahl");
			
			cb.getItems().add("Standardkonto");
			cb.getItems().add("Girokonto");
		cb.getItems().add("Sparkonto");
		
		
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
