/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

public interface Controller {

	public boolean markBox(int positionX, int positionY, int mark);
	
	public void getCombinations(Combinations combosToUpdate);
	
	public int[][] getBoard(int[][] boardToUpdate);

}
