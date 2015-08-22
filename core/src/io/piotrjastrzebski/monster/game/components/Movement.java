package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Movement extends PooledComponent {
	public Vector2 vel = new Vector2();
	public Vector2 acc = new Vector2();

	@Override protected void reset () {
		vel.setZero();
		acc.setZero();
	}

	public Movement setVelocity (float vx, float vy) {
		vel.set(vx, vy);
		return this;
	}

	public Movement setAccelleration (float ax, float ay) {
		acc.set(ax, ay);
		return this;
	}
}
