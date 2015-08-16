package Controller;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Databaseservice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Mycontroller implements Initializable {
	ArrayList rowItems;
	ArrayList rowItems1;
	ArrayList rowItems2;
	ArrayList rowItems3;
	ArrayList rowItems4;
	@FXML
	private ListView<String> listview;
	@FXML
	private ListView<String> listview1;
	@FXML
	private ListView<String> listview2;
	@FXML
	private TextArea textarea;
	@FXML
	private TextField var1 ;
	@FXML
	private TextField var2;
	@FXML
	private TextField flightnumber;
	@FXML
	private TextField flightnumber1;
	@FXML
	private TextField date ;
	@FXML
	private TextField textfield1 ;
	@FXML
	private TextField flightnumber2;
	@FXML
	private TextField date1 ;
	@FXML
	private TextField passengername;
	@FXML
	private ComboBox noofconnections;
	
//ComboBox comboBox = new ComboBox();	
 
	
	//ObservableList<String> noofconnections = 
	//FXCollections.observableArrayList(
	  //  "1",
	//    "2",
	//    "3"
//	);
//	comboBox.setItems(noofconnections);
	//ObservableList<String> noofconnections = 
	//	    FXCollections.observableArrayList(
//		        "1",
	//	        "2",
//		        "3"
	//	    );
//		final ComboBox comboBox = new ComboBox(noofconnections);
	//   comboBox.setItems("1");
//	   comboBox.getItems().addAll(
	//            "1",
	  //          "2",
	  //          "3",
	            
	  //      );
	
	Databaseservice db1 = new Databaseservice(); 
	
	public void displayflights() throws SQLException
	{
		rowItems = new ArrayList();
		//for(int i=1;i<=1;i++)
		//{
		ObservableList<String> value;
		if (noofconnections.getValue().toString().equals("0")){
			//Databaseservice db1 = new Databaseservice(); 
			value=db1.getdirectflight(var1.getText(),var2.getText());
		//Oneconnectingflight one = new Oneconnectingflight(221, "14:54:54","54:44:", 546, "55:55:55","55:55:45","chennai", "bombay","fgfg"); 
		//	rowItems.add(one);
		//	rowItems.add(value);
			
			
		//}
		//ObservableList<String> items =FXCollections.observableArrayList ();
		//for(int j=0;j<rowItems.size();j++)
			//  items.add("Flight "+ rowItems.get(j).getFl1arrival()+"\n"+j);
			//items.add("Flight "+ rowItems.get(0));
			  listview.setItems(value);
		}  
		else if(noofconnections.getValue().toString().equals("1")){
			value=db1.getflightwithoneconnection(var1.getText(),var2.getText());
			//Oneconnectingflight one = new Oneconnectingflight(221, "14:54:54","54:44:", 546, "55:55:55","55:55:45","chennai", "bombay","fgfg"); 
			//	rowItems.add(one);
			//	rowItems.add(value);
				
				
			//}
			//ObservableList<String> items =FXCollections.observableArrayList ();
			//for(int j=0;j<rowItems.size();j++)
				//  items.add("Flight "+ rowItems.get(j).getFl1arrival()+"\n"+j);
				//items.add("Flight "+ rowItems.get(0));
				  listview.setItems(value);
			
		}
		else if(noofconnections.getValue().toString().equals("2")){
			value=db1.getflightwithtwoconnection(var1.getText(),var2.getText());
			//Oneconnectingflight one = new Oneconnectingflight(221, "14:54:54","54:44:", 546, "55:55:55","55:55:45","chennai", "bombay","fgfg"); 
			//	rowItems.add(one);
			//	rowItems.add(value);
				
				
			//}
			//ObservableList<String> items =FXCollections.observableArrayList ();
			//for(int j=0;j<rowItems.size();j++)
				//  items.add("Flight "+ rowItems.get(j).getFl1arrival()+"\n"+j);
				//items.add("Flight "+ rowItems.get(0));
				  listview.setItems(value);
			
		}
		
		//	  textarea.setText("test");
	//}
	}
	public void displayfare() throws SQLException{
		rowItems1 = new ArrayList();
		//Dat db1 = new Databaseservice();
		String faredetails =db1.getfare(flightnumber.getText());
		textarea.setText(faredetails);
	}
	public void displayavailability() throws SQLException{
		rowItems2 = new ArrayList();
		String availability =db1.getnoofavailableseats(flightnumber1.getText(),date.getText());
		textfield1.setText(availability);
	}
	public void displaypassengerlist() throws SQLException{
		rowItems3 = new ArrayList();
		ObservableList<String> value1 =db1.getlistofpassengers(flightnumber2.getText(),date1.getText());
		rowItems3.add(value1);
	//	ObservableList<String> items1 =FXCollections.observableArrayList ();
		//for(int j=0;j<rowItems.size();j++)
			//  items.add("Flight "+ rowItems.get(j).getFl1arrival()+"\n"+j);
			//items.add("Flight "+ rowItems.get(0));
			  listview1.setItems(value1);
	//	textfield1.setText(availability);
	}
	public void flightinstances() throws SQLException{
		rowItems4 = new ArrayList();
	   ObservableList<String> value2 =db1.getlistofflightinstances(passengername.getText());
	   rowItems4.add(value2);
	//	ObservableList<String> items2 =FXCollections.observableArrayList ();
		//for(int j=0;j<rowItems.size();j++)
			//  items.add("Flight "+ rowItems.get(j).getFl1arrival()+"\n"+j);
			//items.add("Flight "+ rowItems.get(0));
		  listview2.setItems(value2);
	//	textfield1.setText(availability);
	}
     
      
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		noofconnections.getItems().addAll("0","1","2");
		
	}

}
