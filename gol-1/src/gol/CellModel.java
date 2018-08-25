package gol;

import java.lang.Math;
import java.util.Arrays;
import java.util.Observable;
import java.util.StringJoiner;
import java.util.Random;

public class CellModel extends Observable{
	
	private Cell[][] pop;
	private final int ROWS;
	private final int COLS;
	
	
	/**
	 * Dflt model c'tor
	 */
	private CellModel(){
		ROWS = 2;
		COLS = 2;
        this.pop = new Cell[ROWS][COLS]; //dflt size of 2x2
    }

	/**
	 * Model c'tor
	 * @param pop 2d array of cells with loc's and alive status
	 */
    private CellModel(Cell[][] pop){
    	ROWS = pop.length;
    	COLS = pop[0].length;
    	this.pop = pop;
    }
    
    /**
     * Dflt model factory
     * @return
     */
    public static CellModel buildCellModel(){
        return new CellModel();
    }

    /**
     * Model factory
     * @param pop 2d array of cells wth loc's and alive status
     * @return CellModel
     */
    public static CellModel buildCellModel(Cell[][] pop){
        return new CellModel(pop);
    }

    //Copy c'tor
    public static CellModel buildCellModel(CellModel oth){
        return new CellModel(oth.pop);
    }

    
    //initialize a CellModel of a certain size
    public static Cell[][] InitPop(int row, int col){

    	Cell[][] pop = new Cell[row][col];
    	
    	//make an array of cells
    	//convert 1d to 2d
    	for(int i = 0; i < row; i++){
    		for(int j = 0; j < col; j++){
    			//pop[i][j] = arr[(j * row) + i]
    			
    			pop[i][j] = Cell.buildCell(0);
    			System.out.println(i + "," + j + "," + pop[i][j].getAlive());
    		}
    	}
    	return pop;
    }
   
    
    
    /**Randomize - randomizes the population
     * @return a new, randomized population
     */
    
    public void Randomize(){
    	//get num rows
    	int rows = this.pop.length;
    	//get num cols
    	int cols = this.pop[0].length;
    	
    	for(int i = 0; i < rows; i++){
    		for(int j = 0; j < cols; j++){
    			Random rand = new Random();
    			int n = rand.nextInt(100) + 1;
    			//40% chance of being spawned alive
    			if(n <= 40){
    				this.pop[i][j].setAlive(0);
    			}
    			else this.pop[i][j].setAlive(1);
    		}
    	}
    	return;
    }
    
    
    /**
     * NaturalSelection - performs a single interation of the cell death/life cycle
     * @return a new cell model, built from the new population
     */
    public CellModel NaturalSelection(){
    	//get num rows
    	int rows = this.pop.length;
    	//get num cols
    	int cols = this.pop[0].length;
    	
    	//make a empty 2darr of the same size
    	Cell[][] tmp = new Cell[rows][cols];
    	for(int i = 0; i < rows; i++){
    		for(int j = 0; j < cols; j++){
    			tmp[i][j] = Cell.buildCell(this.pop[i][j].getAlive());
    			
    		}
    	}
    	
    	//topleft (0,0)
    	int i = 0;
    	int j = 0;
    	
    	int sum = 0;
    	
    	int ur = 0;
    	int lr = 0;
    	int lc = 0;
    	int rc = 0;

    	
    	//for every cell
    	for(i = 0; i < rows; i++){
    		for(j = 0; j < cols; j++){
    			
    	    	ur = Math.max((i-1), 0);
    	    	lr = Math.min((i+1),rows-1);
    	    	lc = Math.max((j-1), 0);
    	    	rc = Math.min((j+1), cols-1);
    	    	sum = 0; 
    	    	
    	    	//for every neighbor of every cell
    	    	for(int r = ur; r <= lr; r++){
    	    		for(int c = lc; c <= rc; c++){
    	    				
    	    			sum += this.pop[r][c].isAlive();
    	    			
    	    		}//close r++
    	    	} //close c++
    	    	
    	    	
    	    	//if your cell was alive, subtract 1
    	    	if(this.pop[i][j].isAlive() == 1){
    	    		sum--;
    	    		
    	    		//if alive, neighborCt > 3 or neighborCt < 2, die
    				if(sum > 3 || sum < 2){
    					tmp[i][j].setAlive(0);
    				}
    				//if alive, neighborCt = 2 or 3, stay alive
    				else tmp[i][j].setAlive(1);
    	    	}		
	        	//Cell is dead
    	    	//born if neighborCt = 3
    	    	else if(this.pop[i][j].isAlive() == 0){
    	    		if(sum == 3){
    	    			tmp[i][j].setAlive(1);
    	    			
    	    		}
    	    		//else stay dead
    	    		else {tmp[i][j].setAlive(0);
    	    		}
    	    	}
    	    	
    		} //close j++
    	} //close i++
    	this.setPop(tmp);
    	return this;
    }//close NaturalSelection

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(pop);
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
		CellModel other = (CellModel) obj;
		if (!Arrays.deepEquals(pop, other.pop))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		
		for(Cell[] row : this.pop){
			sj.add(Arrays.toString(row));
		}
		String result = sj.toString();
		return result;
	}

	/**
	 * @return the pop
	 */
	public Cell[][] getPop() {
		return pop;
	}

	/**
	 * @param pop the pop to set
	 */
	public void setPop(Cell[][] pop) {
		this.pop = pop;
	}

	/**
	 * @return #Cols
	 */
	public int getCOLS() {
		return COLS;
	}

	/**
	 * @return #Rows
	 */
	public int getROWS() {
		return ROWS;
	}

	
	
}//close Class