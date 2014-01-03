/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;

public class SimpleAI extends AI{
	private boolean firstTurn;
	
	public SimpleAI(int marker, Model model) {
		super(marker, model);
		firstTurn = true;
	}
	
	@Override
	public void update() {
		model.getBoard(board);
		model.getCombinations(combos);
		
		if (firstTurn) {
			firstTurn = false;
			while (!makeOpeningMove()) {}
			return;
		}
		
		if (makeMostValuableMove()) {
			return;
		}
		
		makeMove(selectRandomLocation(emptyBoardLocations()));
	}
	
	
	private boolean makeMostValuableMove() {		
		int combinationIndex = getMostValuableCombinationIndex();		
		if (combinationIndex != -1) {
			return makeMove(findOpenComboPositionAtIndex(combinationIndex));
		}
		return false;
	}
	
	
	/**
	 * Finds an empty location in a combination.
	 * @param comboIndex
	 * @return null if the combination is full.
	 */
	private int[] findOpenComboPositionAtIndex(int comboIndex) {
		for (int positionIndex = 0; positionIndex < combos.comboPositions[comboIndex].length; ++positionIndex) {
			int posY = combos.comboPositions[comboIndex][positionIndex][0];
			int posX = combos.comboPositions[comboIndex][positionIndex][1];
			if (board[posY][posX] == 0) {
				return new int[] {posY, posX};
			}
		}
		return null;
	}
	
	
	/**
	 * Finds the index of a combination that will either allow the AI to win
	 * or will block the player from winning.
	 * @return -1 means no valuable combination was found.
	 */
	private int getMostValuableCombinationIndex() {
		int gameLosingIndex = -1;
		for (int countIndex = 0; countIndex < combos.comboCounts.length; ++countIndex) {
			// If the AI has two boxes in the combination and the third box is empty,
			// return the combination's index.
			if (combos.comboCounts[countIndex] == -2) {
				return countIndex;
			}
			// If the player has two boxes in the combination and the third box is empty,
			// store the combination's index but keep checking combinations because a win
			// is more valuable than a block.
			else if (combos.comboCounts[countIndex] == 2) {
				gameLosingIndex = countIndex;
			}
		}
		return gameLosingIndex;
	}
	
	
	/**
	 * Attempts to place marker at location.
	 * @param location - in the format {y position, x position}
	 * @return true if the move is accepted. false otherwise.
	 */
	private boolean makeMove(int[] location) {
		return makeMove(location[1], location[0]);
	}
	
	
	/**
	 * Attempts to place marker at position x,y.
	 * @return true if the move is accepted. false otherwise.
	 */
	private boolean makeMove(int x, int y) {
		return model.markBox(x, y, marker);
	}
	
	
	/**
	 * Randomly selects one of the corners at the beginning of the game.
	 */
	private boolean makeOpeningMove() {
		return makeMove(selectRandomLocation(new int[][] { 
											{0, 0},
											{0, Board.WIDTH - 1},
											{Board.HEIGHT - 1, 0},
											{Board.HEIGHT - 1, Board.WIDTH - 1} } ));
	}
	
	
	/**
	 * Chooses a random location from the locations provided.
	 */
	private int[] selectRandomLocation(int[][] locations) {
		return locations[MathUtils.random(locations.length - 1)];
	}
	
	
	private int[][] emptyBoardLocations() {
		ArrayList<int[]> openLocations = new ArrayList<int[]>();
		for (int y = 0; y < Board.HEIGHT; ++y) {
			for (int x = 0; x < Board.WIDTH; ++x) {
				if (board[y][x] == 0) {
					openLocations.add(new int[] {y, x});
				}
			}
		}
		openLocations.trimToSize();
		// Manually copying array elements to get GWT to not crash.
		int[][] openLocationsArray = new int[openLocations.size()][];
		for (int i = 0, length = openLocations.size(); i < length; ++i) {
			openLocationsArray[i] = new int[2];
			openLocationsArray[i][0] = ((int[])openLocations.get(i))[0];
			openLocationsArray[i][1] = ((int[])openLocations.get(i))[1];
		}
		return openLocationsArray;
	}
	
}
