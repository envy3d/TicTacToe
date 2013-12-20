/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.math.MathUtils;

public class AI extends View {

	protected Combinations combos;
	protected int[][] board;
	
	public AI(int marker, Controller controller) {
		super(marker, controller);
		combos = new Combinations();
	}

	public void update() {
		if (isTurn) {
			if (controller.markBox(MathUtils.random(TicTacToeGame.WIDTH - 1), MathUtils.random(TicTacToeGame.HEIGHT - 1), marker)) {
				isTurn = false;
			}
		}
	}
}
