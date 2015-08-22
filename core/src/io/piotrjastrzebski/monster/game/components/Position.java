package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Position extends PooledComponent {
	public Vector2 pos = new Vector2();
	@Override protected void reset () {
		pos.setZero();
	}

	public Position set (float x, float y) {
		pos.set(x, y);
		return this;
	}
}
