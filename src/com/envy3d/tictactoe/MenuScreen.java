/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen, InputProcessor {
	
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Game game;
	
	private Texture backgroundTex;
	private Texture xWinTex;
	private Texture oWinTex;
	private Texture tieTex;
	
	private char winner;
	
	public MenuScreen(SpriteBatch spriteBatch, Game game) {
		this.spriteBatch = spriteBatch;
		this.game = game;
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		camera.update();
		backgroundTex = new Texture(Gdx.files.internal("menubg.png"));
	}
	
	public MenuScreen(SpriteBatch spriteBatch, Game game, char winner) {
		this(spriteBatch, game);
		this.winner = winner;
		xWinTex = new Texture(Gdx.files.internal("menuXWin.png"));
		oWinTex = new Texture(Gdx.files.internal("menuOWin.png"));
		tieTex = new Texture(Gdx.files.internal("menuTie.png"));
	}

	@Override
	public void show() {
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		int screenHeight = Gdx.graphics.getHeight();
		
		spriteBatch.begin();
		spriteBatch.draw(backgroundTex, 0, screenHeight - backgroundTex.getHeight());
		if (winner == 'X') {
			spriteBatch.draw(xWinTex, 0, screenHeight - xWinTex.getHeight());
		}
		else if (winner == 'O') {
			spriteBatch.draw(oWinTex, 0, screenHeight - oWinTex.getHeight());
		}
		else if (winner == 'T') {
			spriteBatch.draw(tieTex, 0, screenHeight - tieTex.getHeight());
		}
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ENTER) {
			game.setScreen(new GameScreen(spriteBatch, game));
			return true;
		}
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
