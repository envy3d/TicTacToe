package com.envy3d.tictactoe;
/**
 * @author envy3d
 */

public class Model implements Controller {
	private int[][] board;
	private Combinations combinations;
	private int previousMark;
	private GameOverReceiver gameOverReceiver;
	private int turnCount;
	
	
	public Model() {
		board = new int[TicTacToeGame.HEIGHT][];
		for (int rowIndex = 0; rowIndex < TicTacToeGame.HEIGHT; ++rowIndex) {
			board[rowIndex] = new int[TicTacToeGame.WIDTH];
		}
		combinations = new Combinations();
		previousMark = 0;
		turnCount = 0;
	}
	
	/**
	 * Verifies that the parameters are valid then updates the board.
	 * @param positionX
	 * @param positionY
	 * @param mark
	 * @return true if parameters are valid
	 */
	public boolean markBox(int positionX, int positionY, int mark) {
		// Verify that the position to be marked is within the board.
		if (positionX >= 0 && positionX < TicTacToeGame.WIDTH && positionY >= 0 && positionY < TicTacToeGame.HEIGHT) {
			// Verify that mark's value is a valid value for the board. 
			if (mark != previousMark && (mark == -1 || mark == 1)) {
				// Verify that the position is not already taken.
				if (board[positionY][positionX] == 0) {
					board[positionY][positionX] = mark;
					previousMark = mark;
					turnCount++;
					combinations.updateCombinationCounts(positionX, positionY, mark);
					checkForWinner();
					return true;
				}
			}
		}
		return false;
	}
	
	public int[][] getBoard(int[][] boardToOverwrite) {
		if (boardToOverwrite == null) {
			boardToOverwrite = new int[board.length][];
			for (int rowIndex = 0; rowIndex < boardToOverwrite.length; ++rowIndex) {
				boardToOverwrite[rowIndex] = new int[board[rowIndex].length];
			}
		}
		for (int rowIndex = 0; rowIndex < board.length; ++rowIndex) {
			for (int columnIndex = 0; columnIndex < board[rowIndex].length; ++columnIndex) {
				boardToOverwrite[rowIndex][columnIndex] = board[rowIndex][columnIndex];
			}
			//System.arraycopy(board[rowIndex], 0, boardToOverwrite[rowIndex], 0, board[rowIndex].length);
		}
		return boardToOverwrite;
	}
	
	public void getCombinations(Combinations combosToOverwrite) {
		combinations.copyInto(combosToOverwrite);
	}
	
	private void checkForWinner() {
		for (int counterIndex = 0; counterIndex < combinations.comboCounts.length; ++counterIndex) {
			if (combinations.comboCounts[counterIndex] == 3)
				gameOverReceiver.gameOver(1);
			else if (combinations.comboCounts[counterIndex] == -3)
				gameOverReceiver.gameOver(-1);
		}
		if (turnCount == 9) {
			gameOverReceiver.gameOver(0);
		}
	}

	public void attachGameOverReceiver(GameOverReceiver gameOverReceiver) {
		this.gameOverReceiver = gameOverReceiver;
	}
}
