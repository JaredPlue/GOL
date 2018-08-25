package gol;

import java.util.Observable;

public class Cell extends Observable {

    private int alive; //0,1
    
    /**
     * Dflt C'tor, not used
     */
    private Cell() {
        this.alive = 0;
    }
    
    /**
     * 
     * @param loc - col,row of cell
     * @param alive - 0 if dead 1 if alive
     */
    private Cell(int alive) {
        this.alive = alive;
    }

    /**
     * Factory
     * @return Dflt cell - not used
     */
    public static Cell buildCell(){
        return new Cell();
    }
    
    /**
     * Factory for Cell
     * @param loc - col,row of cell
     * @param alive - 0 if dead 1 if alive
     * @return a Cell at loc with status alive
     */
    public static Cell buildCell(int alive){
        return new Cell(alive);
    }

    /**
     * @return 1 if alive, 0 if dead
     */
    public int isAlive(){
    	return this.alive;
    }

	/**
	 * @return the alive
	 */
	public int getAlive() {
		return alive;
	}

	/**
	 * @param alive the alive (0 or 1) to set
	 */
	public void setAlive(int alive) {
		this.alive = alive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alive;
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
		Cell other = (Cell) obj;
		if (alive != other.alive)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cell [alive=" + alive + "]";
	}
	
}

