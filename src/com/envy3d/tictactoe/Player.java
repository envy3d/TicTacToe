/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;

public class Player extends View implements InputProcessor {
	private int boxWidth, boxHeight;
	
	public Player(int marker, Model model) {
		super(marker, model);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			screenX -= 22;
			screenY -= 71;
			model.markBox(screenX / 110, screenY/ 100, marker);
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
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
