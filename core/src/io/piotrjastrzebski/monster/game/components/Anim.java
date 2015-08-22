package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class Anim extends PooledComponent {
	public float time;
	public Animation animation;
	public boolean looping;

	@Override protected void reset () {
		animation = null;
		time = 0;
		looping = false;
	}
}
