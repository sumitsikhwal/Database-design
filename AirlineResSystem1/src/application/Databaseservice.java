package application;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
public class Databaseservice {
	// JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/airlineressystem";

	   //  Database credentials
	static final String USER = "root";
	static final String PASS = "Welcome-1";
	   
	ArrayList<String> list = new ArrayList<String>();
	   
    // Now add observability by wrapping it with ObservableList.
    ObservableList<String> observableList = FXCollections.observableList(list);
   
    ArrayList<String> list1 = new ArrayList<String>();
   
   // Now add observability by wrapping it with ObservableList.


    ArrayList<String> list2 = new ArrayList<String>();

    // Now add observability by wrapping it with ObservableList.

	   
	//   public static void main(String[] args) {
	   Connection conn ;
	   Statement stmt ;
	   public Databaseservice() {
		   conn = null;
		   stmt = null;
		   
	   
	   try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ObservableList<String> getdirectflight(String Dep_air_code,String Arr_Air_code) throws SQLException{
		ObservableList<String> observableList = FXCollections.observableArrayList();
		System.out.println("test11");
		//String sql="select * from flight;";
		String sql="select Number,Airline,Weekdays,Departure_airport,Scheduled_dep_time,Arrivel_airport,Scheduled_arrival_time from flight where Departure_airport = '"+Dep_air_code+"' and Arrivel_airport = '"+Arr_Air_code+"';";
		ResultSet rs = stmt.executeQuery(sql);	
		System.out.println("test1");
		
		//if(!rs.next()){
		//	while(rs.next()){
		//		String sql1=	"SELECT fl1.Number,fl1.Weekdays,fl1.Scheduled_arrival_time ,fl1.Departure_airport,"+
		//		"fl2.Departure_airport,fl2.Scheduled_dep_time,fl1.flightid,fl2.flightid"+
		//		" from flight fl1,flight fl2 where fl1.Arrivel_airport = fl2.Departure_airport"+
		//		"and fl1.Departure_airport = 'ATL'"+ 
		//		"and fl2.Arrivel_airport = 'HNL'  and  fl2.Scheduled_dep_time "+
		//		">fl1.Scheduled_arrival_time;";
		//		ResultSet rs1 = stmt.executeQuery(sql1);
			//	System.out.println(rs.getInt(1)+"/"+rs.getString(2)+"/"+rs.getTime(3)+"/"+rs.getString(4)+"/"+rs.getString(5)+"/"+rs.getString(6)+"/"+rs.getTime(7)+"/"+rs.getString(8)+"/"+rs.getString(9));
		//		observableList.add(rs.getInt(1)+"/"+rs.getString(2)+"/"+rs.getString(3)+"/"+rs.getString(4)+"/"+rs.getTime(5)+"/"+rs.getString(6)+"/"+rs.getTime(7)) ;
				//return(rs.getString(1));
		//	}
		//}
		//else{
			 while(rs.next()){
				//System.out.println(rs.getInt(1)+"/"+rs.getString(2));
				 observableList.add("Flightnum: "+rs.getInt(1)+" Airline: "+rs.getString(2)+" Weekdays: "+rs.getString(3)+" \n Departure airport: "+rs.getString(4)+" Scheduled dep time: "+rs.getTime(5)+" \n Arrivel airport: "+rs.getString(6)+" Scheduled arrival time: "+rs.getTime(7)+"\n---------------------------------------------");
			//	return(rs.getString(1));
			 }
			
		return observableList;
		//	System.out.println("no_of_rows"+rs.getRow());
		//	System.out.println("test");
			
		}
	
	public ObservableList<String> getflightwithoneconnection(String Dep_air_code,String Arr_Air_code) throws SQLException{
		ObservableList<String> observableList = FXCollections.observableArrayList();
		System.out.println("test11");
		//String sql="select * from flight;";

			
				String sql1="SELECT fl1.Number,fl1.Weekdays,fl1.Scheduled_dep_time,fl1.Scheduled_arrival_time ,fl1.Departure_airport,fl1.Arrivel_airport,"+
						"fl2.Number,fl2.Weekdays,fl2.Scheduled_dep_time,fl2.Scheduled_arrival_time ,fl2.Departure_airport,fl2.Arrivel_airport"+
				" from flight fl1,flight fl2 where fl1.Arrivel_airport = fl2.Departure_airport and fl1.Departure_airport = '"+Dep_air_code+"' and fl2.Arrivel_airport = '"+Arr_Air_code+"'"+
				" and TIMEDIFF(fl2.Scheduled_dep_time,fl1.Scheduled_arrival_time) >'01:00:00';";
				ResultSet rs = stmt.executeQuery(sql1);
			//	System.out.println(rs.getInt(1)+"/"+rs.getString(2)+"/"+rs.getTime(3)+"/"+rs.getString(4)+"/"+rs.getString(5)+"/"+rs.getString(6)+"/"+rs.getTime(7)+"/"+rs.getString(8)+"/"+rs.getString(9));
			//	observableList.add(rs.getInt(1)+"/"+rs.getString(2)+"/"+rs.getString(3)+"/"+rs.getString(4)+"/"+rs.getTime(5)+"/"+rs.getString(6)+"/"+rs.getTime(7)) ;
				//return(rs.getString(1));
			
		
		
			 while(rs.next()){
				 String [] weekday = {rs.getString(2),rs.getString(8)};
			     String Avlblweekday=iscompatible(weekday);
				//System.out.println(rs.getInt(1)+"/"+rs.getString(2));
				// observableList.add("Flight1 no."+rs.getInt(1)+"/"+"+"/"+rs.getTime(3)+"/"+rs.getTime(4)+"/"+rs.getString(5)
				//		 +"/"+rs.getInt(6)+"/"+rs.getString(7)+"/"+rs.getTime(8)+"/"+rs.getTime(9)+"/"+rs.getString(10));
			//	 String [] weekday = {rs.getString(2),rs.getString(7)};
			//	 String Avlblweekday=iscompatible(weekday);
				  if (!Avlblweekday.equals("")){
					  observableList.add("Flight1 num: "+rs.getInt(1)+" flight1_dep_time:  "+rs.getTime(3)+" flight1_arr_time:  "+rs.getTime(4)+"\nflight1_dep_airport:  "+rs.getString(5) +"  flight1_arr_airport: "+rs.getString(6)+"\n"
								 +"Flight2 num:  "+rs.getInt(7)+" flight2_dep_time: "  +rs.getTime(9)+" flight2_arr_time:  "+rs.getTime(10)+"\nflight2_dep_airport:   "+rs.getString(11)+"  flight2_arr_airport: "+rs.getString(12)+"\n" +" Availabledays   "+Avlblweekday+"\n--------------------------------------");  
				  }
					 
			//	return(rs.getString(1));
			 }
			
		return observableList;
		//	System.out.println("no_of_rows"+rs.getRow());
		//	System.out.println("test");
			
		}
	private String iscompatible(String[] weekday) {
		// TODO Auto-generated method stub
		String [] Splitweekday;
		String [] Splitweekday1;
		String [] Splitweekday2;
		Splitweekday=weekday[0].split("_");
		Splitweekday1=weekday[1].split("_");
		
		String weekday2 = "";
	if (weekday.length == 2){
			
		HashSet<String> s1 = new HashSet<String>(); 
		HashSet<String>  s2 = new HashSet<String> ();
	    for(int i = 0;i<Splitweekday.length;i++){
			s1.add(Splitweekday[i]);
		}
		for(int j = 0;j<Splitweekday1.length;j++){
		s2.add(Splitweekday1[j]);
		}
		s1.retainAll(s2);
		
	    for (String i : s1 ){
	    	 weekday2 += i; 
	    }
	   	
	}
	else if (weekday.length == 3){
		Splitweekday2=weekday[2].split("_");
		System.out.println("testing 15");
		HashSet<String> s1 = new HashSet<String>(); 
		HashSet<String>  s2 = new HashSet<String> ();
		HashSet<String>  s3 = new HashSet<String> ();
	    for(int i = 0;i<Splitweekday.length;i++){
			s1.add(Splitweekday[i]);
		}
		for(int j = 0;j<Splitweekday1.length;j++){
		s2.add(Splitweekday1[j]);
		}
		for(int j = 0;j<Splitweekday2.length;j++){
			s3.add(Splitweekday2[j]);
			}
		
		s2.retainAll(s3);
		s2.retainAll(s1);
		
	    for (String i : s2 ){
	    	 weekday2 += i; 
	    }
	   	
	}
	System.out.println("testing 16");
	return weekday2; 
	}

	public ObservableList<String> getflightwithtwoconnection(String origin,String destination) throws SQLException{
	/*	System.out.println("test11");
		ObservableList<String> observableList = FXCollections.observableArrayList();
		//String sql="select * from flight;";

			
				String sql1=	"SELECT fl1.Number,fl1.Weekdays,fl1.Scheduled_dep_time,fl1.Scheduled_arrival_time,fl1.Departure_airport,"+
				"fl2.Number,fl2.Weekdays,fl2.Scheduled_dep_time,fl2.Scheduled_arrival_time ,fl2.Departure_airport,"+
				"fl3.Number,fl3.Weekdays,fl3.Scheduled_dep_time,fl3.Scheduled_arrival_time ,fl3.Departure_airport"+		
				" from flight fl1 inner join flight fl2 on (fl1.Arrivel_airport = fl2.Departure_airport)"+
				" inner join flight fl3 on (fl2.Arrivel_airport = fl3.Departure_airport);";
				//" and TIMEDIFF(fl2.Scheduled_dep_time,fl1.Scheduled_arrival_time) >'01:00:00' "+
				 //" and TIMEDIFF(fl3.Scheduled_dep_time,fl2.Scheduled_arrival_time) >'01:00:00'
				
				ResultSet rs = stmt.executeQuery(sql1);
			//	System.out.println(rs.getInt(1)+"/"+rs.getString(2)+"/"+rs.getTime(3)+"/"+rs.getString(4)+"/"+rs.getString(5)+"/"+rs.getString(6)+"/"+rs.getTime(7)+"/"+rs.getString(8)+"/"+rs.getString(9));
			//	observableList.add(rs.getInt(1)+"/"+rs.getString(2)+"/"+rs.getString(3)+"/"+rs.getString(4)+"/"+rs.getTime(5)+"/"+rs.getString(6)+"/"+rs.getTime(7)) ;
				//return(rs.getString(1));
			
		
		
			 while(rs.next()){
				 String [] weekday = {rs.getString(2),rs.getString(7),rs.getString(12)};
				 System.out.println("testing 14");
			     String Avlblweekday1=iscompatible(weekday);
			     System.out.println("testing 14");
				//System.out.println(rs.getInt(1)+"/"+rs.getString(2));
			     if (!Avlblweekday1.equals("")){
			    	 observableList.add("Flight1 num  "+rs.getInt(1)+" flight1_dep_time  "+rs.getTime(3)+" flight1_arr_time  "+rs.getTime(4)+" flight1_dep_airport  "+rs.getString(5) +"\n"
							 +" Flight2 num  "+rs.getInt(6)+" flight2_dep_time  "+"\n"+rs.getTime(8)+" flight2_arr_time  "+rs.getTime(9)+" flight2_dep_airport   "+rs.getString(10)+"\n" +
							 "Flight3 num  "+rs.getInt(11)+" flight3_dep_time  "+rs.getTime(13)+" flight3_arr_time  "+rs.getTime(14)+" flight3_dep_airport  "+rs.getString(15) +"\n" +" Availabledays   "+Avlblweekday1);  
			//	return(rs.getString(1));
			    	 System.out.println("testing 15");
			     }
			 }
			
		return observableList;
		//	System.out.println("no_of_rows"+rs.getRow());
		//	System.out.println("test");
		*/
		ObservableList<String> flights=FXCollections.observableArrayList();
		String sql="select f1.airline, f1.number as flight1_number, f1.weekdays as flight1_weekdays, f1.scheduled_dep_time as flight1_departure, f1.scheduled_arrival_time as flight1_arrival,"+
				"f2.airline, f2.number as flight2_number, f2.weekdays as flight2_weekdays, f2.scheduled_dep_time as flight2_departure, f2.scheduled_arrival_time as flight2_arrival,"+
				"f3.airline, f3.number as flight3_number, f3.weekdays as flight3_weekdays, f3.scheduled_dep_time as flight3_departure, f3.scheduled_arrival_time as flight3_arrival,"+
				"f1.departure_airport as f1Dep, f1.arrivel_airport as f1Arr, f2.departure_airport as f2dep, f2.arrivel_airport as f2Arr, f3.departure_airport as f3dep, f3.arrivel_airport as f3Arr "+
				"from "+
				"flight as f1 inner join flight as f2 on (f1.arrivel_airport=f2.departure_airport and f1.Arrivel_airport<>'"+destination+"') "+
				"inner join flight as f3 on f2.arrivel_airport=f3.departure_airport "+
				"where f1.Departure_airport='"+origin+"' and f3.arrivel_airport='"+destination+"' "+
				"and (f1.scheduled_arrival_time < f2.scheduled_dep_time and TIMEDIFF(f2.scheduled_dep_time,f1.scheduled_arrival_time)>'01:00:00') "+
				"and (f2.Scheduled_arrival_time < f3.Scheduled_dep_time and TIMEDIFF(f3.scheduled_dep_time,f2.scheduled_arrival_time)>'01:00:00');";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			String[] weekdys={rs.getString(3),rs.getString(8),rs.getString(13)};
			String compatibility=iscompatible(weekdys);
			if(!compatibility.equals("")){
				String flight_details="Flight1: "+rs.getString(1)+rs.getInt(2)+
						"\n Departure airport: "+rs.getString(16)+"\t\tDeparture time: "+rs.getTime(4)+
						"\n Arrival airport: "+rs.getString(17)+"\t\t        Arrival time: "+rs.getTime(5)+
						"\n Flight2: "+rs.getString(6)+rs.getInt(7)+
						"\n Departure airport: "+rs.getString(18)+"\t\tDeparture time: "+rs.getTime(9)+
						"\n Arrival airport: "+rs.getString(19)+"\t\tArrival time: "+rs.getTime(10)+
						"\n Flight3: "+rs.getString(11)+rs.getInt(12)+
						"\n Departure airport: "+rs.getString(20)+"\t\tDeparture time: "+rs.getTime(14)+
						"\n Arrival airport: "+rs.getString(21)+"\t\t        Arrival time: "+rs.getTime(15)+
						"\n\nAvailable On: "+compatibility+"\n ------------------------------------------------";
				flights.add(flight_details);
			}			
		}
		return flights;
		}
	public String getfare(String flightnumber) throws SQLException{
		String sql="select *  from fare	where Flight_number = "+flightnumber+";";
		ResultSet rs = stmt.executeQuery(sql);	
		System.out.println("test1");
		String emp1="";
		while(rs.next()){
			//
			emp1 += ("Flight_num : " +rs.getInt(1)+" Fare_code : "+rs.getString(2)+"  Amount:"+rs.getInt(3)+" Restrictions: "+rs.getString(4))+"\n";
			System.out.println("Flight_num " +rs.getInt(1)+" Fare_code "+rs.getString(2)+"\n Amount "+rs.getString(3)+" Restrictions "+rs.getString(4));
		}
		return emp1;
	}
		
	

   public ObservableList<String> getlistofpassengers(String flightnumber1 , String date1) throws SQLException{
	 ObservableList<String> observableList = FXCollections.observableArrayList();
	 String sql="Select Customer_name,Seat_number from Seat_reservation , flight_instance where seat_reservation.flight_number =  flight_instance.flight_number and "+
     "flight_instance.flight_number = "+flightnumber1+" and flight_instance.Date = '"+date1+"';";
	  ResultSet rs = stmt.executeQuery(sql);	
	  System.out.println("test1");
	  String no_of_availabe_seats = "";
	
	while(rs.next()){
		observableList.add("Customer_name: "+ rs.getString(1) + " Seat_number: "+ rs.getString(2));
	}
	return observableList;
   }
   public ObservableList<String> getlistofflightinstances(String passengername) throws SQLException{
	ObservableList<String> observableList = FXCollections.observableArrayList();
	String sql="Select flight_instance.Flight_number,flight_instance.Date from Seat_reservation , flight_instance where seat_reservation.flight_number =  flight_instance.flight_number and "+
      "Seat_reservation.Customer_name = '"+passengername+"';";
	ResultSet rs = stmt.executeQuery(sql);	
	System.out.println("test1");
	
	while(rs.next()){
		observableList.add("Flight_number: "+rs.getString(1)+" Date: "+rs.getDate(2));
	}
	return observableList;
   } 

   public String getnoofavailableseats (String flightnumber1 , String date) throws SQLException{
	int reservations = 0,total_seats = 0,available_seats;
	System.out.println("Sumit :test1" );
	String sql="Select count(*) as reservations from seat_reservation where  seat_reservation.flight_number  = "+flightnumber1+"  and seat_reservation.Date = '"+date+"' ;";
			ResultSet rs = stmt.executeQuery(sql);	
			System.out.println("test1");
			String no_of_availabe_seats = "";
			while(rs.next()){
				  reservations = rs.getInt(1);
			}
			System.out.println("Sumit :test2" );
			String sql1="Select total_number_of_seats as total_seats  from flight_instance,airplane where flight_instance.airplane_id  = airplane.airplane_id  and flight_instance.flight_number  ="+flightnumber1+" and flight_instance.Date = '"+date+"'  ;";
			ResultSet rs1 = stmt.executeQuery(sql1);
			
			System.out.println("Sumit :test3" );
			
			while(rs1.next()){
				  total_seats = rs1.getInt(1);
			}
			System.out.println("total_number_of_seats  :"+ total_seats );
			available_seats=total_seats - reservations;
			 no_of_availabe_seats += (available_seats) ;
			System.out.println("available_seats "+available_seats );
			System.out.println("Sumit :test4" );
			return no_of_availabe_seats;
        }


	
       }
