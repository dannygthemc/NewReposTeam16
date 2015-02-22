
public class weatherData {
	public int maxTemp;
	public int minTemp;
	public int speed;
	public int airPressure;
	public int humidity;
	public String skyCondition;
	public int sunset;//Should we make these strings?
	public int sunrise;//Should we make these strings?
	public String windDirection;
	public int windDirectionDegree;
	public int precipitation;
	boolean metric; //Not in UML
	
	public weatherData(){
		metric = true;
		maxTemp = 0;
		minTemp = 0;
		speed = 0;
		airPressure = 0;
		humidity = 0;
		skyCondition = "Default";
		sunset = 0;
		sunrise = 0;
		windDirection = "Default";
		precipitation = 0;
	}
	
	public void convertImperial() {
		if (metric){
			maxTemp = maxTemp * 9/5 + 32; //To F
			minTemp = minTemp * 9/5 + 32; //To F
			speed = (int)(3.28084 * speed); //To fps
			airPressure = (int)(airPressure * 10 * 0.0145038);//To psi
			precipitation = (int)(precipitation * 0.0393701);//To inches
			metric = false;
		}
	}
	
	public void convertMetric(){
		if(!metric){
			maxTemp = 5/9 * (maxTemp - 32); //To C
			minTemp = 5/9 * (minTemp - 32); //To C
			speed = (int)(speed / 3.28084); //To m/s
			airPressure = (int)(airPressure / (0.145038)); // To hPa
			precipitation = (int)(precipitation / 0.0393701); // To mm
			metric = true;
		}
	}
	
	
}
