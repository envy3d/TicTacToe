package com.envy3d.tictactoe;

import com.badlogic.gdx.math.MathUtils;

/**
 * @author envy3d
 */

public class Model {
	private int[][] board;
	private Combinations combinations;
	private GameOverReceiver gameOverReceiver;
	private int turnCount;
	public int playerTurn;
	
	
	public Model() {
		board = Board.buildEmptyBoard();
		combinations = new Combinations();
		turnCount = 0;
		setStartingPlayer();
	}
	
	/**
	 * Verifies that the parameters are valid then updates the board.
	 * @param mark
	 * @return true if parameters are valid
	 */
	public boolean markBox(int positionX, int positionY, int mark) {
		// Verify that the position to be marked is within the board.
		if (positionX >= 0 && positionX < Board.WIDTH && positionY >= 0 && positionY < Board.HEIGHT) {
			// Verify that mark's value is a valid value for the board. 
			if (mark == playerTurn && (mark == -1 || mark == 1)) {
				// Verify that the position is not already taken.
				if (board[positionY][positionX] == 0) {
					board[positionY][positionX] = mark;
					playerTurn *= -1;
					turnCount++;
					combinations.updateCombinationCounts(positionX, positionY, mark);
					checkForWinner();
					return true;
				}
			}
		}
		return false;
	}
	
	public void getBoard(int[][] boardToOverwrite) {
		for (int rowIndex = 0; rowIndex < board.length; ++rowIndex) {
			for (int columnIndex = 0; columnIndex < board[rowIndex].length; ++columnIndex) {
				boardToOverwrite[rowIndex][columnIndex] = board[rowIndex][columnIndex];
			}
		}
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
		if (turnCount == Board.HEIGHT * Board.WIDTH) {
			gameOverReceiver.gameOver(0);
		}
	}

	public void attachGameOverReceiver(GameOverReceiver gameOverReceiver) {
		this.gameOverReceiver = gameOverReceiver;
	}
	
	private void setStartingPlayer() {
		if (MathUtils.randomBoolean()) {
			playerTurn = 1;
		}
		else {
			playerTurn = -1;
		}
	}
}
