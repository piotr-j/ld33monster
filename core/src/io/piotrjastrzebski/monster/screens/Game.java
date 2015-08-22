package io.piotrjastrzebski.monster.screens;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.game.processors.*;
import io.piotrjastrzebski.monster.utils.Locator;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Game extends Base {
	public static final String WIRE_GAME_CAM = "game-cam";
	public static final String WIRE_GAME_VP = "game-vp";
	public static final String WIRE_GUI_CAM = "gui-cam";
	public static final String WIRE_GUI_VP = "gui-vp";

	protected World world;
	protected InputMultiplexer multiplexer;

	public Game (Locator locator) {
		super(locator);
		multiplexer = new InputMultiplexer();
		WorldConfiguration config = new WorldConfiguration();
		config.register(WIRE_GAME_CAM, gameCamera);
		config.register(WIRE_GAME_VP, gameViewport);
		config.register(WIRE_GUI_CAM, guiCamera);
		config.register(WIRE_GUI_VP, guiViewport);
		config.register(locator.shapeRenderer);
		config.register(locator.batch);
		config.register(locator.assets);
		config.register(multiplexer);

		config.setSystem(new PlayerController());

		config.setSystem(new Mover());
		config.setSystem(new BoundsUpdater());
		config.setSystem(new Renderer());
		config.setSystem(new DebugRenderer());

		world = new World(config);

		EntityEdit edit = world.createEntity().edit();
		edit.create(Bounds.class).set(0, 0, 1, 1);
		edit.create(Rotation.class).set(0);
		edit.create(Position.class).pos.set(5, 5);
		edit.create(Movement.class).setFriction(0.05f);
		edit.create(Player.class).setMoveAcc(5f, 5f);

		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override public void render (float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.delta = delta;
		world.process();
	}

	@Override public void resize (int width, int height) {
		super.resize(width, height);
		// todo handle resize
	}

	@Override public void dispose () {
		super.dispose();
		world.dispose();
	}
}
