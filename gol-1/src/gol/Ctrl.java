package gol;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

public class Ctrl implements GOLCtrl {

	@SuppressWarnings("unused")
	private TickCtrl onTick;
	@SuppressWarnings("unused")
	private ClickCtrl onClick;
	@SuppressWarnings("unused")
	private SpaceCtrl onSpace;

	
	private Ctrl(){}

	private Ctrl(TickCtrl t, ClickCtrl c, SpaceCtrl s){
		this.onClick = c;
		this.onTick = t;
		this.onSpace = s;
	}

	public static Ctrl buildCtrl(Model m, View v){
		TickCtrl t = TickCtrl.buildOnTick(m,v);
		ClickCtrl cl = ClickCtrl.buildOnClick(m,v);
		SpaceCtrl sc = SpaceCtrl.buildOnSpace(m,v);
		Ctrl c = new Ctrl(t,cl,sc);
		return c;
	}

	private static class TickCtrl implements ActionListener{

		private Model model;
		private View view;

		private TickCtrl(Model m, View v) {
			this.model = m;
			this.view = v;
		}


		public void actionPerformed(ActionEvent actionEvent) {
			model.incrementPop();
			view.update(); 
		}

		public static TickCtrl buildOnTick(Model m, View v) {
			TickCtrl t = new TickCtrl(m,v);
			v.addTickCtrl(t);
			return t;
		}
	}

	
	private static class ClickCtrl extends MouseAdapter {

		private Model model;
		private View  view;

		private ClickCtrl(){}

		private ClickCtrl(Model m, View v){
			this.model = m;
			this.view = v;
		}

		public static ClickCtrl buildOnClick(Model m, View v){
			ClickCtrl c = new ClickCtrl(m,v);
			v.addClickCtrl(c);
			return c;
		}

		@Override
		public void mouseClicked(MouseEvent e){

			if(model.isGoing()){
				return; // do nothing
			}
			else if(SwingUtilities.isLeftMouseButton(e)){
				//reset time
				model.setAge(0);
				int mC = e.getX()/ this.view.getSideLength();
				int mR = e.getY()/ this.view.getSideLength();
				System.out.println(mC + "," + mR);
				model.set1(mC,mR);
				view.update();
		}
	  }
	}

	@SuppressWarnings("serial")
	private static class SpaceCtrl extends AbstractAction{

		private Model model;
		@SuppressWarnings("unused")
		private View view;

		public SpaceCtrl(Model m, View v) {
			this.model = m;
			this.view = v;
		}

		public void actionPerformed(ActionEvent actionEvent) {
			this.model.toggleTime();
		}

		public static SpaceCtrl buildOnSpace(Model m, View v) {
			SpaceCtrl s = new SpaceCtrl(m,v);
			v.addSpaceCtrl(s);
			return s;
		}
	}

}


