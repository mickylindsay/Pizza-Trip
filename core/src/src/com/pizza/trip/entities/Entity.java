package com.pizza.trip.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Entity {
	private static final int WIDTH = 63;
	private Texture texture;
	private Sprite sprite;
	protected Body body;

	FixtureDef fixtureDef;

	public Entity(World world, String textureLocation, int x, int y,
			boolean isStatic) {
		texture = new Texture(Gdx.files.internal(textureLocation));

		sprite = new Sprite(texture);
		sprite.setBounds(x, y, WIDTH, WIDTH);
		BodyDef bodyDef = new BodyDef();
		if (isStatic) {
			bodyDef.type = BodyDef.BodyType.StaticBody;
		} else {
			bodyDef.type = BodyDef.BodyType.DynamicBody;
		}
		bodyDef.position.set(sprite.getX(), sprite.getY());
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(WIDTH / 2, WIDTH / 2);

		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = 5f;

		body.createFixture(fixtureDef);

		shape.dispose();

	}

	public void update(float delta) {
		sprite.setRotation((float) Math.toDegrees(body.getAngle()));
		sprite.setPosition(body.getPosition().x - 32, body.getPosition().y - 32);
	}

	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}

	public void dispose() {
		texture.dispose();
	}

	public int getX() {
		return (int) sprite.getX();
	}

	public int getY() {
		return (int) sprite.getY();
	}
}
