import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class LocSearch {

	public static void main(String[] args) 
	{		
		String file = "C:\\Users\\Omar\\workspace\\WeatherApp\\bin\\city_list.txt"; 
		
		String input = "";
		int selection = 0;
		System.out.println("------ Welcome to Location Search for WeatherApp------\n" +
		                   "------ Please wait while database is prepared. -------\n");
		
		SearchIndex database = new SearchIndex(file);
		System.out.println("Database READY.\n" +
						   "\nThe database contains " + database.numElements() + " cities to search from.");
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		for(;;)
		{
			System.out.print("Please enter the city you wish to search for, or type \"exit\" to quit: ");
			try 
			{
				input = keyboard.readLine().toLowerCase();
			} 
			catch (IOException e) {
				
				System.out.println("Not a valid input.");
			}
			
			if(input.compareTo("exit") == 0)
			{
				System.out.println("GOODBYE!");
				System.exit(0);
			}
			else
			{
				ArrayList<CityProfile> results = database.search(input);
				
				if(results == null)
					System.out.println("Your search returned no results.");
				else
				{
					System.out.println("\nYour search returned " + results.size() + " results:\n");
					
					CityProfile[] cities = new CityProfile[results.size()];
					CityProfile current; 
					
					cities = results.toArray(cities);
					
					for(int i = 0; i < cities.length; i++)
					{
						current = cities[i];
						System.out.println("( " + (i + 1) + " )\t" + 
									     current.getName() + ", " + current.getCode() +
									     "\tLong: " + current.getLong() + "\tLat: " + current.getLat());
					}
										
					for(;;)
					{
						System.out.println("\nPlease select a result using the appropriate number, or type \"exit\" to quit: ");
						try 
						{
							input = keyboard.readLine();
						} 
						catch (IOException e) 
						{
							System.out.println("Not a valid input.");
						}
						
						try
						{
							selection = Integer.parseInt(input);
						}
						catch(NumberFormatException e)
						{
							if(input.compareTo("exit") == 0)
							{
								System.out.println("GOODBYE!");
								System.exit(0);
							}
							else
								System.out.println("Not a valid input.");							
						}
	
						if(selection < 1 || selection > cities.length)
							System.out.println("Not a valid selection.");
						else
							break;
					}
					
					System.out.println("You have selected: ( " + selection + " )");
					System.out.println("Displaying information:\n\t" + cities[selection-1]);
					
					System.out.println("Would you like to search another city? Hit Y to continue, or N to quit.");
					
					try 
					{
						input = keyboard.readLine().toLowerCase();
					} 
					catch (IOException e) 
					{
						System.out.println("Not a valid input.");
					}
					if(input.compareTo("n") == 0)
					{
						System.out.println("GOODBYE!");
						System.exit(0);
					}
				}
			}
		}
	}
}
		
	
