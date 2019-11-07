package test;

import static org.junit.Assert.*;

import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;

import com.sun.prism.paint.Color;

import sample.MyController;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class UITest extends ApplicationTest {

	private Scene s;

	@Override
	public void start(Stage primaryStage) throws Exception {
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
}