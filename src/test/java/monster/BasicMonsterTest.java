/*
package monster;


import static org.junit.Assert.*;

import org.junit.Test;
import monster.Monster;
import monster.Penguin;
import monster.Fox;
import monster.Unicorn;
import monster.Monster.MonsterState;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.MyController;

public class BasicMonsterTest extends ApplicationTest {
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
	@Test
	public void statsTest() {
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
	public void TestFSM() {

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

}
*/