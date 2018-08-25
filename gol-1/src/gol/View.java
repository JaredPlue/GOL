/**
 * 
 */
package gol;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * @author jplue
 *
 */
public class View implements GOLView{

	private Canvas canvas;
    private TimeView ticker;
	private ButtonPanel bp;
	
    //Dflt C'tor
    private View(){}

    //C'tor
    private View(Canvas c,TimeView t, ButtonPanel bp){
        this.canvas = c;
        this.ticker = t;
        this.bp = bp;
    }

    //Factory C'tor
    public static View buildView(Model model) {
        Canvas canvas = Canvas.buildCanvas(model);
        TimeView tick = TimeView.buildTimeView(model);
        ButtonPanel bp = ButtonPanel.buildBP(model);
        
        return new View(canvas,tick, bp);
    }
    
	/* (non-Javadoc)
	 * @see gol.GOLView#addCanvasToContentPane(java.awt.Container)
	 */
	public void addComponents(Container contentPane) {
		JPanel panel = new JPanel();
		panel.add(canvas);
		panel.add(bp);
		contentPane.add(panel);
		//contentPane.add(canvas,0);
		//contentPane.add(bp,1);
	}

	/* (non-Javadoc)
	 * @see gol.GOLView#addTickCtrl(java.awt.event.ActionListener)
	 */
	public void addTickCtrl(ActionListener t) {
		 this.ticker.addOnTick(t);
	}

	/* (non-Javadoc)
	 * @see gol.GOLView#addClickCtrl(java.awt.event.MouseListener)
	 */
	public void addClickCtrl(MouseListener c) {
		this.canvas.addMouseListener(c);
	}
	
	

	/* (non-Javadoc)
	 * @see gol.GOLView#getSideLength()
	 */
	public int getSideLength() {
		return Canvas.cellH;
	}

	public void update(){
		
		this.canvas.repaint();
		//this.bp.repaint();
		this.bp.update();
	}
	
	
	/////////CANVAS CLASS//////////

	/**
     * Panel to draw cells on
     */
    @SuppressWarnings("serial")
	private static class Canvas extends JPanel implements Observer{

        private Model model;
       
        private int width;
        private int height;
        private static final int cellW = 25;
        private static final int cellH = 25;

        //dflt Canvas C'tor
        private Canvas(){
            this.model = null;
            this.width = 100;
            this.height = 100;
        }

        //C'tor
        private Canvas(Model model, int width, int height){
            this.model = model;
            this.width = width;
            this.height = height;
        }

        //Factory
        public static Canvas buildCanvas(Model m) {
            int cols = m.numCols()*Canvas.cellW;
            int rows = m.numRows()*Canvas.cellH;
            Canvas c = new Canvas(m,cols,rows);
            
            c.repaint(); //draw model
            
            return c;
        }

        
        @Override
        //Paint model
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            graphics.setColor(Color.DARK_GRAY);
            
            for(int i = 0; i < model.numRows(); i++){
        		for(int j = 0; j < model.numCols(); j++){
        			
        			if(model.get2dpop()[i][j].isAlive() == 1){
        				graphics.fillRect(j * Canvas.cellW, i * Canvas.cellH, Canvas.cellW, Canvas.cellH);
        			}
        			else graphics.drawRect(j * Canvas.cellW, i * Canvas.cellH, Canvas.cellW, Canvas.cellH);
        		}
            }
        }
        

		public Dimension getPreferredSize() {
            return new Dimension(this.width,this.height);
        }

		
        public void update(Observable observable, Object o) {
            this.repaint();
        }

    }


    
    //////////TIME VIEW CLASS//////////
    
    private static class TimeView implements Observer{

        private Timer timer;
        private Model model;

        public TimeView(Timer ticker, Model model) {
            this.timer=ticker;
            this.model=model;
        }

        public static TimeView buildTimeView(Model model) {
            Timer ticker = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                }
            });
            
            TimeView t = new TimeView(ticker,model);
            model.setTimeWatcher(t);
            return t;
        }

        
        public void update(Observable observable, Object o) {

            if(this.model.isGoing() != this.timer.isRunning()){
                if(this.model.isGoing() ){
                    this.timer.start();
                }
                
                else{
                    this.timer.stop();
                }
            }
        }


        public void addOnTick(ActionListener t) {
            this.timer.addActionListener(t);
        }
    }

	public void addSpaceCtrl(Action t) {
		this.canvas.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"pressed");
        this.canvas.getActionMap().put("pressed",t);
	}
}
