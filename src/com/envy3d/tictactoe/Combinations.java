/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

/**
 * This holds the sums of the marker values in the "rows" needed for either player to win.
 * 
 * The comboPositions array holds the positions that make up each "row".
 */
public class Combinations {
	public int[] comboCounts;
	public final int[][][] comboPositions = {	{{0,0},{0,1},{0,2}},
												{{1,0},{1,1},{1,2}},
												{{2,0},{2,1},{2,2}},
												{{0,0},{1,0},{2,0}},
												{{0,1},{1,1},{2,1}},
												{{0,2},{1,2},{2,2}},
												{{0,0},{1,1},{2,2}},
												{{0,2},{1,1},{2,0}}	};
	
	public Combinations() {
		comboCounts = new int[comboPositions.length];
	}
	
	public void updateCombinationCounts(int positionX, int positionY, int valueToAdd) {
		
		for (int comboIndex = 0; comboIndex < comboPositions.length; ++comboIndex) {
			
			for (int positionIndex = 0; positionIndex < comboPositions[comboIndex].length; ++positionIndex) {
				
				if (positionY == comboPositions[comboIndex][positionIndex][0] &&
					positionX == comboPositions[comboIndex][positionIndex][1]) {
					comboCounts[comboIndex] += valueToAdd;
				}
			}
		}
	}
	
	public void copyInto(Combinations combosToCopyTo) {
		System.arraycopy(comboCounts, 0, combosToCopyTo.comboCounts, 0, comboCounts.length);
	}
}
