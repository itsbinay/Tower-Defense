package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;

import com.sun.prism.paint.Color;

import sample.MyController;
import sample.Helper;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

	
//	@Test
//	public void dragTower() {
//		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
//		Label dropPoint = null;
//		for (javafx.scene.Node i : b.getChildren()) {
//			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
//				Label g = (Label)i;
//				if(g.getId() == "green") {
//					dropPoint = g;
//				}				
//			}
//		}
//		for (javafx.scene.Node i : b.getChildren()) {
//			if (i.getClass().getName().equals("javafx.scene.control.Label")) {
//				Label h = (Label)i;
//				if(h.getId() == "labelBasicTower") {
//					drag(h).dropTo(dropPoint);
//				}
//			}
//		}
//	}
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
	@Test
	public void testDragTower() {
		List<Label> labels = new ArrayList<>();
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
	}
	/*
	@Test
	public void testPlayButton() {
		
		clickOn("#buttonPlay");
		
		AnchorPane b = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : b.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getLayoutX()==20 && h.getLayoutY()==20)
					assertEquals(h.getText(),"B");
			}
		}
				
	}
	*/
	
}
