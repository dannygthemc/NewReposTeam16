package cs2212b.team16;


import javax.swing.SwingUtilities;

/**
 * App.java is the driver for the Weather Application. It creates the window for the app and runs it
 * 
 * @author Daniel Gilbert
 * @author Omar Abdel-Qader
 * @author James Crocker
 * @author Long Le
 * @author Angus Poole
 *
 */

public class App {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow window = new MainWindow();
				window.setVisible(true);
			}
		}); }
	}