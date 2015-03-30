package cs2212b.team16;

/**
 * This class is used to define the weather information of a city, and
 * holds all data in one convenient object to allow for quick data access
 * 
 * @author Daniel Gilbert
 * @author Omar Abdel-Qader
 * @author James Crocker
 * @author Long Le
 * @author Angus Poole
 */
import java.awt.image.BufferedImage;

public class weatherData {

	/**
	 * Attributes representing all the fields of a WeatherData object 
	 */
	
	private BufferedImage weatherIcon; //holds weather icon
	private String name; //holds city name
	private String country; //holds country name
	private float temp; //holds temp data;
	private float windSpeed; //holds wind speed
	private float windDir; //holds wind direction in degrees
	private float pressure; //holds air pressure
	private float humidity; //holds humidity index
	private String condition; //holds description of weather condition
	private float minTemp; //holds expected min temp
	private float maxTemp; //holds expected max temp
	private String sunrise; //holds expected time of sunrise
	private String sunset; //holds expected times of sunset
	private float lon; //holds longitude data
	private float lat; //holds latitude date
	
	/**
	 * Constructor instantiates an object of the class
	 */
	public weatherData(){
		
	}
	
	/**
	 * Fill method assigns values to all variables in the WeatherData object, and does not return anything
	 * 
	 * @param longit the longitude of the location
	 * @param latit the latitiude of the location
	 * @param pic the picture associated with the weather in this location
	 * @param city the name of the location 
	 * @param count the country code associated with the city 
	 * @param temp2 the current temperature
	 * @param windSpeed2 the current wind speed
	 * @param windDir2 the current wind direction
	 * @param pressure2 the current air pressure
	 * @param humidity2 the current humidity
	 * @param condit the current sky condition
	 * @param minTemp2 the minimum temperature at the specified location
	 * @param maxTemp2 the maximum temperature at the specified location
	 * @param rise the local sunrise time 
	 * @param set the local sunset time
	 */
	public void fill(float longit, float latit, BufferedImage pic, String city, String count, float temp2, float windSpeed2, float windDir2, float pressure2, float humidity2, String condit, float minTemp2, float maxTemp2, String rise, String set){
		
		lon = longit;
		lat = latit;
		weatherIcon = pic;
		name = city;
		country = count;
		temp = temp2;
		windSpeed = windSpeed2;
		windDir = windDir2;
		pressure = pressure2;
		humidity = humidity2;
		condition = condit;
		minTemp = minTemp2;
		maxTemp = maxTemp2;
		sunrise = rise;
		sunset = set;
	}
	
	/* Setter Methods */
	
	/**
	 * Setter method for longitude of the city
	 * 
	 * @param longitude a float that represents longitude
	 */
	public void setLon(float longitude){
		lon = longitude;
	}
	
	/**
	 * Setter method for latitude of the city
	 * 
	 * @param latitude a float that represents latitude
	 */
	public void setLat(float latitude){
		lat= latitude; 
	}
	
	/**
	 * Setter method for the name of the city
	 * 
	 * @param nm a String containing the name
	 */
	public void setName(String nm){
		name = nm;
	}
	
	/**
	 * Setter method for the country code
	 * 
	 * @param count a String containing the country code
	 */
	public void setCount(String count){
		country = count; 
	}

	/**
	 * Setter method for temperature
	 * 
	 * @param tmp a float that represents temperature
	 */
	public void setTemp(float tmp){
		temp = tmp;
	}
	
	/**
	 * Setter method for wind speed
	 * 
	 * @param speed a float that represents wind speed
	 */
	public void setSpeed(float speed){
		windSpeed = speed;
	}
	
	/**
	 * Setter method for wind direction
	 * 
	 * @param dir a float that represents wind direction
	 */
	public void setDir(float dir){
		windDir = dir;
	}
	
	/**
	 * Setter method for atmospheric pressure
	 * 
	 * @param press a float that represents pressure
	 */
	public void setPress(float press){
		pressure = press;
	}
	
	/**
	 * Setter method for humidity
	 * 
	 * @param humid a float that represents humidity
	 */
	public void setHumid(float humid){
		humidity = humid;
	}
	
	/**
	 * Setter method for sky condition
	 * 
	 * @param condit a String containing the current sky condition
	 */
	public void setCondit(String condit){
		condition = condit;
	}
	
	/**
	 * Setter method for minimum temperature
	 * 
	 * @param min a float that represents minimum temperature
	 */
	public void setMin(float min){
		minTemp = min;
	}
	
	/**
	 * Setter method for maximum temperature
	 * 
	 * @param max a float that represents maximum temperature
	 */
	public void setMax(float max){
		maxTemp = max;
	}
	
	/**
	 * Setter method for sunrise time
	 * 
	 * @param rise a String containing the sunrise time
	 */
	public void setSunrise(String rise){
		sunrise = rise;
	}
	
	/**
	 * Setter method for sunset time
	 * 
	 * @param rise a String containing the sunset time
	 */
	public void setSunset(String set){
		sunset = set;
	}
	
	/**
	 * Setter method for the weather icon
	 * 
	 * @param icon a BufferedImage containing the icon representing the weather
	 */
	public void setIcon(BufferedImage icon){
		weatherIcon = icon;
	}
	
	/* Getter Methods */
	
	/**
	 * Getter method for longitude
	 * 
	 * @return float representing the longitude
	 */
	public Float getLon(){
		return lon;
	}
	
	/**
	 * Getter method for longitude
	 * 
	 * @return float representing the latitude
	 */
	public Float getLat(){
		return lat; 
	}
	
	/**
	 * Getter method for longitude
	 * 
	 * @return String containting the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter method for the country code
	 * 
	 * @return String containing the country code 
	 */
	public String getCount(){
		return country; 
	}
	
	/**
	 * Getter method for temperature
	 * 
	 * @return float representing the temperature
	 */
	public float getTemp(){
		return temp;
	}
	
	/**
	 * Getter method for wind speed
	 * 
	 * @return float representing the wind speed
	 */
	public float getSpeed(){
		return windSpeed;
	}
	
	/**
	 * Getter method for wind direction
	 * 
	 * @return float representing the wind direction
	 */
	public float getDir(){
		return windDir;
	}
	
	/**
	 * Getter method for atmospheric pressure
	 * 
	 * @return float representing the pressure
	 */
	public float getPress(){
		return pressure;
	}
	
	/**
	 * Getter method for humidity
	 * 
	 * @return float representing the humidity
	 */
	public float getHumid(){
		return humidity;
	}
	
	/**
	 * Getter method for sky condition
	 * 
	 * @return String representing the sky condition
	 */
	public String getCondit(){
		return condition;
	}
	
	/**
	 * Getter method for minimum temperature
	 * 
	 * @return float representing the minimum temperature
	 */
	public float getMin(){
		return minTemp;
	}
	
	/**
	 * Getter method for maximum temperature
	 * 
	 * @return float representing the maximum temperature
	 */
	public float getMax(){
		return maxTemp;
	}
	
	/**
	 * Getter method for sunrise time
	 * 
	 * @return String representing sunrise time
	 */
	public String getSunrise(){
		return sunrise;
	}
	
	/**
	 * Getter method for sunset time
	 * 
	 * @return String representing sunset time
	 */
	public String getSunset(){
		return sunset;
	}
	
	/**
	 * Getter method for the weather icon
	 * 
	 * @return BufferedImage of the icon representing the current weather
	 */
	public BufferedImage getIcon(){
		return weatherIcon;
	}
}
