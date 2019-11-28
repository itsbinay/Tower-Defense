<<<<<<< HEAD
//package test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import org.testfx.framework.junit.ApplicationTest;
//
//import com.sun.prism.paint.Color;
//
//import sample.MyController;
//
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import javafx.fxml.FXMLLoader;
//
//
//public class UITest extends ApplicationTest {
//
//	private Scene s;
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
//      Parent root = loader.load();
//      primaryStage.setTitle("Tower Defence");
//      s = new Scene(root, 600, 480);
//      primaryStage.setScene(s);
//      primaryStage.show();
//      MyController appController = (MyController)loader.getController();
//      appController.createArena();   		
//	}
//
//	/*
//	@Test
//	public void testNextFrameButton() {
//		clickOn("#buttonNextFrame");
//		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
//		for (javafx.scene.Node i : b.getChildren()) {
//			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
//				Label h = (Label)i;
//				if (h.getLayoutX() == 0 && h.getLayoutY() == 0)
//					assertEquals(h.getText(), "M");
//			}
//		}
//	}
//	*/
//	@Test
//	public void testPlayButton() {
//		
//		clickOn("#buttonPlay");
//		
//		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
//		for (javafx.scene.Node i : b.getChildren()) {
//			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
//				Label h = (Label)i;
//				if(h.getLayoutX()==20 && h.getLayoutY()==20)
//					assertEquals(h.getText(),"B");
//			}
//		}
//				
//	}
//}
=======
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;

import com.sun.prism.paint.Color;

import sample.MyController;
import tower.Tower;
import sample.Helper;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class UITest extends ApplicationTest {

	private Scene s;

	@Override
	public void start(Stage primaryStage) throws Exception {
	  /*
	   We have to create root in order to allow us to refresh the application after each test has run, 
	   this will ensure that we never have stale data from a previous test causing unexpected errors 
	   in our tests.
	   */
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
      Parent root = loader.load();
      primaryStage.setTitle("Tower Defence");
      s = new Scene(root, 600, 480);
      primaryStage.setScene(s);
      primaryStage.show();
      MyController appController = (MyController)loader.getController();
      appController.createArena();   		
	}

	/*
	@Test
	public void testNextFrameButton() {
		clickOn("#buttonNextFrame");
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : b.getChildren()) {
			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if (h.getLayoutX() == 0 && h.getLayoutY() == 0)
					assertEquals(h.getText(), "M");
			}
		}
	}
	*/
	
	@Before
	public void setUp() {
		MyController.setResourcesForTesting();
	}
	
	@Test
	public void testDragAndDeleteBasicTower() {
		Label b = (Label)s.lookup("#labelBasicTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		assertEquals(drop.getId(), "basicTower");
		clickOn(drop);
		clickOn(600,300);
		assertTrue(MyController.destroyClicked);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
	
	@Test
	public void testDragAndDeleteIceTower() {
		Label b = (Label)s.lookup("#labelIceTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		assertEquals(drop.getId(), "iceTower");
		clickOn(drop);
		clickOn(600,300);
		assertTrue(MyController.destroyClicked);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
	
	@Test
	public void testDragAndDeleteLaserTower() {
		Label b = (Label)s.lookup("#labelLaserTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		assertEquals(drop.getId(), "laserTower");
		clickOn(drop);
		clickOn(600,300);
		assertTrue(MyController.destroyClicked);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
	
	@Test
	public void testDragAndDeleteCatapult() {
		Label b = (Label)s.lookup("#labelCatapult");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		assertEquals(drop.getId(), "catapult");
		clickOn(drop);
		clickOn(600,300);
		assertTrue(MyController.destroyClicked);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}

	@Test
	public void testRangeCircleBasic() {
		Label b = (Label)s.lookup("#labelBasicTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		moveTo(drop);
		assertTrue(MyController.circleShown);		
		clickOn(drop);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}	
	@Test
	public void testRangeCircleIce() {
		Label b = (Label)s.lookup("#labelIceTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		moveTo(drop);
		assertTrue(MyController.circleShown);	
		clickOn(drop);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();	
	}	
	@Test
	public void testRangeCircleLaser() {
		Label b = (Label)s.lookup("#labelLaserTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		moveTo(drop);
		assertTrue(MyController.circleShown);	
		clickOn(drop);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();	
	}	
	@Test
	public void testRangeCircleCatapult() {
		Label b = (Label)s.lookup("#labelCatapult");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		moveTo(drop);
		assertTrue(MyController.circleShown);	
		clickOn(drop);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();	
	}
	
	@Test
	public void testUpgradeBasic() {
		Label b = (Label)s.lookup("#labelBasicTower");
		Label drop = null;
		
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		
		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
		int index = Helper.getTowerIndex(coord, MyController.towersUT);
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		clickOn(drop);
		clickOn(700,300);
		assertEquals(MyController.towersUT.get(index).getPower(), 55);
		assertEquals(MyController.towersUT.get(index).getRange(), 75);
		assertTrue(MyController.upgradeClicked);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
	
	
	
	@Test
	public void testUpgradeIce() {
		Label b = (Label)s.lookup("#labelIceTower");
		Label drop = null;
		
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		
		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
		int index = Helper.getTowerIndex(coord, MyController.towersUT);
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		clickOn(drop);
		clickOn(700,300);
		assertEquals(MyController.towersUT.get(index).getRange(), 110);
		assertTrue(MyController.upgradeClicked);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
	@Test
	public void testUpgradeCatapult() {
		Label b = (Label)s.lookup("#labelCatapult");
		Label drop = null;
		
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
					drop = h;
			}
		}
		
		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
		int index = Helper.getTowerIndex(coord, MyController.towersUT);
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		clickOn(drop);
		clickOn(700,300);
		assertEquals(MyController.towersUT.get(index).getRange(), 160);
		assertTrue(MyController.upgradeClicked);
		clickOn(600,300);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
	
	@Test
	public void testBuildingTowerTwiceOnSameGrid() {
		MyController.resetResourcesForTesting();
		Label b = (Label)s.lookup("#labelCatapult");
		Label drop = null;
		
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green") {
					drop = h;
					break;
				}
			}
		}
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		moveTo(drop);
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		assertTrue(MyController.doubleBuilt);
		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
		int index = Helper.getTowerIndex(coord, MyController.towersUT);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}

	@Test
	public void upgradeNotEnoughResources() {
		MyController.resetResourcesForTesting();
		Label b = (Label)s.lookup("#labelLaserTower");
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green" && h.getLayoutX() == 40 && h.getLayoutY() == 40) {
					drop = h;
				}
				
			}
		}		
		drag(b,MouseButton.PRIMARY).dropTo(drop);
		clickOn(drop);
		clickOn(700,300);
		assertTrue(MyController.notEnoughToUpgrade);
		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
		int index = Helper.getTowerIndex(coord, MyController.towersUT);
		if(!MyController.towersUT.isEmpty()) MyController.towersUT.clear();
	}
}
>>>>>>> 4c359d0eb9b47bf0aaebe5ef64626d132d17fbfa
