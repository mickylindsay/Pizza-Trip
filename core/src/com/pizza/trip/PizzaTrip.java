package com.pizza.trip;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pizza.trip.entities.Entity;
import com.pizza.trip.entities.InclineRight3;
import com.pizza.trip.level.Level;
import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;

public class PizzaTrip extends Game {
	float delta;
	public final int width = 854;
	public final int height = 480;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;
	private TmxMapLoader loader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	

	private Level level;

	@Override
	public void create() {
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		level = new Level();
		level.createBoundry();
		level.createPlayer(100, 100);
		
		//Creating the tmx map loader for premade maps
		loader = new TmxMapLoader();
		map = loader.load("Test Level.tmx");
		//Renderer for the tmx maps
		renderer = new OrthogonalTiledMapRenderer(map);
		
		BodyDef bDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fDef = new FixtureDef();
		Entity body;
		
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() /2);

			level.createEntity("emptyTexture.png", (int)bDef.position.x, (int) bDef.position.y, true);
			
			
		}
		
		for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() /2);

			//InclineRight3.createEntity("emptyTexture.png", (int)bDef.position.x, (int) bDef.position.y, true);
			
			
		}
		
		/* for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() /2);

			level.createEntity("emptyTexture.png", (int)bDef.position.x, (int) bDef.position.y, true);
			
			
		}
		
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bDef.type = BodyDef.BodyType.StaticBody;
			bDef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() /2);

			level.createEntity("emptyTexture.png", (int)bDef.position.x, (int) bDef.position.y, true);
			
			
		} */

	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		debugMatrix = batch.getProjectionMatrix();

		update();
		

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		renderer.render();
		debugRenderer.render(level.getWorld(), debugMatrix);
		//level.render(batch);
		batch.end();

	}

	public void update() {
		level.update(Gdx.graphics.getDeltaTime());
		renderer.setView(camera);
		camera.position.set(level.getPlayer().getX(), level.getPlayer().getY(),
				0);
		camera.update();
	}
}
