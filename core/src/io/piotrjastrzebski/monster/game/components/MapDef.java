package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class MapDef extends PooledComponent {
	public String path;

	@Override protected void reset () {
		path = null;
	}
}
