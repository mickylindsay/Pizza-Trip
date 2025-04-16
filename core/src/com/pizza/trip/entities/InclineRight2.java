package com.pizza.trip.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class InclineRight2 extends Entity{
	
	private static final int WIDTH = 64;
	private Texture texture;
	private Sprite sprite;
	protected Body body;
	private int x;
	private int y;
	//private Float[] VERTICIES = [];

	//TODO
	public InclineRight2(World world, String textureLocation, int x, int y, boolean isStatic) {
		super(world, textureLocation, x, y, isStatic);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(WIDTH / 2, WIDTH / 2);

		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		//fixtureDef.friction = .5f;

		body.createFixture(fixtureDef);

		shape.dispose();
	}

}
