package cs2212b.team16;



/**
 * This class used to define the GUI for the application,
 * and calls the main weatherApp class only
 * 
 * @author Daniel Gilbert
 * @author Omar Abdel-Qader
 * @author James Crocker
 * @author Long Le
 * @author Angus Poole
 * @author Nicholas Teixeira
 * 
 */
import javax.imageio.ImageIO;
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
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;






//used to organize layout
import java.awt.Color;

import javax.swing.GroupLayout;

//import org.imgscalr.Scalr;




import cs2212b.team16.SearchIndex;
import cs2212b.team16.location;
import cs2212b.team16.weatherApp;
import cs2212b.team16.weatherData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

	public class MainWindow extends JFrame  {
		
		private weatherApp app = new weatherApp();
		private SearchIndex dataBase = new SearchIndex();
		
		//GUI Initializations to allow dynamic changes
		private JComboBox<String> locBar = new JComboBox<String>(); //used to list searched locations
		private JMenuBar menubar;
		private JLabel refreshLabel = new JLabel();
		private JTabbedPane tabbedPane = new JTabbedPane(); //creates a tab pane
		
		public Color color1 = new Color(160, 120, 240); //Top gradient color for Current panel
		public Color color2 = new Color(40, 10, 90); //Bottom gradient color for Current panel
		public Color shortColor1 = new Color(160, 120, 240); //Top gradient color for Short-Term panel
		public Color shortColor2 = new Color(40, 10, 90); //Bottom gradient color for Short-Term panel
		public Color longColor1 = new Color(160, 120, 240); //Top gradient color for Long-Term panel
		public Color longColor2 = new Color(40, 10, 90); //Bottom gradient color for Long-Term panel
		public Color marsColor1 = new Color(240, 58, 26); //Top gradient color for Mars panel
		public Color marsColor2 = new Color(10, 4, 3); //Bottom gradient color for Mars panel
		
		public Color[] shortColors = new Color[20]; //Array of colors for inside Short Panel
		public Color[] longColors = new Color[10]; //Array of colors for inside Long Panel

		private JPanel currentPanel = new JPanel(){ //used to display current data
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
//				Color color1 = new Color(30, 255, 90);
//				Color color2 = new Color(45, 110, 35);
				GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		
		private JPanel shortPanel = new JPanel(){ //used to display short data
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
//				Color color1 = new Color(30, 255, 90);
//				Color color2 = new Color(45, 110, 35);
				GradientPaint gp = new GradientPaint(0, 0, shortColor1, 0, h, shortColor2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		
		private JPanel longPanel = new JPanel(){ //used to display long data
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
//				Color color1 = new Color(30, 255, 90);
//				Color color2 = new Color(45, 110, 35);
				GradientPaint gp = new GradientPaint(0, 0, longColor1, 0, h, longColor2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		
		private JPanel marsPanel = new JPanel(){ //used to display mars data
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth();
				int h = getHeight();
//				Color color1 = new Color(30, 255, 90);
//				Color color2 = new Color(45, 110, 35);
				GradientPaint gp = new GradientPaint(0, 0, marsColor1, 0, h, marsColor2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		
		private ActionListener Jcombo = new ActionListener() { //updates weatherView when a new item is searched
			@Override
			public void actionPerformed(ActionEvent event){
				if (((JComboBox<String>)event.getSource()).getSelectedItem() != null){
					String item = ((JComboBox<String>)event.getSource()).getSelectedItem().toString();
					updateWeatherView(item);
				}
			}
		};
		
		/**
		 * This method instantiates an instance of the MainWindow that's been defined
		 */
		public MainWindow() {
			this.initUI();
			
		}
		
		/**
		 * This method initializes the User Interface elements defined below
		 */
private void initUI () {
			
			JFrame error = new JFrame();
			JFrame tmp = new JFrame();
			tmp.setSize(50, 50);
			String select = "cheese";
			boolean boolTemp = false;
			if(new File("prefFile.txt").exists() == false){ //if this is the first run
				while(select.equals("cheese")){
					select = JOptionPane.showInputDialog(tmp, "It appears this is your first run. "
							+ "Enter the city name of your current location:"); //prompts user for their current location
					if(select != null){
						boolTemp = searchBoxUsedTwo(select); //used the search function
					}
					if(boolTemp == false){
						select = "cheese";
					}
				}
				app.setCurrentLocation(app.getVisibleLocation()); //sets the current location
			}
			else{ //if it's been run before
				location tmpLoc = new location();
				try {
					tmpLoc = app.loadPref(); //load the location from memory
				} catch (Exception e) {
					JOptionPane.showMessageDialog(error, "An error occured");
				}
				app.setCurrentLocation(tmpLoc); //and set it as the current location
				app.setVisibleLocation(tmpLoc);
				
			}
			this.setTitle("Weather Application"); //sets title of frame 
			this.setSize(1300, 600); //sets size of frame
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE); //initiates exit on close command
			this.setJMenuBar(this.createMenubar()); 
			
			createFormCalls();
			
			tabbedPane.addTab("Current", null, currentPanel); //fills a tab window with current data
			tabbedPane.addTab("Short Term", null, shortPanel); //fills a tab window with short term data
			tabbedPane.addTab("Long Term", null, longPanel); //fills a tab window with short term data
			tabbedPane.addTab("Mars Weather", null, marsPanel); //fills a tab window with short term data
			
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
		
		/**
		 * This method refreshes the User Interface elements defined below
		 */
		private void refreshPanels(){
			
			
			currentPanel.removeAll();
			shortPanel.removeAll();
			longPanel.removeAll();
			marsPanel.removeAll();
			
			createFormCalls();
			
			updateRefreshTime();
		}
		
		/**
		 * This method makes calls to populate tab information
		 */
		private void createFormCalls(){
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
			try {
				createFormMars();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(error, "An error occured");
				return;
			}
		}
		
		/**
		 * This method is used to fill the location box with preset locations
		 */
		private void populateMyLocationsBox(){
			//locBar.removeActionListener(Jcombo);
			locBar.removeAllItems();
			for (int i = 0; i < app.getMyLocations().length; i ++){
				location tempLoc = app.getMyLocations()[i];
				if (tempLoc.getCityID() != 0){
					String val = tempLoc.getName() + ", " + tempLoc.getCountryCode() + " Lat: " + tempLoc.getLatitude() + " Long: " + tempLoc.getLongitude();
					locBar.addItem(val);
				} 
			}
			if (locBar.getItemCount() == 0){
				locBar.addItem("--Empty--");
			} else {
				locBar.addItem("--Remove?--");
			}
			locBar.addActionListener(Jcombo);
		}
		
		/**
		 * This method refresh the views when saved city is clicked
		 * @param cityName the name of the selected city
		 */
		private void updateWeatherView(String cityName){
			
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
					locBar.removeActionListener(Jcombo);
					populateMyLocationsBox();
						
			 } else if (cityName.equals("--Empty--")){
						//Do nothing
			} else {
					location update = new location();
					String countryCode = cityName.substring(cityName.indexOf(',') + 2, cityName.indexOf(',') + 4);
					cityName = cityName.substring(0, cityName.indexOf(','));
					for (int i = 0; i < app.getMyLocations().length; i ++){
						String checkName = app.getMyLocations()[i].getName();
						String checkCode = app.getMyLocations()[i].getCountryCode();
						if (checkName.equals(cityName) && checkCode.equals(countryCode)){
							update = app.getMyLocations()[i];
							break;
						}
					}
					app.setVisibleLocation(update);
					refreshPanels();
			}
		}
				
		/**
		 * This method refreshes the views when new city is searched
		 * 
		 * @param txt a String of the city searched for
		 */
		private void searchBoxUsed(String txt){
			JFrame frame = new JFrame();
			ArrayList<location> simLoc = dataBase.search(txt);
			if (simLoc == null) JOptionPane.showMessageDialog(frame, "'" + txt + "' not found.");
			else if (simLoc.size() == 1){
				location searchedLoc = simLoc.get(0);
				app.addLocation(searchedLoc);
				app.setVisibleLocation(searchedLoc);
				locBar.removeActionListener(Jcombo);
				populateMyLocationsBox();
				refreshPanels();
			}
			else {
				String [] possibilities = new String[simLoc.size()];
				
				for (int i = 0; i <  simLoc.size(); i ++){
					possibilities[i] = i + 1 + ". " + simLoc.get(i).getName() + ", " + simLoc.get(i).getCountryCode() + " Lat: " 
							+ simLoc.get(i).getLatitude() + " Long: " + simLoc.get(i).getLongitude();
				}
				
				String response = (String) JOptionPane.showInputDialog(frame, "Which '" + txt + "' did you mean?", "Search Location",  
						JOptionPane.QUESTION_MESSAGE, null, possibilities, "Titan");
			
				if (response != null) {
						location searchedLoc = simLoc.get(Integer.parseInt(response.substring(0, response.indexOf('.'))) - 1);
						String[] temp = response.split(" ");
						int length = app.getMyLocations().length;
						boolean add = true;
						for (int i = 0; i < length; i ++){
							location checkLoc = app.getMyLocations()[i];
							if (checkLoc.getCityID() == searchedLoc.getCityID())
								add = false;
						}
						if (add) {
							app.addLocation(searchedLoc);
							locBar.removeActionListener(Jcombo);
							populateMyLocationsBox();
						}
						app.setVisibleLocation(searchedLoc);
						refreshPanels();
				 }
			}
		}
		
		/**
		 * This method reefreshes the views when new city is searched on first run
		 * 
		 * @param txt a String of the city searched for
		 * @return boolean representing if the search box was used 
		 */
		private boolean searchBoxUsedTwo(String txt){
			JFrame frame = new JFrame();
			boolean boolTemp;
			ArrayList<location> simLoc = dataBase.search(txt);
			if (simLoc == null){ 
				JOptionPane.showMessageDialog(frame, "'" + txt + "' not found.");
				boolTemp = false;
			}
			else if (simLoc.size() == 1){
				location searchedLoc = simLoc.get(0);
				app.addLocation(searchedLoc);
				app.setVisibleLocation(searchedLoc);
				locBar.removeActionListener(Jcombo);
				populateMyLocationsBox();
				boolTemp = true;
			}
			else {
				String [] possibilities = new String[simLoc.size()];
				
				for (int i = 0; i <  simLoc.size(); i ++){
					possibilities[i] = i + 1 + ". " + simLoc.get(i).getName() + ", " + simLoc.get(i).getCountryCode() + " Lat: " 
							+ simLoc.get(i).getLatitude() + " Long: " + simLoc.get(i).getLongitude();
				}
				
				String response = (String) JOptionPane.showInputDialog(frame, "Which '" + txt + "' did you mean?", "Search Location",  
						JOptionPane.QUESTION_MESSAGE, null, possibilities, "Titan");
			
				if (response != null) {
						location searchedLoc = simLoc.get(Integer.parseInt(response.substring(0, response.indexOf('.'))) - 1);
						String[] temp = response.split(" ");
						int length = app.getMyLocations().length;
						boolean add = true;
						for (int i = 0; i < length; i ++){
							location checkLoc = app.getMyLocations()[i];
							if (checkLoc.getCityID() == searchedLoc.getCityID())
								add = false;
						}
						if (add) {
							app.addLocation(searchedLoc);
							locBar.removeActionListener(Jcombo);
							populateMyLocationsBox();
						}
						app.setVisibleLocation(searchedLoc);
						
				 }
				boolTemp = true;
			}
			return boolTemp;
		}
		
		/**
		 * This method defines a Menu bar with a "File" option, which can be called by "alt + f"
		 * menu bar contains option for exiting current program
		 */
		private JMenuBar createMenubar() {
			JFrame error = new JFrame();
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
			reset.setMnemonic(KeyEvent.VK_R);
			reset.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
				    JFrame frame = new JFrame();
				    String txt = JOptionPane.showInputDialog(frame, "Enter the city name of your new current location:"); //prompts user for their current location
					ArrayList<location> simLoc = dataBase.search(txt);
					if (simLoc == null) JOptionPane.showMessageDialog(frame, "'" + txt + "' not found.");
					else if (simLoc.size() == 1){
						location searchedLoc = simLoc.get(0);
						app.setCurrentLocation(searchedLoc);
						app.setVisibleLocation(searchedLoc);
						locBar.removeActionListener(Jcombo);
						populateMyLocationsBox();
						refreshPanels();
					}
					else {
						String [] possibilities = new String[simLoc.size()];
						
						for (int i = 0; i <  simLoc.size(); i ++){
							possibilities[i] = i + 1 + ". " + simLoc.get(i).getName() + ", " + simLoc.get(i).getCountryCode() + " Lat: " 
									+ simLoc.get(i).getLatitude() + " Long: " + simLoc.get(i).getLongitude();
						}
						
						String response = (String) JOptionPane.showInputDialog(frame, "Which '" + txt + "' did you mean?", "Search Location",  
								JOptionPane.QUESTION_MESSAGE, null, possibilities, "Titan");
					
						if (response != null) {
								location searchedLoc = simLoc.get(Integer.parseInt(response.substring(0, response.indexOf('.'))) - 1);
								app.setCurrentLocation(searchedLoc);
								app.setVisibleLocation(searchedLoc);
								locBar.removeActionListener(Jcombo);
								populateMyLocationsBox();
								refreshPanels();
						 }
					}
				 }
			 	
			});
			
			JMenuItem switchTo = new JMenuItem("Switch to Current");
			switchTo.setMnemonic(KeyEvent.VK_S);
			switchTo.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
				 app.setVisibleLocation(app.getCurrentLocation());
				 refreshPanels();
			 }
				 
			 });
			
			//Refresh button
			JMenuItem refresh = new JMenuItem("Refresh");
			refresh.setMnemonic(KeyEvent.VK_F);
			updateRefreshTime();
			refresh.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {	
				 refreshPanels(); 
			 	}
			});
			mnuFile.add(switchTo);
			mnuFile.add(reset);
			mnuFile.add(refresh);
			mnuFile.add(mniFileExit); //adds it to the menubar
			menubar.add(mnuFile);
			
			//oF/oC check box
			JCheckBox degree = new JCheckBox();
			degree.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event){
					if(((JCheckBox) event.getSource()).isSelected()){
						app.setUnits("imperial");
					}
					else{
						app.setUnits("metric");
					}
					refreshPanels();
				}
			});
			menubar.add(degree);
			JLabel degLabel = new JLabel("oF");
			menubar.add(degLabel);
			menubar.add(new JLabel("   "));
			
			//Locations -> MyLocations
			locBar.removeActionListener(Jcombo);
			populateMyLocationsBox();
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
		
		/**
		 * This method is used to refresh update time on new weather grab	
		 */
		private void updateRefreshTime(){
			refreshLabel.setText("Last Updated: " + (new Date()).toString());
		}
		
		/**
		 * This method is used to fill a panel with the Long term weather Data
		 */
		private void createFormThree() throws IOException{
			
			weatherData[] tmp = new weatherData[8]; //holds weather data objects
			try {
				app.grabLongTerm(app.getVisibleLocation().getCityID(), app.getUnits()); //grabs long term weather data info from database
			} catch (IOException e) {
				throw new IOException("error");
			} 
			tmp = app.getLongTerm(); //grabs weatherData objects now filled with data
			
			//Left-most panel
			JLabel lbl1 = new JLabel("Date: ");
			lbl1.setForeground(Color.WHITE);
			JLabel lbl2 = new JLabel("Temp: ");
			lbl2.setForeground(Color.WHITE);
			JLabel lbl2A = new JLabel("Min-Temp: ");
			lbl2A.setForeground(Color.WHITE);
			JLabel lbl2B = new JLabel("Max-Temp: ");
			lbl2B.setForeground(Color.WHITE);
			JLabel lbl3 = new JLabel("Weather Condition:");
			lbl3.setForeground(Color.WHITE);
			
			//Second set
			JLabel lbl4 = new JLabel("Date: ");
			lbl4.setForeground(Color.WHITE);
			JLabel lbl5 = new JLabel("Temp: ");
			lbl5.setForeground(Color.WHITE);
			JLabel lbl5A = new JLabel("Min-Temp: ");
			lbl5A.setForeground(Color.WHITE);
			JLabel lbl5B = new JLabel("Max-Temp: ");
			lbl5B.setForeground(Color.WHITE);
			JLabel lbl6 = new JLabel("Weather Condition:");
			lbl6.setForeground(Color.WHITE);
			
			JLabel lblcity = new JLabel(tmp[0].getName() + ", " + tmp[0].getCount() + ", " + tmp[0].getLon() + ", " + tmp[0].getLat() ); //displays location info
			lblcity.setForeground(Color.WHITE);
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
			for(int i =0; i<5; i++){
				hold = new JLabel("" +tmp[i+1].getTemp()); //holds current temp
				temp[i] = hold; //puts it in the array
				hold2 = new JLabel("" +tmp[i+1].getCondit()); //holds current condition
				descrip[i] = hold2;
				
				pic = tmp[i+1].getIcon();
				//pic  = Scalr.resize(pic, 80);
				hold3 = new JLabel(new ImageIcon(pic)); //holds current pic
				lblPic[i] = hold3;
				hold4 = new JLabel("" + tmp[i+1].getMin());//holds current min
				min[i] = hold4;
				hold5 = new JLabel("" + tmp[i+1].getMax());//holds current max
				max[i] = hold5;
				hold6 = new JLabel("" + tmp[i+1].getSunrise());//timestamp stored in Sunrise, since this variable was not in use for ShorTerm
				date[i] = hold6;
				
				temp[i].setForeground(Color.WHITE);
				descrip[i].setForeground(Color.WHITE);
				min[i].setForeground(Color.WHITE);
				max[i].setForeground(Color.WHITE);
				date[i].setForeground(Color.WHITE);
			}

			JPanel[] subLong = new JPanel[7]; //Array of sub-panels for long-term
			JPanel blank = new JPanel(); //Empty panel
			JPanel top = new JPanel(); //Upper panel
			JPanel bot = new JPanel(); //Lower panel
			GroupLayout[] layout = new GroupLayout[12];
			
			GridLayout outGrid = new GridLayout(2, 1); //Overall grid: top and bottom
			GridLayout inGridA = new GridLayout(1, 4); //Grid inside the outer grid at the top
			GridLayout inGridB = new GridLayout(1, 4); //Grid inside the outer grid at the bottom
			
			longPanel.setLayout(outGrid);
			top.setLayout(inGridA);
			bot.setLayout(inGridB);
			
			//Sets the ShortTerm cell colors to default values
			resetLongColors();
			
			//Initializes the sub-panels so components can be added to them 
			makeLongComps(subLong);
			
			//Sets layout and base background color for each sub-panel
			for(int i = 0; i < 7; i++)
			{
				subLong[i].setBackground(Color.BLACK);
				layout[i] = new GroupLayout(subLong[i]);
				layout[i].setAutoCreateContainerGaps(true);
			}
			
			blank.setBackground(Color.BLACK);

			//Group layout used to organize GUI - layer[0] is the upper-left panel
			layout[0].setHorizontalGroup( layout[0].createSequentialGroup() //sets the horizontal groups
						
						.addGroup(layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //shows city data
								.addComponent(lblcity)
								.addGap(10)
								.addComponent(lbl1)
								.addComponent(lbl2)
								.addComponent(lbl2A)
								.addComponent(lbl2B)
								.addComponent(lbl3)
								
						)						
					);
			layout[0].setVerticalGroup( layout[0].createSequentialGroup() //sets the vertical groups
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.BASELINE)//city is in its own vertical group
							.addComponent(lblcity)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGap(35)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //times aligned vertically
							.addComponent(lbl2)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
							.addComponent(lbl3)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
							.addComponent(lbl2A)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
							.addComponent(lbl2B)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //descrips alligned vertically
							.addComponent(lbl1)
							)
					);
			
			//Organizes layout of layer[6] - layer[6] is a copy of layer[0] but without the city name
			layout[6].setHorizontalGroup( layout[6].createSequentialGroup() //sets the horizontal groups
					
					.addGroup(layout[6].createParallelGroup(GroupLayout.Alignment.LEADING) //shows city data
							.addGap(20)
							.addComponent(lbl4)
							.addComponent(lbl5)
							.addComponent(lbl5A)
							.addComponent(lbl5B)
							.addComponent(lbl6)
							
					)						
				);
			layout[6].setVerticalGroup( layout[6].createSequentialGroup() //sets the vertical groups
				.addGroup( layout[6].createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGap(65)
						)
				.addGroup( layout[6].createParallelGroup(GroupLayout.Alignment.LEADING) //times aligned vertically
						.addComponent(lbl5)
						)
				.addGroup( layout[6].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
						.addComponent(lbl6)
						)
				.addGroup( layout[6].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
						.addComponent(lbl5A)
						)
				.addGroup( layout[6].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
						.addComponent(lbl5B)
						)
				.addGroup( layout[6].createParallelGroup(GroupLayout.Alignment.LEADING) //descrips alligned vertically
						.addComponent(lbl4)
						)
				);
			
			//Sets content for the actual weather panels/cells within the grid
			for(int i = 0; i < 5; i++)
			{
				layout[i+1].setHorizontalGroup( layout[i+1].createSequentialGroup() //sets the horizontal groups
						.addGroup(layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING)							
							.addComponent(lblPic[i])
							.addComponent(temp[i])
							.addComponent(descrip[i])
							.addComponent(min[i])
							.addComponent(max[i])
							.addComponent(date[i])
						)
					);
				layout[i+1].setVerticalGroup( layout[i+1].createSequentialGroup() //sets the vertical groups
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.BASELINE) //pics aligned vertically
								.addComponent(lblPic[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //pics aligned vertically
								.addComponent(temp[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //pics aligned vertically
								.addComponent(descrip[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //pics aligned vertically
								.addComponent(min[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //pics aligned vertically
								.addComponent(max[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //pics aligned vertically
								.addComponent(date[i])
								)
						);
			}
			
			//Adds layout[0] to the top section
			subLong[0].setLayout(layout[0]);
			top.add(subLong[0]);
			subLong[0].validate();
			subLong[0].repaint();
			
			//Sets the layout and colors for the rest of the top panels
			for(int i = 1; i < 4; i++)
			{
				subLong[i].setLayout(layout[i]);
				top.add(subLong[i]);
				setLongBgColors(tmp[i], i-1, i+4);
				subLong[i].validate();
				subLong[i].repaint();
			}
			
			//Adds layer[6] to the bottom section
			subLong[6].setLayout(layout[6]);
			bot.add(subLong[6]);
			subLong[6].validate();
			subLong[6].repaint();
			
			//Sets the layout and colors for the rest of the bottom panels
			for(int i = 4; i < 6; i++)
			{
				subLong[i].setLayout(layout[i]); //sets the defined layout to the panel
				bot.add(subLong[i]);
				setLongBgColors(tmp[i], i-1, i+4);
				subLong[i].validate();
				subLong[i].repaint();
			}
			bot.add(blank);
			longPanel.add(top);
			longPanel.add(bot);
		}

		/**
		 * This method resets the short-term color values to white and black
		 */
		public void resetLongColors(){
			for(int i = 0; i < 5; i++){
				longColors[i] = Color.white;
		}
			for(int i = 0; i < 10; i++){
				longColors[i] = Color.black;
			}
		}

		/**
		 * This method initializes the long-term subpanels so that they can have gradient backgrounds
		 */
		public void makeLongComps(JPanel[] subLong){
			subLong[0] = new JPanel();
			subLong[1] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, longColors[0], 0, h, longColors[5]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};

			subLong[2] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, longColors[1], 0, h, longColors[6]); 
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};

			subLong[3] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, longColors[2], 0, h, longColors[7]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};

			subLong[4] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, longColors[3], 0, h, longColors[8]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};

			subLong[5] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, longColors[4], 0, h, longColors[9]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			subLong[6] = new JPanel();
		}

		
		/**
		 * This method is used to create Panel for the shorTermForecast Tab
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
			lbl1.setForeground(Color.WHITE);
			JLabel lbl2 = new JLabel("Temp: ");
			lbl2.setForeground(Color.WHITE);
			JLabel lbl3 = new JLabel("Weather Condition:");
			lbl3.setForeground(Color.WHITE);
			
			//Clone of the above labels for 2nd side cell
			JLabel lbl4 = new JLabel("Time: ");
			lbl4.setForeground(Color.WHITE);
			JLabel lbl5 = new JLabel("Temp: ");
			lbl5.setForeground(Color.WHITE);
			JLabel lbl6 = new JLabel("Weather Condition:");
			lbl6.setForeground(Color.WHITE);

			//Clone of the above labels for 3rd side cell
			JLabel lbl7 = new JLabel("Time: ");
			lbl7.setForeground(Color.WHITE);
			JLabel lbl8 = new JLabel("Temp: ");
			lbl8.setForeground(Color.WHITE);
			JLabel lbl9 = new JLabel("Weather Condition:");
			lbl9.setForeground(Color.WHITE);
			
			JLabel lblcity = new JLabel(tmp[0].getName() + ", " + tmp[0].getCount() + ", " + tmp[0].getLon() + ", " + tmp[0].getLat() ); //displays location info
			lblcity.setForeground(Color.WHITE);
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
				//pic  = Scalr.resize(pic, 80);
				hold3 = new JLabel(new ImageIcon(pic));
				lblPic[i] = hold3;
				hold4 = new JLabel(tmp[i+1].getSunrise());//timestamp stored in Sunrise, since this variable was not in use for ShorTerm
				time[i] = hold4;
				
				temp[i].setForeground(Color.WHITE);
				descrip[i].setForeground(Color.WHITE);
				time[i].setForeground(Color.WHITE);				
			}
			
			//Sub-panels within the main panel
			JPanel[] subShort = new JPanel[12];
			GroupLayout[] layout = new GroupLayout[12];
			
			//Grid format for the panel
			GridLayout grid = new GridLayout(3, 5);
			shortPanel.setLayout(grid);
			
			//Sets the ShortTerm cell colors to default values
			resetShortColors();			
			//Initializes the sub-panels so components can be added to them 
			makeShortComps(subShort);
			
			//Sets the layout for each sub-panel
			for(int i = 0; i < 12; i++)
			{
				subShort[i].setBackground(Color.BLACK);
				layout[i] = new GroupLayout(subShort[i]);
				layout[i].setAutoCreateContainerGaps(true);
			}
			
			//Group layout used to organize GUI - sets format of top side panel
			layout[0].setHorizontalGroup( layout[0].createSequentialGroup() //sets the horizontal groups
						
						.addGroup(layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //shows city data
								.addComponent(lblcity)
								.addGap(10)
								.addComponent(lbl1)
								.addComponent(lbl2)
								.addComponent(lbl3)
								
						)						
					);
			layout[0].setVerticalGroup( layout[0].createSequentialGroup() //sets the vertical groups
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.BASELINE)//city is in its own vertical group
							.addComponent(lblcity)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGap(35)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //time aligned vertically
							.addComponent(lbl1)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //temp aligned vertically
							.addComponent(lbl2)
							)
					.addGroup( layout[0].createParallelGroup(GroupLayout.Alignment.LEADING) //weather condition alligned vertically
							.addComponent(lbl3)
							)
					);
			
			//Sets layout for side panel #2 similar to layer[0] layout
			layout[10].setHorizontalGroup( layout[10].createSequentialGroup() //sets the horizontal groups
					
					.addGroup(layout[10].createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGap(10)
							.addComponent(lbl4)
							.addComponent(lbl5)
							.addComponent(lbl6)
							
					)						
				);
		//Second cell of the left side column
		layout[10].setVerticalGroup( layout[10].createSequentialGroup() //sets the vertical groups
				.addGroup( layout[10].createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(65)
						)
				.addGroup( layout[10].createParallelGroup(GroupLayout.Alignment.LEADING) //time aligned vertically
						.addComponent(lbl4)
						)
				.addGroup( layout[10].createParallelGroup(GroupLayout.Alignment.LEADING) //temp aligned vertically
						.addComponent(lbl5)
						)
				.addGroup( layout[10].createParallelGroup(GroupLayout.Alignment.LEADING) //weather condition alligned vertically
						.addComponent(lbl6)
						)
				);
		
		//Third cell of the left side column
		layout[11].setHorizontalGroup( layout[11].createSequentialGroup() //sets the horizontal groups
				
				.addGroup(layout[11].createParallelGroup(GroupLayout.Alignment.LEADING) //shows city data
						.addGap(10)
						.addComponent(lbl7)
						.addComponent(lbl8)
						.addComponent(lbl9)
						
				)						
			);
		//Third cell of the left side column
		layout[11].setVerticalGroup( layout[11].createSequentialGroup() //sets the vertical groups
			.addGroup( layout[11].createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addGap(65)
					)
			.addGroup( layout[11].createParallelGroup(GroupLayout.Alignment.LEADING) //times aligned vertically
					.addComponent(lbl7)
					)
			.addGroup( layout[11].createParallelGroup(GroupLayout.Alignment.LEADING) //temp aligned vertically
					.addComponent(lbl8)
					)
			.addGroup( layout[11].createParallelGroup(GroupLayout.Alignment.LEADING) //weather condition alligned vertically
					.addComponent(lbl9)
					)
			);
			
			//Actual weather cells
			for(int i = 0; i < 9; i++)
			{
				layout[i+1].setHorizontalGroup( layout[i+1].createSequentialGroup() //sets the horizontal groups
						.addGroup(layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING)							
							.addComponent(lblPic[i])
							.addComponent(time[i])
							.addComponent(temp[i])
							.addComponent(descrip[i])
						)
					);
				layout[i+1].setVerticalGroup( layout[i+1].createSequentialGroup() //sets the vertical groups
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.BASELINE) //pics aligned vertically
								.addComponent(lblPic[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //times aligned vertically
								.addComponent(time[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //temps aligned vertically
								.addComponent(temp[i])
								)
						.addGroup( layout[i+1].createParallelGroup(GroupLayout.Alignment.LEADING) //conditions aligned vertically
								.addComponent(descrip[i])
								)
						);
			}

			subShort[0].setLayout(layout[0]); //sets the layout for the first left-side panel
			shortPanel.add(subShort[0]);
			
			//Sets the layout for each weather panel
			for(int i = 1; i < 10; i++)
			{
				subShort[i].setLayout(layout[i]); //sets the defined layout to the panel
				shortPanel.add(subShort[i]);
					setShortBgColors(tmp[i], i, i+10);
				if(i == 3){ //sets the layout for the second left-side panel
					subShort[10].setLayout(layout[10]);
					subShort[10].validate();
					subShort[10].repaint();
					shortPanel.add(subShort[10]);
				}
				else if(i == 6){ //sets the layout for the third left-side panel
					subShort[11].setLayout(layout[11]);
					subShort[11].validate();
					subShort[11].repaint();
					shortPanel.add(subShort[11]);
				}
				subShort[i].validate();
				subShort[i].repaint();
			}			
		}
		
		/**
		 * This method Resets the short-term color values to white and black
		 */
		public void resetShortColors(){
			for(int i = 0; i < 10; i++){
				shortColors[i] = Color.white;
			}
			for(int i = 0; i < 20; i++){
				shortColors[i] = Color.black;
			}
		}
		
		/**
		 * This method initializes the short-term subpanels so that they can have gradient backgrounds
		 */
		public void makeShortComps(JPanel[] subShort){
			subShort[0] = new JPanel(){ //Side Panel #1
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[0], 0, h, shortColors[10]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[1] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[1], 0, h, shortColors[11]); 
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[2] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[2], 0, h, shortColors[12]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[3] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[3], 0, h, shortColors[13]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[4] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[4], 0, h, shortColors[14]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[5] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[5], 0, h, shortColors[15]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[6] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[6], 0, h, shortColors[16]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[7] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[7], 0, h, shortColors[17]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[8] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[8], 0, h, shortColors[18]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[9] = new JPanel(){
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth();
					int h = getHeight();
					GradientPaint gp = new GradientPaint(0, 0, shortColors[9], 0, h, shortColors[19]);
					g2d.setPaint(gp);
					g2d.fillRect(2, 2, w, h);
				}
			};
			
			subShort[10] = new JPanel(); //Side Panel #2
			subShort[11] = new JPanel(); //Side Panel #3
		}
		
		/**
		 * This method is used to add current weather data to a Panel
		 */
		private void createForm() throws IOException {
			
			 
			weatherData tmp = new weatherData();
			try {
				app.grab(app.getVisibleLocation().getCityID(), app.getUnits());
			} catch (IOException e) {
				throw new IOException("error");
			}
			
			tmp = app.getCurrent();
			
			JLabel lblcity = new JLabel(tmp.getName() + ", " + tmp.getCount() + ", " + tmp.getLon() + ", " + tmp.getLat()); //displays location info
			lblcity.setForeground(Color.WHITE);
			lblcity.setFont(new Font("Lucida Console", Font.PLAIN, 40));
			JLabel lbldescrip = new JLabel("Weather Condition: ");
			lbldescrip.setForeground(Color.WHITE);
			lbldescrip.setFont(new Font("Courier New", Font.BOLD, 18));
			JLabel lbldescrip2 = new JLabel("" +tmp.getCondit());
			
			lbldescrip2.setForeground(Color.WHITE);
			lbldescrip2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lbltemp = new JLabel("Temp:  "); //label for temp
			lbltemp.setForeground(Color.WHITE);
			lbltemp.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lbltemp2 = new JLabel("" + tmp.getTemp()); //actual temp
			lbltemp2.setForeground(Color.WHITE);
			lbltemp2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblmin = new JLabel("Min Temp: "); //label for min
			lblmin.setForeground(Color.WHITE);
			lblmin.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblmin2 = new JLabel("" + tmp.getMin()); //actual min
			lblmin2.setForeground(Color.WHITE);
			lblmin2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblmax = new JLabel("Max Temp: " ); //label for max
			lblmax.setForeground(Color.WHITE);
			lblmax.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblmax2 = new JLabel("" + tmp.getMax()); //actual max
			lblmax2.setForeground(Color.WHITE);
			lblmax2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblspeed = new JLabel("Wind Speed: "); //label for speed
			lblspeed.setForeground(Color.WHITE);
			lblspeed.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblspeed2 = new JLabel("" + tmp.getSpeed()); //actual speed
			lblspeed2.setForeground(Color.WHITE);
			lblspeed2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lbldir = new JLabel("Wind Direction: "); //label for dir 
			lbldir.setForeground(Color.WHITE);
			lbldir.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lbldir2 = new JLabel("" + tmp.getDir()); //actual dir
			lbldir2.setForeground(Color.WHITE);
			lbldir2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblpress = new JLabel("Air Pressure: "); //label for pressure
			lblpress.setForeground(Color.WHITE);
			lblpress.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblpress2 = new JLabel("" + tmp.getPress()); //actual pressure
			lblpress2.setForeground(Color.WHITE);
			lblpress2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblhumid = new JLabel("Humidity: "); //label for humid
			lblhumid.setForeground(Color.WHITE);
			lblhumid.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblhumid2 = new JLabel("" + tmp.getHumid()); //actual humid
			lblhumid2.setForeground(Color.WHITE);
			lblhumid2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblrise = new JLabel("Sunrise Time: "); //label for sunrise
			lblrise.setForeground(Color.WHITE);
			lblrise.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblrise2 = new JLabel("" + tmp.getSunrise()); //actual sunrise
			lblrise2.setForeground(Color.WHITE);
			lblrise2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblset = new JLabel("Sunset Time: "); //label for susnet
			lblset.setForeground(Color.WHITE);
			lblset.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			JLabel lblset2 = new JLabel("" + tmp.getSunset()); //actual sunset
			lblset2.setForeground(Color.WHITE);
			lblset2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, lbldescrip.getFont().getSize()));
			
			//used to set picture
			BufferedImage pic = tmp.getIcon();
			//pic  = Scalr.resize(pic, 80);
			JLabel lblPic = new JLabel(new ImageIcon(pic)); //holds picture
			
			//Set the colours for the background gradient
			setBgColours(tmp);
			
			//adds control with layout organization
			GroupLayout layout = new GroupLayout(currentPanel);
			currentPanel.setLayout(layout);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup() //sets horizontal groups
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER) //holds all the identifier labels
							.addComponent(lblcity)
							.addComponent(lblPic)
							.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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

							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) //sets weather Data labels
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
						)
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
							
						//currentPanel.setLayout(layout); //sets the layout		
			currentPanel.validate();
			currentPanel.repaint();

			 try {
					app.storePref();
				} catch (Exception e) {
					JFrame error = new JFrame();
					JOptionPane.showMessageDialog(error, "An error occured");
				}
			}

		//Sets background colours to a gradient effect based on current weather		
		public void setLongBgColors(weatherData tmp, int i, int j) {
			switch(tmp.getCondit()) {
				case "sky is clear ":
				case "clear sky ":
				case "Sky is Clear ":
							longColors[i] = new Color(255, 215,0);
							longColors[j] = new Color(255, 111, 0);
							break;
				case "few clouds ":
							longColors[i] = new Color(160, 255, 0);
							longColors[j] = new Color(9, 173, 33);
							break;
				case "scattered clouds ":
							longColors[i] = new Color(30, 255, 90);
							longColors[j] = new Color(45, 110, 35);
							break;
				case "broken clouds ":
				case "overcast clouds ":
							longColors[i] = new Color(30, 255, 150);
							longColors[j] = new Color(40, 150, 130);
							break;
				case "shower rain ":
				case "light intensity drizzle ":
				case "drizzle ":
				case "heavy intensity drizzle ":
				case "light intensity drizzle rain ":
				case "drizzle rain ":
				case "heavy intensity drizzle rain ":
				case "shower rain and drizzle ":
				case "heavy shower rain and drizzle ":
				case "shower drizzle ":
				case "light intensity shower rain ":
				case "heavy intensity shower rain ":
				case "ragged shower rain ":
							longColors[i] = new Color(0,255,255);
							longColors[j] = new Color(30, 130, 160);
							break;
				case "rain ":
				case "light rain ":
				case "moderate rain ":
				case "heavy intensity rain ":
				case "very heavy rain ":
				case "extreme rain ":
							longColors[i] = new Color(0, 166, 255);
							longColors[j] = new Color(30, 50, 160);
							break;
				case "thunderstorm ":
				case "thunderstorm with light rain ":
				case "thunderstorm with rain ":
				case "thunderstorm with heavy rain ":
				case "light thunderstorm ":
				case "heavy thunderstorm ":
				case "ragged thunderstorm ":
				case "thunderstorm with light drizzle ":
				case "thunderstorm with drizzle ":
				case "thunderstorm with heavy drizzle ":
							longColors[i] = new Color(0, 95, 255);
							longColors[j] = new Color(60, 30, 160);
							break;
				case "snow ":
				case "freezing rain ":
				case "light snow ":
				case "heavy snow ":
				case "sleet ":
				case "shower sleet ":
				case "light rain and snow ":
				case "rain and snow ":
				case "light shower snow ":
				case "shower snow ":
				case "heavy shower snow ":
							longColors[i] = new Color(145, 245, 245);
							longColors[j] = new Color(75, 150, 160);
							break;
				case "mist ":
				case "smoke ":
				case "haze ":
				case "sand, dust whirls ":
				case "fog ":
				case "sand ":
				case "dust ":
				case "volcanic ash ":
				case "squalls ":
				case "tornado ":
							longColors[i] = new Color(200, 210, 210);
							longColors[j] = new Color(85, 110, 100);
							break;
				default:
							longColors[i] = new Color(160, 120, 240);
							longColors[j] = new Color(40, 10, 90);
							break;
			}
		}

		
		/**
		 * This method sets background colours to a gradient effect based on current weather
		 */
		public void setShortBgColors(weatherData tmp, int i, int j) {
			switch(tmp.getCondit()) {
				case "sky is clear ":
				case "clear sky ":
				case "Sky is Clear ":
							shortColors[i] = new Color(255, 215,0);
							shortColors[j] = new Color(255, 111, 0);
							break;
				case "few clouds ":
							shortColors[i] = new Color(160, 255, 0);
							shortColors[j] = new Color(9, 173, 33);
							break;
				case "scattered clouds ":
							shortColors[i] = new Color(30, 255, 90);
							shortColors[j] = new Color(45, 110, 35);
							break;
				case "broken clouds ":
				case "overcast clouds ":
							shortColors[i] = new Color(30, 255, 150);
							shortColors[j] = new Color(40, 150, 130);
							break;
				case "shower rain ":
				case "light intensity drizzle ":
				case "drizzle ":
				case "heavy intensity drizzle ":
				case "light intensity drizzle rain ":
				case "drizzle rain ":
				case "heavy intensity drizzle rain ":
				case "shower rain and drizzle ":
				case "heavy shower rain and drizzle ":
				case "shower drizzle ":
				case "light intensity shower rain ":
				case "heavy intensity shower rain ":
				case "ragged shower rain ":
							shortColors[i] = new Color(0,255,255);
							shortColors[j] = new Color(30, 130, 160);
							break;
				case "rain ":
				case "light rain ":
				case "moderate rain ":
				case "heavy intensity rain ":
				case "very heavy rain ":
				case "extreme rain ":
							shortColors[i] = new Color(0, 166, 255);
							shortColors[j] = new Color(30, 50, 160);
							break;
				case "thunderstorm ":
				case "thunderstorm with light rain ":
				case "thunderstorm with rain ":
				case "thunderstorm with heavy rain ":
				case "light thunderstorm ":
				case "heavy thunderstorm ":
				case "ragged thunderstorm ":
				case "thunderstorm with light drizzle ":
				case "thunderstorm with drizzle ":
				case "thunderstorm with heavy drizzle ":
							shortColors[i] = new Color(0, 95, 255);
							shortColors[j] = new Color(60, 30, 160);
							break;
				case "snow ":
				case "freezing rain ":
				case "light snow ":
				case "heavy snow ":
				case "sleet ":
				case "shower sleet ":
				case "light rain and snow ":
				case "rain and snow ":
				case "light shower snow ":
				case "shower snow ":
				case "heavy shower snow ":
							shortColors[i] = new Color(145, 245, 245);
							shortColors[j] = new Color(75, 150, 160);
							break;
				case "mist ":
				case "smoke ":
				case "haze ":
				case "sand, dust whirls ":
				case "fog ":
				case "sand ":
				case "dust ":
				case "volcanic ash ":
				case "squalls ":
				case "tornado ":
							shortColors[i] = new Color(200, 210, 210);
							shortColors[j] = new Color(85, 110, 100);
							break;
				default:
							shortColors[i] = new Color(160, 120, 240);
							shortColors[j] = new Color(40, 10, 90);
							break;
			}
		}
		
		/**
		 * This method sets background colours to a gradient effect based on current weather
		 */
				public void setBgColours(weatherData tmp) {
					switch(tmp.getCondit()) {
						case "sky is clear ":
						case "clear sky ":
						case "Sky is Clear ":
									color1 = new Color(255, 215,0);
									color2 = new Color(255, 111, 0);
									break;
						case "few clouds ":
									color1 = new Color(160, 255, 0);
									color2 = new Color(9, 173, 33);
									break;
						case "scattered clouds ":
									color1 = new Color(30, 255, 90);
									color2 = new Color(45, 110, 35);
									break;
						case "broken clouds ":
						case "overcast clouds ":
									color1 = new Color(30, 255, 150);
									color2 = new Color(40, 150, 130);
									break;
						case "shower rain ":
						case "light intensity drizzle ":
						case "drizzle ":
						case "heavy intensity drizzle ":
						case "light intensity drizzle rain ":
						case "drizzle rain ":
						case "heavy intensity drizzle rain ":
						case "shower rain and drizzle ":
						case "heavy shower rain and drizzle ":
						case "shower drizzle ":
						case "light intensity shower rain ":
						case "heavy intensity shower rain ":
						case "ragged shower rain ":
									color1 = new Color(0,255,255);
									color2 = new Color(30, 130, 160);
									break;
						case "rain ":
						case "light rain ":
						case "moderate rain ":
						case "heavy intensity rain ":
						case "very heavy rain ":
						case "extreme rain ":
									color1 = new Color(0, 166, 255);
									color2 = new Color(30, 50, 160);
									break;
						case "thunderstorm ":
						case "thunderstorm with light rain ":
						case "thunderstorm with rain ":
						case "thunderstorm with heavy rain ":
						case "light thunderstorm ":
						case "heavy thunderstorm ":
						case "ragged thunderstorm ":
						case "thunderstorm with light drizzle ":
						case "thunderstorm with drizzle ":
						case "thunderstorm with heavy drizzle ":
									color1 = new Color(0, 95, 255);
									color2 = new Color(60, 30, 160);
									break;
						case "snow ":
						case "freezing rain ":
						case "light snow ":
						case "heavy snow ":
						case "sleet ":
						case "shower sleet ":
						case "light rain and snow ":
						case "rain and snow ":
						case "light shower snow ":
						case "shower snow ":
						case "heavy shower snow ":
									color1 = new Color(145, 245, 245);
									color2 = new Color(75, 150, 160);
									break;
						case "mist ":
						case "smoke ":
						case "haze ":
						case "sand, dust whirls ":
						case "fog ":
						case "sand ":
						case "dust ":
						case "volcanic ash ":
						case "squalls ":
						case "tornado ":
									color1 = new Color(200, 210, 210);
									color2 = new Color(85, 110, 100);
									break;
						default:
									color1 = new Color(160, 120, 240);
									color2 = new Color(40, 10, 90);
									break;
					}
				}
		
		/**
		 * This method is used to add Mars weather data to a Panel
		 */
		private void createFormMars() throws IOException {
			
			weatherData tmp = new weatherData();
			try {
				app.grabMars(app.getUnits());
			} catch (IOException e) {
				throw new IOException("error");
			}
			tmp = app.getMars();

			JLabel lblcity = new JLabel("Mars Weather"); //displays location info
			lblcity.setForeground(Color.WHITE);
			lblcity.setFont(new Font("Lucida Console", Font.PLAIN, 40));
			JLabel lbldescrip = new JLabel("Weather Condition: ");
			lbldescrip.setForeground(Color.WHITE);
			lbldescrip.setFont(new Font("Courier New", Font.BOLD, 18));
			JLabel lbldescrip2 = new JLabel("" +tmp.getCondit());
			lbldescrip2.setForeground(Color.WHITE);
			lbldescrip2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblmin = new JLabel("Min Temp: "); //label for min
			lblmin.setForeground(Color.WHITE);
			lblmin.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblmin2 = new JLabel("" + tmp.getMin()); //actual min
			lblmin2.setForeground(Color.WHITE);
			lblmin2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblmax = new JLabel("Max Temp: " ); //label for max
			lblmax.setForeground(Color.WHITE);
			lblmax.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblmax2 = new JLabel("" + tmp.getMax()); //actual max
			lblmax2.setForeground(Color.WHITE);
			lblmax2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblspeed = new JLabel("Wind Speed: "); //label for speed
			lblspeed.setForeground(Color.WHITE);
			lblspeed.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			//considers whether the wind speed is null in the JSON
			JLabel lblspeed2 = new JLabel();
			if(tmp.getSpeed()==99){
				lblspeed2 = new JLabel("Null");
			}
			else{
				lblspeed2 = new JLabel("" + tmp.getSpeed()); 
			}
			lblspeed2.setForeground(Color.WHITE);
			lblspeed2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			//JLabel lblspeed2 = new JLabel("" + tmp.getSpeed()); //actual speed
			JLabel lbldir = new JLabel("Wind Direction: "); //label for dir 
			lbldir.setForeground(Color.WHITE);
			lbldir.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			//considers whether the wind direction is null in the JSON
			JLabel lbldir2 = new JLabel();
			if(tmp.getDir() == 99){ //accounts for erroneous input
				lbldir2 = new JLabel("Null"); //print N/A
			}
			else{
				lbldir2 = new JLabel("" + tmp.getDir()); //actual dir
			}
			lbldir2.setForeground(Color.WHITE);
			lbldir2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblpress = new JLabel("Air Pressure: "); //label for pressure
			lblpress.setForeground(Color.WHITE);
			lblpress.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblpress2 = new JLabel("" + tmp.getPress()); //actual pressure
			lblpress2.setForeground(Color.WHITE);
			lblpress2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblhumid = new JLabel("Humidity: "); //label for humid
			lblhumid.setForeground(Color.WHITE);
			lblhumid.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblhumid2 = new JLabel();
			if(tmp.getHumid() == 99){ //accounts for erroneous input
				lblhumid2 = new JLabel("Null"); //print N/A
			}
			else{
				lblhumid2 = new JLabel("" + tmp.getHumid()); //actual humdid
			}
			lblhumid2.setForeground(Color.WHITE);
			lblhumid2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblrise = new JLabel("Sunrise Time: "); //label for sunrise
			lblrise.setForeground(Color.WHITE);
			lblrise.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblrise2 = new JLabel("" + tmp.getSunrise()); //actual sunrise
			lblrise2.setForeground(Color.WHITE);
			lblrise2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblset = new JLabel("Sunset Time: "); //label for susnet
			lblset.setForeground(Color.WHITE);
			lblset.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));
			JLabel lblset2 = new JLabel("" + tmp.getSunset()); //actual sunset
			lblset2.setForeground(Color.WHITE);
			lblset2.setFont(new Font(lbldescrip.getFont().getFontName(), Font.BOLD, 18));

			//used to set picture
			InputStream img = getClass().getResourceAsStream("/mars_picture.png");
			BufferedImage pic = ImageIO.read(img);
			//pic  = Scalr.resize(pic, 80);
			JLabel lblPic = new JLabel(new ImageIcon(pic)); //holds picture
			//adds control with layout organization
			GroupLayout layout = new GroupLayout(marsPanel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup() //sets horizontal groups
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING) //holds all the identifier labels
							.addComponent(lblcity)
							.addComponent(lblPic)
							.addComponent(lbldescrip)
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
	
						marsPanel.setLayout(layout); //sets the layout		
								
			}
	}