package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Map extends PooledComponent {
	public TiledMap map;

	@Override protected void reset () {
		map = null;
	}
}
