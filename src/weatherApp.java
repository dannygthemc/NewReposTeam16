
public class weatherApp {
	private weatherData current;
	private weatherData[] longTerm;
	private weatherData[] shortTerm;
	private boolean[] myLocations;
	private customWeatherPref customView;
	
	public weatherApp(){
		current = new weatherData();
		longTerm = new weatherData[7];
	}
}
