
public class customWeatherPref {
	private boolean maxTemp;
	private boolean minTemp;
	private boolean speed;
	private boolean airPressure;
	private boolean humidity;
	private boolean skyCondition;
	private boolean sunset;//Should we make these booleans?
	private boolean sunrise;//Should we make these booleans?
	private boolean windDirection;
	private boolean windDirectionDegree;
	private boolean precipitation;
	
	public customWeatherPref(){
		maxTemp = true;
		minTemp = true;
		speed = true;
		airPressure = true;
		humidity = true;
		skyCondition = true;
		sunset = true;
		sunrise = true;
		windDirection = true;
		windDirectionDegree = true;
		precipitation = true;
	}
	
	public boolean getMaxTemp(){
		return maxTemp;
	}
	
	public void setMaxTemp(boolean maxTemp){
		this.maxTemp = maxTemp;
	}
	
	public boolean getMinTemp(){
		return minTemp;
	}
	
	public void setMinTemp(boolean minTemp){
		this.minTemp = minTemp;
	}
	
	public boolean getSpeed(){
		return speed;
	}
	
	public void setSpeed(boolean speed){
		this.speed = speed;
	}
	
	public boolean getAirPressure(){
		return airPressure;
	}
	
	public void setAirPressure(boolean airPressure){
		this.airPressure = airPressure;
	}
	
	public boolean getHumidity(){
		return humidity;
	}
	
	public void setHumidity(boolean humidity){
		this.humidity = humidity;
	}

	public boolean getSkyCondition(){
		return skyCondition;
	}
	
	public void setSkyCondition(boolean skyCondition){
		this.skyCondition = skyCondition;
	}
	
	public boolean getSunset(){
		return sunset;
	}
	
	public void setSunset(boolean sunset){
		this.sunset = sunset;
	}
	
	public boolean getWindDirection(){
		return windDirection;
	}
	
	public void setWindDirection(boolean windDirection){
		this.windDirection = windDirection;
	}
	
	public boolean getWindDirectionDegree(){
		return windDirectionDegree;
	}
	
	public boolean getPrecipitation(){
		return precipitation;
	}
	
	public void setPrecipitation(boolean precipitation){
		this.precipitation = precipitation;
	}
}