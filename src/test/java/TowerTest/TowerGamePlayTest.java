package TowerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;
import sample.MyController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class TowerGamePlayTest extends ApplicationTest{
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
	
	@Before
	public void setUp() {
		MyController.setResourcesForTesting();
	}
	
	@Test
	public void testTowerAttack() {
		Label bTower = (Label)s.lookup("#labelBasicTower");
		Label cTower = (Label)s.lookup("#labelCatapult");
		Label iTower = (Label)s.lookup("#labelIceTower");
		Label lTower = (Label)s.lookup("#labelLaserTower");
		
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green")
				{
					drop = h;
					break;
				}
			}
		}
		drag(bTower,MouseButton.PRIMARY).dropTo(drop);
		
		int count=0;
		while(count<120) {
			clickOn("#buttonNextFrame");
			count++;
			if(count==20) {
				drop = null;
				for (javafx.scene.Node i : a.getChildren()) {
					if(i.getClass().getName().equals("javafx.scene.control.Label")) {
						Label h = (Label)i;
						if(h.getId()=="green" && h.getLayoutY()==120 && h.getLayoutX()==40)
						{
							drop = h;
							break;
						}
					}
				}
				drag(cTower,MouseButton.PRIMARY).dropTo(drop);
				
			}
			if(count==40) {
				drop=null;
				Label drop2 = null;
				for (javafx.scene.Node i : a.getChildren()) {
					if(i.getClass().getName().equals("javafx.scene.control.Label")) {
						Label h = (Label)i;
						if(h.getId()=="green" && h.getLayoutY()==40 && h.getLayoutX()==120)
						{
							drop = h;
						}
						if(h.getId()=="green" && h.getLayoutY()==120 && h.getLayoutX()==200) {
							drop2 = h;
						}
					}
				}
				drag(bTower,MouseButton.PRIMARY).dropTo(drop2);
				drag(iTower,MouseButton.PRIMARY).dropTo(drop);
			}
			if(count==60) {
				drop=null;
				Label drop2 = null;
				for (javafx.scene.Node i : a.getChildren()) {
					if(i.getClass().getName().equals("javafx.scene.control.Label")) {
						Label h = (Label)i;
						if(h.getId()=="green" && h.getLayoutY()==200 && h.getLayoutX()==200)
						{
							drop = h;
						}
						if(h.getId()=="green" && h.getLayoutY()==200 && h.getLayoutX()==120) {
							drop2 = h;
						}
					}
				}
				drag(lTower,MouseButton.PRIMARY).dropTo(drop);
				drag(bTower,MouseButton.PRIMARY).dropTo(drop2);
			}
		}
	}

}
