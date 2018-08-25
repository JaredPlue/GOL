package gol;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Action;

public interface GOLView {

	
    /**
     * Add canvas to contentpane
     */
    void addComponents(Container contentPane);

    /**
     * Setup UI controllers. Timer, MouseCLicks
     */
    void addTickCtrl(ActionListener t); //iterate
    void addClickCtrl(MouseListener c); //spawn
    void addSpaceCtrl(Action t); //pause

    /**
     * Get size of cells in order to draw appropriately
     */
    int getSideLength();


}
