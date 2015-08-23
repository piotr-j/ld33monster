package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.game.processors.physics.Physics;
import io.piotrjastrzebski.monster.screens.Game;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class MapParser extends EntitySystem {
	private final static String TAG = MapParser.class.getSimpleName();

	protected ComponentMapper<Map> mMap;

	public MapParser () {
		super(Aspect.all(Map.class));
		setPassive(true);
	}
	TmxMapLoader mapLoader;
	@Override protected void initialize () {
		super.initialize();
		mapLoader = new TmxMapLoader();

	}

	@Override protected void inserted (int eid) {
		TiledMap map = mMap.get(eid).map;
		TiledMapTileLayer mapLayer = (TiledMapTileLayer)map.getLayers().get(0);
		for (int x = 0; x < mapLayer.getWidth(); x++) {
			for (int y = 0; y < mapLayer.getHeight(); y++) {
				TiledMapTileLayer.Cell cell = mapLayer.getCell(x, y);
				if (cell == null) {
					Gdx.app.log(TAG, "Null cell at " + x + " " + y);
					continue;
				}
				TiledMapTile tile = cell.getTile();
				if (tile == null) {
					Gdx.app.log(TAG, "Null tile at " + x + " " + y);
					continue;
				}
				createFromTile(tile, x, y);
			}
		}
	}

	private void createFromTile (TiledMapTile tile, int x, int y) {
		// TODO masks
		// building
		if (tile.getId() == 6) {
			EntityEdit edit = world.createEntity().edit();
			edit.create(Bounds.class).set(1, 1);
			edit.create(Rotation.class);
			edit.create(Position.class).pos.set(x, y);
			edit.create(PhysDef.class).set(0.2f, 0.3f, 1f).type(BodyDef.BodyType.StaticBody)
				.userData(new Physics.UserData(edit.getEntity()));
		} else
		// water
		if (tile.getId() == 1) {
			EntityEdit edit = world.createEntity().edit();
			edit.create(Bounds.class).set(1, 1);
			edit.create(Rotation.class);
			edit.create(Position.class).pos.set(x, y);
			edit.create(PhysDef.class).set(0.2f, 0.3f, 1f).type(BodyDef.BodyType.StaticBody)
				.userData(new Physics.UserData(edit.getEntity()));
		} else
		// bounds
		if (x == 0 || y == 0 || x == 99 || y == 99) {
			EntityEdit edit = world.createEntity().edit();
			edit.create(Bounds.class).set(1, 1);
			edit.create(Rotation.class);
			edit.create(Position.class).pos.set(x, y);
			edit.create(PhysDef.class).set(0.2f, 0.3f, 1f).type(BodyDef.BodyType.StaticBody)
				.userData(new Physics.UserData(edit.getEntity()));
		} else {
			// 5% for a rat on available tile
			if (MathUtils.random() < 0.05f) {
				createRat(x, y);
			}
		}
	}

	private void createRat (int x, int y) {
		EntityEdit edit = world.createEntity().edit();
		edit.create(Bounds.class).set(0, 0, 16 * Game.INV_SCALE, 16 * Game.INV_SCALE);
		edit.create(Rotation.class).set(0);
		edit.create(Position.class).pos.set(x + MathUtils.random(0, 0.5f), y + MathUtils.random(0, 0.5f));
		edit.create(Movement.class);
		edit.create(Tint.class).set(1, 0, 0);
		edit.create(Facing.class);
		edit.create(AnimDef.class).path("prey/prey_rat_walk").frame(0.25f).count(2).looping(true).mode(Animation.PlayMode.LOOP);
		edit.create(RenderableDef.class).path("prey/prey_rat_walk");
		edit.create(Status.class).health(2);
		edit.create(PhysDef.class)
			.set(0.2f, 0.3f, 1f)
			.type(BodyDef.BodyType.DynamicBody)
			.linearDamping(8f)
			.userData(new Physics.UserData(edit.getEntity()));
	}

	@Override protected void processSystem () {

	}
}
