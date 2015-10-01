import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PEWordScannerGUI {
	//----- SCANNER ELEMENTS -----
	static PEWordScanner scanner;//Creates the scanner.
	
	//----- GUI ELEMENTS -----
	final static JFrame frame = new JFrame();//Creates a new frame
	final JScrollPane scrollPane = new JScrollPane();//Creates a scroll pane
	final static JPanel listPanel = new JPanel();//Creates a panel for the list/scroll
	final JTextArea textArea = new JTextArea(30, 5);
	
	// -: BUTTONS :-
	final static JButton btnStart = new JButton("START");
	final static JButton btnPause = new JButton("PAUSE");
	final static JButton btnStop = new JButton("STOP");
	final static JButton btnSave = new JButton("SAVE");
	
	//----- END GUI ELEMENTS ------

	public static void main(String[] args) {
		
		PEWordScannerGUI.frame.setVisible(true);//sets the frame visible in the window.
		initializeGUI();//Initializes the GUI.
		

	}
	
	
	//GUI
	public static void initializeGUI() {
		//
		// ---: FRAME SETTINGS :---
		//
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set the program to terminate on pressing the "x"
		frame.setTitle("Periodic Word Scanner");//Set the title of the window
		frame.setBounds(200, 200, 500, 500);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);//Disable the frame from being resized.
		
		//
		// ---: PANEL SETTINGS :---
		//
		
		// - LIST/SCROLL PANEL
		listPanel.setBounds(10, 10, 200, 300);
		frame.getContentPane().add(listPanel);
		
		//
		// ---: SCROLLPANE SETTINGS :---
		//

		
		//
		// ---: BUTTONS :---
		//
		
		// - START BUTTON
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//Action to take when Start is pressed
				if(scanner == null)
				{
					scanner = new PEWordScanner();
					scanner.operate.start();
				}
				else if(!scanner.isFinished())
				{
					scanner.operate.notify();
				}
				
				btnStart.setEnabled(false);//Disables the start button because the scanning is already running.
				btnPause.setEnabled(true);//Enables the pause button because the program is running.
				btnStop.setEnabled(true);//Enables the stop button because the program is running.
				
				if(btnSave.isEnabled())//If the save button has not been enabled yet...
				{
					btnSave.setEnabled(false);//Disable the start button, we're starting the program again.
				}
				
			}
		});
		btnStart.setBounds(10, 425, 100, 30);
		frame.getContentPane().add(btnStart);
		
		// - PAUSE BUTTON
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//Action to take when Pause is pressed
				//REQUIRED: NEED TO PAUSE THE OPERATION HERE!
				btnPause.setEnabled(false);//Disables the pause button because the program will be paused..
				btnStart.setEnabled(true);//Re-enable the start button because the program should now be paused.
				
				if(!scanner.isFinished())
				{
					try {
						scanner.operate.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(!btnSave.isEnabled())//If the save button has not been enabled yet...
				{
					btnSave.setEnabled(true);//Enable the save button, because there is something to potentially save.
				}
				
			}
		});
		btnPause.setBounds(120, 425, 100, 30);
		frame.getContentPane().add(btnPause);
		btnPause.setEnabled(false);//Start the pause button as disabled, nothing is running.
		
		// - STOP BUTTON
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Action to take when Stop is pressed
				//REQUIRED: NEED TO PAUSE THE OPERATION HERE!
				btnStop.setEnabled(false);//Disables the stop button because the program is stopped.
				btnPause.setEnabled(false);//Disables the pause button because the program is stopped.
				btnStart.setEnabled(true);//Re-enable the start button because the program should now be paused.
				
				if(!scanner.isFinished())
				{
					try {
						scanner.operate.wait();
						scanner = null;
						System.gc();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(!btnSave.isEnabled())//If the save button has not been enabled yet...
				{
					btnSave.setEnabled(true);//Enable the save button, because there will be something to save.
				}
					
			}
		});
		btnStop.setBounds(230, 425, 100, 30);
		frame.getContentPane().add(btnStop);
		btnStop.setEnabled(false);//Start the stop button as disabled, nothing is running.
		
		// - SAVE BUTTON
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Action to take when Stop is pressed
				//REQUIRED: NEED TO PAUSE THE OPERATION HERE!
				btnStop.setEnabled(false);//Disables the stop button because the program is stopped.
				btnPause.setEnabled(false);//Disables the pause button because the program is stopped.
				btnStart.setEnabled(true);//Re-enable the start button because the program should now be paused.
					
			}
		});
		btnSave.setBounds(340, 425, 100, 30);
		frame.getContentPane().add(btnSave);
		btnSave.setEnabled(false);//Start the save button as disabled, there's nothing to save yet.
	}

}
