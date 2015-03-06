package cs2212b.team16;
import javax.swing.SwingUtilities;

/**
 * App.java creates the window for the app and runs it
 * @author Daniel Gilbert
 * @author Omar Abdel-Qader
 * @author James Crocker
 * @author Long Le
 * @author Angus Poole
 * @author Nicholas Teixeira
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