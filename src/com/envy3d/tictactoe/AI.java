/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.math.MathUtils;

public class AI extends View {

	protected Combinations combos;
	protected int[][] board;
	
	public AI(int marker, Model model) {
		super(marker, model);
		combos = new Combinations();
		board = Board.buildEmptyBoard();
	}

	public void update() {
		model.markBox(MathUtils.random(Board.WIDTH - 1), MathUtils.random(Board.HEIGHT - 1), marker);
	}
}
