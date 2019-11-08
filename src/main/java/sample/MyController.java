package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.fxml.FXML;
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

public class MyController {
<<<<<<< HEAD
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
	private static int[] startCoord = { 0, 0 };
	Random r = new Random();
	int low = 1;
	int high = 3;

	ArrayList<Monster> monsterList = new ArrayList<Monster>();

	private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; // the grids on arena
	private int x = -1, y = 0; // where is my monster

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
		newLabel.setText("*");
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
				paneArena.getChildren().addAll(newLabel);
			}

		setDragAndDrop();
	}

	public void monsterGenerate() {
		System.out.println("monsterGenerate");
		int result = r.nextInt(high - low) + low;

		switch (result) {
		case 1:
			monsterList.add(new Fox(startCoord, false));
			break;
		case 2:
			monsterList.add(new Unicorn(startCoord, false));
			break;
		case 3:
			monsterList.add(new Penguin(startCoord, false));
			break;

		default:
			monsterList.add(new Fox(startCoord, false));

		}

	}

	private void Move(int op, int x, int y, int spaceLeft, int monsterCount) {

		System.out.println("y: " + y + "x: " + x + " spaceleft : " + spaceLeft + "op " + op);
		if (spaceLeft == 0) {

			int[] newCoord = { y, x };
			grids[y][x].setText("M");
			monsterList.get(monsterCount).setYX(newCoord);

			return;

		}

		if (op == 0) {

			if (y == MAX_V_NUM_GRID - 1) // y ==11
				Move(1, x, y, spaceLeft, monsterCount);

			else
				Move(0, x, y + 1, spaceLeft - 1, monsterCount);

		}

		else if (op == 1) {

			if (grids[y][x + 1].getBackground().getFills().get(0).getFill() == Color.GREEN)

			{

				if (y == MAX_V_NUM_GRID - 1)
					Move(2, x, y, spaceLeft, monsterCount);

				else if (y == 0 && x != 0) {
					Move(0, x, y, spaceLeft, monsterCount);
				}

			} else
				Move(1, x + 1, y, spaceLeft - 1, monsterCount);

		}

		else if (op == 2) {
			if (y == 0)
				Move(1, x, y, spaceLeft, monsterCount);

			else
				Move(2, x, y - 1, spaceLeft - 1, monsterCount);

		}

	}

	@FXML
	private void nextFrame() {

		for (int i = 0; i < monsterList.size(); i++) {

			boolean down = false;
			boolean right = false;

			System.out.println("RUNS");

			int movementSpeed = monsterList.get(i).getMovementSpeed();
			int x = monsterList.get(i).getX();
			int y = monsterList.get(i).getY();

			System.out.println("x: " + x);
			System.out.println("y: " + y);

			grids[y][x].setText("");

			if (x % 4 == 0)
				down = true;
			else
				down = false;

			if (grids[y][x + 1].getBackground().getFills().get(0).getFill() == Color.GREEN) {
				right = false;

			} else
				right = true;

			if (right)

				Move(1, x, y, movementSpeed, i);

			else

			{
				if (down)
					Move(0, x, y, movementSpeed, i);
				else
					Move(2, x, y, movementSpeed, i);

			}

		}

		if (grids[0][0].getText() == "" && nextFrameCounter % 20 == 0) {

			monsterGenerate();

			grids[0][0].setText("M");
		}

		System.out.println("monsterListsize " + monsterList.size());

		/*
		 * if (y == MAX_V_NUM_GRID - 1) return; grids[y++][x].setText("");
		 * grids[y][x].setText("M");
		 */

		nextFrameCounter++;
		System.out.println("nextFrameCounter" + nextFrameCounter);
	}

	/**
	 * A function that demo how drag and drop works
	 */
	private void setDragAndDrop() {
		Label target = grids[3][3];
		target.setText("Drop\nHere");
		Label source1 = labelBasicTower;
		Label source2 = labelIceTower;
		source1.setOnDragDetected(new DragEventHandler(source1));
		source2.setOnDragDetected(new DragEventHandler(source2));

		target.setOnDragDropped(new DragDroppedEventHandler());

		// well, you can also write anonymous class or even lambda
		// Anonymous class
		target.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data is dragged over the target */
				System.out.println("onDragOver");

				/*
				 * accept it only if it is not dragged from the same node and if it has a string
				 * data
				 */
				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
					/* allow for both copying and moving, whatever user chooses */
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				event.consume();
			}
		});

		target.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				System.out.println("onDragEntered");
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != target && event.getDragboard().hasString()) {
					target.setStyle("-fx-border-color: blue;");
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
=======
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
                paneArena.getChildren().addAll(newLabel);
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
        Label target = grids[3][3];
        target.setText("Drop\nHere");
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        source1.setOnDragDetected(new DragEventHandler(source1));
        source2.setOnDragDetected(new DragEventHandler(source2));

        target.setOnDragDropped(new DragDroppedEventHandler());

        //well, you can also write anonymous class or even lambda
        //Anonymous class
        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    target.setStyle("-fx-border-color: blue;");
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
>>>>>>> 78d92e599460151bb45c26a781661676561d6b35
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
		content.putString(source.getText());
		db.setContent(content);

		event.consume();
	}
}

class DragDroppedEventHandler implements EventHandler<DragEvent> {
	@Override
	public void handle(DragEvent event) {
		System.out.println("xx");
		Dragboard db = event.getDragboard();
		boolean success = false;
		System.out.println(db.getString());
		if (db.hasString()) {
			((Label) event.getGestureTarget()).setText(db.getString());
			success = true;
		}
		event.setDropCompleted(success);
		event.consume();

	}
}
