package gol;

import javax.swing.JFrame;

public class Main {

	public static class GOLMain{

	    public static void main(String[] args){

	        Model model = Model.buildRandXYModel(30,40);    
	        View view = View.buildView(model);
	        @SuppressWarnings("unused")
			Ctrl ctrl = Ctrl.buildCtrl(model,view);


	        JFrame f = new JFrame("Game of Life");
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        view.addComponents(f.getContentPane());
	        //f.setResizable(false);
	        f.setLocation(0,0);
	        f.pack();
	        f.setVisible(true);
	        f.getContentPane().repaint();
	        model.toggleTime();
	    }
	}
	
}
