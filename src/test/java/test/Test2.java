package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tower.basicTower;
import tower.Tower.TowerState;

public class Test2{
	int []pos= {0,0};
	@Test
	public void test() {
		basicTower a = new basicTower(pos);
		
		assertEquals(a.getCoord()[0],pos[0]);
		assertEquals(a.getCoord()[1],pos[1]);
		
		assertEquals(a.getTowerCost(),60);
		assertEquals(a.getPower(),50);
		assertEquals(a.getRange(),70);
		assertEquals(a.getTowerState(),TowerState.READY);
		
	}
}
