package gameInterface;

import algorithim.Game;
import algorithim.Player;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class Main extends Application {

	public static final double WIDTH = 1100, HIEGHT = 800;

	Stage primaryStage;

	Scene welcomeScene;
	Scene entryScene;
	Scene playScene;

	static Game game;
	static int gameMode;

	static boolean withMessages;
	// define two players with the game
	Player player1;
	Player player2;// if we are playing against computer it is always player2

	// variable boxes that will be updated in the game
	TextField pointsTextField1;
	TextField pointsTextField2;
	TextField numberOfRoundsTextField;

	// new created fields, revise these
	TextArea statusLabel;
	GridPane gameBoard;

	@Override
	public void start(Stage primaryStage) {

		// set stage
		this.primaryStage = primaryStage;

		designWelcomeScene();

		primaryStage.setTitle("Tic Tac Toe !");
		// primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * function to design welcome scene
	 */
	private void designWelcomeScene() {
		BorderPane pane = new BorderPane();

		VBox vbox = new VBox(40);
		vbox.setPadding(new Insets(100));
		Label title = new Label("Welcome To Tic Tac Toe!");
		Label l1 = new Label("Choose a play mode: ");
		title.getStyleClass().add("title-style");

		// Create a ToggleGroup
		ToggleGroup toggleGroup = new ToggleGroup();

		RadioButton multiplayerRadioButton = new RadioButton("Multiplayer");
		multiplayerRadioButton.getStyleClass().add("radio-button-3");
		RadioButton computerEasyRadioButton = new RadioButton("Computer Easy");
		computerEasyRadioButton.getStyleClass().add("radio-button-3");
		RadioButton computerHardRadioButton = new RadioButton("Computer Hard");
		computerHardRadioButton.getStyleClass().add("radio-button-3");
		// Add buttons to the ToggleGroup

		multiplayerRadioButton.setToggleGroup(toggleGroup);
		computerEasyRadioButton.setToggleGroup(toggleGroup);
		computerHardRadioButton.setToggleGroup(toggleGroup);

		// Set default selection to multiplayer
		multiplayerRadioButton.setSelected(true);

		// Create a Button to go to next scene
		Button toEntryButton = arrowButton("to entry!");
		pane.setCenter(toEntryButton);

		vbox.getChildren().addAll(title, l1, multiplayerRadioButton, computerEasyRadioButton, computerHardRadioButton,
				toEntryButton);
		vbox.setAlignment(Pos.CENTER);

		// Create the scene
		welcomeScene = new Scene(vbox, WIDTH, HIEGHT);
		welcomeScene.getStylesheets().add("styles.css");

		// Set the scene on the stage
		primaryStage.setScene(welcomeScene);

		// program button
		toEntryButton.setOnAction(e -> {
			if (multiplayerRadioButton.isSelected()) {
				gameMode = Game.TWO_PLAYERS;
				designEntrySceneMulti();
			} else if (computerEasyRadioButton.isSelected()) {
				gameMode = Game.RANDOM;
				designEntrySceneComputer();
			} else {
				gameMode = Game.ADVANCED;
				designEntrySceneComputer();
			}

		});

	}

	/**
	 * design entry scene for multiplayer
	 */
	private void designEntrySceneMulti() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));

		GridPane gridPane = new GridPane();
		gridPane.setHgap(40);
		gridPane.setVgap(30);
		gridPane.setPadding(new Insets(30));

		// Labels
		Label player1Label = new Label("Player 1 info");
		Label player2Label = new Label("Palyer 2 info");

		Label player1nameLabel = new Label("Name: ");
		Label player2nameLabel = new Label("Name: ");

		Label player1symbolLabel = new Label("Symbol: ");
		Label player2symbolLabel = new Label("Symbol: ");

		// TextFields
		TextField player1TextField = new TextField();
		TextField player2TextField = new TextField();

		// ToggleGroups
		ToggleGroup toggleGroup1 = new ToggleGroup();

		RadioButton player1XRadioButton = new RadioButton("X");
		player1XRadioButton.setToggleGroup(toggleGroup1);
		player1XRadioButton.getStyleClass().add("radio-button-2");
		RadioButton player1ORadioButton = new RadioButton("O");
		player1ORadioButton.setToggleGroup(toggleGroup1);
		player1ORadioButton.getStyleClass().add("radio-button-2");

		HBox h1 = new HBox(player1XRadioButton, player1ORadioButton);
		h1.setSpacing(10);

		ToggleGroup toggleGroup2 = new ToggleGroup();
		RadioButton player2XRadioButton = new RadioButton("X");
		player2XRadioButton.getStyleClass().add("radio-button-2");
		player2XRadioButton.setToggleGroup(toggleGroup2);
		RadioButton player2ORadioButton = new RadioButton("O");
		player2ORadioButton.getStyleClass().add("radio-button-2");
		player2ORadioButton.setToggleGroup(toggleGroup2);

		HBox h2 = new HBox(player2XRadioButton, player2ORadioButton);
		h2.setSpacing(10);

		// toggele group setting
		player1XRadioButton.setOnAction(e -> {
			player2XRadioButton.setSelected(false);
			player2ORadioButton.setSelected(true);

		});
		player2XRadioButton.setOnAction(e -> {
			player1XRadioButton.setSelected(false);
			player1ORadioButton.setSelected(true);

		});
		player1ORadioButton.setOnAction(e -> {
			player2ORadioButton.setSelected(false);
			player2XRadioButton.setSelected(true);

		});

		player2ORadioButton.setOnAction(e -> {
			player1ORadioButton.setSelected(false);
			player1XRadioButton.setSelected(true);

		});
		player1XRadioButton.setOnAction(e -> {
			player2XRadioButton.setSelected(false);
			player2ORadioButton.setSelected(true);

		});
		// add components to grid pane

		gridPane.add(player1Label, 0, 0, 4, 1); // Spanning 4 columns
		gridPane.add(player2Label, 4, 0, 4, 1);

		gridPane.add(player1nameLabel, 0, 1);
		gridPane.add(player1TextField, 1, 1);

		gridPane.add(player2nameLabel, 4, 1);
		gridPane.add(player2TextField, 5, 1);

		gridPane.add(player1symbolLabel, 0, 2);
		gridPane.add(h1, 1, 2);

		gridPane.add(player2symbolLabel, 4, 2);
		gridPane.add(h2, 5, 2);

		// who will start
		ToggleGroup toggleGroup3 = new ToggleGroup();
		RadioButton player1Start = new RadioButton("Player 1");
		player1Start.getStyleClass().add("radio-button-2");
		player1Start.setToggleGroup(toggleGroup3);
		RadioButton player2Start = new RadioButton("Player 2");
		player2Start.getStyleClass().add("radio-button-2");
		player2Start.setToggleGroup(toggleGroup3);

		HBox h3 = new HBox(player1Start, player2Start);
		h3.setSpacing(20);

		Label startQuestion = new Label("Who will start? ");

		gridPane.add(startQuestion, 0, 4);
		gridPane.add(h3, 1, 4);

		// Number of rounds
		Label roundsQuestion = new Label("How many Rounds? ");
		TextField roundsTextBox = new TextField();

		gridPane.add(roundsQuestion, 0, 5);
		gridPane.add(roundsTextBox, 1, 5);

		pane.setCenter(gridPane);

		// Create a Button
		Button toGameButton = arrowButton("to game!");
		toGameButton.setPadding(new Insets(20));
		// toEntryButton.setAlignment(Pos.TOP_LEFT);
		pane.setAlignment(toGameButton, Pos.BASELINE_CENTER);
		pane.setBottom(toGameButton);

		// Create the scene
		entryScene = new Scene(pane, WIDTH, HIEGHT);
		entryScene.getStylesheets().add("styles.css");

		// Set the scene on the stage
		primaryStage.setScene(entryScene);
		primaryStage.setTitle("Multiplayer Settings");

		// program button
		toGameButton.setOnAction(e -> {

			try {
				// set game settings
				game = new Game(Integer.parseInt(roundsTextBox.getText()));

				char player1Symbol;
				char player2Symbol;

				if (player1XRadioButton.isSelected()) {
					player1Symbol = 'X';
					player2Symbol = 'O';

				} else {
					player1Symbol = 'O';
					player2Symbol = 'X';
				}

				player1 = new Player(player1TextField.getText(), player1Symbol);
				player2 = new Player(player2TextField.getText(), player2Symbol);

				game.addPlayer(player1);
				game.addPlayer(player2);

				if (player1Start.isSelected())
					game.setStratingPlayer(player1);
				else
					game.setStratingPlayer(player2);

				designPlayScene();
				toGameButton.setDisable(true);
			} catch (Exception e2) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Occured ");
				alert.setHeaderText("There was an error in entering information. ");
				alert.setContentText(" Please enter full info correctly and try again.");
				alert.showAndWait();
			}

		});

	}

	/**
	 * design entry scene for computer
	 */
	private void designEntrySceneComputer() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(50));

		GridPane gridPane = new GridPane();
		gridPane.setHgap(40);
		gridPane.setVgap(30);
		gridPane.setPadding(new Insets(30));

		// Labels
		Label player1Label = new Label("Player info");

		Label player1nameLabel = new Label("Name: ");

		Label player1symbolLabel = new Label("Symbol: ");

		// TextFields
		TextField player1TextField = new TextField();

		// ToggleGroups
		ToggleGroup toggleGroup1 = new ToggleGroup();

		RadioButton player1XRadioButton = new RadioButton("X");
		player1XRadioButton.setToggleGroup(toggleGroup1);
		player1XRadioButton.getStyleClass().add("radio-button-2");
		RadioButton player1ORadioButton = new RadioButton("O");
		player1ORadioButton.setToggleGroup(toggleGroup1);
		player1ORadioButton.getStyleClass().add("radio-button-2");

		HBox h1 = new HBox(player1XRadioButton, player1ORadioButton);
		h1.setSpacing(10);

		// add components to grid pane

		gridPane.add(player1Label, 0, 0, 4, 1); // Spanning 4 columns

		gridPane.add(player1nameLabel, 0, 1);
		gridPane.add(player1TextField, 1, 1);

		gridPane.add(player1symbolLabel, 0, 2);
		gridPane.add(h1, 1, 2);

		// who will start
		ToggleGroup toggleGroup3 = new ToggleGroup();
		RadioButton player1Start = new RadioButton("Player ");
		player1Start.getStyleClass().add("radio-button-2");
		player1Start.setToggleGroup(toggleGroup3);
		RadioButton player2Start = new RadioButton("Computer");
		player2Start.getStyleClass().add("radio-button-2");
		player2Start.setToggleGroup(toggleGroup3);

		HBox h3 = new HBox(player1Start, player2Start);
		h3.setSpacing(20);

		Label startQuestion = new Label("Who will start? ");

		gridPane.add(startQuestion, 0, 4);
		gridPane.add(h3, 1, 4);

		// Number of rounds
		Label roundsQuestion = new Label("How many Rounds? ");
		TextField roundsTextBox = new TextField();

		gridPane.add(roundsQuestion, 0, 5);
		gridPane.add(roundsTextBox, 1, 5);

		pane.setCenter(gridPane);

		// with or without messages
		ToggleGroup toggleGroup4 = new ToggleGroup();
		RadioButton withMessagesRB = new RadioButton("Yes");
		withMessagesRB.getStyleClass().add("radio-button-2");
		withMessagesRB.setToggleGroup(toggleGroup4);
		RadioButton withoutMessagesRB = new RadioButton("No");
		withoutMessagesRB.getStyleClass().add("radio-button-2");
		withoutMessagesRB.setToggleGroup(toggleGroup4);

		HBox h4 = new HBox(withMessagesRB, withoutMessagesRB);
		h4.setSpacing(20);

		Label messageQuestion = new Label("Would you like to see messages? ");

		if (gameMode == Game.ADVANCED) {
			gridPane.add(messageQuestion, 0, 6);
			gridPane.add(h4, 1, 6);
		}
		// Create a Button
		Button toGameButton = arrowButton("to game!");
		toGameButton.setPadding(new Insets(20));
		// toEntryButton.setAlignment(Pos.TOP_LEFT);
		pane.setAlignment(toGameButton, Pos.BASELINE_CENTER);
		pane.setBottom(toGameButton);

		// Create the scene
		entryScene = new Scene(pane, WIDTH, HIEGHT);
		entryScene.getStylesheets().add("styles.css");

		// Set the scene on the stage
		primaryStage.setScene(entryScene);
		primaryStage.setTitle("Multiplayer Settings");

		// program button
		toGameButton.setOnAction(e -> {

			try {
				// set game settings
				game = new Game(Integer.parseInt(roundsTextBox.getText()));

				char player1Symbol;
				char player2Symbol;

				if (player1XRadioButton.isSelected()) {
					player1Symbol = 'X';
					player2Symbol = 'O';

				} else {
					player1Symbol = 'O';
					player2Symbol = 'X';
				}

				player1 = new Player(player1TextField.getText(), player1Symbol);
				player2 = new Player("Computer", player2Symbol);

				game.addPlayer(player1);
				game.addPlayer(player2);

				if (player1Start.isSelected())
					game.setStratingPlayer(player1);
				else
					game.setStratingPlayer(player2);

				if (withMessagesRB.isSelected())
					withMessages = true;

				designPlayScene();
				toGameButton.setDisable(true);
			} catch (Exception e2) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Occured ");
				alert.setHeaderText("There was an error in entering information. ");
				alert.setContentText(" Please enter full info correctly and try again.");
				alert.showAndWait();
			}

		});
	}

	/**
	 * design main play scene
	 */
	private void designPlayScene() {
		BorderPane pane = new BorderPane();

		// create VBoxes for players info

		// Create VBox for Player 1
		VBox vboxPlayer1 = new VBox(10);
		vboxPlayer1.setPadding(new Insets(20));
		vboxPlayer1.setAlignment(Pos.CENTER);

		Label player1Label = new Label("Player 1");

		Label nameLabel1 = new Label("Name:");
		TextField nameTextField1 = new TextField();
		nameTextField1.setText(player1.getName());
		nameTextField1.setEditable(false);

		Label pointsLabel1 = new Label("Points:");
		pointsTextField1 = new TextField();
		pointsTextField1.setText(0 + "");
		pointsTextField1.setEditable(false);

		Label symbolLabel1 = new Label("Symbol:");
		TextField symbolTextField1 = new TextField();
		symbolTextField1.setText(player1.getSymbol() + "");
		symbolTextField1.setEditable(false);

		vboxPlayer1.getChildren().addAll(player1Label, nameLabel1, nameTextField1, pointsLabel1, pointsTextField1,
				symbolLabel1, symbolTextField1);

		// Create VBox for Player 2
		VBox vboxPlayer2 = new VBox(10);
		vboxPlayer2.setPadding(new Insets(20));
		vboxPlayer2.setAlignment(Pos.CENTER);

		Label player2Label = new Label("Player 2");

		Label nameLabel2 = new Label("Name:");
		TextField nameTextField2 = new TextField();
		nameTextField2.setText(player2.getName());
		nameTextField2.setEditable(false);

		Label pointsLabel2 = new Label("Points:");
		pointsTextField2 = new TextField();
		pointsTextField2.setText(0 + "");
		pointsTextField2.setEditable(false);

		Label symbolLabel2 = new Label("Symbol:");
		TextField symbolTextField2 = new TextField();
		symbolTextField2.setText(player2.getSymbol() + "");
		symbolTextField2.setEditable(false);

		vboxPlayer2.getChildren().addAll(player2Label, nameLabel2, nameTextField2, pointsLabel2, pointsTextField2,
				symbolLabel2, symbolTextField2);

		pane.setRight(vboxPlayer2);
		pane.setLeft(vboxPlayer1);

		// rounds textbox
		HBox topBox = new HBox(20);
		topBox.setPadding(new Insets(10));
		topBox.setAlignment(Pos.CENTER);

		Label roundsLabel = new Label("Rounds:");
		numberOfRoundsTextField = new TextField();
		numberOfRoundsTextField.setText(game.getCurrentRound() + " of " + game.getNumberOfRounds());
		numberOfRoundsTextField.setEditable(false);

		topBox.getChildren().addAll(roundsLabel, numberOfRoundsTextField);

		pane.setTop(topBox);

		VBox valign = new VBox();
		// create grid Pane in center

		valign.getChildren().add(designGridPane());
		pane.setCenter(valign);

		if (withMessages) {
			statusLabel = new TextArea();
			statusLabel.setPadding(new Insets(0, 5, 5, 10));
			// statusLabel.setAlignment(Pos.CENTER);
			// pane.setAlignment(statusLabel, Pos.TOP_CENTER);
			statusLabel.setStyle(
					"-fx-font-size: 16px; -fx-font-family: 'Arial';-fx-text-fill: black; -fx-font-weight: bold;");

			statusLabel.setText(game.getStatus());
			statusLabel.setMaxHeight(80);
			valign.getChildren().add(statusLabel);
		}
		// Text statusLabel = new Text();
		// statusLabel.setWrappingWidth(300);
		// pane.setBottom(statusLabel);

		// Create the scene
		playScene = new Scene(pane, WIDTH, HIEGHT + 40);
		playScene.getStylesheets().add("styles.css");

		// Set the scene on the stage
		primaryStage.setScene(playScene);
		primaryStage.show();

		// start computer is set , play first
		if (gameMode == Game.ADVANCED || gameMode == Game.RANDOM)
			if (game.getCurrentPlayer().equals(player2))
				computerTurn();
	}

	/**
	 * this updates what shows when round finishes
	 */
	private void updateBoxes() {
		System.out.println(">>>" + player1.getPoints());
		pointsTextField1.setText(player1.getPoints() + "");

		System.out.println(">>>" + player2.getPoints());

		pointsTextField2.setText(player2.getPoints() + "");

		numberOfRoundsTextField.setText(game.getCurrentRound() + " of " + game.getNumberOfRounds());

	}

	// design gridpane of tiles basically
	private GridPane designGridPane() {
		gameBoard = new GridPane();
		// gameBoard.setVgap(5);
		// gameBoard.setHgap(5);
		gameBoard.setMinHeight(HIEGHT - 100);
		gameBoard.setMinWidth(HIEGHT - 100);

		gameBoard.setGridLinesVisible(true);

		gameBoard.setPadding(new Insets(5));
		gameBoard.setAlignment(Pos.CENTER);

		// gameBoard.setStyle("-fx-background-color:black");

		Tile tile = null;

		for (int i = 0; i < Game.ROWS; i++)
			for (int j = 0; j < Game.ROWS; j++) {
				tile = new Tile(Game.ROWS * i + j);
				tile.setOnAction(tileHandler);
				tile.getStyleClass().add("tile");

				gameBoard.add(tile, j, i);

			}

		return gameBoard;
	}

	// This is event handler for all tiles
	EventHandler<ActionEvent> tileHandler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) { // modify
												// this
												// *******
			Tile tile = ((Tile) event.getSource());
			System.out.print(tile.getCellNum());
			System.out.println("Button Clicked!");

			if (game.getCurrentPlayer().getSymbol() == 'X')
				tile.setText("X");
			else
				tile.setText("O");

			// System.out.println(i+">>"+game.getCurrentPlayer().getName());

			// tile.setDisable(false);

			game.play(tile.getCellNum());
			// statusLabel.setText(game.getStatus());
			tile.setDisable(true);

			// System.out.println(game.getCurrentPlayer().getSymbol() + "turn ");

			if (game.isRoundFinished()) {
				updateRoundFinished();

			}
			if (gameMode == Game.ADVANCED || gameMode == Game.RANDOM)
				if (game.getCurrentPlayer().equals(player2))
					computerTurn();

		}

	};

	private void updateRoundFinished() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Round Finished !");
		game.updateScores();
		alert.setHeaderText(game.getStatus());

		if (withMessages)
			statusLabel.clear();

		// game.newRound();

		if (game.getCurrentRound() == game.getNumberOfRounds()) {
			System.out.println("yes iindeed");
			showResults();
			
			//reset
			game.newRound();
		}

		else if (!game.isGameOver()) { // game is not finished

			alert.setContentText("Let's go to next Round!");
			alert.showAndWait();
			// game.updateScores();

			game.newRound();
			updateBoxes();
			enableAll();

			// System.out.println(i+">>"+game.getCurrentPlayer().getName());
			// after starting new round, we continue turn

			if (gameMode == Game.ADVANCED || gameMode == Game.RANDOM)
				if (game.getCurrentPlayer().equals(player2))
					computerTurn();

		} else {

			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Game over !");
			alert.setHeaderText(game.getStatus());
			alert.setContentText("Would you like to continue rounds?");

			// alert.showAndWait();

			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					// choose to continue
					// game.setGameFinished(false);
					game.newRound();
					enableAll();
					updateBoxes();
					// enableAll();

					if (gameMode == Game.ADVANCED || gameMode == Game.RANDOM)
						if (game.getCurrentPlayer().equals(player2))
							computerTurn();

				} else {
					showResults();

				}
			});

		}

		// showResults();
		// enableAll();
		// alert.setContentText("Let's go to Results!");
		// alert.showAndWait();
		// updateBoxes();

	}

	// }

	private void computerTurn() {

		int move = -1;

		if (gameMode == Game.RANDOM)
			move = game.getRandomMove();
		else
			move = game.getBestMove();

		Tile tile = tileAt(move);

		if (game.getCurrentPlayer().getSymbol() == 'X') {
			tile.setText("X");
		} else {
			tile.setText("O");
		}

		game.play(move);
		tile.setDisable(true);

		// System.out.println("printed " + tile.getText() + " at " + tile.getCellNum())
		// Tile tile= (Tile) gameBoard.getChildren().get(move);

		System.out.println(game.getCurrentPlayer().getSymbol() + "turn " + move);

		if (withMessages)
			statusLabel.setText(game.getMessage());

		if (game.isRoundFinished()) {
			updateRoundFinished();

		}

	}

	private void showResults() {
		Stage resultStage = new Stage();

		Label finalResult = new Label();

		if (player1.getPoints() > player2.getPoints())
			finalResult.setText(player1.getName() + " Won!");
		else if (player1.getPoints() < player2.getPoints())
			finalResult.setText(player2.getName() + " Won!");
		else
			finalResult.setText("It's a Tie!");

		finalResult.getStyleClass().add("title-style");
		Label summary = new Label("Summary:");

		Label player1res = new Label(player1.getName() + " : " + player1.getPoints() + " points");
		Label player2res = new Label(player2.getName() + " : " + player2.getPoints() + " points");
		player1res.setStyle("-fx-font-weight: bold;");
		player2res.setStyle("-fx-font-weight: bold;");

		// Create a layout (VBox) to hold the labels
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(40));
		vbox.getChildren().addAll(finalResult, summary, player1res, player2res);

		// Create the scene
		Scene scene = new Scene(vbox, 400, 300);
		scene.getStylesheets().add("styles.css");
		resultStage.setScene(scene);

		// Set the stage title
		resultStage.setTitle("Final Results!");
		resultStage.show();

	}

	// get the tile to draw at
	private Tile tileAt(int num) {
		Tile tile = null;

		for (Node n : gameBoard.getChildren()) {

			if (n instanceof Tile) {
				tile = (Tile) n;
				if (tile.getCellNum() == num)
					return tile;
			}

		}

		return tile;

	}

	// disable all tiles
	void disableAll() {
		for (Node n : gameBoard.getChildren()) {

			if (n instanceof Tile) {
				Tile tile = (Tile) n;
				tile.setDisable(true);
			}

		}
	}

	// enable all tiles
	void enableAll() {
		for (Node n : gameBoard.getChildren()) {
			if (n instanceof Tile) {
				Tile tile = (Tile) n;
				tile.reset();
				tile.setDisable(false);
			}
		}
	}

	/**
	 * helper functions
	 */

	public Button arrowButton(String text) {
		return arrowButton(text, 0, 100);
	}

	public Button arrowButton(String text, double rotate, int width) {

		Button but = new Button(text);
		ImageView arrowImage = new ImageView(new Image("arrow2.png"));
		arrowImage.setFitWidth(40);
		arrowImage.setRotate(rotate);
		arrowImage.setPreserveRatio(true);
		but.setGraphic(arrowImage);
		// arrowImage.setBlendMode(BlendMode.SRC_OVER);
		but.setPrefWidth(width);
		// but.setStyle("-fx-background-color: olive;-fx-border-color: black;");
		// but.setStyle(text);
		but.getStyleClass().add("arrow");

		// but.setStyle("-fx-background-color: rgba(102, 141, 12, 0);");
		but.setContentDisplay(ContentDisplay.TOP);
		return but;

	}

	public static void main(String[] args) {
		launch(args);
	}
}
