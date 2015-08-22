package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Rotation extends PooledComponent {
	public float rotation;
	public int dirX;
	public int dirY;

	@Override protected void reset () {
		rotation = 0;
	}

	public Rotation set (float rot) {
		rotation = rot;
		return this;
	}
}
