package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class DeleteAfter extends PooledComponent {
	public float timer;
	public float after;
	@Override protected void reset () {
		timer = 0;
		after = 0;
	}

	public DeleteAfter after (float after) {
		this.after = after;
		return this;
	}
}
