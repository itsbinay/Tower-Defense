package TowerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;

import sample.MyController;
import sample.MyController.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import monster.Fox;
import monster.Monster;
import monster.Penguin;
import monster.Unicorn;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;

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
	public void test1() {
		int[] foxCoord = { 0, 0 };
		int[] unicornCoord = { 1, 0 };
		int[] penguinCoord = { 2, 0 };
		

		Monster TestFox = new Fox(foxCoord, 100, 2, 0);
		Monster TestUnicorn = new Unicorn(unicornCoord, 150, 1, 0);
		Monster TestPenguin = new Penguin(penguinCoord, 100, 1, 0);
		

		assertEquals(TestFox.getMonsterType(), "Fox");
		assertEquals(TestUnicorn.getMonsterType(), "Unicorn");
		assertEquals(TestPenguin.getMonsterType(), "Penguin");

		assertEquals(TestFox.getResourceEarned(), 50);
		assertEquals(TestUnicorn.getResourceEarned(), 50);
		assertEquals(TestPenguin.getResourceEarned(), 50);

		assertEquals(TestFox.getMonsterState(), Monster.MonsterState.UNTOUCHED);
		assertEquals(TestUnicorn.getMonsterState(), Monster.MonsterState.UNTOUCHED);
		assertEquals(TestPenguin.getMonsterState(), Monster.MonsterState.UNTOUCHED);

		assertEquals(TestFox.getMovementSpeed(), 2);
		assertEquals(TestUnicorn.getMovementSpeed(), 1);
		assertEquals(TestPenguin.getMovementSpeed(), 1);

		assertEquals(TestFox.getImg(), "fox.png");
		assertEquals(TestUnicorn.getImg(), "unicorn.png");
		assertEquals(TestPenguin.getImg(), "penguin.png");

		assertEquals(TestFox.getHp(), 100);
		assertEquals(TestUnicorn.getHp(), 150);
		assertEquals(TestPenguin.getHp(), 100);

		TestFox.setFrozen(1);
		TestUnicorn.setFrozen(1);
		TestPenguin.setFrozen(1);

		TestFox.reduceSpeed();
		TestUnicorn.reduceSpeed();
		TestPenguin.reduceSpeed();

		assertEquals(TestFox.getMovementSpeed(), 1);
		assertEquals(TestUnicorn.getMovementSpeed(), 0);
		assertEquals(TestPenguin.getMovementSpeed(), 0);

		assertEquals(TestFox.getFrozen(), 0);
		assertEquals(TestUnicorn.getFrozen(), 0);
		assertEquals(TestPenguin.getFrozen(), 0);

		assertEquals(TestFox.getMovementSpeed(), 1);
		assertEquals(TestUnicorn.getMovementSpeed(), 0);
		assertEquals(TestPenguin.getMovementSpeed(), 0);

		TestFox.unFreeze();
		TestUnicorn.unFreeze();
		TestPenguin.unFreeze();

		assertEquals(TestFox.getMovementSpeed(), 2);
		assertEquals(TestUnicorn.getMovementSpeed(), 1);
		assertEquals(TestPenguin.getMovementSpeed(), 1);

		TestPenguin.setHp(90);
		TestPenguin.regenerate();
		assertEquals(TestPenguin.getHp(), 100);

	}

	@Test
	public void test2() {

		for (int i = 0; i < 20; i++) {
			clickOn("#buttonNextFrame");
		}

		int[] foxPos = { 200, 440 };
		int[] unicornPos = { 80, 200 };
		int[] penguinPos = { 80, 200 };

		switch (MyController.testMonsterList.get(0).getMonsterType()) {
		case "Fox": {
			assertArrayEquals(MyController.testMonsterList.get(0).getCoord(), foxPos);
			break;
		}
		case "Penguin": {
			assertArrayEquals(MyController.testMonsterList.get(0).getCoord(), penguinPos);
			break;
		}
		case "Unicorn": {
			assertArrayEquals(MyController.testMonsterList.get(0).getCoord(), unicornPos);
			break;

		}

		}

	}
	
	@Test
	public void test3() {
		Label bTower = (Label)s.lookup("#labelBasicTower");
		Label cTower = (Label)s.lookup("#labelCatapult");
		Label iTower = (Label)s.lookup("#labelIceTower");
		Label lTower = (Label)s.lookup("#labelLaserTower");
		
		Label drop = null;
		AnchorPane a = (AnchorPane)s.lookup("#paneArena");
		for (javafx.scene.Node i : a.getChildren()) {
			if(i.getClass().getName().equals("javafx.scene.control.Label")) {
				Label h = (Label)i;
				if(h.getId()=="green" && h.getLayoutY()==40 && h.getLayoutX()==40)
				{
					drop = h;
					break;
				}
			}
		}
		drag(bTower,MouseButton.PRIMARY).dropTo(drop);
		moveTo(drop);
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
				Label drop2 = null,drop3=null;
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
						if(h.getId()=="green" && h.getLayoutY()==280 && h.getLayoutX()==40) {
							drop3 = h;
						}
					}
				}
				drag(bTower,MouseButton.PRIMARY).dropTo(drop2);
				drag(iTower,MouseButton.PRIMARY).dropTo(drop);
				drag(lTower,MouseButton.PRIMARY).dropTo(drop3);
			}
			if(count==60) {
				drop=null;
				Label drop2 = null;
				Label drop3 = null;
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
						if(h.getId()=="green" && h.getLayoutY()==280 && h.getLayoutX()==200) {
							drop3 = h;
						}
					}
				}
				drag(lTower,MouseButton.PRIMARY).dropTo(drop);
				drag(bTower,MouseButton.PRIMARY).dropTo(drop2);
				drag(lTower,MouseButton.PRIMARY).dropTo(drop3);
			}
		}
	}
	
	
}
