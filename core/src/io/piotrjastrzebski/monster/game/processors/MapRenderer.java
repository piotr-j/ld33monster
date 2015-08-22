package io.piotrjastrzebski.monster.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import io.piotrjastrzebski.monster.game.components.*;
import io.piotrjastrzebski.monster.screens.Game;

/**
 * Created by PiotrJ on 22/08/15.
 */
@Wire
public class MapRenderer extends EntitySystem {
	protected ComponentMapper<Map> mMap;
	@Wire(name = Game.WIRE_GAME_CAM) OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;

	public MapRenderer () {
		super(Aspect.all(Map.class));
	}

	@Override protected void inserted (int eid) {
		renderer = new OrthogonalTiledMapRenderer(mMap.get(eid).map, Game.INV_SCALE);
	}

	@Override protected void processSystem () {
		if (renderer == null) return;
		renderer.setView(camera);
		renderer.render();
	}
}
