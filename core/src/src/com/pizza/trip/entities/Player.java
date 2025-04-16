package com.pizza.trip.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Entity {
	// private boolean ascending;
	// private boolean descending;
	// private boolean standing;
	private int standingCount;

	public Player(World world, String textureLocation, int x, int y,
			boolean isStatic) {
		super(world, "mainBase.png", x, y, isStatic);
		standingCount = 0;
	}

	public Body getBody() {
		return body;
	}

	public void update(float delta) {
		super.update(delta);
		if (body.getLinearVelocity().y < .1
				&& body.getLinearVelocity().y > -.1) {
			standingCount++;
		}
		/*
		 * if (body.linVelWorld.y > 0) { ascending = true; descending = false;
		 * standing = false; } else if (body.linVelWorld.y < 0) { ascending =
		 * false; descending = true; standing = false; }else
		 * if(body.linVelWorld.y == 0 &&descending){ ascending = false;
		 * descending = false; standing = true; }
		 */

	}

	public boolean isStanding() {
		return standingCount > 5;
	}

	public void jump() {
		if (isStanding()) {
			standingCount = 0;
			body.setLinearVelocity(body.getLinearVelocity().x, 500f);
		}
	}
}
