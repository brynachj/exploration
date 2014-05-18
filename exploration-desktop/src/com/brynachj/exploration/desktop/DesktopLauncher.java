package com.brynachj.exploration.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brynachj.exploration.Exploration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "camera";
		config.useGL30 = false;
		config.width = 1280;
		config.height = 720;
		
		new LwjglApplication(new Exploration(), config);
	}
}
