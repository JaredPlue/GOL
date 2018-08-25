package tests;

import static org.junit.Assert.*;
import gol.Cell;
import gol.CellModel;

import org.junit.Test;

public class CellModelTests {

	@Test
	public void NaturalSelection() {
		
		/**
		 * Building matrix of 1s and 0s to compare 
		 */
		
		
		Cell[][] actualarr = { 
				//Row1
				{Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)},
				//Row2
				{Cell.buildCell(1), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0)},
				//Row3
				{Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0)},
				//Row4
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0)},
				//Row5
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0)}
				};
		
		CellModel actual = CellModel.buildCellModel(actualarr);
		
		//iterate once
		actual.NaturalSelection();
		
		Cell[][] expectedarr = { 
				//Row1
				{Cell.buildCell(1), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)},
				//Row2
				{Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)},
				//Row3
				{Cell.buildCell(1), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0)},
				//Row4
				{Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0)},
				//Row5
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)}
				};
		
		CellModel expected = CellModel.buildCellModel(expectedarr);
		
		//0 = dead
		//1 = alive
		/** BEFORE			    AFTER
 		 * * * * * * *		* * * * * * *
		 * 1 0 0 0 0 *  --> * 1 1 0 0 0 *
		 * 1 1 0 1 0 *  --> * 1 0 0 0 0 * 
		 * 0 1 1 0 0 *  --> * 1 1 0 1 0 *
		 * 0 0 0 1 0 *  --> * 0 1 0 1 0 *
		 * 0 0 1 0 0 *  --> * 0 0 0 0 0 * 
		 * * * * * * *      * * * * * * *
					   	    * ACTUAL???
						    * * * * * * *	
						    * 1 1 0 0 0 *
						    * 1 0 0 0 0 * 
						    * 1 1 0 1 0 * 
						    * 0 1 0 1 0 *  
						    * 0 0 0 0 0 *  
						    * * * * * * * 
		 */
		
		
		System.out.println("Output:");
		System.out.println(actual);

		System.out.println("Expected output:");
		System.out.println(expected);
		
		assertEquals(actual, expected);
		
		
		
		
		
		Cell[][] ActualBoatArr = { 
				//Row1
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)},
				//Row2
				{Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0)},
				//Row3
				{Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0)},
				//Row4
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0)},
				//Row5
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)}
				};
		
		CellModel ActualBoat = CellModel.buildCellModel(ActualBoatArr);
		ActualBoat.NaturalSelection();
		
		Cell[][] ExpectedBoatArr = { 
				//Row1
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)},
				//Row2
				{Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0)},
				//Row3
				{Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0)},
				//Row4
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(1), Cell.buildCell(0), Cell.buildCell(0)},
				//Row5
				{Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0), Cell.buildCell(0)}
				};
		
		CellModel ExpectedBoat = CellModel.buildCellModel(ExpectedBoatArr);
		
		//BOAT
		/** BEFORE			    AFTER
 		 * * * * * * *		* * * * * * *
		 * x x x x x *  --> * x x x x x *
		 * x o o x x *  --> * x o o x x * 
		 * x o x o x *  --> * x o x o x *
		 * x x o x x *  --> * x x o x x *
		 * x x x x x *  --> * x x x x x * 
		 * * * * * * *      * * * * * * *
		 */

		assertEquals(ActualBoat, ExpectedBoat);
	}

}
