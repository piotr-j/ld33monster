package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import io.piotrjastrzebski.monster.game.components.*;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class MapLoader extends EntitySystem {
	protected ComponentMapper<MapDef> mMapDef;

	public MapLoader () {
		super(Aspect.all(MapDef.class));
		setPassive(true);
	}
	TmxMapLoader mapLoader;
	@Override protected void initialize () {
		super.initialize();
		mapLoader = new TmxMapLoader();

	}

	@Override protected void inserted (int eid) {
		MapDef mapDef = mMapDef.get(eid);
		Map map = world.getEntity(eid).edit().create(Map.class);
		map.map = mapLoader.load(mapDef.path);
	}

	@Override protected void processSystem () {

	}
}
