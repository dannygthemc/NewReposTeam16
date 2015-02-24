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

	public class MainWindow extends JFrame  {
		
		private weatherData obj = new weatherData();
		private weatherApp app = new weatherApp();
		
		
		//instantiates an instance of the MainWindow that's been defined
		public MainWindow() {
			this.initUI();
			
		}
		//initializes the User Interface elements we've defined below
		private void initUI () {
			this.setTitle("Weather Application"); 
			this.setSize(300, 500);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setJMenuBar(this.createMenubar()); 
			//this.add(this.createForm()); 
			
			this.setLayout(new BorderLayout());
			
			this.add(this.createForm(), BorderLayout.CENTER);
			
		}
		
		//defines a Menu bar with a "File" option, which can be called by "alt + f"
		//menu bar contains option for exiting current program
		private JMenuBar createMenubar() {
			JMenuBar menubar = new JMenuBar();
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
			return menubar;
		}
		
		private JPanel createForm() {
			
			weatherData tmp = new weatherData();
			app.grab();
			tmp = app.getCurrent();
			
			JPanel panel = new JPanel();
			JLabel lblcity = new JLabel(tmp.getName() + ", " + tmp.getCount()  ); //displays temperature
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