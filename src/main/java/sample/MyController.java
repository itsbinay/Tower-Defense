package sample;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import monster.Fox;
import monster.Monster;
import monster.Penguin;
import monster.Unicorn;
import sample.Helper;
import tower.*;


public class MyController {

	@FXML
	private Button buttonNextFrame;

	@FXML
	private Button buttonSimulate;

	@FXML
	private Button buttonPlay;

	@FXML
	private AnchorPane paneArena;

	@FXML
	private Label labelBasicTower;

	@FXML
	private Label labelIceTower;

	@FXML
	private Label labelCatapult;

	@FXML
	private Label labelLaserTower;

	@FXML
	private Label money;

	private static final int ARENA_WIDTH = 480;
	private static final int ARENA_HEIGHT = 480;
	private static final int GRID_WIDTH = 40;
	private static final int GRID_HEIGHT = 40;
	private static final int MAX_H_NUM_GRID = 12;
	private static final int MAX_V_NUM_GRID = 12;
	private static final int catapultCost = 80;
	private static final int catapultAOEDamage = 30;
	private static final int basicCost = 60;
	private static final int laserCost = 180;
	private static final int iceCost = 90;
	private static final int laserHurt = 20;
	int resourcesAmount = 200;

	private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; // the grids on arena
	private List<Tower> towers = new ArrayList<>();
	private int x = -1, y = 0; // where is my monster
	private Shape rangeCircle = new Circle();

	private Label invisibleLabel = new Label();
	private boolean circleShown = false;

	private List<Monster> monsterList = new ArrayList<>();

	private List<Integer> collisionX = new ArrayList<Integer>();
	private List<Integer> collisionY = new ArrayList<Integer>();

	private List<Line> laserLines = new ArrayList<>();
	private List<Monster> catapultTarget = new ArrayList<>();
	private Monster laserTarget;

	private List<Monster> monsterInRange = new ArrayList<>();
	private static int monsterCounter = 0;
	private static int nextFrameCounter = 0;
	private static int[] startCoord = { 0, 0 };
	Random r = new Random();
	int low = 1;
	int high = 3;

	private static int speedIncrease = 0;
	private static int numOfFrames = 0;
	private static int bonusHp = 50;
	 
	private static int perFrame = 3;
	
	private boolean clicked = false;
    private int numClicks = 0;
    private boolean upgraded = false;

	/**
	 * Returns the index of the tower in the towers list, given the coordinate if
	 * tower doesn't exist 0 is returned
	 * 
	 * @param coord The Coordinate of the label
	 * @return the index of where the Tower is
	 */
	private int getTowerIndex(int[] coord) {
		for (int i = 0; i < towers.size(); i++) {
			if (towers.get(i).getCoord()[0] == coord[0] && towers.get(i).getCoord()[1] == coord[1])
				return i;
		}
		return 0;
	}

	/**
	 * A dummy function to show how button click works
	 */
	@FXML
	private void play() {
		System.out.println("Play button clicked");
		Label newLabel = new Label();
		newLabel.setLayoutX(GRID_WIDTH / 4);
		newLabel.setLayoutY(GRID_WIDTH / 4);
		newLabel.setMinWidth(GRID_WIDTH / 2);
		newLabel.setMaxWidth(GRID_WIDTH / 2);
		newLabel.setMinHeight(GRID_WIDTH / 2);
		newLabel.setMaxHeight(GRID_WIDTH / 2);
		newLabel.setStyle("-fx-border-color: black;");
		newLabel.setText("|-|");
		newLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		paneArena.getChildren().addAll(newLabel);
	}

	/**
	 * A function that create the Arena
	 */
	@FXML
	public void createArena() {
		Tooltip basicInfo = new Tooltip();
		Tooltip iceInfo = new Tooltip();
		Tooltip catapultInfo = new Tooltip();
		Tooltip laserInfo = new Tooltip();

		basicInfo.setText(Helper.labelProcessing(labelBasicTower.getId()) + "\n" + "Cost: " + Integer.toString(basicCost));
		iceInfo.setText(Helper.labelProcessing(labelIceTower.getId()) + "\n" + "Cost: " + Integer.toString(iceCost));
		catapultInfo.setText(Helper.labelProcessing(labelCatapult.getId()) + "\n" + "Cost: " + Integer.toString(catapultCost));
		laserInfo.setText(Helper.labelProcessing(labelLaserTower.getId()) + "\n" + "Cost: " + Integer.toString(laserCost));

		labelBasicTower.setTooltip(basicInfo);
		labelIceTower.setTooltip(iceInfo);
		labelCatapult.setTooltip(catapultInfo);
		labelLaserTower.setTooltip(laserInfo);
		if (grids[0][0] != null)
			return; // created already
		for (int i = 0; i < MAX_V_NUM_GRID; i++) {
			for (int j = 0; j < MAX_H_NUM_GRID; j++) {
				Label newLabel = new Label();
				if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1))
					newLabel.setBackground(
							new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				else
					newLabel.setBackground(
							new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				newLabel.setLayoutX(j * GRID_WIDTH);
				newLabel.setLayoutY(i * GRID_HEIGHT);
				newLabel.setMinWidth(GRID_WIDTH);
				newLabel.setMaxWidth(GRID_WIDTH);
				newLabel.setMinHeight(GRID_HEIGHT);
				newLabel.setMaxHeight(GRID_HEIGHT);
				newLabel.setStyle("-fx-border-color: black;");
				grids[i][j] = newLabel;
				paneArena.getChildren().addAll(newLabel); // add all the labels to the AnchorPane created, without this
			}
		}
		gameEvents();
	}

	public void monsterGenerate() {
		System.out.println("monsterGenerate");
		int result = r.nextInt(high - low + 1) + low + 1;

		switch (result) {
		case 1:monsterList.add(new Fox(startCoord, 100, 1, 0));break;
		case 2:monsterList.add(new Unicorn(startCoord, 150, 1, 0));break;
		case 3:monsterList.add(new Penguin(startCoord, 100, 1, 0));break;
		default:monsterList.add(new Fox(startCoord, 100, 1, 0));
		}
	}

	private void Move(int op, int x, int y, int spaceLeft, int monsterCount) {
		System.out.println("X AXIS " + x);

		if (spaceLeft < 1) {
			int[] newCoord = { y, x };
			monsterList.get(monsterCount).setYX(newCoord);
			String imageName = monsterList.get(monsterCount).getImg();
			Image monsterImage = new Image(imageName, 30.0, 30.0, true, true);
			ImageView monsterImageView = new ImageView();
			monsterImageView.setImage(monsterImage);
			grids[y][x].setGraphic(monsterImageView);
			grids[y][x].setText(String.valueOf(monsterList.get(monsterCount).getHp()));

			return;
		}
		switch (op) {
		case 0: {
			if (y == MAX_V_NUM_GRID - 1) {
				Move(1, x, y, spaceLeft, monsterCount);
			} else {
				Move(0, x, y + 1, spaceLeft - 1, monsterCount);
			}
			break;
		}
		case 1: {
			if (grids[y][x + 1].getBackground().getFills().get(0).getFill() == Color.GREEN) {
				if (y == MAX_V_NUM_GRID - 1) {
					Move(2, x, y, spaceLeft, monsterCount);
				} else if (y == 0 && x != 0) {
					Move(0, x, y, spaceLeft, monsterCount);
				}
			} else {
				Move(1, x + 1, y, spaceLeft - 1, monsterCount);
			}
			break;
		}
		case 2: {
			if (y == 0) {
				Move(1, x, y, spaceLeft, monsterCount);
			} else {
				Move(2, x, y - 1, spaceLeft - 1, monsterCount);
			}
			break;
		}
		default:
			break;
		}
	}

	/**
	 * Debugging tool to list out all Monster State
	 */
	void listAllMonster() {
		if (monsterList.size() == 0)
			return;
		System.out.println("Listing out All Monsters");
		for (int i = 0; i < monsterList.size(); i++) {
			int[] coord = monsterList.get(i).getCoord();
			System.out.println(i + ":" + coord[0] + "," + coord[1] + " " + monsterList.get(i).getHp());
		}
		System.out.println();
	}

	/**
	 * Debugging tool to list out all Tower State
	 */
	void listAllTower() {
		for (int i = 0; i < towers.size(); i++) {
			System.out.println("Tower(" + i + ") State:" + towers.get(i).getStateStr() + " Timer:" + towers.get(i).getCooldown()+ " Coord:" + towers.get(i).getCoord()[0] + "," + towers.get(i).getCoord()[1]);
		}
		System.out.println("");
	}

	void updateAllTowerState() {
		for(int i=0;i<towers.size();i++)towers.get(i).updateTowerState();
	}

	void getMonstersInRange(Tower curTower) {
		monsterInRange.clear();
		catapultTarget.clear();
		for (int i = 0; i < monsterList.size(); i++) {
			if (curTower.isInRange(monsterList.get(i).getCoord())) {
				monsterInRange.add(monsterList.get(i));
			}
		}
	}

	/*
	 * Catapult Shit
	 */
	boolean withinAOErange(int[] coord1, int[] coord2) {
		double shortestDistance = (Math.pow((coord1[0] - coord2[0]), 2) + Math.pow(coord1[1] - coord2[1], 2));
		if (shortestDistance <= (25 * 25))
			return true; // Within the 25px radius
		return false;
	}

	void catapultAOE() {
		if (catapultTarget.size() == 0)
			return;
		for (int i = 0; i < catapultTarget.size(); i++) {
			List<Monster> targetNearby = new ArrayList<>();
			for (int j = 0; j < monsterList.size(); j++) {
				if (withinAOErange(catapultTarget.get(i).getCoord(), monsterList.get(j).getCoord())) {// If within the
																										// AOE range
					targetNearby.add(monsterList.get(j));
				}
			}
			if (targetNearby.size() == 0)
				continue;
			for (int j = 0; j < targetNearby.size(); j++) {
				targetNearby.get(j).setHp(targetNearby.get(j).getHp() - catapultAOEDamage);
			}
		}
	}

	/*
	 * Laser Tower Shit
	 */
	private enum Laserdir {
		LEFT, STRAIGHT, RIGHT
	};

	boolean checkWithinLaserBeam(Line curLine, Monster monster) {
		double[] lineStart = { curLine.getStartX(), curLine.getStartY() };
		double[] lineEnd = { curLine.getEndX(), curLine.getEndY() };
		int[] monsterCoord = monster.getCoord();
		boolean infSlope = false, zeroSlope = false;

		if (lineStart[0] - lineEnd[0] == 0)
			infSlope = true; // Same X then infinity slope
		if (lineStart[1] - lineEnd[1] == 0)
			zeroSlope = true; // Same y then horizontal slope

		if (infSlope) {
			double distance = Math.abs(monsterCoord[0] - lineStart[0]);
			if (distance < 3)
				return true;
		} else if (zeroSlope) {
			double distance = Math.abs(monsterCoord[1] - lineStart[1]);
			if (distance < 3)
				return true;
		} else {
			double a = -(lineStart[1] - lineEnd[1]) / (lineStart[0] - lineEnd[0]); // -m
			double b = 1.0;
			double c = (-a * monsterCoord[0] - monsterCoord[1]);

			// Distance between point and line Formula = Abs(Ax+By+C)/sqrt(A^2 + B^2);
			double distance = Math.abs(a * monsterCoord[0] + b * monsterCoord[1] + c) / Math.sqrt(a * a + b * b);
			if (distance < 3)
				return true;
		}
		return false;
	}

	void drawLaserLine(int[] start) {
		Laserdir curDir;
		boolean up = false;
		int[] tarCoord = laserTarget.getCoord();

		double slope = 0;
		if (start[0] - tarCoord[0] != 0) {
			slope = (start[1] - tarCoord[1]) / (start[0] - tarCoord[0]);
		}
		double y_intercept_left = start[1] - slope * start[0];
		double y_intercept_right = slope * ARENA_WIDTH + start[1] - slope * start[0];

		if (start[0] > tarCoord[0])
			curDir = Laserdir.LEFT;
		else if (start[0] == tarCoord[0])
			curDir = Laserdir.STRAIGHT;
		else
			curDir = Laserdir.RIGHT;
		if (start[1] > tarCoord[1])
			up = true;

		switch (curDir) { // Adds the line
		case LEFT: {
			Line line1 = new Line((double) start[0] + GRID_WIDTH / 2, (double) start[1] + GRID_HEIGHT / 2, 0.0,
					y_intercept_left);
			line1.setStroke(Color.RED);
			laserLines.add(line1);
			paneArena.getChildren().add(line1);
			break;
		}
		case RIGHT: {
			Line line1 = new Line((double) start[0] + GRID_WIDTH / 2, (double) start[1] + GRID_HEIGHT / 2, ARENA_WIDTH,
					y_intercept_right);
			line1.setStroke(Color.RED);
			laserLines.add(line1);
			paneArena.getChildren().add(line1);
			break;
		}
		case STRAIGHT: {
			if (up) {
				Line line1 = new Line((double) start[0] + GRID_WIDTH / 2, (double) start[1] + GRID_HEIGHT / 2,
						(double) tarCoord[0] + GRID_WIDTH / 2, 0.0);
				line1.setStroke(Color.RED);
				laserLines.add(line1);
				paneArena.getChildren().add(line1);
			} else {
				Line line1 = new Line((double) start[0] + GRID_WIDTH / 2, (double) start[1] + GRID_HEIGHT / 2,
						(double) tarCoord[0] + GRID_WIDTH / 2, (double) ARENA_HEIGHT);
				line1.setStroke(Color.RED);
				laserLines.add(line1);
				paneArena.getChildren().add(line1);
			}
			break;
		}
		}
		System.out.println("Start:" + start[0] + "," + start[1] + "  End:" + tarCoord[0] + "," + tarCoord[1]);
		System.out.println("Drew a line " + laserLines.size());
	}

	void attackAllMonsterNearLine() {
		int count = 0;
		for (int i = 0; i < laserLines.size(); i++) {
			for (int j = 0; j < monsterList.size(); j++) {
				if (checkWithinLaserBeam(laserLines.get(i), monsterList.get(j))) {
					monsterList.get(j).setHp(monsterList.get(j).getHp() - laserHurt);
					count++;
				}
			}
			paneArena.getChildren().remove(laserLines.get(i));
		}
		System.out.println("No. of monsters affectedd by laser beam: " + count);
		laserLines.clear(); // Clears the entire lsit
	}

	void attackClosestMonster(Tower curTower) {
		if (monsterInRange.size() == 0)
			return;
		int[] coord = monsterInRange.get(0).getCoord();

		double shortestDistance = (Math.pow((coord[0] - 440), 2) + Math.pow(coord[1], 2)); // Eucledian Distance with (440,0)

		int index = 0;
		for (int i = 1; i < monsterInRange.size(); i++) {
			coord = monsterInRange.get(i).getCoord();
			double dist = (Math.pow((coord[0] - 440), 2) + Math.pow(coord[1], 2));
			if (shortestDistance > dist) {
				shortestDistance = dist;
				index = 1;
			}
		}
		switch (curTower.getTowerType()) {
		case "basicTower": {
			monsterInRange.get(index).setHp(curTower.attack(monsterInRange.get(index).getHp()));
			break;
		}
		case "iceTower": {
			monsterInRange.get(index).setHp(curTower.attack(monsterInRange.get(index).getHp()));
			monsterInRange.get(index).setFrozen(((IceTower) curTower).getFreezeTimer());
			break;
		}
		case "catapult": {
			catapultTarget.add(monsterInRange.get(index));
			break;
		}
		case "laserTower": {
			if (curTower.getAttackCost() > resourcesAmount)
				return; // If not adequate resources, GTFO
			if (curTower.getTowerState() == Tower.TowerState.READY) {
				laserTarget = monsterInRange.get(index);
				resourcesAmount -= curTower.getAttackCost();
				monsterInRange.get(index).setHp(curTower.attack(monsterInRange.get(index).getHp())); // this called to reset the cooldown																							
				drawLaserLine(curTower.getCoord());
			}
			break;
		}
		}
	}

	void TowerAttackMonster() {
		for (int i = 0; i < towers.size(); i++) {
			getMonstersInRange(towers.get(i));
			attackClosestMonster(towers.get(i));
		}
		catapultAOE();
		attackAllMonsterNearLine();
		updateAllTowerState();
	}

	private void MonsterFSM() {
		numOfFrames++;

		for (int i = 0; i < collisionX.size(); i++) {
			grids[collisionX.get(i)][collisionX.get(i)].setGraphic(null);
			grids[collisionX.get(i)][collisionX.get(i)].setText("");

			collisionX.remove(i);
			collisionY.remove(i);
		}

		if (numOfFrames % 10 == 0 && numOfFrames != 0) {
			speedIncrease++;
			speedIncrease = (int) Math.pow(2, speedIncrease);
		}

		for (int i = 0; i < monsterList.size(); i++) {

			if (monsterList.get(i).getFrozen() > 0)
				monsterList.get(i).reduceSpeed();

			boolean down = false;
			boolean right = false;

			int movementSpeed = monsterList.get(i).getMovementSpeed();
			int x = monsterList.get(i).getX();
			int y = monsterList.get(i).getY();

			System.out.println("x: " + x);
			System.out.println("y: " + y);

			grids[y][x].setGraphic(null);
			grids[y][x].setText("");

			grids[y][x].setGraphic(null);
			grids[y][x].setText("");

			if (x + 1 > 11) {
				System.out.println("Game over");
				perFrame = 100;
				return;
			}

			if (x % 4 == 0)
				down = true;
			else
				down = false;

			if (grids[y][x + 1].getBackground().getFills().get(0).getFill() == Color.GREEN) {
				right = false;
			} else {
				right = true;
			}
			if (right)
				Move(1, x, y, movementSpeed + speedIncrease, i);
			else {
				if (down)
					Move(0, x, y, movementSpeed + speedIncrease, i);
				else
					Move(2, x, y, movementSpeed + speedIncrease, i);
			}

			if (monsterList.get(i).getFrozen() == 0)monsterList.get(i).unFreeze();

			grids[0][0].setText("");

		}

		if (grids[0][0].getGraphic() == null && nextFrameCounter % perFrame == 0) {

			monsterGenerate();
			if (numOfFrames % 10 == 0 && numOfFrames != 0) {
				int currHp = monsterList.get(monsterList.size() - 1).getHp();
				int newHp = currHp + bonusHp;
				bonusHp = bonusHp + 50;

				monsterList.get(monsterList.size() - 1).setHp(newHp);

			}

			String imageName = monsterList.get(monsterList.size() - 1).getImg();
			Image monsterImage = new Image(imageName, 30.0, 30.0, true, true);
			ImageView monsterImageView = new ImageView();
			monsterImageView.setImage(monsterImage);
			grids[0][0].setGraphic(monsterImageView);
			grids[0][0].setText(String.valueOf(monsterList.get(monsterList.size() - 1).getHp()));

		}

		nextFrameCounter++;
	}

	private void clearDeadMonster() {
		for (int i = 0; i < monsterList.size(); i++) {
			if (monsterList.get(i).getHp() <= 0) {
				Image collisionImage = new Image("collision.png", 30.0, 30.0, true, true);
				ImageView collisionImageView = new ImageView();
				collisionImageView.setImage(collisionImage);
				grids[monsterList.get(i).getY()][monsterList.get(i).getX()].setGraphic(collisionImageView);
				collisionX.add(monsterList.get(i).getX());
				collisionY.add(monsterList.get(i).getY());
				System.out.println("Getting resources:" + monsterList.get(i).getResourceEarned());
				resourcesAmount += monsterList.get(i).getResourceEarned(); // Resources Gained
				monsterList.remove(i);
			}
		}
	}

	private void clearAllCollision() {
		if (collisionX.size() != collisionY.size()) {
			System.out.println("WTF Error in collisionList size, X-Y not identical");
			return;
		}
		for (int i = 0; i < collisionX.size(); i++) {
			grids[collisionY.get(i)][collisionX.get(i)].setGraphic(null);
		}
		for (int i = 0; i < collisionX.size(); i++) {
			collisionX.remove(i);
			collisionY.remove(i);
		}
	}

	@FXML
	private void nextFrame() {
		// Debugging Print Tools
		listAllMonster();
		listAllTower();

		clearAllCollision(); // Clears out all collision
		MonsterFSM(); // In charge of how the monster moves on the arena

		TowerAttackMonster(); // In charge of telling the Tower to Attack the Monster
		clearDeadMonster(); // Sets the dead monster as Collision Image
		updateResourceText(); // Update the text after earning some cash from the dead monster
		System.out.println("resources amount:" + resourcesAmount);
	}

	private void updateResourceText() {
		money.setText("Money Left: " + Integer.toString(resourcesAmount));
	}

	public int[] getTowerCoords(int[] coord) {
		int[] returnCoords = { 0, 0 };
		for (int i = 0; i < MAX_V_NUM_GRID; i++)
			for (int j = 0; j < MAX_H_NUM_GRID; j++) {
				if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1))
					continue;
				else {
					if (grids[i][j].getLayoutX() == coord[0] && grids[i][j].getLayoutY() == coord[1]) {
						returnCoords[0] = i;
						returnCoords[1] = j;
						//System.out.println("getTowerCoord-i:" + i + " j:" + j);
					}
				}
			}
		return returnCoords;

	}

	private void initEachInvisibleLabel() {
		invisibleLabel.setMinWidth(GRID_WIDTH);
		invisibleLabel.setMaxWidth(GRID_WIDTH);
		invisibleLabel.setMinHeight(GRID_HEIGHT);
		invisibleLabel.setMinHeight(GRID_HEIGHT);
		invisibleLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	void inadequateBuildError(String tower) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Cannot build " + tower);
		alert.setContentText("Not enough money!");
		alert.show();
	}

	/**
	 * A function that allows dragging of Towers
	 * 
	 */
	private void gameEvents() {
		// where on the x by y grid to put the text "Drop Here"
		// target.setText("Drop\nHere");

		for (int i = 0; i < MAX_V_NUM_GRID; i++) {
			for (int j = 0; j < MAX_H_NUM_GRID; j++) {
				if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1)){
					Tooltip MonsterInfo = new Tooltip();
					
					Label target = grids[i][j];
					target.setOnMouseEntered(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							if (target.getText() != "") {
								String hpInfo = "HP: " + target.getText();
								
								MonsterInfo.setText(hpInfo);
								
								target.setTooltip(MonsterInfo);
								System.out.println("monsterHp " + target.getText());
							} else {

							}
							event.consume();
						}
					});
				}else {
					Label target = grids[i][j];
					Label source1 = labelBasicTower;
					Label source2 = labelIceTower;
					Label source3 = labelCatapult;
					Label source4 = labelLaserTower;
					
					source1.setOnDragDetected(new DragEventHandler(source1)); 
					source2.setOnDragDetected(new DragEventHandler(source2));
					source3.setOnDragDetected(new DragEventHandler(source3));
					source4.setOnDragDetected(new DragEventHandler(source4));

					target.setOnDragDropped(new DragDroppedEventHandler() {
						public void handle(DragEvent event) {
							// if(flagTower[i][j]== false) {
							Dragboard db = event.getDragboard();
							String id = db.getString();
							// System.out.println(Helper.preProcessing(imageName));
							String imageName = Helper.preProcessing(id);
							Image towerImage = new Image(imageName, 30.0, 30.0, true, true);
							ImageView towerImageView = new ImageView();
							towerImageView.setImage(towerImage);

							int[] coord = { (int) target.getLayoutX(), (int) target.getLayoutY() };
							if (target.getGraphic() == null) {
								String towerName = Helper.getTowerName(imageName); // This will give me the towerName
								switch (towerName) {// Switch-case to instantiate a Tower object accordingly
									case "basicTower": {
										if (resourcesAmount < basicCost || resourcesAmount == 0) {
											inadequateBuildError("Basic Tower");
										} else {
											target.setGraphic(towerImageView);
											target.setId(towerName); /**/
											towers.add(new basicTower(coord));
											resourcesAmount -= towers.get(towers.size() - 1).getTowerCost();
										}
										break;
									}
									case "iceTower": {
										if (resourcesAmount < iceCost || resourcesAmount == 0) {
											inadequateBuildError("Ice Tower");
										} else {
											target.setGraphic(towerImageView);
											target.setId(towerName); /**/
											towers.add(new IceTower(coord));
											resourcesAmount -= towers.get(towers.size() - 1).getTowerCost();
										}
										break;
									}
									case "catapult": {
										if (resourcesAmount < catapultCost || resourcesAmount == 0) {
											inadequateBuildError("Catapult");
										} else {
											target.setGraphic(towerImageView);
											target.setId(towerName); /**/
											towers.add(new Catapult(coord));
											resourcesAmount -= towers.get(towers.size() - 1).getTowerCost();
										}
										break;
									}
									case "laserTower": {
										if (resourcesAmount < laserCost || resourcesAmount == 0) {
											inadequateBuildError("Laser Tower");
										} else {
											target.setGraphic(towerImageView);
											target.setId(towerName); /**/
											towers.add(new laserTower(coord));
											resourcesAmount -= towers.get(towers.size() - 1).getTowerCost();
										}
										break;
									}
								}
							} else {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error");
								alert.setContentText("A tower is already built here");
								alert.setHeaderText("Cannot place tower here");
								alert.show();
							}
							
							updateResourceText();
							event.setDropCompleted(true);
							
							event.consume();
						}
						

					});
					target.setOnDragOver(new EventHandler<DragEvent>() {
						public void handle(DragEvent event) {
							if (event.getGestureSource() != target && event.getDragboard().hasString()) {
							// System.out.println("onDragOver");
							}
							event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
							event.consume();
						}
					});

					target.setOnDragEntered(new EventHandler<DragEvent>() {					
						public void handle(DragEvent event) {
							if (event.getGestureSource() != target && event.getDragboard().hasString()) {
								//System.out.println("onDragEntered");
							}
							target.setStyle("-fx-border-color: red;");
							event.consume();
						}
					});
					/* mouse moved away, remove the graphical cues */
					target.setOnDragExited((event) -> {
						System.out.println("Exit");
						target.setStyle("-fx-border-color: black;");	
						event.consume();
					});

					Tooltip towerInfo = new Tooltip();
					
					target.setOnMouseEntered(new EventHandler<MouseEvent>() {
						//target.setPickOnBounds(true);
						@Override
						public void handle(MouseEvent event) {
							if (target.getGraphic() == null) {
								System.out.println("There is no tower");
							} else {
								if (!circleShown) {
									System.out.println("There is tower");

									int[] coord = { (int) target.getLayoutX(), (int) target.getLayoutY() };
									// Circle, Arc or Rectangle
									
									switch (target.getId()) {
										case "basicTower": {
											int radius = towers.get(getTowerIndex(coord)).getRange();
											int length = radius * 2 + GRID_HEIGHT;
											rangeCircle = new Rectangle(target.getLayoutX(), target.getLayoutY() - radius,GRID_WIDTH, length);
											break;

										}
										case "catapult": {	
											rangeCircle = new Circle(towers.get(getTowerIndex(coord)).getRange());
											Circle ringCircle = new Circle(towers.get(getTowerIndex(coord)).getMinRange());
											rangeCircle.setLayoutX(target.getLayoutX() + GRID_WIDTH / 2);
											rangeCircle.setLayoutY(target.getLayoutY() + GRID_HEIGHT / 2);
											ringCircle.setLayoutY(target.getLayoutY() + GRID_HEIGHT / 2);
											ringCircle.setLayoutX(target.getLayoutX() + GRID_WIDTH / 2);
											ringCircle.setFill(Color.TRANSPARENT);
											rangeCircle = rangeCircle.subtract(rangeCircle, ringCircle);
											break;
										}	
										case "laserTower": 
										case "iceTower":{
											rangeCircle = new Circle(towers.get(getTowerIndex(coord)).getRange());
											rangeCircle.setLayoutX(target.getLayoutX() + GRID_WIDTH / 2);
											rangeCircle.setLayoutY(target.getLayoutY() + GRID_HEIGHT / 2);
											break;
										}
									}

									rangeCircle.setOpacity(0.3);
									rangeCircle.setFill(Color.RED);
									paneArena.getChildren().add(rangeCircle);

									initEachInvisibleLabel();
									invisibleLabel.setLayoutY(target.getLayoutY());
									invisibleLabel.setLayoutX(target.getLayoutX());
									paneArena.getChildren().add(invisibleLabel);
									circleShown = true;								
									// Pop-up info

									String towername = Helper.space(target.getId());
									int[] coords = { (int) target.getLayoutX(), (int) target.getLayoutY() };
									String towerStats = "Tower: " + towername + "\n" + "Tower cost: " + Integer.toString(Helper.returnTower(coords, towers).getTowerCost())+ "\n"
										+ "Upgrade Cost: " + Integer.toString(Helper.returnTower(coords, towers).getUpgradeCost())+ "\n"
										+ "Power: " + Integer.toString(Helper.returnTower(coords, towers).getPower())+ "\n" 
										+ "Range: " + Integer.toString(Helper.returnTower(coords, towers).getRange())+ "\n"
										+ "Current state: " + Helper.returnTower(coords, towers).getTowerState().name()+ "\n";

									towerInfo.setText(towerStats);	                       		
									invisibleLabel.setTooltip(towerInfo); 
									
									//towerInfo.show(invisibleLabel, invisibleLabel.getLayoutX(),invisibleLabel.getLayoutY());
								}
							}
						}
					});
					invisibleLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							paneArena.getChildren().remove(rangeCircle);
							paneArena.getChildren().remove(invisibleLabel);
							circleShown = false;
							//System.out.println("Invisible label exit called");
						}
					});
					invisibleLabel.setOnMouseClicked(new EventHandler<MouseEvent>(){
					   	public void handle(MouseEvent event) {
							numClicks++;
							clicked = true;
							if(clicked == true && numClicks < 2) {
								System.out.println("Clicked1:" + clicked);
								ToolBar toolbar = new ToolBar();
								Button destroyButton = new Button("Destroy Tower");
								Button upgradeButton = new Button("Upgrade Tower");
								toolbar.getItems().addAll(destroyButton, upgradeButton);            				
								VBox vbox = new VBox(toolbar);
								Scene scene = new Scene(vbox);
								Stage stage = new Stage();
								stage.setScene(scene);
								stage.show();    
								System.out.println("TOWER SIZE BEFORRE: " + towers.size());				
								destroyButton.setOnAction(new EventHandler<ActionEvent>() {
							   		public void handle(ActionEvent e) {   
										int [] towerCoords = {(int)invisibleLabel.getLayoutX(),(int)invisibleLabel.getLayoutY()};
										int towerIndex = getTowerIndex(towerCoords);
										int []gridCoord = getTowerCoords(towerCoords);

										grids[gridCoord[0]][gridCoord[1]].setGraphic(null);
										grids[gridCoord[0]][gridCoord[1]].setText("wassup");
										towers.remove(towerIndex);
										//System.out.println(towers.size());
										destroyButton.setDisable(true);
										upgradeButton.setDisable(true);         	
							   		}
								});
								upgradeButton.setOnAction(new EventHandler<ActionEvent>() {
							   		public void handle(ActionEvent e) {
								   		int[] towerCoords = {(int) invisibleLabel.getLayoutX(), (int) invisibleLabel.getLayoutY()};
								   		System.out.println("xUpgrade: " + towerCoords[0]);
								   		System.out.println("yUpgrade: " + towerCoords[1]);
								   		for(Tower a : towers) {
									   		int[]aCoor = a.getCoord();
									   		int aX = aCoor[0];
									   		int aY = aCoor[1];
									   		if(towerCoords[0] == aX && towerCoords[1] == aY) {
											   	if(resourcesAmount >= a.getUpgradeCost() && upgraded == false) {
												   	a.upgradeTower(true);
												   	resourcesAmount -= a.getUpgradeCost();
												   	updateResourceText();
									   				System.out.println(a.getTowerType() + " is being upgraded.");
												   	upgraded = true;
												   	//rangeCircle.setRadius(a.getRange());
											   	}
											   	else if (resourcesAmount < a.getUpgradeCost()) {
									   				System.out.println("not enough resources to upgrade " + a.getTowerType());		
											   	}
									   		}
								   		}
								   		upgraded = false;
							   		}
								});
								stage.setOnHidden(new EventHandler<WindowEvent>() {
									public void handle(WindowEvent a) {
										clicked = false;
										numClicks = 0;
										System.out.println("Clicked2:" + clicked);
								   	}
								});
							}				
						} 
					});
				}
			} // inner for-loop
		} // outer for-loop
	} // gameEvents

}// MyController class

class DragEventHandler implements EventHandler<MouseEvent> {
	private Label source;

	/**
	 * 
	 * @param e
	 */
	public DragEventHandler(Label e) {
		source = e;
	}

	/**
	 * 
	 */
	@Override
	public void handle(MouseEvent event) {
		Dragboard db = source.startDragAndDrop(TransferMode.ANY);

		ClipboardContent content = new ClipboardContent();
		content.putString(source.getId());
		db.setContent(content);

		String imageURL = Helper.preProcessing(db.getString());
		db.setDragView(new Image(imageURL, 30.0, 30.0, true, true));
		event.consume();
	}
}

class DragDroppedEventHandler implements EventHandler<DragEvent> {
	@Override
	public void handle(DragEvent event) {
		System.out.println("xx");
		Dragboard db = event.getDragboard();
		boolean success = false;
		// System.out.println(db.getString());

		if (db.hasString()) {
			((Label) event.getGestureTarget()).setText(db.getString());
			success = true;
		}
		event.setDropCompleted(success);
		event.consume();

	}
}