package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Bounds extends PooledComponent {
	public Rectangle bounds = new Rectangle();
	@Override protected void reset () {
		bounds.set(0 , 0, 0, 0);
	}

	public Bounds set (float x, float y, float width, float height) {
		bounds.set(x, y, width, height);
		return this;
	}

	public Bounds set (float width, float height) {
		bounds.setSize(width, height);
		return this;
	}
}
