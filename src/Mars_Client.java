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
import java.util.TimeZone;

public class Mars_Client {

	public static void main(String[] args){
		
		URL url = null;
		try {
			url = new URL("http://marsweather.ingenology.com/v1/latest/?format=json"); //need to be able to input the id and the units
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
	    	    
	    //removes all useless symbols
	    line = line.replace('{', ' ');
	    line = line.replace('}', ' ');
	    line = line.replace(':', ' ');
	    line = line.replace(';', ' ');
	    line = line.replace(',', ' ');
	    
	    StringTokenizer tokens;
	    tokens = new StringTokenizer(line,  " \" "  ); //seperates data into tokens, seperate by a space
	    
	    
	    /*---------------------Below is different from normal weather------------*/
	    
	    //All variables are the same as normal weather, but some weren't needed
	    String tmp = null;
	    String description = "";//holds weather description
	    float temp = 0; //holds current temperature
	    float humidity = 0; //holds humidity
	    float pressure = 0; //holds air pressure
	    float windSpeed = 0; //holds wind speed
	    float windDir = 0; //holds direction of wind 
	    
	    
	  //as long as there are more tokens left, checks for the data needed
	    while(tokens.hasMoreTokens()){ 
	    	
	    	tmp = tokens.nextToken();
	    	
	    	//atmo_opacity is used instead of description
	    	if(tmp.equals("atmo_opacity")){//grabs mars weather description
	    		String token = tokens.nextToken();
	    		description = description.concat(token);
	    	}
	    	if(tmp.equals("temp")) //grabs current temp
	    		temp = Float.parseFloat(tokens.nextToken());
	    	if(tmp.equals("pressure")) //grabs current air pressure
	    		pressure = Float.parseFloat(tokens.nextToken());
	    	if(tmp.equals("humidity")) //grabs current humidity level
	    		humidity = Float.parseFloat(tokens.nextToken());
	    	if(tmp.equals("speed")) //grabs current wind Speed
	    		windSpeed = Float.parseFloat(tokens.nextToken());
	    	if(tmp.equals("deg")) //grabs current wind Direction
	    		windDir = Float.parseFloat(tokens.nextToken());
	    	
	    }
	    
	    System.out.println("Temperature: " + temp);
	    System.out.println("Wind Speed: " + windSpeed);
	    System.out.println("Wind Direction: " + windDir);
	    System.out.println("Air Pressure: " + pressure);
	    System.out.println("Humidity: " + humidity);
	    System.out.println("Description: " + description);
	    
	}
	
	
    
}
	