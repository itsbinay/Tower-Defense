package sample;

import tower.*;
import sample.Helper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.List;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import monster.Fox;
import monster.Monster;
import monster.Penguin;
import monster.Unicorn;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;

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

	private static final int ARENA_WIDTH = 480;
	private static final int ARENA_HEIGHT = 480;
	private static final int GRID_WIDTH = 40;
	private static final int GRID_HEIGHT = 40;
	private static final int MAX_H_NUM_GRID = 12;
	private static final int MAX_V_NUM_GRID = 12;
	
	private static int monsterCounter = 0;
	private static int nextFrameCounter = 0;
	private static int[] startCoord = {0,0};
	Random r = new Random();
	int low = 1;
	int high = 3;
	
	private static int speedIncrease = 0;
	private static int numOfFrames = 0;
	private static int bonusHp = 50;
	ArrayList<Monster> monsterList = new ArrayList<Monster>();
	
	private static int resources = 0;

	private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; // the grids on arena

    private int x = -1, y = 0; // where is my monster
    
    private List<Tower> towers = new ArrayList<>();
    
    private Circle rangeCircle = new Circle();
    private Label invisibleLabel = new Label();
    private boolean rangeShown=false;
    
    private void initInvisibleLabel() {
    	invisibleLabel.setMinWidth(GRID_WIDTH);
    	invisibleLabel.setMaxWidth(GRID_WIDTH);
    	invisibleLabel.setMinHeight(GRID_HEIGHT);
    	invisibleLabel.setMinHeight(GRID_HEIGHT);
    	invisibleLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
    }
    
    
    /**
     * Returns the index of the tower in the towers list, given the coordinate
     * if tower doesn't exist 0 is returned 
     * 
     * @param coord The Coordinate of the label
     * @return	the index of where the Tower is
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
        if (grids[0][0] != null)
            return; // created already
        for (int i = 0; i < MAX_V_NUM_GRID; i++)
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

        setDragAndDrop();
        //mouseEnterExit();
    }

	public void monsterGenerate() {
		System.out.println("monsterGenerate");
		int result = r.nextInt(high - low+1) + low+1;

		switch (result) {
		case 1:monsterList.add(new Fox(startCoord, false));break;
		case 2:monsterList.add(new Unicorn(startCoord, false));break;
		case 3:monsterList.add(new Penguin(startCoord, false));break;
		default:monsterList.add(new Fox(startCoord, false));break;
		}
	}

	private void Move(int op, int x, int y, int spaceLeft, int monsterCount) {

		System.out.println("y: " + y + "x: " + x + " spaceleft : " + spaceLeft + "op " + op);
		if (spaceLeft < 1) {

			int[] newCoord = { y, x };

			monsterList.get(monsterCount).setYX(newCoord);
			
			String imageName = monsterList.get(monsterCount).getImg();
			Image monsterImage = new Image(imageName, 30.0, 30.0, true, true);
			ImageView monsterImageView = new ImageView();
			monsterImageView.setImage(monsterImage);
				
			System.out.println("monstercount "+ monsterCount + " img "+ monsterList.get(monsterCount).getImg());
			grids[y][x].setGraphic(monsterImageView);

			return;
		}
        switch(op){
            case 0:{
                if (y == MAX_V_NUM_GRID - 1){ // y ==11
                    Move(1, x, y, spaceLeft, monsterCount);
                }else{
                    Move(0, x, y + 1, spaceLeft - 1, monsterCount);
                }
                break;
            }
            case 1:{
                if (grids[y][x + 1].getBackground().getFills().get(0).getFill() == Color.GREEN){
                    if (y == MAX_V_NUM_GRID - 1){
                        Move(2, x, y, spaceLeft, monsterCount);
                    }else if (y == 0 && x != 0) {
                        Move(0, x, y, spaceLeft, monsterCount);
                    }
                } else {
                    Move(1, x + 1, y, spaceLeft - 1, monsterCount);
                }
                break;
            }
            case 2:{
                if (y == 0){
                    Move(1, x, y, spaceLeft, monsterCount);
                }else{
                    Move(2, x, y - 1, spaceLeft - 1, monsterCount);
                }
                break;
            }
            default:break;
        }
	}
	/**
	 * Debugging tool to list out all Monster State
	 */
	void listAllMonster() {
		if(monsterList.size()==0)return;
		System.out.println("Listing out All Monsters");
		for(int i=0;i<monsterList.size();i++) {
			int[] coord = monsterList.get(i).getCoord();
			System.out.println(i+":"+coord[0]+","+coord[1]+" "+monsterList.get(i).getHp());
		}
		System.out.println();
	}
	/**
	 * Debugging tool to list out all Tower State
	 */
	void listAllTower() {
		for(int i=0;i<towers.size();i++) {
			System.out.println("Tower("+i+") State:"+towers.get(i).getStateStr()+" Timer:"+towers.get(i).getCooldown()+" Coord:"+towers.get(i).getCoord()[0]+","+towers.get(i).getCoord()[1]);
		}
		System.out.println("");
	}
	
	void updateAllTowerState() {
		for(int i=0;i<towers.size();i++)towers.get(i).updateTowerState();
	}
	void TowerAttackMonster() {
		System.out.println("Monster size:"+monsterList.size()+" Tower size:"+towers.size());
		System.out.println("");
		
		for(int i=0;i<monsterList.size();i++) {
			for(int j=0;j<towers.size();j++) {
				//if(towers.get(j).getStateStr()!="READY")return;
				System.out.println("Monster Coord:"+monsterList.get(i).getCoord()[0]+","+monsterList.get(i).getCoord()[1]);
				System.out.println("Tower Coord:"+towers.get(j).getCoord()[0]+","+towers.get(j).getCoord()[1]);

				if(towers.get(j).isInRange(monsterList.get(i).getCoord())) {
					System.out.println("Monster ("+i+") In Range of Tower ("+j+")");
					
					switch(towers.get(j).getTowerType()) {
						case "basicTower":{
							monsterList.get(i).setHp(towers.get(j).attack(monsterList.get(i).getHp()));
							break;
						}
						case "iceTower":{
							if(towers.get(j).getStateStr()=="Ready") {
								System.out.println("IceTower Shoot");
								monsterList.get(i).setHp(towers.get(j).attack(monsterList.get(i).getHp()));
								monsterList.get(i).setFrozen();
							}
							break;
						}
						case "catapult":{
							monsterList.get(i).setHp(towers.get(j).attack(monsterList.get(i).getHp()));
							break;
						}
						case "laserTower":{
							monsterList.get(i).setHp(towers.get(j).attack(monsterList.get(i).getHp()));
							break;
						}
					}
					monsterList.get(i).setHp(towers.get(j).attack(monsterList.get(i).getHp()));
					
					System.out.println("Tower("+j+") State:"+towers.get(j).getStateStr()+" Timer:"+towers.get(j).getCooldown()+" Coord:"+towers.get(j).getCoord()[0]+","+towers.get(j).getCoord()[1]);
				}
				System.out.println("");
			}
		}
		updateAllTowerState();
	}
	private void MonsterFSM() {
		numOfFrames++;

		if (numOfFrames % 10 == 0 && numOfFrames != 0) {
			speedIncrease++;
			speedIncrease = (int) Math.pow(2, speedIncrease);
		}

		for (int i = 0; i < monsterList.size(); i++) {

			if (monsterList.get(i).getFrozen() == true)
				monsterList.get(i).reduceSpeed();

			boolean down = false;
			boolean right = false;

			int movementSpeed = monsterList.get(i).getMovementSpeed();
			int x = monsterList.get(i).getX();
			int y = monsterList.get(i).getY();

			System.out.println("x: " + x);
			System.out.println("y: " + y);

			grids[y][x].setGraphic(null);

			if (x % 4 == 0)down = true;
			else down = false;

			if (grids[y][x + 1].getBackground().getFills().get(0).getFill() == Color.GREEN) {
				right = false;
			} else{
				right = true;
			}
			if (right)Move(1, x, y, movementSpeed + speedIncrease, i);
			else{
				if (down)Move(0, x, y, movementSpeed + speedIncrease, i);
				else Move(2, x, y, movementSpeed + speedIncrease, i);
			}

			if (monsterList.get(i).getFrozen() == true)
				monsterList.get(i).unFreeze();

		}

		if (grids[0][0].getGraphic() == null && nextFrameCounter % 5 == 0) {

			monsterGenerate();
			if (numOfFrames % 10 == 0 && numOfFrames != 0) {
				int currHp = monsterList.get(monsterList.size()-1).getHp();
				int newHp = currHp + bonusHp;
				bonusHp = bonusHp + 50;

				monsterList.get(monsterList.size()-1).setHp(newHp);

			}
			
			String imageName = monsterList.get(monsterList.size()-1).getImg();
			Image monsterImage = new Image(imageName, 30.0, 30.0, true, true);
			ImageView monsterImageView = new ImageView();
			monsterImageView.setImage(monsterImage);
				grids[0][0].setGraphic(monsterImageView);
			
		}

		nextFrameCounter++;
	}
	
	private void clearDeadMonster() {
		for(int i=0;i<monsterList.size();i++) {
			if(monsterList.get(i).getHp()<=0) {
				Image collisionImage = new Image("collision.png", 30.0, 30.0, true, true);
				ImageView collisionImageView = new ImageView();
				collisionImageView.setImage(collisionImage);
				grids[monsterList.get(i).getY()][monsterList.get(i).getX()].setGraphic(collisionImageView);
				monsterList.remove(i);
			}
		}
	}
	@FXML
	private void nextFrame() {
		MonsterFSM();
		listAllMonster();
		listAllTower();
		TowerAttackMonster();
		clearDeadMonster();
	}
    
	

    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop() {
        // where on the x by y grid to put the text "Drop Here"
        // target.setText("Drop\nHere");
        for (int i = 0; i < MAX_V_NUM_GRID; i++) {
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
                if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1))
                    continue;
                else {
                    Label target = grids[i][j];
                    Label source1 = labelBasicTower;
                    Label source2 = labelIceTower;
                    Label source3 = labelCatapult;
                    Label source4 = labelLaserTower;
                    source1.setOnDragDetected(new DragEventHandler(source1)); // when dragging source1, do something,
                                                                              // source1 is now source in
                                                                              // DragEventHandler class
                    source2.setOnDragDetected(new DragEventHandler(source2));
                    source3.setOnDragDetected(new DragEventHandler(source3));
                    source4.setOnDragDetected(new DragEventHandler(source4));
                    // mouseEnterExit();
                    target.setOnDragDropped(new DragDroppedEventHandler() {
                        public void handle(DragEvent event) {

                            Dragboard db = event.getDragboard();
                            String id = db.getString();
                            String imageName = Helper.preProcessing(id);

                            Image towerImage = new Image(imageName, 30.0, 30.0, true, true);
                            ImageView towerImageView = new ImageView();
                            towerImageView.setImage(towerImage);

                            int[] coord = { (int) target.getLayoutX(), (int) target.getLayoutY() };
                            if (target.getGraphic() == null) { // If there is no image there currently
                                target.setGraphic(towerImageView);
                                String towerName = Helper.getTowerName(imageName); // This will give me the towerName
                                switch (towerName) {// Switch-case to instantiate a Tower object accordingly
                                case "basicTower":
                                    towers.add(new basicTower(coord));
                                    break;
                                case "iceTower":
                                    towers.add(new IceTower(coord));
                                    break;
                                case "catapult":
                                    towers.add(new Catapult(coord));
                                    break;
                                case "laserTower":
                                    towers.add(new laserTower(coord));
                                    break;
                                }

                            }
                            event.setDropCompleted(true);
                            event.consume();
                            
                        }
                    });
                    
                    target.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    	@Override
                        public void handle(MouseEvent event) {
                    		target.setPickOnBounds(true);
                            if (target.getGraphic() == null) {
                               // System.out.println("There is no tower");
                            } else {
                            	if(!rangeShown) {
                                    System.out.println("There is tower");
                                    int[] coord = { (int) target.getLayoutX(), (int) target.getLayoutY() };
                                    System.out.println("This Tower's range: " + towers.get(getTowerIndex(coord)).getRange());
                                    rangeCircle = new Circle(towers.get(getTowerIndex(coord)).getRange());
                                    
                                    rangeCircle.setLayoutX(target.getLayoutX() + GRID_WIDTH / 2);
                                    rangeCircle.setLayoutY(target.getLayoutY() + GRID_HEIGHT / 2);
                                    rangeCircle.setOpacity(0.6);
                                    rangeCircle.setFill(Color.RED);
                                    paneArena.getChildren().add(rangeCircle);
                                    initInvisibleLabel();
                                    invisibleLabel.setLayoutX(target.getLayoutX());
                                    invisibleLabel.setLayoutY(target.getLayoutY());
                                    paneArena.getChildren().add(invisibleLabel);
                                    rangeShown=true;
                            	}
                            }
                    	}
                    });
    
                    // setOnDragOver: allows controlling what happens when something is dragged over
                    // the node.
                    // well, you can also write anonymous class or even lambda
                    // Anonymous class
                    target.setOnDragOver(new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            //System.out.println("onDragOver");
                            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                                /* allow for both copying and moving, whatever user chooses */
                                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                            }
                            event.consume();
                            // Transfer modes define the type of transfer that
                            // happens between the gesture source and gesture target.
                            // Available transfer modes include COPY, MOVE, and LINK
                            // The getDragboard method gives the Dragboard object which contains the files
                            // being transferred.
                            // Gesture source is the object that started drag and drop operation.
                        }
                    });

                    // When the mouse is dragged into the boundaries of potential drop target, the
                    // potential target gets a DRAG_ENTERED event.
                    // When the mouse is dragged outside of the potential target's bounds, it gets a
                    // DRAG_EXITED event.
                    target.setOnDragEntered(new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            /* the drag-and-drop gesture entered the target */
                            //System.out.println("onDragEntered");
                            /* show to the user that it is an actual gesture target */
                            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                                target.setStyle("-fx-border-color: red;");
                            }
                            event.consume();
                        }
                    });

                    // lambda
                    target.setOnDragExited((event) -> {
                        /* mouse moved away, remove the graphical cues */
                        target.setStyle("-fx-border-color: black;");
                        System.out.println("Exit");
                        event.consume();
                    });
                }

            }
        }
        invisibleLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent event) {
        		paneArena.getChildren().remove(invisibleLabel);
        		paneArena.getChildren().remove(rangeCircle);
        		System.out.println("Invisible label exit called");
        		rangeShown=false;
        	}
        });
    }
}

class DragEventHandler implements EventHandler<MouseEvent> {
	private Label source;

	public DragEventHandler(Label e) {
		source = e;
	}

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