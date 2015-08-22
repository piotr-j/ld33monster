package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Player extends PooledComponent {
	public Vector2 moveAcc = new Vector2();
	public int score;
	@Override protected void reset () {
		moveAcc.setZero();
	}

	public Player setMoveAcc (float x, float y) {
		moveAcc.set(x, y);
		return this;
	}
}
