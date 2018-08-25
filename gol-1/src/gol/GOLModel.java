package gol;

import java.util.Observer;

public interface GOLModel {

    /**
     * Size of field
     */
    public int numRows();
    public int numCols();
    
    
    /**
     * Population management
     */
    public void incrementPop();
    public void incrementAge();

    /**
     * Manage the time
     */
    public void toggleTime();
    public boolean isGoing();


    /**
     * Setup Observers isGoing, pop, age
     */
    public void setPopWatcher(Observer o);
    public void setAgeWatcher(Observer o);
	public void setTimeWatcher(Observer o);
	
	
}
