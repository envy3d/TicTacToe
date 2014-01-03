package com.envy3d.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TicTacToeGame extends Game {
	
	private SpriteBatch spriteBatch;
	private AssetManager assets;
	
	@Override
	public void create() {		
		spriteBatch = new SpriteBatch();
		assets = new AssetManager();
		assets.load("paperBg.png", Texture.class);
		assets.load("board.png", Texture.class);
		assets.load("X.png", Texture.class);
		assets.load("O.png", Texture.class);
		assets.load("menu.png", Texture.class);
		assets.load("xWin.png", Texture.class);
		assets.load("oWin.png", Texture.class);
		assets.load("tie.png", Texture.class);
		assets.load("ai1.png", Texture.class);
		assets.load("ai2.png", Texture.class);
		assets.load("aiHighlight.png", Texture.class);
		assets.load("underline.png", Texture.class);
		
		assets.finishLoading();
		MenuScreen menuScreen = new MenuScreen(spriteBatch, assets, this);
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
