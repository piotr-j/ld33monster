package io.piotrjastrzebski.monster.screens;

import com.artemis.EntityEdit;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.game.processors.*;
import io.piotrjastrzebski.monster.game.processors.physics.PhysMaker;
import io.piotrjastrzebski.monster.game.processors.physics.Physics;
import io.piotrjastrzebski.monster.game.processors.physics.PhysicsContacts;
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

		config.setSystem(new AssetInit());
		config.setSystem(new AnimInit());
		config.setSystem(new Physics());
		config.setManager(new PhysicsContacts());
		config.setSystem(new PhysMaker());
		config.setSystem(new PlayerController());
		config.setSystem(new Mover());
		config.setSystem(new PhysUpdater());
		config.setSystem(new PlayerFollower());
		config.setSystem(new FacingRotator());
		config.setSystem(new MoveAnimator());
		config.setSystem(new MapLoader());
		config.setSystem(new MapParser());
		config.setSystem(new MapRenderer());
		config.setSystem(new Renderer());
//		config.setSystem(new DebugRenderer());
		config.setSystem(new Box2dRenderer());

		world = new World(config);

		EntityEdit mapEE = world.createEntity().edit();
		mapEE.create(MapDef.class).path = "map/testmap.tmx";

		EntityEdit edit = world.createEntity().edit();
		edit.create(Bounds.class).set(0, 0, 16 * INV_SCALE, 16 * INV_SCALE);
		edit.create(Rotation.class).set(0);
		edit.create(Position.class).set(32, 32);
		edit.create(Movement.class);
		edit.create(Player.class).setAccel(0.25f);
		edit.create(Tint.class).set(1, 0, 0);
		edit.create(Facing.class);
		edit.create(AnimDef.class).path("monster/monster_baby_walk").frame(0.25f).count(2).looping(true).mode(Animation.PlayMode.LOOP);
		edit.create(RenderableDef.class).path("monster/monster_baby_walk");
		edit.create(PhysDef.class)
			.set(0.2f, 0.3f, 1f)
			.type(BodyDef.BodyType.DynamicBody)
			.linearDamping(8f);

		for (int i = 0; i < 50; i++) {
			createCollider();
		}

		for (int i = 0; i < 50; i++) {
			createRat();
		}

		Gdx.input.setInputProcessor(multiplexer);
	}

	private void createRat () {
		EntityEdit edit = world.createEntity().edit();
		edit.create(Bounds.class).set(0, 0, 16 * INV_SCALE, 16 * INV_SCALE);
		edit.create(Rotation.class).set(0);
		edit.create(Position.class).pos.set(MathUtils.random(0, 100), MathUtils.random(0, 100));
		edit.create(Movement.class);
		edit.create(Tint.class).set(1, 0, 0);
		edit.create(Facing.class);
		edit.create(AnimDef.class).path("prey/prey_rat_walk").frame(0.25f).count(2).looping(true).mode(Animation.PlayMode.LOOP);
		edit.create(RenderableDef.class).path("prey/prey_rat_walk");
		edit.create(PhysDef.class)
			.set(0.2f, 0.3f, 1f)
			.type(BodyDef.BodyType.DynamicBody)
			.linearDamping(8f);
	}

	private void createCollider () {
		EntityEdit edit = world.createEntity().edit();
		edit.create(Bounds.class).set(MathUtils.random(0.5f, 3), MathUtils.random(0.5f, 3));
		edit.create(Rotation.class);
		edit.create(Position.class).pos.set(MathUtils.random(-18, 18), MathUtils.random(-10, 10));
		edit.create(PhysDef.class).set(0.2f, 0.3f, 1f).type(BodyDef.BodyType.StaticBody);
		edit.create(Tint.class).set(0, 1, 0);
	}

	@Override public void render (float delta) {
		super.render(delta);
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
