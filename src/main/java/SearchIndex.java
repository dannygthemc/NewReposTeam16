package cs2212b.team16;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.io.InputStreamReader;

public class SearchIndex {

	/* Attributes */
	
	private ConcurrentHashMap<String, ArrayList<location>> database;
	private int count; 
	
	/* Constructor */
	
	public SearchIndex()
	{
		/* Initialize data structure to hold the names and number of entries */
		
		this.database = new ConcurrentHashMap<String, ArrayList<location>>();
		
		/* Declare and/or initialize variables that will assist in parsing individual lines
		 * and words in the text file. 
		 */
		BufferedReader reader = null;
		
		String line = null;
		String temp = null;
		StringTokenizer tokenizer;
		
		boolean endOfName = false;
		
		int id = 0;
		double longitude = 0.0, latitude = 0.0;
		String name = "";
		String code = null; 
		
		location city;
		
		/* Begin reading file */
		try 
		{
			reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("city_list.txt")));
			reader.readLine();
			
			while((line = reader.readLine()) != null) //loop through lines in the file
			{
				tokenizer = new StringTokenizer(line); //parse individual line
				
				id = Integer.parseInt(tokenizer.nextToken()); //Get city ID
				
				while(!endOfName) //loop for possibility of names with multiple words
				{
					try
					{
						/* Get the next token and try to parse as a double
						 * since these are known strings, they will generate an exception
						 */
						temp = tokenizer.nextToken(); 
						
						/*This code will only execute once the full name
						 * of the city has been concatenated
						 */
						longitude = Double.parseDouble(temp); //this line generates the necessary exception
						latitude = Double.parseDouble(tokenizer.nextToken());
						
						endOfName = true; //ends the loop
					}
					catch (NumberFormatException e) //catch the exception and add the token to the name
					{
						if (name.length() > 0 ) {
							name += " " + temp; //add the current string to the name
						} else {
							name += temp; //add the current string to the name
						}
					}
				}
				
				code = tokenizer.nextToken(); //Get the country code
				
				city = new location();
				city.setCityID(id); //CityProfile(id, name, longitude, latitude, code); //create new city object
				city.setLatitude((int)latitude);
				city.setLongitude((int)longitude);
				city.setName(name);
				city.setCountryCode(code);
				insert(city); //add the new object to the HashMap
				/* Reset all values before next iteration */ 
				
				id = 0;
				longitude = 0.0;
				latitude = 0.0;
				name = "";
				code = null; 
				endOfName = false;
			}
		} 
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("Unable to read file.");
			System.exit(1);
		} 
		
	}
	
	public int numElements()
	{
		return this.count;
	}
	
	/* Search method queries the database for a certain city name
	 * and returns an ArrayList containing the results in that HashMap position 
	 */
	public ArrayList<location> search(String cityName)
	{  

		//String searchWord = cityName.toLowerCase();
		ArrayList<location> results = this.database.get(cityName.toLowerCase());
		
		//System.out.println("RESULTS: " + results);
		//System.out.println(this.database.isEmpty());
		
		if(results != null)
		{
			return results;
		}
		else
			return null;
	}

	/* Insert method creates a key from the given CityProfile and inserts the corresponding
	 * object into the database. If the position in the HashMap is unoccupied, a new list 
	 * is created, inserted, and the element then added to it.  
	 */
	private void insert(location city)
	{
		String key = city.getName().toLowerCase();
		ArrayList<location> list = this.database.get(key);
		
		if(list == null)
			this.database.put(key, list = new ArrayList<location>());
		
		list.add(city);
		this.count++;
	}
}




