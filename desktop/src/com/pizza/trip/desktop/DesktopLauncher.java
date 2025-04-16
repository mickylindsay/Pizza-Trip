package com.pizza.trip.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pizza.trip.Constant;
import com.pizza.trip.PizzaTrip;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constant.WIDTH;
		config.height = Constant.HEIGHT;
		new LwjglApplication(new PizzaTrip(), config);
	}
}
