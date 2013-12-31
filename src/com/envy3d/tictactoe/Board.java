/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

public class Board {
	public static final int WIDTH = 3;
	public static final int HEIGHT = 3;
	
	public static int[][] buildEmptyBoard() {
		int[][] board = new int[HEIGHT][];	
		for (int rowIndex = 0; rowIndex < HEIGHT; ++rowIndex) {
			board[rowIndex] = new int[WIDTH];
		}
		
		return board;
	}
	
	private Board(){}
}
