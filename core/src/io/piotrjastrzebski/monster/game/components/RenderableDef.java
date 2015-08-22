package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class RenderableDef extends PooledComponent {
	String path;
	@Override protected void reset () {
		path = "dummy";
	}
}
