package application;
	
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	ComboBox comboBox = new ComboBox();	
	
	ObservableList<String> noofconnections = 
	FXCollections.observableArrayList(
	    "1",
	    "2",
	    "3"
	);
//	comboBox.setItems(noofconnections);
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("Airlineressystem1.fxml"));
		
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException, ParseException {
		launch(args);
		Databaseservice obj = new Databaseservice();
	//	obj.getflight();
		//obj.getfare();
		//obj.getlistofpassengers();
	//	obj.getlistofflightinstances();
	//	obj.getnoofavailableseats();
	//	DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
      //  sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        
		Date date = new Date(03/22/2015);
        String DATE_FORMAT = "E";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        System.out.println("Today is " + sdf.format(date));
        
}
}