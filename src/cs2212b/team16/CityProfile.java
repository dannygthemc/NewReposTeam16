package cs2212b.team16;
public class CityProfile {
	
	/* Attributes */
	
	private int id;  
	private String name; 
	
	private double longitude; 
	private double latitude; 
	
	private String code; 
	
	/* Constructor */ 
	
	CityProfile(int id, String name, double longitude, double latitude, String code)
	{
		this.id = id; 
		this.name = name; 
		this.longitude = longitude; 
		this.latitude = latitude; 
		this.code = code; 
	}
	
	/* Getter methods */
	
	public int getId()		{ return this.id; }
	
	public String getName()	{ return this.name; }
	
	public double getLong()	{ return this.longitude; }
	
	public double getLat()	{ return this.latitude; }
	
	public String getCode()	{ return this.code; }

	/* toString override method */
	public String toString()
	{
		String result = ""; 
		result += getId() + " " + getName() + " " + getLong() + " " + getLat() + " " + getCode() + " ";
		
		return result;
	}
}
