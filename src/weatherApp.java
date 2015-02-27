import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;


public class weatherApp {
	private weatherData current;
	private weatherData[] longTerm;
	private weatherData[] shortTerm;
	private location[] myLocations;	//** Why is this a boolean array in UML**?
	private customWeatherPref customView;
	
	public weatherApp(){
		current = new weatherData();
		longTerm = new weatherData[5];	//Covers next 5 days
		for (int i = 0; i < 5; i ++){
			longTerm[i] = new weatherData();
		}
		shortTerm = new weatherData[10];	//Covers 24 hours
		for (int i = 0; i < 10; i ++){
			shortTerm[i] = new weatherData();
		}
		myLocations = new location[5];
		for (int i = 0; i < 5; i ++){
			myLocations[i] = new location();
		}
		customView = new customWeatherPref();
	}
	
	public weatherData getCurrent() {
		return current;
	}
	
	public void setCurrent(weatherData current){
		this.current = current;
	}
	
	public weatherData[] getLongTerm() {
		return longTerm;
	}
	
	public void setLongTerm(weatherData[] longTerm){
		int smallest = this.longTerm.length;
		if (smallest > longTerm.length) smallest = longTerm.length;
		for (int i = 0; i < smallest; i ++){
			this.longTerm[i] = longTerm[i];
		}
	}
	
	public weatherData[] getShortTerm(){
		return shortTerm;
	}
	
	public void setShortTerm(weatherData[] shortTerm){
		int smallest = this.shortTerm.length;
		if (smallest > shortTerm.length) smallest = shortTerm.length;
		for (int i = 0; i < smallest; i ++){
			this.shortTerm[i] = shortTerm[i];
		}
	}
	
	public location[] getMyLocations(){
		return myLocations;
	}
	
	public void addLocation(location A){
		int j = 0;
		while (j < myLocations.length && myLocations[j].getCityID() != 0) j ++;
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
	
	public void removeLocation(location A){
		int i = 0;
		while (myLocations[i].getCityID() != A.getCityID()) i ++;
		while (i < myLocations.length - 1){
			myLocations[i] = myLocations[i + 1];
			i ++;
		}
		myLocations[i] = null;
	}
	
	public customWeatherPref getCustomView(){
		return customView;
	}
	
	public void setCustomView(customWeatherPref customView){
		this.customView = customView;
	}
	
	//fills weatherDataobject with JSON data
	//will eventually pass in a city ID and units
	public void grab(){
			
			URL url = null;
			try {
				url = new URL("http://api.openweathermap.org/data/2.5/weather?id=6058560&units=metric"); //need to be able to input the id and the units
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			URLConnection con = null;
			try {
				con = url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InputStream is = null;
		    try {
				is =con.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used to read input from JSON
		    
		    String line = null; //holds info grabbed
		
		    // read each line and write to System.out
		    try {
				while (br.ready()) { //line = br.readLine()) != null
				    line = br.readLine();
				    //System.out.println(line);
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    System.out.println(line);
		    
		    line = line.replace('{', ' ');
		    line = line.replace('}', ' ');
		    line = line.replace(':', ' ');
		    line = line.replace(';', ' ');
		    line = line.replace(',', ' ');
		    
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
		    	System.out.println(tmp);
		    	
		    	
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
		    System.out.println(formattedSunrise);
		    
		    //the following formats a date time out of the unix time provided by the JSON
		    Date date2 = new Date(sunset*1000L); // *1000 is to convert seconds to milliseconds
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of date
		    String formattedSunset = sdf2.format(date2);
		    System.out.println(formattedSunset);
		    
		    System.out.println(description);
		    System.out.println(minTemp);
		    
		    //gets the image for the proper weather description
		    BufferedImage pic = null;
		    try {
		        URL iconUrl = new URL("http://openweathermap.org/img/w/" + icon + ".png");
		        pic = ImageIO.read(iconUrl);
		    } catch (IOException e) {
		    }
		    
		    current.fill(lon, lat, pic, city, country,  temp, windSpeed, windDir, pressure, humidity, description, minTemp, maxTemp, formattedSunrise, formattedSunset);
		}
	
	public void grabShortTerm(){
		
		URL url = null;
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/forecast?id=6058560&units=metric"); //need to be able to input the id and the units
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URLConnection con = null;
		try {
			con = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InputStream is = null;
	    try {
			is =con.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(is)); //used to read input from JSON
	    
	    String line = null; //holds info grabbed
	
	    // read each line and write to System.out
	    try {
			while (br.ready()) { //line = br.readLine()) != null
			    line = br.readLine();
			    //System.out.println(line);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println(line);
	    
	    line = line.replace('{', ' ');
	    line = line.replace('}', ' ');
	    line = line.replace(':', ' ');
	    line = line.replace(';', ' ');
	    line = line.replace(',', ' ');
	    line = line.replace('[', ' ');
	    
	    System.out.println(line);
	    
	    String[] tokens; //used to hold separate, 3 hour weather calls
	    //StringTokenizer tokens;
	    tokens = line.split("weather");
	    
	    String tmp = null;
	    int i =0;
	    
	   while(i <=9){
	    	
	    	tmp = tokens[i];
	    	System.out.println(tmp);
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
		   System.out.println(tmp);
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
}