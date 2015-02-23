
public class weatherApp {
	private weatherData current;
	private weatherData[] longTerm;
	private weatherData[] shortTerm;
	private location[] myLocations;	//** Why is this a boolean array in UML**?
	private customWeatherPref customView;
	
	public weatherApp(){
		current = new weatherData();
		longTerm = new weatherData[5];	//Covers next 5 days
		shortTerm = new weatherData[8];	//Covers 24 hours
		myLocations = new location[5];
		customView = new customWeatherPref();
		
	}
	
	public weatherData getCurrent() {
		return current;
	}
	
	public void setCurrent(weatherData current){
		this.current = current;
	}
	
	public weatherData[] getLongTerm() {
		return longTerm;
	}
	
	public void setLongTerm(weatherData[] longTerm){
		int smallest = this.longTerm.length;
		if (smallest > longTerm.length) smallest = longTerm.length;
		for (int i = 0; i < smallest; i ++){
			this.longTerm[i] = longTerm[i];
		}
	}
	
	public weatherData[] getShortTerm(){
		return shortTerm;
	}
	
	public void setShortTerm(weatherData[] shortTerm){
		int smallest = this.shortTerm.length;
		if (smallest > shortTerm.length) smallest = shortTerm.length;
		for (int i = 0; i < smallest; i ++){
			this.shortTerm[i] = shortTerm[i];
		}
	}
	
	public location[] getMyLocations(){
		return myLocations;
	}
	
	public void addLocation(location A){
		int j = 0;
		while (j < myLocations.length && myLocations[j] != null) j ++;
		if (j < myLocations.length) myLocations[j] = A;
		else {
			location[] temp = new location[myLocations.length + 1];
			for (int i = 0; i < temp.length; i ++){
				temp[i] = myLocations[i];
			}
			temp[myLocations.length] = A;
			myLocations = temp;
		}
	}
	
	public void removeLocation(location A){
		int i = 0;
		while (myLocations[i].cityID != A.cityID) i ++;
		while (i < myLocations.length - 1){
			myLocations[i] = myLocations[i + 1];
			i ++;
		}
		myLocations[i] = null;
	}
	
	public customWeatherPref getCustomView(){
		return customView;
	}
	
	public void setCustomView(customWeatherPref customView){
		this.customView = customView;
	}
}
