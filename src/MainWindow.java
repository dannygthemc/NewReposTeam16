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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

	public class MainWindow extends JFrame  {
		private weatherApp app = new weatherApp();
		JComboBox<String> locBar = new JComboBox<String>();
		JMenuBar menubar;
		ActionListener Jcombo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event){
				String item = ((JComboBox<String>)event.getSource()).getSelectedItem().toString();
				updateWeatherView(item);
			}
		};

		
		//instantiates an instance of the MainWindow that's been defined
		public MainWindow() {
			this.initUI();
			
		}
		//initializes the User Interface elements we've defined below
		private void initUI () {
			this.setTitle("Weather Application"); 
			this.setSize(960, 540);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setJMenuBar(this.createMenubar()); 
			
			this.setLayout(new BorderLayout());
			
			JTabbedPane tabbedPane = new JTabbedPane();
			 tabbedPane.addTab("Current", null, this.createForm());
			 tabbedPane.addTab("Short Term", null, this.createFormTwo());
			 
			this.add(this.createForm(), BorderLayout.CENTER);
			JLabel test = new JLabel ("Hello");
			this.add(test);
			
			 //tabbedPane.addTab("Long Term", null, this.createMultiForm(app.getLongTerm()),"");
			 
			this.add(tabbedPane, BorderLayout.CENTER);
			
		}
		
		//used to create Panel for the shorTermForecast Tab
		private JPanel createFormTwo(){
			
			JPanel panel = new JPanel();
			
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
			GroupLayout layout = new GroupLayout(panel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup()
						
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(lblcity)
								.addGap(10)
								.addComponent(lbl1)
								.addComponent(lbl2)
								.addComponent(lbl3)
								
						)
						
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
			layout.setVerticalGroup( layout.createSequentialGroup()
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblcity)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblPic[0])
							.addComponent(lblPic[1])
							.addComponent(lblPic[2])
							.addComponent(lblPic[3])
							.addComponent(lblPic[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lbl1)
							.addComponent(time[0])
							.addComponent(time[1])
							.addComponent(time[2])
							.addComponent(time[3])
							.addComponent(time[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lbl2)
							.addComponent(temp[0])
							.addComponent(temp[1])
							.addComponent(temp[2])
							.addComponent(temp[3])
							.addComponent(temp[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lbl3)
							.addComponent(descrip[0])
							.addComponent(descrip[1])
							.addComponent(descrip[2])
							.addComponent(descrip[3])
							.addComponent(descrip[4])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblPic[5])
							.addComponent(lblPic[6])
							.addComponent(lblPic[7])
							.addComponent(lblPic[8])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(time[5])
							.addComponent(time[6])
							.addComponent(time[7])
							.addComponent(time[8])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(temp[5])
							.addComponent(temp[6])
							.addComponent(temp[7])
							.addComponent(temp[8])
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(descrip[5])
							.addComponent(descrip[6])
							.addComponent(descrip[7])
							.addComponent(descrip[8])
							)		
					);
			panel.setLayout(layout);
			return panel;
		}
		
		//defines a Menu bar with a "File" option, which can be called by "alt + f"
		//menu bar contains option for exiting current program
		private JMenuBar createMenubar() {
			//File ->Exit menu item
			menubar = new JMenuBar();
			JMenu mnuFile = new JMenu("File");
			mnuFile.setMnemonic(KeyEvent.VK_F);
			JMenuItem mniFileExit = new JMenuItem("Exit");
			mniFileExit.setMnemonic(KeyEvent.VK_E);
			mniFileExit.setToolTipText("Exit application");
			mniFileExit.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
			System.exit(0); }
			});
			mnuFile.add(mniFileExit);
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
			
			//Refresh button
			JMenu refresh = new JMenu("Refresh");
			refresh.setMnemonic(KeyEvent.VK_E);
			refresh.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent event) {
				 
			 }
			});
			menubar.add(refresh);
			return menubar;
		}
		
		private void populateMyLocationsBox(){
			locBar.removeActionListener(Jcombo);
			//locBar.removeAllItems();
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
			//locBar.revalidate();
			//locBar.setVisible(true);
			locBar.addActionListener(Jcombo);
		}
		
		//Refresh the views when saved city is clicked
		private void updateWeatherView(String cityName){
			if (cityName.equals("--Remove?--")){
				JFrame frame = new JFrame();
				String[] possibilities = new String[app.getMyLocations().length];
				for (int i = 0; i < app.getMyLocations().length; i ++){
					String name =  app.getMyLocations()[i].getName();
					if (!name.equals("Default")){
						possibilities[i] = name;
					}
				}
				String response = (String) JOptionPane.showInputDialog(frame, "Pick a location to remove:", "Remove Location",  
						JOptionPane.QUESTION_MESSAGE, null, possibilities, "Titan");
				if (response != null) app.removeLocation(response);
				//TODO:
				//Update the mylocations view when location is removed
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
		
		//Refresh the views when new city is searched
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
				app.addLocation(searchedLoc);
				populateMyLocationsBox();
				//this.initUI();
		    }
		}
		
		private JPanel createForm() {
			
			weatherData tmp = new weatherData();
			app.grab();
			tmp = app.getCurrent();
			
			JPanel panel = new JPanel();
			JLabel lblcity = new JLabel(tmp.getName() + ", " + tmp.getCount() + ", " + tmp.getLon() + ", " + tmp.getLat() ); //displays location info
			JLabel lbldescrip = new JLabel("Weather Condition: ");
			JLabel lbldescrip2 = new JLabel("" +tmp.getCondit());
			JLabel lbltemp = new JLabel("Temp:  "); //displays temperature
			JLabel lbltemp2 = new JLabel("" + tmp.getTemp());
			JLabel lblmin = new JLabel("Min Temp: "); //displays minTemp
			JLabel lblmin2 = new JLabel("" + tmp.getMin());
			JLabel lblmax = new JLabel("Max Temp: " );
			JLabel lblmax2 = new JLabel("" + tmp.getMax());
			JLabel lblspeed = new JLabel("Wind Speed: ");
			JLabel lblspeed2 = new JLabel("" + tmp.getSpeed());
			JLabel lbldir = new JLabel("Wind Direction: ");
			JLabel lbldir2 = new JLabel("" + tmp.getDir());
			JLabel lblpress = new JLabel("Air Pressure: ");
			JLabel lblpress2 = new JLabel("" + tmp.getPress());
			JLabel lblhumid = new JLabel("Humidity: ");
			JLabel lblhumid2 = new JLabel("" + tmp.getHumid());
			JLabel lblrise = new JLabel("Sunrise Time: ");
			JLabel lblrise2 = new JLabel("" + tmp.getSunrise());
			JLabel lblset = new JLabel("Sunset Time: ");
			JLabel lblset2 = new JLabel("" + tmp.getSunset());
			
			
			BufferedImage pic = tmp.getIcon();
			pic  = Scalr.resize(pic, 80);
			JLabel lblPic = new JLabel(new ImageIcon(pic));
			
			
			//adds control with layout organization
			GroupLayout layout = new GroupLayout(panel);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			layout.setHorizontalGroup( layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							
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
			layout.setVerticalGroup( layout.createSequentialGroup()
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblcity)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblPic)
							)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
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
							// Replace all the panel.add(...) statements with the following statement
							panel.setLayout(layout);
							return panel; 			
								
			}
	}