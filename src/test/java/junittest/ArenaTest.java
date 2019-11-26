package junittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import sample.MyController;
import tower.Tower;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ArenaTest extends ApplicationTest{

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
		
//		@Test
//		public void testDragTower() {
//			List<Label> labels = new ArrayList<>();
//			AnchorPane b = (AnchorPane)s.lookup("#paneArena");
//			Label dropPoint = null;
//			for (javafx.scene.Node i : b.getChildren()) {
//				if (i.getClass().getName().equals("javafx.scene.control.Label")) {
//					labels.add((Label)i);			
//				}
//			}
//			for(Label l : labels) {
//				if(l.getId() == "green") {
//					dropPoint = l;
//				}					
//				if(l.getId() == "labelBasicTower") 
//					drag(l,MouseButton.PRIMARY);
//			}
//		}
		
//		@Test
//		public void testNextFrameButton() {
//			clickOn("#buttonNextFrame");
//			AnchorPane b = (AnchorPane)s.lookup("#paneArena");
//			for (javafx.scene.Node i : b.getChildren()) {
//				if (i.getClass().getName().equals("javafx.scene.control.Label")) {
//					Label h = (Label)i;
//					if (h.getLayoutX() == 0 && h.getLayoutY() == 0)
//						assertEquals(h.getText(), "M");
//				}
//			}
//		}
		
		public void testPlayButton() {
			clickOn("#buttonPlay");
			AnchorPane b = (AnchorPane)s.lookup("#paneArena");
			for (javafx.scene.Node i : b.getChildren()) {
				if (i.getClass().getName().equals("javafx.scene.control.Label")) {
					Label h = (Label)i;
					if (h.getLayoutX() == 20 && h.getLayoutY() == 20)
						assertEquals(h.getText(), "|-|");
					System.out.print("PLAY");
				}
			}
		}
		

}
