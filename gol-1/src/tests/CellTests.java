package tests;

import static org.junit.Assert.*;

import gol.Cell;

import org.junit.Test;

public class CellTests {

	Cell cell00 = Cell.buildCell(1);
	Cell cell002 = Cell.buildCell(1);
	Cell cell10 = Cell.buildCell(1);
	Cell cell11 = Cell.buildCell(1);
	Cell cell01 = Cell.buildCell(0);

	
	@Test
	public void isAlive(){
		assertEquals(1, cell00.isAlive());
		assertEquals(1, cell10.isAlive());
		assertEquals(0, cell01.isAlive());
		assertEquals(1, cell11.isAlive());
		assertEquals(cell00, cell002);
		
		cell00.setAlive(0); //kill it
		assertEquals(0, cell00.isAlive());
	}
}
