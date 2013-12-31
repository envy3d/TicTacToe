/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;

public class Player extends View implements InputProcessor {
	private int boxWidth, boxHeight;
	
	public Player(int marker, Model model, int boxWidth, int boxHeight) {
		super(marker, model);
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer == 0 && button == Buttons.LEFT) {
			model.markBox(screenX / boxWidth, screenY / boxHeight, marker);
			return true;
		}
		return false;
	}

	
	
	
	// Unused input events.
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
