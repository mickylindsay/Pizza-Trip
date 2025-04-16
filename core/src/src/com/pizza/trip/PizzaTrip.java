package com.pizza.trip;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.pizza.trip.level.Level;

public class PizzaTrip extends Game {
	float delta;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;

	private Level level;

	@Override
	public void create() {
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		level = new Level();
		level.createBoundry();
		level.createEntity("entity.png", 192, 64, true);
		level.createEntity("entity.png", 320, 128, true);
		level.createPlayer(100, 100);

	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix();

		update();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//debugRenderer.render(level.getWorld(), debugMatrix);
		batch.begin();
		level.render(batch);
		batch.end();

	}

	public void update() {
		level.update(Gdx.graphics.getDeltaTime());
		camera.position.set(level.getPlayer().getX(), level.getPlayer().getY(),
				0);
		camera.update();
	}
}
