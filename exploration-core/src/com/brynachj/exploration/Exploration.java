package com.brynachj.exploration;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brynachj.exploration.buildings.TownCenter;

public class Exploration extends ApplicationAdapter implements InputProcessor {

	private SpriteBatch batch;
	private float elapsedTime = 0f;
	private TownCenter selectedActor;
	private TownCenter otherActor;
	private TownCenter firstActor; 
	private TownCenter[] actors;
	private KeyManager keyManager;
	private ShapeRenderer shapeRenderer;
	
	private Stage stage;
	
	private MainCamera camera;
	
	@Override
	public void create () {
		camera = new MainCamera(1280, 720);

		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera), null);
		
		batch = new SpriteBatch();
		
		firstActor = new TownCenter(200, 20, 10);
		otherActor = new TownCenter(400, 40, 20);
		
		actors = new TownCenter[2];
		actors[0] = firstActor;
		actors[1] = otherActor;
		
		selectedActor = actors[0];
		actors[0].setSelectedActor(true);
		
		keyManager = new KeyManager();
		shapeRenderer = new ShapeRenderer();
		
		for(TownCenter actor : actors){
			stage.addActor(actor);
			System.out.println("Actor " + actor + " added");
		}
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		// Drawing and updating Actors
		
		elapsedTime += Gdx.graphics.getDeltaTime(); // TODO: Need to make this a system-wide constant somehow!
		
		for (TownCenter actor : actors){
			actor.updateAccelerations();

		}
		
		stage.draw();
		
		camera.updateMovements();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		selectedActor.getTextureAtlas().dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		
		selectedActor = keyManager.keycodeSwitcher(keycode, selectedActor, actors);
		
		if(keycode == Keys.UP){
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
				camera.setZoomIn(true);
			}
			else
			camera.setUp(true);
		}
		if(keycode == Keys.DOWN){
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
				camera.setZoomOut(true);
			}
			else
			camera.setDown(true);
		}
		if(keycode == Keys.LEFT){
			camera.setLeft(true);
		}
		if(keycode == Keys.RIGHT){
			camera.setRight(true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO: Call keyManager method here instead
		if(keycode == Keys.W){
			selectedActor.setAcceleratingUp(false);
		}
		if(keycode == Keys.S){
			selectedActor.setAcceleratingDown(false);
		}
		if(keycode == Keys.A){
			selectedActor.setAcceleratingLeft(false);
		}
		if(keycode == Keys.D){
			selectedActor.setAcceleratingRight(false);
		}
		if(keycode == Keys.UP){
			camera.setZoomIn(false);
			camera.setUp(false);
		}
		if(keycode == Keys.DOWN){
			camera.setZoomOut(false);
			camera.setDown(false);
		}
		if(keycode == Keys.LEFT){
			camera.setLeft(false);
		}
		if(keycode == Keys.RIGHT){
			camera.setRight(false);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
