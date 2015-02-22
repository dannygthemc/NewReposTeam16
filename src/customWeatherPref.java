
public class customWeatherPref {
	public boolean maxTemp;
	public boolean minTemp;
	public boolean speed;
	public boolean airPressure;
	public boolean humidity;
	public boolean skyCondition;
	public boolean sunset;//Should we make these booleans?
	public boolean sunrise;//Should we make these booleans?
	public boolean windDirection;
	public boolean windDirectionDegree;
	public boolean precipitation;
	
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
}
