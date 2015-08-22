package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Dash extends PooledComponent {
	public float dst;
	public float delay;
	public float timer;

	@Override protected void reset () {
		dst = 0;
		delay = 0;
		timer = 0;
	}

	public Dash dst (float dst) {
		this.dst = dst;
		return this;
	}

	public Dash delay (float delay) {
		this.delay = delay;
		return this;
	}
}
