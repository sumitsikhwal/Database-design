package mydatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.lang.Object.*;
import java.util.Map.Entry;
import java.util.*;

import com.csvreader.CsvReader;

//import au.com.bytecode.opencsv.CSVReader;

//import com.csvreader.*;
public class MyDatabase {
	
	private static String idIndFile = "id.ndx";
	private static String cmpnyIndFile = "company.ndx";
	private static String drugidIndFile = "drugid.ndx";
	private static String trialsIndFile = "trials.ndx";
	private static String patientsIndFile = "patients.ndx";
	private static String dosage_mgIndFile = "dosage_mg.ndx";
	private static String readingIndFile = "reading.ndx";
	private static String double_blindIndFile = "double_blind.ndx";
	private static String controlled_studyIndFile = "controlled_study.ndx";
	private static String govt_fundedIndFile = "govt_funded.ndx";
	private static String fda_approvedIndFile = "fda_approved.ndx";
	private static TreeMap<Integer, Long> idMap = new TreeMap<Integer, Long>();
	private static TreeMap<String, ArrayList<Long>> companyMap = new TreeMap<String, ArrayList<Long>>();
	private static TreeMap<String, ArrayList<Long>> drug_idMap = new TreeMap<String,ArrayList<Long>>();
	private static TreeMap<Integer, ArrayList<Long>> trialsMap = new TreeMap<Integer,ArrayList<Long>>();
	private static TreeMap<Integer, ArrayList<Long>> patientsMap = new TreeMap<Integer, ArrayList<Long>>();
	private static TreeMap<Integer, ArrayList<Long>> dosage_mgMap = new TreeMap<Integer, ArrayList<Long>>();
	private static TreeMap<Float, ArrayList<Long>> readingMap = new TreeMap<Float, ArrayList<Long>>();
	private static TreeMap<String, ArrayList<Long>> double_blindMap = new TreeMap<String, ArrayList<Long>>();
	private static TreeMap<String, ArrayList<Long>> controlled_studyMap = new TreeMap<String, ArrayList<Long>>();
	private static TreeMap<String, ArrayList<Long>> govt_fundedMap = new TreeMap<String, ArrayList<Long>>();
	private static TreeMap<String, ArrayList<Long>> fda_approvedMap = new TreeMap<String, ArrayList<Long>>();
	
	
	public static void main(String args[]) throws IOException{
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\Data.db");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\id.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\company.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\drugid.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\trials.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\patients.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\dosage_mg.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\reading.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\double_blind.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\controlled_study.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\govt_funded.ndx");
		clearfile("C:\\JavaWorkspace\\MyDatabase\\src\\fda_approved.ndx");
		
		boolean flag;
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		//System.out.println("Reading input from console using Scanner in Java ");
		System.out.println("\t \t option 1 : Parse the input csv file to binary.Answer in yes or no");
		
	   // System.out.println("Please enter your input: ");
	    String input = scanner.nextLine();
	  //  System.out.println("input "+input);
	     if (input.equals("yes")) {
	    //	 System.out.println("test");
	    	 flag = true;
	     }
	     else flag = false;
	     String csvFile ="/Users/Sumit Sikhwal/Downloads/PHARMA_TRIALS_1000Ba.csv";
			CsvReader reader = new CsvReader("C:/JavaWorkspace/MyDatabase/PHARMA_TRIALS_1000B.csv");
			ArrayList<Long> companyList;
			RandomAccessFile file = new RandomAccessFile("C:\\JavaWorkspace\\MyDatabase\\src\\Data.db","rw");
			System.out.println("\t \t Parsing .......");
	 if (flag){ 
	     
		
		reader.readHeaders();
		
		while (reader.readRecord())
		{
			//String[] record = products.get(cvsSplitBy);	
			int id = Integer.parseInt(reader.get("id"));
			//System.out.println("id " +id);
			String company = reader.get("company");
			String drug_id = reader.get("drug_id");
			int trials = Integer.parseInt (reader.get("trials"));
		    int patients = Integer.parseInt(reader.get("patients"));
		    int dosage_mg  = Integer.parseInt(reader.get("dosage_mg"));
		    float reading     = Float.parseFloat(reader.get("reading"));
		    String double_blind = reader.get("double_blind");
		    
		  //  String double_blind = Boolean.valueOf(reader.get("double_blind"));
		    String controlled_study =reader.get("controlled_study");
		    String govt_funded = reader.get("govt_funded");
		    String fda_approved = reader.get("fda_approved");
		    
		    long len =file.length();
		  //  System.out.println("len " +len);
		  //********updating index for id
		    idMap.put(id, len);	
		    file.writeInt(id);
		    updateIdIndexFile(idIndFile);
		    	
		   // updateIdIndexFile(id,len);
		    //file.seek(file.length());
		    int val = 0;
		    int chk =company.length();
		    ArrayList<Long> trialList,drug_idList,patientsList,double_blindList,dosage_mgList,readingList,controlled_studyList,govt_fundedList,fda_approvedList;
		    //System.out.println("chk " +chk);
		    if (companyMap.containsKey(company)) {
		    	companyList = companyMap.get(company);
		    }else{
		    companyList = new ArrayList<Long>();
		    }
		    companyList.add(len);
		  //updating index for  company
		    companyMap.put(company,companyList);
		    udpatecompanyIndexFile(cmpnyIndFile);
		    if (drug_idMap.containsKey(drug_id)) {
				drug_idList = drug_idMap.get(drug_id);
			} else {
				drug_idList = new ArrayList<Long>();
			}
		    drug_idList.add(len);
		    drug_idMap.put(drug_id, drug_idList);
		    udpatedrugidIndexFile(drugidIndFile);
		  //****updating trials index*****************
		    if (trialsMap.containsKey(trials)) {
				trialList = trialsMap.get(trials);
			} else {
				trialList = new ArrayList<Long>();
			}
		    trialList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    trialsMap.put(trials,trialList);
		    udpatetrialsIndexFile(trialsIndFile);
		   // udpatedrugidIndexFile(drugidIndFile);
		    //****updating patient index*****************
		    if (patientsMap.containsKey(patients)) {
		    	patientsList = patientsMap.get(patients);
			} else {
				patientsList = new ArrayList<Long>();
			}
		    patientsList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    patientsMap.put(patients,patientsList);
		    udpatepatientsIndexFile(patientsIndFile);
		    //***updating dosage_mg index
		    if (dosage_mgMap.containsKey(patients)) {
		    	dosage_mgList = dosage_mgMap.get(patients);
			} else {
				dosage_mgList = new ArrayList<Long>();
			}
		    dosage_mgList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    dosage_mgMap.put(dosage_mg,dosage_mgList);
		    udpatedosage_mgIndexFile(dosage_mgIndFile);
		    //***updating reading index
		    if (readingMap.containsKey(reading)) {
		    	readingList = readingMap.get(reading);
			} else {
				readingList = new ArrayList<Long>();
			}
		    readingList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    readingMap.put(reading,readingList);
		    udpatereadingIndexFile(readingIndFile);
		    //*****updating double_blin index ***
		    
		    if (double_blindMap.containsKey(double_blind)) {
		    	double_blindList = double_blindMap.get(double_blind);
			} else {
				double_blindList = new ArrayList<Long>();
			}
		    double_blindList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    double_blindMap.put(double_blind,double_blindList);
		    udpatedouble_blindIndexFile(double_blindIndFile);
            //*****updating controlled study index ***
		    
		    if (controlled_studyMap.containsKey(controlled_study)) {
		    	controlled_studyList = controlled_studyMap.get(controlled_study);
			} else {
				controlled_studyList = new ArrayList<Long>();
			}
		    controlled_studyList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    controlled_studyMap.put(double_blind,double_blindList);
		    udpatecontrolled_studyIndexFile(controlled_studyIndFile);
		  //*****updating govt_funded index ***
		    if (govt_fundedMap.containsKey(govt_funded)) {
		    	govt_fundedList = govt_fundedMap.get(govt_funded);
			} else {
				govt_fundedList = new ArrayList<Long>();
			}
		    govt_fundedList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    govt_fundedMap.put(govt_funded,govt_fundedList);
		    udpategovt_fundedIndexFile(govt_fundedIndFile);
		  //*****updating fda_approved index ***
		    if (fda_approvedMap.containsKey(fda_approved)) {
		    	fda_approvedList = fda_approvedMap.get(fda_approved);
			} else {
				fda_approvedList = new ArrayList<Long>();
			}
		    fda_approvedList.add(len);
			//trialsMap.put(pr.getlname(), lNameList);
		    fda_approvedMap.put(fda_approved,fda_approvedList);
		    udpatefda_approvedIndexFile(fda_approvedIndFile);
		   
		    file.writeByte(company.length());
		    file.writeBytes(company);
		    file.writeBytes(drug_id);
		    file.writeShort(trials);
		    file.writeShort(patients);
		    file.writeShort(dosage_mg);
		    file.writeFloat(reading);
		    //if (double_blind == "true")
		    val  = Boolean.parseBoolean(double_blind) ? val|8 : val;
		    val =  Boolean.parseBoolean(controlled_study) ? val|4 : val;
		    val =  Boolean.parseBoolean(govt_funded) ? val|2 : val;
		    val =  Boolean.parseBoolean(fda_approved) ? val|1 : val; 
		    file.writeByte(val); 
		   
		  }
	//	MyDatabase DBObj = new MyDatabase ();
    //    DBObj.loadIDIndexMap(idIndFile);
    //    DBObj.loadCompanyMap(cmpnyIndFile);
       // DBObj.loadStateIndexMap(stateIndFile);
      //Search with Id=300
	 }
	 System.out.println(" \t \t Option 2 : Select a field for querying ");
	        String input3 = scanner.nextLine();
	   if (input3.equals("id")){
      		System.out.println(" \t \t Search Operation enter id ");
      		String input1 = scanner.nextLine();
      		System.out.println(" \t \t Search Operation enter comparison operator ");
      		String input2 = scanner.nextLine();
      		System.out.println("---------------------------------");
      			int id = Integer.parseInt(input1);
      			String operator = input2;
      			System.out.println("id " +id);
      			search_Id(id, file,operator);
      			
      			
	   }
	   if (input3.equals("drug_id")){
		   System.out.println(" \t \t Search Operation enter drug_id ");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     			//int id = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_drug_id(input1, file,operator);
	   }
	   if (input3.equals("reading")){
		   System.out.println(" \t \t Search Operation enter reading ");
    		String input1 = scanner.nextLine();
    		System.out.println(" \t \t  enter comparison operator ");
    		String input2 = scanner.nextLine();
    		System.out.println("---------------------------------");
    			float reading = Float.parseFloat(input1);
    			String operator = input2;
    			//System.out.println("drug_id " +input1);
    			search_reading(reading, file,operator,readingMap);
	  
	   }
	   if (input3.equals("trials")){
		   System.out.println(" \t \t Search Operation enter trials ");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     			int trials = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_trials(trials, file,operator,trialsMap);
	   }
	   if (input3.equals("patients")){
		   System.out.println(" \t \t Search Operation enter patients ");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     			int patients = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_patients(patients, file,operator,patientsMap);
	   }
	   if (input3.equals("dosage_mg")){
		   System.out.println(" \t \t Search Operation enter dosage_mg ");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     			int dosage_mg = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_dosage_mg(dosage_mg, file,operator,dosage_mgMap);
	   }
	   if (input3.equals("company")){
		   System.out.println(" \t \t Search Operation enter company name ");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     		//	int trials = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_company(input1, file,operator,companyMap);
	   }
	   if (input3.equals("double_blind")){
		   System.out.println(" \t \t Search Operation enter double_blind value");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     		//	int trials = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_double_blind(input1, file,operator,double_blindMap);
	   }
	   if (input3.equals("controlled_study")){
		   System.out.println(" \t \t Search Operation enter controlled_study value");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     		//	int trials = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_controlled_study(input1, file,operator,controlled_studyMap);
	   }
	   if (input3.equals("govt_funded")){
		   System.out.println(" \t \t Search Operation enter govt_funded value");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     		//	int trials = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_govt_funded(input1, file,operator,govt_fundedMap);
	   }
	   if (input3.equals("fda_approved")){
		   System.out.println(" \t \t Search Operation enter fda_approved value");
     		String input1 = scanner.nextLine();
     		System.out.println(" \t \t  enter comparison operator ");
     		String input2 = scanner.nextLine();
     		System.out.println("---------------------------------");
     		//	int trials = Integer.parseInt(input1);
     			String operator = input2;
     			//System.out.println("drug_id " +input1);
     			search_fda_approved(input1, file,operator,fda_approvedMap);
	   }
	}
	

	

	private static void search_fda_approved(String input1,
			RandomAccessFile file, String operator,
			TreeMap<String, ArrayList<Long>> fda_approvedMap) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\fda_approved.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	if (element[0].equals(input1) ) {
		    //		System.out.println("test10");
		    		if (fda_approvedMap.containsKey(input1)) {
		    //			System.out.println("test11");
		    			ArrayList<Long> list = fda_approvedMap.get(input1);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		}
		
		
		
	}




	private static void search_govt_funded(String input1,
			RandomAccessFile file, String operator,
			TreeMap<String, ArrayList<Long>> govt_fundedMap) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\govt_funded.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	if (element[0].equals(input1) ) {
	//	    		System.out.println("test10");
		    		if (govt_fundedMap.containsKey(input1)) {
  //		    			System.out.println("test11");
		    			ArrayList<Long> list = govt_fundedMap.get(input1);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		}
		
		
		
	}

	private static void search_controlled_study(String input1,
			RandomAccessFile file, String operator,
			TreeMap<String, ArrayList<Long>> controlled_studyMap) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\controlled_study.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	if (element[0].equals(input1) ) {
	//	    		System.out.println("test10");
		    		if (controlled_studyMap.containsKey(input1)) {
//		    			System.out.println("test11");
		    			ArrayList<Long> list = controlled_studyMap.get(input1);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		}
		
		
	}


	private static void search_reading(float reading, RandomAccessFile file,
			String operator, TreeMap<Float, ArrayList<Long>> readingMap) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\reading.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
			//System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	//System.out.println("test7");
		    	//System.out.println("patients "+ patients );
		    	if (Float.parseFloat(element[0]) == reading) {
		    		//System.out.println("test5");
		    		if (readingMap.containsKey(reading)) {
		    		//	System.out.println("test6");
		    			ArrayList<Long> list = readingMap.get(reading);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		    if (operator.equals(">=")){
			 if (Float.parseFloat(element[0]) >= reading ){
				 if (readingMap.containsKey(reading)) {
		    			ArrayList<Long> list1 = readingMap.get(Float.parseFloat(element[0]));
		    			for (Long offset : list1) {
					 displayRecords(file,offset,operator);
		    			}
				 //displayRecords(file,offset,operator);
				 }
			 }
		    }
	
		    if (operator.equals(">")){
				 if (Float.parseFloat(element[0]) > reading ){
					 if (readingMap.containsKey(reading)) {
			    			ArrayList<Long> list2 = readingMap.get(Float.parseFloat(element[0]));
			    			for (Long offset : list2) {
						 displayRecords(file,offset,operator);
			    			}
					 //displayRecords(file,Integer.parseInt(element[1]),operator);
			   
				 }
			    }
	}	
	}
	}


	private static void search_double_blind(String input1,
			RandomAccessFile file, String operator,
			TreeMap<String, ArrayList<Long>> double_blindMap) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\double_blind.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
			//System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	if (element[0].equals(input1) ) {
	//	    		System.out.println("test10");
		    		if (double_blindMap.containsKey(input1)) {
		//    			System.out.println("test11");
		    			ArrayList<Long> list = double_blindMap.get(input1);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		}
		
	}


	private static void search_dosage_mg(int dosage_mg, RandomAccessFile file,
			String operator, TreeMap<Integer, ArrayList<Long>> dosage_mgMap) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\dosage_mg.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
			//System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	//System.out.println("test7");
		    	//System.out.println("patients "+ patients );
		    	if (Integer.parseInt(element[0]) == dosage_mg) {
		    		//System.out.println("test5");
		    		if (dosage_mgMap.containsKey(dosage_mg)) {
		    		//	System.out.println("test6");
		    			ArrayList<Long> list = dosage_mgMap.get(dosage_mg);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		    if (operator.equals(">=")){
			 if (Integer.parseInt(element[0]) >= dosage_mg ){
				 if (dosage_mgMap.containsKey(dosage_mg)) {
		    			ArrayList<Long> list1 = dosage_mgMap.get(Integer.parseInt(element[0]));
		    			for (Long offset : list1) {
					 displayRecords(file,offset,operator);
		    			}
				 //displayRecords(file,offset,operator);
				 }
			 }
		    }
	
		    if (operator.equals(">")){
				 if (Integer.parseInt(element[0]) > dosage_mg ){
					 if (dosage_mgMap.containsKey(dosage_mg)) {
			    			ArrayList<Long> list2 = dosage_mgMap.get(Integer.parseInt(element[0]));
			    			for (Long offset : list2) {
						 displayRecords(file,offset,operator);
			    			}
					 //displayRecords(file,Integer.parseInt(element[1]),operator);
			   
				 }
			    }
	}	
	}	
	}
	private static void search_patients(int patients, RandomAccessFile file,
			String operator, TreeMap<Integer, ArrayList<Long>> patientsMap) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\patients.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
			//System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	//System.out.println("test7");
		    	//System.out.println("patients "+ patients );
		    	if (Integer.parseInt(element[0]) == patients) {
		    		//System.out.println("test5");
		    		if (patientsMap.containsKey(patients)) {
		    		//	System.out.println("test6");
		    			ArrayList<Long> list = patientsMap.get(patients);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		    if (operator.equals(">=")){
			 if (Integer.parseInt(element[0]) >= patients ){
				 if (patientsMap.containsKey(patients)) {
		    			ArrayList<Long> list1 = patientsMap.get(Integer.parseInt(element[0]));
		    			for (Long offset : list1) {
					 displayRecords(file,offset,operator);
		    			}
				 //displayRecords(file,offset,operator);
				 }
			 }
		    }
	
		    if (operator.equals(">")){
				 if (Integer.parseInt(element[0]) > patients ){
					 if (patientsMap.containsKey(patients)) {
			    			ArrayList<Long> list2 = patientsMap.get(Integer.parseInt(element[0]));
			    			for (Long offset : list2) {
						 displayRecords(file,offset,operator);
			    			}
					 //displayRecords(file,Integer.parseInt(element[1]),operator);
			   
				 }
			    }
	}	
	}
	}

	private static void search_company(String company, RandomAccessFile file,
			String operator, TreeMap<String, ArrayList<Long>> companyMap) throws IOException {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\company.ndx"));
	//	System.out.println("test1");
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
			//System.out.println("test2");	
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	//System.out.println("test3");
		    	
		    	if (element[0].equals(company) ){
		    		if (companyMap.containsKey(company)) {
		    			ArrayList<Long> list = companyMap.get(company);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}

		    		}
	    }
		    }
		}
	}
	private static void search_trials(int trials, RandomAccessFile file,
			String operator, TreeMap<Integer, ArrayList<Long>> trialsMap) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\trials.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	if (Integer.parseInt(element[0]) == trials) {
		    		if (trialsMap.containsKey(trials)) {
		    			ArrayList<Long> list = trialsMap.get(trials);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}
		    	}
		    		
		    	}
		    }
		    if (operator.equals(">=")){
			 if (Integer.parseInt(element[0]) >= trials ){
				 if (trialsMap.containsKey(trials)) {
		    			ArrayList<Long> list = trialsMap.get(Integer.parseInt(element[0]));
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
				 //displayRecords(file,Integer.parseInt(element[1]),operator);
		    			}
		    			
			 }
		    }
		}
		    if (operator.equals(">")){
				 if (Integer.parseInt(element[0]) > trials ){
					 if (trialsMap.containsKey(trials)) {
			    			ArrayList<Long> list = trialsMap.get(Integer.parseInt(element[0]));
			    			for (Long offset : list) {
						 displayRecords(file,offset,operator);
					 //displayRecords(file,Integer.parseInt(element[1]),operator);
			    			}
				 }
					// displayRecords(file,Integer.parseInt(element[1]),operator);
			   
				 }
			    }
	}	
	}
	private static void search_drug_id(String drug_id, RandomAccessFile file,
			String operator) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\drugid.ndx"));
	//	System.out.println("test1");
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
			//System.out.println("test2");	
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	//System.out.println("test3");
		    	
		    	if (element[0].equals(drug_id) ){
		    		if (drug_idMap.containsKey(drug_id)) {
		    			ArrayList<Long> list = drug_idMap.get(drug_id);
		    			for (Long offset : list) {
					 displayRecords(file,offset,operator);
		    			}

		    		}
		    	}
		    	
		    }
		    if (operator.equals(">=")){
			// if (element[0] >= drug_id ){
			//	 displayRecords(file,Integer.parseInt(element[1]),operator);
		   
			 }
		    
	
		    if (operator.equals(">")){
			//	 if (element[0] > drug_id ){
			//		 displayRecords(file,Integer.parseInt(element[1]),operator);
			   
				 }
			    
		}
			
		
	}

	private static void search_Id(int id, RandomAccessFile file,String operator) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String sCurrentLine = "";
		br = new BufferedReader(new FileReader("C:\\JavaWorkspace\\MyDatabase\\src\\id.ndx"));
		
		while ((sCurrentLine = br.readLine()) != null)  {
		//	System.out.println(sCurrentLine);
		
			 String[]   element = sCurrentLine.split("\t");
		//	 System.out.println("element "+ element[0] + "element1 "+ element[1]  );
		    if (operator.equals("=")){
		    	if (Integer.parseInt(element[0]) == id ){
					 displayRecords(file,Integer.parseInt(element[1]),operator);
		    	}
		    	
		    }
		    if (operator.equals(">=")){
			 if (Integer.parseInt(element[0]) >= id ){
				 displayRecords(file,Integer.parseInt(element[1]),operator);
		   
			 }
		    }
	
		    if (operator.equals(">")){
				 if (Integer.parseInt(element[0]) > id ){
					 displayRecords(file,Integer.parseInt(element[1]),operator);
			   
				 }
			    }
	}
			
          //if( operator == ">="){
		 // for(Entry<Integer, Long> entry : idMap.entrySet()) {
			//  int key = entry.getKey();
			 // long value = entry.getValue();

			  //System.out.println(key + " => " + value);
			//}
             
		/*	if (idMap.containsKey(id)) {
				long value = idMap.get(id);
				System.out.println("value  "+value);
				displayRecords(file, value,operator);
				
			} else {
				System.out.println("No record exists with this id");
			}*/
		//	System.out.println("---------------------------------");
		
	}

	private static void displayRecords(RandomAccessFile file, long pointerPosition,String operator) throws IOException {
		// TODO Auto-generated method stub
		//try {
		   // String company = "";
		   // byte companyname []= new byte[length];
		   // String drug_id = "";
		 //  System.out.println("operator "+ operator );
		  // if (operator == "=" |operator == ">=" | operator == "<=") 
		   //{	   
			   
	//	System.out.println("test4"); 		   
		    file.seek(pointerPosition);
		    int ID = file.readInt();
		   
		    int length=file.readByte();
		//	System.out.println("pointer "+ pointerPosition);
			
			//System.out.println("len : "+length);
			byte companyname []= new byte[length];
			byte drug_id []= new byte[6];
			//int length=file.readByte();
			 for (int i = 0 ; i < length; i++ ){
				  companyname[i] = file.readByte();
			//	System.out.println("cmpnyname "+companyname[i]);
			    //company += cmpnyname; 
			}
			 String company = new String(companyname);
			 
			//System.out.println("company "+ company);
			for (int i= 0 ; i <6 ; i++){
				drug_id[i] = file.readByte();	
			}
			String drug_id1 = new String(drug_id);
			
			//System.out.println("drug_id "+ drug_id1);
			int trials = file.readShort();
		//	System.out.println("trials "+ trials);
			int patients = file.readShort();
	//		System.out.println("patients "+ patients);
			int dosage_mg = file.readShort();
	//		System.out.println("dosage_mg "+ dosage_mg);
			float reading = file.readFloat();
	//		System.out.println("reading "+ reading);
			byte CommonByte=file.readByte();
	//		System.out.println("Commonbyte "+CommonByte);
	//		System.out.print("double_blind bit is:     ");
	//		System.out.println(8==(byte)(CommonByte & 8));
	//		System.out.print("controlled_study bit is: ");
	//		System.out.println(4==(byte)(CommonByte & 4));
	//		System.out.print("govt_funded bit is:      ");
	//		System.out.println(2==(byte)(CommonByte & 2));
	//		System.out.print("fda_approved bit is:     ");
	//		System.out.println(1==(byte)(CommonByte & 1));
		//	boolean double_blind = file.readFloat();

			System.out.print(ID +" "+ company + " " + drug_id1 + " "+trials + " "+patients +" "+dosage_mg + " " +reading + "");
			System.out.print(" ");
			System.out.print(8==(byte)(CommonByte & 8));
			System.out.print(" ");
			System.out.print(4==(byte)(CommonByte & 4));
			System.out.print(" ");
			System.out.print(2==(byte)(CommonByte & 2));
			System.out.print(" ");
			System.out.print(1==(byte)(CommonByte & 1));
			System.out.println();
			//file.read();
		/*	file.seek(pointerPosition);
			System.out.println("Record details are :");
			System.out.println("---------------------------------");
			System.out.println("ID: "+file.readInt()+"   Company_Name: "+displayRec("fname", file, 20)+"   Last_Name: "+displayRec("lname", file, 20)+"   Company_Name: "+displayRec("company", file, 80)+"   Address: "+ displayRec("address", file, 80)+"   City: " +displayRec("city", file, 20)+"   County: "+ displayRec("county", file, 20)+"   State: "+ displayRec("state", file, 2)+"   ZIP: "+displayRec("zip", file, 10)+"   Phone1: " + displayRec("ph1", file, 12)+"   Phone2: " + displayRec("ph2", file, 12)+"   Email: "+displayRec("email", file, 90)+"   Web: "+displayRec("web", file, 90));
			System.out.println("---------------------------------");
		*/
	//	} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}

	private static String displayRec(String field, RandomAccessFile file, int len) {
		// TODO Auto-generated method stub
		String value=null;
		try {
			 value = file.readUTF();
			for (int i = 0; i < len - value.length(); i++) {
				file.readByte();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}

	private static void udpatecompanyIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<String, ArrayList<Long>>> nameSet = companyMap.entrySet();
		for (Entry<String, ArrayList<Long>> listEntry : nameSet) {
			fileWriter.write(listEntry.getKey() + "\t");
			for (Long value : listEntry.getValue()) {
				fileWriter.write(String.valueOf(value) + "\t");
			}
			fileWriter.write("\n");
		}
		fileWriter.close();
	}

	private static void updateIdIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<Integer, Long>> idSet = idMap.entrySet();
		for (Entry<Integer, Long> entry : idSet) {
		//	System.out.println("key " +entry.getKey());
		//	System.out.println("value "+entry.getValue());
			writer.write(entry.getKey() + "\t");
			writer.write(String.valueOf(entry.getValue()));
			writer.write("\n");
		}
		writer.close();
	}
	private static void udpatedrugidIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<String, ArrayList<Long>>> idSet = drug_idMap.entrySet();
		for (Entry<String, ArrayList<Long>> entry : idSet) {
			//	System.out.println("key " +entry.getKey());
			//	System.out.println("value "+entry.getValue());
			
				
				writer.write(entry.getKey() + "\t");
				for (Long value : entry.getValue()) {
					writer.write(String.valueOf(value) + "\t");
				}
				writer.write("\n");
				//writer.write(String.valueOf(entry.getValue()));
				//writer.write("\n");
			}
		writer.close();
	}
	private static void udpatedouble_blindIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<String, ArrayList<Long>>> nameSet = double_blindMap.entrySet();
		for (Entry<String, ArrayList<Long>> listEntry : nameSet) {
			fileWriter.write(listEntry.getKey() + "\t");
			for (Long value : listEntry.getValue()) {
				fileWriter.write(String.valueOf(value) + "\t");
			}
			fileWriter.write("\n");
		}
		fileWriter.close();

	}
	
	private static void udpategovt_fundedIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<String, ArrayList<Long>>> nameSet = govt_fundedMap.entrySet();
		for (Entry<String, ArrayList<Long>> listEntry : nameSet) {
			fileWriter.write(listEntry.getKey() + "\t");
			for (Long value : listEntry.getValue()) {
				fileWriter.write(String.valueOf(value) + "\t");
			}
			fileWriter.write("\n");
		}
		fileWriter.close();
	
	}
	private static void udpatefda_approvedIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<String, ArrayList<Long>>> nameSet = fda_approvedMap.entrySet();
		for (Entry<String, ArrayList<Long>> listEntry : nameSet) {
			fileWriter.write(listEntry.getKey() + "\t");
			for (Long value : listEntry.getValue()) {
				fileWriter.write(String.valueOf(value) + "\t");
			}
			fileWriter.write("\n");
		}
		fileWriter.close();
	}

	private static void udpatecontrolled_studyIndexFile(
			String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<String, ArrayList<Long>>> nameSet = controlled_studyMap.entrySet();
		for (Entry<String, ArrayList<Long>> listEntry : nameSet) {
			fileWriter.write(listEntry.getKey() + "\t");
			for (Long value : listEntry.getValue()) {
				fileWriter.write(String.valueOf(value) + "\t");
			}
			fileWriter.write("\n");
		}
		fileWriter.close();
	}

	private static void udpatepatientsIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<Integer, ArrayList<Long>>> idSet = patientsMap.entrySet();
		for (Entry<Integer, ArrayList<Long>> entry : idSet) {
			//	System.out.println("key " +entry.getKey());
			//	System.out.println("value "+entry.getValue());
				writer.write(entry.getKey() + "\t");
				for (Long value : entry.getValue()) {
				writer.write(String.valueOf(value) + "\t");
				}
				writer.write("\n");
			}
		writer.close();
		
	}
	private static void udpatereadingIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<Float, ArrayList<Long>>> idSet = readingMap.entrySet();
		for (Entry<Float, ArrayList<Long>> entry : idSet) {
			//	System.out.println("key " +entry.getKey());
			//	System.out.println("value "+entry.getValue());
				writer.write(entry.getKey() + "\t");
				for (Long value : entry.getValue()) {
				writer.write(String.valueOf(value) + "\t");
				}
				writer.write("\n");
			}
		writer.close();
	}
	

	private static void udpatedosage_mgIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<Integer, ArrayList<Long>>> idSet = dosage_mgMap.entrySet();
		for (Entry<Integer, ArrayList<Long>> entry : idSet) {
			//	System.out.println("key " +entry.getKey());
			//	System.out.println("value "+entry.getValue());
				writer.write(entry.getKey() + "\t");
				for (Long value : entry.getValue()) {
				writer.write(String.valueOf(value) + "\t");
				}
				writer.write("\n");
			}
		writer.close();
	}
	

	private static void udpatetrialsIndexFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
		Set<Entry<Integer, ArrayList<Long>>> idSet = trialsMap.entrySet();
		for (Entry<Integer, ArrayList<Long>> entry : idSet) {
			//	System.out.println("key " +entry.getKey());
			//	System.out.println("value "+entry.getValue());
				writer.write(entry.getKey() + "\t");
				for (Long value : entry.getValue()) {
				writer.write(String.valueOf(value) + "\t");
				}
				writer.write("\n");
			}
		writer.close();
	}
	public void loadIDIndexMap(String fileName) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
		idMap = new TreeMap<Integer, Long>();
		String line = null;
		if (fileReader != null) {
			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split("\t");
				idMap.put(Integer.parseInt(tokens[0]),
						Long.parseLong(tokens[1]));
			}
		}
		fileReader.close();

	}
	private void loadCompanyMap(String fileName) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
		companyMap = new TreeMap<String, ArrayList<Long>>();
		String line = null;
		if (fileReader != null) {
			while ((line = fileReader.readLine()) != null) {
				String[] splitArray = line.split("\t");
				String key = splitArray[0];
				ArrayList<Long> list = new ArrayList<Long>();
				for (int i = 1; i < splitArray.length; i++) {
					list.add(Long.parseLong(splitArray[i]));
				}

				companyMap.put(key, list);
			}
		}
		fileReader.close();
	}
	private static void clearfile(String filename) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(filename);
		PrintWriter pw = new PrintWriter(fw);
		pw.write("");
		pw.flush(); 
		pw.close();
	/*	PrintWriter writer = new PrintWriter(filename);
		writer.print("");
		writer.close();*/
	}
	
	
	}

	