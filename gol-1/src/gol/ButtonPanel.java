package gol;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

//////////ButtonPanel CLASS/////////////

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements Observer, ActionListener{

	//Variables
	private JButton pauseButton;
	@SuppressWarnings("unused")
	private JButton randomButton;
	private JLabel ageText;
	private Model model;

	//C'tor
	private ButtonPanel(Model model, JButton pauseButton, JButton randomButton, JLabel ageText){
		this.model = model;
		this.pauseButton = pauseButton;
		this.randomButton = randomButton;
		pauseButton.addActionListener(this);
		randomButton.addActionListener(this);
		this.setAgeText(ageText);
	}

	/**
	 * Creates the layout
	 * Initializes fields
	 */
	public static ButtonPanel buildBP(Model model) {
		JLabel ageText = new JLabel();
		JButton pauseButton = new JButton();
		JButton randomButton = new JButton();
		
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ageText.setText("0");
		pauseButton.setText("Toggle time");
		randomButton.setText("Randomize");
		
		
		
		ButtonPanel bpanel = new ButtonPanel(model, pauseButton, randomButton, ageText);
		bpanel.setLayout(new GridLayout(3,1));
		bpanel.add(ageText);
		bpanel.add(pauseButton);
		bpanel.add(randomButton);
		
		model.setTimeWatcher(bpanel);
		
		return bpanel;
	}

	
	public void update(Observable arg0, Object arg1) {
		if(this.model.isGoing()){
			this.pauseButton.setText("Stop");
		}
		else this.pauseButton.setText("Go");
		
	}

	public JLabel getAgeText() {
		return ageText;
	}

	public void setAgeText(JLabel ageText) {
		this.ageText = ageText;
	}

	public void update() {
		getAgeText().setText(Integer.toString(model.getAge()));
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this.pauseButton){		
			if(this.model.isGoing()){
				this.pauseButton.setText("Stop");
			}
			else this.pauseButton.setText("Go");
			model.toggleTime();
		}
		else {
			  model.setRand();
		}
			 
	}
}