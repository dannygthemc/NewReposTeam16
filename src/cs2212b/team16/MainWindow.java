package cs2212b.team16;
/*
 * @author: Daniel, James, Omar, Long, Angus, Nick
 * this class used to define the GUI for the application
 * Calls the main weatherApp class only
 */
import javax.swing.JFrame; //used to create a Jframe















//used to create a menu bar and responses to user Interface
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;















//used to create controls
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;















//used to organize layout
import java.awt.Color;

import javax.swing.GroupLayout;















import org.imgscalr.Scalr;









//used to organize border layout
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

	public class MainWindow extends JFrame  {
		
		private weatherApp app = new weatherApp();
		private SearchIndex dataBase = new SearchIndex();
		
		//GUI Initializations to allow dynmaic changes
		private JComboBox<String> locBar = new JComboBox<String>(); //used to list searched locations
		private JMenuBar menubar;
		private JLabel refreshLabel = new JLabel();
		private JTabbedPane tabbedPane = new JTabbedPane(); //creates a tab pane
		private JPanel currentPanel = new JPanel();
		private JPanel shortPanel = new JPanel();
		private JPanel longPanel = new JPanel();
		private ActionListener Jcombo = new ActionListener() { //updates weatherView when a new item is searched
			@Override
			public void actionPerformed(ActionEvent event){
				if (((JComboBox<String>)event.getSource()).getSelectedItem() != null){
					String item = ((JComboBox<String>)event.getSource()).getSelectedItem().toString();
					updateWeatherView(item);
				}
			}
		};
		
		/*
		 * instantiates an instance of the MainWindow that's been defined
		 * @param none
		 * @return none
		 */
		public MainWindow() {
			this.initUI();
			
		}
		
		/*
		 * initializes the User Interface elements defined below
		 * @param none
		 * @return none, initializes UI
		 * 
		 */
		private void initUI () {
			
			this.setTitle("Weather Application"); //sets title of frame 
			this.setSize(1000, 600); //sets size of frame
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE); //initiates exit on close command
			this.setJMenuBar(this.createMenubar()); 
			
			JFrame error = new JFrame();
			try {
				createForm();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
			try {
				createFormTwo();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
			try {
				createFormThree();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
			tabbedPane.addTab("Current", null, currentPanel); //fills a tab window with current data
			tabbedPane.addTab("Short Term", null, shortPanel); //fills a tab window with short term data
			tabbedPane.addTab("Long Term", null, longPanel); //fills a tab window with short term data
			//tabbedPane.addTab("Long Term", null, this.createMultiForm(app.getLongTerm()),"");
			//this.setLayout(new BorderLayout());
			
			
			GroupLayout layout = new GroupLayout(this.getContentPane());
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup() //sets the vertical groups
					
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
								.addComponent(tabbedPane)
								.addComponent(refreshLabel)
						)

			);
			layout.setVerticalGroup( layout.createSequentialGroup() //sets the vertical groups
					
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
							.addComponent(tabbedPane)
					)
					.addComponent(refreshLabel)

		);
			
			
			//this.add(lbl1);
			//this.add(tabbedPane, BorderLayout.CENTER);//adds the tabbed pane to the main window
			this.getContentPane().setLayout(layout);
		}
		
		/*
		 * Refreshes the User Interface elements defined below
		 * @param none
		 * @return none, refreshes UI
		 * 
		 */
		private void refreshPanels(){
			JFrame error = new JFrame();
			
			currentPanel.removeAll();
			shortPanel.removeAll();
			longPanel.removeAll();
			
			try {
				createForm();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
			try {
				createFormTwo();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
			try {
				createFormThree();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
			
			updateRefreshTime();
		}
		
		/*
		 * used to fill the location box with preset locations
		 * @param none
		 * @return none, fills location box
		 */
		private void populateMyLocationsBox(){
			locBar.removeActionListener(Jcombo);
			locBar.removeAllItems();
			for (int i = 0; i < app.getMyLocations().length; i ++){
				location tempLoc = app.getMyLocations()[i];
				if (tempLoc.getCityID() != 0){
					locBar.addItem(tempLoc.getName() + ", " + tempLoc.getCountryCode() + " Lat: " + tempLoc.getLatitude() 
							+ " Long: " + tempLoc.getLongitude() );
				} 
			}
			if (locBar.getItemCount() == 0){
				locBar.addItem("--Empty--");
			} else {
				locBar.addItem("--Remove?--");
			}
			locBar.addActionListener(Jcombo);
		}
		
		/*
		 * Refresh the views when saved city is clicked
		 * @param string of city name clicked
		 * @return none, updates weatherView
		 */
		private void updateWeatherView(String cityName){
			/*
			 * possibilities[i] = i + 1 + ". " + simLoc.get(i).getName() + ", " + simLoc.get(i).getCountryCode() + " Lat: " 
							+ simLoc.get(i).getLatitude() + " Long: " + simLoc.get(i).getLongitude();
			 */
			
			if (cityName.equals("--Remove?--")){
					int count = 0;
					JFrame frame = new JFrame();
					String[] possibilities = new String[app.getMyLocations().length];
					for (int i = 0; i < app.getMyLocations().length; i ++){
						location loc =  app.getMyLocations()[i];
						if (!loc.getName().equals("Default")){
							possibilities[i] = loc.getName() + ", " + loc.getCountryCode() + " Lat: " + loc.getLatitude()
									+ " Long: " + loc.getLongitude();
							count ++;
						}
					}
					String response = (String) JOptionPane.showInputDialog(frame, "Pick a location to remove:", "Remove Location",  
							JOptionPane.QUESTION_MESSAGE, null, Arrays.copyOfRange(possibilities, 0, count), "Titan");
					if (response != null){
						String countryCode = response.substring(response.indexOf(',') + 2, response.indexOf(',') + 5);
						cityName = response.substring(0, response.indexOf(','));
						app.removeLocation(cityName, countryCode);
					}
					populateMyLocationsBox();
						
			 } else if (cityName.equals("--Empty--")){
						//Do nothing
			} else {
					location update = new location();
					String countryCode = cityName.substring(cityName.indexOf(',') + 2, cityName.indexOf(',') + 5);
					cityName = cityName.substring(0, cityName.indexOf(','));
					for (int i = 0; i < app.getMyLocations().length; i ++){
						if (app.getMyLocations()[i].getName().equals(cityName) && app.getMyLocations()[i].getCountryCode().equals(countryCode));
							update = app.getMyLocations()[i];
					}
					app.setVisibleLocation(update);
					refreshPanels();
			}
		}
				
		/*
		 * Refresh the views when new city is searched
		 * @param String of city searched for
		 * @return none, adds city to search Box
		 */
		private void searchBoxUsed(String txt){
			JFrame frame = new JFrame();
			ArrayList<location> simLoc = dataBase.search(txt);
			if (simLoc == null) JOptionPane.showMessageDialog(frame, "'" + txt + "' not found.");
			else if (simLoc.size() == 1){
				location searchedLoc = simLoc.get(0);
				app.addLocation(searchedLoc);
				app.setVisibleLocation(searchedLoc);
				populateMyLocationsBox();
				refreshPanels();
			}
			else {
				String [] possibilities = new String[simLoc.size()];
				
				for (int i = 0; i <  simLoc.size(); i ++){
					possibilities[i] = i + 1 + ". " + simLoc.get(i).getName() + ", " + simLoc.get(i).getCountryCode() + " Lat: " 
							+ simLoc.get(i).getLatitude() + " Long: " + simLoc.get(i).getLongitude();
				}
				
				String response = (String) JOptionPane.showInputDialog(frame, "Which " + txt + " did you mean?", "Search Location",  
						JOptionPane.QUESTION_MESSAGE, null, possibilities, "Titan");
			
				if (response != null) {
						location searchedLoc = simLoc.get(Integer.parseInt(response.substring(0, response.indexOf('.'))) - 1);
						app.addLocation(searchedLoc);
						app.setVisibleLocation(searchedLoc);
						populateMyLocationsBox();
						refreshPanels();
				 }
			}
		}
		
		/*
		 * defines a Menu bar with a "File" option, which can be called by "alt + f"
		 * menu bar contains option for exiting current program
		 */
		private JMenuBar createMenubar() {
			JMenuBar menubar = new JMenuBar(); //creates new menu bar
			JMenu mnuFile = new JMenu("File"); //creates file option
			mnuFile.setMnemonic(KeyEvent.VK_F);
			JMenuItem mniFileExit = new JMenuItem("Exit"); //creates exit button
			mniFileExit.setMnemonic(KeyEvent.VK_E);
			mniFileExit.setToolTipText("Exit application"); //sets tool tip
			mniFileExit.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) { //when clicked
			System.exit(0); } //exit program
			});
			
			//Reset StartUp Location
			JMenuItem reset = new JMenuItem("Reset Current");
			reset.setMnemonic(KeyEvent.VK_E);
			reset.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
				 JFrame frame = new JFrame();
				 String response = (String) JOptionPane.showInputDialog(frame, "Enter the city name of the new current location:", "Reset Current Location",  
							JOptionPane.QUESTION_MESSAGE, null, null, " ");
				 if (response != null){
					 ArrayList<location> simLoc = dataBase.search(response);
					 if (simLoc == null) JOptionPane.showMessageDialog(frame, "'" + response + "' not found.");
					 else if (simLoc.size() == 1){
							location searchedLoc = simLoc.get(0);
							app.addLocation(searchedLoc);
							app.setVisibleLocation(searchedLoc);
							populateMyLocationsBox();
							refreshPanels();
						}
					 else {
						String [] possibilities = new String[simLoc.size()];
						
						for (int i = 0; i <  simLoc.size(); i ++){
							possibilities[i] = i + 1 + ". " + simLoc.get(i).getName() + ", " + simLoc.get(i).getCountryCode() + " Lat: " 
									+ simLoc.get(i).getLatitude() + " Long: " + simLoc.get(i).getLongitude();
						}
						
						String response2 = (String) JOptionPane.showInputDialog(frame, "Which " + response + " did you mean?", "Current Location",  
								JOptionPane.QUESTION_MESSAGE, null, possibilities, "Titan");
					
						if (response != null) {
								location searchedLoc = simLoc.get(Integer.parseInt(response2.substring(0, response.indexOf('.'))) - 1);
								app.setCurrentLocation(searchedLoc);
								app.setVisibleLocation(searchedLoc);
								refreshPanels();
						 }
					 }
				 }
			 }
			 	
			});
			
			//Refresh button
			JMenuItem refresh = new JMenuItem("Refresh");
			refresh.setMnemonic(KeyEvent.VK_E);
			updateRefreshTime();
			refresh.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {	
				 refreshPanels(); 
			 	}
			});
			mnuFile.add(reset);
			mnuFile.add(refresh);
			mnuFile.add(mniFileExit); //adds it to the menubar
			menubar.add(mnuFile);
			
			//oF/oC check box
			JCheckBox degree = new JCheckBox();
			degree.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event){
					
				}
			});
			menubar.add(degree);
			JLabel degLabel = new JLabel("°F");
			menubar.add(degLabel);
			menubar.add(new JLabel("   "));
			
			//Locations -> MyLocations
			populateMyLocationsBox();
			locBar.addActionListener(Jcombo);
			menubar.add(locBar);
						
			//Search text box
			JTextField txtBar = new JTextField("Search Location");
			txtBar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event){
					searchBoxUsed(((JTextField) event.getSource()).getText());
				}
			});
			menubar.add(txtBar);
				
			
			return menubar;
		}
		
		/*
		 * used to refresh update time on new weather grab
		 * @param none
		 * @return none
		 */
		private void updateRefreshTime(){
			refreshLabel.setText("Last Updated: " + (new Date()).toString());
		}
		/*
		 * this method is used to fill a panel with the Long term weather Data
		 * @param none
		 * @return none, fills panel with data
		 */
		private void createFormThree() throws IOException{
			
			weatherData[] tmp = new weatherData[8]; //holds weather data objects
			try {
				app.grabLongTerm(app.getVisibleLocation().getCityID(), app.getUnits()); //grabs long term weather data info from database
			} catch (IOException e) {
				throw new IOException("error");
			} 
			tmp = app.getLongTerm(); //grabs weatherData objects now filled with data
			
			//used for formatting
			//JLabel lbl1 = new JLabel("Time: ");
			JLabel lbl1 = new JLabel("Date: ");
			JLabel lbl2 = new JLabel("Temp: ");
			JLabel lbl2A = new JLabel("Min-Temp: ");
			JLabel lbl2B = new JLabel("Max-Temp: ");
			JLabel lbl3 = new JLabel("Weather Condition:");
			
			JLabel lblcity = new JLabel(tmp[0].getName() + ", " + tmp[0].getCount() + ", " + tmp[0].getLon() + ", " + tmp[0].getLat() ); //displays location info
			JLabel[] date = new JLabel[7]; //array of dates
			JLabel[] temp = new JLabel[7]; //array of temperature labels
			JLabel[] min = new JLabel[7]; //array of min temp labels
			JLabel[] max = new JLabel[7]; //array of max temp labels
			JLabel[] descrip = new JLabel[7]; //array of description labels
			JLabel[] lblPic = new JLabel[7]; //array of picture labels
			
			BufferedImage pic = null; //used to temporarily hold pictures
			JLabel hold; //temporarily hold labels for storage
			JLabel hold2;
			JLabel hold3;
			JLabel hold4;
			JLabel hold5;
			JLabel hold6;
			
			//fills the arrays with their respective values
			for(int i =0; i<7; i++){
				hold = new JLabel("" +tmp[i+1].getTemp()); //holds current temp
				temp[i] = hold; //puts it in the array
				hold2 = new JLabel("" +tmp[i+1].getCondit()); //holds current condition
				descrip[i] = hold2;
				
				pic = tmp[i+1].getIcon();
				pic  = Scalr.resize(pic, 80);
				hold3 = new JLabel(new ImageIcon(pic)); //holds current pic
				lblPic[i] = hold3;
				hold4 = new JLabel("" + tmp[i+1].getMin());//holds current min
				min[i] = hold4;
				hold5 = new JLabel("" + tmp[i+1].getMax());//holds current max
				max[i] = hold5;
				hold6 = new JLabel("" + tmp[i+1].getSunrise());//timestamp stored in Sunrise, since this variable was not in use for ShorTerm
				date[i] = hold6;
			}
			
			//Group layout used to organize GUI
			GroupLayout layout = new GroupLayout(longPanel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup() //sets the horizontal groups
						
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) //shows city data
								.addComponent(lblcity)
								.addGap(10)
								.addComponent(lbl1)
								.addComponent(lbl2)
								.addComponent(lbl2A)
								.addComponent(lbl2B)
								.addComponent(lbl3)
								
						)
						//the rest of the horizontal groups contain weather Data in 3 hour increments
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[0])
								.addComponent(date[0])
								.addComponent(temp[0])
								.addComponent(min[0])
								.addComponent(max[0])
								.addComponent(descrip[0])
								.addGap(30)
								.addComponent(lblPic[5])
								.addComponent(date[5])
								.addComponent(temp[5])
								.addComponent(min[5])
								.addComponent(max[5])
								.addComponent(descrip[5])
								
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[1])
								.addComponent(date[1])
								.addComponent(temp[1])
								.addComponent(min[1])
								.addComponent(max[1])
								.addComponent(descrip[1])
								.addGap(30)
								.addComponent(lblPic[6])
								.addComponent(date[6])
								.addComponent(temp[6])
								.addComponent(min[6])
								.addComponent(max[6])
								.addComponent(descrip[6])
								
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[2])
								.addComponent(date[2])
								.addComponent(temp[2])
								.addComponent(min[2])
								.addComponent(max[2])
								.addComponent(descrip[2])
								.addGap(30)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[3])
								.addComponent(date[3])
								.addComponent(temp[3])
								.addComponent(min[3])
								.addComponent(max[3])
								.addComponent(descrip[3])
								.addGap(30)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[4])
								.addComponent(date[4])
								.addComponent(temp[4])
								.addComponent(min[4])
								.addComponent(max[4])
								.addComponent(descrip[4])
								
						)
						
						);
			layout.setVerticalGroup( layout.createSequentialGroup() //sets the vertical groups
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//city is in its own vertical group
							.addComponent(lblcity)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //pics aligned vertically
							.addComponent(lblPic[0])
							.addComponent(lblPic[1])
							.addComponent(lblPic[2])
							.addComponent(lblPic[3])
							.addComponent(lblPic[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //dates alligned vertically
							.addComponent(lbl1)
							.addComponent(date[0])
							.addComponent(date[1])
							.addComponent(date[2])
							.addComponent(date[3])
							.addComponent(date[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //temps aligned vertically
							.addComponent(lbl2)
							.addComponent(temp[0])
							.addComponent(temp[1])
							.addComponent(temp[2])
							.addComponent(temp[3])
							.addComponent(temp[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) // min temps aligned vertically
							.addComponent(lbl2A)
							.addComponent(min[0])
							.addComponent(min[1])
							.addComponent(min[2])
							.addComponent(min[3])
							.addComponent(min[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //max temps aligned vertically
							.addComponent(lbl2B)
							.addComponent(max[0])
							.addComponent(max[1])
							.addComponent(max[2])
							.addComponent(max[3])
							.addComponent(max[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //descrips alligned vertically
							.addComponent(lbl3)
							.addComponent(descrip[0])
							.addComponent(descrip[1])
							.addComponent(descrip[2])
							.addComponent(descrip[3])
							.addComponent(descrip[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //this is the start of the second row of weather Data, pics aligned
							.addComponent(lblPic[5])
							.addComponent(lblPic[6])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//dates aligned
							.addComponent(date[5])
							.addComponent(date[6])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//temps aligned
							.addComponent(temp[5])
							.addComponent(temp[6])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//min temps aligned
							.addComponent(min[5])
							.addComponent(min[6])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//max temps aligned
							.addComponent(max[5])
							.addComponent(max[6])
							)		
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//descrips aligned
							.addComponent(descrip[5])
							.addComponent(descrip[6])
							)		
					);
			longPanel.setLayout(layout); //sets the defined layout to the panel
		}
		
		/*
		 * used to create Panel for the shorTermForecast Tab
		 * @param none
		 * @return none, fills panel with data
		 */
		private void createFormTwo() throws IOException{
			
			weatherData[] tmp = new weatherData[10]; //holds weather data objects
			try {
				app.grabShortTerm(app.getVisibleLocation().getCityID(), app.getUnits()); //grabs current weather data info from database
			} catch (IOException e) {
				throw new IOException("error");
			} 
			tmp = app.getShortTerm(); //grabs weatherData objects now filled with data
			
			//used for formatting
			JLabel lbl1 = new JLabel("Time: ");
			JLabel lbl2 = new JLabel("Temp: ");
			JLabel lbl3 = new JLabel("Weather Condition:");
			
			JLabel lblcity = new JLabel(tmp[0].getName() + ", " + tmp[0].getCount() + ", " + tmp[0].getLon() + ", " + tmp[0].getLat() ); //displays location info
			JLabel[] temp = new JLabel[9]; //array of temperature labels
			JLabel[] time = new JLabel[9]; //array of time stamps
			JLabel[] descrip = new JLabel[9]; //array of description labels
			JLabel[] lblPic = new JLabel[9]; //array of picture labels
			
			BufferedImage pic = null; //used to temporarily hold pictures
			JLabel hold; //temporarily hold labels for storage
			JLabel hold2;
			JLabel hold3;
			JLabel hold4;
			
			//fills the arrays with their respective values
			for(int i =0; i<9; i++){
				hold = new JLabel("" +tmp[i+1].getTemp()); //creates a new label
				temp[i] = hold; //puts it in the array
				hold2 = new JLabel("" +tmp[i+1].getCondit());
				descrip[i] = hold2;
				
				pic = tmp[i+1].getIcon();
				pic  = Scalr.resize(pic, 80);
				hold3 = new JLabel(new ImageIcon(pic));
				lblPic[i] = hold3;
				hold4 = new JLabel(tmp[i+1].getSunrise());//timestamp stored in Sunrise, since this variable was not in use for ShorTerm
				time[i] = hold4;
			}
			
			//Group layout used to organize GUI
			GroupLayout layout = new GroupLayout(shortPanel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup() //sets the horizontal groups
						
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) //shows city data
								.addComponent(lblcity)
								.addGap(10)
								.addComponent(lbl1)
								.addComponent(lbl2)
								.addComponent(lbl3)
								
						)
						//the rest of the horizontal groups contain weather Data in 3 hour increments
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[0])
								.addComponent(time[0])
								.addComponent(temp[0])
								.addComponent(descrip[0])
								.addGap(30)
								.addComponent(lblPic[5])
								.addComponent(time[5])
								.addComponent(temp[5])
								.addComponent(descrip[5])
								
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[1])
								.addComponent(time[1])
								.addComponent(temp[1])
								.addComponent(descrip[1])
								.addGap(30)
								.addComponent(lblPic[6])
								.addComponent(time[6])
								.addComponent(temp[6])
								.addComponent(descrip[6])
								
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[2])
								.addComponent(time[2])
								.addComponent(temp[2])
								.addComponent(descrip[2])
								.addGap(30)
								.addComponent(lblPic[7])
								.addComponent(time[7])
								.addComponent(temp[7])
								.addComponent(descrip[7])
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[3])
								.addComponent(time[3])
								.addComponent(temp[3])
								.addComponent(descrip[3])
								.addGap(30)
								.addComponent(lblPic[8])
								.addComponent(time[8])
								.addComponent(temp[8])
								.addComponent(descrip[8])
								
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								
								.addComponent(lblPic[4])
								.addComponent(time[4])
								.addComponent(temp[4])
								.addComponent(descrip[4])
								
						)
						
						);
			layout.setVerticalGroup( layout.createSequentialGroup() //sets the vertical groups
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//city is in its own vertical group
							.addComponent(lblcity)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //pics aligned vertically
							.addComponent(lblPic[0])
							.addComponent(lblPic[1])
							.addComponent(lblPic[2])
							.addComponent(lblPic[3])
							.addComponent(lblPic[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //times aligned vertically
							.addComponent(lbl1)
							.addComponent(time[0])
							.addComponent(time[1])
							.addComponent(time[2])
							.addComponent(time[3])
							.addComponent(time[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //temps aligned vertically
							.addComponent(lbl2)
							.addComponent(temp[0])
							.addComponent(temp[1])
							.addComponent(temp[2])
							.addComponent(temp[3])
							.addComponent(temp[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //descrips alligned vertically
							.addComponent(lbl3)
							.addComponent(descrip[0])
							.addComponent(descrip[1])
							.addComponent(descrip[2])
							.addComponent(descrip[3])
							.addComponent(descrip[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //this is the start of the second row of weather Data, pics aligned
							.addComponent(lblPic[5])
							.addComponent(lblPic[6])
							.addComponent(lblPic[7])
							.addComponent(lblPic[8])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //times aligned
							.addComponent(time[5])
							.addComponent(time[6])
							.addComponent(time[7])
							.addComponent(time[8])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//temps aligned
							.addComponent(temp[5])
							.addComponent(temp[6])
							.addComponent(temp[7])
							.addComponent(temp[8])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)//descrips aligned
							.addComponent(descrip[5])
							.addComponent(descrip[6])
							.addComponent(descrip[7])
							.addComponent(descrip[8])
							)		
					);
			shortPanel.setLayout(layout); //sets the defined layout to the panel
		}
		
		
				
		/*
		 * used to add current weather data to a Panel
		 * @param none
		 * @return none
		 */
		private void createForm() throws IOException {
			
			weatherData tmp = new weatherData();
			try {
				app.grab(app.getVisibleLocation().getCityID(), app.getUnits());
			} catch (IOException e) {
				throw new IOException("error");
			}
			tmp = app.getCurrent();
			
			JLabel lblcity = new JLabel(tmp.getName() + ", " + tmp.getCount() + ", " + tmp.getLon() + ", " + tmp.getLat() ); //displays location info
			JLabel lbldescrip = new JLabel("Weather Condition: ");
			JLabel lbldescrip2 = new JLabel("" +tmp.getCondit());
			JLabel lbltemp = new JLabel("Temp:  "); //label for temp
			JLabel lbltemp2 = new JLabel("" + tmp.getTemp()); //actual temp
			JLabel lblmin = new JLabel("Min Temp: "); //label for min
			JLabel lblmin2 = new JLabel("" + tmp.getMin()); //actual min
			JLabel lblmax = new JLabel("Max Temp: " ); //label for max
			JLabel lblmax2 = new JLabel("" + tmp.getMax()); //actual max
			JLabel lblspeed = new JLabel("Wind Speed: "); //label for speed
			JLabel lblspeed2 = new JLabel("" + tmp.getSpeed()); //actual speed
			JLabel lbldir = new JLabel("Wind Direction: "); //label for dir 
			JLabel lbldir2 = new JLabel("" + tmp.getDir()); //actual dir
			JLabel lblpress = new JLabel("Air Pressure: "); //label for pressure
			JLabel lblpress2 = new JLabel("" + tmp.getPress()); //actual pressure
			JLabel lblhumid = new JLabel("Humidity: "); //label for humid
			JLabel lblhumid2 = new JLabel("" + tmp.getHumid()); //actual humdi
			JLabel lblrise = new JLabel("Sunrise Time: "); //label for sunrise
			JLabel lblrise2 = new JLabel("" + tmp.getSunrise()); //actual sunrise
			JLabel lblset = new JLabel("Sunset Time: "); //label for susnet
			JLabel lblset2 = new JLabel("" + tmp.getSunset()); //actual sunset
			
			//used to set picture
			BufferedImage pic = tmp.getIcon();
			pic  = Scalr.resize(pic, 80);
			JLabel lblPic = new JLabel(new ImageIcon(pic)); //holds picture
			
			
			//adds control with layout organization
			GroupLayout layout = new GroupLayout(currentPanel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup() //sets horizontal groups
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) //holds all the identifier labels
							.addComponent(lblcity)
							.addComponent(lblPic)
							.addComponent(lbldescrip)
							.addComponent(lbltemp)
							.addComponent(lblmin)
							.addComponent(lblmax)
							.addComponent(lblspeed)
							.addComponent(lbldir)
							.addComponent(lblpress)
							.addComponent(lblhumid)
							.addComponent(lblrise)
							.addComponent(lblset)
							
							)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) //sets wetaher Data labels
							
							.addComponent(lbldescrip2)
							.addComponent(lbltemp2)
							.addComponent(lblmin2)
							.addComponent(lblmax2)
							.addComponent(lblspeed2)
							.addComponent(lbldir2)
							.addComponent(lblpress2)
							.addComponent(lblhumid2)
							.addComponent(lblrise2)
							.addComponent(lblset2)
							)
						 );
			layout.setVerticalGroup( layout.createSequentialGroup() //sets verticsal groups
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //city is on its own
							.addComponent(lblcity)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //pic is on its own
							.addComponent(lblPic)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE) //the rest have the identifier, followed by the actual data
							.addComponent(lbldescrip)
							.addComponent(lbldescrip2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lbltemp)
							.addComponent(lbltemp2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblmin)
							.addComponent(lblmin2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblmax)
							.addComponent(lblmax2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblspeed)
							.addComponent(lblspeed2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lbldir)
							.addComponent(lbldir2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblpress)
							.addComponent(lblpress2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblhumid)
							.addComponent(lblhumid2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblrise)
							.addComponent(lblrise2)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblset)
							.addComponent(lblset2)
							)					
						);
							
						currentPanel.setLayout(layout); //sets the layout		
								
			}
	}