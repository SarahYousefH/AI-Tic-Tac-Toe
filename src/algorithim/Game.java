package algorithim;

import java.util.Random;

/**
 * This class represent the main class for the tic-tac-toe game. It has two
 * players and board.
 */
public class Game {

	/**
	 * constants definitions:
	 */

	public static final int ROWS = 3; // It is always square board, so (ROWS=COLS)
	public static final int COLS = 3;

	// States of the game at any time
	public static final int WIN = 1;
	public static final int DRAW = 0;
	public static final int LOSE = -1;
	public static final int NON_FINAL = -2; // means game is undecided yet, so continue playing

	public static final boolean MAXIMIZIG = true;
	public static final boolean MINIMIZING = false;

	// Game modes
	public static final int TWO_PLAYERS = 1;
	public static final int RANDOM = 2;
	public static final int ADVANCED = 3;

	/**
	 * Field variables :
	 */
	private char[][] board;
	private int numberOfRounds;
	private int currentRound; // number of current round
	private Player currentPlayer; // the player that currently has the turn to play
	private int numOfPlayers = 0; // number of players added so far

	// Two main players of the game
	private Player playerX;
	private Player playerO;

	private String status = ""; // keeps track og game changes
	private String message = ""; // tracks messages

	//private boolean gameFinished;

	/**
	 * Constructor for game
	 * 
	 * @param numberOfRounds : number of tatal numberOfRounds in the game
	 */
	public Game(int numberOfRounds) {
		board = new char[ROWS][COLS];
		this.numberOfRounds = numberOfRounds;
		// Initialize board;
		newRound();
	}

	/**
	 * Main methods:
	 */

	/**
	 * A method to start a new Round. It increments current Round and clear the
	 * cells.
	 */
	public void newRound() {
		// increment current Round
		currentRound++;

		// clear the board
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				board[row][col] = ' '; // all cells empty
			}
		}

	}

	/**
	 * a method to add new Player to game
	 * 
	 * @param newPlayer : The player to be added
	 * @param goFirst   : Does this play goes first or not
	 */
	public void addPlayer(Player newPlayer, boolean goFirst) {

		if (numOfPlayers < 2) { // max number of players is 2

			if (newPlayer.getSymbol() == 'X') {

				playerX = newPlayer;
				if (goFirst == true)
					currentPlayer = playerX;
				else
					currentPlayer = playerO;

			} else {

				playerO = newPlayer;
				if (goFirst == true)
					currentPlayer = playerO;
				else
					currentPlayer = playerX;
			}

			// status = getCurrentPlayer().getName() + " turn";
			numOfPlayers++;
		}

	}

	/**
	 * a method to add new Player to game
	 * 
	 * @param newPlayer : The player to be added
	 * @param goFirst   : Does this play goes first or not
	 */
	public void addPlayer(Player newPlayer) {

		if (numOfPlayers < 2) { // max number of players is 2

			if (newPlayer.getSymbol() == 'X') {

				playerX = newPlayer;

			} else {
				playerO = newPlayer;
			}

			numOfPlayers++;
		}

	}

	/**
	 * a method to start game from specified player
	 * 
	 * @param player
	 */
	public void setStratingPlayer(Player player) {
		currentPlayer = player;
	}

	/**
	 * This is the main action function for each player to play.
	 * 
	 * @param cell: The number of cell that will be played
	 */
	public void play(int cell) {

		if (!isRoundFinished() && isValidMove(cell)) {
			board[cell / ROWS][cell % ROWS] = getCurrentPlayer().getSymbol();
			switchPlayer();

		}
	}

	/**
	 * a method to get the opponent player of the current player specified. It gets
	 * playerO if playerX is sent. It gets playerX if playerO is sent.
	 * 
	 * @param currentPlayer
	 * @return
	 */
	private Player otherPlayer(Player player) {
		if (player.equals(playerO)) // check if equal
			return playerX;
		else
			return playerO;

	}

	/**
	 * A method to simply switch the turns and update status
	 */
	private void switchPlayer() {
		currentPlayer = otherPlayer(currentPlayer);
		// status = currentPlayer.getName() + " Turn";

	}

	/**
	 * a method to check if a move is valid that is if it is in range and if the
	 * place to add move is currently empty.
	 * 
	 * @param cell: where to check
	 * @return true if valid
	 */
	private boolean isValidMove(int cell) { // is empty and in range
		if (cell < 0 || cell > 8)
			return false;

		if (board[cell / ROWS][cell % ROWS] == ' ') // if is empty
			return true;
		else
			return false;
	}

	/**
	 * a method to simply check if round finished or not
	 * 
	 * @return true if round finished
	 */
	public boolean isRoundFinished() {

		int state = getStatus(currentPlayer);
		// if we haven't reached a final state this means round is not finished
		if (state == NON_FINAL)
			return false;
		else {
			return true;
		}

	}

	/**
	 * This basically set the status of game when win , lose or tie. This is called
	 * after confirming round is finished.
	 * 
	 * @param state
	 */
	public void updateScores() {

		int state = getStatus(currentPlayer);

		if (state == WIN) {
			status = currentPlayer.getName() + " won!";
			System.out.println("**" + currentPlayer.getPoints());
			currentPlayer.setPoints(currentPlayer.getPoints() + 1);
		} else if (state == LOSE) {
			status = otherPlayer(currentPlayer).getName() + " won!";
			otherPlayer(currentPlayer).setPoints(otherPlayer(currentPlayer).getPoints() + 1);
		} else if (state == DRAW)
			status = "It's a tie!";
		else
			status = "undecided";

	}

	/**
	 * a method to check if all rounds are done
	 * 
	 * @return true if all rounds finished
	 */
	public boolean isGameOver() {

		if (currentRound >= numberOfRounds)
			return true;
		else if (playerX.getPoints() > numberOfRounds / 2)
			return  true;
		else if (playerO.getPoints() > numberOfRounds / 2)
			return true;
		
		return false;

	}

	/**
	 * A method to get status of game , this is known as static evaluation function.
	 * It returns the state of the game form the specified player perspective.
	 * 
	 * @param player : The player perspective.
	 * @return the state of the game
	 */
	private int getStatus(Player player) {

		// 1. check if player won:

		// check player rows
		for (int i = 0; i < ROWS; i++) {
			if (board[i][0] == player.getSymbol() && board[i][1] == player.getSymbol()
					&& board[i][2] == player.getSymbol())
				return WIN;
		}

		// check player columns
		for (int i = 0; i < ROWS; i++) {
			if (board[0][i] == player.getSymbol() && board[1][i] == player.getSymbol()
					&& board[2][i] == player.getSymbol())
				return WIN;
		}

		// check player diagonals:

		// the first diagonal (top left to bottom right)
		if (board[0][0] == player.getSymbol() && board[1][1] == player.getSymbol() && board[2][2] == player.getSymbol())
			return WIN;

		// the second diagonal (top right to bottom left)

		if (board[0][2] == player.getSymbol() && board[1][1] == player.getSymbol() && board[2][0] == player.getSymbol())
			return WIN;

		// 2. check if opponent won:

		Player opponent = otherPlayer(player);

		// check rows
		for (int i = 0; i < ROWS; i++) {
			if (board[i][0] == opponent.getSymbol() && board[i][1] == opponent.getSymbol()
					&& board[i][2] == opponent.getSymbol())
				return LOSE;
		}

		// check column
		for (int i = 0; i < ROWS; i++) {
			if (board[0][i] == opponent.getSymbol() && board[1][i] == opponent.getSymbol()
					&& board[2][i] == opponent.getSymbol())
				return LOSE;
		}

		// check diagonal
		if (board[0][0] == opponent.getSymbol() && board[1][1] == opponent.getSymbol()
				&& board[2][2] == opponent.getSymbol())
			return LOSE;

		if (board[0][2] == opponent.getSymbol() && board[1][1] == opponent.getSymbol()
				&& board[2][0] == opponent.getSymbol())
			return LOSE;

		// 3. check if game is draw, considered draw if no one wins and all places
		// filled
		if (isBoardFilled())
			return DRAW;

		// other wise it is not a final state and we can not make a decision
		return NON_FINAL;

	}

	/**
	 * a method to check if board is filled
	 * 
	 * @return true if all cells filled
	 */
	public boolean isBoardFilled() {

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < ROWS; j++) {
				if (board[i][j] == ' ') { // if one of cells is not filled it is false
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * A method to get any random valid move.s
	 * 
	 * @return the cell number of the move
	 */
	public int getRandomMove() {

		// we can use random function like here, but because this is an easy mode we can
		// simply just get next available spot

		/*
		 * Random random = new Random(); while (true) {
		 * 
		 * int move = random.nextInt(9); if (isValidMove(move)) { return move;
		 * 
		 * } }
		 */

		for (int i = 0; i < ROWS * COLS; i++) {
			if (isValidMove(i))
				return i;
		}

		return -1; // no valid moves were found

	}

	/**
	 * This method gets best move for current player (maximize currentPlayer).
	 * 
	 * @return
	 */
	public int getBestMove() {

		message = "";// clear previous message

		// The best move is maximizing this player(current Player).
		// We want to find best score.

		int bestMove = -1;
		int bestScore = -2; // set to minimum

		int currentScore = 0;// we will get this using minimax algorithm for each empty spot

		for (int i = 0; i < ROWS * COLS; i++) {

			if (isValidMove(i)) {

				// System.out.println("entered");
				tryMove(i, getCurrentPlayer());
				// printBoard();

				boolean[] possibilities = possiblitiesRecord(); // gets a new possiblitiesRecord()
				currentScore = minimax(currentPlayer, MINIMIZING, otherPlayer(getCurrentPlayer()), possibilities); // now

				// we clear the move because we continue on same board
				clearMove(i);

				// message += "for " + i + " score is " + currentScore + "\n";
				message += "For cell " + i + " cell score is " + currentScore + ", possibilities are : "
						+ getPossiblities(possibilities) + "\n";
				// System.out.println("for " + i + " score is " + currentScore + ">>>>" +
				// printPossiblities(possibilities));

				if (currentScore > bestScore) {
					bestMove = i;
					bestScore = currentScore;
				}
			}
		}

		message += "move at " + bestMove + " was played";
		return bestMove;

	}

	private int minimax(Player maximizingPlayer, boolean isMaximizing, Player minimizingPlayer,
			boolean[] possibilities) {

		// printBoard();

		int state = getStatus(maximizingPlayer);

		if (state != NON_FINAL) // base case for recursion reached final state
		{
			addPossibility(state, possibilities);
			return state;

		}

		// round not finished:
		if (isMaximizing == MAXIMIZIG) {

			int largestScore = -2; // set to minimum
			int currentScore = 0;

			for (int i = 0; i < ROWS * COLS; i++) {
				if (isValidMove(i)) {
					tryMove(i, maximizingPlayer);
					currentScore = minimax(maximizingPlayer, MINIMIZING, minimizingPlayer, possibilities);
					clearMove(i);

					if (currentScore > largestScore)
						largestScore = currentScore;

				}

			}

			return largestScore;

		} else { // minimize

			int leastScore = 2; // set to maximum
			int currentScore = 0;

			for (int i = 0; i < ROWS * COLS; i++) {
				if (isValidMove(i)) {
					tryMove(i, minimizingPlayer);
					currentScore = minimax(maximizingPlayer, MAXIMIZIG, minimizingPlayer, possibilities);
					clearMove(i);

					if (currentScore < leastScore)
						leastScore = currentScore;

				}
			}

			return leastScore;
		}

	}

	/**
	 * A method to try the move on the current board, we call it try because it
	 * helps in finding the possibilities but then we clear it. We don't record the
	 * move after finding the possibility.
	 * 
	 * @param cell   : cell to try move it
	 * @param player : the player that tries move here (get the symbol of player)
	 */
	public void tryMove(int cell, Player player) {
		if (isValidMove(cell)) {
			board[cell / ROWS][cell % ROWS] = player.getSymbol();
		}
	}

	/**
	 * This clears move from a specific cell
	 */
	public void clearMove(int cell) {
		board[cell / ROWS][cell % ROWS] = ' ';
	}

	/**
	 * Possibilities helper functions
	 */
	private boolean[] possiblitiesRecord() {
		/*
		 * This is used to record possible results for each move. possibilities [0] =
		 * false , means that lose possibility does not exist, possibilities [0] = true
		 * , means that lose possibility exists.
		 * 
		 * possibilities [0] is lose state, possibilities [1] is draw state ,
		 * possibilities [2] is win state.
		 * 
		 * We initialize all states to false, because they don't exist yet.
		 */
		boolean[] possiblities = { false, false, false };
		return possiblities;
	}

	private void addPossibility(int state, boolean[] possibilities) {
		possibilities[state + 1] = true;

	}

	public String getPossiblities(boolean[] possibilities) {
		String res = "";

		if (possibilities[0] == true)
			res += " Lose ";
		if (possibilities[1] == true)
			res += " Draw ";
		if (possibilities[2] == true)
			res += " Win ";

		return res;

	}

	/**
	 * Helper functions
	 */

	public void printBoard() {
		System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
		System.out.println("-+-+-");
		System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println("-+-+-");
		System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);

		System.out.println("---------------------------------------------------");

	}

	/**
	 * setters and getters
	 */

	public String getStatus() {
		return status;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public String getMessage() {
		return message;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	
	
	public boolean allRoundsDone() {
		//isGameFinished();
		return currentRound >= numberOfRounds ;
	}

/*
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}
*/
}
