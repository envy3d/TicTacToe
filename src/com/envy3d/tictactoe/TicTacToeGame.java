package com.envy3d.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TicTacToeGame extends Game {
	public static final int WIDTH = 3;
	public static final int HEIGHT = 3;
	
	private SpriteBatch spriteBatch;
	
	@Override
	public void create() {		
		spriteBatch = new SpriteBatch();
		MenuScreen menuScreen = new MenuScreen(spriteBatch, this);
		Gdx.input.setInputProcessor(menuScreen);
		setScreen(menuScreen);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
