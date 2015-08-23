package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Projectile extends PooledComponent {
	public float dmg;
	public float delay;
	public float timer;
	public float dst;

	@Override protected void reset () {
		dmg = 0;
		dst = 0;
		delay = 0;
		timer = 0;
	}

	public Projectile dmg (float dmg) {
		this.dmg = dmg;
		return this;
	}

	public Projectile dst (float dst) {
		this.dst = dst;
		return this;
	}

	public Projectile delay (float delay) {
		this.delay = delay;
		return this;
	}
}
