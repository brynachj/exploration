package com.brynachj.exploration.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TownCenter extends Actor { 

	private float topSpeed;
	private float currentSpeedX;
	private float currentSpeedY;
	private float acceleration;
	private float deceleration;
	private Animation animation;
	private TextureAtlas textureAtlas;
	private Texture backgroundTexture;
	boolean controllable;
	boolean selectedActor;

	private boolean acceleratingUp;
	private boolean acceleratingDown;
	private boolean acceleratingRight;
	private boolean acceleratingLeft;
	
	private float elapsedTime = 0f;


	public TownCenter(float topSpeed, float acceleration, float deceleration) {
		controllable = true;
		selectedActor = false;
		
		currentSpeedX = 0f;
		currentSpeedY = 0f;
		
		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.deceleration = deceleration;
				
		acceleratingDown = false; 
		acceleratingLeft = false;
		acceleratingRight = false;
		acceleratingUp = false;

		// TODO: Create texture.class to hold single instance to each spritesheet so you don't new one up each time you create a structure
		textureAtlas = new TextureAtlas("townCenterSpriteSheet.atlas");

		animation = new Animation(1/15f, textureAtlas.getRegions());
		setBounds(getX(), getY(), textureAtlas.getRegions().get(0).getRegionWidth(), textureAtlas.getRegions().get(0).getRegionHeight());
		
		setUpAnimations();
		
		backgroundTexture = new Texture("backgroundImage.png");
	}

    @Override
    public void draw(Batch batch, float alpha){
    	if(selectedActor) {
    		batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());
    	}
		elapsedTime += Gdx.graphics.getDeltaTime();

    	batch.draw(getAnimation().getKeyFrame(elapsedTime, true), getX(), getY());
    	}
	
	private void setUpAnimations() {
		TextureRegion[] glowInAndOut = new TextureRegion[21];

		glowInAndOut[0] = (getTextureAtlas().findRegion("TownCenterSprite50"));
		glowInAndOut[1] = (getTextureAtlas().findRegion("TownCenterSprite55"));
		glowInAndOut[2] = (getTextureAtlas().findRegion("TownCenterSprite60"));
		glowInAndOut[3] = (getTextureAtlas().findRegion("TownCenterSprite65"));
		glowInAndOut[4] = (getTextureAtlas().findRegion("TownCenterSprite70"));
		glowInAndOut[5] = (getTextureAtlas().findRegion("TownCenterSprite75"));
		glowInAndOut[6] = (getTextureAtlas().findRegion("TownCenterSprite80"));
		glowInAndOut[7] = (getTextureAtlas().findRegion("TownCenterSprite85"));
		glowInAndOut[8] = (getTextureAtlas().findRegion("TownCenterSprite90"));
		glowInAndOut[9] = (getTextureAtlas().findRegion("TownCenterSprite95"));
		glowInAndOut[10] = (getTextureAtlas().findRegion("TownCenterSprite100"));
		glowInAndOut[11] = (getTextureAtlas().findRegion("TownCenterSprite95"));
		glowInAndOut[12] = (getTextureAtlas().findRegion("TownCenterSprite90"));
		glowInAndOut[13] = (getTextureAtlas().findRegion("TownCenterSprite85"));
		glowInAndOut[14] = (getTextureAtlas().findRegion("TownCenterSprite80"));
		glowInAndOut[15] = (getTextureAtlas().findRegion("TownCenterSprite75"));
		glowInAndOut[16] = (getTextureAtlas().findRegion("TownCenterSprite70"));
		glowInAndOut[17] = (getTextureAtlas().findRegion("TownCenterSprite65"));
		glowInAndOut[18] = (getTextureAtlas().findRegion("TownCenterSprite60"));
		glowInAndOut[19] = (getTextureAtlas().findRegion("TownCenterSprite55"));
		glowInAndOut[20] = (getTextureAtlas().findRegion("TownCenterSprite50"));

		setAnimation(new Animation(0.1f, glowInAndOut));
	}

	public void updateAccelerations() {
		updateAccelerationsX();
		updateAccelerationsY();
	}
	
	public void updateAccelerationsX() {
		if(isAcceleratingRight()){
			setCurrentSpeedX(Math.min(topSpeed, currentSpeedX + acceleration * Gdx.graphics.getDeltaTime()));
			setX(getX() + getCurrentSpeedX() * Gdx.graphics.getDeltaTime());
			System.out.println("Straight Right");
		}else if(isAcceleratingLeft()){
			setCurrentSpeedX(Math.max(-topSpeed, currentSpeedX - acceleration * Gdx.graphics.getDeltaTime()));
			setX(getX() + getCurrentSpeedX() * Gdx.graphics.getDeltaTime());
			System.out.println("Straight Left");
		}else{
			slowToStopX();
		}
	}

	public void updateAccelerationsY() {
		if(isAcceleratingUp()){
			setCurrentSpeedY(Math.min(topSpeed, currentSpeedY + acceleration * Gdx.graphics.getDeltaTime()));
			setY(getY() + getCurrentSpeedY() * Gdx.graphics.getDeltaTime());
			System.out.println("Straight Up");
		}else if(isAcceleratingDown()){
			setCurrentSpeedY(Math.max(-topSpeed, currentSpeedY - acceleration * Gdx.graphics.getDeltaTime()));
			setY(getY() + getCurrentSpeedY() * Gdx.graphics.getDeltaTime());
			System.out.println("Straight Down");
		}else{
			slowToStopY();
		}

	}
	private void slowToStopX() {
		if(getCurrentSpeedX() > 0){
			setCurrentSpeedX(currentSpeedX - deceleration * Gdx.graphics.getDeltaTime());
			setX(getX() + getCurrentSpeedX() * Gdx.graphics.getDeltaTime());
			System.out.println("Slowly Right");
		}
		if(getCurrentSpeedX() < 0){
			setCurrentSpeedX(currentSpeedX + deceleration * Gdx.graphics.getDeltaTime());
			setX(getX() + getCurrentSpeedX() * Gdx.graphics.getDeltaTime());
			System.out.println("Slowly Left");
		}
		if(Math.abs(getCurrentSpeedX()) < 0.25){
			setCurrentSpeedX(0);
		}

	}

	private void slowToStopY() {
		if(getCurrentSpeedY() > 0){
			setCurrentSpeedY(currentSpeedY - deceleration * Gdx.graphics.getDeltaTime());
			setY(getY() + getCurrentSpeedY() * Gdx.graphics.getDeltaTime());
			System.out.println("Slowly Up");
		}
		if(getCurrentSpeedY() < 0){
			setCurrentSpeedY(currentSpeedY + deceleration * Gdx.graphics.getDeltaTime());
			setY(getY() + getCurrentSpeedY() * Gdx.graphics.getDeltaTime());
			System.out.println("Slowly Down");
		}

		if(Math.abs(getCurrentSpeedY()) < 0.25){
			setCurrentSpeedY(0);
		}

	}
	
	@Override
	public TownCenter hit (float x, float y, boolean touchable) {
		System.out.println("I've been hit!!");

		if (touchable && getTouchable() != Touchable.enabled) return null;
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ? this : null;
	}

	/***********************
	 * Getters and Setters * 
	 ***********************/

	public Animation getAnimation() {
		return this.animation;
	}

	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	public float getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(float topSpeed) {
		this.topSpeed = topSpeed;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public float getCurrentSpeedX() {
		return currentSpeedX;
	}

	public void setCurrentSpeedX(float currentSpeed) {
		this.currentSpeedX = currentSpeed;
	}

	public float getCurrentSpeedY() {
		return currentSpeedY;
	}

	public void setCurrentSpeedY(float currentSpeedY) {
		this.currentSpeedY = currentSpeedY;
	}

	public boolean isAcceleratingUp() {
		return acceleratingUp;
	}

	public void setAcceleratingUp(boolean acceleratingUp) {
		this.acceleratingUp = acceleratingUp;
	}

	public boolean isAcceleratingDown() {
		return acceleratingDown;
	}

	public void setAcceleratingDown(boolean acceleratingDown) {
		this.acceleratingDown = acceleratingDown;
	}

	public boolean isAcceleratingRight() {
		return acceleratingRight;
	}

	public void setAcceleratingRight(boolean acceleratingRight) {
		this.acceleratingRight = acceleratingRight;
	}

	public boolean isAcceleratingLeft() {
		return acceleratingLeft;
	}

	public void setAcceleratingLeft(boolean acceleratingLeft) {
		this.acceleratingLeft = acceleratingLeft;
	}

	public void setAllCommands(boolean allAcceleration) {
		acceleratingDown = allAcceleration;
		acceleratingRight = allAcceleration;
		acceleratingLeft = allAcceleration;
		acceleratingUp = allAcceleration;
		
	}
	
	public boolean isSelectedActor() {
		return selectedActor;
	}

	public void setSelectedActor(boolean selectedActor) {
		this.selectedActor = selectedActor;
	}
}