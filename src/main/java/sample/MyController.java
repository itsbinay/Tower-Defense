package sample;
import sample.Helper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;

import java.io.File;


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

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    
    private int x = -1, y = 0; //where is my monster
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
            return; //created already
        for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) { 
                Label newLabel = new Label();
                if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1))
                    newLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                else
                    newLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
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

        setDragAndDrop();
    }

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
    }

    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop() {
         // where on the x by y grid to put the text "Drop Here"
//        target.setText("Drop\nHere");
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

            	           Dragboard db = event.getDragboard();
            	           String id = db.getString();
            	           String imageName = Helper.preProcessing(id);
            	           Image towerImage = new Image(imageName, 30.0, 30.0, true,true);
            	           ImageView towerImageView = new ImageView();
            	           towerImageView.setImage(towerImage);
            	           if(target.getGraphic()==null) {	//If there is no image there currently
            	        	   target.setGraphic(towerImageView);
            	           }else {
            	        	   System.out.println("Cannot place tower here.");
            	           }
            	           event.setDropCompleted(true);
            	           event.consume();
            	     }        	                        		
            	});
       
        //setOnDragOver: allows controlling what happens when something is dragged over the node.
        //well, you can also write anonymous class or even lambda
        //Anonymous class
            	target.setOnDragOver(new EventHandler <DragEvent>() {
            		public void handle(DragEvent event) {
            			System.out.println("onDragOver");
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
            }
        }      
    }
}

class DragEventHandler implements EventHandler<MouseEvent> {
    private Label source;
    public DragEventHandler(Label e) {
        source = e;
    }
    @Override
    public void handle (MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(source.getId());
//        content.putString(source.getText());
//        db.setContent(content);
        db.setContent(content);
        
        String imageURL = Helper.preProcessing(db.getString());
        db.setDragView(new Image(imageURL, 30.0, 30.0, true,true));
        event.consume();
    }
}

class DragDroppedEventHandler implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent event) {
        System.out.println("xx");
        Dragboard db = event.getDragboard();
        boolean success = false;
        //System.out.println(db.getString());
        
        if (db.hasString()) {
            ((Label)event.getGestureTarget()).setText(db.getString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();

    }
}
