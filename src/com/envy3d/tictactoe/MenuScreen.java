/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen, InputProcessor {
	
	private SpriteBatch spriteBatch;
	private AssetManager assets;
	private OrthographicCamera camera;
	private Game game;
	
	private Texture backgroundTex;
	private Texture menuTex;
	private Sprite ai1;
	private Sprite ai2;
	private Sprite aiMouseOver;
	
	private char winner;
	
	public MenuScreen(SpriteBatch spriteBatch, AssetManager assets, Game game) {
		this.spriteBatch = spriteBatch;
		this.assets = assets;
		this.game = game;
		
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		camera.update();
		backgroundTex = assets.get("paperBg.png");
		menuTex = assets.get("menu.png");
		ai1 = new Sprite(assets.get("ai1.png", Texture.class));
		ai1.setPosition(120, 95);
		ai2 = new Sprite(assets.get("ai2.png", Texture.class));
		ai2.setPosition(120, 15);
		aiMouseOver = new Sprite(assets.get("aiHighlight.png", Texture.class));
		aiMouseOver.setPosition(0, -200);
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
		spriteBatch.draw(menuTex, 0, screenHeight - menuTex.getHeight());
		ai1.draw(spriteBatch);
		ai2.draw(spriteBatch);
		aiMouseOver.draw(spriteBatch);
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
		screenY = (screenY * -1) + Gdx.graphics.getHeight();
		if (screenX >= ai1.getX() && screenX <= ai1.getX() + ai1.getWidth() && screenY >= ai1.getY() && screenY <= ai1.getY() + ai1.getHeight()) {
			game.setScreen(new GameScreen(spriteBatch, assets, game, 1));
			return true;
		}
		else if (screenX >= ai2.getX() && screenX <= ai2.getX() + ai2.getWidth() && screenY >= ai2.getY() && screenY <= ai2.getY() + ai2.getHeight()) {
			game.setScreen(new GameScreen(spriteBatch, assets, game, 2));
			return true;
		}
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
		screenY = (screenY * -1) + Gdx.graphics.getHeight();
		if (screenX >= ai1.getX() && screenX <= ai1.getX() + ai1.getWidth() && screenY >= ai1.getY() && screenY <= ai1.getY() + ai1.getHeight()) {
			aiMouseOver.setPosition(ai1.getX(), ai1.getY());
		}
		else if (screenX >= ai2.getX() && screenX <= ai2.getX() + ai2.getWidth() && screenY >= ai2.getY() && screenY <= ai2.getY() + ai2.getHeight()) {
			aiMouseOver.setPosition(ai2.getX(), ai2.getY());
		}
		else {
			aiMouseOver.setPosition(0, -200);
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
