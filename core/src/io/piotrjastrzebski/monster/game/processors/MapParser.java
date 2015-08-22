package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import io.piotrjastrzebski.monster.game.components.*;

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
		if (tile.getId() == 6) {
			EntityEdit edit = world.createEntity().edit();
			edit.create(Bounds.class).set(1, 1);
			edit.create(Rotation.class);
			edit.create(Position.class).pos.set(x, y);
			edit.create(PhysDef.class).set(0.2f, 0.3f, 1f).type(BodyDef.BodyType.StaticBody);
		}
	}

	@Override protected void processSystem () {

	}
}
