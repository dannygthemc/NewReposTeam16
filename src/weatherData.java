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
		weatherIcon = null;
		name = "Default";
		country = "Default";
		temp = 0;
		windSpeed = 0;
		windDir = 0;
		pressure = 0;
		humidity = 0;
		condition = "Default";
		minTemp = 0;
		maxTemp = 0;
		sunrise = "12345";
		sunset = "12345";
		lon = 0;
		lat = 0;
	}
	
	
	//assigns values to all variables
	public void fill(BufferedImage pic, String city, String count, float temp2, float windSpeed2, float windDir2, float pressure2, float humidity2, String condit, float minTemp2, float maxTemp2, String rise, String set){
		
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
	
	//methods to set the variables
	public void setLon(float longitude){
		lon = longitude;
	}
	public void setLat(float latitude){
		lat= latitude; 
	}
	public void setName(String nm){
		name = nm;
	}
	public void setCount(String count){
		country = count; 
	}
	public void setTemp(float tmp){
		temp = tmp;
	}
	public void setSpeed(float speed){
		windSpeed = speed;
	}
	public void setDir(float dir){
		windDir = dir;
	}
	public void setPress(float press){
		pressure = press;
	}
	public void setHumid(float humid){
		humidity = humid;
	}
	public void setCondit(String condit){
		condition = condit;
	}
	public void setMin(float min){
		minTemp = min;
	}
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
	public Float getLon(){
		return lon;
	}
	public Float getLat(){
		return lat; 
	}
	public String getName(){
		return name;
	}
	public String getCount(){
		return country; 
	}
	public float getTemp(){
		return temp;
	}
	public float getSpeed(){
		return windSpeed;
	}
	public float getDir(){
		return windDir;
	}
	public float getPress(){
		return pressure;
	}
	public float getHumid(){
		return humidity;
	}
	public String getCondit(){
		return condition;
	}
	public float getMin(){
		return minTemp;
	}
	public float getMax(){
		return maxTemp;
	}
	public String getSunrise(){
		return sunrise;
	}
	public String getSunset(){
		return sunset;
	}
	public BufferedImage getIcon(){
		return weatherIcon;
	}
	
	
}
