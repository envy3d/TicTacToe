/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

public class GameScreen implements Screen, GameOverReceiver {
	private SpriteBatch spriteBatch;
	private AssetManager assets;
	private OrthographicCamera camera;
	private Game game;
	
	private Texture backgroundTex;
	private Texture boardTex;
	private Texture xTex;
	private Texture oTex;
	private Texture turnTex;
	private Texture xWinTex;
	private Texture oWinTex;
	private Texture tieTex;
	
	private Model model;
	private Player player;
	private AI ai;
	private boolean readyToQueueAiTurn;
	
	private int[][] board;
	private int markerSymbol;
	private boolean gameOver;
	private int winner;
	
	public GameScreen(SpriteBatch spriteBatch, AssetManager assets, Game game, int aiDifficulty) {
		this.game = game;
		
		// Set up rendering
		this.spriteBatch = spriteBatch;
		this.assets = assets;
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		camera.update();
		backgroundTex = assets.get("paperBg.png");
		boardTex = assets.get("board.png");
		xTex = assets.get("X.png");
		oTex = assets.get("O.png");
		turnTex = assets.get("underline.png");
		xWinTex = assets.get("xWin.png");
		oWinTex = assets.get("oWin.png");
		tieTex = assets.get("tie.png");
		
		// Set up logic
		model = new Model();
		model.attachGameOverReceiver(this);
		player = new Player(1, model);
		Gdx.input.setInputProcessor(player);
		readyToQueueAiTurn = true;
		board = Board.buildEmptyBoard();
		gameOver = false;
		winner = 0;
		switch (aiDifficulty) {
			case 1:
				ai = new AI(-1, model);
				break;
			case 2:
				ai = new SimpleAI(-1, model);
				break;
			default:
				ai = new SimpleAI(-1, model);
		}
		
		markerSymbol = MathUtils.randomBoolean() == true ? 1 : -1;
	}
	
	
	@Override
	public void show() {
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void render(float delta) {
		if (model.playerTurn == -1 && readyToQueueAiTurn) {
			readyToQueueAiTurn = false;
			queueAiTurn();
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		model.getBoard(board);
		spriteBatch.begin();
		spriteBatch.draw(backgroundTex, 0, 0);
		spriteBatch.draw(boardTex, 0, 0);
		
		for (int positionY = 0; positionY < board.length; ++positionY) {
			for (int positionX = 0; positionX < board[positionY].length; ++positionX) {
				
				// When markerSymbol equals 1, the player is X. When markerSymbol equals -1, the player is O.
				if (board[positionY][positionX] == markerSymbol) {
					spriteBatch.draw(xTex, positionX * xTex.getWidth() + 40, 315 - (positionY * xTex.getHeight()));
				}
				else if (board[positionY][positionX] == markerSymbol * -1) {
					spriteBatch.draw(oTex, positionX * oTex.getWidth() + 40, 315 - (positionY * oTex.getHeight()));
				}
			}
		}
		if (markerSymbol == 1) {
			spriteBatch.draw(xTex, 480, 285);
			spriteBatch.draw(oTex, 480, 60);
		}
		else {
			spriteBatch.draw(oTex, 480, 285);
			spriteBatch.draw(xTex, 480, 60);
		}
		if (model.playerTurn == 1) {
			spriteBatch.draw(turnTex, 460, 255);
		}
		else if (model.playerTurn == -1) {
			spriteBatch.draw(turnTex, 460, 30);
		}
		
		if (gameOver) {
			if (winner * markerSymbol == 1) {
				spriteBatch.draw(xWinTex, 20, 20);
			}
			else if (winner * markerSymbol == -1) {
				spriteBatch.draw(oWinTex, 20, 20);
			}
			else {
				spriteBatch.draw(tieTex, 20, 20);
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
	
	private void queueAiTurn() {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				ai.update();
				readyToQueueAiTurn = true;
			}
		}, 1.5f);
	}

	@Override
	public void gameOver(int winner) {
		this.winner = winner;
		gameOver = true;
		
		// Set the playerTurn to an invalid player to prevent the AI or human player from attempting to play.
		model.playerTurn = 0;
		
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				switchToGameOverMenuScreen();
			}
		}, 2.5f);
	}

	private void switchToGameOverMenuScreen() {		
		MenuScreen menuScreen = new MenuScreen(spriteBatch, assets, game);
		Gdx.input.setInputProcessor(menuScreen);
		game.setScreen(menuScreen);
	}
}
