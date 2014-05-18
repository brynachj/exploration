package com.brynachj.exploration;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainCamera extends OrthographicCamera {
	
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	private boolean zoomIn;
	private boolean zoomOut;
	
	private float maxZoom = 10f;
	private float minZoom = 0.5f;
	
	MainCamera(int width, int height){
		super(width, height);
		
		up = false;
		down = false;
		right = false;
		left = false;
	}

	public void updateMovements() {
		updateCameraX();
		updateCameraY();
		updateZoom();
		this.update();
	}

	private void updateCameraX() {
		if(isRight()){
			this.translate(5,0);
		}else if(isLeft()){
			this.translate(-5,0);
		}	
	}

	private void updateCameraY() {
		if(isUp()){
			this.translate(0,5);
		}else if(isDown()){
			this.translate(0,-5);
		}			
	}
	
	
	private void updateZoom() {
		if(isZoomIn() && getZoom() > minZoom){
			setZoom(getZoom() - 0.01f);
		}
		if(isZoomOut() && getZoom() < maxZoom){
			setZoom(getZoom() + 0.01f);
		}
	}
	
	/***********************
	 * Getters and Setters * 
	 ***********************/
	
	private void setZoom(float f) {
		this.zoom = f;
	}

	private float getZoom() {
		return this.zoom;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isZoomIn() {
		return zoomIn;
	}

	public void setZoomIn(boolean zoomIn) {
		this.zoomIn = zoomIn;
	}

	public boolean isZoomOut() {
		return zoomOut;
	}

	public void setZoomOut(boolean zoomOut) {
		this.zoomOut = zoomOut;
	}
}
