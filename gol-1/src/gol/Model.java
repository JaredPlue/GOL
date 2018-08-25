/**
 * 
 */
package gol;

import java.util.Observable;
import java.util.Observer;

/**
 * @author jplue
 *
 */
public class Model implements GOLModel {

    private TimeModel timemodel;
	private CellModel pop;
	
    private Model(){
        this.pop = CellModel.buildCellModel();
        this.timemodel = new TimeModel();
    }
    
    private Model(CellModel pop, TimeModel timemodel){
        this.pop = pop;
        this.timemodel = timemodel;
    }

    private Model(CellModel pop){
    	this.pop=pop;
    	this.timemodel = new TimeModel();
    }
    
    public static Model buildModel() {
        return new Model();
    }
    
    public Model buildModel(CellModel pop, TimeModel time) {
        return new Model(CellModel.buildCellModel(pop),
                                        new TimeModel(time));
    }

    public static Model build2dModel(Cell[][] arr){
    	return new Model(CellModel.buildCellModel(arr));
    }
    
    public static Model buildRandXYModel(int x, int y){
    	Cell[][] pop = CellModel.InitPop(x,y);
    	Model m = Model.build2dModel(pop);
    	m.setRand();
    	return m;
    }
    
    public Model set1(int x, int y){
    	int status = this.pop.getPop()[y][x].getAlive();
    	System.out.println(status);
    	
    	if(status == 0){
    		status = 1;
    		this.pop.getPop()[y][x].setAlive(status);
    		return this;
    	}
    	else status = 0;
    	
    	this.pop.getPop()[y][x].setAlive(status);
    	return this;
    }
   

    /**
     * Set random living/dead cells (40%) rate
     */
    
    public void setRand(){
    	this.pop.Randomize();
    	this.timemodel.setAge(0);
    }
    
    /**
     * Returns the number of rows in the 2darr
     */
	public int numRows() {
		return this.pop.getROWS();
	}

	/**
	 * Returns the number of cols in the 2darr
	 */
	public int numCols() {
		return this.pop.getCOLS();
	}

	/**
	 * GetAge of timemodel
	 */
	public int getAge(){
		return timemodel.getAge();
	}
	
	/**
	 * SetAge of timemodel
	 */
	
	public void setAge(int n){
		timemodel.setAge(n);
	}
	
	/**
	 * Performs the natural selection method
	 */
	public void incrementPop() {
		//this.timemodel.ageinc(); why does this break it
		this.timemodel.age++; //why does this work
		this.pop.NaturalSelection();
		System.out.println(this.timemodel.age);
	}

	/**
	 * Increases the age by 1
	 */
	public void incrementAge() { //why does this not work
		this.timemodel.ageinc();
	}

	/**
	 * Stops/Begins time
	 * If changes are made, resets age
	 */
	public void toggleTime() {
		this.timemodel.toggle();
	}

	/**
	 * returns true if time is going, else false
	 */
	public boolean isGoing() {
         return this.timemodel.isGoing();
	}

	/**
	 * Sets an observer of population
	 */
	public void setPopWatcher(Observer o) {
		this.pop.addObserver(o);
	}

	/**
	 * Sets an observer of age
	 */
	public void setAgeWatcher(Observer o) {
		this.timemodel.addObserver(o);	
	}

	/**
	 * Sets an observer of time
	 */
    public void setTimeWatcher(Observer o) {
        this.timemodel.addObserver(o);
    }

    public Cell[][] get2dpop(){
    	return this.pop.getPop();
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pop == null) ? 0 : pop.hashCode());
		result = prime * result
				+ ((timemodel == null) ? 0 : timemodel.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (pop == null) {
			if (other.pop != null)
				return false;
		} else if (!pop.equals(other.pop))
			return false;
		if (timemodel == null) {
			if (other.timemodel != null)
				return false;
		} else if (!timemodel.equals(other.timemodel))
			return false;
		return true;
	}


//////////TIME MODEL/////////////
public class TimeModel extends Observable {

    private boolean isGoing;
    private int age;
    
    /**
     * Dflt c'tor
     */
    public TimeModel(){
        this.isGoing = false;
        this.age = 0;
    }

    /**
     * TimeModel c'tor
     * @param isGoing true if time is going
     */
    private TimeModel(boolean isGoing, int age){
        this.isGoing = isGoing;
        this.setAge(age);
    }

    /**
     * TimeModel c'tor
     * @param isGoing true if time is going
     */
    private TimeModel(TimeModel oth){
        this.isGoing = oth.isGoing;
        this.setAge(oth.age);
    }
    
    /**
     * Dflt TimeModel Factory
     * @return dflt timemodel
     */
    public TimeModel buildTimeModel(){
        return new TimeModel();
    }

    public TimeModel buildTimeModel(boolean isGoing, int age){
        return new TimeModel(isGoing, age);
    }
   
    //Copy c'tor
    public TimeModel buildTimeModel(TimeModel oth){
        return new TimeModel(oth.isGoing, oth.age);
    }
    

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeModel other = (TimeModel) obj;
		if (isGoing != other.isGoing)
			return false;
		return true;
	}

    /**
     * toggle
     * Halt or begin time
     * Notify observers of this change
     */
    public void toggle(){
    	System.out.println("Time toggled");
        this.isGoing = !this.isGoing;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * IsGoing
     * @return true if time is going
     */
    public boolean isGoing(){
        return this.isGoing;
    }
    
    public void ageinc(){
    	while(this.isGoing){
    		age++;
    		this.setChanged();
    		this.notifyObservers();
	        
    	}
    }

    /**
     * 
     * @return age of model
     */
	public int getAge() {
		return age;
	}

    /**
     * Changes age to age
     * @return none
     */
	public void setAge(int age) {
		this.age = age;
	}
    
    
	}


}