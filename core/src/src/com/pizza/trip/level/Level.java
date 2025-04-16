package com.pizza.trip.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.Controller;
import com.pizza.trip.entities.Entity;
import com.pizza.trip.entities.Player;
import com.pizza.controller.XBox360Pad;

public class Level {
	private ArrayList<Entity> entities;
	private Player player;
	private World world;
	private Controller controller;

	public Level() {
		world = new World(new Vector2(0, -128), true);
		entities = new ArrayList<Entity>();
		controller = Controllers.getControllers().first();

	}

	public void createBoundry() {
		for (int i = 0; i < 10; i++) {
			createEntity("entity.png", i * 64, 0, true);
		}
	}

	public void createEntity(String textureLocation, int x, int y,
			boolean isStatic) {
		entities.add(new Entity(world, textureLocation, x, y, isStatic));
	}

	public void createPlayer(int x, int y) {
		player = new Player(world, "entity.png", x, y, false);
	}

	public World getWorld() {
		return world;
	}

	public Player getPlayer() {
		return player;
	}

	public void update(float delta) {
		world.step(delta, 20, 2);
		player.update(delta);

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A) || controller.getAxis(XBox360Pad.AXIS_LEFT_X) < -0.1f) {
			player.getBody().setLinearVelocity(-60f,
					player.getBody().getLinearVelocity().y);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D) || controller.getAxis(XBox360Pad.AXIS_LEFT_X) > 0.1f) {
			player.getBody().setLinearVelocity(60f,
					player.getBody().getLinearVelocity().y);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || controller.getButton(XBox360Pad.BUTTON_A)) {
			player.jump();
		}
		if (Gdx.input.isButtonPressed(Input.Keys.Q)) {
			player.getBody().setTransform(player.getX() + 100, player.getY(),
					player.getBody().getAngle() + 20);
		}
	}

	public void render(SpriteBatch batch) {
		player.render(batch);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(batch);
		}

	}

}
