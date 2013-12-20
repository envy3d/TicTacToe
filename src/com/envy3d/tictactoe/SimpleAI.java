/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.math.MathUtils;

public class SimpleAI extends AI{
	private boolean firstTurn;
	
	public SimpleAI(int marker, Controller controller) {
		super(marker, controller);
		firstTurn = true;
	}
	
	@Override
	public void update() {
		if (isTurn) {
			board = controller.getBoard(board);
			controller.getCombinations(combos);
			
			// On the first turn, determine whether or not this AI is the first one to place a mark.
			if (firstTurn) {
				int checkForBlankBoard = 0;
				for (int comboCount : combos.comboCounts) {
					if (comboCount != 0) {
						checkForBlankBoard = comboCount;
					}
				}
				if (checkForBlankBoard == 0) {
					controller.markBox(0, 0, marker);
					isTurn = false;
				}
				firstTurn = false;
				return;
			}
			
			// Scan through combinationCounts to see if this AI can make a game-winning move
			//	 or if the opponent can make a game-winning move.
			int gameWinningIndex = -1;
			int gameLosingIndex = -1;
			for (int countIndex = 0; countIndex < combos.comboCounts.length; ++countIndex) {
				if (combos.comboCounts[countIndex] == -2) {
					gameWinningIndex = countIndex;
				}
				else if (combos.comboCounts[countIndex] == 2) {
					gameLosingIndex = countIndex;
				}
			}
			if (gameWinningIndex != -1) {
				findOpenComboPositionAtIndex(gameWinningIndex);
				isTurn = false;
				return;
			}
			else if (gameLosingIndex != -1) {
				findOpenComboPositionAtIndex(gameLosingIndex);
				isTurn = false;
				return;
			}
			else if (controller.markBox(MathUtils.random(TicTacToeGame.WIDTH - 1), MathUtils.random(TicTacToeGame.HEIGHT - 1), marker)) {
				isTurn = false;
			}
		}
	}
	
	private void findOpenComboPositionAtIndex(int comboIndex) {
		for (int positionIndex = 0; positionIndex < combos.comboPositions[comboIndex].length; ++positionIndex) {
			int posY = combos.comboPositions[comboIndex][positionIndex][0];
			int posX = combos.comboPositions[comboIndex][positionIndex][1];
			if (board[posY][posX] == 0) {
				controller.markBox(posX, posY, marker);
			}
		}
	}
}
