package sample;

import tower.*;
import sample.Helper;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
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
    
    @FXML
    private Label money;

    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 480;
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;    
    private static final int catapultCost = 80;
    private static final int basicCost = 60;
    private static final int laserCost = 180;
    private static final int iceCost = 90;
    
    int amtRrs = 200;
    int bT = 0;
	int c = 0;
	int lT = 0;
	int iT = 0;
	int sumbT = 0;
	int sumC = 0;
	int sumiT = 0;
	int sumlT = 0;
	int sumTotal = 0;
	
    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; // the grids on arena
    private List<Tower> towers = new ArrayList<>();
    private int x = -1, y = 0; // where is my monster
    private Circle rangeCircle = new Circle();
    //List<Circle> rangeCircle = new ArrayList<>();
    private Label invisibleLabel = new Label();
    private boolean circleShown = false;
    //List<Label> invisibleLabel = new ArrayList<>();
    //HashMap<String, Label> invLabels = new HashMap<String, Label>();
    
    /**
     * 
     * @param coord The Coordinate of the s
     * @return
     */
    private int getTowerIndex(int [] coord) {
    	for(int i=0;i<towers.size();i++) {
    		if(towers.get(i).getCoord()[0]==coord[0] && towers.get(i).getCoord()[1]==coord[1])
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
                // map will be empty without tiles
            }

        gameEvents();
       
    }
    private void updateResources() {
    	if(!towers.isEmpty()) {
    		sumbT = bT * basicCost;
    		sumC = c * catapultCost;
    		sumlT = lT * laserCost;
    		sumiT = iT * iceCost;
    		
    		bT = 0;
    		c = 0;
    		lT = 0;
    		iT = 0;
    		
    		sumTotal = sumbT + sumC + sumlT + sumiT;
    		if(amtRrs > 0)
    			amtRrs -= sumTotal;
    		else amtRrs = 0;
    		money.setText("Money Left: " + Integer.toString(amtRrs));
    	}
    	else
    		money.setText("Money Left: " + Integer.toString(amtRrs));    		
    }
   /**
    * A function that allows monster to move forward
    */
    @FXML
    private void nextFrame() {

        if (x == -1) {
            grids[0][0].setText("M");
            x = 0;
            return;
        }
        if (y == MAX_V_NUM_GRID - 1)
            return;
        grids[y++][x].setText("");
        grids[y][x].setText("M");
        
        /* add after pulling from monster */
        //updateResources();
    }
   
    

    public int [] getTowerCoords(int [] coord)
    {
    	int[] returnCoords = {0,0};
    	for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
            	if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1))
                    continue;
                else {
                	if(grids[i][j].getLayoutX() == coord[0] && grids[i][j].getLayoutY() == coord[0]) {
                		returnCoords[0] = i;
                		returnCoords[1] = j; 
                		System.out.println("getTowerCoord-i:"+i+" j:"+j);
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
    	invisibleLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
   
    }
    /**
     * A function that allows dragging of Towers 
     * 
     */
    private void gameEvents() {
        // where on the x by y grid to put the text "Drop Here"
        // target.setText("Drop\nHere");
        for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {	
            	if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1)) continue;
              	else {
            	Label target = grids[i][j];
            	Label source1 = labelBasicTower;
            	Label source2 = labelIceTower;
            	Label source3 = labelCatapult;
            	Label source4 = labelLaserTower;
            	source1.setOnDragDetected(new DragEventHandler(source1)); // when dragging source1, do something, source1 is now source in DragEventHandler class
            	source2.setOnDragDetected(new DragEventHandler(source2));
            	source3.setOnDragDetected(new DragEventHandler(source3));
            	source4.setOnDragDetected(new DragEventHandler(source4));
        
            	target.setOnDragDropped(new DragDroppedEventHandler() {
            		public void handle(DragEvent event) {
            			//if(flagTower[i][j]== false) {
            	           Dragboard db = event.getDragboard();
            	           String id = db.getString();
            			//System.out.println(Helper.preProcessing(imageName)); 
            	           String imageName = Helper.preProcessing(id);
            	           Image towerImage = new Image(imageName, 30.0, 30.0, true,true);
            	           ImageView towerImageView = new ImageView();
                           towerImageView.setImage(towerImage);
                           
                           int[] coord = {(int) target.getLayoutX(), (int) target.getLayoutY()};
            	           if(target.getGraphic() == null) {
//                               target.setGraphic(towerImageView);
                                String towerName = Helper.getTowerName(imageName);	//This will give me the towerName
//                               target.setId(towerName); /**/
                                switch (towerName) {//Switch-case to instantiate a Tower object accordingly
                                case "basicTower":
                                	if(amtRrs < basicCost || amtRrs == 0) {
                                	   Alert alert = new Alert(AlertType.ERROR);
                     	        	   alert.setTitle("Error");
                     	        	   alert.setHeaderText("Cannot build Basic Tower");
                     	        	   alert.setContentText("Not enough money!");
                     	        	   alert.show();
                                	}
                                	else {
                                       target.setGraphic(towerImageView);
                                       target.setId(towerName); /**/
                                	   towers.add(new basicTower(coord));
                                	   bT++;
                                	}
                                    break;
                                case "iceTower":
                                	if(amtRrs < iceCost || amtRrs == 0) {
                                 	   Alert alert = new Alert(AlertType.ERROR);
                      	        	   alert.setTitle("Error");
                      	        	   alert.setHeaderText("Cannot build Ice Tower");
                      	        	   alert.setContentText("Not enough money!");
                      	        	   alert.show();
                                 	}
                                	else {
                                	   target.setGraphic(towerImageView);
                                       target.setId(towerName); /**/
                                       towers.add(new IceTower(coord));
                                       iT++;
                                	}
                                    break;
                                case "catapult":
                                	if(amtRrs < catapultCost || amtRrs == 0) {
                                 	   Alert alert = new Alert(AlertType.ERROR);
                      	        	   alert.setTitle("Error");
                      	        	   alert.setHeaderText("Cannot build Catapult");
                      	        	   alert.setContentText("Not enough money!");
                      	        	   alert.show();
                                 	}
                                	else {
                                	   target.setGraphic(towerImageView);
                                       target.setId(towerName); /**/
                                       towers.add(new Catapult(coord));
                                       c++;
                                	}
                                    break;
                                case "laserTower":
                                	if(amtRrs < laserCost || amtRrs == 0) {
                                  	   Alert alert = new Alert(AlertType.ERROR);
                       	        	   alert.setTitle("Error");
                       	        	   alert.setHeaderText("Cannot build Laser Tower");
                       	        	   alert.setContentText("Not enough money!");
                       	        	   alert.show();
                                  	}
                                 	else {
                                 	   target.setGraphic(towerImageView);
                                       target.setId(towerName); /**/
                                       towers.add(new laserTower(coord));
                                       lT++;
                                 	}
                                    break;
                                }
            	           } else {
            	        	   Alert alert = new Alert(AlertType.ERROR);
            	        	   alert.setTitle("Error");
            	        	   alert.setHeaderText("Cannot place tower here");
            	        	   alert.setContentText("A tower is already built here");
            	        	   alert.show();
            	           }
            	           event.setDropCompleted(true);
            	           
                           event.consume();
                           System.out.println(towers.get(towers.size() - 1).getCoord()[0]);
                           updateResources();            	          
            	     }        	                        		
            	});
       
        //setOnDragOver: allows controlling what happens when something is dragged over the node.
        //well, you can also write anonymous class or even lambda
        //Anonymous class
            	target.setOnDragOver(new EventHandler <DragEvent>() {
            		public void handle(DragEvent event) {
                /* data is dragged over the target */
            			System.out.println("onDragOver");
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
            			if (event.getGestureSource() != target &&                	
            					event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
            				    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            			}
            			event.consume();
                // Transfer modes define the type of transfer that 
                // happens between the gesture source and gesture target. 
                // Available transfer modes include COPY, MOVE, and LINK
                // The getDragboard method gives the Dragboard object which contains the files being transferred.             
                // Gesture source is the object that started drag and drop operation.
            		}
            	});
        
        // When the mouse is dragged into the boundaries of potential drop target, the potential target gets a DRAG_ENTERED event. 
        // When the mouse is dragged outside of the potential target's bounds, it gets a DRAG_EXITED event.    
            	target.setOnDragEntered(new EventHandler <DragEvent>() {
            		
            		public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
            			System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
            			if (event.getGestureSource() != target &&
            					event.getDragboard().hasString()) {
            				target.setStyle("-fx-border-color: red;");                   
            			}
            			event.consume();
            		}
            	});
        
        //lambda
            	target.setOnDragExited((event) -> {
            		/* mouse moved away, remove the graphical cues */
            		target.setStyle("-fx-border-color: black;");
            		System.out.println("Exit");
            		event.consume();
            	});
            	
            	Tooltip towerInfo = new Tooltip();
            	
            	
            	target.setOnMouseEntered(new EventHandler<MouseEvent>() {
                	@Override
                    public void handle(MouseEvent event) {
                		target.setPickOnBounds(true);
                        if (target.getGraphic() == null) {
                            System.out.println("There is no tower");
                        } else {
                        	if(!circleShown) {
                        		System.out.println("There is tower");
                            
                            // Circle
                        		
                        		int[] coord = { (int) target.getLayoutX(), (int) target.getLayoutY() };
                        		System.out.println("This Tower's range: " + towers.get(getTowerIndex(coord)).getRange());                            
                        		rangeCircle = new Circle(towers.get(getTowerIndex(coord)).getRange());
                        		rangeCircle.setLayoutX(target.getLayoutX() + GRID_WIDTH / 2);
                        		rangeCircle.setLayoutY(target.getLayoutY() + GRID_HEIGHT / 2);
                        		rangeCircle.setOpacity(0.6);
                        		rangeCircle.setFill(Color.RED);
                        		rangeCircle.setId("Circlerange");
                                paneArena.getChildren().add(rangeCircle);                            
                        		initEachInvisibleLabel();                                    
                        		invisibleLabel.setLayoutX(target.getLayoutX());
                        		invisibleLabel.setLayoutY(target.getLayoutY());                           
                        		paneArena.getChildren().add(invisibleLabel);                       
                        		circleShown = true;
                            
                            // Pop-up info
                        		
                        		String towerName = Helper.space(target.getId());
                        		int[] coords = {(int) target.getLayoutX(), (int) target.getLayoutY()};
                        		String towerStats = "Tower: " + towerName + "\n" + "Tower cost: " + Integer.toString(Helper.returnTower(coords, towers).getTowerCost())+ "\n"
                                		+ "Upgrade Cost: " + Integer.toString(Helper.returnTower(coords, towers).getUpgradeCost())+ "\n"
                                		+ "Power: " + Integer.toString(Helper.returnTower(coords, towers).getPower())+ "\n" 
                                		+ "Range: " + Integer.toString(Helper.returnTower(coords, towers).getRange())+ "\n"
                                		+ "Current state: " + Helper.returnTower(coords, towers).getTowerState().name()+ "\n";
//                        		System.out.println(towerStats);
                        		towerInfo.setText(towerStats);	                       		
                        		invisibleLabel.setTooltip(towerInfo); 
                        		
                        		//towerInfo.show(invisibleLabel, invisibleLabel.getLayoutX(), invisibleLabel.getLayoutY());
                        	}
                        }
                	}
                }); 
            	invisibleLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            		public void handle(MouseEvent event) {           				
            			paneArena.getChildren().remove(invisibleLabel);
            			paneArena.getChildren().remove(rangeCircle);
            			System.out.println("Invisible label exit called");
            			circleShown = false;
            			}
            		});
            	} // big else
              	} // 2nd big for loop
            } //gameEvents    
  
}//MyController class

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
