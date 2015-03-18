package cs2212b.team16;

/**
 * location.java stores values for the user's location
 * @author Daniel Gilbert
 * @author Omar Abdel-Qader
 * @author James Crocker
 * @author Long Le
 * @author Angus Poole
 * @author Nicholas Teixeira
 *
 */
public class location {
	private int latitude;
	private int longitude;
	private String name;
	private int cityID;
	private String countryCode;
	
	
	/**
	 * Constructor for the location class
	 */
	public location(){
		latitude = 0;
		longitude = 0;
		name = "Default";
		cityID = 0;
		countryCode = "Default";
	}
	
	/**
	 * getCountryCode function returns the user's stored country code
	 * @return country code value
	 */
	public String getCountryCode(){
		return countryCode;
	}
	
	/**
	 * setCountryCode function returns the user's stored country code
	 * @return country code value
	 */
	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}
	
	/**
	 * getLatitude function returns the user's stored latitude
	 * @return latitude value
	 */
	public int getLatitude() {
		return latitude;
	}
	
	/**
	 * setLatitude function stores the specified latitude value
	 * @param latitude the latitude to be stored
	 */
	public void setLatitude(int latitude){
		this.latitude = latitude;
	}
	
	/**
	 * getLongitude function returns the user's stored longitude
	 * @return longitude value
	 */
	public int getLongitude(){
		return longitude;
	}
	
	/**
	 * setLongitude function stores the specified longitude value
	 * @param longitude the longitude to be stored
	 */
	public void setLongitude(int longitude){
		this.longitude = longitude;
	}
	
	/**
	 * getName function returns the user's stored name value
	 * @return name value
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * setName function stores the specified name
	 * @param name the name value to be stored
	 */
	public void setName (String name){
		this.name = name;
	}
	
	/**
	 * getCityID retrieves the unique city ID number
	 * @return cityID value
	 */
	public int getCityID(){
		return cityID;
	}
	
	/**
	 * setCityID stores the city ID number
	 * @param cityID the city ID number to be stored
	 */
	public void setCityID(int cityID){
		this.cityID = cityID;
	}
}