package cs2212b.team16;
/*
 * @author Daniel, James, Omar, Long, Angus, Nick
 * this class is used to define the weather Data info of a city
 * holds all data in one convenient object and allows returns of data
 */
import java.awt.image.BufferedImage;

public class weatherData {

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
	
	//instantiates an object of the class
	public weatherData(){
		
	}
	
	
	/*
	 * assigns values to all variables in the object
	 * @param parameters which hold every part of a weather Data object including temperatures, picture of current conidition and more
	 * @return no returns, fills object
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
	
	/*
	 * @param float that holds longitude
	 * @return
	 */
	public void setLon(float longitude){
		lon = longitude;
	}
	
	/*
	 * @param float that holds latitude
	 * @return
	 */
	public void setLat(float latitude){
		lat= latitude; 
	}
	/*
	 * @param String that holds the city Name
	 * @return 
	 */
	public void setName(String nm){
		name = nm;
	}
	/*
	 * @param String that holds country name
	 * @return
	 */
	public void setCount(String count){
		country = count; 
	}
	/*
	 * @param float that holds temperature
	 * @return
	 */
	public void setTemp(float tmp){
		temp = tmp;
	}
	/*
	 * @param float that holds latitude
	 * @return
	 */
	public void setSpeed(float speed){
		windSpeed = speed;
	}
	/*
	 * @param float that holds wind direction
	 * @return
	 */
	public void setDir(float dir){
		windDir = dir;
	}
	/*
	 * @param float that holds air pressure
	 * @return
	 */
	public void setPress(float press){
		pressure = press;
	}
	/*
	 * @param float that holds humidity
	 * @return
	 */
	public void setHumid(float humid){
		humidity = humid;
	}
	/*
	 * @param String that holds weather description
	 * @return
	 */
	public void setCondit(String condit){
		condition = condit;
	}
	/*
	 * @param float that holds min temperature
	 * @return
	 */
	public void setMin(float min){
		minTemp = min;
	}
	/*
	 * @param float that holds max temperature
	 * @return
	 */
	public void setMax(float max){
		maxTemp = max;
	}
	public void setSunrise(String rise){
		sunrise = rise;
	}
	public void setSunset(String set){
		sunset = set;
	}
	public void setIcon(BufferedImage icon){
		weatherIcon = icon;
	}
	
	//methods to get the variables
	
	/*
	 * @param none
	 * @return the longitude
	 */
	public Float getLon(){
		return lon;
	}
	/*
	 * @param none
	 * @return the latitude
	 */
	public Float getLat(){
		return lat; 
	}
	/*
	 * @param none
	 * @return the city name
	 */
	public String getName(){
		return name;
	}
	/*
	 * @param none
	 * @return the country
	 */
	public String getCount(){
		return country; 
	}
	/*
	 * @param none
	 * @return the temperature
	 */
	public float getTemp(){
		return temp;
	}
	/*
	 * @param none
	 * @return the wind speed
	 */
	public float getSpeed(){
		return windSpeed;
	}
	/*
	 * @param none
	 * @return the wind direction
	 */
	public float getDir(){
		return windDir;
	}
	/*
	 * @param none
	 * @return the pressure
	 */
	public float getPress(){
		return pressure;
	}
	/*
	 * @param none
	 * @return the humidity
	 */
	public float getHumid(){
		return humidity;
	}
	/*
	 * @param none
	 * @return the condition
	 */
	public String getCondit(){
		return condition;
	}
	/*
	 * @param none
	 * @return the min temperature
	 */
	public float getMin(){
		return minTemp;
	}
	/*
	 * @param none
	 * @return the max temperature
	 */
	public float getMax(){
		return maxTemp;
	}
	/*
	 * @param none
	 * @return the sunrise time
	 */
	public String getSunrise(){
		return sunrise;
	}
	/*
	 * @param none
	 * @return the sunset time
	 */
	public String getSunset(){
		return sunset;
	}
	/*
	 * @param none
	 * @return the weather Icon
	 */
	public BufferedImage getIcon(){
		return weatherIcon;
	}
	
	
}
