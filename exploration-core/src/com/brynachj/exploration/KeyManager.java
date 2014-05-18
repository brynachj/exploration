package com.brynachj.exploration;

import com.badlogic.gdx.Input.Keys;
import com.brynachj.exploration.buildings.TownCenter;

public class KeyManager {

	public TownCenter keycodeSwitcher(int keycode, TownCenter selectedActor, TownCenter[] actors) {
		
		if(keycode == Keys.W){
			selectedActor.setAcceleratingUp(true);
		}
		if(keycode == Keys.S){
			selectedActor.setAcceleratingDown(true);
		}
		if(keycode == Keys.A){
			selectedActor.setAcceleratingLeft(true);
		}
		if(keycode == Keys.D){
			selectedActor.setAcceleratingRight(true);
		}
		
		if(keycode == Keys.O){
			selectedActor.setX(0);
			selectedActor.setY(0);
		}
		
		if(keycode == Keys.TAB){
			selectedActor.setSelectedActor(false);
			
			if(selectedActor.equals(actors[0])){
				selectedActor = actors[1];
			}else{
				selectedActor = actors[0];
			}
			System.out.println("Switched selectedObject");
			for(TownCenter actor : actors) {
				actor.setAllCommands(false);
			}
			selectedActor.setSelectedActor(true);
		}
		
		return selectedActor;
	}
	
}
