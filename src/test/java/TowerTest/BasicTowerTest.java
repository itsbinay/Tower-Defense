package TowerTest;

import static org.junit.Assert.*;

import org.junit.Test;
import tower.basicTower;
import tower.Tower.TowerState;
import tower.Tower;
import tower.Tower.TowerState;
import tower.Catapult;
import tower.IceTower;
import tower.laserTower;

public class BasicTowerTest {
	
	//Basic Functional Test
	@Test
	public void CoordTest() {
		int[] coord = {0,0};
		Tower testTower = new basicTower(coord);
		Tower testTower2 = new IceTower(coord);
		Tower testTower3 = new Catapult(coord);
		Tower testTower4 = new laserTower(coord);
		
		int[] pos = testTower.getCoord();
		int[] pos2 = testTower2.getCoord();
		int[] pos3 = testTower3.getCoord();
		int[] pos4 = testTower4.getCoord();
		
		assertArrayEquals(coord,pos);
		assertArrayEquals(coord,pos2);
		assertArrayEquals(coord,pos3);
		assertArrayEquals(coord,pos4);
		
		assertEquals("basicTower",testTower.getTowerType());
		assertEquals("iceTower",testTower2.getTowerType());
		assertEquals("catapult",testTower3.getTowerType());
		assertEquals("laserTower",testTower4.getTowerType());
		int[] tower1 = {testTower.getPower()+5,testTower.getRange()+5};
		int[] tower2 = {((IceTower)testTower2).getFreezeTimer()+1,testTower2.getRange()+10};
		int[] tower3 = {testTower3.getMaxCDTimer()-1,testTower3.getRange()+10};
		int[] tower4 = {testTower4.getPower()+30,testTower4.getRange()+50};
		
		testTower.upgradeTower(true);
		testTower2.upgradeTower(true);
		testTower3.upgradeTower(true);
		testTower4.upgradeTower(true);
		
		assertEquals(tower1[0],testTower.getPower());
		assertEquals(tower1[1],testTower.getRange());
		assertEquals(tower2[0],((IceTower)testTower2).getFreezeTimer());
		assertEquals(tower2[1],testTower2.getRange());
		assertEquals(tower3[0],testTower3.getMaxCDTimer());
		assertEquals(tower3[1],testTower3.getRange());
		assertEquals(tower4[0],testTower4.getPower());
		assertEquals(tower4[1],testTower4.getRange());
		
		testTower.upgradeTower(false);
		testTower2.upgradeTower(false);
		testTower3.upgradeTower(false);
		testTower4.upgradeTower(false);
		
		
		assertEquals(tower1[0],testTower.getPower());
		assertEquals(tower1[1],testTower.getRange());
		
		assertEquals(tower2[0],((IceTower)testTower2).getFreezeTimer());
		assertEquals(tower2[1],testTower2.getRange());
		
		assertEquals(tower3[0],testTower3.getMaxCDTimer());
		assertEquals(tower3[1],testTower3.getRange());
		
		assertEquals(tower4[0],testTower4.getPower());
		assertEquals(tower4[1],testTower4.getRange());
	}
	

	@Test
	public void attackTest() {
		int[] coord = {0,0};
		Tower testTower = new basicTower(coord);
		Tower testTower2 = new IceTower(coord);
		Tower testTower3 = new Catapult(coord);
		Tower testTower4 = new laserTower(coord);
		
		int hp=100,hp2=100,hp3=100,hp4=100;
		testTower.setTowerState(TowerState.READY);
		testTower2.setTowerState(TowerState.READY);
		testTower3.setTowerState(TowerState.READY);
		testTower4.setTowerState(TowerState.READY);
		
		int newHP = testTower.attack(hp);
		int newHP2 = testTower2.attack(hp2);
		int newHP3 = testTower3.attack(hp3);
		int newHP4 = testTower4.attack(hp4);
		
		hp -= testTower.getPower();
		hp2 -= testTower2.getPower();
		hp3 -= testTower3.getPower();
		hp4 -= testTower4.getPower();
		
		assertEquals(hp,newHP);
		assertEquals(hp2,newHP2);
		assertEquals(hp3,newHP3);
		assertEquals(hp4,newHP4);
		
		assertEquals(testTower.getTowerState(),TowerState.ATTACK);
		assertEquals(testTower2.getTowerState(),TowerState.ATTACK);
		assertEquals(testTower3.getTowerState(),TowerState.ATTACK);
		assertEquals(testTower4.getTowerState(),TowerState.ATTACK);
		
		assertEquals(testTower.getStateStr(),"Attack");
		
		testTower.updateTowerState();
		testTower2.updateTowerState();
		testTower3.updateTowerState();
		testTower4.updateTowerState();
		
		assertEquals(testTower.getTowerState(),TowerState.READY);
		assertEquals(testTower2.getTowerState(),TowerState.COOLDOWN);
		assertEquals(testTower3.getTowerState(),TowerState.COOLDOWN);
		assertEquals(testTower4.getTowerState(),TowerState.COOLDOWN);
		
		assertEquals(testTower.getStateStr(),"Ready");
		assertEquals(testTower2.getStateStr(),"Cooldown");
		
		newHP = testTower.attack(hp);
		newHP2 = testTower2.attack(hp2);
		newHP3 = testTower3.attack(hp3);
		newHP4 = testTower4.attack(hp4);
		
		hp -= testTower.getPower();
		
		assertEquals(hp,newHP);
		assertEquals(hp2,newHP2);
		assertEquals(hp3,newHP3);
		assertEquals(hp4,newHP4);
		
		while(testTower3.getTowerState()!=TowerState.READY){
			testTower3.updateTowerState();
		}
		while(testTower4.getTowerState()!=TowerState.READY) {
			testTower4.updateTowerState();
		}
		testTower2.updateTowerState();
		assertEquals(testTower2.getCooldown(),2);
		testTower2.updateTowerState();
		assertEquals(testTower2.getCooldown(),1);
		assertEquals(testTower2.getTowerState(),TowerState.READY);
		
		assertEquals(testTower.getMinRange(),0);
		assertEquals(testTower.getAttackCost(),0);
		
	}
	
	@Test
	public void towerGettersTest() {
		int[] coord = {0,0};
		laserTower tower1 = new laserTower(coord);
		Catapult tower2 = new Catapult(coord);
		
		//Invalid data
		int[] invalid2 = {150,100};
		int[] invalid3 = {800,900};
		
		//Valid1
		int[] valid1 = {50,0};
		int[] valid2 = {0,50};
		int[] valid3 = {150,0};
		int[] valid4 = {0,150};
		assertFalse(tower2.isInRange(coord));	
		assertFalse(tower2.isInRange(invalid2));
		assertFalse(tower2.isInRange(invalid3));
		
		assertTrue(tower2.isInRange(valid1));
		assertTrue(tower2.isInRange(valid2));
		assertTrue(tower2.isInRange(valid3));
		assertTrue(tower2.isInRange(valid4));
		
		assertEquals(tower2.getMinRange(),50);
		assertEquals(tower1.getAttackCost(),20);
		assertEquals(tower1.getRangeIncrement(),50);
		
		assertEquals(tower1.getTowerType(),"laserTower");
		assertEquals(tower1.getUpgradeCost(),120);
		
		//Invalid data - laserTower
		int[] invalid4 = {999,9999};
		int[] invalid5 = {81,0};
		
		//Valid data - laserTower
		int[] valid5 = {33,33};
		int[] valid6 = {50,23};
		int[] valid7 = {35,33};
		assertFalse(tower1.isInRange(invalid4));
		assertFalse(tower1.isInRange(invalid5));
		
		assertTrue(tower1.isInRange(coord));
		assertTrue(tower1.isInRange(valid5));
		assertTrue(tower1.isInRange(valid6));
		assertTrue(tower1.isInRange(valid7));
		
	}
	

}
