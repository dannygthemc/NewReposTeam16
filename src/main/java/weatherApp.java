package cs2212b.team16;

/**
 * This class is used to define the weather application, 
 * and makes use of the location and weatherData classes to perform its duties
 * 
 * @author Daniel Gilbert
 * @author Omar Abdel-Qader
 * @author James Crocker
 * @author Long Le
 * @author Angus Poole
 * @author Nicholas Teixeira
 */
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;



public class weatherApp {
	
	/**
	 * Attributes representing all the fields of the weatherApp class
	 */
	
	private weatherData current; //used to store current location data
	private weatherData[] longTerm; //used to store long term data
	private weatherData[] shortTerm; //used to store short term data
	private weatherData mars; //used to store Mars weather Data
	private location[] myLocations;	//used to store users preset locations
	private location currentLocation;	//Current location for user persistence
	private location visibleLocation;	//Location that is currently visible on app
	private String units; //used to keep track of type of unit being used
	
	/**
	 * Constructor instantiates an object of the class
	 * and initializes all arrays.
	 */
	public weatherApp(){
		
		current = new weatherData();
		mars = new weatherData();
		longTerm = new weatherData[8];	//Covers next 5 days
		for (int i = 0; i < 5; i ++){
			longTerm[i] = new weatherData();
		}
		shortTerm = new weatherData[10];	//Covers 24 hours
		for (int i = 0; i < 10; i ++){
			shortTerm[i] = new weatherData();
		}
		myLocations = new location[5]; //holds five locations
		for (int i = 0; i < 5; i ++){
			myLocations[i] = new location();
		}
		//TODO: populate current location with file current location on startup
		currentLocation = new location();
		visibleLocation = currentLocation;
		//visibleLocation.setCityID(6058560);
		units = "metric";
	}
	
	/**
	 * The storePref method is used to store the location at the end of the program run,
	 * has no parameters or return values 
	 */
	public void storePref() throws IOException{
		File store = new File("prefFile.txt");
		if(store.exists() == true){
			store.delete();
		}
		store.createNewFile();
		
		ObjectOutputStream out = null;

			out = new ObjectOutputStream(
						  new FileOutputStream(store));
		
		  
		 out.writeObject(currentLocation); //write the person to the file
		  
		 out.close();
		  
	  }
	
	/**
	 * The loadPref method is used to load the location stored at the end of the last program run
	 * 
	 * @return location	the city stored in the last run of the program  
	 */
	public location loadPref() throws ClassNotFoundException, IOException{
		
		File store = new File("prefFile.txt");
		ObjectInputStream in = null;

			in = new ObjectInputStream(
					  new FileInputStream(store));
		
		  
		  location obj = (location)in.readObject(); //write the person to the file
		  
		  in.close();
		  return obj;
		  
	  }
	
	/**
	 * Getter method for current unit of measurement 
	 *
	 * @return String containging the units
	 */
	public String getUnits(){
		return units;
	}
	
	/**
	 * Setter method for current unit of measurement
	 * 
	 * @param type a String containing the current unit 
	 */
	public void setUnits(String type){
		units = type;
	}
	
	/**
	 * The getMars method returns mars location data object
	 * 
	 * @return location object holding mars Data
	 */
	public weatherData getMars(){
		return mars;
	}
	
	/**
	 * The getCurrentLocation method returns current location data object
	 * 
	 * @return location object holding current location
	 */
	public location getCurrentLocation(){
		return currentLocation;
	}

	
	/**
	 * The setCurrentLocation method sets current location data object
	 * 
	 * @param location object A
	 */
	public void setCurrentLocation(location A){
		currentLocation = A;
	}

	/**
	 * The getVisibleLocation method returns visible location data object 
	 * 
	 * @return location object holding visible location
	 */
	public location getVisibleLocation(){
		return visibleLocation;
	}

	/**
	 * The setVisibleLocation method returns visible location data object 
	 * 
	 * @param location object to be set to visible
	 */
	public void setVisibleLocation(location A){
		visibleLocation = A;
	}
	
	/**
	 * The getCurrent method returns current weather data object
	 *
	 * @return weatherData object holding current weather Data
	 */
	public weatherData getCurrent() {
		return current;
	}
	
	/**
	 * The setCurrent method allows setting of the current weatherData object
	 */
	public void setCurrent(weatherData current){
		this.current = current;
	}
	
	/**
	 * The getLongTerm method returns the longTerm weather data array
	 * 
	 * @return weatherData[] an array containing long-term weather data 
	 */
	public weatherData[] getLongTerm() {
		return longTerm;
	}
	
	/**
	 * The setLongTerm method is used to fill the longTerm array with weather Data
	 * 
	 * @param weatherData[] an array containing long-term weather data
	 */
	public void setLongTerm(weatherData[] longTerm){
		int smallest = this.longTerm.length;
		if (smallest > longTerm.length) smallest = longTerm.length;
		for (int i = 0; i < smallest; i ++){
			this.longTerm[i] = longTerm[i];
		}
	}
	
	/**
	 * The setShortTerm method is used to fill the shortTerm array with weather Data
	 * 
	 * @param weatherData[] an array containing short-term weather data
	 */
	public void setShortTerm(weatherData[] shortTerm){
		int smallest = this.shortTerm.length;
		if (smallest > shortTerm.length) smallest = shortTerm.length;
		for (int i = 0; i < smallest; i ++){
			this.shortTerm[i] = shortTerm[i];
		}
	}
	
	/**
	 * The getShortTerm method returns the shorTerm data array
	 *
	 * @return weatherData[] an array containing short-term weather data
	 */
	public weatherData[] getShortTerm(){
		return shortTerm;
	}
	
	/**
	 * The getMyLocations method returns the array of user's saved location data
	 * 
	 * @return location[] an array containing the user's saved locations 
	 */
	public location[] getMyLocations(){
		return myLocations;
	}
	
	/**
	 * The addLocation method adds a location to the myLocation array
	 * 
	 * @param location the city to be added to the array  	 
	 */
	public void addLocation(location A){
		int j = 0;
		while (j < myLocations.length && !myLocations[j].getName().equals("Default")) j ++;
		if (j < myLocations.length) myLocations[j] = A;
		else {
			location[] temp = new location[myLocations.length + 1];
			for (int i = 0; i < temp.length - 1; i ++){
				temp[i] = myLocations[i];
			}
			temp[myLocations.length] = A;
			myLocations = temp;
		}
	}
	
	/**
	 * The removeLocation method removes a location from the location array
	 * 
	 * @param name a String representing the name of the location
	 * @param code a String representing the country code of the location
	 */
	public void removeLocation(String name, String code){
		int i = 0;
		while (!myLocations[i].getName().equals(name) && !myLocations[i].getCountryCode().equals(code)) i ++;
		while (i < myLocations.length - 1){
			myLocations[i] = myLocations[i + 1];
			i ++;
		}
		myLocations[i] = new location();
	}
	
	
	/**
	 * The grab method fills current weatherData object with JSON data
	 * 
	 * @param cityId a unique int representing the city, used to get weather information
	 * @param units the unit of measurement to represent results
	 */
	public void grab(int cityId, String units) throws MalformedURLException, IOException{
			
			String Id = Integer.toString(cityId);
			URL url = null; //used to hold the desired URL address
			try {
				url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + Id + "&units=" + units); //need to be able to input the id and the units
			} catch (MalformedURLException e) {
				throw new MalformedURLException("improper URL");
			}
			
			URLConnection con = null;//used to establish a connection to the desired address
			try {
				con = url.openConnection();
			} catch (IOException e) {
				throw new IOException("could not connect");
			}
			
			InputStream is = null; //used to read from the connection
		    try {
				is =con.getInputStream();
			} catch (IOException e) {
				throw new IOException("could not create stream");
			}
			
		    BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used to read input from JSON
		    
		    String line = null; //holds info grabbed
		
		    // read each line and write to System.out
		    try {
				while (br.ready()) { //line = br.readLine()) != null
				    line = br.readLine();
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		    //formats the string for easy parsing
		    line = line.replace('{', ' ');
		    line = line.replace('}', ' ');
		    line = line.replace(':', ' ');
		    line = line.replace(';', ' ');
		    line = line.replace(',', ' ');
		    
		    //used to separate segments of JSON 
		    StringTokenizer tokens;
		    tokens = new StringTokenizer(line,  " \" "  );
		    
		    String tmp = null; //used to hold tokens
		    String city = null; //holds city name
		    String icon = null; //holds image icon name
		    float lon = 0; //holds longitude value
		    float lat = 0; //holds latitude value
		    String country = null;//holds country code
		    long sunrise = 0; //holds sunrise time
		    long sunset = 0; //holds sunset time
		    String description = "";//holds weather description
		    float temp = 0; //holds current temperature
		    float minTemp = 0; //holds min temperature
		    float maxTemp = 0; //holds max temperature
		    float humidity = 0; //holds humidity
		    float pressure = 0; //holds air pressure
		    float windSpeed = 0; //holds wind speed
		    float windDir = 0; //holds direction of wind 
		    
		    
		    
		    while(tokens.hasMoreTokens()){
		    	
		    	tmp = tokens.nextToken();
		    	
		    	
		    	if(tmp.equals("lon")) //grabs longitude data
		    		lon = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("lat")) //grabs latitude data
		    		lat = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("country")) //grabs country name
		    		country = tokens.nextToken();
		    	if(tmp.equals("sunrise")) //grabs sunrise time
		    		sunrise = Long.parseLong(tokens.nextToken());
		    	if(tmp.equals("sunset")) //grabs sunset time
		    		sunset = Long.parseLong(tokens.nextToken());
		    	if(tmp.equals("description")){//grabs weather description
		    		String token = tokens.nextToken();
		    		while(token.equals("icon") == false){
		    			description = description.concat(token) + " ";
		    			token = tokens.nextToken();
		    		}
		    		icon = tokens.nextToken(); //grabs icon type
		    	}
		    	if(tmp.equals("temp")) //grabs current temp
		    		temp = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("temp_min")) //grabs min temp
		    		minTemp = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("temp_max")) //grabs max temp
		    		maxTemp = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("pressure")) //grabs current air pressure
		    		pressure = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("humidity")) //grabs current humidity level
		    		humidity = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("speed")) //grabs current wind Speed
		    		windSpeed = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("deg")) //grabs current wind Direction
		    		windDir = Float.parseFloat(tokens.nextToken());
		    	if(tmp.equals("name")) //grabs current wind Direction
		    		city = tokens.nextToken();
		    	
		    
		    }
		    
		    //the following formats a date time out of the unix time provided by the JSON
		    Date date = new Date(sunrise*1000L); // *1000 is to convert seconds to milliseconds
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of date
		    String formattedSunrise = sdf.format(date);
		    
		    //the following formats a date time out of the unix time provided by the JSON
		    Date date2 = new Date(sunset*1000L); // *1000 is to convert seconds to milliseconds
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of date
		    String formattedSunset = sdf2.format(date2);
		    
		    
		    //gets the image for the proper weather description
		    BufferedImage pic = null;
		    try {
		        URL iconUrl = new URL("http://openweathermap.org/img/w/" + icon + ".png");
		        pic = ImageIO.read(iconUrl);
		    } catch (IOException e) {
		    }
		    
		    //fills the current object with the data
		    current.fill(lon, lat, pic, city, country,  temp, windSpeed, windDir, pressure, humidity, description, minTemp, maxTemp, formattedSunrise, formattedSunset);
	}
	
	/**
	 * The grabShortTerm method grabs the data for the short term data structure
	 * 
	 * @param cityId a unique int representing the city, used to get weather information
	 * @param units the unit of measurement to represent results
	 */
	public void grabShortTerm(int cityId, String units) throws MalformedURLException, IOException{
		
		String Id = Integer.toString(cityId);
		URL url = null; //used to hold desired URL
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=" + Id + "&units=" + units); //need to be able to input the id and the units
		} catch (MalformedURLException e) {
			throw new MalformedURLException("malformed URL");
		}
		
		URLConnection con = null; //used to establish connection
		try {
			con = url.openConnection();
		} catch (IOException e) {
			throw new IOException("could not connect");
		}
		
		InputStream is = null; //used to read from connection
	    try {
			is =con.getInputStream();
		} catch (IOException e) {
			throw new IOException("could not create Stream");
		}
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used to read input from JSON
	    
	    String line = null; //holds info grabbed
	
	    // read each line and write to System.out
	    try {
			while (br.ready()) { //line = br.readLine()) != null
			    line = br.readLine();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    //formats the string for easy parsing
	    line = line.replace('{', ' ');
	    line = line.replace('}', ' ');
	    line = line.replace(':', ' ');
	    line = line.replace(';', ' ');
	    line = line.replace(',', ' ');
	    line = line.replace('[', ' ');
	    
	    
	    String[] tokens; //used to hold separate, 3 hour weather calls
	    //StringTokenizer tokens;
	    tokens = line.split("weather");
	    
	    String tmp = null;
	    int i =0;
	   
	    //separates 10 lines of data into different string
	    //first line holds city info, next 9 hold weather data in 3 hour increments
	   while(i <=9){
	    	
	    	tmp = tokens[i];
	    	i++;
	    }
	   
	   StringTokenizer tokens2; //used to divide up each 3 hour period
	   float lon = 0; //used to temporarily hold longitude
	   float lat = 0; //used to temporarily hold latitude
	   String count = null; //holds country name
	   String name = null; //holds city name
	   float temp = 0; //holds temperature
	   float minTemp = 0; //holds min temperature
	   float maxTemp = 0; //holds max temperature
	   String descrip = ""; //holds weather description
	   String icon = null; //holds name of weather icon
	   BufferedImage pic = null; //holds picture of weather icon
	   String time = null;
	   
	   //initialize array of weatherData objects
	   for(int j = 0; j <=9; j++){
		   
		   shortTerm[j] = new weatherData();
	   }
	   
	   String hold = null; //holds tokens temporarily
	   tokens2 = new StringTokenizer(tokens[0],  " \" "  );
	   
	   //used to grab the first line of data
	   //i.e. city, country, coordinates
	   while(tokens2.hasMoreTokens()){
		   tmp = tokens2.nextToken();
		   if(tmp.equals("lon")) //grabs longitude data
	    		lon = Float.parseFloat(tokens2.nextToken());
	    	if(tmp.equals("lat")) //grabs latitude data
	    		lat = Float.parseFloat(tokens2.nextToken());
	    	if(tmp.equals("country")) //grabs country name
	    		count = (tokens2.nextToken());
	    	if(tmp.equals("name")) //grabs country name
	    		name = (tokens2.nextToken()); 	
	   }
	   
	   //sets data of the first weatherData object, location data
	   shortTerm[0].setLon(lon);
	   shortTerm[0].setLat(lat);
	   shortTerm[0].setCount(count);
	   shortTerm[0].setName(name);
	   
	   //used to grab data for the next 8, 3 hour periods
	   for(int k =1; k <=9; k++){
		   
		   descrip = "";//reset description
		   time = ""; //reset time
		   tokens2 = new StringTokenizer(tokens[k],  " \" "  ); //tokenizes current line of data
		   //grabs needed data 
		   while(tokens2.hasMoreTokens()){
			   
			    tmp = tokens2.nextToken();
		    	if(tmp.equals("temp")) //grabs current temp
		    		temp = Float.parseFloat(tokens2.nextToken());
		    	if(tmp.equals("dt_txt")){ //grabs time of grab
		    		String tk = tokens2.nextToken();
		    		while(tk.equals("dt") == false){
		    			time = time.concat(tk) + " ";
		    			tk = tokens2.nextToken();
		    		}
		    	}
		    	if(tmp.equals("temp_min")) //grabs min temp
		    		minTemp = Float.parseFloat(tokens2.nextToken());
		    	if(tmp.equals("temp_max")) //grabs max temp
		    		maxTemp = Float.parseFloat(tokens2.nextToken());
		    	if(tmp.equals("description")){//grabs weather description
		    		String token = tokens2.nextToken();
		    		while(token.equals("icon") == false){
		    			descrip = descrip.concat(token) + " ";
		    			token = tokens2.nextToken();
		    		}
		    		icon = tokens2.nextToken(); //grabs icon type
		    	}
		   }
		   
		   //gets the image for the proper weather description
		    
		    try {
		        URL iconUrl = new URL("http://openweathermap.org/img/w/" + icon + ".png");
		        pic = ImageIO.read(iconUrl);
		    } catch (IOException e) {
		    }
		    
		    //sets the values of the current weatherData object
		    shortTerm[k].setTemp(temp);
		    shortTerm[k].setMin(minTemp);
		    shortTerm[k].setMax(minTemp);
		    shortTerm[k].setCondit(descrip);
		    shortTerm[k].setIcon(pic);
		    shortTerm[k].setSunrise(time); //used to hold the time info was grabbed at instead of actually the sunrise
		   
	   }
	   
	   
	}
	
	/**
	 * The grabLongTerm method grabs the data for the long term data structure
	 * 
	 * @param cityId a unique int representing the city, used to get weather information
	 * @param units the unit of measurement to represent results
	 */
	public void grabLongTerm(int cityId, String units)throws MalformedURLException, IOException{
		
		String Id = Integer.toString(cityId);
		URL url = null;
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?id=" + Id + "&units=" + units); //need to be able to input the id and the units
		} catch (MalformedURLException e) {
			throw new MalformedURLException("malformed URL");
		}
		
		URLConnection con = null;
		try {
			con = url.openConnection();
		} catch (IOException e) {
			throw new IOException("could not connect");
		}
		
		InputStream is = null;
	    try {
			is =con.getInputStream();
		} catch (IOException e) {
			throw new IOException("could not creat stream");
		}
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used to read input from JSON
	    
	    String line = null; //holds info grabbed
	
	    // read each line
	    try {
			while (br.ready()) { //line = br.readLine()) != null
			    line = br.readLine();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    line = line.replace('{', ' ');
	    line = line.replace('}', ' ');
	    line = line.replace(':', ' ');
	    line = line.replace(';', ' ');
	    line = line.replace(',', ' ');
	    line = line.replace('[', ' ');
	    
	    
	    String[] tokens; //used to hold separate 7 day calls
	    //StringTokenizer tokens;
	    tokens = line.split("temp");
	    
	    String tmp = null;
	    int i =0;
	    
	   while(i <=5){
	    	
	    	tmp = tokens[i];
	    	i++;
	    }
	   
	   StringTokenizer tokens2; //used to divide up each 3 hour period
	   float lon = 0; //used to temporarily hold longitude
	   float lat = 0; //used to temporarily hold latitude
	   long date = 0; //used to hold date in long form
	   String count = null; //holds country name
	   String name = null; //holds city name
	   float temp = 0; //holds temperature
	   float minTemp = 0; //holds min temperature
	   float maxTemp = 0; //holds max temperature
	   String descrip = ""; //holds weather description
	   String icon = null; //holds name of weather icon
	   BufferedImage pic = null; //holds picture of weather icon
	   
	   //initialize array of weatherData objects
	   for(int j = 0; j <=7; j++){
		   
		   longTerm[j] = new weatherData();
	   }
	   
	   String hold = null; //holds tokens temporarily
	   tokens2 = new StringTokenizer(tokens[0],  " \" "  );
	   
	   //used to grab the first line of data
	   //i.e. city, country, coordinates
	   while(tokens2.hasMoreTokens()){
		   tmp = tokens2.nextToken();
		   if(tmp.equals("lon")) //grabs longitude data
	    		lon = Float.parseFloat(tokens2.nextToken());
	    	if(tmp.equals("lat")) //grabs latitude data
	    		lat = Float.parseFloat(tokens2.nextToken());
	    	if(tmp.equals("country")) //grabs country name
	    		count = (tokens2.nextToken());
	    	if(tmp.equals("name")) //grabs country name
	    		name = (tokens2.nextToken());
	    	if(tmp.equals("dt")) //grabs date of first weather data
	    		date = Long.parseLong(tokens2.nextToken()); 
	   }
	   
	   Date date2 = new Date(date*1000L); // *1000 is to convert seconds to milliseconds
	   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of date
	   String formattedDate = sdf2.format(date2);
	   //sets data of the first weatherData object, location data
	   longTerm[0].setLon(lon);
	   longTerm[0].setLat(lat);
	   longTerm[0].setCount(count);
	   longTerm[0].setName(name);
	   longTerm[1].setSunrise(formattedDate); //sets date of the first one
	   
	   //used to grab data for the next 7 days
	   for(int k =1; k <= 5; k++){
		   
		   tokens2 = new StringTokenizer(tokens[k],  " \" "  ); //tokenizes current line of data
		   descrip = "";
		   //grabs needed data 
		   while(tokens2.hasMoreTokens()){
			   
			    
			    tmp = tokens2.nextToken();
		    	if(tmp.equals("day")) //grabs current temp
		    		temp = Float.parseFloat(tokens2.nextToken());
		    	if(tmp.equals("dt")) //grabs date for the next day
		    		date = Long.parseLong(tokens2.nextToken());
		    	if(tmp.equals("min")) //grabs min temp
		    		minTemp = Float.parseFloat(tokens2.nextToken());
		    	if(tmp.equals("max")) //grabs max temp
		    		maxTemp = Float.parseFloat(tokens2.nextToken());
		    	if(tmp.equals("description")){//grabs weather description
		    		String token = tokens2.nextToken();
		    		while(token.equals("icon") == false){
		    			descrip = descrip.concat(token) + " ";
		    			token = tokens2.nextToken();
		    		}
		    		icon = tokens2.nextToken(); //grabs icon type
		    	}
		    	
		   }
		   
		   //gets the image for the proper weather description
		    
		    try {
		        URL iconUrl = new URL("http://openweathermap.org/img/w/" + icon + ".png");
		        pic = ImageIO.read(iconUrl);
		    } catch (IOException e) {
		    }
		    
		    //sets the values of the long Term weatherData object
		    longTerm[k].setTemp(temp);
		    longTerm[k].setMin(minTemp);
		    longTerm[k].setMax(maxTemp);
		    longTerm[k].setCondit(descrip);
		    longTerm[k].setIcon(pic);
		    
		    if(k != 7){//as long as we're not on the last day
		       date2 = new Date(date*1000L); // *1000 is to convert seconds to milliseconds
		 	   sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of date
		 	   formattedDate = sdf2.format(date2);
		 	  longTerm[k+1].setSunrise(formattedDate);
		    }
		   
	   }
	}
	
	/**
	 * The grabMars method grabs and formats the data for the Mars weather structure
	 * 
	 * @param units a String definining unit type
	 */
	public void grabMars(String units) throws IOException{
		
		URL url = null;
		try {
			url = new URL("http://marsweather.ingenology.com/v1/latest/?format=json"); //need to be able to input the id and the units
		} catch (MalformedURLException e) {
			throw new MalformedURLException("malformed URL");
		}
		
		URLConnection con = null;
		try {
			con = url.openConnection();
		} catch (IOException e) {
			throw new IOException("could not connect");
		}
		
		InputStream is = null;
	    try {
			is =con.getInputStream();
		} catch (IOException e) {
			throw new IOException("could not creat stream");
		}
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used to read input from JSON
	    
	    String line = null; //holds info grabbed
	
	    // read each line and write to System.out
	    try {
			while (br.ready()) { //line = br.readLine()) != null
			    line = br.readLine();
			   // System.out.println(line);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	    
	    //removes all useless symbols
	    line = line.replace('{', ' ');
	    line = line.replace('}', ' ');
	    line = line.replace(':', ' ');
	    line = line.replace(';', ' ');
	    line = line.replace(',', ' ');
	    
	    StringTokenizer tokens;
	    tokens = new StringTokenizer(line,  " \" "  ); //separates data into tokens, seperate by a space
	    
	    //All variables are the same as normal weather, but some weren't needed
	    String tmp = null;
	    String description = "";//holds weather description
	    float humidity = 0; //holds humidity
	    float pressure = 0; //holds air pressure
	    float windSpeed = 0; //holds wind speed
	    float minTemp = 0; //holds min temp
	    float maxTemp = 0; //holds max temp
	    String sunrise = ""; //holds sunrise time
	    String sunset = ""; //holds sunset time
	    String windDir = ""; //holds direction of wind 
	    
	    
	  //as long as there are more tokens left, checks for the data needed
	    while(tokens.hasMoreTokens()){ 
	    	tmp = tokens.nextToken();
	    	
	    	//atmo_opacity is used instead of description
	    	if(tmp.equals("atmo_opacity")){//grabs mars weather description
	    		String token = tokens.nextToken();
	    		while(token.equals("season") == false){
	    			description = description.concat(token) + " ";
	    			token = tokens.nextToken();
	    		}
	    	}
	    	
	    	if(units.equals("imperial")){ //if we're looking for imperial
	    		if(tmp.equals("min_temp_fahrenheit")) //grabs min
		    		minTemp = Float.parseFloat(tokens.nextToken());
	    		if(tmp.equals("max_temp_fahrenheit")) //grabs max
		    		maxTemp = Float.parseFloat(tokens.nextToken());
	    	}
	    	else{ //otherwise, celsius
	    		if(tmp.equals("min_temp")) //grabs min
		    		minTemp = Float.parseFloat(tokens.nextToken());
	    		if(tmp.equals("max_temp")) //grabs max
		    		maxTemp = Float.parseFloat(tokens.nextToken());
	    	}
	    	
	    	if(tmp.equals("sunrise")) //grabs sunrise time
	    		sunrise = tokens.nextToken();
	    	if(tmp.equals("sunset")) //grabs sunset time
	    		sunset = tokens.nextToken();
	    	if(tmp.equals("pressure")) //grabs current air pressure
	    		pressure = Float.parseFloat(tokens.nextToken());
	    	if(tmp.equals("abs_humidity")){ //grabs current humidity level
	    		if ((tokens.nextElement()).toString().compareTo("null")==0){
	    			humidity = 99f;
	    		}
	    		else{
	    			humidity = Float.parseFloat(tokens.nextToken());
	    		}
	    	}
	    	if(tmp.equals("wind_speed")){ //grabs current wind speed
	    		if ((tokens.nextElement()).toString().compareTo("null")==0){
	    			windSpeed = 99f;
	    		}
	    		else{
	    			windSpeed = Float.parseFloat(tokens.nextToken());
	    		}
	    		
	    	}
	    	if(tmp.equals("wind_direction")){ //grabs current wind Direction
	    		String tmp2 = tokens.nextToken();
	    		if (tmp2.compareTo("--") == 0){
	    			windDir = "N/A";
	    		}
	    		else{
	    			windDir = tmp2;
	    		}
	    		
	    	}
	    	
	    }
	    
	   mars.setCondit(description);
	   mars.setMin(minTemp);
	   mars.setMax(maxTemp);
	   mars.setSpeed(windSpeed);
	   if(windDir.equals("N/A")){
		   mars.setDir(99);
	   }
	   else{
		   mars.setDir(Float.parseFloat(windDir));
	   }
	   mars.setPress(pressure);
	   mars.setHumid(humidity);
	   mars.setSunrise(sunrise);
	   mars.setSunset(sunset);
	    
	}
}