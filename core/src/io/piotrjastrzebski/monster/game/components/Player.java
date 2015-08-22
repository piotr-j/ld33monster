package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Player extends PooledComponent {
	public float accel;
	public int score;
	@Override protected void reset () {
		accel = 0;
	}

	public void setAccel (float accel) {
		this.accel = accel;
	}
}
