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
import java.util.Arrays;
import java.util.Date;

	public class MainWindow extends JFrame  {
		
		private weatherApp app = new weatherApp();
		
		//GUI Initializations to allow dynamic changes
		private JComboBox<String> locBar = new JComboBox<String>(); //used to list searched locations
		private JMenuBar menubar;
		private JLabel refreshLabel = new JLabel();
		private JTabbedPane tabbedPane = new JTabbedPane(); //creates a tab pane
		private JPanel currentPanel = new JPanel();
		private JPanel shortPanel = new JPanel();
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
			this.setSize(900, 540); //sets size of frame
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE); //initiates exit on close command
			this.setJMenuBar(this.createMenubar()); 
			
			createForm();
			createFormTwo();
			
			tabbedPane.addTab("Current", null, currentPanel); //fills a tab window with current data
			tabbedPane.addTab("Short Term", null, shortPanel); //fills a tab window with short term data
			//tabbedPane.addTab("Long Term", null, this.createMultiForm(app.getLongTerm()),"");			
			
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
			
			this.getContentPane().setLayout(layout);
		}
		
		/*
		 * Refreshes the User Interface elements defined below
		 * @param none
		 * @return none, refreshes UI
		 * 
		 */
		private void refreshPanels(){
			currentPanel.removeAll();
			shortPanel.removeAll();
			
			createForm();
			createFormTwo();
			
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
					locBar.addItem(tempLoc.getName());
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
			if (cityName.equals("--Remove?--")){
					int count = 0;
					JFrame frame = new JFrame();
					String[] possibilities = new String[app.getMyLocations().length];
					for (int i = 0; i < app.getMyLocations().length; i ++){
						String name =  app.getMyLocations()[i].getName();
						if (!name.equals("Default")){
							possibilities[i] = name;
							count ++;
						}
					}
					String response = (String) JOptionPane.showInputDialog(frame, "Pick a location to remove:", "Remove Location",  
							JOptionPane.QUESTION_MESSAGE, null, Arrays.copyOfRange(possibilities, 0, count), "Titan");
					if (response != null) app.removeLocation(response);
					populateMyLocationsBox();
						
			 } else if (cityName.equals("--Empty--")){
						//Do nothing
			} else {
					location update = new location();
					for (int i = 0; i < app.getMyLocations().length; i ++){
						if (app.getMyLocations()[i].getName().equals(cityName));
							update = app.getMyLocations()[i];
					}
					//TODO:
					///Update the weather view based on 'update' location
			}
		}
		
		
		/*
		 * Refresh the views when new city is searched
		 * @param String of city searched for
		 * @return none, adds city to search Box
		 */
		private void searchBoxUsed(String txt){
			location searchedLoc = new location();
			searchedLoc.setCityID(1234);
			searchedLoc.setName(txt);
			//TODO:
			//Assign the new location a location object from Omar's database
			//Verify it was a correct entry
			JFrame frame = new JFrame();
			int result = JOptionPane.showConfirmDialog(frame, "Did you mean: " + txt + " Lat:1235 Lon:1234?");
			if (JOptionPane.YES_OPTION == result) {
					int changeCurrent = JOptionPane.showConfirmDialog(frame, "Set your current location to " + txt + "?");
					if (JOptionPane.YES_OPTION == changeCurrent) {
						app.setCurrentLocation(searchedLoc);
						app.setVisibleLocation(app.getCurrentLocation());
					} else {
						app.addLocation(searchedLoc);
						app.setVisibleLocation(searchedLoc);
						populateMyLocationsBox();
					}
					refreshPanels();
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
			
			mnuFile.add(refresh);
			mnuFile.add(mniFileExit); //adds it to the menubar
			menubar.add(mnuFile);
	
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
		 * used to create Panel for the shorTermForecast Tab
		 * @param none
		 * @return none
		 */
		private void createFormTwo(){
			
			weatherData[] tmp = new weatherData[10]; //holds weather data objects
			app.grabShortTerm(); //grabs current weather data info from database
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
		private void createForm() {
			
			weatherData tmp = new weatherData();
			app.grab();
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