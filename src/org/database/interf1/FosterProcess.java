package org.database.interf1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.database.actors.Animal;
import org.database.actors.Shelter;

public class FosterProcess extends JFrame {
	private JPanel panel;             // A panel container
	private JLabel animalDescription;      // A message to display
	private JLabel appointmentMessage;
	private JButton proceedButton;       // Move to adoption page
	private final int WINDOW_WIDTH = 300;  // Window width
	private final int WINDOW_HEIGHT = 200; // Window height
	private Animal currentanimal;
	private String personname;
	private JLabel createappointment;
	private JTextField numofappointments;

	
	public FosterProcess() {
		
		// Call the JFrame constructor.
	      super("Foster and Appointment Process");

	      // Set the size of the window.
	      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	      // Specify what happens when the close
	      // button is clicked.
	      
	      setLocationRelativeTo(null);

	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		}
	
	public void setcurrentAnimal(Animal an) {
		currentanimal = an;
	}
	
	public void setpersonName(String name) {
		personname = name;
	}
	
	public void displayFoster() {
		// Build the panel and add it to the frame.
	    buildPanel();

	    // Add the panel to the frame's content pane.
	    add(panel);
	    
	    setVisible(true);
	}
	
	private void buildPanel() {
		
		// Create the label, text field, and button components.
			String description = currentanimal.displayDescription();
			animalDescription = new JLabel(description);
			appointmentMessage = new JLabel("Please look at the animal's description and determine if you need to make an "
					+ "appointment to meet this animal.");
			createappointment = new JLabel("How many appointments do you make?");
			numofappointments = new JTextField(9);
			proceedButton = new JButton("Proceed");
			
			proceedButton.addActionListener(new ProceedButtonListener());

	      // Add an action listener to the button.	      

	      // Create a panel to hold the components.
	      panel = new JPanel();
	      
	      // Add the label, text field, and button to the panel.
	      panel.add(animalDescription);
	      panel.add(appointmentMessage);
	      panel.add(createappointment);
	      panel.add(numofappointments);
	      panel.add(proceedButton);
	      
	}
	
	public class ProceedButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  	String a;
	    	  	
	    	  	a = numofappointments.getText();
	    	  	
	    	  	if(a != null && !(a.equals(""))) {
	    	  		int numtimes;
	    	  		
	    	  		numtimes = Integer.parseInt(a);
	    	  		
	    	  		if(numtimes == 0) {
	    	  			Shelter s;
	    	  			s = currentanimal.getShelter();
	    	  			
	    	  			s.moveFosteredAnimal(currentanimal, personname);
	    	  			
	    	  			//DecimalFormat df2 = new DecimalFormat("#.00");
	    	  			
	    	  			String message = "You have fostered " + currentanimal.getName() + "! Your new temporary family member will be waiting for you to pick "
	    	  					+ "them up at the shelter.\n" + "The animal's ID number is " + currentanimal.getID()
	    	  					+ ". Please write it down because when you return " + currentanimal.getName() +
	    	  							 " you will need to know this number.";
	    	  			JOptionPane.showMessageDialog(null, message, "Foster Successful", JOptionPane.INFORMATION_MESSAGE);
	    	  		}
	    	  		else if(numtimes < 0) {
	    	  			String error = "You can't make a negative number of appointments. Please enter a valid number.";
		    	  		JOptionPane.showMessageDialog(null, error, "Blank number of appointments", JOptionPane.ERROR_MESSAGE);
	    	  		}
	    	  		else {
	    	  			FosterAppointments aa = new FosterAppointments();
	    				aa.setcurrentAnimal(currentanimal);
	    				aa.setpersonName(personname);
	    				aa.setnumAppointments(numtimes);
	    				aa.makeAppointments();
	    	  		}
	    	  	}
	    	  	else {
	    	  		String error = "The number of appointments you entered is blank. Please enter a valid number";
	    	  		JOptionPane.showMessageDialog(null, error, "Blank number of appointments", JOptionPane.ERROR_MESSAGE);
	    	  	}
	      }
	   }

}

