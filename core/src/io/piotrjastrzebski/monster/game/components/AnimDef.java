package io.piotrjastrzebski.monster.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by PiotrJ on 22/08/15.
 */
public class AnimDef extends PooledComponent {
	public String path;
	public int count;
	public float frame;
	public boolean looping;
	public Animation.PlayMode mode = Animation.PlayMode.NORMAL;

	@Override protected void reset () {
		path = "dummy";
	}

	public AnimDef path (String path) {
		this.path = path;
		return this;
	}

	public AnimDef frame (float frame) {
		this.frame = frame;
		return this;
	}

	public AnimDef count (int count) {
		this.count = count;
		return this;
	}

	public AnimDef looping (boolean looping) {
		this.looping = looping;
		return this;
	}

	public AnimDef mode (Animation.PlayMode mode) {
		this.mode = mode;
		return this;
	}
}
