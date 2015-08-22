package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Status extends PooledComponent {
	public float health;
	public float armor;
	public float energy;

	@Override protected void reset () {
		health = 0;
		armor = 0;
		energy = 0;
	}

	public Status health (float health) {
		this.health = health;
		return this;
	}
}
