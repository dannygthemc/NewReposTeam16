public class location {
	private int latitude;
	private int longitude;
	private String name;
	private int cityID;
	
	public location(){
		latitude = 0;
		longitude = 0;
		name = "Default";
		cityID = 0;
	}
	
	public int getLatitude() {
		return latitude;
	}
	
	public void setLatitude(int latitude){
		this.latitude = latitude;
	}
	
	public int getLongitude(){
		return longitude;
	}
	
	public void setLongitude(int longitude){
		this.longitude = longitude;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName (String name){
		this.name = name;
	}
	
	public int getCityID(){
		return cityID;
	}
	
	public void setCityID(int cityID){
		this.cityID = cityID;
	}
}