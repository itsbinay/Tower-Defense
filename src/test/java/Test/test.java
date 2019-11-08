package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import monster.Monster;

public class test {

	@Test
	public void testMonster() {
		Monster z = new Monster(10,30,true);
	}

	@Test
	public void testGetMovementSpeed() {
		Monster b = new Monster(10,30,true);
		assertEquals(b.getMovementSpeed(),10);
	}

	@Test
	public void testGetHp() {
		
		Monster a = new Monster(10,30,true);
		assertEquals(a.getHp(),30);

	}

	@Test
	public void testGetMonsterStatus() {
		Monster c = new Monster(10,30,true);
		assertEquals(c.getMonsterStatus(),true);

	}
	@Test
	public void testReduceSpeed() {
		Monster c = new Monster(10,30,true);
		c.reduceSpeed();
		c.increaseSpeed();
		assertEquals(c.getMovementSpeed(),10);
		

	}

	

}
