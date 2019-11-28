//package test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import sample.Helper;
//import sample.MyController;
//
//public class AlertsTest extends ApplicationTest {
//
//	private Scene s;
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//	  /*
//	   We have to create root in order to allow us to refresh the application after each test has run, 
//	   this will ensure that we never have stale data from a previous test causing unexpected errors 
//	   in our tests.
//	   */
//	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
//      Parent root = loader.load();
//      primaryStage.setTitle("Tower Defence");
//      s = new Scene(root, 600, 480);
//      primaryStage.setScene(s);
//      primaryStage.show();
//      MyController appController = (MyController)loader.getController();
//      appController.createArena();   		
//	}
//	
//	@Test
//	public void testBuildingTowerTwiceOnSameGrid() {
//		MyController.resetResourcesForTesting();
//		Label b = (Label)s.lookup("#labelCatapult");
//		Label drop = null;
//		
//		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
//		for (javafx.scene.Node i : a.getChildren()) {
//			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
//				Label h = (Label)i;
//				if(h.getId()=="green") {
//					drop = h;
//					break;
//				}
//			}
//		}
//		drag(b,MouseButton.PRIMARY).dropTo(drop);
//		moveTo(drop);
//		drag(b,MouseButton.PRIMARY).dropTo(drop);
//		assertTrue(MyController.doubleBuilt);
//		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
//		int index = Helper.getTowerIndex(coord, MyController.towersUT);
//		if(!MyController.towersUT.isEmpty()) MyController.towersUT.remove(index);
//	}
//	
//	@Test
//	public void upgradeNotEnoughResources() {
//		MyController.resetResourcesForTesting();
//		Label b = (Label)s.lookup("#labelLaserTower");
//		Label drop = null;
//		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
//		for (javafx.scene.Node i : a.getChildren()) {
//			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
//				Label h = (Label)i;
//				if(h.getId()=="green" && h.getLayoutX() == 40 && h.getLayoutY() == 40) {
//					drop = h;
//				}
//				
//			}
//		}		
//		drag(b,MouseButton.PRIMARY).dropTo(drop);
//		clickOn(drop);
//		clickOn(700,300);
//		assertTrue(MyController.notEnoughToUpgrade);
//		int[] coord = { (int) drop.getLayoutX(), (int) drop.getLayoutY() };
//		int index = Helper.getTowerIndex(coord, MyController.towersUT);
//		if(!MyController.towersUT.isEmpty()) MyController.towersUT.remove(index);
//	}
//	
//	
//}
