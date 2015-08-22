package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class RenderableDef extends PooledComponent {
	public String path;
	@Override protected void reset () {
		path = "dummy";
	}

	public RenderableDef path (String path) {
		this.path = path;
		return this;
	}
}
