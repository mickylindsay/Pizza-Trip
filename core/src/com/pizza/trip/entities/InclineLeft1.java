package com.pizza.trip.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class InclineLeft1 extends Entity{
	
	private static final int WIDTH = 64;
	private Texture texture;
	private Sprite sprite;
	protected Body body;
	private int x;
	private int y;
	//private Float[] VERTICIES = [];

	
	public InclineLeft1(World world, String textureLocation, int x, int y, boolean isStatic) {
		super(world, textureLocation, x, y, isStatic);
		
		PolygonShape shape = new PolygonShape();
		//shape.set(vertices, offset, len);

		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		//fixtureDef.friction = .5f;

		body.createFixture(fixtureDef);

		shape.dispose();
	}

}
