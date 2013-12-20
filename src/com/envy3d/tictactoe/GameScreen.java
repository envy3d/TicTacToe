/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class GameScreen implements Screen, GameOverReceiver {
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Game game;
	
	private Texture boardTex;
	private Texture xTex;
	private Texture oTex;
	
	private Model model;
	private Player player;
	private AI ai;
	
	private int[][] board;
	private int markerSymbol;
	
	public GameScreen(SpriteBatch spriteBatch, Game game) {
		this.spriteBatch = spriteBatch;
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		camera.update();
		boardTex = new Texture(Gdx.files.internal("board.png"));
		xTex = new Texture(Gdx.files.internal("X.png"));
		oTex = new Texture(Gdx.files.internal("O.png"));
		
		model = new Model();
		model.attachGameOverReceiver(this);
		player = new Player(1, model, boardTex.getWidth() / TicTacToeGame.WIDTH, boardTex.getHeight() / TicTacToeGame.HEIGHT);
		Gdx.input.setInputProcessor(player);
		ai = new SimpleAI(-1, model);
		
		board = new int[TicTacToeGame.HEIGHT][];
		for (int rowIndex = 0; rowIndex < TicTacToeGame.HEIGHT; ++rowIndex) {
			board[rowIndex] = new int[TicTacToeGame.WIDTH];
		}
		
		markerSymbol = MathUtils.random(1) == 1 ? 1 : -1;
	}
	
	@Override
	public void show() {
		spriteBatch.setProjectionMatrix(camera.combined);
		
		if (markerSymbol == 1)
			player.isTurn = true;
		else 
			ai.isTurn = true;
	}
	
	@Override
	public void render(float delta) {
		if (player.isTurn == false) {
			ai.isTurn = true;
			ai.update();
			if (ai.isTurn == false) {
				player.isTurn = true;
			}
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		model.getBoard(board);
		spriteBatch.begin();
		spriteBatch.draw(boardTex, 0, 0);
		
		for (int positionY = 0; positionY < board.length; ++positionY) {
			for (int positionX = 0; positionX < board[positionY].length; ++positionX) {
				
				// When markerSymbol equals 1, the player is X. When markerSymbol equals -1, the player is O.
				if (board[positionY][positionX] == 1 * markerSymbol) {
					spriteBatch.draw(xTex, positionX * xTex.getWidth() + positionX * 4, positionY * xTex.getHeight() + positionY * 4);
				}
				else if (board[positionY][positionX] == -1 * markerSymbol) {
					spriteBatch.draw(oTex, positionX * oTex.getWidth() + positionX * 4, positionY * oTex.getHeight() + positionY * 4);
				}
			}
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
		boardTex.dispose();
		xTex.dispose();
		oTex.dispose();
	}

	@Override
	public void gameOver(int winnerMarker) {
		char winnerSymbol = 'T';
		if (winnerMarker * markerSymbol == 1)
			winnerSymbol = 'X';
		else if (winnerMarker * markerSymbol == -1)
			winnerSymbol = 'O';
		
		MenuScreen menuScreen = new MenuScreen(spriteBatch, game, winnerSymbol);
		Gdx.input.setInputProcessor(menuScreen);
		game.setScreen(menuScreen);
	}

}
